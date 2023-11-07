package com.example.animals.repository;

import com.example.animals.model.Animal;
import com.example.animals.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    @Query("select p from Person p where p.age between :min and :max")
    List<Person> findAllAgeBetweenMinAndMax(Integer min, Integer max);
    @Query("select p from Person p where :animal MEMBER OF p.animals")
    List<Person> findByOwnedAnimal(Animal animal);
}