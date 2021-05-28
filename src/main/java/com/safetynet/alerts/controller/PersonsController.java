package com.safetynet.alerts.controller;

import com.safetynet.alerts.CustomProperties;
import com.safetynet.alerts.model.Persons;
import com.safetynet.alerts.service.PersonsService;
import com.safetynet.alerts.utils.ReaderJsonPerson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class PersonsController {

    private static final Logger logger = LoggerFactory.getLogger(PersonsService.class);

    @Autowired
    private CustomProperties props;

    @Autowired
    private PersonsService personsService;

    //Chargement
    public void chargedPerson(String jsonDatafile) {
        ArrayList<Persons> list = ReaderJsonPerson.readJsonFile(jsonDatafile);
        personsService.listSave(list);
    }

    //Home
    @GetMapping("/")
    public String home() {
    return "home";
    }

    //inconnu
    @GetMapping("/unknow")
    public String inconnu() {
        return "Person unknow into base";
    }

    /**
     * Create - Add a new person
     *
     * @param persons An object persons
     * @return The person object saved
     */
    @PostMapping("/createperson")
    public Persons createPerson(@RequestBody Persons persons) {
        return personsService.createPerson(persons);
    }

    /**
     * Read - Get one person
     *
     * @param id The id of the person
     * @return An Person object full filled
     */
    @GetMapping("/person/{id}")
    public Persons getPersonById(@PathVariable("id") final int id) {
        Optional<Persons> persons = personsService.getPersonsById(id);
        if (persons.isPresent()) {
            return persons.get();
        } else {
            return null;
        }
    }

    /**
     * Read - Get all persons
     *
     * @return - An Iterable object of Person full filled
     */
    @GetMapping("/person")
    public Iterable<Persons> getPersonAll() {
        return personsService.getPersonAll();
    }

    /**
     * Update - Update an existing person
     *
     * @param id      - The id of the person to update
     * @param persons - The person object updated
     * @return
     */
    @PutMapping("/person/{id}")
    public Persons updateEmployee(@PathVariable("id") final int id, @RequestBody Persons persons) {
        Optional<Persons> p = personsService.getPersonsById(id);
        if (p.isPresent()) {
            Persons currentPerson = p.get();

            String firstName = persons.getFirstName();

            if (firstName != null) {
                currentPerson.setFirstName(firstName);
            }
            String lastName = persons.getLastName();
            if (lastName != null) {
                currentPerson.setLastName(lastName);
                ;
            }
            String mail = persons.getEmail();
            if (mail != null) {
                currentPerson.setEmail(mail);
            }
            personsService.savePerson(currentPerson);
            return currentPerson;
        } else {
            return null;
        }
    }

    /**
     * Delete - Delete an person
     *
     * @param id - The id of the person to delete
     */
    @GetMapping("/person/delete/{id}") // ok
    public ModelAndView deleteEmployee(@PathVariable("id") final int id) {
        Optional<Persons> p = personsService.getPersonsById(id);
        if (p.isPresent()) {
            personsService.deletePerson(id);
            return new ModelAndView("redirect:/person/all");
        } else {
            return new ModelAndView("redirect:/unknow");
        }

        //TODO écrire le log
    }

    //    Cette url doit retourner une liste d'enfants (tout individu âgé de 18 ans ou moins) habitant à cette adresse.
//    La liste doit comprendre le prénom et le nom de famille de chaque enfant, son âge et une liste des autres
//    membres du foyer. S'il n'y a pas d'enfant, cette url peut renvoyer une chaîne vide.
    @GetMapping(value = "/childAlert/?address={address}")
    public ArrayList<Persons> childAlert() {
        ArrayList<Persons> list = ReaderJsonPerson.readJsonFile(props.getJsonDatafile());
        return list;
    }

    //Cette url doit retourner une liste des numéros de téléphone des résidents desservis par la caserne de
    //pompiers. Nous l'utiliserons pour envoyer des messages texte d'urgence à des foyers spécifiques.
    @GetMapping(value = "/fire?address={address}")
    public ArrayList<Persons> fire() {
        ArrayList<Persons> list = ReaderJsonPerson.readJsonFile(props.getJsonDatafile());
        return list;
    }

    //Cette url doit retourner une liste de tous les foyers desservis par la caserne. Cette liste doit regrouper les
    //personnes par adresse. Elle doit aussi inclure le nom, le numéro de téléphone et l'âge des habitants, et
    //faire figurer leurs antécédents médicaux (médicaments, posologie et allergies) à côté de chaque nom.
    @GetMapping(value = "/flood/stations?stations={a list of station_numbers}")
    public ArrayList<Persons> floodStations() {
        ArrayList<Persons> list = ReaderJsonPerson.readJsonFile(props.getJsonDatafile());
        return list;
    }

    //Cette url doit retourner le nom, l'adresse, l'âge, l'adresse mail et les antécédents médicaux (médicaments,
    //posologie, allergies) de chaque habitant. Si plusieurs personnes portent le même nom, elles doivent
    //toutes apparaître.
    @GetMapping(value = "personInfo?firstName={firstName} &lastName={lastName}")
    public ArrayList<Persons> personInfo() {
        ArrayList<Persons> list = ReaderJsonPerson.readJsonFile(props.getJsonDatafile());
        return list;
    }

    //Cette url doit retourner les adresses mail de tous les habitants de la ville.
    @GetMapping(value = "/communityEmail")
    public List communityEmail(@PathParam("city") String city){
        return personsService.communityEmail(city);
    }



//    http://localhost:8080/person
//    Cet endpoint permettra d’effectuer les actions suivantes via Post/Put/Delete avec HTTP :
//            ● ajouter une nouvelle personne ;
//● mettre à jour une personne existante (pour le moment, supposons que le prénom et le nom de
//            famille ne changent pas, mais que les autres champs peuvent être modifiés) ;
//● supprimer une personne (utilisez une combinaison de prénom et de nom comme identificateur
//            unique).




}



