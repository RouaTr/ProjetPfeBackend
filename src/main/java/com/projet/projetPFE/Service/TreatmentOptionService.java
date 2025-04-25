package com.projet.projetPFE.Service;

import com.projet.projetPFE.Entities.TreatmentOption;

import java.util.List;

public interface TreatmentOptionService {
    List<TreatmentOption> getAllTreatmentOptions();

    TreatmentOption addTreatmentOption(String treatmentName);

    void deleteTreatmentOption(Long id);

    TreatmentOption updateTreatmentOption(Long id, String treatmentName);
}
