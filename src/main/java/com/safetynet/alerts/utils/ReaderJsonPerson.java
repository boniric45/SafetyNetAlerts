package com.safetynet.alerts.utils;

import com.safetynet.alerts.model.Persons;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.util.ArrayList;

@Component
public class ReaderJsonPerson {

    private static final Logger logger = LoggerFactory.getLogger(ReaderJsonPerson.class);

    public static ArrayList<Persons> readJsonFile(String dataFile) {

        ArrayList<Persons> listPerson = new ArrayList<>();
        Persons persons = new Persons();
        JSONParser parser = new JSONParser();

        try {
            logger.info("//////////////// loading Person JSON to H2 ////////////////");
            JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(dataFile));
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
            logger.error(".... Not Charged ....");
            e.printStackTrace();
        } finally {
            logger.info(".... Charged ....");
        }
        return listPerson;
    }

}











