package com.safetynet.alerts.testIntegration;

import com.safetynet.alerts.service.FireStationsService;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EndpointListFirestationByStationNumberTestIT {

    @Autowired
    FireStationsService fireStationsService;

    @Autowired
    private MockMvc mockMvc;

    // Create ListFirestationByStationNumber Test
    @Test
    public void create_Firestation_Test() throws Exception {
        // GIVEN
        String fsRecord = "{\"station\":\"3\",\"address\":\"1509 Culver St\"}";

        // WHEN
        MockHttpServletRequestBuilder req = post("/firestation")
                .contentType(MediaType.APPLICATION_JSON)
                .content(fsRecord);

        // THEN
        this.mockMvc.perform(req)
                .andExpect(status().isOk())
                .andExpect(jsonPath("station", CoreMatchers.is("3")))
                .andExpect(jsonPath("address", CoreMatchers.is("1509 Culver St")))
                .andExpect(status().isOk());

    }

    // Read All ListFirestationByStationNumber Test
    @Test
    public void testReadAllFirestation() throws Exception {
        this.mockMvc.perform(get("/firestations"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    // Read One ListFirestationByStationNumber Test
    @Test
    public void testReadOneFirestation() throws Exception {

        this.mockMvc.perform(get("/firestation/6"))
                .andExpect(jsonPath("station", CoreMatchers.is("3")))
                .andExpect(jsonPath("address", CoreMatchers.is("112 Steppes Pl")))
                .andDo(print())
                .andExpect(status().isOk());
    }

    // Update ListFirestationByStationNumber Test
    @Test
    public void testUpdateFirestation() throws Exception {

        // GIVEN
        String updatefsRecord = "{\"station\":\"4\",\"address\":\"9 Newyork Street\"}";

        // WHEN
        MockHttpServletRequestBuilder req = put("/firestation/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(updatefsRecord);

        this.mockMvc.perform(get("/firestation/1")); // Read ListFirestationByStationNumber Id 1


        // THEN
        this.mockMvc.perform(req)   // Update ListFirestationByStationNumber
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("station", CoreMatchers.is("4")))
                .andExpect(jsonPath("address", CoreMatchers.is("9 Newyork Street")));

        this.mockMvc.perform(get("/firestation/1"));   // Read ListFirestationByStationNumber Id 1
    }

    // Delete ListFirestationByStationNumber Test
    @Test
    public void delete_Firestation_Test() throws Exception {

        // GIVEN
        String createFirestation = "{\"id\": 14,\"station\":\"4\",\"address\":\"9 Newyork Street\"}";

        // WHEN
        this.mockMvc.perform(post("/firestation") // Create ListFirestationByStationNumber
                .contentType(MediaType.APPLICATION_JSON)
                .content(createFirestation));

        this.mockMvc.perform(get("/firestation/14")) // Read ListFirestationByStationNumber Id 14
                .andExpect(status().isOk());

        // THEN
        this.mockMvc.perform(delete("/firestation/4/9 Newyork Street")); // Delete by Station and Address

        this.mockMvc.perform(get("/firestations")) // Read All Firestations
                .andExpect(status().isOk())
                .andDo(print());

    }
}
