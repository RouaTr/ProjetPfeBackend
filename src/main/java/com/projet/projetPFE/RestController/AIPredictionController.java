package com.projet.projetPFE.RestController;


import com.projet.projetPFE.Repository.PatientRepository;

import com.projet.projetPFE.Service.TreatmentPredictionService;
import com.projet.projetPFE.config.TreatmentPredictionDTO;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/ai")
public class AIPredictionController {


    @Autowired
    private TreatmentPredictionService treatmentPredictionService;

    @Autowired
    private PatientRepository patientRepository;

    // Endpoint pour entraîner le modèle Weka
    @GetMapping("/train")
    public String trainModel() {
        try {
            // Appeler le service de prédiction pour entraîner le modèle
            treatmentPredictionService.trainModel();
            return "Modèle entraîné avec succès à partir de la base de données.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erreur pendant l'entraînement : " + e.getMessage();
        }
    }

    // Endpoint pour prédire si un traitement est efficace
    @GetMapping("/prediction/by-id/{id}")
    public ResponseEntity<?> predictFromLatestDataById(@PathVariable("id") Long id) {
        try {
            // Appel du service pour prédire à partir des données récentes du patient
            TreatmentPredictionDTO prediction = treatmentPredictionService.predictFromLatestData(id);

            // Retourner le DTO directement
            return ResponseEntity.ok(prediction);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la prédiction: " + e.getMessage());
        }
    }

}
