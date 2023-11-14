package com.example.speciesrest.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AnimalDto {
    @Id
    private Integer id;
    @NotEmpty
    private String name;
    @NotNull
    String species;
    private String color;
    @Size(max=255)
    private String sex;
    String persons;
}
