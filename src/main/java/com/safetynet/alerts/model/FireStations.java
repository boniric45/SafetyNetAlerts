package com.safetynet.alerts.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity //data table
@Table(name = "firestation") //name table

/**
 * Model Firestations
 */
public class FireStations {

    @Id //autoincrement id key primary
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String station;
    private String address;

    // Constructor by default
    public FireStations() {
    }

    // Constructor Firestations
    public FireStations(int id, String station, String address) {
        this.id = id;
        this.station = station;
        this.address = address;
    }


}
