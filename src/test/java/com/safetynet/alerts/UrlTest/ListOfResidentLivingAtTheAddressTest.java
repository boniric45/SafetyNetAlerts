package com.safetynet.alerts.UrlTest;

import com.safetynet.alerts.service.PersonsService;
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
public class ListOfResidentLivingAtTheAddressTest {

    @Autowired
    public MockMvc mockMvc;

    @Mock
    PersonsService personsService;


    @Test
    public void testListPersonByAddress() throws Exception {

        // GIVEN
        String address = "834 Binoc Ave";
        String result = "[\" Adress: 834 Binoc Ave ListFirestationByStationNumber: 3 lastName: Carman phone: 841-874-6512 age: 9 years allergies: [] medications: []\"]";

        // WHEN
        this.mockMvc.perform(get("/fire?address=" + address))

                // THEN
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(result)));
    }

    @Test
    public void testListPersonByAddressIsNull() throws Exception {

        // GIVEN
        String address = "";

        // WHEN
        this.mockMvc.perform(get("/fire?address=" + address))

                // THEN
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("")));
    }


}
