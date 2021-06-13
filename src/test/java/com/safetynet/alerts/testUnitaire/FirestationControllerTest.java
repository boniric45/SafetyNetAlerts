package com.safetynet.alerts.testUnitaire;

import com.safetynet.alerts.CustomProperties;
import com.safetynet.alerts.controller.FireStationsController;
import com.safetynet.alerts.model.FireStations;
import com.safetynet.alerts.model.MedicalRecords;
import com.safetynet.alerts.model.Persons;
import com.safetynet.alerts.repository.FireStationsRepository;
import com.safetynet.alerts.service.FireStationsService;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.text.ParseException;
import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class FirestationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CustomProperties props;

    @Autowired
    private FireStationsController fireStationsController;

    @Autowired
    private FireStationsService fireStationsService;

    @Autowired
    private FireStationsRepository fireStationsRepository;

    private String station;
    private String address;
    private String firestation;
    private String result;
    private ArrayList<String> listresult = new ArrayList<>();

    @BeforeEach
    private void setUpPerTest() {
        station = null;
        address = null;
        firestation = null;
        result = "";
//        listresult.clear();
    }

    @Test
    public void testChargedJsonFirestation() throws ParseException {

        // GIVEN
        station = "3";
        address = "1509 Culver St";

        // WHEN
        fireStationsController.chargedFirestation(props.getJsonDatafile());
        Iterable<FireStations> fireStationsIterable = fireStationsController.getAllFirestation();

        for (FireStations fireStations : fireStationsIterable) {
            String resultAdress = fireStations.getAddress();
            String resultStation = fireStations.getStation();
            if (address.equals(resultAdress) && station.equals(resultStation)) {
                result = fireStations.getStation();
            }
        }
        // THEN
        Assertions.assertEquals(station, result);

    }

    // Create Firestation
    @Test
    public void testCreateFirestation() {
        //GIVEN
        FireStations fireStations = new FireStations(0, "20", "15 Street Medecis");
        address = "15 Street Medecis";

        //WHEN
        fireStationsController.createFirestation(fireStations);
        Iterable<FireStations> fireStationsIterable = fireStationsController.getAllFirestation();

        for (FireStations fs : fireStationsIterable) {
            String resultStation = fs.getStation();
            String resultAddress = fs.getAddress();
            if (resultStation.equals("20")) {
                result = fs.getAddress();
            }
        }

        //THEN
        Assertions.assertEquals(address, result);
    }

    // Read All Firestation Test
    @Test
    public void testReadAllFirestation() {

        // GIVEN
        int element = 14;

        // WHEN
        Iterable<FireStations> fireStationsIterable = fireStationsController.getAllFirestation();
        for (FireStations fs : fireStationsIterable) {
            String resultStation = fs.getStation();
            listresult.add(resultStation);
        }

        //THEN
        Assertions.assertEquals(element, listresult.size());
    }

    // Read Firestation By Id Test
    @Test
    public void testReadFirestationById() throws Exception {

        mockMvc.perform(get("/firestation/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("station", CoreMatchers.is("3")));
    }

    // Update Firestation Test
    @Test
    public void testUpdateFirestation() {

        // GIVEN
        FireStations fireStations = new FireStations(1, "3", "15 Street Medecis");
        station = "3";
        address = "15 Street Medecis";

        // WHEN
        fireStationsController.getFirestationById(1);
        fireStationsController.updateFirestations(1, fireStations);

        // THEN
        Assertions.assertEquals(address, fireStationsController.getFirestationById(1).getAddress());
    }

    // Delete Firestation By Firstname And Lastname Test
    @Test
    public void testDeleteFirestation() throws Exception {

        //WHEN
        String firestationRecord = "{\"station\":\"30\",\"address\":\"4 Binocle Ave\"}";

        // Create Person
        this.mockMvc.perform(post("/firestation")
                .contentType(MediaType.APPLICATION_JSON)
                .content(firestationRecord));

        // Read id 14 Firestation
        this.mockMvc.perform(get("/firestation/14"))
                .andExpect(status().isOk());

        //GIVEN
        // Delete Firestation by Station and Address
        this.mockMvc.perform(delete("/firestation/30/4 Binocle Ave")); // Delete by Station and Address


        //THEN
        mockMvc.perform(MockMvcRequestBuilders.get("/firestation"))
                .andExpect(jsonPath("id", "14").doesNotExist())
                .andExpect(status().isOk());


    }

    @Test
    public void testUpdateFirestationValueNull() {
        //GIVEN
        FireStations fireStations = new FireStations(1,null,null);

        //WHEN
        fireStationsController.updateFirestations(1,fireStations);

        //THEN
        Assertions.assertNull(fireStations.getStation());
        Assertions.assertNull(fireStations.getAddress());
    }

    @Test
    public void testUpdateFirestationDoesNotExist() {


        //GIVEN
        FireStations fireStations = new FireStations(100,null,null);

        //WHEN
        fireStationsController.updateFirestations(100,fireStations);

        //THEN
        Assertions.assertNull(fireStations.getStation());
        Assertions.assertNull(fireStations.getAddress());
    }

    @Test
    public void testDeleteFirestationValueNull(){

        //GIVEN
        FireStations fireStations = new FireStations(1,null,null);

        //WHEN
        fireStationsController.deleteFirestations(null,null);

        //THEN
        Assertions.assertNull(fireStations.getStation());
        Assertions.assertNull(fireStations.getAddress());
    }
}
