package com.example.speciesrest.DTO;

import com.example.speciesrest.entity.Animal;
import com.example.speciesrest.entity.Person;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.Set;

@Data
public class PersonDTO {
    @Id
    private Integer id;
    @Min(0)
    @Max(110)
    private Integer age;
    @NotEmpty
    @Size(max = 50)
    private String firstname;
    @NotEmpty
    @Size(max = 50)
    private String lastname;
    Set<Animal> animals;
}
