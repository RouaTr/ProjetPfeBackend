package com.projet.projetPFE.config;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.util.Date;

public class TrendDTO {
    @Temporal(TemporalType.DATE)
    private Date medicalTestDate;
    private Double cd4Count;  // Modifier en Double
    private Double viralLoad; // Modifier en Double
    private String treatmentName;
    @Temporal(TemporalType.DATE)
    private Date treatmentStartDate;
    @Temporal(TemporalType.DATE)
    private Date next_intake_Date;

    // Constructeur avec 6 paramètres
    public TrendDTO(Date medicalTestDate, Double cd4Count, Double viralLoad,
                    Date treatmentStartDate, Date next_intake_Date, String treatmentName) {
        this.medicalTestDate = medicalTestDate;
        this.cd4Count = cd4Count;
        this.viralLoad = viralLoad;
        this.treatmentStartDate = treatmentStartDate;
        this.next_intake_Date = next_intake_Date;
        this.treatmentName = treatmentName;
    }

    // Getters et setters
    public Date getNext_intake_Date() {
        return next_intake_Date;
    }

    public void setNext_intake_Date(Date next_intake_Date) {
        this.next_intake_Date = next_intake_Date;
    }

    public Date getTreatmentStartDate() {
        return treatmentStartDate;
    }

    public void setTreatmentStartDate(Date treatmentStartDate) {
        this.treatmentStartDate = treatmentStartDate;
    }

    public Date getMedicalTestDate() {
        return medicalTestDate;
    }

    public void setMedicalTestDate(Date medicalTestDate) {
        this.medicalTestDate = medicalTestDate;
    }

    public Double getCd4Count() {
        return cd4Count;
    }

    public void setCd4Count(Double cd4Count) {
        this.cd4Count = cd4Count;
    }

    public Double getViralLoad() {
        return viralLoad;
    }

    public void setViralLoad(Double viralLoad) {
        this.viralLoad = viralLoad;
    }

    public String getTreatmentName() {
        return treatmentName;
    }

    public void setTreatmentName(String treatmentName) {
        this.treatmentName = treatmentName;
    }
}