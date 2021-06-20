package com.safetynet.alerts.service;

import com.safetynet.alerts.model.MedicalRecords;
import com.safetynet.alerts.repository.MedicalsRecordsRepository;
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
public class MedicalsRecordsService {

    private final List<MedicalRecords> medicalRecordsList = new ArrayList<>();
    @Autowired
    private MedicalsRecordsRepository medicalsRecordsRepository;

    //EndPoint

    /**
     * Create - Add a new medicalRecord
     *
     * @param medicalRecords An object medicalRecord
     * @return The medicalRecord object saved
     */
    public MedicalRecords createMedicalRecord(MedicalRecords medicalRecords) {
        return medicalsRecordsRepository.save(medicalRecords);
    }

    /**
     * Read - Get all medicalRecord
     *
     * @return An MedicalRecord object full filled
     */
    public Iterable<MedicalRecords> getMedicalRecordsAll() {
        return medicalsRecordsRepository.findAll();
    }

    /**
     * Read - Get one medicalRecord
     *
     * @param id The id of the medicalRecord
     * @return An MedicalRecord object full filled
     */
    public Optional<MedicalRecords> getMedicalRecordsById(int id) {
        return medicalsRecordsRepository.getMedicalRecordsById(id);
    }

    /**
     * Update - Update an existing medicalRecord
     *
     * @param id - The id of the medicalRecord to update
     * @return The medicalRecord object saved
     */
    public Optional<MedicalRecords> getUpdateMedicalRecords(int id) {
        return medicalsRecordsRepository.getMedicalRecordsById(id);
    }

    /**
     * Delete - Delete an medicalRecord
     * <p>
     * The FirstName and LastName of the medicalRecord to delete
     */
    public void deleteMedicalRecordByFirstNameAndLastName(String firstName, String lastName) {
        medicalsRecordsRepository.deleteMedicalRecordByFirstNameAndLastName(firstName, lastName);
    }


    public Iterable<MedicalRecords> listSaveMedicalrecords(List<MedicalRecords> list) {
        medicalsRecordsRepository.saveAll(list);
        return list;
    }

    public void saveMedicalRecord(MedicalRecords currentMedical) {
        medicalsRecordsRepository.save(currentMedical);
    }

}
