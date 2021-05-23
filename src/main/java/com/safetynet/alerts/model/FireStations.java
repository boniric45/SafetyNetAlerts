package com.safetynet.alerts.model;

import lombok.Data;

import javax.persistence.*;

@Data //charge getter & setter
@Entity //data table
@Table(name = "firestation") //name table
public class FireStations {

    @Id //autoincrement id key primary
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String station;
    private String address;

    public FireStations() {
    }

    public FireStations(int id, String station, String address) {
        this.id = id;
        this.station = station;
        this.address = address;
    }
}
