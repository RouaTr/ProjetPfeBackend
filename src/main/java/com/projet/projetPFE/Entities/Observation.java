package com.projet.projetPFE.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "observation")
public class Observation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date observationDate;  // Date de l'observation

    @Column(columnDefinition = "TEXT", nullable = false)
    private String observationDetails;  // Détails de l'observation

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    // Constructors
    public Observation() {}

    public Observation(Patient patient, Date observationDate, String observationDetails) {
        this.patient = patient;
        this.observationDate = observationDate;
        this.observationDetails = observationDetails;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getObservationDate() {
        return observationDate;
    }

    public void setObservationDate(Date observationDate) {
        this.observationDate = observationDate;
    }

    public String getObservationDetails() {
        return observationDetails;
    }

    public void setObservationDetails(String observationDetails) {
        this.observationDetails = observationDetails;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
