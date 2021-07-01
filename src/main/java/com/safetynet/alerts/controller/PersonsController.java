package com.safetynet.alerts.controller;

import com.safetynet.alerts.config.CustomProperties;
import com.safetynet.alerts.model.Persons;
import com.safetynet.alerts.service.PersonsService;
import com.safetynet.alerts.utils.ReadJsonPerson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Controller Person
 */
@RestController
public class PersonsController {

    Logger logger = LogManager.getLogger(PersonsController.class);
    @Autowired
    private CustomProperties props;
    @Autowired
    private PersonsService personsService;

    //ReadJsonPerson and Save H2 Database
    public void savePersonToH2(String jsonDatafile) {
        ArrayList<Persons> list = ReadJsonPerson.readJsonFile(jsonDatafile);
        personsService.savePersonList(list);
    }

    /**
     * Home
     */
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
    @PostMapping("/person")
    public Persons createNewPerson(@RequestBody Persons persons) {

        if (persons == null) {
            logger.error(" ERROR CREATE /person ");
            return null;
        } else {
            logger.info(" SUCCESS CREATE /person ");
        }

        return personsService.createNewPerson(persons);
    }

    /**
     * Read - Get all persons
     *
     * @return - An Iterable object of Person full filled
     */
    @GetMapping("/person")
    public Iterable<Persons> getPersonAll() {
        logger.info(" SUCCESS READ All /person ");
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
            logger.info(" SUCCESS READ ONE /person  ");
            return persons.get();
        } else {
            logger.error(" ERROR READ ONE /person ");
            return null;
        }
    }

    /**
     * Update - Update an existing person
     *
     * @param id      - The id of the person to update
     * @param persons - The person object updated
     * @return currentPerson
     */
    @PutMapping("/person/{id}")
    public Persons updatePersonById(@PathVariable("id") final int id, @RequestBody Persons persons) {
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
            logger.info(" SUCCESS UPDATE /person ");
            personsService.savePerson(currentPerson);
            return currentPerson;
        } else {
            logger.error(" ERROR UPDATE /person ");
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
        if (firstName == null || lastName == null) {
            logger.error("ERROR DELETE /person");
        }
        logger.info(" SUCCESS DELETE /person ");
        personsService.deletePersonByFirstNameAndLastName(firstName, lastName);
    }
}



