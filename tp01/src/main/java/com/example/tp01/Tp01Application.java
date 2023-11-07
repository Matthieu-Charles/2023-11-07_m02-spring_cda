package com.example.tp01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Tp01Application {

    public static void main(String[] args) {
        SpringApplication.run(Tp01Application.class, args);
    }

    @GetMapping("hello/{nom}")
    public String hello(@PathVariable("nom") String nom) {
        return "Bonjour " + nom;
    }

}
