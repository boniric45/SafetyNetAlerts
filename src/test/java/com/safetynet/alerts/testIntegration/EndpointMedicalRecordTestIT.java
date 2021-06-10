package com.safetynet.alerts.testIntegration;

import com.safetynet.alerts.service.MedicalsRecordsService;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EndpointMedicalRecordTestIT {

    @Autowired
    MedicalsRecordsService medicalsRecordsService;

    @Autowired
    private MockMvc mockMvc;

    // Create Medical Record Test
    @Test
    public void create_MedicalRecord_Test() throws Exception {
        //GIVEN
        String mrRaw = "{\"firstName\":\"John\",\"lastName\":\"Boyd\",\"birthdate\":\"03/06/1985\",\"medications\":\"[aznol:60mg]\",\"allergies\":\"[peanut]\"}";

        //WHEN
        MockHttpServletRequestBuilder req = post("/medicalRecord")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mrRaw);

        //THEN
        this.mockMvc.perform(req)
                .andExpect(status().isOk())
                .andExpect(jsonPath("firstName", CoreMatchers.is("John")))
                .andExpect(jsonPath("lastName", CoreMatchers.is("Boyd")))
                .andExpect(jsonPath("birthdate", CoreMatchers.is("03/06/1985")))
                .andExpect(jsonPath("medications", CoreMatchers.is("[aznol:60mg]")))
                .andExpect(jsonPath("allergies", CoreMatchers.is("[peanut]")));

    }

    // Read All Medical Record Test
    @Test
    public void read_All_MedicalRecord_Test() throws Exception {
        this.mockMvc.perform(get("/medicalRecord/7"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("firstName", CoreMatchers.is("Tessa")))
                .andExpect(jsonPath("lastName", CoreMatchers.is("Carman")));
    }

    // Read One Medical Record Test
    @Test
    public void get_One_MedicalRecord_Test() throws Exception {
        this.mockMvc.perform(get("/medicalRecord/6"))
                .andExpect(status().isOk());


    }

    // Update Medical Record Test
    @Test
    public void update_MedicalRecord_Test() throws Exception {
        //GIVEN
        String updateMedicalRecord = "{\"firstName\":\"Felicia\",\"lastName\":\"Boyd\",\"birthdate\":\"01/02/1906\",\"medications\":\"[tetracyclaz:650mg]\",\"allergies\":\"[xilliathal]\"}";

        this.mockMvc.perform(get("/medicalRecord/5")).andDo(print());

        //WHEN
        MockHttpServletRequestBuilder req = put("/medicalRecord/5")
                .contentType(MediaType.APPLICATION_JSON)
                .content(updateMedicalRecord);

        //THEN
        this.mockMvc.perform(req)
                .andExpect(status().isOk())
                .andExpect(jsonPath("firstName", CoreMatchers.is("Felicia")))
                .andExpect(jsonPath("lastName", CoreMatchers.is("Boyd")))
                .andExpect(jsonPath("birthdate", CoreMatchers.is("01/02/1906")))
                .andExpect(jsonPath("medications", CoreMatchers.is("[tetracyclaz:650mg]")))
                .andExpect(jsonPath("allergies", CoreMatchers.is("[xilliathal]")));

        this.mockMvc.perform(get("/medicalRecord/5")).andDo(print());
    }

    // Delete Medical Record Test
    @Test
    public void delete_MedicalRecord_Test() throws Exception {

        //GIVEN
        String medRec = "{\"id\": 8,\"firstName\":\"Peter\",\"lastName\":\"Duncan\",\"birthdate\":\"03/06/1985\",\"medications\":\"[aznol:60mg]\",\"allergies\":\"[peanut]\"}";

        //WHEN
        // Create medicalRecord
        this.mockMvc.perform(post("/medialRecord")
                .contentType(MediaType.APPLICATION_JSON)
                .content(medRec));

        this.mockMvc.perform(get("/medicalRecord/8")) //Test Id 8
                .andExpect(status().isOk());

        //THEN
        this.mockMvc.perform(delete("/medicalRecord/Peter/Duncan")); // Delete by Firstname and Lastname

        this.mockMvc.perform(get("/medicalRecord/8")) //Test Id 8
                .andExpect(status().isOk());

    }

}
