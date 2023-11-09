package com.example.animals.repository;

import com.example.animals.model.Person;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Random;

public class PersonRepositoryCustomImpl implements PersonRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Integer deletePersonsWithoutAnimal() {
        TypedQuery<Person> typedQuery = em.createQuery("Select p from Person p", Person.class);
        List<Person> listPerson = typedQuery.getResultList();
        Integer compteurSuppression = 0;
        for(Person p: listPerson) {
            if(p.getAnimals() == null || p.getAnimals().isEmpty()) {
                em.remove(p);
                compteurSuppression++;
            }
        }
        return compteurSuppression;
    }

    @Override
    @Transactional
    public Integer insertRandomPerson(Integer numberOfEntities) {

        String[] firstNames = {"Jean", "Franck", "Ernesto", "Timoté", "Matilda"};
        String[] lastNames = {"Carabit", "Oddet", "Mage", "Bellamy", "Renard"};


        Integer compteur = 0;
        while(compteur < numberOfEntities){
            Random random = new Random();
            String firstName = firstNames[random.nextInt(firstNames.length)];
            String lastName = lastNames[random.nextInt(lastNames.length)];
            Person person = new Person();
            person.setFirstname(firstName);
            person.setLastname(lastName);
            person.setAge(random.nextInt(10, 90));
            em.persist(person);
            compteur++;
        }

        return compteur;
    }
}
