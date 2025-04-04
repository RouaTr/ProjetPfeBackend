package com.projet.projetPFE.Service;

import com.projet.projetPFE.Entities.Patient;
import com.projet.projetPFE.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class PatientServiceImpl implements PatientService{
    @Autowired
    PatientRepository patientRepository;
    @Override
    public Patient addPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public Patient updatePatient(Long id ,Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public List<Patient> displayPatient() {
        return patientRepository.findAll();
    }

    @Override
    public Optional<Patient> displayPatientById(Long id) {
        return patientRepository.findById(id);
    }
    @Override
    public boolean doesPatientExist(String lastName, String firstName) {
        return patientRepository.existsByLastNameAndFirstName(lastName, firstName);
    }


}
