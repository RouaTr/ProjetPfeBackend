package com.projet.projetPFE.RestController;

import com.projet.projetPFE.Entities.MedicalTreatment;
import com.projet.projetPFE.Service.MedicalTreatmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/medicaltratment")
public class MedicalTreatmentRestController {

    @Autowired
    private MedicalTreatmentService medicalTreatmentService;

    // Ajouter une MedicalTreatment
    @PostMapping
    public MedicalTreatment addMedicalTreatment(@RequestParam Long patientId, @RequestBody MedicalTreatment medicalTreatment) {
        return medicalTreatmentService.addMedicalTreatment(patientId, medicalTreatment);
    }

    // Modifier une MedicalTreatment
    @PutMapping("/{id}")
    public ResponseEntity<MedicalTreatment> updateMedicalTreatment(@PathVariable("id") Long id,
                                                         @RequestParam Long patientId,
                                                         @RequestBody MedicalTreatment medicalTreatment) {
        MedicalTreatment updatedMedicalTreatment = medicalTreatmentService.updateMedicalTreatment(id, patientId, medicalTreatment);
        return ResponseEntity.ok(updatedMedicalTreatment);
    }

    // Afficher toutes les MedicalTreatment
    @GetMapping
    public List<MedicalTreatment> displayMedicalTreatment() {
        return medicalTreatmentService.displayMedicalTreatment();
    }
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<MedicalTreatment>> getMedicalTreatmentByPatientId(@PathVariable Long patientId) {
        List<MedicalTreatment> medicalTreatment = medicalTreatmentService.findMedicalTreatmentByPatientId(patientId);
        return ResponseEntity.ok(medicalTreatment);
    }
    @GetMapping("/{id}")
    public ResponseEntity<MedicalTreatment> displayMedicalTreatmentnById(@PathVariable("id") Long id) {
        return medicalTreatmentService.displayMedicalTreatmentById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build()); // Retourne une erreur 404 si MedicalTreatment n'existe pas
    }




}

