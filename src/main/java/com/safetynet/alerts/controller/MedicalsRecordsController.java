package com.safetynet.alerts.controller;
import com.safetynet.alerts.model.MedicalRecords;
import com.safetynet.alerts.service.MedicalsRecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MedicalsRecordsController {

    @Autowired
    private MedicalsRecordsService medicalsRecordsService;

    @GetMapping("/medicalsRecords_all")
    public Iterable<MedicalRecords> getMedicalsRecords() {
        return medicalsRecordsService.getMedicalsRecords();
    }
}
