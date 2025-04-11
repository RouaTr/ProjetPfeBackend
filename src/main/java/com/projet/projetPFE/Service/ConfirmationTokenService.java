package com.projet.projetPFE.Service;

import com.projet.projetPFE.Entities.ConfirmationToken;
import com.projet.projetPFE.Entities.Practitionner;

public interface ConfirmationTokenService {


    // Envoie un email de réinitialisation de mot de passe à l'utilisateur.


    void sendResetPasswordEmail(String toEmail, String resetLink);

   // Génère un token de confirmation pour l'utilisateur.

    ConfirmationToken generateConfirmationToken(Practitionner practitionner);
}
