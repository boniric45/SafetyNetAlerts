package com.safetynet.alerts.controller;

import com.safetynet.alerts.CustomProperties;
import com.safetynet.alerts.model.Persons;
import com.safetynet.alerts.service.PersonsService;
import com.safetynet.alerts.utils.ReaderJsonPerson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.websocket.server.PathParam;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class PersonsController {

    Logger logger = LogManager.getLogger(PersonsController.class);
    @Autowired
    private CustomProperties props;
    @Autowired
    private PersonsService personsService;

    //ReaderJsonPerson and Save H2 Database
    public void chargedPerson(String jsonDatafile) {
        ArrayList<Persons> list = ReaderJsonPerson.readJsonFile(jsonDatafile);
        personsService.listSave(list);
    }

    //Home
    @GetMapping("/")
    public String home() {
        logger.info("Home");
        return "home";
    }

    /**
     * Create - Add a new person
     *
     * @param persons An object persons
     * @return The person object saved
     */
    @PostMapping("/createperson") //Todo a finir
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
            logger.info("Search Person : " + persons.get());
            return persons.get();
        } else {
            logger.info("Person Unknow : " + persons.get());
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
        logger.info("get All Person  ");
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
    @GetMapping("/person/delete/{id}")
    public ModelAndView deleteEmployee(@PathVariable("id") final int id) {
        Optional<Persons> p = personsService.getPersonsById(id);
        if (p.isPresent()) {
            personsService.deletePerson(id);
            return new ModelAndView("redirect:/person");
        } else {
            return new ModelAndView("redirect:/");
        }

        //TODO écrire le log
    }

    @GetMapping(value = "/childAlert")
    public List<String> childAlert(@PathParam("address") String address) throws ParseException {
        return personsService.getChildAlert(address);
    }

    @GetMapping(value = "/fire")
    public List<String> fire(@PathParam("address") String address) throws ParseException {
        return personsService.getFire(address);
    }

    @GetMapping(value = "/communityEmail")
    public List communityEmail(@PathParam("city") String city) {
        return personsService.communityEmail(city);
    }

    @GetMapping(value = "/personInfo")
    public List<String> getPersonInfo(@PathParam("firstName") String firstName, @PathParam("lastName") String lastName) throws ParseException {
        return personsService.getPersonInfo(firstName, lastName);
    }

}



