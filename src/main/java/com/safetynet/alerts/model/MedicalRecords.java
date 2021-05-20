package com.safetynet.alerts.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data //charge getter & setter
@Entity //data table
@Table(name = "medicalrecords") //name table
public class MedicalRecords {

    @Id //autoincrement id key primary
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    private Date birthdate;
    private String medications;
    private String allergies;

    public MedicalRecords(){}
}
