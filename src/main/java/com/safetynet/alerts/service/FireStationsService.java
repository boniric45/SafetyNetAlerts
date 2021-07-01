package com.safetynet.alerts.service;

import com.safetynet.alerts.model.FireStations;
import com.safetynet.alerts.model.MedicalRecords;
import com.safetynet.alerts.model.Persons;
import com.safetynet.alerts.repository.FireStationsRepository;
import com.safetynet.alerts.repository.MedicalsRecordsRepository;
import com.safetynet.alerts.repository.PersonsRepository;
import com.safetynet.alerts.utils.CalculateAgeUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@Service
/**
 * Service Firestation
 */
public class FireStationsService {

    private final List<String> fireStationsList = new ArrayList<>();
    private final List<String> fireStationListAdult = new ArrayList<>();
    private final List<String> fireStationListChildren = new ArrayList<>();
    private FireStations fireStations;
    private Persons persons;
    private MedicalRecords medicalRecords;

    @Autowired
    private FireStationsRepository fireStationsRepository;
    @Autowired
    private PersonsRepository personsRepository;
    @Autowired
    private MedicalsRecordsRepository medicalsRecordsRepository;

    /**
     * Create - Add a new firestation
     *
     * @param fireStations An object firestation
     * @return The firestation object saved
     */
    public FireStations createNewFirestation(FireStations fireStations) {
        return fireStationsRepository.save(fireStations);
    }

    /**
     * Read - Get all firestation
     *
     * @return - An Iterable object of ListFirestationByStationNumber full filled
     */
    public Iterable<FireStations> getFirestationAll() {
        return fireStationsRepository.findAll();
    }

    /**
     * Read  an existing firestation
     *
     * @param id - The id of the firestation to update
     * @return
     */
    public Optional<FireStations> getFirestationsById(int id) {
        return fireStationsRepository.findById(id);
    }

    /**
     * Delete - Delete an firestation
     *
     * @param address - The station address of the firestation to delete
     */
    public void deleteFireStationStationByAddress(String station, String address) {

        fireStationsRepository.deleteFirestationByStationAndAddress(station, address);

    }

    // Save list all firestation
    public void saveListAllFirestation(List<FireStations> list) {
        fireStationsRepository.saveAll(list);
    }

    // return list firestation from station number
    public List<String> getFirestationsFromStationNumber(String station) {
        fireStationsList.clear();
        Iterable<FireStations> fireStations = fireStationsRepository.findAllByStation(station);
        Iterable<Persons> personsIterable = personsRepository.findAll();
        Iterable<MedicalRecords> medicalRecordsIterable = medicalsRecordsRepository.findAll();

        int countAdult = 0;
        int countChildren = 0;

        for (FireStations fs : fireStations) {
            String addressFirestation = fs.getAddress();

            for (Persons ps : personsIterable) {
                String addressPerson = ps.getAddress();
                String phone = ps.getPhone();
                String firstNamePs = ps.getFirstName();
                String lastNamePs = ps.getLastName();

                if (addressFirestation.equals(addressPerson)) {

                    for (MedicalRecords mr : medicalRecordsIterable) {
                        String firstNameMr = mr.getFirstName();
                        String lastNameMr = mr.getLastName();

                        if (firstNamePs.equals(firstNameMr) && lastNamePs.equals(lastNameMr)) {
                            String birthDate = mr.getBirthdate();

                            long age = CalculateAgeUtil.getAge(birthDate);

                            if (age <= 18) {
                                fireStationListChildren.add(" LastName : " + lastNameMr + " FirstName : " + firstNameMr + " Address : " + addressPerson + " Phone : " + phone);
                                countChildren++;
                            } else {
                                fireStationListAdult.add(" LastName : " + lastNameMr + " FirstName : " + firstNameMr + " Address : " + addressPerson + " Phone : " + phone);
                                countAdult++;
                            }
                        }
                    }
                }
            }
        }
        fireStationsList.add("Children List :" + fireStationListChildren + "  Number of children : " + countChildren + " Adult List : " + fireStationListAdult + " Number of adult : " + countAdult);
        return fireStationsList;
    }

    // return list phone number
    public List<String> getListPhoneNumber(String firestation) {
        Iterable<FireStations> fireStations = fireStationsRepository.findAllByStation(firestation);
        Iterable<Persons> personsIterable = personsRepository.findAll();
        fireStationsList.clear();

        for (FireStations fs : fireStations) {
            String addressFirestation = fs.getAddress();

            for (Persons ps : personsIterable) {
                String addressPerson = ps.getAddress();
                String phone = ps.getPhone();
                if (addressFirestation.equals(addressPerson)) {
                    fireStationsList.add(phone);
                }
            }
        }
        return fireStationsList;
    }

    // return list all homes by the firestation
    public List<String> getListAllHomesByTheFirestation(String stations) {

        fireStationsList.clear();

        Iterable<FireStations> fireStationsIterable = fireStationsRepository.findAllByStation(stations);
        Iterable<Persons> personsIterable = personsRepository.findAll();
        Iterable<MedicalRecords> medicalRecordsIterable = medicalsRecordsRepository.findAll();

        for (FireStations fs : fireStationsIterable) {
            String addressFirestation = fs.getAddress();
            String firestationNumber = fs.getStation();

            for (Persons ps : personsIterable) {
                String addressPerson = ps.getAddress();
                String phone = ps.getPhone();
                String firstNamePs = ps.getFirstName();
                String lastNamePs = ps.getLastName();

                if (addressFirestation.equals(addressPerson)) {

                    for (MedicalRecords mr : medicalRecordsIterable) {
                        String firstNameMr = mr.getFirstName();
                        String lastNameMr = mr.getLastName();
                        String allergies = mr.getAllergies();
                        String medications = mr.getMedications();

                        if (firstNamePs.equals(firstNameMr) && lastNamePs.equals(lastNameMr)) {
                            String birthDate = mr.getBirthdate();
                            long age = CalculateAgeUtil.getAge(birthDate);
                            fireStationsList.add("firestation: " + firestationNumber + " address: " + addressPerson + " firstname: " + firstNamePs + " lastname: " + lastNamePs + " allergies: " + allergies + " medications: " + medications + " phone: " + phone + " age: " + age);
                        }
                    }
                }
            }
        }
        return fireStationsList;
    }

    public void saveFirestation(FireStations fs) {
        fireStationsRepository.save(fs);
    }

}
