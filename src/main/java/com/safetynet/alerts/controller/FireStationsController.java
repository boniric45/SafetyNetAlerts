package com.safetynet.alerts.controller;
import com.safetynet.alerts.model.FireStations;
import com.safetynet.alerts.service.FireStationsService;
import com.safetynet.alerts.service.PersonsService;
import com.safetynet.alerts.utils.ReadJsonFirestation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class FireStationsController {
    private static final Logger logger = LoggerFactory.getLogger(PersonsService.class);
    @Autowired
    private FireStationsService fireStationsService;
    private FireStations fireStations;

//    @GetMapping("/listFirestation")
    public Iterable<FireStations> chargedFirestation() {
        String dataFile = "D:/Openclassrooms/Projet5/SafetyNet/src/main/resources/json/data.json";
        ArrayList<FireStations> list = ReadJsonFirestation.readJsonFileFirestation(dataFile);
        fireStationsService.listSaveFirestation(list);
        return list;
    }
}
