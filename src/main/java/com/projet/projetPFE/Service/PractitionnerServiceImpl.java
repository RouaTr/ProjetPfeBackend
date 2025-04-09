package com.projet.projetPFE.Service;

import com.projet.projetPFE.Entities.Practitionner;
import com.projet.projetPFE.Repository.PractitionnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PractitionnerServiceImpl implements PractitionnerService{
    @Autowired
    PractitionnerRepository practitionnerRepository;
    @Override
    public Practitionner addPractitionner(Practitionner practitionner) {
        return practitionnerRepository.save(practitionner);
    }

    @Override
    public Practitionner updatePractitionner(Practitionner practitionner) {
        return practitionnerRepository.save(practitionner);
    }

    @Override
    public void deletePractitionner(Long id) {
        practitionnerRepository.deleteById(id);
    }

    @Override
    public List<Practitionner> displayPractitionner() {
        return practitionnerRepository.findAll();
    }

    @Override
    public Optional<Practitionner> displayPractitionnerbyid(Long id) {
        return practitionnerRepository.findById(id);
    }
}
