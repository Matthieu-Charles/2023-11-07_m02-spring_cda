package com.example.speciesmvc.controller;

import com.example.speciesmvc.entity.Animal;
import com.example.speciesmvc.entity.Person;
import com.example.speciesmvc.entity.Species;
import com.example.speciesmvc.repository.AnimalRepository;
import com.example.speciesmvc.repository.PersonRepository;
import com.example.speciesmvc.repository.SpeciesRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class PersonController {

    @Autowired
    private AnimalRepository animalRepository;
    @Autowired
    private SpeciesRepository speciesRepository;
    @Autowired
    private PersonRepository personRepository;

    @GetMapping("/person")
    public String listPerson(Model model) {
        List<Person> persons = personRepository.findAll();
        model.addAttribute("persons", persons);
        return "person/list_person";
    }
    @GetMapping("/person/{id}")
    public String getOneSpecie(@PathVariable Integer id, Model model) {
        Optional<Person> person = personRepository.findById(id);
        if(person.isPresent()) {
            model.addAttribute("person", person.get());
            model.addAttribute("animalsList", animalRepository.findAll(Sort.by(Sort.Direction.ASC, "name")));
            return "person/update_person";
        }
        else return "error";
    }
    @GetMapping("/person/create")
    public String getCreatePersonTemplate(Model model) {
        model.addAttribute(new Person());
        model.addAttribute("speciesList", speciesRepository.findAll(Sort.by(Sort.Direction.ASC, "commonName")));
        model.addAttribute("animalsList", animalRepository.findAll(Sort.by(Sort.Direction.ASC, "name")));
        return "person/create_person";
    }
    @PostMapping("/person")
    public String createOrUpdate(@Valid Person person, BindingResult result) {
        if(result.hasErrors()) {
            return "/person/create_person";
        }
        if(person.getAnimals() != null) {
            List<Animal> listAnimalEnBase = this.animalRepository.findAll();
            for (Animal a : listAnimalEnBase) {
                if(person.getAnimals().contains(a)){
                    a.getPersons().add(person);
                } else {
                    a.getPersons().remove(person);
                }
            }
        }
        this.personRepository.save(person);
        return "redirect:/person";
    }
    @GetMapping("/person/delete/{id}")
    public String delete(@PathVariable("id") Integer personId) {
        Optional<Person> optionalPersonToDelete = this.personRepository.findById(personId);
        optionalPersonToDelete.ifPresent(person -> {
            for(Animal a : person.getAnimals()) a.getPersons().remove(person);
            this.personRepository.delete(person);
        });
        return "redirect:/person";
    }
}
