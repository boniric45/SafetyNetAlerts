package com.safetynet.alerts;

import com.safetynet.alerts.config.InitialisationH2Bdd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
@EnableAutoConfiguration
public class SafetyNetAlertsApplication implements CommandLineRunner {

    @Autowired
    InitialisationH2Bdd initialisationH2Bdd = new InitialisationH2Bdd();

    public static void main(String[] args) throws IOException {
        SpringApplication.run(SafetyNetAlertsApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        initialisationH2Bdd.init(); // Read data.json and Create H2 Bdd with data
    }
}
