package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.FireStations;
import com.safetynet.alerts.service.FireStationsService;
import com.safetynet.alerts.service.PersonsService;
import com.safetynet.alerts.utils.ReadJsonFirestation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
        return fireStationsService.createFirestation(fireStations);
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
        return fireStations.orElse(null);
    }

    /**
     * Update - Update an existing firestation
     *
     * @param id           - The id of the firestation to update
     * @param fireStations - The firestation object updated
     * @return
     */
    @PutMapping("/firestation/{id}")
    public FireStations updateFirestations(@PathVariable("id") final int id, @RequestBody FireStations fireStations) {
        Optional<FireStations> firestation = fireStationsService.getFirestationsById(id);
        return firestation.orElse(null);
    }

    /**
     * Delete - Delete an firestation
     *
     * @param id - The id of the firestation to delete
     */
    @GetMapping("/firestation/delete/{id}") // ok
    public ModelAndView deleteFirestations(@PathVariable("id") final int id) {
        Optional<FireStations> fireStations = fireStationsService.getFirestationsById(id);
        if (fireStations.isPresent()) {
            fireStationsService.deleteFirestation(id);
        } else {
            return null;
        }
        return new ModelAndView("redirect:/firestation");
        //TODO écrire le log
    }

    //URL
    @GetMapping(value = "/firestation")
    public List<String> getFirestationsFromStation(@PathParam("stationNumber") String stationNumber) throws ParseException {
        logger.info("requête GET sur le endpoint /firestation avec le paramètre station: " + stationNumber);
        fireStationsService.getFireStationsList().clear();
        return fireStationsService.getFirestationsFromStationNumber(stationNumber);
    }

    @GetMapping(value = "/phoneAlert")
    public List<String> getPhoneAlert(@PathParam("firestation") String firestation) throws ParseException {
        logger.info("requête GET sur le endpoint /phoneAlert avec le paramètre firestation: " + firestation);
        return fireStationsService.getPhoneAlert(firestation);
    }

    @GetMapping(value = "/flood/stations")
    public List<String> getFloodStations(@PathParam("stations") String stations) throws ParseException {
        logger.info("requête GET sur le endpoint /flood/stations avec le paramètre stations: " + stations);
        return fireStationsService.getFloodStations(stations);
    }

//    http://localhost:8080/firestation
//    Cet endpoint permettra d’effectuer les actions suivantes via Post/Put/Delete avec HTTP :
//            ● ajout d'un mapping caserne/adresse ;
//            ● mettre à jour le numéro de la caserne de pompiers d'une adresse ;
//            ● supprimer le mapping d'une caserne ou d'une adresse.


}
