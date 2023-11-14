package com.example.speciesrest.mapper;

import com.example.speciesrest.dto.PersonDto;
import com.example.speciesrest.entity.Animal;
import com.example.speciesrest.entity.Person;
import com.example.speciesrest.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class PersonMapper {

    @Autowired
    AnimalRepository animalRepository;
    public PersonDto toDto(Person person) {
        PersonDto personDTO = new PersonDto();
        personDTO.setId(person.getId());
        personDTO.setName(person.getLastname().toUpperCase() + " " + person.getFirstname());
        personDTO.setAge(person.getAge());
        String[] animals = person.getAnimals()
                        .stream()
                        .map(Animal::getName)
                        .toArray(String[]::new);
        personDTO.setAnimals(animals);
        return personDTO;
    }

    public Person toPerson(PersonDto personDTO) {
        Person person = new Person();

        person.setId(personDTO.getId());
        String[] firstNameAndLastName = personDTO.getName().split(",");
        person.setFirstname(firstNameAndLastName[0]);
        person.setLastname(firstNameAndLastName[1]);
        person.setAge(personDTO.getAge());

        Set<Animal> setAnimal = Arrays.stream(personDTO.getAnimals())
                .map(a -> animalRepository.findByName(a))
                .collect(Collectors.toSet());

        person.setAnimals(setAnimal);
        return person;
    }
}
