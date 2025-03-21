package com.projet.projetPFE.RestController;

import com.projet.projetPFE.Entities.MedicalHistory;
import com.projet.projetPFE.Entities.Patient;
import com.projet.projetPFE.Service.MedicalHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin("*")
@RestController
@RequestMapping("/medicalHistories")
public class MedicalHistoryRestControler {

    @Autowired
    private MedicalHistoryService medicalHistoryService;

    // Ajouter un antécédent médical
    @PostMapping
    public MedicalHistory addMedicalHistory(@RequestParam Long patientId, @RequestBody MedicalHistory medicalHistory) {
        return medicalHistoryService.addMedicalHistory(patientId, medicalHistory);
    }

    // Mettre à jour un antécédent médical
    @PutMapping("/{id}")
    public ResponseEntity<MedicalHistory> updateMedicalHistory(
            @PathVariable Long id,
            @RequestParam Long patientId,
            @RequestBody MedicalHistory medicalHistory) {

        MedicalHistory updatedMedicalHistory = medicalHistoryService.updateMedicalHistory(id, patientId, medicalHistory);
        return ResponseEntity.ok(updatedMedicalHistory);
    }

    // Afficher tous les antécédents médicaux
    @GetMapping
    public List<MedicalHistory> displayMedicalHistories() {
        return medicalHistoryService.displayMedicalHistory();
    }

    // Afficher un antécédent médical par ID
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Optional<MedicalHistory> displayMedicalHistoryById(@PathVariable("id") Long id) {
        return medicalHistoryService.displayMedicalHistoryById(id);
    }
}

