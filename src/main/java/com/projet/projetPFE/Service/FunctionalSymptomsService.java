package com.projet.projetPFE.Service;

import com.projet.projetPFE.Entities.FunctionalSymptoms;
import com.projet.projetPFE.Entities.Patient;

import java.util.List;
import java.util.Optional;

public interface FunctionalSymptomsService {
    FunctionalSymptoms addFunctionalSymptoms(Long patientId,FunctionalSymptoms functionalSymptoms);
    FunctionalSymptoms updateFunctionalSymptoms(Long id,Long patientId, FunctionalSymptoms functionalSymptoms);
    List<FunctionalSymptoms> displayFunctionalSymptoms();
    Optional<FunctionalSymptoms> displayFunctionalSymptomsById(Long id);
}
