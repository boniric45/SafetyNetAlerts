package com.safetynet.alerts;

import com.safetynet.alerts.controller.FireStationsController;
import com.safetynet.alerts.controller.MedicalsRecordsController;
import com.safetynet.alerts.controller.PersonsController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class SafetyNetAlertsApplication implements CommandLineRunner {


    @Autowired
    private CustomProperties props;
    @Autowired
    PersonsController pc = new PersonsController();
    @Autowired
    FireStationsController fsc = new FireStationsController();
    @Autowired
    MedicalsRecordsController mrc = new MedicalsRecordsController();

    public static void main(String[] args) throws IOException {
        SpringApplication.run(SafetyNetAlertsApplication.class, args);
    }

    /**
     * Callback used to run the bean.
     *
     * @param args incoming main method arguments
     * @throws Exception on error
     */
    @Override
    public void run(String... args) throws Exception {
       System.out.println(props.getApiURL());
       pc.chargedPerson();
       fsc.chargedFirestation();
       mrc.chargedMedicalRecord();
    }
}