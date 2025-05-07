package com.projet.projetPFE.Service;

import com.projet.projetPFE.ARFFExporter;
import com.projet.projetPFE.Repository.PredictionRepository;
import com.projet.projetPFE.Repository.TreatmentOptionRepository;
import com.projet.projetPFE.config.TreatmentPredictionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service

public class ARFFService {
    private final PredictionRepository predictionRepository;

    @Autowired
    public ARFFService(PredictionRepository predictionRepository) {
        this.predictionRepository = predictionRepository;
    }
    @Autowired
    private TreatmentOptionRepository treatmentOptionRepository;

    public void generateARFF() throws IOException {
        // Créer le dossier s'il n'existe pas
        File outputDir = new File("output");
        if (!outputDir.exists()) {
            outputDir.mkdir();
        }

        List<TreatmentPredictionDTO> predictions = predictionRepository.fetchTreatmentPredictionData();

        // 👉 Récupérer tous les traitements de la base de données
        List<String> treatmentOptions = treatmentOptionRepository.findAllTreatmentNames();

        ARFFExporter.exportToARFF(predictions, "output/data.arff", treatmentOptions);
    }



}
