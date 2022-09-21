package com.osho.restBasics.controller;

import com.osho.restBasics.model.Person;
import com.osho.restBasics.repository.PersonRepository;
import com.osho.restBasics.service.PersonService;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/person") // i.e. http://localhost:8080/api/v1/customer
public class PersonController {

    @Autowired
    private PersonService personService;

//    @Autowired
//    private PersonRepository personRepository;



    //////////////////// READ (GET) ////////////////////

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable int id) {
        return new ResponseEntity<Person>(personService.getPerson(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Person>> getAllPersons() {
        return ResponseEntity.ok().body(personService.getAllPersons());
    }


    //////////////////// CREATE (SAVE) ////////////////////

    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
        return new ResponseEntity<Person>(personService.createPerson(person), HttpStatus.CREATED);
    }

    @PostMapping("/logiccreate")
    public ResponseEntity<Person> createPersonWithLogic(@RequestBody Person person) {
        return new ResponseEntity<Person>(personService.createPersonWithLogic(person), HttpStatus.CREATED);
    }


    //////////////////// UPDATE ////////////////////

    @PutMapping("/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable int id, @RequestBody Person person) {
        return new ResponseEntity<Person>(personService.updatePerson(person, id), HttpStatus.CREATED);
    }

    // Uses the update method version 2 in the PersonService class
    @PutMapping("/logicupdate/{id}")
    public ResponseEntity<Person> updatePersonWithLogic(@PathVariable int id, @RequestBody Person person) {
        return new ResponseEntity<Person>(personService.updatePersonWithLogic(person, id), HttpStatus.CREATED);
    }


    //////////////////// DELETE ////////////////////

}
