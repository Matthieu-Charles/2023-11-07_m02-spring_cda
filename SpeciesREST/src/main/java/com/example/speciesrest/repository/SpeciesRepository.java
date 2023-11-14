package com.example.speciesrest.repository;

import com.example.speciesrest.entity.Species;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SpeciesRepository extends JpaRepository<Species, Integer> {
    @Query("select s from Species s order by s.commonName ASC")
    List<Species> findAllOrderByCommonNameAsc();
    @Query("select s from Species s where s.commonName like CONCAT('%',:commonName,'%')")
    List<Species> findSpeciesWithCommonNameLike(String commonName);
}