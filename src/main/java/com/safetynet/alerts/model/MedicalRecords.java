package com.safetynet.alerts.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity //data table
@Table(name = "medicalrecords") //name table

/**
 * Model MedicalRecords
 */
public class MedicalRecords {

    @Id //autoincrement id key primary
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    private String birthdate;
    private String medications;
    private String allergies;

    // Default Constructor
    public MedicalRecords() {
    }

    // Constructor MedicalRecords
    public MedicalRecords(int id, String firstName, String lastName, String birthdate, String medications, String allergies) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.medications = medications;
        this.allergies = allergies;
    }
}
