package com.safetynet.alerts.utils;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

/**
 * calculate the age according to the date of birth return long
 */
@Component
public class CalculateAgeUtil {
    public static long getAge(String birthDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
        LocalDate date1 = LocalDate.parse(birthDate, formatter);
        LocalDate date2 = LocalDate.now();
        TemporalUnit unit = ChronoUnit.YEARS;
        return Period.between(date1, date2).get(unit);
    }
}
