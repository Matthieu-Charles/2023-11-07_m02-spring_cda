package com.example.animals.repository;

public interface PersonRepositoryCustom {

    Integer deletePersonsWithoutAnimal();
    Integer insertRandomPerson(Integer numberOfEntities);

}
