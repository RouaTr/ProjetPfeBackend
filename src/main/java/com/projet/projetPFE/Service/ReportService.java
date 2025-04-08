package com.projet.projetPFE.Service;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Service
public class ReportService {

    @Autowired
    private DataSource dataSource;

    public JasperPrint generateOrdonnanceReport(Long patientId) throws JRException {
        JasperPrint jasperPrint = null;
        try {
            // Charger le fichier JRXML
            JasperReport jasperReport = JasperCompileManager.compileReport("src/main/resources/Ordonnance.jrxml");

            // Paramètres pour la requête, y compris l'ID du patient
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("patientId", patientId);

            // Créer un JasperPrint à partir de la source de données
            jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource.getConnection());

        } catch (SQLException e) {
            // Gestion de l'exception SQL, vous pouvez aussi enregistrer l'erreur ou réagir en fonction du besoin
            e.printStackTrace(); // Exemple de gestion d'erreur
            throw new JRException("Error while generating the report", e); // Provoque un JRException
        }

        return jasperPrint;
    }


    public byte[] exportReportToPdf(JasperPrint jasperPrint) throws JRException {
        // Exporter le rapport en format PDF
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }
}