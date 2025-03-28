package com.projet.projetPFE.Service;

import com.projet.projetPFE.Entities.FunctionalSymptoms;
import com.projet.projetPFE.Entities.Observation;
import com.projet.projetPFE.Entities.Patient;
import com.projet.projetPFE.Repository.FunctionalSymptomsRepository;
import com.projet.projetPFE.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FunctionalSymptomsServiceImpl implements FunctionalSymptomsService {
    @Autowired
    FunctionalSymptomsRepository functionalSymptomsRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Override
    public FunctionalSymptoms addFunctionalSymptoms(Long patientId, FunctionalSymptoms functionalSymptoms) {
        // Vérifier si le patient existe
        Optional<Patient> patientOptional = patientRepository.findById(patientId);
        if (patientOptional.isEmpty()) {
            throw new RuntimeException("Patient non trouvé avec l'ID : " + patientId);
        }

        // Associer uniquement l'ID du patient
        Patient patient = patientOptional.get();
        functionalSymptoms.setPatient(patient);

        // Enregistrer l'observation
        return functionalSymptomsRepository.save(functionalSymptoms);
    }


    @Override
    public FunctionalSymptoms updateFunctionalSymptoms(Long id, Long patientId, FunctionalSymptoms functionalSymptoms) {
        if (!functionalSymptomsRepository.existsById(id)) {
            throw new RuntimeException("Singes Fonctionnels  non trouvés avec l'ID : " + id);
        }

        // Vérifier si le patient existe
        Optional<Patient> patientOptional = patientRepository.findById(patientId);
        if (patientOptional.isEmpty()) {
            throw new RuntimeException("Patient non trouvé avec l'ID : " + patientId);
        }

        // Associer uniquement l'ID du patient et l'ID de l'observation
        Patient patient = patientOptional.get();
        functionalSymptoms.setId(id);
        functionalSymptoms.setPatient(patient);

        return functionalSymptomsRepository.save(functionalSymptoms);
    }

    @Override
    public List<FunctionalSymptoms> displayFunctionalSymptoms() {
        return functionalSymptomsRepository.findAll();
    }

    @Override
    public Optional<FunctionalSymptoms> displayFunctionalSymptomsById(Long id) {
        return functionalSymptomsRepository.findById(id);
    }

    public List<FunctionalSymptoms> findFunctionalSymptomsByPatientId(Long patientId) {
        return functionalSymptomsRepository.findByPatientId(patientId);
    }
}
