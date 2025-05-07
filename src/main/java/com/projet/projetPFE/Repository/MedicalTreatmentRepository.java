package com.projet.projetPFE.Repository;

import com.projet.projetPFE.Entities.MedicalTreatment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicalTreatmentRepository extends JpaRepository<MedicalTreatment,Long> {
    List<MedicalTreatment> findByPatientId(Long patientId);
    MedicalTreatment findTopByPatient_IdOrderByTreatmentStartDateDesc(Long patientId);

}
