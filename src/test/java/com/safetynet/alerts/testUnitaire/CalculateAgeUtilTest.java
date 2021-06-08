package com.safetynet.alerts.testUnitaire;

import com.safetynet.alerts.utils.CalculateAgeUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CalculateAgeUtilTest {

    @Test
    public void testGetAgeTest() throws Exception {
        //GIVEN
        long age = 21;

        //WHEN
        long result = CalculateAgeUtil.getAge("05/18/2000");

        //THEN
        Assertions.assertEquals(age, result);
    }

}
