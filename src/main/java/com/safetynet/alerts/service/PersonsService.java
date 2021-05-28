package com.safetynet.alerts.service;

import com.safetynet.alerts.model.Persons;
import com.safetynet.alerts.repository.PersonsRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
@Service
public class PersonsService {

    private final List<String> personsList = new ArrayList<>();

    @Autowired
    private PersonsRepository personsRepository;
    private Persons Persons;

    /**
     * Create - Add a new person
     * @param persons An object persons
     * @return The person object saved
     */
    public Persons createPerson(Persons persons) {
        Persons savePerson = personsRepository.save(persons);
        return savePerson;
    }

    /**
     * Read - Get all persons
     * @return - An Iterable object of Person full filled
     */
    public Iterable<Persons> getPersonAll() {
        return personsRepository.findAll();
    }

    /**
     * Update - Update an existing person
     * @param id - The id of the person to update
     * @return
     */
    public Optional<Persons> getPersonsById(int id) {
        return personsRepository.findById(id);
    }

    /**
     * Delete - Delete an person
     * @param id - The id of the person to delete
     */
    public void deletePerson(final int id) {
        personsRepository.deleteById(id);
    }

    public Persons savePerson(Persons persons) {
        return personsRepository.save(persons);
    }

    public Iterable<Persons> listSave(List<Persons> list) {
        personsRepository.saveAll(list);
        return list;
    }

    public List<String> communityEmail(final String city) {
        Iterable<Persons> personsIterable = personsRepository.findAllByCity(city);
        for (Persons persons : personsIterable) {
            personsList.add(persons.getEmail());
            if (!personsList.contains(persons.getEmail())){
                personsList.add(persons.getEmail());
            }
        }
        return personsList;
    }
}
