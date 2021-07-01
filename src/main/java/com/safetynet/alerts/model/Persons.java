package com.safetynet.alerts.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity //Table bdd
@Table(name = "persons") //Table associé

/**
 * Model person
 */
public class Persons {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    private String address;
    private String zip;
    private String city;
    private String phone;
    private String email;

    // Default Constructor
    public Persons() {
    }

    // Constructor Person
    public Persons(int id, String firstName, String lastName, String address, String zip, String city, String phone, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.zip = zip;
        this.city = city;
        this.phone = phone;
        this.email = email;
    }

}
