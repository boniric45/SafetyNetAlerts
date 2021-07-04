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
public class ListChildAlertTest {

    @Autowired
    public MockMvc mockMvc;

    @Mock
    PersonsService personsService;

    @Test
    public void testChildAlertWithAddress() throws Exception {

        // GIVEN
        String address = "892 Downing Ct";

        // WHEN
        this.mockMvc.perform(get("/childAlert?address=" + address))
                .andDo(print())
                .andExpect(status().isOk())
                // THEN
                .andExpect(content().string(containsString("03/06/1988")));
    }

    @Test
    public void testChildAlertWithNoAddress() throws Exception {

        // GIVEN
        String address = "";

        // WHEN
        mockMvc.perform(get("/childAlert?address=" + address))
                .andDo(print())
                .andExpect(status().isOk())
                // THEN
                .andExpect(content().string(containsString("")));
    }

    @Test
    public void testChildAlertWithAddressNull() throws Exception {

        // GIVEN
        String address = null;

        // WHEN
        mockMvc.perform(get("/childAlert?address=" + address))
                .andDo(print())
                .andExpect(status().isOk())
                // THEN
                .andExpect(content().string(containsString("")));
    }

}
