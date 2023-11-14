package com.example.speciesrest.DTO;

import com.example.speciesrest.entity.Person;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;
@Data
public class AnimalDTO {
    @Id
    private Integer id;
    @NotEmpty
    private String name;
    private String color;
    @Size(max=255)
    private String sex;
    @NotNull
    Integer species;
    Set<Person> persons;
}
