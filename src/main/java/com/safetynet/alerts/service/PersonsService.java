package com.safetynet.alerts.service;

import com.safetynet.alerts.model.FireStations;
import com.safetynet.alerts.model.MedicalRecords;
import com.safetynet.alerts.model.Persons;
import com.safetynet.alerts.repository.FireStationsRepository;
import com.safetynet.alerts.repository.MedicalsRecordsRepository;
import com.safetynet.alerts.repository.PersonsRepository;
import com.safetynet.alerts.utils.CalculateAgeUtil;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
@Service
public class PersonsService {

    private final List<String> personsList = new ArrayList<>();
    private final List<String> personListAdult = new ArrayList<>();
    private final List<String> personListChildren = new ArrayList<>();

    @Autowired
    private PersonsRepository personsRepository;

    @Autowired
    private MedicalsRecordsRepository medicalsRecordsRepository;

    @Autowired
    private FireStationsRepository fireStationsRepository;

    @Autowired
    MedicalsRecordsService medicalsRecordsService;

    private Persons Persons;
    private MedicalRecords medicalRecords;


    /**
     * Create - Add a new person
     *
     * @param persons An object persons
     * @return The person object saved
     */
    public Persons createPerson(Persons persons) {
        return personsRepository.save(persons);
    }

    /**
     * Read - Get all persons
     *
     * @return - An Iterable object of Person full filled
     */
    public Iterable<Persons> getPersonAll() {
        return personsRepository.findAll();
    }

    /**
     * Update - Update an existing person
     *
     * @param id - The id of the person to update
     * @return Optional<Persons>
     */
    public Optional<Persons> getPersonsById(int id) {
        return personsRepository.findById(id);
    }

    /**
     * Delete - Delete an person
     *
     * @param firstName lastName of the person to delete
     */
    public void deletePersonByFirstNameAndLastName(String firstName, String lastName) {
        personsRepository.deletePersonByFirstNameAndLastName(firstName,lastName);
    }

    public void savePerson(Persons persons) {
        personsRepository.save(persons);
    }

    public void listSave(List<Persons> list) {
        personsRepository.saveAll(list);
    }

    public List<String> communityEmail(final String city) {
        Iterable<Persons> personsIterable = personsRepository.findAllByCity(city);
        for (Persons persons : personsIterable) {
            personsList.add(persons.getEmail());
            if (!personsList.contains(persons.getEmail())) {
                personsList.add(persons.getEmail());
            }
        }
        return personsList;
    }

    public List<String> getChildAlert(String address) throws ParseException {
        Iterable<Persons> personsIterable = personsRepository.findAllByAddress(address);
        Iterable<MedicalRecords> medicalRecordsIterable = medicalsRecordsRepository.findAll();
        personListAdult.clear();
        personListChildren.clear();
        personsList.clear();
        long age;

        for (Persons ps : personsIterable) {
            String lastNamePs = ps.getLastName();
            String firstNamePs = ps.getFirstName();
            String addressPerson = ps.getAddress();

            for (MedicalRecords mr : medicalRecordsIterable) {
                String lastNameMr = mr.getLastName();
                String firstNameMr = mr.getFirstName();
                String birthDate = mr.getBirthdate();
                if (firstNameMr.equals(firstNamePs) && lastNameMr.equals(lastNamePs) && addressPerson.equals(address)) {
                    age = CalculateAgeUtil.getAge(birthDate);
                    if (age <= 18) {
                        personListChildren.add("LastName:" + lastNameMr + " FirstName:" + firstNameMr + " BirthDate:" + birthDate + " Age:" + age);
                    } else {
                        personListAdult.add(" LastName: " + lastNameMr + " FirstName: " + firstNameMr + " BirthDate: " + birthDate + " Age: " + age);
                    }
                }
            }
        }
        if (personListChildren.size() == 0) {
              personsList.clear();
         } else {
        personsList.add("Children List" + personListChildren);
        personsList.add("Adult List" + personListAdult);
        }
        return personsList;
    }

    public List<String> getFire(String address) throws ParseException {
        personsList.clear();
        Iterable<Persons> personsIterable = personsRepository.findAllByAddress(address);
        Iterable<MedicalRecords> medicalRecordsIterable = medicalsRecordsRepository.findAll();
        Iterable<FireStations> fireStationsIterable = fireStationsRepository.findAll();
        long age;
        for (Persons ps : personsIterable){
            String lastNamePs = ps.getLastName();
            String firstNamePs = ps.getFirstName();
            String addressPs = ps.getAddress();
            String phonePs = ps.getPhone();
            for (MedicalRecords mr : medicalRecordsIterable) {
                String lastNameMr = mr.getLastName();
                String firstNameMr = mr.getFirstName();
                String birthDate = mr.getBirthdate();
                String allergies = mr.getAllergies();
                String medications = mr.getMedications();
                if (firstNameMr.equals(firstNamePs) && lastNameMr.equals(lastNamePs) && addressPs.equals(address)) {
                    age = CalculateAgeUtil.getAge(birthDate);
                    for (FireStations fs : fireStationsIterable){
                        String stationNumber = fs.getStation();
                        String stationAddress = fs.getAddress();
                        if (addressPs.equals(stationAddress)) {
                            personsList.add(" Adress: " + addressPs + " Firestation: " + stationNumber + " lastName: " + lastNamePs + " phone: " + phonePs + " age: " + age + " years" + " allergies: " + allergies + " medications: " + medications);
                        }
                    }
                }
            }

        }
        return personsList;
    }

    public List<String> getPersonInfo(String firstName, String lastName) throws ParseException {
        personsList.clear();
        Iterable<Persons> personsIterable = personsRepository.findAll();
        Iterable<MedicalRecords> medicalRecordsIterable = medicalsRecordsRepository.findAll();
        String firstNamePs;
        String lastNamePs;
        String mailPs;
        String addressPs;
        String medication = null;
        String allergies = null;
        String birthDate;
        long age = 0;

        for (Persons ps : personsIterable){
            firstNamePs = ps.getFirstName();
            lastNamePs = ps.getLastName();
            mailPs = ps.getEmail();
            addressPs = ps.getAddress();

            if (firstName.equals(firstNamePs) && lastName.equals(lastNamePs)){

                for (MedicalRecords mr : medicalRecordsIterable){
                medication = mr.getMedications();
                allergies = mr.getAllergies();
                birthDate = mr.getBirthdate();
                age = CalculateAgeUtil.getAge(birthDate);
                }
                personsList.add("firstName: "+firstNamePs+" lastName: "+lastNamePs+" address: "+addressPs+" age: "+age+" mail: "+mailPs+" allergies: "+allergies+" medications: "+medication);
          //TODO en attente de Mandfred
            }
        }
        return personsList;
    }


}
