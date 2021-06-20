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
public class CommunityEmailTest {

    @Autowired
    public MockMvc mockMvc;

    @Mock
    PersonsService personsService;

    @Test
    public void testReturnEmailPersonFromTheCity() throws Exception {

        // GIVEN
        String city = "Culver";
        String result = "jaboyd@email.com";

        // WHEN
        this.mockMvc.perform(get("/communityEmail?city=" + city))
                .andDo(print())
                .andExpect(status().isOk())
                // THEN
                .andExpect(content().string(containsString(result)));
    }

    @Test
    public void testReturnEmailPersonFromTheCityIsNull() throws Exception {

        // GIVEN
        String city = "";
        String result = "jaboyd@email.com";

        // WHEN
        this.mockMvc.perform(get("/communityEmail?city=" + city))
                .andDo(print())
                .andExpect(status().isOk())
                // THEN
                .andExpect(content().string(containsString("")));
    }


}
