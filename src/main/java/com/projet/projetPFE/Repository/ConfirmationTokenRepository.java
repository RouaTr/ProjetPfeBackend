package com.projet.projetPFE.Repository;

import com.projet.projetPFE.Entities.ConfirmationToken;
import com.projet.projetPFE.Entities.Practitionner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {
    ConfirmationToken findByConfirmationToken(String confirmationToken);
    ConfirmationToken findByPractitionner(Practitionner practitionner);
}

