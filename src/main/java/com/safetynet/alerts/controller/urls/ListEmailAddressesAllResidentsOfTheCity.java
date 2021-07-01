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
 * This url must return the email addresses of all the inhabitants of the city.
 *
 */
@RestController
public class ListEmailAddressesAllResidentsOfTheCity {

    @Autowired
    PersonsService personsService;

    Logger logger = LogManager.getLogger(PersonsController.class);

    // URL http://localhost:8080/communityEmail?city=City
    @GetMapping(value = "/communityEmail")
    public List<String> getListEmailAddressesAllResidentsOfTheCity(@PathParam("city") String city) {
        if (city.isEmpty()) {
            logger.error("ERROR /communityEmail");
        } else {
            logger.info(" SUCCESS /communityEmail");
        }
        return personsService.getListEmailAddressesAllResidentsOfTheCity(city);
    }


}
