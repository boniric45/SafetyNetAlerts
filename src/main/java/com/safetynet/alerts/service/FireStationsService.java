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

@Data
@Service
public class FireStationsService {

    @Autowired
    private FireStationsRepository fireStationsRepository;

    @Autowired
    private PersonsRepository personsRepository;

    @Autowired
    private MedicalsRecordsRepository medicalsRecordsRepository;

    private FireStations fireStations;
    private Persons persons;
    private MedicalRecords medicalRecords;

    private final List<String> fireStationsList = new ArrayList<>();
    private final List<String> fireStationListAdult = new ArrayList<>();
    private final List<String> fireStationListChildren = new ArrayList<>();
    public void listSaveFirestation (List<FireStations> list){
        fireStationsRepository.saveAll(list);
    }

    public List<String> getFirestationsFromStationNumber(String station) throws ParseException {

    Iterable<FireStations> fireStations = fireStationsRepository.findAllByStation(station);
    Iterable<Persons> personsIterable = personsRepository.findAll();
    Iterable<MedicalRecords> medicalRecordsIterable = medicalsRecordsRepository.findAll();
    int countAdult=0;
    int countChildren=0;
    for (FireStations fs : fireStations){
        String addressFirestation = fs.getAddress();

        for (Persons ps : personsIterable){
            String addressPerson = ps.getAddress();
            String phone = ps.getPhone();
            if (addressFirestation.equals(addressPerson)){

            for (MedicalRecords mr: medicalRecordsIterable){
                String firstNamePs = ps.getFirstName();
                String lastNamePs = ps.getLastName();
                String firstNameMr = mr.getFirstName();
                String lastNameMr = mr.getLastName();

                if (firstNamePs.equals(firstNameMr) && lastNamePs.equals(lastNameMr)){
                    String birthDate = mr.getBirthdate();
                    long age = CalculateAgeUtil.getAge(birthDate);

                    if (age<=18){
                        fireStationListChildren.add(" LastName : "+lastNameMr+" FirstName : "+firstNameMr+" Address : "+addressPerson+" Phone : "+phone+" Age : "+age+" Years");
                        countChildren++;
                    } else {
                        fireStationListAdult.add(" LastName : "+lastNameMr+" FirstName : "+firstNameMr+" Address : "+addressPerson+" Phone : "+phone+" Age : "+age+" Years");
                        countAdult++;
                    }
                }
             }
           }
        }
    }
          fireStationsList.add("Minor List :"+fireStationListChildren+"  Number of children : "+countChildren+" Adult List : "+fireStationListAdult+" Number of adult : "+countAdult);
        return fireStationsList;
    }
}
