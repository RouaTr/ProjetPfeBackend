package com.projet.projetPFE.Service;

import com.projet.projetPFE.Entities.Laboratory;
import com.projet.projetPFE.Entities.Observation;
import com.projet.projetPFE.Entities.Patient;
import com.projet.projetPFE.Repository.LaboratoryRepository;
import com.projet.projetPFE.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class LaboratoryServiceImpl implements LaboratoryService{
    @Autowired
    LaboratoryRepository laboratoryRepository;

    @Autowired
    private PatientRepository patientRepository;
    public Laboratory addLaboratory(Long patientId, Laboratory laboratory) {
        // Vérifier si le patient existe
        Optional<Patient> patientOptional = patientRepository.findById(patientId);
        if (patientOptional.isEmpty()) {
            throw new RuntimeException("Patient non trouvé avec l'ID : " + patientId);
        }

        // Associer uniquement l'ID du patient
        Patient patient = patientOptional.get();
        laboratory.setPatient(patient);


        return laboratoryRepository.save(laboratory);
    }

    @Override
    public Laboratory updateLaboratory(Long id, Long patientId, Laboratory laboratory) {

        if (!laboratoryRepository.existsById(id)) {
            throw new RuntimeException("bilan non trouvé avec l'ID : " + id);
        }

        // Vérifier si le patient existe
        Optional<Patient> patientOptional = patientRepository.findById(patientId);
        if (patientOptional.isEmpty()) {
            throw new RuntimeException("Patient non trouvé avec l'ID : " + patientId);
        }

        // Associer uniquement l'ID du patient et l'ID de bilan
        Patient patient = patientOptional.get();
        laboratory.setId(id);
        laboratory.setPatient(patient);

        return laboratoryRepository.save(laboratory);
    }

    @Override
    public List<Laboratory> displayLaboratory() {
        return laboratoryRepository.findAll();
    }

    public List<Laboratory> findLaboratoryByPatientId(Long patientId) {
        System.out.println("Requête reçue pour patientId: " + patientId);
        return laboratoryRepository.findByPatientId(patientId);
    }

    @Override
    public Optional<Laboratory> displayLaboratoryById(Long id) {
        return laboratoryRepository.findById(id);
    }
}
