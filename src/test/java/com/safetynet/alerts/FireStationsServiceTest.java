package com.safetynet.alerts;

import com.safetynet.alerts.controller.FireStationsController;
import com.safetynet.alerts.model.FireStations;
import com.safetynet.alerts.model.MedicalRecords;
import com.safetynet.alerts.model.Persons;
import com.safetynet.alerts.repository.FireStationsRepository;
import com.safetynet.alerts.repository.MedicalsRecordsRepository;
import com.safetynet.alerts.repository.PersonsRepository;
import com.safetynet.alerts.service.FireStationsService;
import com.safetynet.alerts.service.PersonsService;
import com.safetynet.alerts.utils.CalculateAgeUtil;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.stereotype.Service;
import org.springframework.test.web.servlet.MockMvc;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class FireStationsServiceTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    PersonsService personsService;

    @Test
    public void testEndpointFirestationStationNumber() throws Exception {
        mockMvc.perform(get("/firestation?stationNumber=1"))
                .andExpect(status().isOk());
    }
}
