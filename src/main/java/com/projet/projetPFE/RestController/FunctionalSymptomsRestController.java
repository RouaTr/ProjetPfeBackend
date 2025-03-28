package com.projet.projetPFE.RestController;

import com.projet.projetPFE.Entities.FunctionalSymptoms;
import com.projet.projetPFE.Service.FunctionalSymptomsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/functionalsymptoms")
public class FunctionalSymptomsRestController {

    @Autowired
    private FunctionalSymptomsService functionalSymptomsService;

    // Ajouter un FunctionalSymptoms
    @PostMapping
    public FunctionalSymptoms addFunctionalSymptoms(@RequestParam Long patientId, @RequestBody FunctionalSymptoms functionalSymptoms) {
        return functionalSymptomsService.addFunctionalSymptoms(patientId, functionalSymptoms);
    }

    // Modifier un FunctionalSymptoms
    @PutMapping("/{id}")
    public ResponseEntity<FunctionalSymptoms> updateFunctionalSymptoms(@PathVariable("id") Long id,
                                                                       @RequestParam Long patientId,
                                                                       @RequestBody FunctionalSymptoms functionalSymptoms) {
        FunctionalSymptoms updatedFunctionalSymptoms = functionalSymptomsService.updateFunctionalSymptoms(id, patientId, functionalSymptoms);
        return ResponseEntity.ok(updatedFunctionalSymptoms);
    }
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<FunctionalSymptoms>> getFunctionalSymptomsByPatientId(@PathVariable Long patientId) {
        List<FunctionalSymptoms> functionalSymptoms = functionalSymptomsService.findFunctionalSymptomsByPatientId(patientId);
        return ResponseEntity.ok(functionalSymptoms);
    }


    // Afficher tous les FunctionalSymptoms
    @GetMapping
    public List<FunctionalSymptoms> displayFunctionalSymptoms() {
        return functionalSymptomsService.displayFunctionalSymptoms();
    }

    // Afficher un FunctionalSymptoms par ID
    @GetMapping("/{id}")
    public ResponseEntity<FunctionalSymptoms> displayFunctionalSymptomsById(@PathVariable("id") Long id) {
        return functionalSymptomsService.displayFunctionalSymptomsById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}

