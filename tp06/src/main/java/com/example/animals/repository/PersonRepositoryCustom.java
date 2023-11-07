package com.example.animals.repository;

public interface PersonRepositoryCustom {

    Integer deleteAllNotOwningAnimal();
    Integer createEntities(Integer numberOfEntities);

}
