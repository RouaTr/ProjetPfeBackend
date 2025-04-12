package com.projet.projetPFE.Repository;

import com.projet.projetPFE.Entities.Patient;
import com.projet.projetPFE.Entities.Practitionner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientRepository extends JpaRepository <Patient,Long>{
    boolean existsByLastNameAndFirstName(String lastName, String firstName);
    List<Patient> findByPractitionner(Practitionner practitioner);

    List<Patient> findByPractitionnerId(Long id);
}
