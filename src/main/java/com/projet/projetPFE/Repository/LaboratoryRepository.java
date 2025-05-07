package com.projet.projetPFE.Repository;

import com.projet.projetPFE.Entities.FunctionalSymptoms;
import com.projet.projetPFE.Entities.Laboratory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LaboratoryRepository extends JpaRepository<Laboratory,Long> {
    List<Laboratory> findByPatientId(Long patientId);
    Laboratory findTopByPatient_IdOrderByMedicaltestDateDesc(Long patientId);

    // Nouvelle méthode pour récupérer la dernière charge virale non nulle
    Laboratory findTopByPatient_IdAndViralLoadIsNotNullOrderByMedicaltestDateDesc(Long patientId);

    // Nouvelle méthode pour récupérer le dernier taux de CD4 non nul
    Laboratory findTopByPatient_IdAndCd4CountIsNotNullOrderByMedicaltestDateDesc(Long patientId);
}
