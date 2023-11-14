package com.example.speciesrest.service;

import com.example.speciesrest.dto.AnimalDto;
import com.example.speciesrest.entity.Animal;
import com.example.speciesrest.entity.Person;
import com.example.speciesrest.entity.Species;
import com.example.speciesrest.exception.EntityToCreateHasAnIdException;
import com.example.speciesrest.exception.EntityToCreateHasNoIdException;
import com.example.speciesrest.mapper.AnimalMapper;
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
public class AnimalService {

    @Autowired
    AnimalRepository animalRepository;
    @Autowired
    PersonRepository personRepository;
    @Autowired
    SpeciesRepository speciesRepository;
    @Autowired
    AnimalMapper animalMapper;

    public Animal create(@Valid AnimalDto animalDTO) {
        if (animalDTO.getId() != null) throw new EntityToCreateHasAnIdException("L'animal à créer a déjà un id");
        Animal animalToSave = new Animal();
        animalToSave.setName(animalDTO.getName());
        animalToSave.setColor(animalDTO.getColor());
        Optional<Species> species = speciesRepository.findByCommonName(animalDTO.getSpecies());
        species.ifPresentOrElse(
                animalToSave::setSpecies,
                () -> {
                    throw new RuntimeException("species non présente");
                }
        );
        animalToSave.setSex(animalDTO.getSex());
        return this.animalRepository.save(animalToSave);
    }
    public Animal update(@Valid AnimalDto animalDTO) {
        if (animalDTO.getId() == null) throw new EntityToCreateHasNoIdException("L'animal à mettre à jour n'a pas d'id");
        Optional<Animal> animal = this.animalRepository.findById(animalDTO.getId());
        if(animal.isPresent()) {
            Animal animal1 = animal.get();
            List<Person> listPersonEnBase = this.personRepository.findAll();
            Animal animal2 = animalMapper.toAnimal(animalDTO);
            System.out.println(animal2.getPersons());
            for (Person p : listPersonEnBase) {
                if(animal2.getPersons().contains(p)){
                    p.getAnimals().add(animal1);
                } else {
                    p.getAnimals().remove(animal1);
                }
            }
            animal1.setName(animal2.getName());
            animal1.setColor(animal2.getColor());
            animal1.setSex(animal2.getSex());
            Optional<Species> species = speciesRepository.findById(animal2.getSpecies().getId());
            species.ifPresent(animal1::setSpecies);
            animal1.setSex(animal2.getSex());
            return this.animalRepository.save(animal1);
        } else {
            throw new EntityNotFoundException("animal avec l'id " + animalDTO.getId() + " non trouvé");
        }
    }
    public void delete(Integer id) {
        Optional<Animal> optionalAnimalToDelete = this.animalRepository.findById(id);
        optionalAnimalToDelete.ifPresentOrElse(animal -> {
            for(Person p : animal.getPersons()) p.getAnimals().remove(animal);
            this.animalRepository.delete(animal);
            },
            () -> {
            throw new EntityNotFoundException("animal avec l'id " + id + " non trouvé");
            }
        );
    }
    public Page<Animal> findAll(Pageable pageable) {
        return this.animalRepository.findAll(pageable);
    }
    public Animal findById(Integer id) {
        return this.animalRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("animal avec l'id " + id + " non trouvé"));
    }

}
