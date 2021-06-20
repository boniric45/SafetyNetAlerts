package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.FireStations;
import com.safetynet.alerts.service.FireStationsService;
import com.safetynet.alerts.service.PersonsService;
import com.safetynet.alerts.utils.ReadJsonFirestation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
public class FireStationsController {
    private static final Logger logger = LoggerFactory.getLogger(PersonsService.class);

    @Autowired
    private FireStationsService fireStationsService;

    //ReaderJsonFirestation and Save H2 Database
    public void chargedFirestation(String jsonDatafile) {
        ArrayList<FireStations> list = ReadJsonFirestation.readJsonFileFirestation(jsonDatafile);
        fireStationsService.listSaveFirestation(list);
    }

    /**
     * Create - Add a new firestation
     *
     * @param fireStations An object firestation
     * @return The firestation object saved
     */
    @PostMapping("/firestation")
    public FireStations createFirestation(@RequestBody FireStations fireStations) {
        if (fireStations == null) {
            logger.error(" ERROR CREATE /firestation ");
            return null;
        } else {
            logger.info(" SUCCESS CREATE /firestation ");
        }

        return fireStationsService.createFirestation(fireStations);
    }

    /**
     * Read - Get all firestation
     *
     * @return An firestation object full filled
     */
    @GetMapping("/firestations")
    public Iterable<FireStations> getAllFirestation() {
        logger.info(" SUCCESS READ All /firestations ");
        return fireStationsService.getFirestationAll();
    }

    /**
     * Read - Get one firestation
     *
     * @param id The id of the firestation
     * @return An firestation object full filled
     */
    @GetMapping("/firestation/{id}")
    public FireStations getFirestationById(@PathVariable("id") final int id) {
        Optional<FireStations> fireStations = fireStationsService.getFirestationsById(id);
        if (!fireStations.isPresent()) {
            logger.error(" ERROR READ /firestation");
        } else {
            logger.info(" SUCCESS READ /firestation");
        }
        return fireStations.orElse(null);
    }

    /**
     * Update - Update an existing firestation
     *
     * @param id           - The id of the firestation to update
     * @param fireStations - The firestation object updated
     * @return fs
     */
    @PutMapping("/firestation/{id}")
    public FireStations updateFirestations(@PathVariable("id") final int id, @RequestBody FireStations fireStations) {
        Optional<FireStations> firestations = fireStationsService.getFirestationsById(id);

        if (firestations.isPresent()) {
            FireStations currentFirestation = firestations.get();

            String station = fireStations.getStation();
            if (station != null) {
                currentFirestation.setStation(station);
            }

            String address = fireStations.getAddress();
            if (address != null) {
                currentFirestation.setAddress(address);
            }
            logger.info(" SUCCESS UPDATE /firestation/ ");
            fireStationsService.saveFirestation(currentFirestation);
            return currentFirestation;
        } else {
            logger.error(" ERROR UPDATE /firestation/  ");
            return null;
        }
    }

    /**
     * Delete - Delete an firestation
     *
     * @param address - The station of the firestation to delete
     */
    @Transactional
    @DeleteMapping("/firestation/{station}/{address}")
    public void deleteFirestations(@PathVariable("station") String station, @PathVariable("address") String address) {

        if (station == null || address == null) {
            logger.error(" ERROR DELETE /firestation");
        } else {
            logger.info(" SUCCESS DELETE /firestation");
        }
        fireStationsService.deleteFireStationStationByAddress(station, address);
    }

}
