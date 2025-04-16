package com.projet.projetPFE.RestController;

import com.projet.projetPFE.Service.PatientImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

public class PatientImportController {
    @Autowired
    private PatientImportService patientCSVImporter;
    @PostMapping("/import-patients")
    public ResponseEntity<String> uploadCSV(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Fichier vide !");
        }

        try {
            patientCSVImporter.importCSV(file.getInputStream());
            return ResponseEntity.ok("Importation réussie !");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de l'importation !");
        }
    }

}
