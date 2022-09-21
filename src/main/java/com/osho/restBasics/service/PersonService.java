package com.osho.restBasics.service;

import com.osho.restBasics.exception.ResourceNotFoundException;
import com.osho.restBasics.model.Person;
import com.osho.restBasics.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// In this class the core parts of the method is done; the logic (including if null etc.)
// The methods here gets called by the PersonController
// The methods here uses PersonRepository for the crud operation itself

@Service
public class PersonService implements PersonServiceRepository {

    @Autowired // Autowire this declaration OR autowire a constructor
    private PersonRepository personRepository;


    //////////////////// READ (GET) ////////////////////

    @Override
    public Person getPerson(int id) {
        Optional<Person> foundPerson = personRepository.findById(id);
        if (foundPerson.isPresent()) {
            return foundPerson.get();
        } else {
//            throw new IllegalStateException("No person with id " + id + " exists");
            // Testing the customized exception class
            throw new ResourceNotFoundException("Person", "id ", id);
        }
    }

    @Override
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    // GET and SORT

    @Override // Find all persons, and sort ascending
    public List<Person> getAllPersonsAsc() {
        return personRepository.findByOrderByNameAsc();
    }

    @Override // Find all persons, and sort descending
    public List<Person> getAllPersonsDesc() {
        return personRepository.findByOrderByNameDesc();
    }

    // Find person with certain field and sort by certain field; example method
    @Override
    public List<Person> findByNameOrderByAgeAsc(String name) {
        return personRepository.findByNameOrderByAgeAsc(name);
    }

    @Override
    public List<Person> findByNameOrderByAgeDesc(String name) {
        return personRepository.findByNameOrderByAgeDesc(name);
    }


    //////////////////// CREATE (SAVE) ////////////////////
    @Override
    public Person createPerson(Person person) {
        // Ensure a person with same email doesn't already exist
        Optional<Person> foundByEmail = personRepository.findPersonByEmail(person.getEmail());
        if (foundByEmail.isPresent()) {
            throw new IllegalStateException(person.getEmail() + " is used by another person");
        } else {
            System.out.println(person.getName() + " is added");
            personRepository.save(person);
        }
        return personRepository.save(person);
    }

    @Override
    public Person createPersonWithLogic(Person person) {
        Optional<Person> foundByEmail = personRepository.findPersonByEmail(person.getEmail());
        if (foundByEmail.isPresent()) {
            throw new IllegalStateException(person.getEmail() + " is used by another person");
        } else {
            // Before saving, check if is an adult, and if name-field is included
            if (person.getName().isEmpty() || person.getName() == null) {
                throw new IllegalStateException("Name-field is empty");
            }
            if (person.getAge() < 18) {
                throw new IllegalStateException("Only adults are allowed to register");
            }
            System.out.println(person.getName() + " is added");
            personRepository.save(person);
        }
        return personRepository.save(person);
    }


    //////////////////// UPDATE ////////////////////

    @Override
    public Person updatePerson(Person person, int id) {
        // Find person to update, i.e. the person with same id as the passed-in-person
        Person personToUpdate = personRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("No person with id " + id + " exists"));

        // If personToUpdate is found, enter the fields from the person-argument into the personToUpdate
        personToUpdate.setName(person.getName());
        personToUpdate.setAge(person.getAge());
        personToUpdate.setEmail(person.getEmail());
        return personRepository.save(personToUpdate);
    }

    // Update method version 2, with logic: checks if email occupied, name missing & age>=18
    @Override
    public Person updatePersonWithLogic(Person person, int id) {
        // Find person to update, i.e. the person with same id as the passed-in-person
        Person personToUpdate = personRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("No person with id " + id + " exists"));

        // If the passed-in-person's name-field is NOT empty or null, set it as new name
        if (person.getName().isEmpty() || person.getName() == null) {
            throw new IllegalStateException("Name-field is empty");
        } else {
            personToUpdate.setName(person.getName());
        }

        // If the passed-in-person's age-field is above 18/is adult, set it as the new age
        if (person.getAge() < 18) {
            throw new IllegalStateException("Only adults are allowed to register");
        } else {
            personToUpdate.setAge(person.getAge());
        }

        // Check if the email is already in use by any other person/user
        Optional<Person> foundByEmail = personRepository.findPersonByEmail(person.getEmail());
        if (foundByEmail.isPresent()) {
            throw new IllegalStateException(person.getEmail() + " is already in use");
        } else if (person.getEmail().isEmpty() || person.getEmail() == null) {
            throw new IllegalStateException("Email-field is empty");
        } else {
            // If email-field is not empty and not occupied: Set old email to new passed in email
            personToUpdate.setEmail(person.getEmail());
        }
        return personRepository.save(personToUpdate);
    }


    //////////////////// DELETE ////////////////////

    // Delete method - simple version (when no issue with fk constraints exists yet)
    @Override
    public void deletePerson(int id) {
        // Check if person with this id exist, or else throw error/stop method
        personRepository.findById(id).orElseThrow(() -> new IllegalStateException("No id " + id + " in database."));
        personRepository.deleteById(id); // If exists, delete

        // Alternatively: assign person here if person exists, then delete the person
//        Person personToDelete = personRepository.findById(id).orElseThrow(
//                () -> new IllegalStateException("No id " + id + " in database."));
//        personRepository.delete(personToDelete);
    }

}
