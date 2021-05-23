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
import java.util.ArrayList;

public class ReadJsonMedicalRecord {

        private static final Logger logger = LoggerFactory.getLogger(ReaderJsonPerson.class);

        public static ArrayList<MedicalRecords> readJsonFileMedicalRecord(String dataFile) {

            ArrayList<MedicalRecords> listMedicalRecords = new ArrayList<>();
            JSONParser parser = new JSONParser();
            MedicalRecords medicalRecords = new MedicalRecords();

            try {
                logger.info("//////////////// loading Medical Records JSON to H2 ////////////////");
                JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(dataFile));
                JSONArray jsonArray = (JSONArray) jsonObject.get("medicalrecords");
                int id = 1;
                for (int i = 0; i < jsonArray.size(); i++) {
                    JSONObject jo = (JSONObject) jsonArray.get(i);

                    listMedicalRecords.add(medicalRecords);

                    String firstName = (String) jo.get("firstName");
                    String lastName = (String) jo.get("lastName");
                    String birthdate = (String) jo.get("birthdate");
                    String medications = (String) jo.get("medications").toString();
                    String allergies = (String) jo.get("allergies").toString();

                    medicalRecords.setId(id);
                    medicalRecords.setFirstName(firstName);
                    medicalRecords.setLastName(lastName);
                    medicalRecords.setBirthdate(birthdate);
                    medicalRecords.setMedications(medications);
                    medicalRecords.setAllergies(allergies);

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



