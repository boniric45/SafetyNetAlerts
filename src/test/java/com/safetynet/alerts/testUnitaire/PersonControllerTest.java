package com.safetynet.alerts.testUnitaire;

import com.safetynet.alerts.CustomProperties;
import com.safetynet.alerts.controller.PersonsController;
import com.safetynet.alerts.model.Persons;
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

    private int count;
    private Iterable<Persons> resultIterable;

    @BeforeEach
    private void setUpPerTest() {
        count = 0;
        resultIterable = null;
    }

    @Test
    public void testChargedPerson() throws ParseException {

        // GIVEN
        personsController.chargedPerson(props.getJsonDatafile());

        // WHEN
        resultIterable = personsController.getPersonAll();
        for (Persons persons : resultIterable) {
            count++;
        }


        // THEN
        Assertions.assertEquals(24, count);

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

        // GIVEN
        String updatePerson = "{\"firstName\":\"John\",\"lastName\":\"Boyd\",\"address\":\"1509 Culver St\",\"zip\":\"98000\",\"city\":\"Paris\",\"phone\":\"841-874-6512\",\"email\":\"jaboyd@email.com\"}";


        mockMvc.perform(get("/person/1")) // Read Person Id 1
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
        mockMvc.perform(get("/person/1")) // Read Person Id 1
                .andExpect(status().isOk());
    }

    // Delete Person By Firstname And Lastname Test
    @Test
    public void testDeletePerson() throws Exception {

        // GIVEN
        String personRecord = "{\"id\":8,\"firstName\":\"Peter\",\"lastName\":\"Duncan\",\"address\":\"644 Gershwin Cir\",\"zip\":\"97451\",\"city\":\"Culver\",\"phone\":\"841-874-6512\",\"email\":\"jaboyd@email.com\"}";

        this.mockMvc.perform(post("/person")  // Create Person
                .contentType(MediaType.APPLICATION_JSON)
                .content(personRecord));

        this.mockMvc.perform(get("/person/8")) // Read id 8 Person
                .andExpect(status().isOk());

        // WHEN
        this.mockMvc.perform(delete("/person/Peter/Duncan")); // Delete Person by Firstname and Lastname

        // THEN
        mockMvc.perform(MockMvcRequestBuilders.get("/person"))
                .andExpect(jsonPath("id", "8").doesNotExist())
                .andExpect(status().isOk());
    }

    @Test
    public void testCreatePersonRequestBodyIsNull() throws Exception {

        // GIVEN
        String person = "";

        // WHEN
        MockHttpServletRequestBuilder req = post("/person")
                .contentType(MediaType.APPLICATION_JSON)
                .content(person);

        // THEN
        this.mockMvc.perform(req)
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testErrorCreatePerson() throws Exception {

        Persons persons = null;
        Assertions.assertNull(personsController.createPerson(persons));

    }

    @Test
    public void testErrorReadPersonById() {

        // GIVEN
        int id = 400;

        // WHEN
        Persons persons = personsController.getPersonById(id);

        // GIVEN
        Assertions.assertNull(persons);
    }

    @Test
    public void testUpdatePersonDoesNotExist() throws Exception {

        // GIVEN
        Persons person = new Persons(120, "Jean", "Jean", "Street Flower", "45661", "Manathan", "456-469-753", "jjean@email.com");

        // WHEN
        personsController.updatePerson(120, person);

        // THEN
        mockMvc.perform(put("/person/120"))
                .andExpect(jsonPath("id", "120").doesNotExist());
    }

    @Test
    public void testCreatePersonValueNull() {

        // GIVEN
        Persons person = new Persons(0, null, null, null, null, null, null, null);

        // WHEN
        personsController.createPerson(person);

        // THEN
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

        // GIVEN
        Persons person = new Persons(1, null, null, null, null, null, null, null);

        // WHEN
        personsController.updatePerson(1, person);

        // THEN
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

        // GIVEN
        Persons person = new Persons(1, null, null, null, null, null, null, null);

        // WHEN
        personsController.deletePersonByFirstNameAndLastName(null, null);

        // THEN
        Assertions.assertNull(person.getLastName());
        Assertions.assertNull(person.getFirstName());
        Assertions.assertNull(person.getAddress());
        Assertions.assertNull(person.getZip());
        Assertions.assertNull(person.getCity());
        Assertions.assertNull(person.getPhone());
        Assertions.assertNull(person.getEmail());
    }
}
