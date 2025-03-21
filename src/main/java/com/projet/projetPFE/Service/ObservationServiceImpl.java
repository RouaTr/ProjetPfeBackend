package com.projet.projetPFE.Service;

import com.projet.projetPFE.Entities.Observation;
import com.projet.projetPFE.Repository.ObservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ObservationServiceImpl implements ObservationService {
    @Autowired
    ObservationRepository observationRepository;
    @Override
    public Observation addObservation(Long patientId, Observation observation) {
        return observationRepository.save(observation);
    }

    @Override
    public Observation updateObservation(Long id, Long patientId, Observation observation) {
        observation.setId(id);
        return observationRepository.save(observation);
    }

    @Override
    public List<Observation> displayObservation() {
        return observationRepository.findAll();
    }

    @Override
    public Optional<Observation> displayObservation(Long id) {
        return observationRepository.findById(id);
    }
}
