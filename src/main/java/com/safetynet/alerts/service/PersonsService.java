package com.safetynet.alerts.service;

import com.safetynet.alerts.model.Persons;
import com.safetynet.alerts.repository.PersonsRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@Data
@Service
public class PersonsService {

    @Autowired
    private PersonsRepository personsRepository;
    private final List<Persons> personsList = new ArrayList<>();

    public Iterable<Persons> listSave (List<Persons> list){
        personsRepository.saveAll(list);
        return list;
    }

}
