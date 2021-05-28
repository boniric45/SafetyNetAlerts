package com.safetynet.alerts.utils;

import com.safetynet.alerts.model.MedicalRecords;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;

public class ReadJsonMedicalRecord {

    private static final Logger logger = LoggerFactory.getLogger(ReaderJsonPerson.class);

    public static class DateChecker {
        public static boolean isValid(String strdate) {
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

            try {
                Date date = df.parse(strdate);
                if (date.getMonth()>12){
                    logger.info(String.valueOf(date.getMonth()));

                }



                return true;
            } catch (java.text.ParseException ex) {
                logger.error(DateChecker.class.getName());
                return false;
            }
        }
    }

    public static ArrayList<MedicalRecords> readJsonFileMedicalRecord(String jsonDatafile) {
        ArrayList<MedicalRecords> listMedicalRecords = new ArrayList<>();
        JSONParser parser = new JSONParser();
        MedicalRecords medicalRecords = new MedicalRecords();

        try {
            logger.info("//////////////// loading Medical Records JSON to H2 ////////////////");
            JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(jsonDatafile));
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


//                if (birthdate>12){
//                    medicalRecords.setBirthdate("null");
//                    medicalRecords.setId(id);
//                    medicalRecords.setFirstName(firstName);
//                    medicalRecords.setLastName(lastName);
//                    medicalRecords.setMedications(medications);
//                    medicalRecords.setAllergies(allergies);
//
//                } else {
//                    medicalRecords.setBirthdate(birthdate);
//                    medicalRecords.setId(id);
//                    medicalRecords.setFirstName(firstName);
//                    medicalRecords.setLastName(lastName);
//                    medicalRecords.setMedications(medications);
//                    medicalRecords.setAllergies(allergies);
//                }


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

                medicalRecords = new MedicalRecords(id,firstName,lastName,birthdate,medications,allergies);
                id++;
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        } finally {
            logger.info(".... Charged ....");
        }
        return listMedicalRecords;


    }


}



