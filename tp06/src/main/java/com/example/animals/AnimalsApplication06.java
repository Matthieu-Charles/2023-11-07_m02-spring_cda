package com.example.animals;

import com.example.animals.repository.AnimalRepository;
import com.example.animals.repository.PersonRepository;
import com.example.animals.repository.SpeciesRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AnimalsApplication06 implements CommandLineRunner {

    PersonRepository personRepository;
    AnimalRepository animalRepository;
    SpeciesRepository speciesRepository;
    public AnimalsApplication06(PersonRepository personRepository, AnimalRepository animalRepository, SpeciesRepository speciesRepository) {
        this.personRepository = personRepository;
        this.animalRepository = animalRepository;
        this.speciesRepository = speciesRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(AnimalsApplication06.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Integer suppression= this.personRepository.deleteAllNotOwningAnimal();
        System.out.println(suppression);

        Integer personCreated = this.personRepository.createEntities(2);
        System.out.println(personCreated);

    }
}
