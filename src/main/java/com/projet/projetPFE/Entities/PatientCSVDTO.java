package com.projet.projetPFE.Entities;

public class PatientCSVDTO {
    private String medicalRecordNumber;
    private String lastName;
    private String firstName;
    private String gender;
    private String  practitionner_id;
    private String birthDate;
    private String age;

    private String positiveHIVDate;

    public String getPractitionner_id() {
        return practitionner_id;
    }

    public void setPractitionner_id(String practitionner_id) {
        this.practitionner_id = practitionner_id;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPositiveHIVDate() {
        return positiveHIVDate;
    }

    public void setPositiveHIVDate(String positiveHIVDate) {
        this.positiveHIVDate = positiveHIVDate;
    }

    // Getters & Setters
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

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
