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
@RequestMapping(path = "/api/v1/person") // i.e. http://localhost:8080/api/v1/person
// @CrossOrigin("http://127.0.0.1:8080/api/v1/person")
public class PersonController {

    @Autowired
    private PersonService personService;

//    @Autowired
//    private PersonRepository personRepository;


    //////////////// LANDING PAGE (GET) /////////////////
    @GetMapping("/home")
    public void homePage(@PathVariable int id) {
        // TODO: either add a thymeleaf page or fix/update react first page
        System.out.println("landing page under construction");
    }

    //////////////////// READ (GET) ////////////////////

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable int id) {
        return new ResponseEntity<Person>(personService.getPerson(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Person>> getAllPersons() {
        // return ResponseEntity.ok().body(personService.getAllPersons()); // Alternatively
        return new ResponseEntity<List<Person>>(personService.getAllPersons(), HttpStatus.OK);
    }

    @GetMapping("/all/asc")
    public ResponseEntity<List<Person>> sortPersonsAsc() {
        return new ResponseEntity<List<Person>>(personService.getAllPersonsAsc(), HttpStatus.OK);
    }

    @GetMapping("/all/desc")
    public ResponseEntity<List<Person>> sortPersonsDesc() {
        return new ResponseEntity<List<Person>>(personService.getAllPersonsDesc(), HttpStatus.OK);
    }

    @GetMapping("/{name}/asc")
    public ResponseEntity<List<Person>> findByNameOrderByNameAsc(@PathVariable String name) {
        return new ResponseEntity<List<Person>>(personService.findByNameOrderByAgeAsc(name), HttpStatus.OK);
    }
    @GetMapping("/{name}/desc")
    public ResponseEntity<List<Person>> findByNameOrderByNameDesc(@PathVariable String name) {
        return new ResponseEntity<List<Person>>(personService.findByNameOrderByAgeDesc(name), HttpStatus.OK);
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

    @PutMapping("/update/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable int id, @RequestBody Person person) {
        return new ResponseEntity<Person>(personService.updatePerson(person, id), HttpStatus.CREATED);
    }

    // Uses the update method version 2 in the PersonService class
    @PutMapping("/logicupdate/{id}")
    public ResponseEntity<Person> updatePersonWithLogic(@PathVariable int id, @RequestBody Person person) {
        return new ResponseEntity<Person>(personService.updatePersonWithLogic(person, id), HttpStatus.CREATED);
    }


    //////////////////// DELETE ////////////////////

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePerson(@PathVariable int id) {
        personService.deletePerson(id);
        return new ResponseEntity<String>("Person with id " + id + " successfully deleted", HttpStatus.OK);
    }

}
