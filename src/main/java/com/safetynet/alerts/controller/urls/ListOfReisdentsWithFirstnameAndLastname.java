package com.safetynet.alerts.controller.urls;

import com.safetynet.alerts.controller.PersonsController;
import com.safetynet.alerts.service.PersonsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;

/**
 * Return list of residents
 *
 */
@RestController
public class ListOfReisdentsWithFirstnameAndLastname {

    @Autowired
    PersonsService personsService;

    Logger logger = LogManager.getLogger(PersonsController.class);

    // URL http://localhost:8080/personInfo?firstname=firstname&lastName=lastname
    @GetMapping(value = "/personInfo")
    public List<String> getListOfResidents(@PathParam("firstName") String firstName, @PathParam("lastName") String lastName) {

        if (firstName.isEmpty() || lastName.isEmpty()) {
            logger.error("ERROR /personInfo");
        } else {
            logger.info(" SUCCESS /personInfo");
        }

        return personsService.getListOfResidents(firstName, lastName);
    }
}
