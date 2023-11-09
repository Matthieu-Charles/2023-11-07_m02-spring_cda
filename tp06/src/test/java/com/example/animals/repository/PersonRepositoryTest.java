package com.example.animals.repository;

import com.example.animals.model.Animal;
import com.example.animals.model.Person;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootTest
@Transactional
public class PersonRepositoryTest {

    @PersistenceContext
    private EntityManager em;
    
    @Autowired
    private PersonRepository personRepository;

    @BeforeEach
    public void initDate(){
        em.clear();

        Animal animal1 = new Animal();
        animal1.setName("Pilou");
        animal1.setSex("M");
        animal1.setColor("Rouge");
        em.persist(animal1);

        Set<Animal> listAnimals = new HashSet<>();
        listAnimals.add(animal1);
        Person p1 = new Person();
        p1.setAge(12);
        p1.setFirstname("Jean");
        p1.setLastname("Valjean");
        p1.setAnimals(listAnimals);
        System.out.println(p1);
        em.persist(p1);

        Set<Person> personSet = new HashSet<>();
        personSet.add(p1);
        animal1.setPersons(personSet);
        
        Person p2 = new Person();
        p2.setAge(18);
        p2.setFirstname("Ernesto");
        p2.setLastname("Truite");
        em.persist(p2);

        em.flush();
    }

    @Test
    public void findByAgeGreaterThanEqual(){
        List<Person> test1 = this.personRepository.findByAgeGreaterThanEqual(10);
        List<Person> test2 = this.personRepository.findByAgeGreaterThanEqual(13);
        Assertions.assertEquals(2, test1.size());
        Assertions.assertEquals(1, test2.size());
    }
    @Test
    public void findPersonAgeBetween(){
        List<Person> test1 = this.personRepository.findPersonAgeBetween(9,14);
        List<Person> test2 = this.personRepository.findPersonAgeBetween(12,18);
        Assertions.assertEquals(1, test1.size());
        Assertions.assertEquals(2, test2.size());
    }
//    @Test
//    public void findOwnerByAnimal(){
//        List<Person> test1 = this.personRepository.findOwnerByAnimal();
//        List<Person> test2 = this.personRepository.findOwnerByAnimal();
//        Assertions.assertEquals(1, test1.size());
//        Assertions.assertEquals(2, test2.size());
//    }
    @Test
    public void insertRandomPerson(){
        Integer test1 = this.personRepository.insertRandomPerson(5);
        Integer test2 = this.personRepository.insertRandomPerson(4);
        Assertions.assertEquals(5, test1);
        Assertions.assertEquals(4, test2);
    }
    @Test
    public void deletePersonsWithoutAnimal(){
        Integer test1 = this.personRepository.deletePersonsWithoutAnimal();
        Integer test2 = this.personRepository.deletePersonsWithoutAnimal();
        Assertions.assertEquals(1, test1);
        Assertions.assertEquals(0, test2);
    }

}
