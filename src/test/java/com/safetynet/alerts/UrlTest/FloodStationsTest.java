package com.safetynet.alerts.UrlTest;

import com.safetynet.alerts.service.FireStationsService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.matchers.JUnitMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class FloodStationsTest {

    @Autowired
    public MockMvc mockMvc;

    @Mock
    FireStationsService fireStationsService;

    @Test
    public void testReturnListHomeByFirestation() throws Exception {

        // GIVEN
        String numberStation = "4";
        String result = "[\"firestation: 4 address: 489 Manchester St firstname: Lily lastname: Cooper allergies: [] medications: [] phone: 841-874-9845 age: 27\",\"firestation: 4 address: 112 Steppes Pl firstname: Tony lastname: Cooper allergies: [\\\"shellfish\\\"] medications: [\\\"hydrapermazol:300mg\\\",\\\"dodoxadin:30mg\\\"] phone: 841-874-6874 age: 27\",\"firestation: 4 address: 112 Steppes Pl firstname: Ron lastname: Peters allergies: [] medications: [] phone: 841-874-8888 age: 56\",\"firestation: 4 address: 112 Steppes Pl firstname: Allison lastname: Boyd allergies: [\\\"nillacilan\\\"] medications: [\\\"aznol:200mg\\\"] phone: 841-874-9888 age: 56\"]";

        // WHEN
        this.mockMvc.perform(get("/flood/stations?stations=" + numberStation))

                // THEN
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(result)));

    }

    @Test
    public void testReturnListHomeByFirestationIsNull() throws Exception {

        // GIVEN
        String numberStation = "";

        // WHEN
        this.mockMvc.perform(get("/flood/stations?stations=" + numberStation))

                // THEN
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("")));

    }
}
