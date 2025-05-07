package com.projet.projetPFE.RestController;
import com.projet.projetPFE.Repository.PatientRepository;
import org.springframework.ui.Model;

import com.projet.projetPFE.Entities.ConfirmationToken;
import com.projet.projetPFE.Entities.Patient;
import com.projet.projetPFE.Entities.Practitionner;
import com.projet.projetPFE.Repository.ConfirmationTokenRepository;
import com.projet.projetPFE.Repository.PractitionnerRepository;
import com.projet.projetPFE.Service.ConfirmationTokenService;
import com.projet.projetPFE.Service.PractitionnerService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping(value="/practitionner")
public class PractitionnerRestController {
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private ConfirmationTokenService confirmationTokenService;

    @Autowired

    PractitionnerRepository practitionnerRepository;

    @Autowired
    PractitionnerService practitionnerService;
    @RequestMapping(method = RequestMethod.POST )
    ResponseEntity<?> addPractitionner(@RequestBody Practitionner practitionner){
        HashMap<String, Object> response = new HashMap<>();
        if(practitionnerRepository.existsByPractitionnerEmail(practitionner.getPractitionnerEmail())){
            response.put("message", "email exist deja !");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }else{
            practitionner.setPassword(this.bCryptPasswordEncoder.encode(practitionner.getPassword()));
            Practitionner savedUser = practitionnerRepository.save(practitionner);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);}

    }
    @RequestMapping(value = "/{id}" ,method = RequestMethod.PUT)
    public Practitionner updatePractitionner(@PathVariable("id")Long id, @RequestBody Practitionner practitionner){
        practitionner.setPassword(this.bCryptPasswordEncoder.encode(practitionner.getPassword()));
        Practitionner savedUser = practitionnerRepository.save(practitionner);

        Practitionner newPractitionner = practitionnerService.updatePractitionner(practitionner);
        return newPractitionner;
    }

    @RequestMapping(method = RequestMethod.GET )
    public List<Practitionner> diplayPractitionner(){
        return practitionnerService.displayPractitionner();

    }


    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> loginPractitionner(@RequestBody Practitionner practitionner) {
        System.out.println("In login-practitionner with: " + practitionner);
        HashMap<String, Object> response = new HashMap<>();

        // Récupération de l'utilisateur depuis la base de données par son email
        Practitionner userFromDB = practitionnerRepository.findPractitionnerByPractitionnerEmail(practitionner.getPractitionnerEmail());
        System.out.println("User from DB: " + userFromDB);

        // Vérification si l'utilisateur existe
        if (userFromDB == null) {
            response.put("message", "Practitionner not found !");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } else {
            // Vérifier si le compte est activé
            if (!"active".equals(userFromDB.getStatus())) {
                response.put("message", "Compte en attente d'activation");
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
            }

            // Comparaison des mots de passe
            boolean compare = this.bCryptPasswordEncoder.matches(practitionner.getPassword(), userFromDB.getPassword());
            System.out.println("Password comparison result: " + compare);

            if (!compare) {
                response.put("message", "Password incorrect!");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            } else {
                // Récupération du rôle de l'utilisateur
                String practitionnerRole = userFromDB.getPractitionnerRole(); // "medecin", "pharmacien", "admin"
                Long practitionnerId = userFromDB.getId(); // ID de l'utilisateur

                // Création du token JWT
                String token = Jwts.builder()
                        .claim("practitionnerRole", practitionnerRole)
                        .claim("id", practitionnerId)
                        .claim("practitionnerFirstName", userFromDB.getPractitionnerFirstName())
                        .claim("practitionnerLastName", userFromDB.getPractitionnerLastName())
                        .claim("practitionnerEmail", userFromDB.getPractitionnerEmail())
                        .signWith(SignatureAlgorithm.HS256, "SECRET")
                        .compact();

                // Ajout du token, du rôle et de l'ID dans la réponse
                response.put("token", token);
                response.put("practitionnerRole", practitionnerRole);
                response.put("id", practitionnerId);

                // Logique basée sur le rôle
                if ("admin".equals(practitionnerRole)) {
                    response.put("message", "Welcome Admin");
                } else if ("medecin".equals(practitionnerRole)) {
                    response.put("message", "Welcome Doctor");
                } else if ("pharmacien".equals(practitionnerRole)) {
                    response.put("message", "Welcome Pharmacist");
                }

                // Réponse avec statut OK et informations sur l'utilisateur
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }
        }
    }


    @GetMapping("/exists")
    public ResponseEntity<Boolean> doesPatientExist(@RequestParam String practitionnerLastName, @RequestParam String practitionnerFirstName) {
        boolean exists = practitionnerService.doesPractitionnerExist(practitionnerLastName, practitionnerFirstName);
        return ResponseEntity.ok(exists);
    }
    @RequestMapping(value = "/{id}" , method = RequestMethod.GET)
    public Optional<Practitionner> getPractitionnerById(@PathVariable("id") Long id){

        Optional<Practitionner> practitionner = practitionnerService.displayPractitionnerbyid(id);
        return practitionner;
    }
    @PostMapping("/forgot-password")
    @CrossOrigin("*")
    public ResponseEntity<?> forgotPassword(@RequestBody Map<String, String> payload) {
        String email = payload.get("email");
        Practitionner user = practitionnerRepository.findPractitionnerByPractitionnerEmail(email);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Utilisateur non trouvé.");
        }

        // Vérifie si un token existe déjà
        ConfirmationToken token = confirmationTokenRepository.findByPractitionner(user);
        if (token == null) {
            // Si aucun token, on en crée un nouveau
            token = confirmationTokenService.generateConfirmationToken(user);
            confirmationTokenRepository.save(token);
        }

        // Envoie du lien de réinitialisation
        String resetUrl = "http://localhost:4200/reset-password?token=" + token.getConfirmationToken();
        confirmationTokenService.sendResetPasswordEmail(email, resetUrl);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Lien de réinitialisation envoyé.");
        return ResponseEntity.ok(response);
    }


    @PostMapping("/reset-password")
    @CrossOrigin("*")
    public ResponseEntity<?> resetPassword(@RequestParam String token, @RequestParam String newPassword) {
        ConfirmationToken confirmationToken = confirmationTokenRepository.findByConfirmationToken(token);

        if (confirmationToken == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Token invalide.");
        }

        Practitionner user = confirmationToken.getPractitionner();
        user.setPassword(this.bCryptPasswordEncoder.encode(newPassword));
        practitionnerRepository.save(user);



        Map<String, String> response = new HashMap<>();
        response.put("message", "Mot de passe réinitialisé avec succès.");
        return ResponseEntity.ok(response);
    }
    @PutMapping("/{id}/role")
    public ResponseEntity<?> updatePractitionnerRole(@PathVariable Long id, @RequestParam String newRole) {
        try {
            Practitionner updated = practitionnerService.updatePractitionnerRole(id, newRole);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Practicien introuvable.");
        }
    }
    @PutMapping("/{id}/validate")
    public ResponseEntity<?> validatePractitionner(@PathVariable("id") Long id, @RequestParam String newRole) {
        Optional<Practitionner> practitionner = practitionnerService.displayPractitionnerbyid(id);
        if (practitionner.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Praticien non trouvé");
        }

        Practitionner p = practitionner.get();
        p.setPractitionnerRole(newRole);  // Attribution du rôle
        p.setStatus("active");  // Activation du compte

        practitionnerService.updatePractitionner(p);  // Mise à jour du praticien
        return ResponseEntity.status(HttpStatus.OK).body("Praticien activé avec rôle : " + newRole);
    }








}