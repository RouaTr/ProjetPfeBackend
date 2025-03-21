package com.projet.projetPFE.Service;

import com.projet.projetPFE.Entities.ClinicalSymptoms;
import com.projet.projetPFE.Entities.Patient;

import java.util.List;
import java.util.Optional;

public interface ClinicalSymptomsService {
    ClinicalSymptoms addClinicalSymptoms(Long patientId,ClinicalSymptoms clinicalSymptoms);
    ClinicalSymptoms updateClinicalSymptoms(Long id,Long patientId,ClinicalSymptoms clinicalSymptoms);
    List<ClinicalSymptoms> displayClinicalSymptoms();
    Optional<ClinicalSymptoms> displayClinicalSymptomsById(Long id);
}
