package com.example.speciesrest.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.util.Set;

@Getter
@Entity
@Table(name = "species")
public class Species {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "common_Name", columnDefinition = "varchar(50)")
    @NotEmpty
    @Size(max = 50)
    private String commonName;
    @Column(name = "latin_Name", columnDefinition = "varchar(200)")
    @NotEmpty
    @Size(max = 200)
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

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public void setLatinName(String latinName) {
        this.latinName = latinName;
    }

    public void setAnimals(Set<Animal> animals) {
        this.animals = animals;
    }
}
