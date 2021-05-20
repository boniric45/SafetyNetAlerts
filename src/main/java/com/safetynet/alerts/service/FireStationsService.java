package com.safetynet.alerts.service;

import com.safetynet.alerts.model.FireStations;
import com.safetynet.alerts.repository.FireStationsRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
public class FireStationsService {

    @Autowired
    private FireStationsRepository fireStationsRepository;

    public Optional<FireStations> getFireStation(final Long id) {
        return fireStationsRepository.findById(id);
    }

    public Iterable<FireStations> getFireStations() {
        return fireStationsRepository.findAll();
    }

    public void deleteFireStations(final Long id) {
        fireStationsRepository.deleteById(id);
    }

    public FireStations saveFireStations(FireStations fireStations) {
        FireStations savedFireStations = fireStationsRepository.save(fireStations);
        return savedFireStations;
    }
}
