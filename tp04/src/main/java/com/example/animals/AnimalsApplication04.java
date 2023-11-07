package com.example.animals;

import com.example.animals.model.Animal;
import com.example.animals.model.Person;
import com.example.animals.model.Species;
import com.example.animals.repository.AnimalRepository;
import com.example.animals.repository.PersonRepository;
import com.example.animals.repository.SpeciesRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class AnimalsApplication04 implements CommandLineRunner {

    PersonRepository personRepository;
    AnimalRepository animalRepository;
    SpeciesRepository speciesRepository;
    public AnimalsApplication04(PersonRepository personRepository, AnimalRepository animalRepository, SpeciesRepository speciesRepository) {
        this.personRepository = personRepository;
        this.animalRepository = animalRepository;
        this.speciesRepository = speciesRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(AnimalsApplication04.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Species species =  this.speciesRepository.findFirstByCommonName("Chat");
        System.out.println(species);
        List<Species> speciesList = this.speciesRepository.findAllByLatinNameContainingIgnoreCase("lu");
        System.out.println(speciesList);

        List<Person> personList = this.personRepository.findByLastnameContainsOrFirstnameContains("Lamarque", "o");
        System.out.println(personList);
        List<Person> personAgeList = this.personRepository.findByAgeGreaterThanEqual(33);
        System.out.println(personAgeList);

        List<Animal> listAnimalSpecie = this.animalRepository.findBySpecies(this.speciesRepository.findById(1).get());
        System.out.println(listAnimalSpecie);

        List<String> colorsList = new ArrayList<>();
        colorsList.add("Noir");
        colorsList.add("Brun");
        colorsList.add("Blanc");

        List<Animal> listAnimalColor = this.animalRepository.findAllByColorList(colorsList);
        System.out.println("listAnimalColor : " + listAnimalColor);

        Optional<Animal> animal = this.animalRepository.findById(1);
        System.out.println(animal);


    }
}
