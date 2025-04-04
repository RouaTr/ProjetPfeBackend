package com.projet.projetPFE.Service;

import com.projet.projetPFE.Entities.MedicalTreatment;

import java.util.List;
import java.util.Optional;

public interface MedicalTreatmentService {
    MedicalTreatment addMedicalTreatment(Long patientId, MedicalTreatment medicalTreatment);
    MedicalTreatment updateMedicalTreatment(Long id,Long patientId, MedicalTreatment medicalTreatment);
    List<MedicalTreatment> displayMedicalTreatment();
    Optional<MedicalTreatment> displayMedicalTreatmentById(Long id);
    List<MedicalTreatment> findMedicalTreatmentByPatientId(Long patientId);
}
