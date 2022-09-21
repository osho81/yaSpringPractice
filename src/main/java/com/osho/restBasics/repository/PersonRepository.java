package com.osho.restBasics.repository;

import com.osho.restBasics.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


// Interface repository with ready crud operations to use
// Customize additional methods/operations here if needed
// Use/autowire this repository in (at least) PersonService class

public interface PersonRepository extends JpaRepository<Person, Integer> {

    ///////// Customized methods /////////////

    Optional<Person> findPersonByEmail(String email);
    Optional<Person> findByEmail(String email); // Same as findPersonByEmail

    // Sorting related crud operations
    List<Person> findByOrderByNameAsc(); // Default is ascending, so "Asc" is not compulsory
    List<Person> findByOrderByNameDesc();
    List<Person> findByNameOrderByAgeAsc(String name);
    List<Person> findByNameOrderByAgeDesc(String name);
}
