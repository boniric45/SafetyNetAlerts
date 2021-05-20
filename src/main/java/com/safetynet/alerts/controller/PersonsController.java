package com.safetynet.alerts.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.model.Persons;
import com.safetynet.alerts.service.PersonsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;

@RestController
public class PersonsController {

    @Autowired
    private PersonsService personsService;

    @GetMapping("/persons")
    public Iterable<Persons> getPersons() throws IOException {

        //create ObjectMapper instance
        ObjectMapper objectMapper = new ObjectMapper();

        //read json file and convert to customer object
        Persons persons = objectMapper.readValue(new File("D:/Openclassrooms/Projet5/SafetyNet_Alerts/src/main/resources/json/data.json"), Persons.class);

        //print customer details
        System.out.println(persons.getAddress());

        return personsService.getPersons();
    }
}
