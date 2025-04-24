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

            // Vérifier si le médecin traitant a changé
            if (patient.getPractitionner() != null && existingPatient.getPractitionner() != null) {
                if (!patient.getPractitionner().getPractitionnerEmail().equals(existingPatient.getPractitionner().getPractitionnerEmail())) {
                    // Si le médecin traitant n'est pas celui du praticien connecté, on ne change pas le médecin
                    if (!existingPatient.getPractitionner().getPractitionnerEmail().equals(practitionnerEmail)) {
                        // Ne pas changer le médecin du patient
                        patient.setPractitionner(existingPatient.getPractitionner());
                    }
                }
            }

            // Mettre à jour tous les champs du patient même si certains ne sont pas fournis
            if (patient.getLastName() != null) existingPatient.setLastName(patient.getLastName());
            if (patient.getFirstName() != null) existingPatient.setFirstName(patient.getFirstName());
            if (patient.getBirthDate() != null) existingPatient.setBirthDate(patient.getBirthDate());
            if (patient.getAge() != null) existingPatient.setAge(patient.getAge());
            if (patient.getPhoneNumber() != null) existingPatient.setPhoneNumber(patient.getPhoneNumber());
            if (patient.getCity() != null) existingPatient.setCity(patient.getCity());
            if (patient.getRegion() != null) existingPatient.setRegion(patient.getRegion());
            if (patient.getPostalCode() != null) existingPatient.setPostalCode(patient.getPostalCode());
            if (patient.getAddress() != null) existingPatient.setAddress(patient.getAddress());
            if (patient.getNationality() != null) existingPatient.setNationality(patient.getNationality());
            if (patient.getWeight() != null) existingPatient.setWeight(patient.getWeight());
            if (patient.getHeight() != null) existingPatient.setHeight(patient.getHeight());
            if (patient.getMaritalStatus() != null) existingPatient.setMaritalStatus(patient.getMaritalStatus());
            if (patient.getChildren() != null) existingPatient.setChildren(patient.getChildren());
            if (patient.getHousing() != null) existingPatient.setHousing(patient.getHousing());
            if (patient.getHousingType() != null) existingPatient.setHousingType(patient.getHousingType());
            if (patient.getEducationLevel() != null) existingPatient.setEducationLevel(patient.getEducationLevel());
            if (patient.getSmoking() != null) existingPatient.setSmoking(patient.getSmoking());
            if (patient.getAlcohol() != null) existingPatient.setAlcohol(patient.getAlcohol());
            if (patient.getDrugUse() != null) existingPatient.setDrugUse(patient.getDrugUse());
            if (patient.getPhysicalActivity() != null) existingPatient.setPhysicalActivity(patient.getPhysicalActivity());
            if (patient.getBodyTemperature() != null) existingPatient.setBodyTemperature(patient.getBodyTemperature());
            if (patient.getHeartRate() != null) existingPatient.setHeartRate(patient.getHeartRate());
            if (patient.getBloodPressure() != null) existingPatient.setBloodPressure(patient.getBloodPressure());
            if (patient.getContaminationMode() != null) existingPatient.setContaminationMode(patient.getContaminationMode());
            if (patient.getInitialScreeningType() != null) existingPatient.setInitialScreeningType(patient.getInitialScreeningType());
            if (patient.getInitialScreeningReason() != null) existingPatient.setInitialScreeningReason(patient.getInitialScreeningReason());
            if (patient.getLastNegativeDate() != null) existingPatient.setLastNegativeDate(patient.getLastNegativeDate());
            if (patient.getPositiveHIVDate() != null) existingPatient.setPositiveHIVDate(patient.getPositiveHIVDate());
            if (patient.getHlaB5701Typing() != null) existingPatient.setHlaB5701Typing(patient.getHlaB5701Typing());
            if (patient.getScreeningCircumstance() != null) existingPatient.setScreeningCircumstance(patient.getScreeningCircumstance());
            if (patient.getViralType() != null) existingPatient.setViralType(patient.getViralType());
            if (patient.getContaminationDate() != null) existingPatient.setContaminationDate(patient.getContaminationDate());
            if (patient.getCdcStage() != null) existingPatient.setCdcStage(patient.getCdcStage());

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
