package com.projet.projetPFE.Service;

import com.projet.projetPFE.Entities.Patient;
import com.projet.projetPFE.Entities.Practitionner;
import com.projet.projetPFE.Repository.PatientRepository;
import com.projet.projetPFE.Repository.PractitionnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PractitionnerRepository practitionnerRepository;

    @Override
    public Patient addPatient(Patient patient) {
        return patientRepository.save(patient);
    }
    @Override
    public Patient updatePatient(Long id, Patient patient, String practitionnerEmail) {
        // Récupérer le patient existant
        Optional<Patient> existingPatientOpt = patientRepository.findById(id);

        if (existingPatientOpt.isPresent()) {
            Patient existingPatient = existingPatientOpt.get();

            // Vérifier si le médecin traitant n'a pas changé
            if (patient.getPractitionner() != null && existingPatient.getPractitionner() != null) {
                if (!patient.getPractitionner().getPractitionnerEmail().equals(existingPatient.getPractitionner().getPractitionnerEmail())) {
                    // Si ce n'est pas le médecin du praticien connecté, on ne change pas le médecin
                    if (!existingPatient.getPractitionner().getPractitionnerEmail().equals(practitionnerEmail)) {
                        // Si le médecin traitant n'est pas celui du praticien connecté, on ne change rien
                        patient.setPractitionner(existingPatient.getPractitionner());
                    }
                }
            }

            // Mettre à jour les autres champs
            existingPatient.setLastName(patient.getLastName());
            existingPatient.setFirstName(patient.getFirstName());
            // Ajouter ici toutes les autres propriétés à mettre à jour...

            // Sauvegarder le patient mis à jour
            return patientRepository.save(existingPatient);
        }

        // Si le patient n'existe pas, retourner null ou lancer une exception
        return null;
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

    // Méthode qui recherche les patients en fonction du praticien
    @Override
    public List<Patient> getPatientsByPractitionner(Practitionner practitioner) {
        return patientRepository.findByPractitionner(practitioner); // Recherche des patients associés au praticien
    }

    // Méthode qui recherche les patients en fonction de l'email du praticien
    @Override
    public List<Patient> getPatientByPractitionner(String practitionnerEmail) {
        // Récupérer le praticien par son email
        Practitionner practitioner = practitionnerRepository.findPractitionnerByPractitionnerEmail(practitionnerEmail);

        // Vérifier si le praticien existe
        if (practitioner != null) {
            System.out.println("Recherche des patients pour l'email : " + practitionnerEmail); // Log pour vérifier l'email reçu
            return patientRepository.findByPractitionnerId(practitioner.getId()); // Retourner les patients associés au praticien
        }
        // Retourner une liste vide si le praticien n'est pas trouvé
        return new ArrayList<>();
    }

}
