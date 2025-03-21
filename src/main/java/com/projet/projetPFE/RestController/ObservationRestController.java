package com.projet.projetPFE.RestController;

import com.projet.projetPFE.Entities.Observation;
import com.projet.projetPFE.Service.ObservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin("*")
@RestController
@RequestMapping("/observations")
public class ObservationRestController {

    @Autowired
    private ObservationService observationService;

    // Ajouter une observation
    @PostMapping
    public Observation addObservation(@RequestParam Long patientId, @RequestBody Observation observation) {
        return observationService.addObservation(patientId, observation);
    }

    // Modifier une observation
    @PutMapping("/{id}")
    public ResponseEntity<Observation> updateObservation(@PathVariable("id") Long id,
                                                         @RequestParam Long patientId,
                                                         @RequestBody Observation observation) {
        Observation updatedObservation = observationService.updateObservation(id, patientId, observation);
        return ResponseEntity.ok(updatedObservation);
    }

    // Afficher toutes les observations
    @GetMapping
    public List<Observation> displayObservations() {
        return observationService.displayObservation();
    }

    // Afficher une observation par ID
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Observation>> displayObservationById(@PathVariable("id") Long id) {
        Optional<Observation> observation = observationService.displayObservation(id);
        return ResponseEntity.ok(observation);
    }
}
