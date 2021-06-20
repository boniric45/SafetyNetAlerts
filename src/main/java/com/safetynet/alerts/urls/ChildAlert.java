package com.safetynet.alerts.urls;

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
public class ChildAlert {

    Logger logger = LogManager.getLogger(PersonsController.class);
    @Autowired
    private PersonsService personsService;

    @GetMapping(value = "/childAlert")
    public List<String> childAlert(@PathParam("address") String address) {
        if (address.isEmpty()) {
            logger.error("ERROR /childAlert");
        } else {

            logger.info(" SUCCESS /childAlert");
        }
        return personsService.getChildAlert(address);
    }


}
