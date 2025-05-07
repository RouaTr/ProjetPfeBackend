package com.projet.projetPFE.config;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.util.Date;

public class TreatmentPredictionDTO {

    private String medicalRecordNumber;
    private Double viralLoad;
    private Double cd4Count;
    private String treatmentName;
    private Boolean effective;
    private String suggestedTreatment;
    @Temporal(TemporalType.DATE)
    private Date labDate;
    // Constructeur sans argument pour Hibernate
    public TreatmentPredictionDTO() {
    }

    // Constructeur avec arguments pour l'utilisation de HQL
    public TreatmentPredictionDTO(String medicalRecordNumber, Double viralLoad, Double cd4Count, String treatmentName, Boolean effective) {
        this.medicalRecordNumber = medicalRecordNumber;
        this.viralLoad = viralLoad;
        this.cd4Count = cd4Count;
        this.treatmentName = treatmentName;
        this.effective = effective;
    }

    // Getters et setters

    public String getMedicalRecordNumber() {
        return medicalRecordNumber;
    }

    public void setMedicalRecordNumber(String medicalRecordNumber) {
        this.medicalRecordNumber = medicalRecordNumber;
    }

    public Double getViralLoad() {
        return viralLoad;
    }

    public void setViralLoad(Double viralLoad) {
        this.viralLoad = viralLoad != null ? viralLoad : 0.0; // Vérification lors du setter
    }

    public Double getCd4Count() {
        return cd4Count;
    }

    public void setCd4Count(Double cd4Count) {
        this.cd4Count = cd4Count != null ? cd4Count : 0.0; // Vérification lors du setter
    }

    public String getTreatmentName() {
        return treatmentName;
    }

    public void setTreatmentName(String treatmentName) {
        this.treatmentName = treatmentName;
    }

    public Boolean getEffective() {
        return effective;
    }

    public void setEffective(Boolean effective) {
        this.effective = effective != null ? effective : false; // Si null, on attribue false
    }

    public String getSuggestedTreatment() {
        return suggestedTreatment;
    }

    public void setSuggestedTreatment(String suggestedTreatment) {
        this.suggestedTreatment = suggestedTreatment;
    }

    public Date getLabDate() {
        return labDate;
    }

    public void setLabDate(Date labDate) {
        this.labDate = labDate;
    }
}
