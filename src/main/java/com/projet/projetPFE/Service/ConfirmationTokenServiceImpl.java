package com.projet.projetPFE.Service;

import com.projet.projetPFE.Entities.ConfirmationToken;
import com.projet.projetPFE.Entities.Practitionner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
@Service
@Slf4j
public class ConfirmationTokenServiceImpl implements ConfirmationTokenService{
    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendResetPasswordEmail(String toEmail, String resetLink) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Réinitialisation de votre mot de passe");
        message.setText("Bonjour,\n\nCliquez sur ce lien pour réinitialiser votre mot de passe : \n" + resetLink);

        mailSender.send(message);
    }

    @Override
    public ConfirmationToken generateConfirmationToken(Practitionner practitionner) {
        return new ConfirmationToken(practitionner);
    }
}
