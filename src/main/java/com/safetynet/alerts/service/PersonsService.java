package com.safetynet.alerts.service;

import com.safetynet.alerts.model.Persons;
import com.safetynet.alerts.repository.PersonsRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
public class PersonsService {

    @Autowired
    private PersonsRepository personsRepository;

    public Optional<Persons> getPerson(final Long id){
        return personsRepository.findById(id);
    }

    public Iterable<Persons> getPersons(){
        return personsRepository.findAll();
    }

    public void deletePersons(final Long id){
        personsRepository.deleteById(id);
    }

    public Persons savePersons(Persons persons){
        Persons savedPersons = personsRepository.save(persons);
        return savedPersons;
    }
}
