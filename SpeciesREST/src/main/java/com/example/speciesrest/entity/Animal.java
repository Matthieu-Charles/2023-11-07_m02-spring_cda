package com.example.speciesrest.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.util.Objects;
import java.util.Set;

@Getter
@Entity
@Table(name = "animal")
@JsonIgnoreProperties({ "species", "persons" })
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(columnDefinition = "varchar(50)")
    @NotEmpty
    private String name;
    @Column(columnDefinition = "varchar(50)")
    private String color;
    @Column(columnDefinition = "varchar(255)")
    @Size(max=255)
    private String sex;

    @ManyToOne
    @JoinColumn(name="species_id")
    @NotNull
    Species species;

    @ManyToMany(mappedBy = "animals")
    Set<Person> persons;
    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", sex='" + sex + '\'' +
                ", species=" + species +
                ", persons=" + persons +
                '}';
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setPersons(Set<Person> persons) {
        this.persons = persons;
    }

    public void setSpecies(Species species) {
        this.species = species;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return Objects.equals(getId(), animal.getId()) && Objects.equals(getName(), animal.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }
}
