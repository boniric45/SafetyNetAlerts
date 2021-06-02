package com.safetynet.alerts.repository;

import com.safetynet.alerts.model.MedicalRecords;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MedicalsRecordsRepository extends CrudRepository<MedicalRecords,Long> {


    Optional<MedicalRecords> getMedicalRecordsById(int id);

    void deleteById(int id);

}
