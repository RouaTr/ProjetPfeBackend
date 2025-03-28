package com.projet.projetPFE.Service;


import com.projet.projetPFE.Entities.Observation;
import com.projet.projetPFE.Entities.Patient;

import java.util.List;
import java.util.Optional;

public interface ObservationService {
    Observation addObservation(Long patientId,Observation observation);
   Observation updateObservation(Long id,Long patientId, Observation observation);
    List<Observation> displayObservation();
    Optional<Observation> displayObservationById(Long id);
    List<Observation> findObservationsByPatientId(Long patientId);


}
