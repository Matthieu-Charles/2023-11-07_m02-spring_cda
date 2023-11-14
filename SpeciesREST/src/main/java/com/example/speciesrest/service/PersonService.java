package com.example.speciesrest.service;

import com.example.speciesrest.PersonNotFoundException;
import com.example.speciesrest.DTO.PersonDTO;
import com.example.speciesrest.entity.Animal;
import com.example.speciesrest.entity.Person;
import com.example.speciesrest.repository.AnimalRepository;
import com.example.speciesrest.repository.PersonRepository;
import com.example.speciesrest.repository.SpeciesRepository;
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

    public Person create(@Valid PersonDTO personDTO) {
        Person personToSave = new Person();
        personToSave.setAge(personDTO.getAge());
        personToSave.setFirstname(personDTO.getFirstname());
        personToSave.setLastname(personDTO.getLastname());
        return this.personRepository.save(personToSave);
    }
    public Person update(@Valid PersonDTO personDTO) throws PersonNotFoundException {
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
            person1.setLastname(personDTO.getLastname());
            person1.setFirstname(personDTO.getFirstname());
            person1.setAge(personDTO.getAge());
            return this.personRepository.save(person1);
        } else {
            throw new PersonNotFoundException("Il n'existe pas d'person avec cet id");
        }
    }

    public void delete(Integer id) {
        Optional<Person> optionalPersonToDelete = this.personRepository.findById(id);
        optionalPersonToDelete.ifPresent(person -> {
            for(Animal a : person.getAnimals()) a.getPersons().remove(person);
            this.personRepository.delete(person);
        });
    }
    public Page<Person> findAll(Pageable pageable) {
        return this.personRepository.findAll(pageable);
    }
    public Person findById(Integer id) throws PersonNotFoundException {
        return this.personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException("person avec l'id " + id + " non trouvé"));
    }

}
