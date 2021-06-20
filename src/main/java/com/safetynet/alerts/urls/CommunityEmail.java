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
public class CommunityEmail {

    @Autowired
    PersonsService personsService;

    Logger logger = LogManager.getLogger(PersonsController.class);

    @GetMapping(value = "/communityEmail")
    public List<String> communityEmail(@PathParam("city") String city) {
        if (city.isEmpty()) {
            logger.error("ERROR /communityEmail");
        } else {
            logger.info(" SUCCESS /communityEmail");
        }
        return personsService.communityEmail(city);
    }


}
