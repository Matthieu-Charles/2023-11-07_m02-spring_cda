package com.example.speciesmvc.repository;

import com.example.speciesmvc.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AnimalRepository extends JpaRepository<Animal, Integer> {
    @Query("Select count(a) from Animal a where a.sex = :sex")
    Integer findNumberBySex(String sex);

    @Query("Select CASE WHEN EXISTS (SELECT p from Person p WHERE :animal MEMBER of p.animals) "
            + "THEN true "
            + "ELSE false end")
    Boolean isOwned(Animal animal);
}