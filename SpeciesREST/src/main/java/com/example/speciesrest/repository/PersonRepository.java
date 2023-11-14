package com.example.speciesrest.repository;

import com.example.speciesrest.entity.Animal;
import com.example.speciesrest.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Integer>, PersonRepositoryCustom {
    @Query("select p from Person p where p.age between :min and :max")
    List<Person> findAllAgeBetweenMinAndMax(Integer min, Integer max);
    @Query("select p from Person p where :animal MEMBER OF p.animals")
    List<Person> findOwnerByAnimal(Animal animal);
    Person findByLastname(String a);
}