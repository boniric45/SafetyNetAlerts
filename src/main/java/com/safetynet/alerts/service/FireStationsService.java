package com.safetynet.alerts.service;

import com.safetynet.alerts.model.FireStations;
import com.safetynet.alerts.repository.FireStationsRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
@Service
public class FireStationsService {

    @Autowired
    private FireStationsRepository fireStationsRepository;
    private final List<FireStations> fireStationsList = new ArrayList<>();

    public Iterable<FireStations> listSaveFirestation (List<FireStations> list){
        fireStationsRepository.saveAll(list);
        return list;
    }

}
