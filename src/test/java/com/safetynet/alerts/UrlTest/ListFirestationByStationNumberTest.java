package com.safetynet.alerts.UrlTest;

import org.junit.jupiter.api.Test;
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
public class ListFirestationByStationNumberTest {

    @Autowired
    public MockMvc mockMvc;

    @Test
    public void testFirestationStationNumber() throws Exception {

        mockMvc.perform(get("/firestation?stationNumber=1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("841-874-7462")));
    }

    @Test
    public void testFirestationStationNumberIsNull() throws Exception {

        mockMvc.perform(get("/firestation?stationNumber="))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("")));
    }

}
