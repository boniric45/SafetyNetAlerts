package com.safetynet.alerts.utils;

import com.safetynet.alerts.model.MedicalRecords;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.Date;

@Component
public class CalculateAgeUtil {


    public static long getAge(String birthDate) throws ParseException {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
            LocalDate date1 = LocalDate.parse(birthDate, formatter);
            LocalDate date2 = LocalDate.now();
            TemporalUnit unit = ChronoUnit.YEARS;
            return Period.between(date1, date2).get(unit);
    }

}
