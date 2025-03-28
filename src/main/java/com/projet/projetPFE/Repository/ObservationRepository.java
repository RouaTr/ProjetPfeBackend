package com.projet.projetPFE.Repository;

import com.projet.projetPFE.Entities.Observation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ObservationRepository extends JpaRepository<Observation,Long> {
    List<Observation> findByPatientId(Long patientId);
}
