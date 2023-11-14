package com.example.speciesrest;

import com.example.speciesrest.controller.AnimalController;
import com.example.speciesrest.repository.AnimalRepository;
import com.example.speciesrest.repository.PersonRepository;
import com.example.speciesrest.repository.SpeciesRepository;
import com.example.speciesrest.service.AnimalService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class SpeciesRestApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpeciesRestApplication.class, args);
	}

	PersonRepository personRepository;
	AnimalRepository animalRepository;
	SpeciesRepository speciesRepository;
	AnimalService animalService;

	AnimalController animalController;
	public SpeciesRestApplication(PersonRepository personRepository, AnimalRepository animalRepository, SpeciesRepository speciesRepository, AnimalController animalController, AnimalService animalService) {
		this.personRepository = personRepository;
		this.animalRepository = animalRepository;
		this.speciesRepository = speciesRepository;
		this.animalController = animalController;
		this.animalService = animalService;
	}

	@Override
	public void run(String... args) throws Exception {

		System.out.println("dans run");
		this.animalController.getTest();

//		this.animalService.create(null);
		this.animalService.findById(2);
	}

}
