package com.projet.projetPFE.RestController;

import com.projet.projetPFE.Entities.Patient;
import com.projet.projetPFE.Entities.Practitionner;
import com.projet.projetPFE.Service.PatientService;
import com.projet.projetPFE.Repository.PatientRepository;
import com.projet.projetPFE.Service.PractitionnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin("*")
@RestController
@RequestMapping("/patients")
public class PatientRestController {

    @Autowired
    private PatientService patientService;
    @Autowired
    private PractitionnerService practitionnerService;
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Patient> addPatient(@RequestBody Patient patient, @RequestParam String practitionnerEmail) {
        // Utiliser la méthode findPractitionnerByEmail du service pour récupérer le praticien par email
        Practitionner practitionner = practitionnerService.findPractitionnerByPractitionnerEmail(practitionnerEmail);

        // Vérifier si le praticien existe
        if (practitionner == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);  // Si le praticien n'est pas trouvé, retourner une erreur
        }

        // Assigner le praticien au patient
        patient.setPractitionner(practitionner);

        // Sauvegarder le patient
        Patient savedPatient = patientService.addPatient(patient);

        // Vérifiez que l'ID est bien généré
        if (savedPatient.getId() != null) {
            return new ResponseEntity<>(savedPatient, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);  // Si l'ID n'est pas généré, renvoyer une erreur
        }
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Patient> updatePatient(
            @PathVariable("id") Long id,
            @RequestBody Patient patient,
            @RequestParam String practitionnerEmail) {
        // Recherche du praticien par son email
        Practitionner practitionner = practitionnerService.findPractitionnerByPractitionnerEmail(practitionnerEmail);

        // Vérification si le praticien existe
        if (practitionner == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }

        // Ajout du praticien à l'objet patient
        patient.setPractitionner(practitionner);

        // Mise à jour du patient via le service
        Patient updatedPatient = patientService.updatePatient(id, patient, practitionnerEmail);

        // Retourner la réponse avec le patient mis à jour
        return ResponseEntity.ok(updatedPatient);
    }




    @RequestMapping(method = RequestMethod.GET)
    public List<Patient> displayPatients() {
        return patientService.displayPatient();
    }




    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Optional<Patient> displayPatientById(@PathVariable("id") Long id) {
        return patientService.displayPatientById(id);
    }

    @RequestMapping(value = "/by-practitionner", method = RequestMethod.GET)
    public ResponseEntity<List<Patient>> getPatientsByPractitionner(@RequestParam String practitionnerEmail) {
        System.out.println("Email du praticien reçu : " + practitionnerEmail); // Log pour vérifier l'email reçu

        // Vérifier si l'email est fourni
        if (practitionnerEmail == null || practitionnerEmail.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Si l'email est manquant, retourner une erreur
        }

        // Récupérer le praticien par email
        Practitionner practitioner = practitionnerService.findPractitionnerByPractitionnerEmail(practitionnerEmail);

        // Vérifier si le praticien existe
        if (practitioner == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Si aucun praticien trouvé, retourner une erreur
        }

        // Récupérer les patients associés à ce praticien
        List<Patient> patients = patientService.getPatientsByPractitionner(practitioner);

        // Retourner les patients ou une erreur si aucune donnée trouvée
        if (patients.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Si aucun patient n'est trouvé, retourner un statut 204
        }

        return ResponseEntity.ok(patients); // Retourner la liste des patients associés
    }
    @GetMapping("/exists")
    public ResponseEntity<Boolean> doesPatientExist(@RequestParam String lastName, @RequestParam String firstName) {
        boolean exists = patientService.doesPatientExist(lastName, firstName);
        return ResponseEntity.ok(exists);
    }



}
