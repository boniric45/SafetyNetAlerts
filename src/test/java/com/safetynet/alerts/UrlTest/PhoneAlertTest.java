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
public class PhoneAlertTest {

    @Autowired
    public MockMvc mockMvc;

    @Mock
    FireStationsService fireStationsService;

    @Test
    public void testPhoneAlertFirestation() throws Exception {

        // GIVEN
        String contentList = "841-874-9845";
        String strFirestation = "4";

        // WHEN
        this.mockMvc.perform(get("/phoneAlert?firestation=" + strFirestation))
                .andDo(print())
                // THEN
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(contentList)));
    }

    @Test
    public void testPhoneAlertFirestationIsNull() throws Exception {

        // GIVEN
        String strFirestation = "";

        // WHEN
        this.mockMvc.perform(get("/phoneAlert?firestation=" + strFirestation))
                .andDo(print())
                // THEN
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("")));
    }
}
