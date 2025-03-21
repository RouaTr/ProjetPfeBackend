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
@RequestMapping("/functional-symptoms")
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

    // Afficher tous les FunctionalSymptoms
    @GetMapping
    public List<FunctionalSymptoms> displayFunctionalSymptoms() {
        return functionalSymptomsService.displayFunctionalSymptoms();
    }

    // Afficher un FunctionalSymptoms par ID
    @GetMapping("/{id}")
    public ResponseEntity<Optional<FunctionalSymptoms>> displayFunctionalSymptomsById(@PathVariable("id") Long id) {
        Optional<FunctionalSymptoms> functionalSymptoms = functionalSymptomsService.displayFunctionalSymptomsById(id);
        return ResponseEntity.ok(functionalSymptoms);
    }
}

