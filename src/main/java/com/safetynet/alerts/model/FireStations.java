package com.safetynet.alerts.model;

import lombok.Data;

import javax.persistence.*;

@Data //charge getter & setter
@Entity //data table
@Table(name = "firestations") //name table
public class FireStations {

    @Id //autoincrement id key primary
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "station")
    private int station;
    private String address;

}
