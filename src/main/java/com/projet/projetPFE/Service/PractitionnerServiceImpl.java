package com.projet.projetPFE.Service;

import com.projet.projetPFE.Entities.Patient;
import com.projet.projetPFE.Entities.Practitionner;
import com.projet.projetPFE.Repository.PatientRepository;
import com.projet.projetPFE.Repository.PractitionnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PractitionnerServiceImpl implements PractitionnerService {

    @Autowired
    PractitionnerRepository practitionnerRepository;

    @Autowired
    PatientRepository patientRepository;

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

    @Override
    public boolean doesPractitionnerExist(String practitionnerLastName, String practitionnerFirstName) {
        return practitionnerRepository.existsByPractitionnerLastNameAndPractitionnerFirstName(practitionnerLastName, practitionnerFirstName);
    }

    @Override
    public Practitionner updatePractitionnerRole(Long id, String newRole) {
        Optional<Practitionner> optionalPractitionner = practitionnerRepository.findById(id);
        if (optionalPractitionner.isPresent()) {
            Practitionner p = optionalPractitionner.get();
            p.setPractitionnerRole(newRole);
            return practitionnerRepository.save(p);
        } else {
            throw new RuntimeException("Practitionner not found with id " + id);
        }
    }

    @Override
    public Practitionner findPractitionnerByPractitionnerEmail(String practitionnerEmail) {
        // Implémentation pour récupérer un praticien par son email
        return practitionnerRepository.findPractitionnerByPractitionnerEmail(practitionnerEmail);
    }

    @Override
    public List<Patient> getPatientsByPractitionner(Practitionner practitioner) {
        return patientRepository.findByPractitionner(practitioner); // Recherche des patients associés à un praticien
    }
}
