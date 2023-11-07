package com.example.animals.repository;

import com.example.animals.model.Species;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpeciesRepository extends JpaRepository<Species, Integer> {

    Species findFirstByCommonName(String commonName);
    List<Species> findAllByLatinNameContainingIgnoreCase(String latinName);
}