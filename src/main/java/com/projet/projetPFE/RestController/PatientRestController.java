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

    @RequestMapping(method = RequestMethod.POST )

    public Patient addPatient (@RequestBody Patient patient){
        return patientService.addPatient(patient);

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
}
