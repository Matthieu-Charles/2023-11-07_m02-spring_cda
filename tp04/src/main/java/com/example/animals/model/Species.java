package com.example.animals.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "species")
public class Species {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "common_Name", columnDefinition = "varchar(50)")
    private String commonName;
    @Column(name = "latin_Name", columnDefinition = "varchar(200)")
    private String latinName;
    @OneToMany(mappedBy ="species")
    private Set<Animal> animals;

    @Override
    public String toString() {
        return "Species{" +
                "id=" + id +
                ", commonName='" + commonName + '\'' +
                ", latinName='" + latinName + '\'' +
                '}';
    }
}
