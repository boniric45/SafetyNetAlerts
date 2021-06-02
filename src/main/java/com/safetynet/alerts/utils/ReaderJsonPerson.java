package com.safetynet.alerts.utils;

import com.fasterxml.jackson.core.json.UTF8StreamJsonParser;
import com.safetynet.alerts.model.Persons;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ContainerFactory;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

@Component
public class ReaderJsonPerson {

    private static final Logger logger = LogManager.getLogger(ReaderJsonPerson.class);

    public static ArrayList<Persons> readJsonFile(String dataFile) {

        ArrayList<Persons> listPerson = new ArrayList<>();
        Persons persons = new Persons();
        JSONParser parser = new JSONParser();

        try {
            logger.info("Read JSON Person to H2");

            JSONObject jsonObject = (JSONObject) parser.parse(new InputStreamReader(new FileInputStream(dataFile),StandardCharsets.UTF_8));
            JSONArray jsonArray = (JSONArray) jsonObject.get("persons");

            int id = 1;

            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jo = (JSONObject) jsonArray.get(i);
                listPerson.add(persons);
                String lastName = (String) jo.get("lastName");
                String firstName = (String) jo.get("firstName");
                String address = (String) jo.get("address");
                String zip = (String) jo.get("zip");
                String city = (String) jo.get("city");
                String phone = (String) jo.get("phone");
                String email = (String) jo.get("email");

                persons.setId(id);
                persons.setLastName(lastName);
                persons.setFirstName(firstName);
                persons.setAddress(address);
                persons.setZip(zip);
                persons.setCity(city);
                persons.setPhone(phone);
                persons.setEmail(email);

                persons = new Persons(id, lastName, firstName, address, zip, city, phone, email);
                id++;
            }
        } catch (Exception e) {
            logger.error("Error Read JSON Person: " + e);
        } finally {
            logger.info("Person saved to H2");
        }
        return listPerson;
    }
}











