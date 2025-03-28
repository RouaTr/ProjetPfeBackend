package com.projet.projetPFE.Repository;

import com.projet.projetPFE.Entities.FunctionalSymptoms;
import com.projet.projetPFE.Entities.Laboratory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LaboratoryRepository extends JpaRepository<Laboratory,Long> {
    List<Laboratory> findByPatientId(Long patientId);
}
