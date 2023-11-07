package com.example.animals.repository;

import com.example.animals.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Integer> {

    List<Person> findByLastnameContainsOrFirstnameContains(String lastName, String FirstName);
    List<Person> findByAgeGreaterThanEqual(Integer age);



}