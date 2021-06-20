package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.MedicalRecords;
import com.safetynet.alerts.service.MedicalsRecordsService;
import com.safetynet.alerts.utils.ReadJsonMedicalRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
public class MedicalsRecordsController {

    Logger logger = LogManager.getLogger(MedicalsRecordsController.class);
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
        if (medicalRecords == null) {
            logger.error(" ERROR CREATE /medicalRecord ");
            return null;
        } else {
            logger.info("SUCCESS CREATE /medicalRecord ");
        }
        return medicalsRecordsService.createMedicalRecord(medicalRecords);
    }

    /**
     * Read - Get all medicalRecord
     *
     * @return An MedicalRecord object full filled
     */
    @GetMapping("/medicalRecord")
    public Iterable<MedicalRecords> getMedicalRecordsAll() {
        logger.info(" SUCCESS READ All /medicalRecord ");
        return medicalsRecordsService.getMedicalRecordsAll();
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

        if (!medicalRecords.isPresent()) {
            logger.error(" ERROR READ /medicalRecord ");
            return null;
        } else {
            logger.info(" SUCCESS READ /medicalRecord");
        }
        return medicalRecords.orElse(null);
    }

    /**
     * Update - Update an existing medicalRecord
     *
     * @param id             - The id of the medicalRecord to update
     * @param medicalRecords - The medicalRecord object updated
     * @return
     */
    @PutMapping("/medicalRecord/{id}")
    public MedicalRecords updateMedicalRecord(@PathVariable("id") final int id, @RequestBody MedicalRecords medicalRecords) {
        Optional<MedicalRecords> mr = medicalsRecordsService.getMedicalRecordsById(id);
        if (mr.isPresent()) {
            MedicalRecords currentMedical = mr.get();

            String birthdate = medicalRecords.getBirthdate();
            if (birthdate != null) {
                currentMedical.setBirthdate(birthdate);
            }

            String medications = medicalRecords.getMedications();
            if (medications != null) {
                currentMedical.setMedications(medications);
            }

            String allergies = medicalRecords.getAllergies();
            if (allergies != null) {
                currentMedical.setAllergies(allergies);
            }
            medicalsRecordsService.saveMedicalRecord(currentMedical);
            logger.info(" SUCCESS UPDATE /medicalRecord ");
            return currentMedical;

        } else {
            logger.error(" ERROR UPDATE /medicalRecord ");
            return null;
        }
    }

    /**
     * Delete - Delete an medicalRecord
     *
     * @param - The FirstName and LastName of the medicalRecord to delete
     */
    @Transactional
    @DeleteMapping("/medicalRecord/{firstName}/{lastName}")
    public void deleteMedicalRecordByFirstNameAndLastName(@PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName) {

        if (firstName == null || lastName == null) {
            logger.error("ERROR DELETE /medicalRecord ");

        } else {
            logger.info("SUCCESS DELETE /medicalRecord ");
        }

        medicalsRecordsService.deleteMedicalRecordByFirstNameAndLastName(firstName, lastName);
    }

}




