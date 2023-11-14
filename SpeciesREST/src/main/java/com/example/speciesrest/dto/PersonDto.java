package com.example.speciesrest.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class PersonDto {
    @Id
    private Integer id;
    @NotEmpty
    @Size(max = 50)
    private String name;
    @Min(0)
    @Max(110)
    private Integer age;
    String[] animals;
}
