package com.safetynet.alerts.repository;

import com.safetynet.alerts.model.Persons;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonsRepository extends CrudRepository<Persons, Integer> {

    List<Persons> findMailByCity(String city);

    List<Persons> findAllByAddress(String address);

    void deletePersonByFirstNameAndLastName(String firstName, String lastName);

}
