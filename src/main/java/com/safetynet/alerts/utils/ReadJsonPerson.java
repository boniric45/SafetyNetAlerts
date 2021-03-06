package com.safetynet.alerts.utils;

import com.safetynet.alerts.model.Persons;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * read data.json and save person data into h2 bdd
 */

public class ReadJsonPerson {

    private static final Logger logger = LogManager.getLogger(ReadJsonPerson.class);

    public static ArrayList<Persons> readJsonFile(String dataFile) {

        ArrayList<Persons> listPerson = new ArrayList<>();
        Persons persons = new Persons();
        JSONParser parser = new JSONParser();

        try {
            logger.info("Read JSON Person to H2");

            JSONObject jsonObject = (JSONObject) parser.parse(new InputStreamReader(new FileInputStream(dataFile), StandardCharsets.UTF_8));
            JSONArray jsonArray = (JSONArray) jsonObject.get("persons");

            int id = 1;

            for (Object o : jsonArray) {
                JSONObject jo = (JSONObject) o;
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











