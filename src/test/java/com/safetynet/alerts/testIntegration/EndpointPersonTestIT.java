package com.safetynet.alerts.testIntegration;

import com.safetynet.alerts.service.PersonsService;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EndpointPersonTestIT {

    @Autowired
    PersonsService personsService;

    @Autowired
    private MockMvc mockMvc;

    // Create Person Test
    @Test
    public void testCreatePerson() throws Exception {

        // GIVEN
        String person = "{\"firstName\":\"John\",\"lastName\":\"Boyd\",\"address\":\"1509 Culver St\",\"zip\":\"97451\",\"city\":\"Culver\",\"phone\":\"841-874-6512\",\"email\":\"jaboyd@email.com\"}";

        // WHEN
        MockHttpServletRequestBuilder req = post("/person")
                .contentType(MediaType.APPLICATION_JSON)
                .content(person);

        // THEN
        this.mockMvc.perform(req)
                .andExpect(status().isOk())
                .andExpect(jsonPath("firstName", CoreMatchers.is("John")))
                .andExpect(jsonPath("lastName", CoreMatchers.is("Boyd")));
    }

    // Read All Person Test
    @Test
    public void testReadAllPersons() throws Exception {

        mockMvc.perform(get("/person"))
                .andExpect(status().isOk());
    }

    // Read Person By Id Test
    @Test
    public void testReadPersonById() throws Exception {

        mockMvc.perform(get("/person/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("firstName", CoreMatchers.is("John")));
    }

    // Update Person Test
    @Test
    public void testUpdatePerson() throws Exception {

        // GIVEN
        String updatePerson = "{\"firstName\":\"John\",\"lastName\":\"Boyd\",\"address\":\"1509 Culver St\",\"zip\":\"98000\",\"city\":\"Paris\",\"phone\":\"841-874-6512\",\"email\":\"jaboyd@email.com\"}";

        mockMvc.perform(get("/person/1")) // Read Person Id 2
                .andExpect(status().isOk());

        // WHEN
        MockHttpServletRequestBuilder req = put("/person/1") // Update Person with updatePerson
                .contentType(MediaType.APPLICATION_JSON)
                .content(updatePerson);

        mockMvc.perform(req)
                .andExpect(status().isOk())
                .andExpect(jsonPath("zip", CoreMatchers.is("98000")))
                .andExpect(jsonPath("city", CoreMatchers.is("Paris")));

        // THEN
        mockMvc.perform(get("/person/2")) // Read Person Id 2
                .andExpect(status().isOk());
    }

    // Delete Person By Firstname And Lastname Test
    @Test
    public void testDeletePerson() throws Exception {

        // WHEN
        String personRecord = "{\"id\":8,\"firstName\":\"Peter\",\"lastName\":\"Duncan\",\"address\":\"644 Gershwin Cir\",\"zip\":\"97451\",\"city\":\"Culver\",\"phone\":\"841-874-6512\",\"email\":\"jaboyd@email.com\"}";

        this.mockMvc.perform(post("/person") // Create Person
                .contentType(MediaType.APPLICATION_JSON)
                .content(personRecord));

        this.mockMvc.perform(get("/person/8"))  // Read id 8 Person
                .andExpect(status().isOk());

        // GIVEN
        this.mockMvc.perform(delete("/person/Peter/Duncan")); // Delete Person by Firstname and Lastname

        // THEN
        this.mockMvc.perform(get("/person/8")) //Test Id 8
                .andExpect(status().isOk());

    }

    // No Update Person by Firstname And Lastname Test
    @Test
    public void testNoUpdatePersonByFirstnameAndLastname() throws Exception {
        // GIVEN
        String updatePerson = "{\"firstName\":\"Julio\",\"lastName\":\"Becker\",\"address\":\"1509 Culver St\",\"zip\":\"99999\",\"city\":\"Culver\",\"phone\":\"841-874-6512\",\"email\":\"jaboyd@email.com\"}";

        this.mockMvc.perform(get("/person/1")) // Read Person Id 1 => Firstname: Tenley and Lastname: Boyd
                .andExpect(status().isOk());
        // WHEN
        this.mockMvc.perform(put("/person/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(updatePerson))
                .andExpect(jsonPath("firstName", CoreMatchers.is("John")))
                .andExpect(jsonPath("lastName", CoreMatchers.is("Boyd")))
                .andExpect(jsonPath("zip", CoreMatchers.is("99999")));

        // THEN
        this.mockMvc.perform(get("/person/1"))
                .andExpect(status().isOk());

    }


}
