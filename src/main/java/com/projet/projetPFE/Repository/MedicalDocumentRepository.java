package com.projet.projetPFE.Repository;

import com.projet.projetPFE.Entities.MedicalDocument;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MedicalDocumentRepository extends JpaRepository<MedicalDocument, Long> {
    List<MedicalDocument> findByPatientId(Long patientId);


}
