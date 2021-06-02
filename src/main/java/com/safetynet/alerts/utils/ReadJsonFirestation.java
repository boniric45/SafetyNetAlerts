package com.safetynet.alerts.utils;

import com.safetynet.alerts.model.FireStations;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class ReadJsonFirestation {

    private static final Logger logger = LogManager.getLogger(ReadJsonFirestation.class);

    public static ArrayList<FireStations> readJsonFileFirestation(String dataFile) {
        ArrayList<FireStations> listFireStation = new ArrayList<>();
        JSONParser parser = new JSONParser();
        FireStations fireStations = new FireStations();

        try {
            logger.info("Read JSON FireStation to H2");
            JSONObject jsonObject = (JSONObject) parser.parse(new InputStreamReader(new FileInputStream(dataFile), StandardCharsets.UTF_8));
            JSONArray jsonArray = (JSONArray) jsonObject.get("firestations");
            int id = 1;
            for (int i = 0; i < jsonArray.size(); i++) {

                JSONObject jo = (JSONObject) jsonArray.get(i);
                listFireStation.add(fireStations);
                String station = (String) jo.get("station");
                String address = (String) jo.get("address");

                fireStations.setId(id);
                fireStations.setStation(station);
                fireStations.setAddress(address);

                fireStations = new FireStations(id,station, address);
                id++;
            }

        } catch (IOException | ParseException e) {
            logger.error("Error Read JSON FireStation: "+e);
        } finally {
            logger.info("FireStation saved to H2");
        }

        return listFireStation;
    }
}
