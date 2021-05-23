package com.safetynet.alerts.controller;
import com.safetynet.alerts.model.MedicalRecords;
import com.safetynet.alerts.service.MedicalsRecordsService;
import com.safetynet.alerts.utils.ReadJsonMedicalRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class MedicalsRecordsController {

    @Autowired
    private MedicalsRecordsService medicalsRecordsService;

//    @GetMapping("/listMedicalsRecords")
        public ArrayList<MedicalRecords> chargedMedicalRecord(){
        String dataFile = "D:/Openclassrooms/Projet5/SafetyNet/src/main/resources/json/data.json";
        ArrayList<MedicalRecords> list = ReadJsonMedicalRecord.readJsonFileMedicalRecord(dataFile);
        medicalsRecordsService.listSaveMedicalrecords(list);
        return list;
    }
}
