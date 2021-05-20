package com.safetynet.alerts.controller;
import com.safetynet.alerts.model.FireStations;
import com.safetynet.alerts.service.FireStationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FireStationsController {

    @Autowired
    private FireStationsService fireStationsService;

    @GetMapping("/firestations_all")
    public Iterable<FireStations> getFireStations() {
        return fireStationsService.getFireStations();
    }
}
