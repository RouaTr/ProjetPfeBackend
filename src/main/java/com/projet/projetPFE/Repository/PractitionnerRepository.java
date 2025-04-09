package com.projet.projetPFE.Repository;

import com.projet.projetPFE.Entities.Practitionner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PractitionnerRepository extends JpaRepository<Practitionner,Long> {
    /*Practitionner findPractitionnerByEmail(String practitionnerEmail);

    boolean existsByPractitionnerEmail(String practitionnerEmail);*/
}
