package com.projet.projetPFE.Entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDate;
@Entity
public class MedicalDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate saveDate;
    private String fileNetId; // ID retourné par FileNet
    private String type; // "ordonnance" ou "bilan"

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    private String patientName;

    public LocalDate getSaveDate() {
        return saveDate;
    }

    public void setSaveDate(LocalDate saveDate) {
        this.saveDate = saveDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileNetId() {
        return fileNetId;
    }

    public void setFileNetId(String fileNetId) {
        this.fileNetId = fileNetId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }
}
