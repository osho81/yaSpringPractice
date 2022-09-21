package com.osho.restBasics.service;

// Implement this interface in PersonService, and override/fll in its methods
// This interface is e.g. suitable for a fast overview of methods in PersonService

import com.osho.restBasics.model.Person;

import java.util.List;
import java.util.Optional;

public interface PersonServiceRepository {

    //////// READ (GET) ///////////
    Person getPerson(int id);
    List<Person> getAllPersons();

    /////// CREATE (SAVE) /////////
    Person createPerson(Person person);
    Person createPersonWithLogic(Person person);

    ///////// UPDATE /////////////
    Person updatePerson(Person person, int id);
    Person updatePersonWithLogic(Person person, int id);

    ///////// DELETE /////////////
    void deletePerson(int id);

}
