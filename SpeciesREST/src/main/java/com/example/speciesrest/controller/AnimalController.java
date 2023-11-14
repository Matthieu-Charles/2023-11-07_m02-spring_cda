package com.example.speciesrest.controller;

import com.example.speciesrest.AnimalNotFoundException;
import com.example.speciesrest.DTO.AnimalDTO;
import com.example.speciesrest.entity.Animal;
import com.example.speciesrest.service.AnimalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/animal")
public class AnimalController {

    @Autowired
    private AnimalService animalService;
    @GetMapping("")
    public Page<Animal> findAll() {
        return this.animalService.findAll(PageRequest.of(0, 3, Sort.by("name")));
    }
    @GetMapping("/{id}")
    public Animal findById(@PathVariable("id") Integer id) throws AnimalNotFoundException {
        return this.animalService.findById(id);
    }
    @PostMapping("/create")
    public Animal create(@RequestBody @Valid AnimalDTO animalDTO) {
        return this.animalService.create(animalDTO);
    }
    @PutMapping("/update")
    public Animal update(@RequestBody @Valid AnimalDTO animalDTO) throws AnimalNotFoundException {
        return this.animalService.update(animalDTO);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {
        this.animalService.delete(id);
    }

    public void getTest() {}

}
