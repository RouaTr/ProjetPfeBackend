package com.projet.projetPFE.Service;


import com.projet.projetPFE.Entities.Patient;
import com.projet.projetPFE.Repository.PatientRepository;
import org.apache.commons.csv.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;


@Service
public class PatientImportService {

    @Autowired
    private PatientRepository patientRepository;

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public void importCSV(InputStream inputStream) throws Exception {
        Reader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        Iterable<CSVRecord> records = CSVFormat.DEFAULT
                .withFirstRecordAsHeader()
                .parse(reader);

        for (CSVRecord record : records) {
            Patient patient = new Patient();

            patient.setMedicalRecordNumber(record.get("medicalRecordNumber"));
            patient.setLastName(record.get("lastName"));
            patient.setFirstName(record.get("firstName"));
            patient.setGender(record.get("gender"));

            // Champs numériques (vérifie qu’ils sont bien parsés)
            patient.setAge(parseDouble(record.get("age")));

            // Champs dates
            patient.setBirthDate(parseDate(record.get("birthDate")));
            patient.setPositiveHIVDate(parseDate(record.get("positiveHIVDate")));

            patientRepository.save(patient);
        }
    }

    private Date parseDate(String input) {
        try {
            return dateFormat.parse(input);
        } catch (Exception e) {
            return null;
        }
    }

    private Double parseDouble(String input) {
        try {
            return Double.parseDouble(input);
        } catch (Exception e) {
            return null;
        }
    }

    private Integer parseInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (Exception e) {
            return null;
        }
    }

    private Boolean parseBoolean(String input) {
        return "true".equalsIgnoreCase(input) || "1".equals(input);
    }
}