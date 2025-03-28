package com.projet.projetPFE.Service;

import com.projet.projetPFE.Entities.MedicalHistory;
import com.projet.projetPFE.Entities.Observation;
import com.projet.projetPFE.Entities.Patient;
import com.projet.projetPFE.Repository.MedicalHistoryRepository;
import com.projet.projetPFE.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class MedicalHistoryServiceImpl implements MedicalHistoryService{
    @Autowired
    MedicalHistoryRepository medicalHistoryRepository;
    @Autowired
    private PatientRepository patientRepository;
    public  MedicalHistory addMedicalHistory(Long patientId, MedicalHistory medicalHistory) {
        // Vérifier si le patient existe
        Optional<Patient> patientOptional = patientRepository.findById(patientId);
        if (patientOptional.isEmpty()) {
            throw new RuntimeException("Patient non trouvé avec l'ID : " + patientId);
        }


        Patient patient = patientOptional.get();
        medicalHistory.setPatient(patient);


        return  medicalHistoryRepository.save( medicalHistory);
    }


    @Override
    public MedicalHistory updateMedicalHistory(Long id, Long patientId, MedicalHistory medicalHistory) {
        if (!medicalHistoryRepository.existsById(id)) {
            throw new RuntimeException("Observation non trouvée avec l'ID : " + id);
        }

        // Vérifier si le patient existe
        Optional<Patient> patientOptional = patientRepository.findById(patientId);
        if (patientOptional.isEmpty()) {
            throw new RuntimeException("Patient non trouvé avec l'ID : " + patientId);
        }


        Patient patient = patientOptional.get();
        medicalHistory.setId(id);
        medicalHistory.setPatient(patient);

        return medicalHistoryRepository.save(medicalHistory);
    }

    @Override
    public List<MedicalHistory> displayMedicalHistory() {
        return medicalHistoryRepository.findAll();
    }

    @Override
    public Optional<MedicalHistory> displayMedicalHistoryById(Long id) {
        return medicalHistoryRepository.findById(id);
    }
    public List<MedicalHistory> findMedicalHistoryByPatientId(Long patientId) {
        System.out.println("Requête reçue pour patientId: " + patientId);
        return medicalHistoryRepository.findByPatientId(patientId);
    }
}
