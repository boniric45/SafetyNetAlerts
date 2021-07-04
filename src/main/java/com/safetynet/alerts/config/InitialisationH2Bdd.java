package com.safetynet.alerts.config;

import com.safetynet.alerts.controller.FireStationsController;
import com.safetynet.alerts.controller.MedicalsRecordsController;
import com.safetynet.alerts.controller.PersonsController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * InitialisationH2Bdd H2 BDD
 * <p>
 * read data.json and insert into H2 BDD
 */

@RestController
public class InitialisationH2Bdd {

    @Autowired
    CustomProperties properties; // Path file data.json

    @Autowired
    PersonsController personsController;

    @Autowired
    FireStationsController fireStationsController;

    @Autowired
    MedicalsRecordsController medicalsRecordsController;

    // Insert Person, ListFirestationByStationNumber, MedicalRecord into H2 BDD
    public void init() {
        personsController.savePersonToH2(properties.getJsonDatafile());
        fireStationsController.saveFirestationToH2(properties.getJsonDatafile());
        medicalsRecordsController.saveMedicalRecordToH2(properties.getJsonDatafile());

    }

}
