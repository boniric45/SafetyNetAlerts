package com.safetynet.alerts.testIntegration;

import com.safetynet.alerts.utils.ReadJsonFirestation;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ReadJsonFirestationTest {

    @Test
    public void testReadJsonFirestationIsFalse() {
        ReadJsonFirestation.readJsonFileFirestation("datas.xml");
    }
}
