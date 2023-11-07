package com.example.animals.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "animal")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(columnDefinition = "varchar(50)")
    private String color;
    @Column(columnDefinition = "varchar(50)")
    private String name;
    @Column(columnDefinition = "varchar(255)")
    private String sex;
    @ManyToMany(mappedBy = "animals")
    Set<Person> persons;

    @ManyToOne
    @JoinColumn(name="species_id")
    Species species;

    @Override
    public String toString() {
        return "Animal{" +
                ", name='" + name + '\'' +
                '}';
    }
}
