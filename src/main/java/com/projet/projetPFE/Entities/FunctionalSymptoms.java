package com.projet.projetPFE.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

@Entity
@Table(name = "functional_symptoms")
public class FunctionalSymptoms {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String fever;

    @Column(columnDefinition = "TEXT")
    private String diarrhea;

    @Column(columnDefinition = "TEXT")
    private String cough;

    @Column(columnDefinition = "TEXT")
    private String abdominalPain;

    @Column(columnDefinition = "TEXT")
    private String dyspnea;

    @Column(columnDefinition = "TEXT")
    private String nausea;

    @Column(columnDefinition = "TEXT")
    private String asthenia;

    @Column(columnDefinition = "TEXT")
    private String arthralgia;

    @Column(columnDefinition = "TEXT")
    private String nightSweats;

    @Column(columnDefinition = "TEXT")
    private String headache;

    @Column(columnDefinition = "TEXT")
    private String dysphagia;

    @Column(columnDefinition = "TEXT")
    private String pruritus;

    @Column(columnDefinition = "TEXT")
    private String anorexia;

    @Column(columnDefinition = "TEXT")
    private String insomnia;

    @Column(columnDefinition = "TEXT")
    private String moodDisorders;

    @Column(columnDefinition = "TEXT")
    private String rhinorrhea;

    @Column(columnDefinition = "TEXT")
    private String paresthesia;

    @Column(columnDefinition = "TEXT")
    private String cramps;

    @Column(columnDefinition = "TEXT")
    private String visualDisturbances;

    @Column(columnDefinition = "TEXT")
    private String myalgia;

    @Column(columnDefinition = "TEXT")
    private String libidoDisorders;

    @Column(columnDefinition = "TEXT")
    private String otherSymptoms;  // Stores custom symptoms added by the user

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    // Constructors
    public FunctionalSymptoms() {}

    public FunctionalSymptoms(Patient patient, String fever, String diarrhea, String cough,
                              String abdominalPain, String dyspnea, String nausea, String asthenia,
                              String arthralgia, String nightSweats, String headache, String dysphagia,
                              String pruritus, String anorexia, String insomnia, String moodDisorders,
                              String rhinorrhea, String paresthesia, String cramps,
                              String visualDisturbances, String myalgia, String libidoDisorders,
                              String otherSymptoms) {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFever() {
        return fever;
    }

    public void setFever(String fever) {
        this.fever = fever;
    }

    public String getDiarrhea() {
        return diarrhea;
    }

    public void setDiarrhea(String diarrhea) {
        this.diarrhea = diarrhea;
    }

    public String getCough() {
        return cough;
    }

    public void setCough(String cough) {
        this.cough = cough;
    }

    public String getAbdominalPain() {
        return abdominalPain;
    }

    public void setAbdominalPain(String abdominalPain) {
        this.abdominalPain = abdominalPain;
    }

    public String getDyspnea() {
        return dyspnea;
    }

    public void setDyspnea(String dyspnea) {
        this.dyspnea = dyspnea;
    }

    public String getNausea() {
        return nausea;
    }

    public void setNausea(String nausea) {
        this.nausea = nausea;
    }

    public String getAsthenia() {
        return asthenia;
    }

    public void setAsthenia(String asthenia) {
        this.asthenia = asthenia;
    }

    public String getArthralgia() {
        return arthralgia;
    }

    public void setArthralgia(String arthralgia) {
        this.arthralgia = arthralgia;
    }

    public String getNightSweats() {
        return nightSweats;
    }

    public void setNightSweats(String nightSweats) {
        this.nightSweats = nightSweats;
    }

    public String getHeadache() {
        return headache;
    }

    public void setHeadache(String headache) {
        this.headache = headache;
    }

    public String getDysphagia() {
        return dysphagia;
    }

    public void setDysphagia(String dysphagia) {
        this.dysphagia = dysphagia;
    }

    public String getPruritus() {
        return pruritus;
    }

    public void setPruritus(String pruritus) {
        this.pruritus = pruritus;
    }

    public String getAnorexia() {
        return anorexia;
    }

    public void setAnorexia(String anorexia) {
        this.anorexia = anorexia;
    }

    public String getInsomnia() {
        return insomnia;
    }

    public void setInsomnia(String insomnia) {
        this.insomnia = insomnia;
    }

    public String getMoodDisorders() {
        return moodDisorders;
    }

    public void setMoodDisorders(String moodDisorders) {
        this.moodDisorders = moodDisorders;
    }

    public String getRhinorrhea() {
        return rhinorrhea;
    }

    public void setRhinorrhea(String rhinorrhea) {
        this.rhinorrhea = rhinorrhea;
    }

    public String getParesthesia() {
        return paresthesia;
    }

    public void setParesthesia(String paresthesia) {
        this.paresthesia = paresthesia;
    }

    public String getCramps() {
        return cramps;
    }

    public void setCramps(String cramps) {
        this.cramps = cramps;
    }

    public String getVisualDisturbances() {
        return visualDisturbances;
    }

    public void setVisualDisturbances(String visualDisturbances) {
        this.visualDisturbances = visualDisturbances;
    }

    public String getMyalgia() {
        return myalgia;
    }

    public void setMyalgia(String myalgia) {
        this.myalgia = myalgia;
    }

    public String getLibidoDisorders() {
        return libidoDisorders;
    }

    public void setLibidoDisorders(String libidoDisorders) {
        this.libidoDisorders = libidoDisorders;
    }

    public String getOtherSymptoms() {
        return otherSymptoms;
    }

    public void setOtherSymptoms(String otherSymptoms) {
        this.otherSymptoms = otherSymptoms;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
