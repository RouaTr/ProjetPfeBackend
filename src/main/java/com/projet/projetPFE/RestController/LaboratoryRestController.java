package com.projet.projetPFE.RestController;

import com.projet.projetPFE.Entities.Laboratory;

import com.projet.projetPFE.Service.LaboratoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/laboratory")
public class LaboratoryRestController {

    @Autowired
    private LaboratoryService laboratoryService;

    // Ajouter un laboratoire
    @PostMapping
    public Laboratory addLaboratory(@RequestParam Long patientId, @RequestBody Laboratory laboratory) {
        return laboratoryService.addLaboratory(patientId, laboratory);
    }

    // Modifier laboratoire
    @PutMapping("/{id}")
    public ResponseEntity<Laboratory> updateLaboratory(@PathVariable("id") Long id,
                                                         @RequestParam Long patientId,
                                                         @RequestBody Laboratory laboratory) {
        Laboratory updatedLaboratory = laboratoryService.updateLaboratory(id, patientId, laboratory);
        return ResponseEntity.ok(updatedLaboratory);
    }


    @GetMapping
    public List<Laboratory> displayLaboratory() {
        return laboratoryService.displayLaboratory();
    }
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<Laboratory>> getLaboratoryByPatientId(@PathVariable Long patientId) {
        List<Laboratory> laboratory = laboratoryService.findLaboratoryByPatientId(patientId);
        return ResponseEntity.ok(laboratory);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Laboratory> displayLaboratoryById(@PathVariable("id") Long id) {
        return laboratoryService.displayLaboratoryById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build()); // Retourne une erreur 404 si le bilan n'existe pas
    }

}
