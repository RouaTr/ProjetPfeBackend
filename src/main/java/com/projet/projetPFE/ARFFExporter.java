package com.projet.projetPFE;

import com.projet.projetPFE.config.TreatmentPredictionDTO;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ARFFExporter {

    public static void exportToARFF(List<TreatmentPredictionDTO> dataList, String filePath, List<String> additionalData) throws IOException {
        List<String> treatmentNames = new ArrayList<>();
        // Recueillir tous les traitements uniques
        dataList.forEach(dto -> {
            if (!treatmentNames.contains(dto.getTreatmentName())) {
                treatmentNames.add(dto.getTreatmentName());
            }
        });

        // Définir les attributs
        ArrayList<Attribute> attributes = new ArrayList<>();
        attributes.add(new Attribute("viralLoad"));
        attributes.add(new Attribute("cd4Count"));
        attributes.add(new Attribute("treatmentName", treatmentNames));

        // Attribut classe
        List<String> classValues = List.of("yes", "no");
        attributes.add(new Attribute("effective", classValues));

        // Créer le fichier ARFF
        Instances data = new Instances("TreatmentPredictionData", attributes, dataList.size());
        data.setClassIndex(data.numAttributes() - 1);

        for (TreatmentPredictionDTO dto : dataList) {
            Instance instance = new DenseInstance(attributes.size());
            instance.setValue(attributes.get(0), dto.getViralLoad());
            instance.setValue(attributes.get(1), dto.getCd4Count());
            instance.setValue(attributes.get(2), dto.getTreatmentName());
            instance.setValue(attributes.get(3), dto.getEffective() ? "yes" : "no");
            data.add(instance);
        }

        // Écrire dans un fichier ARFF
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(data.toString());
        }
    }
}
