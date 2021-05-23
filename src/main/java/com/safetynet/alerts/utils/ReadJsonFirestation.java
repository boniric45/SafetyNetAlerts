package com.safetynet.alerts.utils;

import com.safetynet.alerts.model.FireStations;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadJsonFirestation {

    private static final Logger logger = LoggerFactory.getLogger(ReaderJsonPerson.class);

    public static ArrayList<FireStations> readJsonFileFirestation(String dataFile) {
        ArrayList<FireStations> listFireStation = new ArrayList<>();
        JSONParser parser = new JSONParser();
        FireStations fireStations = new FireStations();

        try {
            logger.info("//////////////// loading FireStation JSON to H2 ////////////////");
            JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(dataFile));
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

                fireStations = new FireStations(id, station, address);
                id++;
            }


        } catch (IOException | ParseException e) {
            e.printStackTrace();
        } finally {
            logger.info(".... Charged ....");
        }

        return listFireStation;
    }
}
