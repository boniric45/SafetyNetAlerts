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

import javax.websocket.server.PathParam;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
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

    //EndPoint

    /**
     * Create - Add a new firestation
     *
     * @param fireStations An object firestation
     * @return The firestation object saved
     */
    @PostMapping("/firestation")
    public FireStations createFirestation(@RequestBody FireStations fireStations) {
        logger.info(" CREATE /firestation > " + fireStations);
        return fireStationsService.createFirestation(fireStations);
    }

    /**
     * Read - Get all firestation
     *
     * @return An firestation object full filled
     */
    @GetMapping("/firestations")
    public Iterable<FireStations> getAllFirestation(){
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
        logger.info(" READ /firestation > " + fireStations.get());
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
        Optional<FireStations> firestation = fireStationsService.getFirestationsById(id);

        if (firestation.isPresent()) {
            FireStations fs = firestation.get();

            String station = fs.getStation();
            if (station != null) {
                fs.setStation(station);
            }

            String address = fs.getAddress();
            if (address != null) {
                fs.setAddress(address);
            }
            logger.info(" UPDATE /firestation > " + fs);
            fireStationsService.saveFirestation(fs);
            return fs;
        } else {
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
    public void deleteFirestations(@PathVariable("station") String station, @PathVariable("address") String address ) {
    logger.info(" DELETE /firestation > Station: "+station+" Address: " + address);
    fireStationsService.deleteFireStationStationByAddress(station,address);
    }




    //URL
    @GetMapping(value = "/firestation")
    public List<String> getFirestationsFromStation(@PathParam("stationNumber") String stationNumber) throws ParseException {
        logger.info("Query GET Endpoint /firestation with param station: " + stationNumber);
        fireStationsService.getFireStationsList().clear();
        return fireStationsService.getFirestationsFromStationNumber(stationNumber);
    }

    @GetMapping(value = "/phoneAlert")
    public List<String> getPhoneAlert(@PathParam("firestation") String firestation) throws ParseException {
        logger.info("Query GET Endpoint /phoneAlert with param firestation: " + firestation);
        return fireStationsService.getPhoneAlert(firestation);
    }

    @GetMapping(value = "/flood/stations")
    public List<String> getFloodStations(@PathParam("stations") String stations) throws ParseException {
        logger.info("Query GET Endpoint /flood/stations with param stations: " + stations);
        return fireStationsService.getFloodStations(stations);
    }


}
