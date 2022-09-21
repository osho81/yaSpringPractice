package com.osho.restBasics.service;

// Create temporary objects at start up fpr the Person table etc

import com.osho.restBasics.model.Person;
import com.osho.restBasics.repository.PersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class GeneralConfig {

    @Bean
    CommandLineRunner commandLineRunner(PersonRepository personRepository) {
        return args -> {
            Person donald = new Person(
                    "Donald Duck",
                    30,
                    "dd@gmail.com"
            );
            Person ronald = new Person(
                    "Ronald Ruck",
                    50,
                    "rr@gmail.com"
            );
            Person mickey = new Person(
                    "Mickey Mouse",
                    20,
                    "mm@gmail.com"
            );
            personRepository.saveAll(List.of(donald, ronald, mickey));
        };
    }


}
