package com.projet.projetPFE.RestController;
import com.projet.projetPFE.Service.FileNetDocumentService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/filenet")
@CrossOrigin(origins = "*")
public class FileNetController {

    @Autowired
    private FileNetDocumentService fileNetDocumentService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadDocument(@RequestParam("file") MultipartFile file,
                                                 @RequestParam("title") String title) {
        try {
            String documentId = fileNetDocumentService.uploadToFileNet(file, title);
            return ResponseEntity.ok("Document uploaded with ID: " + documentId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Upload failed: " + e.getMessage());
        }
    }

    // Méthode pour télécharger un fichier
    @GetMapping("/download/{documentId}")
    public void downloadFile(@PathVariable String documentId, HttpServletResponse response) {
        try {
            // Utilisation du service pour récupérer le document
            InputStream inputStream = fileNetDocumentService.downloadDocumentFromFileNet(documentId);

            // Définir le type de contenu pour le PDF
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=" + documentId + ".pdf");

            // Lire le contenu du document et l'écrire dans la réponse HTTP
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                response.getOutputStream().write(buffer, 0, bytesRead);
            }

            inputStream.close();
            response.flushBuffer();
        } catch (Exception e) {
            // Si une erreur se produit, retourner une erreur HTTP
            try {
                response.sendError(500, "Erreur lors du téléchargement du fichier : " + e.getMessage());
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

}
