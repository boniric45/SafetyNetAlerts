package com.safetynet.alerts.repository;

import com.safetynet.alerts.model.Persons;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonsRepository extends CrudRepository<Persons, Integer> {
    Iterable<Persons> findAllByCity(String city);
}
