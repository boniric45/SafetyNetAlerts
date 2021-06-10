package com.safetynet.alerts.testUnitaire;

import com.safetynet.alerts.service.PersonsService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class GetChildAlertTest {

    @Mock
    PersonsService personsService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testChildAlertWithAddress() throws Exception {
        String address = "892 Downing Ct";
        List<String> personsList = new ArrayList<>();

        mockMvc.perform(get("/childAlert?address=" + address))
                .andExpect(status().isOk());
        //TODO à revoir
    }

}
