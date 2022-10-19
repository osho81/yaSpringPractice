package com.osho.restBasics.config;

import com.osho.restBasics.model.Person;
import com.osho.restBasics.repository.PersonRepository;
import com.osho.restBasics.service.PersonService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

// Create temporary objects at start up fpr the Person table etc

@Configuration
public class GeneralConfig {

    @Bean
//    CommandLineRunner commandLineRunner(PersonRepository personRepository) { // Used during early development
    CommandLineRunner commandLineRunner(PersonService personService) {
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

//            personRepository.saveAll(List.of(donald, ronald, mickey)); // Used during early development

            // Use customized save methods in PersonService to avoid re-create rows when ddl-auto=update
            try {
                personService.createPerson(donald);
            } catch (RuntimeException e) {
                System.out.println(donald.getName() + " already exists");
            }
            try {
                personService.createPerson(ronald);
            } catch (RuntimeException e) {
                System.out.println(ronald.getName() + " already exists");
            }
            try {
                personService.createPerson(mickey);
            } catch (RuntimeException e) {
                System.out.println(mickey.getName() + " already exists");
            }

        };
    }


}
