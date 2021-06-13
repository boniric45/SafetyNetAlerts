package com.safetynet.alerts.UrlTest;

import com.safetynet.alerts.service.PersonsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UrlTest {

    @MockBean
    PersonsService personsService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testUrlFirestationStationNumber() throws Exception {
        mockMvc.perform(get("/firestation?stationNumber=1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testUrlChildAlert() throws Exception {
        mockMvc.perform(get("/childAlert?address=834 Binoc Ave"))
                .andExpect(status().isOk());
    }

    @Test
    public void testUrlPhoneAlert() throws Exception {
        mockMvc.perform(get("/phoneAlert?firestation=1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testUrlFireAdress() throws Exception {
        mockMvc.perform(get("/fire?address=834 Binoc Ave"))
                .andExpect(status().isOk());
    }

    @Test
    public void testUrlFloodStations() throws Exception {
        mockMvc.perform(get("/flood/stations?stations=1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testUrlCommunityEmail() throws Exception {
        when(personsService.communityEmail(anyString())).thenReturn(anyList());
        mockMvc.perform(get("/communityEmail?city=Culver"))
                .andExpect(status().isOk());
    }

}
