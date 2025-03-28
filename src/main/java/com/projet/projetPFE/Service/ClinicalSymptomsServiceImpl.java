package com.projet.projetPFE.Service;

import com.projet.projetPFE.Entities.ClinicalSymptoms;
import com.projet.projetPFE.Entities.Patient;
import com.projet.projetPFE.Repository.ClinicalSymptomsRepository;
import com.projet.projetPFE.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ClinicalSymptomsServiceImpl implements ClinicalSymptomsService{
    @Autowired
    ClinicalSymptomsRepository clinicalSymptomsRepository;
    @Autowired
    private PatientRepository patientRepository;


    @Override
    public ClinicalSymptoms addClinicalSymptoms(Long patientId, ClinicalSymptoms clinicalSymptoms) {
        // Vérifier si le patient existe
        Optional<Patient> patientOptional = patientRepository.findById(patientId);
        if (patientOptional.isEmpty()) {
            throw new RuntimeException("Patient non trouvé avec l'ID : " + patientId);
        }

        // Associer uniquement l'ID du patient
        Patient patient = patientOptional.get();
        clinicalSymptoms.setPatient(patient);

        // Enregistrer l'observation
        return clinicalSymptomsRepository.save(clinicalSymptoms);
    }


    @Override
    public ClinicalSymptoms updateClinicalSymptoms(Long id, Long patientId, ClinicalSymptoms clinicalSymptoms) {
        if (!clinicalSymptomsRepository.existsById(id)) {
            throw new RuntimeException("Signes Cliniques non trouvés  avec l'ID : " + id);
        }

        // Vérifier si le patient existe
        Optional<Patient> patientOptional = patientRepository.findById(patientId);
        if (patientOptional.isEmpty()) {
            throw new RuntimeException("Patient non trouvé avec l'ID : " + patientId);
        }

        // Associer uniquement l'ID du patient et l'ID de l'observation
        Patient patient = patientOptional.get();
        clinicalSymptoms.setId(id);
        clinicalSymptoms.setPatient(patient);

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
    public List<ClinicalSymptoms> findClinicalSymptomsByPatientId(Long patientId) {
        System.out.println("Requête reçue pour patientId: " + patientId);
        return clinicalSymptomsRepository.findByPatientId(patientId);
    }
}
