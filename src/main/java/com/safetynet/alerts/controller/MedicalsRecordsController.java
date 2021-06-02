package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.MedicalRecords;
import com.safetynet.alerts.model.Persons;
import com.safetynet.alerts.service.MedicalsRecordsService;
import com.safetynet.alerts.utils.CalculateAgeUtil;
import com.safetynet.alerts.utils.ReadJsonMedicalRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Optional;

@RestController
public class MedicalsRecordsController {

    @Autowired
    private MedicalsRecordsService medicalsRecordsService;

    //ReaderJsonMedicalRecord and Save H2 Database
    public void chargedMedicalRecord(String jsonDatafile) {
        ArrayList<MedicalRecords> list = ReadJsonMedicalRecord.readJsonFileMedicalRecord(jsonDatafile);
        medicalsRecordsService.listSaveMedicalrecords(list);
    }


    /**
     * Create - Add a new MedicalRecord
     *
     * @param medicalRecords An object persons
     * @return The medicalRecord object saved
     */
    @PostMapping("/medicalRecord")
    public MedicalRecords createMedicalRecord(@RequestBody MedicalRecords medicalRecords) {
        return medicalsRecordsService.createMedicalRecord(medicalRecords);
    }

    /**
     * Read - Get one medicalRecord
     *
     * @param id The id of the medicalRecord
     * @return An MedicalRecord object full filled
     */
    @GetMapping("/medicalRecord/{id}")
    public MedicalRecords getMedicalRecordsById(@PathVariable("id") final int id) {
        Optional<MedicalRecords> medicalRecords = medicalsRecordsService.getMedicalRecordsById(id);
        if (medicalRecords.isPresent()) {
            return medicalRecords.get();
        } else {
            return null;
        }
    }

    /**
     * Update - Update an existing medicalRecord
     *
     * @param id      - The id of the medicalRecord to update
     * @param medicalRecords - The medicalRecord object updated
     * @return
     */
    @PutMapping("/medicalRecord/{id}")
    public MedicalRecords updateMedicalRecords(@PathVariable("id") final int id, @RequestBody MedicalRecords medicalRecords) {
        Optional<MedicalRecords> mr = medicalsRecordsService.getMedicalRecordsById(id);
        if (mr.isPresent()) {
            return mr.get();
        } else {
            return null;
        }
    }

    /**
     * Delete - Delete an medicalRecord
     *
     * @param id - The id of the medicalRecord to delete
     */
    @GetMapping("/medicalRecord/delete/{id}") // ok
    public ModelAndView deleteMedicalRecord(@PathVariable("id") final int id) {
        Optional<MedicalRecords> mr = medicalsRecordsService.getMedicalRecordsById(id);
        if (mr.isPresent()) {
            medicalsRecordsService.deleteMedicalRecord(id);
            return new ModelAndView("redirect:/medicalRecord");
        } else {
            return null;
        }

        //TODO écrire le log
    }


//    http://localhost:8080/medicalRecord
//    Cet endpoint permettra d’effectuer les actions suivantes via Post/Put/Delete HTTP :
//            ● ajouter un dossier médical ;
//● mettre à jour un dossier médical existant (comme évoqué précédemment, supposer que le
//            prénom et le nom de famille ne changent pas) ;
//● supprimer un dossier médical (utilisez une combinaison de prénom et de nom comme
//            identificateur unique).

}
