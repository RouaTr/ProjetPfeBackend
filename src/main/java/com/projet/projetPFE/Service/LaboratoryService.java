package com.projet.projetPFE.Service;

import com.projet.projetPFE.Entities.Laboratory;
import com.projet.projetPFE.Entities.Observation;
import com.projet.projetPFE.Entities.Patient;

import java.util.List;
import java.util.Optional;

public interface LaboratoryService {
    Laboratory addLaboratory(Long patientId,Laboratory laboratory);
    Laboratory updateLaboratory(Long id,Long patientId, Laboratory laboratory);
    List<Laboratory> displayLaboratory();
    Optional<Laboratory> displayLaboratoryById(Long id);
    List<Laboratory> findLaboratoryByPatientId(Long patientId);
}
