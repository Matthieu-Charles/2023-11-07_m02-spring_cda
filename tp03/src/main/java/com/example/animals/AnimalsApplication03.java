package com.example.animals;

import com.example.animals.model.Animal;
import com.example.animals.model.Person;
import com.example.animals.repository.AnimalRepository;
import com.example.animals.repository.PersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

@SpringBootApplication
public class AnimalsApplication03 implements CommandLineRunner {

    PersonRepository personRepository;

    AnimalRepository animalRepository;

    public AnimalsApplication03(PersonRepository personRepository, AnimalRepository animalRepository) {
        this.personRepository = personRepository;
        this.animalRepository = animalRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(AnimalsApplication03.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        List<Person> list = this.personRepository.findAll();
        System.out.println("[1] - " + list);

        Person p = new Person();
        p.setAge(12);
        p.setFirstname("Jacques");
        p.setLastname("Haddock");

        Set<Animal> animals = new HashSet<>();
        this.animalRepository.findById(1).ifPresent(animals::add);
        this.animalRepository.findById(2).ifPresent(animals::add);
        this.animalRepository.findById(3).ifPresent(animals::add);

        p.setAnimals(animals);
        Person savedPerson = personRepository.save(p);

        System.out.println(savedPerson);
        System.out.println("[2] - " + this.personRepository.findAll());

        personRepository.deleteById(savedPerson.getId());
        System.out.println("[3] - " + this.personRepository.findAll());

    }
}
