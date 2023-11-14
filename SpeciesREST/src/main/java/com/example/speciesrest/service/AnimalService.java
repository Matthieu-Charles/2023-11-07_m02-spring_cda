package com.example.speciesrest.service;

import com.example.speciesrest.AnimalNotFoundException;
import com.example.speciesrest.DTO.AnimalDTO;
import com.example.speciesrest.entity.Animal;
import com.example.speciesrest.entity.Person;
import com.example.speciesrest.entity.Species;
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
public class AnimalService {

    @Autowired
    AnimalRepository animalRepository;
    @Autowired
    PersonRepository personRepository;
    @Autowired
    SpeciesRepository speciesRepository;

    public Animal create(@Valid AnimalDTO animalDTO) {
        Animal animalToSave = new Animal();
        animalToSave.setName(animalDTO.getName());
        animalToSave.setColor(animalDTO.getColor());
        Optional<Species> species = speciesRepository.findById(animalDTO.getSpecies());
        species.ifPresent(animalToSave::setSpecies);
        System.out.println(species);
        animalToSave.setSex(animalDTO.getSex());
        return this.animalRepository.save(animalToSave);
    }
    public Animal update(@Valid AnimalDTO animalDTO) throws AnimalNotFoundException {
        Optional<Animal> animal = this.animalRepository.findById(animalDTO.getId());
        if(animal.isPresent()) {
            Animal animal1 = animal.get();
            List<Person> listPersonEnBase = this.personRepository.findAll();
            for (Person p : listPersonEnBase) {
                if(animalDTO.getPersons().contains(p)){
                    p.getAnimals().add(animal1);
                } else {
                    p.getAnimals().remove(animal1);
                }
            }
            animal1.setName(animalDTO.getName());
            animal1.setColor(animalDTO.getColor());
            animal1.setSex(animalDTO.getSex());
            Optional<Species> species = speciesRepository.findById(animalDTO.getSpecies());
            species.ifPresent(animal1::setSpecies);
            animal1.setSex(animalDTO.getSex());
            return this.animalRepository.save(animal1);
        } else {
            throw new AnimalNotFoundException("Il n'existe pas d'animal avec cet id");
        }
    }
    public void delete(Integer id) {
        Optional<Animal> optionalAnimalToDelete = this.animalRepository.findById(id);
        optionalAnimalToDelete.ifPresent(animal -> {
            for(Person p : animal.getPersons()) p.getAnimals().remove(animal);
            this.animalRepository.delete(animal);
        });
    }
    public Page<Animal> findAll(Pageable pageable) {
        return this.animalRepository.findAll(pageable);
    }
    public Animal findById(Integer id) throws AnimalNotFoundException {
        return this.animalRepository.findById(id).orElseThrow(() -> new AnimalNotFoundException("animal avec l'id " + id + " non trouvé"));
    }

}
