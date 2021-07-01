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

/**
 * list of all homes served by the fire station.
 *
 */
@RestController
public class ListOfAllHomesByTheFirestation {

    @Autowired
    FireStationsService fireStationsService;

    Logger logger = LogManager.getLogger(PersonsController.class);

    // URL http://localhost:8080/flood/stations?stations=
    @GetMapping(value = "/flood/stations")
    public List<String> getListOfAllHomesByTheFirestation(@PathParam("stations") String stations) throws ParseException {

        if (stations.isEmpty()) {
            logger.error("ERROR /flood/stations");
        } else {
            logger.info("SUCCESS /flood/stations");
        }

        return fireStationsService.getListAllHomesByTheFirestation(stations);
    }
}
