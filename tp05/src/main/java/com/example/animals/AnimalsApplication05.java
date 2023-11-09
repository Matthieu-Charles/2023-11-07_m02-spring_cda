package com.example.animals;

import com.example.animals.model.Person;
import com.example.animals.model.Species;
import com.example.animals.repository.AnimalRepository;
import com.example.animals.repository.PersonRepository;
import com.example.animals.repository.SpeciesRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class AnimalsApplication05 implements CommandLineRunner {

    PersonRepository personRepository;
    AnimalRepository animalRepository;
    SpeciesRepository speciesRepository;
    public AnimalsApplication05(PersonRepository personRepository, AnimalRepository animalRepository, SpeciesRepository speciesRepository) {
        this.personRepository = personRepository;
        this.animalRepository = animalRepository;
        this.speciesRepository = speciesRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(AnimalsApplication05.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        List<Species> species = this.speciesRepository.findAllOrderByCommonNameAsc();
        System.out.println(species);

        List<Species> speciesLike = this.speciesRepository.findSpeciesWithCommonNameLike("Ch");
        System.out.println(speciesLike);

        List<Person> personListAgeMinAndMax = this.personRepository.findAllAgeBetweenMinAndMax(20,27);
        System.out.println(personListAgeMinAndMax);

        List<Person> p = this.personRepository.findOwnerByAnimal(this.animalRepository.findById(2).get());
        System.out.println(p);

        Integer nbrF = this.animalRepository.findNumberBySex("F");
        System.out.println("Nbr Female : " + nbrF);

        // Suppression du lien animalPerson pour l'animal id=6 au préalable
        Boolean isOwned = this.animalRepository.isOwned(this.animalRepository.findById(6).get());
        System.out.println("isOwned? : " + isOwned);

    }
}
