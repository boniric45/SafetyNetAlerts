package com.safetynet.alerts.testIntegration;

import com.safetynet.alerts.utils.ReadJsonMedicalRecord;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ReadJsonMedicalRecordTest {

    @Test
    public void testReadJsonMedicalRecordIsFalse() {
        ReadJsonMedicalRecord.readJsonFileMedicalRecord("datas.xml");
    }
}
