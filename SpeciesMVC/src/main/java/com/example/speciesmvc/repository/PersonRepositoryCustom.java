package com.example.speciesmvc.repository;

public interface PersonRepositoryCustom {

    Integer deletePersonsWithoutAnimal();
    Integer insertRandomPerson(Integer numberOfEntities);

}
