package com.safetynet.alerts.testUnitaire;

import com.safetynet.alerts.model.Persons;
import com.safetynet.alerts.repository.PersonsRepository;
import com.safetynet.alerts.service.PersonsService;
import org.junit.Test;
import org.junit.jupiter.api.Disabled;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.text.ParseException;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class PersonsServiceTest {

    @Mock
    PersonsRepository personsRepository;

    @InjectMocks
    PersonsService personsService;

    @Test
    public void testWhenSavePersonItShouldReturnPerson() {

        // GIVEN
        Persons persons = new Persons(100, "John", "Steed", "10 th Street", "45", "New York", "118-356-684", "john.steed@email.com");

        // WHEN
        when(personsRepository.save(any(Persons.class))).thenReturn(persons);
        Persons created = personsService.createPerson(persons);

        // THEN
        assertThat(created).isSameAs(persons);
    }

 @Disabled
    public void testGetChildAlert() throws ParseException {
        String address = "892 Downing Ct";
        String contentList = "[\"Children List[LastName:Zemicks FirstName:Zach BirthDate:03/06/2017 Age:4]\",\"Adult List[ LastName: Zemicks FirstName: Sophia BirthDate: 03/06/1988 Age: 33,  LastName: Zemicks FirstName: Warren BirthDate: 03/06/1985 Age: 36]\"]";

        List<String> resultChildAlert = personsService.getChildAlert(address);
        System.out.println(resultChildAlert);
        when(personsService.getChildAlert(address)).thenReturn(Collections.singletonList(contentList));
        assertThat(contentList).isSameAs(resultChildAlert);

        System.out.println(contentList);

    }


}


