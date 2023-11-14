package com.example.speciesrest.repository;

public interface PersonRepositoryCustom {

    Integer deletePersonsWithoutAnimal();
    Integer insertRandomPerson(Integer numberOfEntities);

}
