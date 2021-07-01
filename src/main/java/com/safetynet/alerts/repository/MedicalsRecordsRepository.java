package com.safetynet.alerts.repository;

import com.safetynet.alerts.model.MedicalRecords;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository MedicalRecord
 */
@Repository
public interface MedicalsRecordsRepository extends CrudRepository<MedicalRecords, Long> {

    Optional<MedicalRecords> getMedicalRecordsById(int id);

    void deleteMedicalRecordByFirstNameAndLastName(String firstName, String lastName);

}
