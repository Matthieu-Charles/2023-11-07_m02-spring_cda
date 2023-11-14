package com.example.speciesrest.controller;

import com.example.speciesrest.PersonNotFoundException;
import com.example.speciesrest.DTO.PersonDTO;
import com.example.speciesrest.entity.Person;
import com.example.speciesrest.service.PersonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/person")
public class PersonController {

    @Autowired
    private PersonService personService;
    @GetMapping("")
    public Page<Person> findAll() {
        return this.personService.findAll(PageRequest.of(0, 3, Sort.by("name")));
    }
    @GetMapping("/{id}")
    public Person findById(@PathVariable("id") Integer id) throws PersonNotFoundException {
        return this.personService.findById(id);
    }
    @PostMapping("/create")
    public Person create(@RequestBody @Valid PersonDTO personDTO) {
        return this.personService.create(personDTO);
    }
    @PutMapping("/update")
    public Person update(@RequestBody @Valid PersonDTO personDTO) throws PersonNotFoundException {
        return this.personService.update(personDTO);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {
        this.personService.delete(id);
    }

}
