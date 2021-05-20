package com.safetynet.alerts;

import com.safetynet.alerts.controller.PersonsController;
import com.safetynet.alerts.service.PersonsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PersonsController.class)
public class PersonsControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonsService personsService;

    @Test
    public void testGetPersons() throws Exception {
        mockMvc.perform(get("/persons")).andExpect(status().isOk());
    }


}
