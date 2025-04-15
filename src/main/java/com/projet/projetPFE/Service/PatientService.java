package com.projet.projetPFE.Service;

import com.projet.projetPFE.Entities.Patient;
import com.projet.projetPFE.Entities.Practitionner;

import java.util.List;
import java.util.Optional;

public interface PatientService {
    Patient addPatient(Patient patient);
    Patient updatePatient(Long id, Patient patient, String practitionnerEmail);
    List<Patient> displayPatient();
    Optional<Patient> displayPatientById(Long id);
    boolean doesPatientExist(String lastName, String firstName);

    List<Patient> getPatientsByPractitionner(Practitionner practitioner);
    List<Patient> getPatientByPractitionner(String practitionerEmail);

}
