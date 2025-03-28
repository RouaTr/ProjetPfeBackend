package com.projet.projetPFE.Entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "functional_symptoms")
public class FunctionalSymptoms {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean fever;
    private Boolean diarrhea;
    private Boolean cough;
    private Boolean abdominalPain;
    private Boolean dyspnea;
    private Boolean nausea;
    private Boolean asthenia;
    private Boolean arthralgia;
    private Boolean nightSweats;
    private Boolean headache;
    private Boolean dysphagia;
    private Boolean pruritus;
    private Boolean anorexia;
    private Boolean insomnia;
    private Boolean moodDisorders;
    private Boolean rhinorrhea;
    private Boolean paresthesia;
    private Boolean cramps;
    private Boolean visualDisturbances;
    private Boolean myalgia;
    private Boolean libidoDisorders;
    private Boolean otherSymptoms;
    @Temporal(TemporalType.DATE)
    private Date functionalSymptomsDate;

    public Date getFunctionalSymptomsDate() {
        return functionalSymptomsDate;
    }

    public void setFunctionalSymptomsDate(Date functionalSymptomsDate) {
        this.functionalSymptomsDate = functionalSymptomsDate;
    }

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    // Constructeurs
    public FunctionalSymptoms() {}

    public FunctionalSymptoms(Patient patient, Boolean fever, Boolean diarrhea, Boolean cough,
                              Boolean abdominalPain, Boolean dyspnea, Boolean nausea, Boolean asthenia,
                              Boolean arthralgia, Boolean nightSweats, Boolean headache, Boolean dysphagia,
                              Boolean pruritus, Boolean anorexia, Boolean insomnia, Boolean moodDisorders,
                              Boolean rhinorrhea, Boolean paresthesia, Boolean cramps,
                              Boolean visualDisturbances, Boolean myalgia, Boolean libidoDisorders,
                              Boolean otherSymptoms) {
        this.patient = patient;
        this.fever = fever;
        this.diarrhea = diarrhea;
        this.cough = cough;
        this.abdominalPain = abdominalPain;
        this.dyspnea = dyspnea;
        this.nausea = nausea;
        this.asthenia = asthenia;
        this.arthralgia = arthralgia;
        this.nightSweats = nightSweats;
        this.headache = headache;
        this.dysphagia = dysphagia;
        this.pruritus = pruritus;
        this.anorexia = anorexia;
        this.insomnia = insomnia;
        this.moodDisorders = moodDisorders;
        this.rhinorrhea = rhinorrhea;
        this.paresthesia = paresthesia;
        this.cramps = cramps;
        this.visualDisturbances = visualDisturbances;
        this.myalgia = myalgia;
        this.libidoDisorders = libidoDisorders;
        this.otherSymptoms = otherSymptoms;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getFever() {
        return fever;
    }

    public void setFever(Boolean fever) {
        this.fever = fever;
    }

    public Boolean getDiarrhea() {
        return diarrhea;
    }

    public void setDiarrhea(Boolean diarrhea) {
        this.diarrhea = diarrhea;
    }

    public Boolean getCough() {
        return cough;
    }

    public void setCough(Boolean cough) {
        this.cough = cough;
    }

    public Boolean getAbdominalPain() {
        return abdominalPain;
    }

    public void setAbdominalPain(Boolean abdominalPain) {
        this.abdominalPain = abdominalPain;
    }

    public Boolean getDyspnea() {
        return dyspnea;
    }

    public void setDyspnea(Boolean dyspnea) {
        this.dyspnea = dyspnea;
    }

    public Boolean getNausea() {
        return nausea;
    }

    public void setNausea(Boolean nausea) {
        this.nausea = nausea;
    }

    public Boolean getAsthenia() {
        return asthenia;
    }

    public void setAsthenia(Boolean asthenia) {
        this.asthenia = asthenia;
    }

    public Boolean getArthralgia() {
        return arthralgia;
    }

    public void setArthralgia(Boolean arthralgia) {
        this.arthralgia = arthralgia;
    }

    public Boolean getNightSweats() {
        return nightSweats;
    }

    public void setNightSweats(Boolean nightSweats) {
        this.nightSweats = nightSweats;
    }

    public Boolean getHeadache() {
        return headache;
    }

    public void setHeadache(Boolean headache) {
        this.headache = headache;
    }

    public Boolean getDysphagia() {
        return dysphagia;
    }

    public void setDysphagia(Boolean dysphagia) {
        this.dysphagia = dysphagia;
    }

    public Boolean getPruritus() {
        return pruritus;
    }

    public void setPruritus(Boolean pruritus) {
        this.pruritus = pruritus;
    }

    public Boolean getAnorexia() {
        return anorexia;
    }

    public void setAnorexia(Boolean anorexia) {
        this.anorexia = anorexia;
    }

    public Boolean getInsomnia() {
        return insomnia;
    }

    public void setInsomnia(Boolean insomnia) {
        this.insomnia = insomnia;
    }

    public Boolean getMoodDisorders() {
        return moodDisorders;
    }

    public void setMoodDisorders(Boolean moodDisorders) {
        this.moodDisorders = moodDisorders;
    }

    public Boolean getRhinorrhea() {
        return rhinorrhea;
    }

    public void setRhinorrhea(Boolean rhinorrhea) {
        this.rhinorrhea = rhinorrhea;
    }

    public Boolean getParesthesia() {
        return paresthesia;
    }

    public void setParesthesia(Boolean paresthesia) {
        this.paresthesia = paresthesia;
    }

    public Boolean getCramps() {
        return cramps;
    }

    public void setCramps(Boolean cramps) {
        this.cramps = cramps;
    }

    public Boolean getVisualDisturbances() {
        return visualDisturbances;
    }

    public void setVisualDisturbances(Boolean visualDisturbances) {
        this.visualDisturbances = visualDisturbances;
    }

    public Boolean getMyalgia() {
        return myalgia;
    }

    public void setMyalgia(Boolean myalgia) {
        this.myalgia = myalgia;
    }

    public Boolean getLibidoDisorders() {
        return libidoDisorders;
    }

    public void setLibidoDisorders(Boolean libidoDisorders) {
        this.libidoDisorders = libidoDisorders;
    }

    public Boolean getOtherSymptoms() {
        return otherSymptoms;
    }

    public void setOtherSymptoms(Boolean otherSymptoms) {
        this.otherSymptoms = otherSymptoms;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
