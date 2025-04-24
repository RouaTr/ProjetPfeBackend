package com.projet.projetPFE.Service;

import com.projet.projetPFE.Entities.Laboratory;
import com.projet.projetPFE.Entities.MedicalTreatment;
import com.projet.projetPFE.Entities.Observation;
import com.projet.projetPFE.Entities.Patient;
import com.projet.projetPFE.Repository.LaboratoryRepository;
import com.projet.projetPFE.Repository.MedicalTreatmentRepository;
import com.projet.projetPFE.Repository.PatientRepository;
import com.projet.projetPFE.config.TrendDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    @Autowired
    private MedicalTreatmentRepository medicalTreatmentRepository;
    public List<TrendDTO> getTrendsForPatient(Long patientId) {
        // Récupère les résultats de laboratoire pour le patient
        List<Laboratory> labs = laboratoryRepository.findByPatientId(patientId);

        // Récupère tous les traitements pour ce patient
        List<MedicalTreatment> treatments = medicalTreatmentRepository.findByPatientId(patientId);

        // Si aucun traitement n'est trouvé, on renvoie un traitement par défaut "Unknown"
        return labs.stream()
                .flatMap(lab -> treatments.stream()
                        .map(treatment -> new TrendDTO(
                                lab.getMedicaltestDate(),
                                lab.getCd4Count(),
                                lab.getViralLoad(),
                                treatment.getTreatmentStartDate(),
                                treatment.getNext_intake_Date(),
                                treatment.getTreatmentName() // Utiliser le nom du traitement pour chaque laboratoire
                        ))
                )
                .collect(Collectors.toList());
    }


}
