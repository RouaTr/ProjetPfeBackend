package com.projet.projetPFE.Repository;

import com.projet.projetPFE.Entities.ClinicalSymptoms;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClinicalSymptomsRepository extends JpaRepository<ClinicalSymptoms,Long> {
    List<ClinicalSymptoms> findByPatientId(Long patientId);
}
