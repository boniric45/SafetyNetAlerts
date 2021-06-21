package com.safetynet.alerts.controller.urls;

import com.safetynet.alerts.controller.PersonsController;
import com.safetynet.alerts.service.FireStationsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.text.ParseException;
import java.util.List;

@RestController
public class Firestation {

    @Autowired
    FireStationsService fireStationsService;
    Logger logger = LogManager.getLogger(PersonsController.class);

    @GetMapping(value = "/firestation")
    public List<String> getFirestationsFromStation(@PathParam("stationNumber") String stationNumber) throws ParseException {
        fireStationsService.getFireStationsList().clear();

        if (stationNumber.isEmpty()) {
            logger.error("ERROR /firestation?station");
        } else {
            logger.info("SUCCESS /firestation?station");
        }
        return fireStationsService.getFirestationsFromStationNumber(stationNumber);
    }
}
