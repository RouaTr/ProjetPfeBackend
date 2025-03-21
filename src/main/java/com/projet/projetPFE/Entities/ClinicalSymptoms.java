package com.projet.projetPFE.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "clinical_symptoms")
public class ClinicalSymptoms {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String generalSigns;

    @Column(columnDefinition = "TEXT")
    private String dermatological;

    @Column(columnDefinition = "TEXT")
    private String neuroMuscular;

    @Column(columnDefinition = "TEXT")
    private String ent; // Otorhinolaryngology (ENT)

    @Column(columnDefinition = "TEXT")
    private String psychiatric;

    @Column(columnDefinition = "TEXT")
    private String cardiovascular;

    @Column(columnDefinition = "TEXT")
    private String digestive;

    @Column(columnDefinition = "TEXT")
    private String lipodystrophy;

    @Column(columnDefinition = "TEXT")
    private String genitourinary;

    @Column(columnDefinition = "TEXT")
    private String ophthalmological;

    @Column(columnDefinition = "TEXT")
    private String pulmonary;

    @Column(columnDefinition = "TEXT")
    private String puberty;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    // Constructors
    public ClinicalSymptoms() {}

    public ClinicalSymptoms(Patient patient, String generalSigns, String dermatological,
                            String neuroMuscular, String ent, String psychiatric,
                            String cardiovascular, String digestive, String lipodystrophy,
                            String genitourinary, String ophthalmological, String pulmonary,
                            String puberty) {
        this.patient = patient;
        this.generalSigns = generalSigns;
        this.dermatological = dermatological;
        this.neuroMuscular = neuroMuscular;
        this.ent = ent;
        this.psychiatric = psychiatric;
        this.cardiovascular = cardiovascular;
        this.digestive = digestive;
        this.lipodystrophy = lipodystrophy;
        this.genitourinary = genitourinary;
        this.ophthalmological = ophthalmological;
        this.pulmonary = pulmonary;
        this.puberty = puberty;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGeneralSigns() {
        return generalSigns;
    }

    public void setGeneralSigns(String generalSigns) {
        this.generalSigns = generalSigns;
    }

    public String getDermatological() {
        return dermatological;
    }

    public void setDermatological(String dermatological) {
        this.dermatological = dermatological;
    }

    public String getNeuroMuscular() {
        return neuroMuscular;
    }

    public void setNeuroMuscular(String neuroMuscular) {
        this.neuroMuscular = neuroMuscular;
    }

    public String getEnt() {
        return ent;
    }

    public void setEnt(String ent) {
        this.ent = ent;
    }

    public String getPsychiatric() {
        return psychiatric;
    }

    public void setPsychiatric(String psychiatric) {
        this.psychiatric = psychiatric;
    }

    public String getCardiovascular() {
        return cardiovascular;
    }

    public void setCardiovascular(String cardiovascular) {
        this.cardiovascular = cardiovascular;
    }

    public String getDigestive() {
        return digestive;
    }

    public void setDigestive(String digestive) {
        this.digestive = digestive;
    }

    public String getLipodystrophy() {
        return lipodystrophy;
    }

    public void setLipodystrophy(String lipodystrophy) {
        this.lipodystrophy = lipodystrophy;
    }

    public String getGenitourinary() {
        return genitourinary;
    }

    public void setGenitourinary(String genitourinary) {
        this.genitourinary = genitourinary;
    }

    public String getOphthalmological() {
        return ophthalmological;
    }

    public void setOphthalmological(String ophthalmological) {
        this.ophthalmological = ophthalmological;
    }

    public String getPulmonary() {
        return pulmonary;
    }

    public void setPulmonary(String pulmonary) {
        this.pulmonary = pulmonary;
    }

    public String getPuberty() {
        return puberty;
    }

    public void setPuberty(String puberty) {
        this.puberty = puberty;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
