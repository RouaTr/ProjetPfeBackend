package com.projet.projetPFE.Entities;

import jakarta.persistence.*;

import java.util.Date;
@Entity
public class MedicalTreatment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long treatmentId;
    private  String  treatmentName;
    @Temporal(TemporalType.DATE)
    private Date treatmentStartDate;

    private Double treatment_intake_duration;

    @Temporal(TemporalType.DATE)
    private Date next_intake_Date;
    private Double duration_of_visual_loss;
    private  String  status;
    @Temporal(TemporalType.DATE)
    private Date treatmentRegistrationDate;
    private Boolean delivered;

    public Boolean getDelivered() {
        return delivered;
    }

    public void setDelivered(Boolean delivered) {
        this.delivered = delivered;
    }

    public Date getTreatmentRegistrationDate() {
        return treatmentRegistrationDate;
    }

    public void setTreatmentRegistrationDate(Date treatmentRegistrationDate) {
        this.treatmentRegistrationDate = treatmentRegistrationDate;
    }

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;
    public Long getTreatmentId() {
        return treatmentId;
    }

    public void setTreatmentId(Long treatmentId) {
        this.treatmentId = treatmentId;
    }

    public String getTreatmentName() {
        return treatmentName;
    }

    public void setTreatmentName(String treatmentName) {
        this.treatmentName = treatmentName;
    }

    public Date getTreatmentStartDate() {
        return treatmentStartDate;
    }

    public void setTreatmentStartDate(Date treatmentStartDate) {
        this.treatmentStartDate = treatmentStartDate;
    }

    public Double getTreatment_intake_duration() {
        return treatment_intake_duration;
    }

    public void setTreatment_intake_duration(Double treatment_intake_duration) {
        this.treatment_intake_duration = treatment_intake_duration;
    }

    public Date getNext_intake_Date() {
        return next_intake_Date;
    }

    public void setNext_intake_Date(Date next_intake_Date) {
        this.next_intake_Date = next_intake_Date;
    }

    public Double getDuration_of_visual_loss() {
        return duration_of_visual_loss;
    }

    public void setDuration_of_visual_loss(Double duration_of_visual_loss) {
        this.duration_of_visual_loss = duration_of_visual_loss;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }


}
