package com.example.animals.repository;

import com.example.animals.model.Animal;
import com.example.animals.model.Species;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AnimalRepository extends JpaRepository<Animal, Integer> {

    List<Animal> findBySpecies(Species species);

    @Query("Select a from Animal a where a.color in :colors")
    List<Animal> findAllByColorList(List<String> colors);

}