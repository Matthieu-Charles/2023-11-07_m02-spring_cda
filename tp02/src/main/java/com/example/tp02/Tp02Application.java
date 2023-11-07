package com.example.tp02;

import com.example.tp02.service.BavardService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Tp02Application {

    BavardService bavardService;
    public static void main(String[] args) {
        SpringApplication.run(Tp02Application.class, args);
    }

    public Tp02Application(BavardService bavardService)  {
        this.bavardService = bavardService;
    }

    @GetMapping("hello/{nom}")
    public String hello(@PathVariable("nom") String nom) {
            return "Bonjour " + nom;
    }

    @GetMapping("/blabla")
    public String parler() {
        return bavardService.parler();
    }

}
