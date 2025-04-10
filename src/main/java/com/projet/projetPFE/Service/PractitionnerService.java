package com.projet.projetPFE.Service;

import com.projet.projetPFE.Entities.Practitionner;

import java.util.List;
import java.util.Optional;

public interface PractitionnerService {
    Practitionner addPractitionner(Practitionner practitionner);
    Practitionner updatePractitionner( Practitionner practitionner);
    void deletePractitionner(Long id);
    List< Practitionner> displayPractitionner();
    Optional< Practitionner> displayPractitionnerbyid(Long id);
    boolean doesPractitionnerExist(String practitionnerLastName, String practitionnerFirstName);

}
