package com.safetynet.alerts.testIntegration;

import com.safetynet.alerts.utils.ReaderJsonPerson;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ReadJsonPersonTest {
    @Test
    public void testReadJsonPersonIsFalse() {
        ReaderJsonPerson.readJsonFile("datas.xml");
    }

}
