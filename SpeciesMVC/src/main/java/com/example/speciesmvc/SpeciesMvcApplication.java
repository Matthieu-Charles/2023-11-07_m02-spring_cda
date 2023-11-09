package com.example.speciesmvc;

import com.example.speciesmvc.entity.Person;
import com.example.speciesmvc.entity.Species;
import com.example.speciesmvc.repository.AnimalRepository;
import com.example.speciesmvc.repository.PersonRepository;
import com.example.speciesmvc.repository.SpeciesRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class SpeciesMvcApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpeciesMvcApplication.class, args);
	}

	PersonRepository personRepository;
	AnimalRepository animalRepository;
	SpeciesRepository speciesRepository;
	public SpeciesMvcApplication(PersonRepository personRepository, AnimalRepository animalRepository, SpeciesRepository speciesRepository) {
		this.personRepository = personRepository;
		this.animalRepository = animalRepository;
		this.speciesRepository = speciesRepository;
	}

	@Override
	public void run(String... args) throws Exception {



	}

}
