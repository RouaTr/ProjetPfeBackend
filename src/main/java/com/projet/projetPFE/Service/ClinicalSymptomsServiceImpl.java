package com.projet.projetPFE.Service;

import com.projet.projetPFE.Entities.ClinicalSymptoms;
import com.projet.projetPFE.Repository.ClinicalSymptomsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ClinicalSymptomsServiceImpl implements ClinicalSymptomsService{
    @Autowired
    ClinicalSymptomsRepository clinicalSymptomsRepository;
    @Override
    public ClinicalSymptoms addClinicalSymptoms(Long patientId, ClinicalSymptoms clinicalSymptoms) {
        return clinicalSymptomsRepository.save(clinicalSymptoms);
    }

    @Override
    public ClinicalSymptoms updateClinicalSymptoms(Long id,Long patientId, ClinicalSymptoms clinicalSymptoms) {
        clinicalSymptoms.setId(id);
        return clinicalSymptomsRepository.save(clinicalSymptoms);
    }

    @Override
    public List<ClinicalSymptoms> displayClinicalSymptoms() {
        return clinicalSymptomsRepository.findAll();
    }

    @Override
    public Optional<ClinicalSymptoms> displayClinicalSymptomsById(Long id) {
        return clinicalSymptomsRepository.findById(id);
    }
}
