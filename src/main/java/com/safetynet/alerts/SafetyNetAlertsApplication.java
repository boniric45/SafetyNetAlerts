package com.safetynet.alerts;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.controller.PersonsController;
import com.safetynet.alerts.model.Persons;
import com.safetynet.alerts.repository.PersonsRepository;
import com.safetynet.alerts.service.PersonsService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.persistence.Entity;
import java.io.File;
import java.io.IOException;
import java.util.List;

@SpringBootApplication
public class SafetyNetAlertsApplication  {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(SafetyNetAlertsApplication.class, args);

    }

}
