package com.osho.restBasics.model;

import javax.persistence.*;

@Entity
@Table(name = "Person")
public class Person {
    @Id // Primary key
    @GeneratedValue(strategy = GenerationType.AUTO) // Or IDENTITY
    private int personID;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "age")
    private int age;

    @Column(name = "email")
    private String email;


    // Empty constructor
    public Person() {
    }

    // All parameters constructor
//    public Person(int personID, String name, int age, String email) {
//        this.personID = personID;
//        this.name = name;
//        this.age = age;
//        this.email = email;
//    }

    // Constructor without ID/primary key
    public Person(String name, int age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }


    ////////////////////// GETTERS & SETTERS ////////////////////////

    public int getPersonID() {
        return personID;
    }
    public void setPersonID(int personID) {
        this.personID = personID;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
