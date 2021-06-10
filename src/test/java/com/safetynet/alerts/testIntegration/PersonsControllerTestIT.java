package com.safetynet.alerts.testIntegration;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PersonsControllerTestIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetPersons() throws Exception {
        mockMvc.perform(get("/person"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName", is("John")))
                .andExpect(jsonPath("$[0].lastName", is("Boyd")));
    }

    @Test
    public void testCreatePersonRequestBodyIsNull() throws Exception {

            //GIVEN
            String person = "";

            //WHEN
            MockHttpServletRequestBuilder req = post("/person")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(person);

            //THEN
            this.mockMvc.perform(req)
                    .andExpect(status().isBadRequest());
        }

}