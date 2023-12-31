package com.example.speciesmvc.repository;

import com.example.speciesmvc.entity.Animal;
import com.example.speciesmvc.entity.Species;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AnimalRepository extends JpaRepository<Animal, Integer> {
    @Query("Select count(a) from Animal a where a.sex = :sex")
    Integer findNumberBySex(String sex);

    @Query("Select CASE WHEN EXISTS (SELECT p from Person p WHERE :animal MEMBER of p.animals) "
            + "THEN true "
            + "ELSE false end")
    Boolean isOwned(Animal animal);

    List<Animal> findBySpecies(Species species);
}