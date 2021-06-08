package com.safetynet.alerts.testUnitaire;

import com.safetynet.alerts.SafetyNetAlertsApplication;
import com.safetynet.alerts.service.PersonsService;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EndpointTest {

    @Autowired
    PersonsService personsService;
    @Autowired
    private MockMvc mockMvc;

    ///////////////// EndPoint Persons /////////////////

    @Test
    public void test_Endpoint_Create_Person() throws Exception {
        //GIVEN
        String person = "{\"firstName\":\"John\",\"lastName\":\"Boyd\",\"address\":\"1509 Culver St\",\"zip\":\"97451\",\"city\":\"Culver\",\"phone\":\"841-874-6512\",\"email\":\"jaboyd@email.com\"}";

        //WHEN
        MockHttpServletRequestBuilder req = post("/person").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(person);

        //THEN
        this.mockMvc.perform(req)
                .andExpect(status().isOk())
                .andExpect(jsonPath("firstName", CoreMatchers.is("John")))
                .andExpect(jsonPath("lastName", CoreMatchers.is("Boyd")));
    }

    @Test
    public void test_Endpoint_Read_Person_By_Id() throws Exception {

        mockMvc.perform(get("/person"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName", CoreMatchers.is("John")));
    }

    @Test
    public void test_Endpoint_Get_All_Persons() throws Exception {
        mockMvc.perform(get("/person")).andExpect(status().isOk());
    }

    @Test
    public void test_Endpoint_Update_Person() throws Exception {

        //GIVEN
          String updatePerson = "{\"firstName\":\"John\",\"lastName\":\"Boyd\",\"address\":\"1509 Culver St\",\"zip\":\"97451\",\"city\":\"Culver\",\"phone\":\"841-874-6512\",\"email\":\"jaboyd@email.com\"}";

        //WHEN
        MockHttpServletRequestBuilder req = put("/person/2").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(updatePerson);

        //THEN
        this.mockMvc.perform(req)
                .andExpect(status().isOk())
                .andExpect(jsonPath("firstName", CoreMatchers.is("Jacob")))
                .andExpect(jsonPath("lastName", CoreMatchers.is("Boyd")));
    }

    @Test
    public void test_Endpoint_No_Update_Firstname_And_Lastname() throws Exception {
        //GIVEN
        String updatePerson = "{\"firstName\":\"Tenlay\",\"lastName\":\"Boyds\",\"address\":\"1509 Culver St\",\"zip\":\"97451\",\"city\":\"Culver\",\"phone\":\"841-874-6512\",\"email\":\"jaboyd@email.com\"}";

        //WHEN
        MockHttpServletRequestBuilder req = put("/person/3").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(updatePerson);

        //THEN
        this.mockMvc.perform(req)
                .andExpect(jsonPath("firstName", CoreMatchers.is("Tenley")))
                .andExpect(jsonPath("lastName", CoreMatchers.is("Boyd")));

    }


    @Test
    public void test_Endpoint_Delete_Person() throws Exception {

        //WHEN
        ResultActions resultActions = mockMvc.perform(get("/person/1")); // by Id
        System.out.println(resultActions);
        //GIVEN
        this.mockMvc.perform(delete("/person/John/Boyd")); // Delete by Firstname and Lastname
        System.out.println(resultActions);
        //THEN
        this.mockMvc.perform(get("/person/1")) //Test Id 1
                .andExpect(status().isOk())
                .andExpect(status().reason((String) null));
    }


    @Test
    public void test_Endpoint_Get_Firestation() throws Exception {
        mockMvc.perform(get("/firestation"))
                .andExpect(status().isOk());
    }

    @Test
    public void testEndpointGetMedicalRecords() throws Exception {
        mockMvc.perform(get("/medicalRecord"))
                .andExpect(status().isOk());
    }



}
