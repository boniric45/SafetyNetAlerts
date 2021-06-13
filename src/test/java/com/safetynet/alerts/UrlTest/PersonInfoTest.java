package com.safetynet.alerts.UrlTest;

import com.safetynet.alerts.model.Persons;
import com.safetynet.alerts.service.PersonsService;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.junit.matchers.JUnitMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.web.servlet.function.RequestPredicates.contentType;

@SpringBootTest
@AutoConfigureMockMvc
public class PersonInfoTest {

    @Autowired
    public MockMvc mockMvc;

    @Mock
    PersonsService personsService;

//    @Test
//    public void testReturnListPersonWithMedicalRecordAndAllergies() throws Exception {
//
//        //GIVEN
//        String person = "{\"firstName\":\"Sylvanas\",\"lastName\":\"Coursevent\",\"address\":\"1509 Culver St\",\"zip\":\"97451\",\"city\":\"Culver\",\"phone\":\"841-874-6512\",\"email\":\"jaboyd@email.com\"}";
//        //String result = "[\"firstName: Sylvanas lastName: Coursevent address: 1509 Culver St age: 75 mail: jaboyd@email.com allergies: [] medications: [\\\"tradoxidine:400mg\\\"]\"]";
//String result ="[\"firstName: Sylvanas lastName: Coursevent address: 1509 Culver St age: 75 mail: jaboyd@email.com allergies: [] medications: [\\\"tradoxidine:400mg\\\"]\"]";
//
//        MockHttpServletRequestBuilder req = post("/person")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(person);
//
//        this.mockMvc.perform(req)
//                .andExpect(status().isOk())
//                .andDo(print());
//
//        this.mockMvc.perform(get("/personInfo?firstName=Sylvanas&lastName=Coursevent" )
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andDo(print())
//                .andExpect(content().string(containsString(result)));
//
//        //TODO à revoir médication erroné
//    }

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
