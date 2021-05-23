package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.Persons;
import com.safetynet.alerts.service.PersonsService;
import com.safetynet.alerts.utils.ReaderJsonPerson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;

@RestController
public class PersonsController {
    private static final Logger logger = LoggerFactory.getLogger(PersonsService.class);
    @Autowired
    private PersonsService personsService;
    private Persons Persons;

//    @GetMapping(value = "/list")
    public ArrayList<Persons> chargedPerson() throws IOException {
        String dataFile = "D:/Openclassrooms/Projet5/SafetyNet/src/main/resources/json/data.json";
        ArrayList<Persons> list = ReaderJsonPerson.readJsonFile(dataFile);
        personsService.listSave(list);
        return list;
    }


}



