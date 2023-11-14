package com.example.speciesrest.service;

import com.example.speciesrest.dto.PersonDto;
import com.example.speciesrest.entity.Animal;
import com.example.speciesrest.entity.Person;
import com.example.speciesrest.exception.EntityToCreateHasAnIdException;
import com.example.speciesrest.exception.EntityToCreateHasNoIdException;
import com.example.speciesrest.mapper.PersonMapper;
import com.example.speciesrest.repository.AnimalRepository;
import com.example.speciesrest.repository.PersonRepository;
import com.example.speciesrest.repository.SpeciesRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;
    @Autowired
    AnimalRepository animalRepository;
    @Autowired
    SpeciesRepository speciesRepository;
    @Autowired
    PersonMapper personMapper;

    public Person create(@Valid PersonDto personDTO) {
        if (personDTO.getId() != null) throw new EntityToCreateHasAnIdException("La personne à créer a déjà un id");
        Person personToSave = personMapper.toPerson(personDTO);
        return this.personRepository.save(personToSave);
    }
    public Person update(@Valid PersonDto personDTO) {
        if (personDTO.getId() == null) throw new EntityToCreateHasNoIdException("La personne à créer a déjà un id");
        Optional<Person> person = this.personRepository.findById(personDTO.getId());
        if(person.isPresent()) {
            Person person1 = person.get();
            List<Animal> listAnimalEnBase = this.animalRepository.findAll();
            for (Animal a : listAnimalEnBase) {
                if(person1.getAnimals().contains(a)){
                    a.getPersons().add(person1);
                } else {
                    a.getPersons().remove(person1);
                }
            }
            Person personMapped = personMapper.toPerson(personDTO);
            person1.setLastname(personMapped.getLastname());
            person1.setFirstname(personMapped.getFirstname());
            person1.setAge(personMapped.getAge());
            return this.personRepository.save(person1);
        } else {
            throw new EntityNotFoundException("personne avec l'id " + personDTO.getId() + " non trouvé");
        }
    }

    public void delete(Integer id) {
        Optional<Person> optionalPersonToDelete = this.personRepository.findById(id);
        optionalPersonToDelete.ifPresentOrElse(person -> {
                    for(Animal a : person.getAnimals()) a.getPersons().remove(person);
                    this.personRepository.delete(person);
                },
                () -> {
                    throw new EntityNotFoundException("personne avec l'id " + id + " non trouvé");
                }
        );
    }
    public Page<Person> findAll(Pageable pageable) {
        return this.personRepository.findAll(pageable);
    }
    public Person findById(Integer id) {
        return this.personRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("personne avec l'id " + id + " non trouvé"));
    }

}
