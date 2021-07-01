package com.safetynet.alerts.testUnitaire;

import com.safetynet.alerts.config.CustomProperties;
import com.safetynet.alerts.controller.FireStationsController;
import com.safetynet.alerts.model.FireStations;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.text.ParseException;
import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ListFirestationByStationNumberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CustomProperties props;

    @Autowired
    private FireStationsController fireStationsController;

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
    }

    @Test
    public void testChargedJsonFirestation() throws ParseException {

        // GIVEN
        station = "3";
        address = "1509 Culver St";

        // WHEN
        fireStationsController.saveFirestationToH2(props.getJsonDatafile());
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

    // Create ListFirestationByStationNumber
    @Test
    public void testCreateFirestation() {

        // GIVEN
        FireStations fireStations = new FireStations(0, "20", "15 Street Medecis");
        address = "15 Street Medecis";

        // WHEN
        fireStationsController.createNewFirestation(fireStations);
        Iterable<FireStations> fireStationsIterable = fireStationsController.getAllFirestation();

        for (FireStations fs : fireStationsIterable) {
            String resultStation = fs.getStation();
            String resultAddress = fs.getAddress();
            if (resultStation.equals("20")) {
                result = fs.getAddress();
            }
        }

        // THEN
        Assertions.assertEquals(address, result);
    }

    // Read All ListFirestationByStationNumber Test
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

        // THEN
        Assertions.assertEquals(element, listresult.size());
    }

    // Read ListFirestationByStationNumber By Id Test
    @Test
    public void testReadFirestationById() throws Exception {

        mockMvc.perform(get("/firestation/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("station", CoreMatchers.is("3")));
    }

    // Update ListFirestationByStationNumber Test
    @Test
    public void testUpdateFirestation() {

        // GIVEN
        FireStations fireStations = new FireStations(1, "3", "15 Street Medecis");
        station = "3";
        address = "15 Street Medecis";

        // WHEN
        fireStationsController.getFirestationById(1);
        fireStationsController.updateFirestationsById(1, fireStations);

        // THEN
        Assertions.assertEquals(address, fireStationsController.getFirestationById(1).getAddress());
    }

    // Delete ListFirestationByStationNumber By Firstname And Lastname Test
    @Test
    public void testDeleteFirestationByFirstnameAndLastname() throws Exception {

        // WHEN
        String firestationRecord = "{\"station\":\"30\",\"address\":\"4 Binocle Ave\"}";

        // Create Person
        this.mockMvc.perform(post("/firestation")
                .contentType(MediaType.APPLICATION_JSON)
                .content(firestationRecord));

        // Read id 14 ListFirestationByStationNumber
        this.mockMvc.perform(get("/firestation/14"))
                .andExpect(status().isOk());

        // GIVEN
        this.mockMvc.perform(delete("/firestation/30/4 Binocle Ave")) // Delete ListFirestationByStationNumber by Station and Address
                //THEN
                .andExpect(status().isOk());

    }

    @Test
    public void testErrorCreateFirestation() throws Exception {

        // GIVEN
        FireStations fireStations = null;

        // WHEN
        FireStations result = fireStationsController.createNewFirestation(fireStations);

        // THEN
        Assertions.assertNull(result);

    }

    @Test
    public void testErrorReadFirestationById() {

        // GIVEN
        int id = 400;

        // WHEN
        FireStations fireStations = fireStationsController.getFirestationById(id);

        // THEN
        Assertions.assertNull(fireStations);
    }

    @Test
    public void testUpdateFirestationValueNull() {

        // GIVEN
        FireStations fireStations = new FireStations(1, null, null);

        // WHEN
        fireStationsController.updateFirestationsById(1, fireStations);

        // THEN
        Assertions.assertNull(fireStations.getStation());
        Assertions.assertNull(fireStations.getAddress());
    }

    @Test
    public void testUpdateFirestationDoesNotExist() {

        // GIVEN
        FireStations fireStations = new FireStations(100, null, null);

        // WHEN
        fireStationsController.updateFirestationsById(100, fireStations);

        // THEN
        Assertions.assertNull(fireStations.getStation());
        Assertions.assertNull(fireStations.getAddress());
    }

    @Test
    public void testDeleteFirestationValueNull() {

        // GIVEN
        FireStations fireStations = new FireStations(1, null, null);

        // WHEN
        fireStationsController.deleteFirestationsByStationAndAddress(null, null);

        // THEN
        Assertions.assertNull(fireStations.getStation());
        Assertions.assertNull(fireStations.getAddress());
    }
}
