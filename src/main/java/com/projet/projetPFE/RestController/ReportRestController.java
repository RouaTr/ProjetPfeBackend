package com.projet.projetPFE.RestController;




import com.projet.projetPFE.Service.ReportService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
@CrossOrigin("*")
@RestController
@RequestMapping("/report")
public class ReportRestController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/ordonnance/{patientId}")
    public ResponseEntity<byte[]> generateOrdonnance(@PathVariable Long patientId) {
        try {
            // Générer le rapport pour l'ordonnance
            var jasperPrint = reportService.generateOrdonnanceReport(patientId);
            var pdfBytes = reportService.exportReportToPdf(jasperPrint);

            return ResponseEntity.ok()
                    .header("Content-Type", "application/pdf")
                    .header("Content-Disposition", "inline; filename=ordonnance.pdf")
                    .body(pdfBytes);
        } catch (JRException e) {
            e.printStackTrace(); // Afficher l'exception dans la console
            String errorMessage = "Erreur serveur lors de la génération du rapport: " + e.getMessage();
            return ResponseEntity.status(500)
                    .body(errorMessage.getBytes(StandardCharsets.UTF_8));
        }
    }



}
