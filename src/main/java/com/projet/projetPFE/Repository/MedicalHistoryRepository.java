package com.projet.projetPFE.Repository;

import com.projet.projetPFE.Entities.FunctionalSymptoms;
import com.projet.projetPFE.Entities.MedicalHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicalHistoryRepository extends JpaRepository<MedicalHistory,Long> {
    List<MedicalHistory> findByPatientId(Long patientId);
}
