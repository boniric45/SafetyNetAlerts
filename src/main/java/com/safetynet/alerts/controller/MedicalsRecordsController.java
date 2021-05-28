package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.MedicalRecords;
import com.safetynet.alerts.service.MedicalsRecordsService;
import com.safetynet.alerts.utils.CalculateAgeUtil;
import com.safetynet.alerts.utils.ReadJsonMedicalRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.ArrayList;

@RestController
public class MedicalsRecordsController {

    @Autowired
    private MedicalsRecordsService medicalsRecordsService;

    public void chargedMedicalRecord(String jsonDatafile) {
        ArrayList<MedicalRecords> list = ReadJsonMedicalRecord.readJsonFileMedicalRecord(jsonDatafile);
        medicalsRecordsService.listSaveMedicalrecords(list);
    }

    /**
     * Read - Get all persons
     *
     * @return - An Iterable object of Person full filled
     */
    @GetMapping("/medicalrecords/all")
    public Iterable<MedicalRecords> getMedicalsRecordsAll() throws ParseException {
        return medicalsRecordsService.getMedicalsRecordsAll();
    }


//    http://localhost:8080/medicalRecord
//    Cet endpoint permettra d’effectuer les actions suivantes via Post/Put/Delete HTTP :
//            ● ajouter un dossier médical ;
//● mettre à jour un dossier médical existant (comme évoqué précédemment, supposer que le
//            prénom et le nom de famille ne changent pas) ;
//● supprimer un dossier médical (utilisez une combinaison de prénom et de nom comme
//            identificateur unique).

}
