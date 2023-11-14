package com.example.speciesrest.controller;

import com.example.speciesrest.dto.AnimalDto;
import com.example.speciesrest.entity.Animal;
import com.example.speciesrest.mapper.AnimalMapper;
import com.example.speciesrest.service.AnimalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/animal")
public class AnimalController {

    @Autowired
    private AnimalService animalService;
    @Autowired
    private AnimalMapper animalMapper;

    @GetMapping("/{id}")
    public AnimalDto findById(@PathVariable("id") Integer id) {
        return animalMapper.toDto(this.animalService.findById(id));
    }
    @PostMapping("/create")
    public AnimalDto create(@RequestBody @Valid AnimalDto animalDTO) {
        return animalMapper.toDto(this.animalService.create(animalDTO));
    }
    @PutMapping("/update")
    public AnimalDto update(@RequestBody @Valid AnimalDto animalDTO) {
        return animalMapper.toDto(this.animalService.update(animalDTO));
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {
        this.animalService.delete(id);
    }

    @GetMapping("")
    public Page<Animal> findPage(
            @RequestParam(value= "pageNumber", defaultValue = "0") Integer pageNumber,
            @RequestParam(value= "pageSize", defaultValue = "20") Integer pageSize
    ) {
        return animalService.findAll(PageRequest.of(pageNumber, pageSize));
    }
    public void getTest() {}

}
