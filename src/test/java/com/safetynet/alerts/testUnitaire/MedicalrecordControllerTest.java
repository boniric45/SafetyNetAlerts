package com.safetynet.alerts.testUnitaire;

import com.safetynet.alerts.config.CustomProperties;
import com.safetynet.alerts.controller.MedicalsRecordsController;
import com.safetynet.alerts.model.MedicalRecords;
import com.safetynet.alerts.utils.DateIsValid;
import com.safetynet.alerts.utils.ReadJsonMedicalRecord;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MedicalrecordControllerTest {

    @Autowired
    public MedicalsRecordsController medicalsRecordsController;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CustomProperties props;

    private String firstName;
    private String lastName;
    private ArrayList<String> result;
    private String birthdate;
    private String medications;
    private String allergies;

    @BeforeEach
    private void setUpPerTest() {
        firstName = null;
        lastName = null;
        birthdate = null;
        result = new ArrayList<>();
        medications = null;
        allergies = null;
    }

    @Test
    public void testNoChargedMedicalRecord() {

        // GIVEN
        ReadJsonMedicalRecord.readJsonFileMedicalRecord(props.getJsonDatafile());
        String fakedate = "fakedate";

        // WHEN
        Boolean isvalid = DateIsValid.DateChecker.isValid(fakedate);

        //THEN
        Assertions.assertEquals(isvalid, false);
    }

    @Test
    public void testNoChargedFileMedicalRecord() {

        // GIVEN
        ArrayList<MedicalRecords> medicalRecordsList = ReadJsonMedicalRecord.readJsonFileMedicalRecord("datajson.xml");

        // WHEN
        assert medicalRecordsList != null;
        int result = medicalRecordsList.size();

        //THEN
        Assertions.assertEquals(result, 0);
    }


    @Test
    public void testchargedMedicalRecord() {

        // GIVEN
        int element = 23;
        medicalsRecordsController.saveMedicalRecordToH2(props.getJsonDatafile());

        // WHEN
        Iterable<MedicalRecords> medicalRecordsIterable = medicalsRecordsController.getMedicalRecordsAll();
        for (MedicalRecords mr : medicalRecordsIterable) {
            String resultMedicalRecord = mr.getFirstName();
            result.add(resultMedicalRecord);
        }

        //THEN
        Assertions.assertEquals(element, result.size());

    }


    // Create MedicalRecord Test
    @Test
    public void testCreateMedicalRecord() throws Exception {

        // GIVEN
        firstName = "Sylvanas";
        lastName = "Coursevent";
        birthdate = "05/04/1950";
        medications = "dodoxadin:30mg";
        allergies = "peanut";

        String medicalRecords = "{\"id\":24,\"firstName\":\"Sylvanas\",\"lastName\":\"Coursevent\",\"birthdate\":\"05/04/1950\",\"medications\":\"dodoxadin:30mg\",\"allergies\":\"peanut\"}";

        // WHEN
        mockMvc.perform(post("/medicalRecord")
                .contentType(MediaType.APPLICATION_JSON)
                .content(medicalRecords));

        // THEN
        mockMvc.perform(get("/medicalRecord/24"))
                .andExpect(jsonPath("firstName", CoreMatchers.is(firstName)))
                .andExpect(jsonPath("lastName", CoreMatchers.is(lastName)))
                .andExpect(jsonPath("birthdate", CoreMatchers.is(birthdate)))
                .andExpect(jsonPath("medications", CoreMatchers.is(medications)))
                .andExpect(jsonPath("allergies", CoreMatchers.is(allergies)));
    }

    // Read All MedicalRecord Test
    @Test
    public void testReadAllMedicalRecord() {

        // GIVEN
        int element = 22;

        // WHEN
        Iterable<MedicalRecords> medicalRecordsIterable = medicalsRecordsController.getMedicalRecordsAll();
        for (MedicalRecords mr : medicalRecordsIterable) {
            String resultMedicalRecord = mr.getFirstName();
            result.add(resultMedicalRecord);
        }

        // THEN
        Assertions.assertEquals(element, result.size());
    }

    // Read MedicalRecord By Id Test
    @Test
    public void testReadMedicalRecordById() {

        // GIVEN
        firstName = "Roger";
        lastName = "Boyd";
        birthdate = "09/06/2017";
        medications = "[]";
        allergies = "[]";

        // WHEN
        MedicalRecords medicalRecords = medicalsRecordsController.getMedicalRecordsById(4);

        // THEN
        Assertions.assertEquals(firstName, medicalRecords.getFirstName());
        Assertions.assertEquals(lastName, medicalRecords.getLastName());
        Assertions.assertEquals(birthdate, medicalRecords.getBirthdate());
        Assertions.assertEquals(medications, medicalRecords.getMedications());
        Assertions.assertEquals(allergies, medicalRecords.getAllergies());
    }

    // Update MedicalRecord Test
    @Test
    public void testUpdateMedicalRecord() {

        // GIVEN
        MedicalRecords updateMedicalRecords = new MedicalRecords(1, "John", "Boyd", "01/01/2002", "[hydrapermazol:500mg]", "[\"shellfish\"]");

        // WHEN
        medicalsRecordsController.getMedicalRecordsById(1);
        medicalsRecordsController.updateMedicalRecordById(1, updateMedicalRecords);
        MedicalRecords medicalRecordsAfterUpdate = medicalsRecordsController.getMedicalRecordsById(1);

        // THEN
        Assertions.assertEquals(updateMedicalRecords.getBirthdate(), medicalRecordsAfterUpdate.getBirthdate());
    }

    // Delete MedicalRecord By Firstname And Lastname Test
    @Test
    public void testDeleteMedicalRecord() throws Exception {
        // GIVEN
        firstName = "Tony";
        lastName = "Cooper";
        birthdate = "01/01/2002";
        medications = "[\"hydrapermazol:300mg\",\"dodoxadin:30mg\"]";
        allergies = "[\"shellfish\"]";
        MedicalRecords createMedicalRecord = new MedicalRecords(24, firstName, lastName, birthdate, medications, allergies);

        // WHEN
        medicalsRecordsController.createNewMedicalRecord(createMedicalRecord);
        medicalsRecordsController.deleteMedicalRecordByFirstNameAndLastName(firstName, lastName);

        // THEN
        mockMvc.perform(MockMvcRequestBuilders.get("/medicalRecord"))
                .andExpect(jsonPath("id", "24").doesNotExist())
                .andExpect(status().isOk());
    }

    @Test
    public void testMethodNotAllowedCreateMedicalRecord() throws Exception {

        // GIVEN
        String medicalRecords = "{\"id\":24,\"firstName\":\"Sylvanas\",\"lastName\":\"Coursevent\",\"birthdate\":\"05/04/1950\",\"medications\":\"dodoxadin:30mg\",\"allergies\":\"peanut\"}";

        // WHEN
        mockMvc.perform(post("/medicalRecord/24")
                .contentType(MediaType.APPLICATION_JSON)
                .content(medicalRecords))
                // THEN
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    public void testCreateMedicalRecordIsNull() {

        // WHEN
        MedicalRecords medicalRecords = null;

        // THEN
        Assertions.assertNull(medicalsRecordsController.createNewMedicalRecord(medicalRecords));

    }

    @Test
    public void testReadMedicalRecordByIdIsNull() throws Exception {

        // GIVEN
        int id = 400;

        // WHEN
        MedicalRecords medicalRecords = medicalsRecordsController.getMedicalRecordsById(id);

        // THEN
        Assertions.assertNull(medicalRecords);

    }

    @Test
    public void testUpdateMedicalRecordValueNull() {

        // GIVEN
        MedicalRecords medicalRecords = null;

        // WHEN
        MedicalRecords result = medicalsRecordsController.updateMedicalRecordById(1000, medicalRecords);

        // THEN
        Assertions.assertNull(result);

    }

    @Test
    public void testDeleteMedicalRecordValueNull() {

        // GIVEN
        MedicalRecords medicalRecords = new MedicalRecords(1, null, null, null, null, null);

        // WHEN
        medicalsRecordsController.deleteMedicalRecordByFirstNameAndLastName(null, null);

        // THEN
        Assertions.assertNull(medicalRecords.getFirstName());
        Assertions.assertNull(medicalRecords.getLastName());
        Assertions.assertNull(medicalRecords.getBirthdate());
        Assertions.assertNull(medicalRecords.getMedications());
        Assertions.assertNull(medicalRecords.getAllergies());

    }

}
