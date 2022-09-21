package com.osho.restBasics.repository;

import com.osho.restBasics.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


// Interface repository with ready to use methods
// Customize additional methods here if needed
// Use/autowire this repository in PersonService class

public interface PersonRepository extends JpaRepository<Person, Integer> {

    ///////// Customized methods /////////////

    Optional<Person> findPersonByEmail(String email);

    Optional<Person> findByEmail(String email);
}