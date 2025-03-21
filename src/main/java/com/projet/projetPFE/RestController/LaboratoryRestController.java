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
@RequestMapping("/laboratories")
public class LaboratoryRestController {

    @Autowired
    private LaboratoryService laboratoryService;

    // Ajouter un laboratoire
    @PostMapping
    public Laboratory addLaboratory(@RequestParam Long patientId, @RequestBody Laboratory laboratory) {
        return laboratoryService.addLaboratory(patientId, laboratory);
    }

    // Mettre à jour un laboratoire
    @PutMapping("/{id}")
    public ResponseEntity<Laboratory> updateLaboratory(
            @PathVariable Long id,
            @RequestParam Long patientId,
            @RequestBody Laboratory laboratory) {

        Laboratory updatedLaboratory = laboratoryService.updateLaboratory(id, patientId, laboratory);
        return ResponseEntity.ok(updatedLaboratory);
    }

    // Afficher tous les laboratoires
    @GetMapping
    public List<Laboratory> displayLaboratories() {
        return laboratoryService.displayLaboratory();
    }

    // Afficher un laboratoire par ID
    @GetMapping("/{id}")
    public Optional<Laboratory> displayLaboratoryById(@PathVariable("id") Long id) {
        return laboratoryService.displayLaboratoryById(id);
    }
}
