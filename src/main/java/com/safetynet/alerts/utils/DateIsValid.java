package com.safetynet.alerts.utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * verify date is valid return boolean
 */
public class DateIsValid {

    private static final Logger logger = LogManager.getLogger(ReadJsonMedicalRecord.class);

    public static class DateChecker {
        public static boolean isValid(String strdate) {
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

            try {
                Date date = df.parse(strdate);
                if (date.getMonth() > 12) {
                    logger.info(String.valueOf(date.getMonth()));
                }
                return true;
            } catch (java.text.ParseException ex) {
                logger.error(DateChecker.class.getName());
                return false;
            }
        }
    }
}
