package com.projet.projetPFE.Repository;

import com.projet.projetPFE.Entities.Patient;
import com.projet.projetPFE.config.TreatmentPredictionDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PredictionRepository extends JpaRepository<Patient, Long> {
    // Cette requête extrait les données nécessaires à la prédiction de l'efficacité d'un traitement
    // en combinant les informations du patient, des tests de laboratoire et des traitements médicaux.
    @Query("SELECT new com.projet.projetPFE.config.TreatmentPredictionDTO(" +
            "p.medicalRecordNumber, " +
            "l.viralLoad, " +
            "l.cd4Count, " +
            "m.treatmentName, " +
            "CASE " +
            "   WHEN l.viralLoad < 50 THEN true " +
            "   WHEN l.viralLoad < 200 AND l.cd4Count > 200 THEN true " +
            "   ELSE false " +
            "END) " +
            "FROM Patient p " +
            "JOIN p.laboratories l " +
            "JOIN p.medicalTreatments m " +
            "WHERE l.medicaltestDate >= m.treatmentStartDate " +
            "AND (l.medicaltestDate <= m.next_intake_Date OR m.next_intake_Date IS NULL) " +
            "AND l.cd4Count IS NOT NULL " +
            "AND l.viralLoad IS NOT NULL")

    List<TreatmentPredictionDTO> fetchTreatmentPredictionData();


    //  récupérer l'historique des traitements d'un patient
    @Query("SELECT m.treatmentName FROM MedicalTreatment m WHERE m.patient.medicalRecordNumber = :medicalRecordNumber")
    List<String> fetchTreatmentHistoryByMedicalRecordNumber(String medicalRecordNumber);

    @Query("SELECT new com.projet.projetPFE.config.TreatmentPredictionDTO(" +
            "p.medicalRecordNumber, " +
            "l.viralLoad, " +
            "l.cd4Count, " +
            "m.treatmentName, " +
            "CASE " +
            "   WHEN l.viralLoad < 50 THEN true " +
            "   WHEN l.viralLoad < 200 AND l.cd4Count > 200 THEN true " +
            "   ELSE false " +
            "END) " +
            "FROM Patient p " +
            "JOIN p.laboratories l " +
            "JOIN p.medicalTreatments m " +
            "WHERE l.cd4Count IS NOT NULL " +
            "AND l.viralLoad IS NOT NULL")
    List<TreatmentPredictionDTO> fetchAllTreatmentPredictions();

}

