package com.safetynet.alerts.testUnitaire;

import com.safetynet.alerts.CustomProperties;
import com.safetynet.alerts.controller.PersonsController;
import com.safetynet.alerts.model.Persons;
import com.safetynet.alerts.service.PersonsService;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CustomProperties props;

    @Autowired
    private PersonsController personsController;

    @Autowired
    private PersonsService personsService;

    private String firstName;
    private String lastName;
    private List<String> result;

    @BeforeEach
    private void setUpPerTest() {
        firstName = null;
        lastName = null;
        result = new ArrayList<>();
    }

    @Test
    public void testChargedPerson() throws ParseException {
        // GIVEN
        firstName = "Eric";
        lastName = "Cadigan";

        // WHEN
        personsController.chargedPerson(props.getJsonDatafile());
        result = personsController.getPersonInfo(firstName, lastName);

        // THEN
        Assertions.assertEquals(1, result.size());

    }

    @Test
    public void testMethodisUnsupportedMediaTypeCreatePerson() throws Exception {
        // GIVEN
        String personRecord = "{\"id\":8,\"firstName\":\"Peter\",\"lastName\":\"Duncan\",\"address\":\"644 Gershwin Cir\",\"zip\":\"97451\",\"city\":\"Culver\",\"phone\":\"841-874-6512\",\"email\":\"jaboyd@email.com\"}";

        // WHEN
        MockHttpServletRequestBuilder req = post("/person")
                .content(personRecord);

        // THEN
        this.mockMvc.perform(req)
                .andExpect(status().isUnsupportedMediaType());

    }

    // Create Person
    @Test
    public void testCreatePerson() throws Exception {
        //GIVEN
        String person = "{\"firstName\":\"John\",\"lastName\":\"Boyd\",\"address\":\"1509 Culver St\",\"zip\":\"97451\",\"city\":\"Culver\",\"phone\":\"841-874-6512\",\"email\":\"jaboyd@email.com\"}";

        //WHEN
        MockHttpServletRequestBuilder req = post("/person")
                .contentType(MediaType.APPLICATION_JSON)
                .content(person);

        //THEN
        this.mockMvc.perform(req)
                .andExpect(status().isOk())
                .andExpect(jsonPath("firstName", CoreMatchers.is("John")))
                .andExpect(jsonPath("lastName", CoreMatchers.is("Boyd")));
    }

    @Test
    public void testMethodNotAllowedCreatePerson() throws Exception {
        // GIVEN
        String personRecord = "{\"id\":8,\"firstName\":\"Peter\",\"lastName\":\"Duncan\",\"address\":\"644 Gershwin Cir\",\"zip\":\"97451\",\"city\":\"Culver\",\"phone\":\"841-874-6512\",\"email\":\"jaboyd@email.com\"}";

        // WHEN
        MockHttpServletRequestBuilder req = post("/person/8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(personRecord);

        // THEN
        this.mockMvc.perform(req)
                .andExpect(status().isMethodNotAllowed());

    }

    // Read All Person Test
    @Test
    public void testReadAllPersons() throws Exception {
        mockMvc.perform(get("/person")).andExpect(status().isOk());
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

        //GIVEN
        String updatePerson = "{\"firstName\":\"John\",\"lastName\":\"Boyd\",\"address\":\"1509 Culver St\",\"zip\":\"98000\",\"city\":\"Paris\",\"phone\":\"841-874-6512\",\"email\":\"jaboyd@email.com\"}";

        // Read Person Id 2
        mockMvc.perform(get("/person/1"))
                .andExpect(status().isOk());

        //WHEN
        //Update Person with updatePerson
        MockHttpServletRequestBuilder req = put("/person/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(updatePerson);

        mockMvc.perform(req)
                .andExpect(status().isOk())
                .andExpect(jsonPath("zip", CoreMatchers.is("98000")))
                .andExpect(jsonPath("city", CoreMatchers.is("Paris")));

        //THEN
        // Read Person Id 2
        mockMvc.perform(get("/person/2"))
                .andExpect(status().isOk());
    }

    // Delete Person By Firstname And Lastname Test
    @Test
    public void testDeletePerson() throws Exception {

        //WHEN
        String personRecord = "{\"id\":8,\"firstName\":\"Peter\",\"lastName\":\"Duncan\",\"address\":\"644 Gershwin Cir\",\"zip\":\"97451\",\"city\":\"Culver\",\"phone\":\"841-874-6512\",\"email\":\"jaboyd@email.com\"}";

        // Create Person
        this.mockMvc.perform(post("/person")
                .contentType(MediaType.APPLICATION_JSON)
                .content(personRecord));
        // Read id 8 Person
        this.mockMvc.perform(get("/person/8"))
                .andExpect(status().isOk());

        //GIVEN
        // Delete Person by Firstname and Lastname
        this.mockMvc.perform(delete("/person/Peter/Duncan")); // Delete by Firstname and Lastname

        //THEN
        mockMvc.perform(MockMvcRequestBuilders.get("/person"))
                .andExpect(jsonPath("id", "8").doesNotExist())
                .andExpect(status().isOk());

    }

    @Test
    public void testCommunityEmail() {
        // GIVEN
        String city = "Narbonne";

        // WHEN
        result = personsService.communityEmail(city);

        // THEN
        Assertions.assertEquals(0, result.size());
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

    @Test
    public void testUpdatePersonDoesNotExist() throws Exception {
        //GIVEN
        Persons person = new Persons(120,"Jean","Jean","Street Flower","45661","Manathan","456-469-753","jjean@email.com");

        personsController.updatePerson(120,person);

        //THEN
        mockMvc.perform(put("/person/120"))
                .andExpect(jsonPath("id", "120").doesNotExist());

    }

    @Test
    public void testCreatePersonValueNull() {
        //GIVEN
        Persons person = new Persons(0,null,null,null,null,null,null,null);

        //WHEN
        personsController.createPerson(person);

        //THEN
        Assertions.assertNull(person.getAddress());
        Assertions.assertNull(person.getLastName());
        Assertions.assertNull(person.getFirstName());
        Assertions.assertNull(person.getZip());
        Assertions.assertNull(person.getCity());
        Assertions.assertNull(person.getPhone());
        Assertions.assertNull(person.getEmail());

    }

    @Test
    public void testUpdatePersonValueNull() {
        //GIVEN
        Persons person = new Persons(1,null,null,null,null,null,null,null);

        //WHEN
        personsController.updatePerson(1,person);

        //THEN
        Assertions.assertNull(person.getLastName());
        Assertions.assertNull(person.getFirstName());
        Assertions.assertNull(person.getAddress());
        Assertions.assertNull(person.getZip());
        Assertions.assertNull(person.getCity());
        Assertions.assertNull(person.getPhone());
        Assertions.assertNull(person.getEmail());
    }

    @Test
    public void testDeletePersonValueNull() {
        //GIVEN
        Persons person = new Persons(1,null,null,null,null,null,null,null);

        //WHEN
        personsController.deletePersonByFirstNameAndLastName(null,null);

        //THEN
        Assertions.assertNull(person.getLastName());
        Assertions.assertNull(person.getFirstName());
        Assertions.assertNull(person.getAddress());
        Assertions.assertNull(person.getZip());
        Assertions.assertNull(person.getCity());
        Assertions.assertNull(person.getPhone());
        Assertions.assertNull(person.getEmail());
    }



}
