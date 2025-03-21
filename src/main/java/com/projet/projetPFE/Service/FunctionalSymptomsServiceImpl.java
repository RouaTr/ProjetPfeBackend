package com.projet.projetPFE.Service;

import com.projet.projetPFE.Entities.FunctionalSymptoms;
import com.projet.projetPFE.Repository.FunctionalSymptomsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FunctionalSymptomsServiceImpl implements FunctionalSymptomsService {
    @Autowired
    FunctionalSymptomsRepository functionalSymptomsRepository;
    @Override
    public FunctionalSymptoms addFunctionalSymptoms(Long patientId, FunctionalSymptoms functionalSymptoms) {
        return functionalSymptomsRepository.save(functionalSymptoms);
    }

    @Override
    public FunctionalSymptoms updateFunctionalSymptoms(Long id,Long patientId, FunctionalSymptoms functionalSymptoms) {
        functionalSymptoms.setId(id);
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
}
