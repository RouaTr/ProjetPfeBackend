package com.projet.projetPFE.Service;

import com.projet.projetPFE.Entities.TreatmentOption;
import com.projet.projetPFE.Repository.TreatmentOptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TreatmentOptionServiceImpl implements TreatmentOptionService {

    @Autowired
    private TreatmentOptionRepository treatmentOptionRepository;

    @Override
    public List<TreatmentOption> getAllTreatmentOptions() {
        return treatmentOptionRepository.findAll();
    }

    @Override
    public TreatmentOption addTreatmentOption(String treatmentName) {
        TreatmentOption newOption = new TreatmentOption();
        newOption.setTreatmentName(treatmentName);
        return treatmentOptionRepository.save(newOption);
    }

    @Override
    public void deleteTreatmentOption(Long id) {
        treatmentOptionRepository.deleteById(id);
    }

    @Override
    public TreatmentOption updateTreatmentOption(Long id, String treatmentName) {
        TreatmentOption existingOption = treatmentOptionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Option de traitement introuvable"));
        existingOption.setTreatmentName(treatmentName);
        return treatmentOptionRepository.save(existingOption);
    }
}
