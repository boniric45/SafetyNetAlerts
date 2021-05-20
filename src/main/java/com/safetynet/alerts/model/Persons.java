package com.safetynet.alerts.model;
import lombok.Data;
import javax.persistence.*;

@Data //ajout auto getter setter
@Entity //Table bdd
@Table(name = "persons") //Table associé
public class Persons {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String address;
    private String city;
    private String zip;
    private String phone;
    private String email;

}
