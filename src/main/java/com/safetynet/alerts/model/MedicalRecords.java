package com.safetynet.alerts.model;

import lombok.Data;

import javax.persistence.*;

@Data //charge getter & setter
@Entity //data table
@Table(name = "medicalrecords") //name table
public class MedicalRecords {

    @Id //autoincrement id key primary
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    private String birthdate;
    private String medications;
    private String allergies;

    public MedicalRecords() {
    }

    public MedicalRecords(int id, String firstName, String lastName, String birthdate, String medications, String allergies) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.medications = medications;
        this.allergies = allergies;
    }
}
