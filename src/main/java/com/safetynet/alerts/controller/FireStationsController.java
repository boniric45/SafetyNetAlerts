package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.FireStations;
import com.safetynet.alerts.service.FireStationsService;
import com.safetynet.alerts.service.PersonsService;
import com.safetynet.alerts.utils.CalculateAgeUtil;
import com.safetynet.alerts.utils.ReadJsonFirestation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

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

    public void chargedFirestation(String jsonDatafile) {
        ArrayList<FireStations> list = ReadJsonFirestation.readJsonFileFirestation(jsonDatafile);
        fireStationsService.listSaveFirestation(list);
    }

    @GetMapping(value = "/firestation")
    public List<String> getFirestationsFromStation(@PathParam("stationNumber") String stationNumber) throws ParseException {
        logger.info("requête GET sur le endpoint /firestation avec le paramètre station: " + stationNumber);

        return fireStationsService.getFirestationsFromStationNumber(stationNumber);
    }



    //    Cette url doit retourner une liste des numéros de téléphone des résidents desservis par la caserne de
//    pompiers. Nous l'utiliserons pour envoyer des messages texte d'urgence à des foyers spécifiques.
//    @GetMapping(value = "/phoneAlert?firestation={firestation_number}")
//    public ArrayList<Persons> phoneAlert() {
//        ArrayList<Persons> list = ReaderJsonPerson.readJsonFile(props.getJsonDatafile());
//        return list;
//    }



//    http://localhost:8080/firestation
//    Cet endpoint permettra d’effectuer les actions suivantes via Post/Put/Delete avec HTTP :
//            ● ajout d'un mapping caserne/adresse ;
//            ● mettre à jour le numéro de la caserne de pompiers d'une adresse ;
//            ● supprimer le mapping d'une caserne ou d'une adresse.



}
