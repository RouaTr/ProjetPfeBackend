package com.projet.projetPFE.Service;

import com.projet.projetPFE.Entities.Laboratory;
import com.projet.projetPFE.Repository.LaboratoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class LaboratoryServiceImpl implements LaboratoryService{
    @Autowired
    LaboratoryRepository laboratoryRepository;
    @Override
    public Laboratory addLaboratory(Long patientId, Laboratory laboratory) {
        return laboratoryRepository.save(laboratory);
    }

    @Override
    public Laboratory updateLaboratory(Long id,Long patientId, Laboratory laboratory) {
        laboratory.setId(id);
        return laboratoryRepository.save(laboratory);
    }

    @Override
    public List<Laboratory> displayLaboratory() {
        return laboratoryRepository.findAll();
    }

    @Override
    public Optional<Laboratory> displayLaboratoryById(Long id) {
        return laboratoryRepository.findById(id);
    }
}
