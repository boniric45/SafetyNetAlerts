package com.safetynet.alerts.repository;

import com.safetynet.alerts.model.Persons;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonsRepository extends CrudRepository<Persons, Integer> {
    Iterable<Persons> findAllByCity(String city);
    List<Persons> findAllByAddress(String address);
    Iterable<Persons> findAll();


//    Iterable<Persons> findPersonFirstnameLastname(@Param("firstName") String firstName, @Param("lastName") String lastName);
//    List<Persons> findPersonFirstnameLastname(String firstName);
//Iterable<Persons> findPersonFirstnameLastname(String firstName);

}
