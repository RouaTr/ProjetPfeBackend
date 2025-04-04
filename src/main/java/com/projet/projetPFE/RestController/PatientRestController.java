package com.projet.projetPFE.RestController;

import com.projet.projetPFE.Entities.Patient;
import com.projet.projetPFE.Service.PatientService;
import com.projet.projetPFE.Repository.PatientRepository;
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

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Patient> addPatient(@RequestBody Patient patient) {
        Patient savedPatient = patientService.addPatient(patient);

        // Vérifiez que l'ID est bien généré
        if (savedPatient.getId() != null) {
            return new ResponseEntity<>(savedPatient, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);  // Si l'ID n'est pas généré, renvoyer une erreur
        }
    }



    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Patient> updatePatient(@PathVariable("id") Long id, @RequestBody Patient patient) {
        Patient updatedPatient = patientService.updatePatient(id, patient);
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
    @GetMapping("/exists")
    public ResponseEntity<Boolean> doesPatientExist(@RequestParam String lastName, @RequestParam String firstName) {
        boolean exists = patientService.doesPatientExist(lastName, firstName);
        return ResponseEntity.ok(exists);
    }
}
