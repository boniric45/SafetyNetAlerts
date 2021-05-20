package com.safetynet.alerts.service;

import com.safetynet.alerts.model.MedicalRecords;
import com.safetynet.alerts.repository.MedicalsRecordsRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
public class MedicalsRecordsService {

    @Autowired
    private MedicalsRecordsRepository medicalsRecordsRepository;

    public Optional<MedicalRecords> getMedicalsRecord(final Long id) {
        return medicalsRecordsRepository.findById(id);
    }

    public Iterable<MedicalRecords> getMedicalsRecords() {
        return medicalsRecordsRepository.findAll();
    }

    public void deleteMedicalsRecords(final Long id) {
        medicalsRecordsRepository.deleteById(id);
    }

    public MedicalRecords saveMedicalsRecords(MedicalRecords medicalRecords) {
        MedicalRecords savedMedicalsRecords = medicalsRecordsRepository.save(medicalRecords);
        return savedMedicalsRecords;
    }
}
