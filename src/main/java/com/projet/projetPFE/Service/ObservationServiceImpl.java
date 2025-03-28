package com.projet.projetPFE.Service;


import com.projet.projetPFE.Entities.Observation;
import com.projet.projetPFE.Entities.Patient;
import com.projet.projetPFE.Repository.ObservationRepository;
import com.projet.projetPFE.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ObservationServiceImpl implements ObservationService {
    @Autowired
    private ObservationRepository observationRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public Observation addObservation(Long patientId, Observation observation) {
        // Vérifier si le patient existe
        Optional<Patient> patientOptional = patientRepository.findById(patientId);
        if (patientOptional.isEmpty()) {
            throw new RuntimeException("Patient non trouvé avec l'ID : " + patientId);
        }

        // Associer uniquement l'ID du patient
        Patient patient = patientOptional.get();
        observation.setPatient(patient);

        // Enregistrer l'observation
        return observationRepository.save(observation);
    }

    @Override
    public Observation updateObservation(Long id, Long patientId, Observation observation) {
        // Vérifier si l'observation existe
        if (!observationRepository.existsById(id)) {
            throw new RuntimeException("Observation non trouvée avec l'ID : " + id);
        }

        // Vérifier si le patient existe
        Optional<Patient> patientOptional = patientRepository.findById(patientId);
        if (patientOptional.isEmpty()) {
            throw new RuntimeException("Patient non trouvé avec l'ID : " + patientId);
        }

        // Associer uniquement l'ID du patient et l'ID de l'observation
        Patient patient = patientOptional.get();
        observation.setId(id);
        observation.setPatient(patient);

        return observationRepository.save(observation);
    }

    @Override
    public List<Observation> displayObservation() {
        return observationRepository.findAll();
    }

    public List<Observation> findObservationsByPatientId(Long patientId) {
        System.out.println("Requête reçue pour patientId: " + patientId);
        return observationRepository.findByPatientId(patientId);
    }

    @Override
    public Optional<Observation> displayObservationById(Long id) {
        return observationRepository.findById(id);
    }




}
