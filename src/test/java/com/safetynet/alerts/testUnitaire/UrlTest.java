package com.safetynet.alerts.testUnitaire;

import com.safetynet.alerts.service.PersonsService;
import org.junit.jupiter.api.Disabled;
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
    public void testEndpointFirestationStationNumber() throws Exception {
        mockMvc.perform(get("/firestation?stationNumber=1"))
                .andExpect(status().isOk());
    }

    @Disabled
    @Test
    public void testEndpointChildAlert() throws Exception {
        mockMvc.perform(get("/childAlert?address="))
                .andExpect(status().isOk());
    }
    @Disabled
    @Test
    public void testEndpointPhoneAlert() throws Exception {
        mockMvc.perform(get("/phoneAlert?firestation="))
                .andExpect(status().isOk());
    }
    @Disabled
    @Test
    public void testEndpointFireAdress() throws Exception {
        mockMvc.perform(get("/fire?address="))
                .andExpect(status().isOk());
    }
    @Disabled
    @Test
    public void testEndpointFloodStations() throws Exception {
        mockMvc.perform(get("/flood/stations?stations= "))
                .andExpect(status().isOk());
    }

    @Test
    public void testEndpointCommunityEmail() throws Exception {
        when(personsService.communityEmail(anyString())).thenReturn(anyList());
        mockMvc.perform(get("/communityEmail?city=Culver"))
                .andExpect(status().isOk());
    }


}
