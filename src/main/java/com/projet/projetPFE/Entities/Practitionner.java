package com.projet.projetPFE.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Practitionner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String practitionnerLastName;
    private String practitionnerFirstName;

    private String practitionnerPhoneNumber;
    private String practitionnerEmail;

    private String password;

    private String practitionnerRole;
    private String status = "pending";

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getPractitionnerLastName() {
        return practitionnerLastName;
    }

    public void setPractitionnerLastName(String practitionnerLastName) {
        this.practitionnerLastName = practitionnerLastName;
    }

    public String getPractitionnerFirstName() {
        return practitionnerFirstName;
    }

    public void setPractitionnerFirstName(String practitionnerFirstName) {
        this.practitionnerFirstName = practitionnerFirstName;
    }

    public String getPractitionnerPhoneNumber() {
        return practitionnerPhoneNumber;
    }

    public void setPractitionnerPhoneNumber(String practitionnerPhoneNumber) {
        this.practitionnerPhoneNumber = practitionnerPhoneNumber;
    }

    public String getPractitionnerEmail() {
        return practitionnerEmail;
    }

    public void setPractitionnerEmail(String practitionnerEmail) {
        this.practitionnerEmail = practitionnerEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPractitionnerRole() {
        return practitionnerRole;
    }

    public void setPractitionnerRole(String practitionnerRole) {
        this.practitionnerRole = practitionnerRole;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
