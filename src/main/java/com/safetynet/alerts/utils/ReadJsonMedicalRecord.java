package com.safetynet.alerts.utils;

import com.safetynet.alerts.model.MedicalRecords;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ReadJsonMedicalRecord {

    private static final Logger logger = LogManager.getLogger(ReadJsonMedicalRecord.class);

    public static ArrayList<MedicalRecords> readJsonFileMedicalRecord(String dataFile) {
        ArrayList<MedicalRecords> listMedicalRecords = new ArrayList<>();
        JSONParser parser = new JSONParser();
        MedicalRecords medicalRecords = new MedicalRecords();

        try {
            logger.info("Read JSON MedicalRecord to H2");
            JSONObject jsonObject = (JSONObject) parser.parse(new InputStreamReader(new FileInputStream(dataFile), StandardCharsets.UTF_8));
            JSONArray jsonArray = (JSONArray) jsonObject.get("medicalrecords");
            int id = 1;
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jo = (JSONObject) jsonArray.get(i);

                listMedicalRecords.add(medicalRecords);

                String firstName = (String) jo.get("firstName");
                String lastName = (String) jo.get("lastName");
                String medications = (String) jo.get("medications").toString();
                String allergies = (String) jo.get("allergies").toString();
                String birthdate = (String) jo.get("birthdate");


                if (DateChecker.isValid(birthdate)) {
                    medicalRecords.setBirthdate(birthdate);
                    medicalRecords.setId(id);
                    medicalRecords.setFirstName(firstName);
                    medicalRecords.setLastName(lastName);
                    medicalRecords.setMedications(medications);
                    medicalRecords.setAllergies(allergies);
                } else {
                    medicalRecords.setBirthdate(null);
                    medicalRecords.setId(id);
                    medicalRecords.setFirstName(firstName);
                    medicalRecords.setLastName(lastName);
                    medicalRecords.setMedications(medications);
                    medicalRecords.setAllergies(allergies);
                }

                medicalRecords = new MedicalRecords(id, firstName, lastName, birthdate, medications, allergies);
                id++;
            }
        } catch (IOException | ParseException e) {
            logger.error("Error Read JSON MedicalRecord: " + e);
        } finally {
            logger.info("MedicalRecord saved to H2");
        }
        return listMedicalRecords;
    }

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



