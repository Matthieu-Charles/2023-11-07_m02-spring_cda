package com.example.speciesmvc.controller;

import com.example.speciesmvc.entity.Animal;
import com.example.speciesmvc.entity.Person;
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
public class AnimalController {

    @Autowired
    private AnimalRepository animalRepository;
    @Autowired
    private SpeciesRepository speciesRepository;
    @Autowired
    private PersonRepository personRepository;

    @GetMapping("/animal")
    public String listAnimal(Model model) {
        List<Animal> animals = animalRepository.findAll();
        model.addAttribute("animals", animals);
        return "animal/list_animal";
    }

    @GetMapping("/animal/{id}")
    public String getOneSpecie(@PathVariable Integer id, Model model) {
        Optional<Animal> animal = animalRepository.findById(id);
        if(animal.isPresent()) {
            model.addAttribute("animal", animal.get());
            model.addAttribute("speciesList", speciesRepository.findAll(Sort.by(Sort.Direction.ASC, "commonName")));
            model.addAttribute("ownersList", personRepository.findAll(Sort.by(Sort.Direction.ASC, "firstname")));
            return "animal/update_animal";
        }
        else return "error";
    }
    @GetMapping("/animal/create")
    public String getCreateAnimalTemplate(Model model) {
        model.addAttribute(new Animal());
        model.addAttribute("speciesList", speciesRepository.findAll(Sort.by(Sort.Direction.ASC, "commonName")));
        model.addAttribute("ownersList", personRepository.findAll(Sort.by(Sort.Direction.ASC, "firstname")));
        return "animal/create_animal";
    }

    @PostMapping("/animal")
    public String createOrUpdate(@Valid Animal animal, BindingResult result) {
        if(result.hasErrors()) {
            return "/animal/create_animal";
        }
        List<Person> listPersonEnBase = this.personRepository.findAll();
        for (Person p : listPersonEnBase) {
            if(animal.getPersons().contains(p)){
                p.getAnimals().add(animal);
            } else {
                p.getAnimals().remove(animal);
            }
        }
        this.animalRepository.save(animal);
        return "redirect:/animal";
    }
    @GetMapping("/animal/delete/{id}")
    public String delete(@PathVariable("id") Integer animalId) {
        Optional<Animal> optionalAnimalToDelete = this.animalRepository.findById(animalId);
        optionalAnimalToDelete.ifPresent(animal -> {
            for(Person p : animal.getPersons()) p.getAnimals().remove(animal);
            this.animalRepository.delete(animal);
        });
        return "redirect:/animal";
    }

}
