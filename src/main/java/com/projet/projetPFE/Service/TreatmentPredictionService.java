package com.projet.projetPFE.Service;

import com.projet.projetPFE.Entities.Laboratory;
import com.projet.projetPFE.Entities.MedicalTreatment;
import com.projet.projetPFE.Entities.Patient;
import com.projet.projetPFE.Entities.TreatmentOption;
import com.projet.projetPFE.Repository.*;
import com.projet.projetPFE.config.TreatmentPredictionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import weka.classifiers.Classifier;
import weka.classifiers.trees.J48;
import weka.core.*;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TreatmentPredictionService {

    @Autowired
    private PredictionRepository predictionRepository;

    @Autowired
    private TreatmentOptionRepository treatmentOptionRepository;

    @Autowired
    private LaboratoryRepository laboratoryRepository;

    @Autowired
    private MedicalTreatmentRepository treatmentHistoryRepository;

    @Autowired
    private PatientRepository patientRepository;

    private Classifier classifier;

    public void trainModel() {
        try {
            Instances trainingData = createTrainingData();

            if (trainingData == null || trainingData.isEmpty()) {
                throw new IllegalStateException("Aucune donnée d'entraînement trouvée.");
            }

            classifier = new J48();
            classifier.buildClassifier(trainingData);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Instances createTrainingData() {
        try {
            List<TreatmentPredictionDTO> predictions = predictionRepository.fetchTreatmentPredictionData();
            List<String> treatmentOptions = treatmentOptionRepository.findAllTreatmentNames();

            ArrayList<Attribute> attributes = new ArrayList<>();
            attributes.add(new Attribute("viralLoad"));
            attributes.add(new Attribute("cd4Count"));
            attributes.add(new Attribute("treatmentHistory", new ArrayList<>(treatmentOptions)));

            List<String> classValues = List.of("yes", "no");
            attributes.add(new Attribute("effective", new ArrayList<>(classValues)));

            Instances data = new Instances("TreatmentData", attributes, predictions.size());
            data.setClassIndex(data.numAttributes() - 1);

            for (TreatmentPredictionDTO dto : predictions) {
                if (!treatmentOptions.contains(dto.getTreatmentName())) continue;

                System.out.printf("CV: %.1f, CD4: %.1f, Traitement: %s, Efficace: %s\n",
                        dto.getViralLoad(), dto.getCd4Count(), dto.getTreatmentName(), dto.getEffective());

                Instance instance = new DenseInstance(4);
                instance.setValue(attributes.get(0), dto.getViralLoad() != null ? dto.getViralLoad() : 0);
                instance.setValue(attributes.get(1), dto.getCd4Count() != null ? dto.getCd4Count() : 0);
                instance.setValue(attributes.get(2), dto.getTreatmentName());
                instance.setValue(attributes.get(3), dto.getEffective() != null && dto.getEffective() ? "yes" : "no");

                data.add(instance);
            }

            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public TreatmentPredictionDTO predictFromLatestData(Long patientId) {
        TreatmentPredictionDTO dto = new TreatmentPredictionDTO();
        dto.setMedicalRecordNumber(patientId.toString());

        Patient patient = patientRepository.findById(patientId).orElse(null);
        if (patient == null) {
            System.err.println("Patient introuvable pour le numéro de dossier : " + patientId);
            return dto;
        }

        // Récupérer la dernière charge virale
        Laboratory latestViralLoad = laboratoryRepository.findTopByPatient_IdAndViralLoadIsNotNullOrderByMedicaltestDateDesc(patientId);
        if (latestViralLoad != null) {
            dto.setViralLoad(latestViralLoad.getViralLoad());
            System.out.println("Dernière charge virale: " + latestViralLoad.getViralLoad() + 
                             " du " + latestViralLoad.getMedicaltestDate());
        } else {
            System.err.println("Aucune charge virale trouvée pour le patient " + patientId);
            dto.setViralLoad(0.0);
        }

        // Récupérer le dernier taux de CD4
        Laboratory latestCd4 = laboratoryRepository.findTopByPatient_IdAndCd4CountIsNotNullOrderByMedicaltestDateDesc(patientId);
        if (latestCd4 != null) {
            dto.setCd4Count(latestCd4.getCd4Count());
            System.out.println("Dernier taux de CD4: " + latestCd4.getCd4Count() + 
                             " du " + latestCd4.getMedicaltestDate());
        } else {
            System.err.println("Aucun taux de CD4 trouvé pour le patient " + patientId);
            dto.setCd4Count(0.0);
        }

        // Récupérer le dernier traitement
        MedicalTreatment latestTreatment = treatmentHistoryRepository.findTopByPatient_IdOrderByTreatmentStartDateDesc(patientId);
        if (latestTreatment != null) {
            dto.setTreatmentName(latestTreatment.getTreatmentName());
            System.out.println("Dernier traitement: " + latestTreatment.getTreatmentName() + 
                             " du " + latestTreatment.getTreatmentStartDate());
        } else {
            System.err.println("Aucun traitement trouvé pour le patient " + patientId);
        }

        // Faire la prédiction
        predictTreatmentWithML(dto);
        return dto;
    }

    public void predictTreatmentWithML(TreatmentPredictionDTO predictionDTO) {
        try {
            if (classifier == null) trainModel();

            double viralLoad = predictionDTO.getViralLoad() != null ? predictionDTO.getViralLoad() : 0;
            double cd4Count = predictionDTO.getCd4Count() != null ? predictionDTO.getCd4Count() : 0;

            // Nouvelle logique de prédiction basée sur les données observées
            boolean ruleEffective = false;
            
            // Vérifier si les valeurs sont valides
            if (viralLoad == 0 || cd4Count == 0) {
                System.err.println("Attention: Données de laboratoire incomplètes - Charge virale: " + viralLoad + ", CD4: " + cd4Count);
                predictionDTO.setEffective(false);
                predictionDTO.setSuggestedTreatment("Données de laboratoire incomplètes");
                return;
            }

            // Si la charge virale est < 50 et CD4 > 200, le traitement est considéré comme efficace
            if (viralLoad < 50 && cd4Count > 200) {
                ruleEffective = true;
            }
            // Si la charge virale est entre 50 et 200, on utilise le modèle Weka
            else if (viralLoad < 200) {
                Instances dataset = createTrainingData();
                if (dataset != null && !dataset.isEmpty()) {
                    Instance instance = new DenseInstance(4);
                    instance.setDataset(dataset);
                    instance.setValue(0, viralLoad);
                    instance.setValue(1, cd4Count);
                    instance.setValue(2, predictionDTO.getTreatmentName());

                    double predicted = classifier.classifyInstance(instance);
                    String predictedClass = dataset.classAttribute().value((int) predicted);
                    ruleEffective = predictedClass.equals("yes");
                }
            }
            // Si la charge virale est > 200, le traitement est considéré comme inefficace
            else {
                ruleEffective = false;
            }

            predictionDTO.setEffective(ruleEffective);

            if (!ruleEffective) {
                String suggestion = suggestAlternativeTreatment(predictionDTO);
                predictionDTO.setSuggestedTreatment(suggestion);
            } else {
                predictionDTO.setSuggestedTreatment(null);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String suggestAlternativeTreatment(TreatmentPredictionDTO predictionDTO) {
        List<TreatmentOption> allOptions = treatmentOptionRepository.findAll();
        List<String> names = allOptions.stream()
                .map(TreatmentOption::getTreatmentName)
                .collect(Collectors.toList());

        // Récupérer l'historique complet des traitements
        List<String> treatmentHistory = predictionRepository.fetchTreatmentHistoryByMedicalRecordNumber(
                predictionDTO.getMedicalRecordNumber());

        // Filtrer les traitements déjà utilisés ET le traitement actuel
        List<String> availableTreatments = names.stream()
                .filter(treatment -> !treatmentHistory.contains(treatment) && 
                                  !treatment.equals(predictionDTO.getTreatmentName()))
                .collect(Collectors.toList());

        if (availableTreatments.isEmpty()) {
            System.out.println("Aucun traitement alternatif disponible pour le patient " + 
                             predictionDTO.getMedicalRecordNumber());
            return null;
        }

        double viralLoad = predictionDTO.getViralLoad();
        double cd4Count = predictionDTO.getCd4Count();

        System.out.println("Analyse pour le patient " + predictionDTO.getMedicalRecordNumber());
        System.out.println("- Charge virale : " + viralLoad);
        System.out.println("- CD4 : " + cd4Count);
        System.out.println("- Traitement actuel : " + predictionDTO.getTreatmentName());
        System.out.println("- Historique des traitements : " + treatmentHistory);

        // Logique de sélection basée sur l'historique et les valeurs actuelles
        if (viralLoad > 1000) {
            // Charge virale très élevée : besoin d'un traitement puissant
            // Éviter les classes de médicaments déjà utilisées
            String suggested = availableTreatments.stream()
                    .filter(t -> {
                        // Si le patient a déjà utilisé des inhibiteurs de protéase
                        boolean usedPI = treatmentHistory.stream()
                                .anyMatch(h -> h.contains("Lop/r") || h.contains("ATV/r"));
                        // Si le patient a déjà utilisé des inhibiteurs d'intégrase
                        boolean usedINSTI = treatmentHistory.stream()
                                .anyMatch(h -> h.contains("DTG"));
                        
                        // Suggérer un traitement avec une classe différente
                        if (usedPI && usedINSTI) {
                            return t.contains("EFV"); // Essayer un inhibiteur non-nucléosidique
                        } else if (usedPI) {
                            return t.contains("DTG"); // Essayer un inhibiteur d'intégrase
                        } else if (usedINSTI) {
                            return t.contains("Lop/r"); // Essayer un inhibiteur de protéase
                        }
                        return t.contains("DTG") && t.contains("Lop/r"); // Combinaison la plus puissante
                    })
                    .findFirst()
                    .orElse(availableTreatments.get(0));
            
            System.out.println("Traitement suggéré (charge virale très élevée) : " + suggested);
            return suggested;
        } 
        else if (viralLoad > 200) {
            // Charge virale modérément élevée
            String suggested = availableTreatments.stream()
                    .filter(t -> {
                        // Vérifier si le patient a déjà eu des problèmes avec certains médicaments
                        boolean hadSideEffects = treatmentHistory.stream()
                                .anyMatch(h -> h.contains("EFV")); // Exemple : effets secondaires avec EFV
                        
                        if (hadSideEffects) {
                            return !t.contains("EFV"); // Éviter les médicaments problématiques
                        }
                        return t.contains("DTG"); // Privilégier les inhibiteurs d'intégrase
                    })
                    .findFirst()
                    .orElse(availableTreatments.get(0));
            
            System.out.println("Traitement suggéré (charge virale modérée) : " + suggested);
            return suggested;
        } 
        else if (cd4Count < 200) {
            // CD4 bas : besoin d'un traitement qui augmente rapidement les CD4
            String suggested = availableTreatments.stream()
                    .filter(t -> t.contains("Lop/r")) // LPV/r est connu pour augmenter rapidement les CD4
                    .findFirst()
                    .orElse(availableTreatments.stream()
                            .filter(t -> t.contains("DTG"))
                            .findFirst()
                            .orElse(availableTreatments.get(0)));
            
            System.out.println("Traitement suggéré (CD4 bas) : " + suggested);
            return suggested;
        } 
        else {
            // Cas standard : choisir un traitement simple et bien toléré
            String suggested = availableTreatments.stream()
                    .filter(t -> {
                        // Éviter les combinaisons complexes si possible
                        boolean isSimple = !t.contains("+") && !t.contains("/");
                        // Privilégier les traitements en un seul comprimé
                        boolean isSingleTablet = t.contains("=");
                        
                        return isSimple || isSingleTablet;
                    })
                    .findFirst()
                    .orElse(availableTreatments.get(0));
            
            System.out.println("Traitement suggéré (cas standard) : " + suggested);
            return suggested;
        }
    }
}
