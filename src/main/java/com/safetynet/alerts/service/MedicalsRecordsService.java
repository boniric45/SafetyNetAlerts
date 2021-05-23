package com.safetynet.alerts.service;

import com.safetynet.alerts.model.MedicalRecords;
import com.safetynet.alerts.repository.MedicalsRecordsRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Data
@Service
public class MedicalsRecordsService {

    private final List<MedicalRecords> medicalRecordsList = new ArrayList<>();
    @Autowired
    private MedicalsRecordsRepository medicalsRecordsRepository;

    public Iterable<MedicalRecords> listSaveMedicalrecords(List<MedicalRecords> list) {
        medicalsRecordsRepository.saveAll(list);
        return list;
    }

}
