package com.example.animals.repository;

import com.example.animals.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AnimalRepository extends JpaRepository<Animal, Integer> {

    Optional<Animal> findById(Integer integer);

    @Query("Select count(a) from Animal a where a.sex = :sex")
    Integer findNumberBySex(String sex);

    @Query("Select CASE WHEN EXISTS (SELECT p from Person p WHERE :animal MEMBER of p.animals) "
            + "THEN true "
            + "ELSE false end")
    Boolean isOwned(Animal animal);
}