package com.projet.projetPFE.Repository;

import com.projet.projetPFE.Entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository <Patient,Long>{
    boolean existsByLastNameAndFirstName(String lastName, String firstName);



}
