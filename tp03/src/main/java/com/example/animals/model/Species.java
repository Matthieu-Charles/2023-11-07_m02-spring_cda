package com.example.animals.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "species")
public class Species {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(columnDefinition = "varchar(50)")
    private String common_name;
    @Column(columnDefinition = "varchar(200)")
    private String latin_name;
    @OneToMany(mappedBy ="species")
    private Set<Animal> animals;

}
