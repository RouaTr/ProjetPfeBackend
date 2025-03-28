package com.projet.projetPFE.Repository;

import com.projet.projetPFE.Entities.FunctionalSymptoms;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FunctionalSymptomsRepository extends JpaRepository<FunctionalSymptoms,Long> {
    List<FunctionalSymptoms> findByPatientId(Long patientId);

}
