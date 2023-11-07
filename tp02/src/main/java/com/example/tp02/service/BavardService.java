package com.example.tp02.service;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class BavardService {
    private String nom;

    public String parler() {
        return this.nom + " " + this.getClass().getSimpleName();
    }

    @PostConstruct
    private void postConstruct() {
        System.out.println("postConstruct appelé dans BavardService");
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
