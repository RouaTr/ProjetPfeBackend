package com.projet.projetPFE.config;

import com.projet.projetPFE.Service.PatientImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Component
public class PatientDataLoader implements CommandLineRunner {

    @Autowired
    private PatientImportService patientCSVImporter;

    @Override
    public void run(String... args) throws Exception {
        ClassPathResource resource = new ClassPathResource("data/patient_corrige_dates_fiables.csv");
        try (InputStream inputStream = resource.getInputStream()) {
            patientCSVImporter.importCSV(inputStream);
            System.out.println("✅ Données patients importées avec succès !");
        } catch (Exception e) {
            System.err.println("❌ Erreur lors de l'importation des patients : " + e.getMessage());
        }
    }
}