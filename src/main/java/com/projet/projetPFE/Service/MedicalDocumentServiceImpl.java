package com.projet.projetPFE.Service;
import com.projet.projetPFE.Entities.MedicalDocument;
import com.projet.projetPFE.Entities.Patient;
import com.projet.projetPFE.Repository.MedicalDocumentRepository;
import com.projet.projetPFE.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@Service
public class MedicalDocumentServiceImpl implements MedicalDocumentService{

    @Autowired
    private MedicalDocumentRepository medicalDocumentRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private FileNetDocumentServiceImpl fileNetDocumentServiceImpl;  // Injecter FileNetService

    @Override
    public String uploadAndSaveToDatabase(MultipartFile file, String title, Long patientId, String type, LocalDate saveDate) throws Exception {
        // Utilisation de la méthode d'upload réelle pour récupérer l'ID du document
        String fileNetId = fileNetDocumentServiceImpl.uploadToFileNet(file, title);

        // Trouver le patient
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        // Création du document médical
        MedicalDocument doc = new MedicalDocument();
        doc.setFileNetId(fileNetId);  // ID du document sur FileNet
        doc.setType(type);            // Type de document
        doc.setPatient(patient);      // Lier le document au patient

        // Concatenation du nom et prénom du patient
        String fullName = patient.getLastName() + " " + patient.getFirstName();
        doc.setPatientName(fullName);  // Enregistrement du nom complet du patient
        doc.setSaveDate(saveDate);
        // Sauvegarder le document médical dans la base de données
        medicalDocumentRepository.save(doc);

        return fileNetId;  // Retourne l'ID du document FileNet
    }


    public List<MedicalDocument> getDocumentsByPatientId(Long patientId) {
        return medicalDocumentRepository.findByPatientId(patientId);
    }
}
