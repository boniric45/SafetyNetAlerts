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

@RestController
public class Fire {

    @Autowired
    PersonsService personsService;
    Logger logger = LogManager.getLogger(PersonsController.class);

    @GetMapping(value = "/fire")
    public List<String> fire(@PathParam("address") String address) {

        if (address.isEmpty()) {
            logger.error("ERROR /fire");
        } else {
            logger.info(" SUCCESS /fire");
        }
        return personsService.getFire(address);
    }
}
