package com.safetynet.alerts.controller;

import com.safetynet.alerts.CustomProperties;
import com.safetynet.alerts.model.Persons;
import com.safetynet.alerts.service.PersonsService;
import com.safetynet.alerts.utils.ReaderJsonPerson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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
    } //ok

    //Endpoint
    /**
     * Create - Add a new person
     *
     * @param persons An object persons
     * @return The person object saved
     */ //TODO KO
    @PostMapping("/person")
    public Persons createPerson(@RequestBody Persons persons) {
        logger.info(" CREATE /person > "+persons);
        return personsService.createPerson(persons);
    }

    /**
     * Read - Get all persons
     *
     * @return - An Iterable object of Person full filled
     */
    @GetMapping("/person")
    public Iterable<Persons> getPersonAll() {
        logger.info("READ All Person  ");
        return personsService.getPersonAll();
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
            logger.info(" READ /person > " + persons.get());
            return persons.get();
        } else {
            return null;
        }
    }

    /**
     * Update - Update an existing person
     *
     * @param id      - The id of the person to update
     * @param persons - The person object updated
     * @return
     */
    @PutMapping("/person/{id}")
    public Persons updatePerson(@PathVariable("id") final int id, @RequestBody Persons persons) {
        Optional<Persons> p = personsService.getPersonsById(id);

            if (p.isPresent()) {
                Persons currentPerson = p.get();
                String address = persons.getAddress();
                if (address != null) {
                    currentPerson.setAddress(address);
                }
                String zip = persons.getZip();
                if (zip != null) {
                    currentPerson.setZip(zip);
                }
                String city = persons.getCity();
                if (city != null) {
                    currentPerson.setCity(city);
                }
                String phone = persons.getPhone();
                if (phone != null) {
                    currentPerson.setPhone(phone);
                }
                String mail = persons.getEmail();
                if (mail != null) {
                    currentPerson.setEmail(mail);
                }
                logger.info(" UPDATE /person > "+currentPerson);
                personsService.savePerson(currentPerson);
                return currentPerson;
        } else {
            return null;
        }
    }

    /**
     * Delete - Delete an person
     *
     * @param firstName lastName the person to delete
     */
    @Transactional
    @DeleteMapping("/person/{firstName}/{lastName}")
    public void deletePersonByFirstNameAndLastName(@PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName) {
        logger.info(" DELETE /person with lastName: " + lastName + " and firstName: " + firstName);
        personsService.deletePersonByFirstNameAndLastName(firstName, lastName);
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



