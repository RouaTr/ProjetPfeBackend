package com.projet.projetPFE.Repository;

import com.projet.projetPFE.Entities.TreatmentOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TreatmentOptionRepository extends JpaRepository<TreatmentOption, Long> {
    @Query("SELECT t.treatmentName FROM TreatmentOption t")
    List<String> findAllTreatmentNames();
}

