package com.projet.projetPFE.Service;

import com.projet.projetPFE.Entities.MedicalHistory;
import com.projet.projetPFE.Repository.MedicalHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class MedicalHistoryServiceImpl implements MedicalHistoryService{
    @Autowired
    MedicalHistoryRepository medicalHistoryRepository;
    @Override
    public MedicalHistory addMedicalHistory(Long patientId, MedicalHistory medicalHistory) {
        return medicalHistoryRepository.save(medicalHistory);
    }

    @Override
    public MedicalHistory updateMedicalHistory(Long id,Long patientId, MedicalHistory medicalHistory) {
        medicalHistory.setId(id);
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
}
