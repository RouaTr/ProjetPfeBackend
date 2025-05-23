package com.projet.projetPFE.RestController;

import com.projet.projetPFE.Entities.ClinicalSymptoms;
import com.projet.projetPFE.Service.ClinicalSymptomsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/clinicalsymptoms")
public class ClinicalSymptomsRestController {

    @Autowired
    private ClinicalSymptomsService clinicalSymptomsService;

    // Ajouter un ClinicalSymptoms
    @PostMapping
    public ResponseEntity<ClinicalSymptoms> addClinicalSymptoms(@RequestParam Long patientId, @RequestBody ClinicalSymptoms clinicalSymptoms) {
        ClinicalSymptoms savedClinicalSymptoms = clinicalSymptomsService.addClinicalSymptoms(patientId, clinicalSymptoms);
        return ResponseEntity.ok(savedClinicalSymptoms);
    }

    // Modifier un ClinicalSymptoms
    @PutMapping("/{id}")
    public ResponseEntity<ClinicalSymptoms> updateClinicalSymptoms(@PathVariable("id") Long id,
                                                                   @RequestParam Long patientId,
                                                                   @RequestBody ClinicalSymptoms clinicalSymptoms) {
        ClinicalSymptoms updatedClinicalSymptoms = clinicalSymptomsService.updateClinicalSymptoms(id, patientId, clinicalSymptoms);
        return ResponseEntity.ok(updatedClinicalSymptoms);
    }

    // Afficher tous les ClinicalSymptoms
    @GetMapping
    public List<ClinicalSymptoms> displayClinicalSymptoms() {
        return clinicalSymptomsService.displayClinicalSymptoms();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClinicalSymptoms> displayClinicalSymptomsById(@PathVariable("id") Long id) {
        return clinicalSymptomsService.displayClinicalSymptomsById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<ClinicalSymptoms>> getClinicalSymptomsByPatientId(@PathVariable Long patientId) {
        List<ClinicalSymptoms> clinicalSymptoms = clinicalSymptomsService.findClinicalSymptomsByPatientId(patientId);
        return ResponseEntity.ok(clinicalSymptoms);
    }
}

