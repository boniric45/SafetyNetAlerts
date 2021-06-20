package com.safetynet.alerts.UrlTest;

import com.safetynet.alerts.controller.MedicalsRecordsController;
import com.safetynet.alerts.model.MedicalRecords;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
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
public class PersonInfoTest {

    @Autowired
    public MockMvc mockMvc;

    @Autowired
    private MedicalsRecordsController medicalsRecordsController;

    @Test
    public void testReturnListPersonWithMedicalRecordAndAllergies() throws Exception {

        // GIVEN
        String firstName = "Sylvanas";
        String lastName = "Coursevent";
        String bitrhdate = "10/10/1950";
        String medications = "doliprane";
        String allergies = "peanut";
        MedicalRecords medicalRecordsCreate = new MedicalRecords(24, "Sylvanas", "Coursevent", "10/10/1950", "doliprane", "peanut");

        // WHEN
        medicalsRecordsController.createMedicalRecord(medicalRecordsCreate);
        MedicalRecords medicalRecordResult = medicalsRecordsController.getMedicalRecordsById(24);

        // THEN
        Assertions.assertEquals(firstName, medicalRecordResult.getFirstName());
        Assertions.assertEquals(lastName, medicalRecordResult.getLastName());
        Assertions.assertEquals(bitrhdate, medicalRecordResult.getBirthdate());
        Assertions.assertEquals(medications, medicalRecordResult.getMedications());
        Assertions.assertEquals(allergies, medicalRecordResult.getAllergies());

    }

    @Test
    public void testNoReturnListPersonWithMedicalRecordAndAllergiesWithoutfirstname() throws Exception {

        String firstName = "";
        String lastName = "Carman";
        String result = "[]";

        this.mockMvc.perform(get("/personInfo?firstName=" + firstName + "&lastName=" + lastName))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(result)));
    }

    @Test
    public void testNoReturnListPersonWithMedicalRecordAndAllergiesWithoutLastname() throws Exception {
        String firstName = "Tessa";
        String lastName = "";
        String result = "[]";

        this.mockMvc.perform(get("/personInfo?firstName=" + firstName + "&lastName=" + lastName))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(result)));
    }

    @Test
    public void testNoReturnListPersonWithMedicalRecordAndAllergiesWithoutFirstAndLastname() throws Exception {
        String firstName = "";
        String lastName = "";
        String result = "[]";

        this.mockMvc.perform(get("/personInfo?firstName=" + firstName + "&lastName=" + lastName))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(result)));
    }

    @Test
    public void testReturnListPersonWithMedicalRecordAndAllergiesNotFound() throws Exception {
        String firstName = "Tessa";
        String lastName = "Carmani";
        String result = "[]";

        this.mockMvc.perform(get("/personInfo?firstName=" + firstName + "&lastName=" + lastName))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(result)));
    }
}
