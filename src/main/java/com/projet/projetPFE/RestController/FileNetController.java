package com.projet.projetPFE.RestController;

import com.projet.projetPFE.Entities.MedicalDocument;
import com.projet.projetPFE.Service.FileNetDocumentService;
import com.projet.projetPFE.Service.MedicalDocumentService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/filenet")
@CrossOrigin(origins = "*")
public class FileNetController {

    @Autowired
    private MedicalDocumentService medicalDocumentService;

    @Autowired
    private FileNetDocumentService fileNetDocumentService;

    // Endpoint pour upload et enregistrer en BDD
    @PostMapping("/upload")
    public ResponseEntity<Map<String, Object>> uploadDocument(
            @RequestParam("file") MultipartFile file,
            @RequestParam("title") String title,
            @RequestParam("patientId") Long patientId,
            @RequestParam("type") String type,
            @RequestParam("saveDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate saveDate
    ) {
        try {
            // Appel au service combiné : FileNet + DB
            String documentId = medicalDocumentService.uploadAndSaveToDatabase(file, title, patientId, type, saveDate);

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Document uploaded and saved successfully.");

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Upload failed: " + e.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // Télécharger un document
    @GetMapping("/download/{documentId}")
    public void downloadFile(@PathVariable String documentId, HttpServletResponse response) {
        try {
            InputStream inputStream = fileNetDocumentService.downloadDocumentFromFileNet(documentId);
            String mimeType = fileNetDocumentService.getMimeType(documentId);

            if (inputStream == null || mimeType == null) {
                response.sendError(404, "Document non trouvé ou vide");
                return;
            }

            String extension = switch (mimeType) {
                case "application/pdf" -> ".pdf";
                case "image/jpeg" -> ".jpg";
                case "image/png" -> ".png";
                default -> "";
            };

            response.setContentType(mimeType);
            response.setHeader("Content-Disposition", "attachment; filename=" + documentId + extension);

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                response.getOutputStream().write(buffer, 0, bytesRead);
            }

            inputStream.close();
            response.flushBuffer();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                response.sendError(500, "Erreur lors du téléchargement du fichier : " + e.getMessage());
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    // Récupérer les documents d’un patient
    @GetMapping("/documents/patient/{patientId}")
    public ResponseEntity<?> getDocumentsByPatient(@PathVariable Long patientId) {
        try {
            List<MedicalDocument> documents = medicalDocumentService.getDocumentsByPatientId(patientId);
            return ResponseEntity.ok(documents);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la récupération des documents : " + e.getMessage());
        }
    }
}
