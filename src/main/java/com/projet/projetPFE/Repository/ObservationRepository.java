package com.projet.projetPFE.Repository;

import com.projet.projetPFE.Entities.Observation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ObservationRepository extends JpaRepository<Observation,Long> {
}
