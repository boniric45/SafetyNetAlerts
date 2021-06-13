package com.safetynet.alerts.UrlTest;

import com.safetynet.alerts.model.Persons;
import com.safetynet.alerts.service.PersonsService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.junit.matchers.JUnitMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CommunityEmailTest {

    @Autowired
    public MockMvc mockMvc;

    @Mock
    PersonsService personsService;

    @Test
    public void testReturnEmailPersonFromTheCity() throws Exception {
        String city = "Culver";
        String result = "jaboyd@email.com";

        this.mockMvc.perform(get("/communityEmail?city="+city))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(result)));
    }

    @Test
    public void testgetAll() throws Exception {

        ResultActions resultActions = this.mockMvc.perform(get("/person"));
//        resultActions.andDo(print());

        Iterable<Persons> result = personsService.getPersonAll();
        List<Persons> listResult = (List<Persons>) result;

        for (Persons personsIterable : result){
            System.out.println(personsIterable.getFirstName());
        }

        System.out.println(listResult);


    }

}
