package com.example.speciesrest.mapper;

import com.example.speciesrest.dto.AnimalDto;
import com.example.speciesrest.entity.Animal;
import com.example.speciesrest.entity.Person;
import com.example.speciesrest.repository.PersonRepository;
import com.example.speciesrest.repository.SpeciesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class AnimalMapper {

    @Autowired
    SpeciesRepository speciesRepository;

    @Autowired
    PersonRepository personRepository;

    public AnimalDto toDto(Animal animal) {
        AnimalDto animalDTO = new AnimalDto();
        animalDTO.setId(animal.getId());
        animalDTO.setName(animal.getName());
        animalDTO.setColor(animal.getColor());
        animalDTO.setSex(animal.getSex());
        animalDTO.setSpecies(animal.getSpecies().getCommonName());

        String listPersons = animal.getPersons().stream()
                .map(p -> p.getLastname() + " " + p.getFirstname())
                .collect(Collectors.joining(", "));
        animalDTO.setPersons(listPersons);

        return animalDTO;
    }

    public Animal toAnimal(AnimalDto animalDTO) {
        Animal animal = new Animal();

        animal.setId(animalDTO.getId());
        animal.setName(animalDTO.getName());
        animal.setColor(animalDTO.getColor());
        animal.setSpecies(this.speciesRepository.findByCommonName(animalDTO.getSpecies()).get());
        animal.setSex(animalDTO.getSex());

        Set<Person> setPerson = Arrays.stream(animalDTO.getPersons().split(","))
                .map(String::trim)
                .map(a -> a.split(" ")[1])
                .map(a -> personRepository.findByLastname(a))
                .collect(Collectors.toSet());

        animal.setPersons(setPerson);
        return animal;
    }
}
