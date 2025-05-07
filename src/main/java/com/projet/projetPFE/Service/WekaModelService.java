package com.projet.projetPFE.Service;

import com.projet.projetPFE.Entities.TreatmentOption;
import com.projet.projetPFE.Repository.PredictionRepository;
import com.projet.projetPFE.Repository.TreatmentOptionRepository;
import com.projet.projetPFE.config.TreatmentPredictionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class WekaModelService {

    @Autowired
    private PredictionRepository predictionRepository;

    @Autowired
    private TreatmentOptionRepository treatmentOptionRepository;

    public void trainModelFromDatabase() throws Exception {
        // Récupérer les données de la base
        List<TreatmentPredictionDTO> dataList = predictionRepository.fetchTreatmentPredictionData();

        // Récupérer tous les noms de traitements disponibles (en MAJUSCULES pour uniformiser)
        List<String> treatmentNames = treatmentOptionRepository.findAll()
                .stream()
                .map(t -> t.getTreatmentName().toUpperCase())
                .distinct()
                .sorted()
                .collect(Collectors.toList());

        // Définir les attributs
        ArrayList<Attribute> attributes = new ArrayList<>();
        attributes.add(new Attribute("viralLoad"));
        attributes.add(new Attribute("cd4Count"));
        attributes.add(new Attribute("treatmentName", new ArrayList<>(treatmentNames)));
        List<String> classValues = List.of("no", "yes");
        attributes.add(new Attribute("effective", new ArrayList<>(classValues)));

        // Créer l'ensemble de données
        Instances data = new Instances("TreatmentPredictionData", attributes, dataList.size());
        data.setClassIndex(data.numAttributes() - 1);

        // Remplir les données
        int ignored = 0;
        System.out.println("=== Echantillon de données ===");
        for (int i = 0; i < Math.min(10, dataList.size()); i++) {
            TreatmentPredictionDTO dto = dataList.get(i);
            System.out.printf("→ VL: %.2f, CD4: %.2f, TTT: %s, EFFECTIVE: %s%n",
                    dto.getViralLoad(), dto.getCd4Count(), dto.getTreatmentName(), dto.getEffective());
        }

        for (TreatmentPredictionDTO dto : dataList) {
            String treatment = dto.getTreatmentName().toUpperCase();
            if (!treatmentNames.contains(treatment)) {
                System.err.println("⚠️ Traitement inconnu ignoré : " + treatment);
                ignored++;
                continue;
            }

            Instance instance = new DenseInstance(attributes.size());
            instance.setValue(attributes.get(0), dto.getViralLoad() != null ? dto.getViralLoad() : 0);
            instance.setValue(attributes.get(1), dto.getCd4Count() != null ? dto.getCd4Count() : 0);
            instance.setValue(attributes.get(2), treatment);
            instance.setValue(attributes.get(3), dto.getEffective() != null && dto.getEffective() ? "yes" : "no");
            data.add(instance);
        }

        System.out.println("Nombre d’instances ignorées (traitements inconnus) : " + ignored);

        // Vérifier la répartition des classes
        int yesCount = 0, noCount = 0;
        for (int i = 0; i < data.numInstances(); i++) {
            String val = data.instance(i).stringValue(data.classIndex());
            if ("yes".equals(val)) yesCount++;
            else noCount++;
        }
        System.out.println("Nombre de 'yes' : " + yesCount);
        System.out.println("Nombre de 'no' : " + noCount);

        // Ajuster la pondération des classes si nécessaire
        double classWeight = 1.0;
        if (yesCount < noCount) {
            classWeight = (double) noCount / yesCount;
            // Assurer que la pondération ne soit pas infinie ou négative
            if (Double.isInfinite(classWeight) || classWeight < 0) {
                classWeight = 1.0;  // On applique un poids égal si la pondération est invalide
            }
            System.out.println("Pondération des classes : " + classWeight);
        }

        // Entraîner l’arbre J48
        J48 tree = new J48();
        String options = "-C 0.15 -M 4";  // Ajustement des paramètres de l'arbre
        if (classWeight != 1.0) {
            options += " -W " + classWeight;  // Appliquer la pondération des classes
        }
        tree.setOptions(weka.core.Utils.splitOptions(options));
        tree.buildClassifier(data);

        // Évaluer le modèle
        Evaluation eval = new Evaluation(data);
        eval.crossValidateModel(tree, data, 10, new Random(1));

        // Affichage des résultats
        System.out.println("=== Arbre de Décision ===\n" + tree);
        System.out.println("=== Résumé ===\n" + eval.toSummaryString());
        System.out.println("=== Matrice de confusion ===\n" + eval.toMatrixString());
    }
}
