package com.projet.projetPFE.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;


import java.util.Date;
import java.util.List;
@Entity
@Table(name = "patients")
public class Patient {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /*@JsonProperty*/
    private Long id;
    @Column(name = "medical_record_number", unique = true)
    private String medicalRecordNumber;
    private String lastName;
    private String firstName;

    @Temporal(TemporalType.DATE)
    private Date birthDate;

    private String gender;
    private String phoneNumber;

    private String city;
    private String region;
    private String postalCode;
    private String address;
    private String nationality;

    private Double weight;
    private Double height;

    private String maritalStatus;
    private Integer children;
    private String housing;
    private String housingType;
    private String educationLevel;

    private Boolean smoking;
    private Boolean alcohol;
    private Boolean drugUse;
    private Boolean physicalActivity;

    // Signes vitaux
    private Double bodyTemperature;
    private Integer heartRate;
    private String bloodPressure;

    // Épidémiologie
    private String contaminationMode;
    private String initialScreeningType;
    private String initialScreeningReason;


    public Double getAge_at_HIV_diagnosis() {
        return age_at_HIV_diagnosis;
    }

    public void setAge_at_HIV_diagnosis(Double age_at_HIV_diagnosis) {
        this.age_at_HIV_diagnosis = age_at_HIV_diagnosis;
    }

    @Temporal(TemporalType.DATE)
    private Date lastNegativeDate;

    @Temporal(TemporalType.DATE)
    private Date positiveHIVDate;

    private String hlaB5701Typing;
    private String screeningCircumstance;
    private String viralType;
    private Double age_at_HIV_diagnosis;

    @Temporal(TemporalType.DATE)
    private Date contaminationDate;

    private String cdcStage;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<MedicalHistory> medicalHistories;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Laboratory> laboratories;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Observation> observations;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<FunctionalSymptoms> functionalSymptoms;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<ClinicalSymptoms> clinicalSymptoms;
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<MedicalTreatment> medicalTreatments;

    @ManyToOne
    @JoinColumn(name = "practitionner_id")
    private Practitionner practitionner;

    public void setPractitionner(Practitionner practitionner) {
        this.practitionner = practitionner;
    }

    public Practitionner getPractitionner() {
        return practitionner;
    }

    public List<MedicalTreatment> getMedicalTreatments() {
        return medicalTreatments;
    }

    public void setMedicalTreatments(List<MedicalTreatment> medicalTreatments) {
        this.medicalTreatments = medicalTreatments;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getMedicalRecordNumber() {
        return medicalRecordNumber;
    }

    public void setMedicalRecordNumber(String medicalRecordNumber) {
        this.medicalRecordNumber = medicalRecordNumber;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public Integer getChildren() {
        return children;
    }

    public void setChildren(Integer children) {
        this.children = children;
    }

    public String getHousing() {
        return housing;
    }

    public void setHousing(String housing) {
        this.housing = housing;
    }

    public String getHousingType() {
        return housingType;
    }

    public void setHousingType(String housingType) {
        this.housingType = housingType;
    }

    public String getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(String educationLevel) {
        this.educationLevel = educationLevel;
    }

    public Boolean getSmoking() {
        return smoking;
    }

    public void setSmoking(Boolean smoking) {
        this.smoking = smoking;
    }

    public Boolean getAlcohol() {
        return alcohol;
    }

    public void setAlcohol(Boolean alcohol) {
        this.alcohol = alcohol;
    }

    public Boolean getDrugUse() {
        return drugUse;
    }

    public void setDrugUse(Boolean drugUse) {
        this.drugUse = drugUse;
    }

    public Boolean getPhysicalActivity() {
        return physicalActivity;
    }

    public void setPhysicalActivity(Boolean physicalActivity) {
        this.physicalActivity = physicalActivity;
    }

    public Double getBodyTemperature() {
        return bodyTemperature;
    }

    public void setBodyTemperature(Double bodyTemperature) {
        this.bodyTemperature = bodyTemperature;
    }

    public Integer getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(Integer heartRate) {
        this.heartRate = heartRate;
    }

    public String getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(String bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public String getContaminationMode() {
        return contaminationMode;
    }

    public void setContaminationMode(String contaminationMode) {
        this.contaminationMode = contaminationMode;
    }

    public String getInitialScreeningType() {
        return initialScreeningType;
    }

    public void setInitialScreeningType(String initialScreeningType) {
        this.initialScreeningType = initialScreeningType;
    }

    public String getInitialScreeningReason() {
        return initialScreeningReason;
    }

    public void setInitialScreeningReason(String initialScreeningReason) {
        this.initialScreeningReason = initialScreeningReason;
    }

    public Date getLastNegativeDate() {
        return lastNegativeDate;
    }

    public void setLastNegativeDate(Date lastNegativeDate) {
        this.lastNegativeDate = lastNegativeDate;
    }

    public Date getPositiveHIVDate() {
        return positiveHIVDate;
    }

    public void setPositiveHIVDate(Date positiveHIVDate) {
        this.positiveHIVDate = positiveHIVDate;
    }

    public String getHlaB5701Typing() {
        return hlaB5701Typing;
    }

    public void setHlaB5701Typing(String hlaB5701Typing) {
        this.hlaB5701Typing = hlaB5701Typing;
    }

    public String getScreeningCircumstance() {
        return screeningCircumstance;
    }

    public void setScreeningCircumstance(String screeningCircumstance) {
        this.screeningCircumstance = screeningCircumstance;
    }

    public String getViralType() {
        return viralType;
    }

    public void setViralType(String viralType) {
        this.viralType = viralType;
    }

    public Date getContaminationDate() {
        return contaminationDate;
    }

    public void setContaminationDate(Date contaminationDate) {
        this.contaminationDate = contaminationDate;
    }

    public String getCdcStage() {
        return cdcStage;
    }

    public void setCdcStage(String cdcStage) {
        this.cdcStage = cdcStage;
    }

    public List<MedicalHistory> getMedicalHistories() {
        return medicalHistories;
    }

    public void setMedicalHistories(List<MedicalHistory> medicalHistories) {
        this.medicalHistories = medicalHistories;
    }

    public List<Laboratory> getLaboratories() {
        return laboratories;
    }

    public void setLaboratories(List<Laboratory> laboratories) {
        this.laboratories = laboratories;
    }

    public List<Observation> getObservations() {
        return observations;
    }

    public void setObservations(List<Observation> observations) {
        this.observations = observations;
    }

    public List<FunctionalSymptoms> getFunctionalSymptoms() {
        return functionalSymptoms;
    }

    public void setFunctionalSymptoms(List<FunctionalSymptoms> functionalSymptoms) {
        this.functionalSymptoms = functionalSymptoms;
    }

    public List<ClinicalSymptoms> getClinicalSymptoms() {
        return clinicalSymptoms;
    }

    public void setClinicalSymptoms(List<ClinicalSymptoms> clinicalSymptoms) {
        this.clinicalSymptoms = clinicalSymptoms;
    }
}
