package com.projet.projetPFE.Service;
import com.projet.projetPFE.Entities.Laboratory;
import com.projet.projetPFE.Entities.MedicalDocument;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

public interface MedicalDocumentService {
    String uploadAndSaveToDatabase(MultipartFile file, String title, Long patientId, String type , LocalDate saveDate) throws Exception;
    List<MedicalDocument> getDocumentsByPatientId(Long patientId);
}
