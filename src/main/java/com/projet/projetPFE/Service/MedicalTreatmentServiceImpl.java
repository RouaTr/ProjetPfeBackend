package com.projet.projetPFE.Service;

import com.projet.projetPFE.Entities.MedicalTreatment;
import com.projet.projetPFE.Entities.Patient;
import com.projet.projetPFE.Repository.MedicalTreatmentRepository;
import com.projet.projetPFE.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class MedicalTreatmentServiceImpl implements MedicalTreatmentService {
    @Autowired
    private MedicalTreatmentRepository medicalTreatmentRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public MedicalTreatment addMedicalTreatment(Long patientId, MedicalTreatment medicalTreatment) {
        // Vérifier si le patient existe
        Optional<Patient> patientOptional = patientRepository.findById(patientId);
        if (patientOptional.isEmpty()) {
            throw new RuntimeException("Patient non trouvé avec l'ID : " + patientId);
        }

        // Associer uniquement l'ID du patient
        Patient patient = patientOptional.get();
        medicalTreatment.setPatient(patient);

        // Enregistrer l'observation
        return medicalTreatmentRepository.save(medicalTreatment);
    }

    @Override
    public MedicalTreatment updateMedicalTreatment(Long id, Long patientId, MedicalTreatment medicalTreatment) {
        // Vérifier si l'observation existe
        if (!medicalTreatmentRepository.existsById(id)) {
            throw new RuntimeException("Observation non trouvée avec l'ID : " + id);
        }

        // Vérifier si le patient existe
        Optional<Patient> patientOptional = patientRepository.findById(patientId);
        if (patientOptional.isEmpty()) {
            throw new RuntimeException("Patient non trouvé avec l'ID : " + patientId);
        }

        // Associer uniquement l'ID du patient et l'ID de l'observation
        Patient patient = patientOptional.get();
        medicalTreatment.setTreatmentId(id);
        medicalTreatment.setPatient(patient);

        return medicalTreatmentRepository.save(medicalTreatment);
    }

    @Override
    public List<MedicalTreatment> displayMedicalTreatment() {
        return medicalTreatmentRepository.findAll();
    }

    public List<MedicalTreatment> findMedicalTreatmentByPatientId(Long patientId) {
        System.out.println("Requête reçue pour patientId: " + patientId);
        return medicalTreatmentRepository.findByPatientId(patientId);
    }

    @Override
    public Optional<MedicalTreatment> displayMedicalTreatmentById(Long id) {
        return medicalTreatmentRepository.findById(id);
    }




}


