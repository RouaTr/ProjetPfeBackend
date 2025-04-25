package com.projet.projetPFE.RestController;

import com.projet.projetPFE.Entities.TreatmentOption;
import com.projet.projetPFE.Service.TreatmentOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/treatment-options")
@CrossOrigin("*")
public class TreatmentOptionController {
    @Autowired
    private TreatmentOptionService treatmentOptionService;

    // Récupérer toutes les options de traitement
    @GetMapping
    public List<TreatmentOption> getAllTreatmentOptions() {
        return treatmentOptionService.getAllTreatmentOptions();
    }

    // Ajouter une nouvelle option de traitement
    @PostMapping
    public TreatmentOption addTreatmentOption(@RequestBody String treatmentName) {
        return treatmentOptionService.addTreatmentOption(treatmentName);
    }

    // Mettre à jour une option de traitement existante
    @PutMapping("/{id}")
    public TreatmentOption updateTreatmentOption(@PathVariable Long id, @RequestBody String treatmentName) {
        return treatmentOptionService.updateTreatmentOption(id, treatmentName);
    }

    // Supprimer une option de traitement
    @DeleteMapping("/{id}")
    public void deleteTreatmentOption(@PathVariable Long id) {
        treatmentOptionService.deleteTreatmentOption(id);
    }
}
