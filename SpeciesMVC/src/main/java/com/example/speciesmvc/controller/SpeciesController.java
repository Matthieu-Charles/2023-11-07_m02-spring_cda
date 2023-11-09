package com.example.speciesmvc.controller;

import com.example.speciesmvc.entity.Animal;
import com.example.speciesmvc.entity.Species;
import com.example.speciesmvc.repository.AnimalRepository;
import com.example.speciesmvc.repository.SpeciesRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class SpeciesController {

    @Autowired
    private AnimalRepository animalRepository;
    @Autowired
    private SpeciesRepository speciesRepository;

    @GetMapping("/species")
    public String listSpecies(Model model) {
        List<Species> species = speciesRepository.findAll();
        model.addAttribute("species", species);
        return "species/list_species";
    }

    @GetMapping("/species/{id}")
    public String getOneSpecie(@PathVariable Integer id, Model model) {
        Optional<Species> species = speciesRepository.findById(id);
        if(species.isPresent()) {
            model.addAttribute("species", species.get());
            model.addAttribute("animalsList", animalRepository.findAll(Sort.by(Sort.Direction.ASC, "name")));
            return "species/update_species";
        }
        else return "error";
    }
    @GetMapping("/species/create")
    public String getCreateSpeciesTemplate(Model model) {
        model.addAttribute(new Species());
        model.addAttribute("speciesList", speciesRepository.findAll(Sort.by(Sort.Direction.ASC, "commonName")));
        model.addAttribute("animalsList", animalRepository.findAll(Sort.by(Sort.Direction.ASC, "name")));
        return "species/create_species";
    }

    @PostMapping("/species")
    public String createOrUpdate(@Valid Species species, BindingResult result, Model model) {
        if(result.hasErrors()) {
            model.addAttribute("animalsList", animalRepository.findAll(Sort.by(Sort.Direction.ASC, "name")));
            return "/species/create_species";
        }
        List<Animal> listAnimauxEnBase = this.animalRepository.findAll();
        for (Animal a : listAnimauxEnBase) {
            if(species.getAnimals().contains(a)){
                a.setSpecies(species);
            }
        }
        this.speciesRepository.save(species);
        return "redirect:/species";
    }
    @GetMapping("/species/delete/{id}")
    public String delete(@PathVariable("id") Integer speciesId) {
        Optional<Species> optionalSpeciesToDelete = this.speciesRepository.findById(speciesId);
        optionalSpeciesToDelete.ifPresent(species -> {
            for(Animal a : species.getAnimals()) a.setSpecies(null);
            this.speciesRepository.delete(species);
        });
        return "redirect:/species";
    }
}
