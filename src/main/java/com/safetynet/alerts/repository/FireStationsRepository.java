package com.safetynet.alerts.repository;

import com.safetynet.alerts.model.FireStations;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository Firestation
 */
@Repository
public interface FireStationsRepository extends CrudRepository<FireStations, String> {

    Iterable<FireStations> findAllByStation(String station);

    Optional<FireStations> findById(int id);

    void deleteFirestationByStationAndAddress(final String station, final String address);

}
