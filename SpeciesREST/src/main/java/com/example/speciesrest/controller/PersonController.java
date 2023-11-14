package com.example.speciesrest.controller;

import com.example.speciesrest.dto.PersonDto;
import com.example.speciesrest.entity.Person;
import com.example.speciesrest.mapper.PersonMapper;
import com.example.speciesrest.service.PersonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/person")
public class PersonController {

    @Autowired
    private PersonService personService;
    @Autowired
    private PersonMapper personMapper;
    @GetMapping("/{id}")
    public PersonDto findById(@PathVariable("id") Integer id) {
        return this.personMapper.toDto(this.personService.findById(id));
    }
    @PostMapping("/create")
    public PersonDto create(@RequestBody @Valid PersonDto personDTO) {
        return this.personMapper.toDto(this.personService.create(personDTO));
    }
    @PutMapping("/update")
    public PersonDto update(@RequestBody @Valid PersonDto personDTO) {
        return this.personMapper.toDto(this.personService.update(personDTO));
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {
        this.personService.delete(id);
    }

    @GetMapping("")
    public Page<Person> findPage(
            @RequestParam(value= "pageNumber", defaultValue = "0") Integer pageNumber,
            @RequestParam(value= "pageSize", defaultValue = "20") Integer pageSize
    ) {
        return personService.findAll(PageRequest.of(pageNumber, pageSize));
    }

}
