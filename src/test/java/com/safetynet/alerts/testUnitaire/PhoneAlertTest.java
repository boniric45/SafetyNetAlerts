package com.safetynet.alerts.testUnitaire;

import com.safetynet.alerts.service.FireStationsService;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PhoneAlertTest {

    @Mock
    FireStationsService fireStationsService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testPhoneAlertFirestation() throws Exception {

        String numberFirestation = "1";
        String contentList = "[\"841-874-6512\",\"841-874-8547\",\"841-874-7462\",\"841-874-7784\",\"841-874-7784\",\"841-874-7784\"]";

        this.mockMvc.perform(get("/phoneAlert?firestation=1"))
                .andDo(print())
                .andExpect(status().isOk());

    }


}
