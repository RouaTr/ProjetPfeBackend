package com.projet.projetPFE.Service;

import com.projet.projetPFE.Entities.MedicalHistory;

import com.projet.projetPFE.Entities.Patient;

import java.util.List;
import java.util.Optional;

public interface MedicalHistoryService {
    MedicalHistory addMedicalHistory(Long patientId,MedicalHistory medicalHistory);
    MedicalHistory updateMedicalHistory(Long id,Long patientId, MedicalHistory medicalHistory);
    List<MedicalHistory> displayMedicalHistory();
    Optional<MedicalHistory> displayMedicalHistoryById(Long id);
    List<MedicalHistory> findMedicalHistoryByPatientId(Long patientId);
}
