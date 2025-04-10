package com.projet.projetPFE.Repository;

import com.projet.projetPFE.Entities.Practitionner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PractitionnerRepository extends JpaRepository<Practitionner,Long> {
   Practitionner findPractitionnerByPractitionnerEmail(String practitionnerEmail);

    boolean existsByPractitionnerEmail(String practitionnerEmail);
    boolean existsByPractitionnerLastNameAndPractitionnerFirstName(String practitionnerLastName, String practitionnerFirstName);

}
