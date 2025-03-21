package com.projet.projetPFE.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "medical_history")
public class MedicalHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String historyType; // Type: Vaccin, Allergie, Médicaux, Familiaux, IST

    // Vaccination
    private String vaccineCode;
    private String vaccineType;
    private String vaccineBatchNumber;
    private String vaccineStatus;

    @Temporal(TemporalType.DATE)
    private Date vaccineDate;

    // Allergies
    @Temporal(TemporalType.DATE)
    private Date allergyDiagnosisDate;
    private String allergyType;
    private String allergyReaction;
    private String allergyMedication;
    private String allergyStatus;

    // Medical conditions
    private String medicalCondition;

    @Temporal(TemporalType.DATE)
    private Date medicalDiagnosisDate;
    private String medicalTreatment;

    @Temporal(TemporalType.DATE)
    private Date medicalTreatmentStartDate;

    @Temporal(TemporalType.DATE)
    private Date medicalTreatmentEndDate;
    private String medicalFollowUp;
    private String medicalStatus;

    // Family history
    private String hereditaryDisease;
    private String affectedFamilyMember;

    @Temporal(TemporalType.DATE)
    private Date familyDiagnosisDate;
    private String familyStatus;

    // Sexually Transmitted Infections (STIs)
    private String stiType;

    @Temporal(TemporalType.DATE)
    private Date stiDiagnosisDate;
    private String stiTreatment;

    @Temporal(TemporalType.DATE)
    private Date stiTreatmentStartDate;

    @Temporal(TemporalType.DATE)
    private Date stiTreatmentEndDate;
    private String stiFollowUp;
    private String stiStatus;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    // Constructors
    public MedicalHistory() {}

    public MedicalHistory(Patient patient, String historyType, String vaccineCode, String vaccineType,
                          String vaccineBatchNumber, String vaccineStatus, Date vaccineDate,
                          Date allergyDiagnosisDate, String allergyType, String allergyReaction,
                          String allergyMedication, String allergyStatus, String medicalCondition,
                          Date medicalDiagnosisDate, String medicalTreatment, Date medicalTreatmentStartDate,
                          Date medicalTreatmentEndDate, String medicalFollowUp, String medicalStatus,
                          String hereditaryDisease, String affectedFamilyMember, Date familyDiagnosisDate,
                          String familyStatus, String stiType, Date stiDiagnosisDate, String stiTreatment,
                          Date stiTreatmentStartDate, Date stiTreatmentEndDate, String stiFollowUp,
                          String stiStatus) {
        this.patient = patient;
        this.historyType = historyType;
        this.vaccineCode = vaccineCode;
        this.vaccineType = vaccineType;
        this.vaccineBatchNumber = vaccineBatchNumber;
        this.vaccineStatus = vaccineStatus;
        this.vaccineDate = vaccineDate;
        this.allergyDiagnosisDate = allergyDiagnosisDate;
        this.allergyType = allergyType;
        this.allergyReaction = allergyReaction;
        this.allergyMedication = allergyMedication;
        this.allergyStatus = allergyStatus;
        this.medicalCondition = medicalCondition;
        this.medicalDiagnosisDate = medicalDiagnosisDate;
        this.medicalTreatment = medicalTreatment;
        this.medicalTreatmentStartDate = medicalTreatmentStartDate;
        this.medicalTreatmentEndDate = medicalTreatmentEndDate;
        this.medicalFollowUp = medicalFollowUp;
        this.medicalStatus = medicalStatus;
        this.hereditaryDisease = hereditaryDisease;
        this.affectedFamilyMember = affectedFamilyMember;
        this.familyDiagnosisDate = familyDiagnosisDate;
        this.familyStatus = familyStatus;
        this.stiType = stiType;
        this.stiDiagnosisDate = stiDiagnosisDate;
        this.stiTreatment = stiTreatment;
        this.stiTreatmentStartDate = stiTreatmentStartDate;
        this.stiTreatmentEndDate = stiTreatmentEndDate;
        this.stiFollowUp = stiFollowUp;
        this.stiStatus = stiStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHistoryType() {
        return historyType;
    }

    public void setHistoryType(String historyType) {
        this.historyType = historyType;
    }

    public String getVaccineCode() {
        return vaccineCode;
    }

    public void setVaccineCode(String vaccineCode) {
        this.vaccineCode = vaccineCode;
    }

    public String getVaccineType() {
        return vaccineType;
    }

    public void setVaccineType(String vaccineType) {
        this.vaccineType = vaccineType;
    }

    public String getVaccineBatchNumber() {
        return vaccineBatchNumber;
    }

    public void setVaccineBatchNumber(String vaccineBatchNumber) {
        this.vaccineBatchNumber = vaccineBatchNumber;
    }

    public String getVaccineStatus() {
        return vaccineStatus;
    }

    public void setVaccineStatus(String vaccineStatus) {
        this.vaccineStatus = vaccineStatus;
    }

    public Date getVaccineDate() {
        return vaccineDate;
    }

    public void setVaccineDate(Date vaccineDate) {
        this.vaccineDate = vaccineDate;
    }

    public Date getAllergyDiagnosisDate() {
        return allergyDiagnosisDate;
    }

    public void setAllergyDiagnosisDate(Date allergyDiagnosisDate) {
        this.allergyDiagnosisDate = allergyDiagnosisDate;
    }

    public String getAllergyType() {
        return allergyType;
    }

    public void setAllergyType(String allergyType) {
        this.allergyType = allergyType;
    }

    public String getAllergyReaction() {
        return allergyReaction;
    }

    public void setAllergyReaction(String allergyReaction) {
        this.allergyReaction = allergyReaction;
    }

    public String getAllergyMedication() {
        return allergyMedication;
    }

    public void setAllergyMedication(String allergyMedication) {
        this.allergyMedication = allergyMedication;
    }

    public String getAllergyStatus() {
        return allergyStatus;
    }

    public void setAllergyStatus(String allergyStatus) {
        this.allergyStatus = allergyStatus;
    }

    public String getMedicalCondition() {
        return medicalCondition;
    }

    public void setMedicalCondition(String medicalCondition) {
        this.medicalCondition = medicalCondition;
    }

    public Date getMedicalDiagnosisDate() {
        return medicalDiagnosisDate;
    }

    public void setMedicalDiagnosisDate(Date medicalDiagnosisDate) {
        this.medicalDiagnosisDate = medicalDiagnosisDate;
    }

    public String getMedicalTreatment() {
        return medicalTreatment;
    }

    public void setMedicalTreatment(String medicalTreatment) {
        this.medicalTreatment = medicalTreatment;
    }

    public Date getMedicalTreatmentStartDate() {
        return medicalTreatmentStartDate;
    }

    public void setMedicalTreatmentStartDate(Date medicalTreatmentStartDate) {
        this.medicalTreatmentStartDate = medicalTreatmentStartDate;
    }

    public Date getMedicalTreatmentEndDate() {
        return medicalTreatmentEndDate;
    }

    public void setMedicalTreatmentEndDate(Date medicalTreatmentEndDate) {
        this.medicalTreatmentEndDate = medicalTreatmentEndDate;
    }

    public String getMedicalFollowUp() {
        return medicalFollowUp;
    }

    public void setMedicalFollowUp(String medicalFollowUp) {
        this.medicalFollowUp = medicalFollowUp;
    }

    public String getMedicalStatus() {
        return medicalStatus;
    }

    public void setMedicalStatus(String medicalStatus) {
        this.medicalStatus = medicalStatus;
    }

    public String getHereditaryDisease() {
        return hereditaryDisease;
    }

    public void setHereditaryDisease(String hereditaryDisease) {
        this.hereditaryDisease = hereditaryDisease;
    }

    public String getAffectedFamilyMember() {
        return affectedFamilyMember;
    }

    public void setAffectedFamilyMember(String affectedFamilyMember) {
        this.affectedFamilyMember = affectedFamilyMember;
    }

    public Date getFamilyDiagnosisDate() {
        return familyDiagnosisDate;
    }

    public void setFamilyDiagnosisDate(Date familyDiagnosisDate) {
        this.familyDiagnosisDate = familyDiagnosisDate;
    }

    public String getFamilyStatus() {
        return familyStatus;
    }

    public void setFamilyStatus(String familyStatus) {
        this.familyStatus = familyStatus;
    }

    public String getStiType() {
        return stiType;
    }

    public void setStiType(String stiType) {
        this.stiType = stiType;
    }

    public Date getStiDiagnosisDate() {
        return stiDiagnosisDate;
    }

    public void setStiDiagnosisDate(Date stiDiagnosisDate) {
        this.stiDiagnosisDate = stiDiagnosisDate;
    }

    public String getStiTreatment() {
        return stiTreatment;
    }

    public void setStiTreatment(String stiTreatment) {
        this.stiTreatment = stiTreatment;
    }

    public Date getStiTreatmentStartDate() {
        return stiTreatmentStartDate;
    }

    public void setStiTreatmentStartDate(Date stiTreatmentStartDate) {
        this.stiTreatmentStartDate = stiTreatmentStartDate;
    }

    public Date getStiTreatmentEndDate() {
        return stiTreatmentEndDate;
    }

    public void setStiTreatmentEndDate(Date stiTreatmentEndDate) {
        this.stiTreatmentEndDate = stiTreatmentEndDate;
    }

    public String getStiFollowUp() {
        return stiFollowUp;
    }

    public void setStiFollowUp(String stiFollowUp) {
        this.stiFollowUp = stiFollowUp;
    }

    public String getStiStatus() {
        return stiStatus;
    }

    public void setStiStatus(String stiStatus) {
        this.stiStatus = stiStatus;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
