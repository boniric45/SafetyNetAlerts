package com.safetynet.alerts.controller.urls;

import com.safetynet.alerts.controller.PersonsController;
import com.safetynet.alerts.service.FireStationsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
public class PhoneAlert {

    @Autowired
    FireStationsService fireStationsService;

    Logger logger = LogManager.getLogger(PersonsController.class);

    @GetMapping(value = "/phoneAlert")
    public List<String> getPhoneAlert(@PathParam("firestation") String firestation) {

        if (firestation.isEmpty()) {
            logger.error("ERROR /phoneAlert");
        } else {
            logger.info("SUCCESS /phoneAlert");
        }

        return fireStationsService.getPhoneAlert(firestation);
    }


}
