package com.projet.projetPFE.Entities;

import jakarta.persistence.*;
import java.util.Date;
@Entity
public class Laboratory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.DATE)
    private Date medicaltestDate;
    //chimie
    @Temporal(TemporalType.DATE)
    private Date chemistrytestDate;


    private Double potassium;
    private Double sodium;
    private Double calcium;
    private Double phosphorus;
    private Double magnesium;


    private Double urea;
    private Double creatinine;


    private Double alat;  // ALT
    private Double asat;  // AST
    private Double ggt;   // GGT
    private Double pal;   // ALP


    private Double directBilirubin;
    private Double totalBilirubin;


    private Double albumin;
    private Double troponin;
    private Double lipase;
    //NFS
    @Temporal(TemporalType.DATE)
    private Date cbcTestDate; //nfs


    private Double wbc;  // GB (Globules Blancs)
    private Double neutrophilsAbs;  // GNN#
    private Double eosinophilsAbs;  // Eo#


    private Double rbc;  // GR
    private Double hemoglobin;  // HGB
    private Double hematocrit;  // HCT
    private Double mcv;  // VGM
    private Double mch;  // TCMH
    private Double mchc;  // CCMH
    private Double rdwCv;  // IDR


    private Double platelets;  // PLT
    private Double mpv;  // VPM


    private Double monocytesPercentage;  // Mono%
    private Double lymphocytesPercentage;  // Lymph%
    private Double eosinophilsPercentage;  // Eos%
    private Double basophilsPercentage;  // Bas%
    private Double neutrophilsPercentage;  // PNN%


    private Double lymphocytesAbs;  // Lymph#
    private Double basophilsAbs;
    private Double monocytesAbs;// Bas#
    private Double iono; // Ionogramme
    private Double vitaminD; // Vitamine D
    private Double urineTestStrips; // Bandelettes urinaires
    private Double fastingBloodGlucose; // Glycémie à jeun

    private Double hdl; // HDL
    private Double ldl; // LDL
    private Double totalCholesterol; // Cholestérol total (CHT)
    private Double triglycerides; // Triglycérides (TG)

    // Sérologies
    @Temporal(TemporalType.DATE)
    private Date serologytestDate;
    private String hepatitisAIgG;
    private String hepatitisAIgM; // Hépatite A Igm
    private String hepatitisBAgHbs; // Antigène HBs
    private String hepatitisBAntiHbc; // Anti-HBc
    private Double hepatitisBAntiHbs; // Anti-HBs

    private String hepatitisC; // Hépatite C
    private String syphilisTpha;// SyphilisTpha
    private String syphilisVdrl;// SyphilisVdrl
    private String cmvIgG;
    private String cmvIgM;// Cytomégalovirus (CMV)
    private String toxoplasmosis; // Toxoplasmose
    private String leishmaniasis; // Leishmaniose
    private String tuberculinTest; // IDR à la tuberculine
    private String quantiferonTest; // Test au Quantiféron
    private String chestXRay; // Radiothorax

    // Examens complémentaires
    private String proctologicExam; // Examen proctologique
    private Double viralLoad;

    private Double cd4Count; // Taux de CD4
    private String genotypicResistanceTest; // Test génotypique de résistance

    public String getHepatitisAIgG() {
        return hepatitisAIgG;
    }

    public void setHepatitisAIgG(String hepatitisAIgG) {
        this.hepatitisAIgG = hepatitisAIgG;
    }

    public String getHepatitisAIgM() {
        return hepatitisAIgM;
    }

    public void setHepatitisAIgM(String hepatitisAIgM) {
        this.hepatitisAIgM = hepatitisAIgM;
    }

    public String getHepatitisBAgHbs() {
        return hepatitisBAgHbs;
    }

    public void setHepatitisBAgHbs(String hepatitisBAgHbs) {
        this.hepatitisBAgHbs = hepatitisBAgHbs;
    }

    public String getHepatitisBAntiHbc() {
        return hepatitisBAntiHbc;
    }

    public void setHepatitisBAntiHbc(String hepatitisBAntiHbc) {
        this.hepatitisBAntiHbc = hepatitisBAntiHbc;
    }

    public Double getHepatitisBAntiHbs() {
        return hepatitisBAntiHbs;
    }

    public void setHepatitisBAntiHbs(Double hepatitisBAntiHbs) {
        this.hepatitisBAntiHbs = hepatitisBAntiHbs;
    }

    public String getHepatitisC() {
        return hepatitisC;
    }

    public void setHepatitisC(String hepatitisC) {
        this.hepatitisC = hepatitisC;
    }

    public String getSyphilisTpha() {
        return syphilisTpha;
    }

    public void setSyphilisTpha(String syphilisTpha) {
        this.syphilisTpha = syphilisTpha;
    }

    public String getSyphilisVdrl() {
        return syphilisVdrl;
    }

    public void setSyphilisVdrl(String syphilisVdrl) {
        this.syphilisVdrl = syphilisVdrl;
    }

    public String getCmvIgG() {
        return cmvIgG;
    }

    public void setCmvIgG(String cmvIgG) {
        this.cmvIgG = cmvIgG;
    }

    public String getCmvIgM() {
        return cmvIgM;
    }

    public void setCmvIgM(String cmvIgM) {
        this.cmvIgM = cmvIgM;
    }

    public String getToxoplasmosis() {
        return toxoplasmosis;
    }

    public void setToxoplasmosis(String toxoplasmosis) {
        this.toxoplasmosis = toxoplasmosis;
    }

    public String getLeishmaniasis() {
        return leishmaniasis;
    }

    public void setLeishmaniasis(String leishmaniasis) {
        this.leishmaniasis = leishmaniasis;
    }

    public String getTuberculinTest() {
        return tuberculinTest;
    }

    public void setTuberculinTest(String tuberculinTest) {
        this.tuberculinTest = tuberculinTest;
    }

    public String getQuantiferonTest() {
        return quantiferonTest;
    }

    public void setQuantiferonTest(String quantiferonTest) {
        this.quantiferonTest = quantiferonTest;
    }

    public String getChestXRay() {
        return chestXRay;
    }

    public void setChestXRay(String chestXRay) {
        this.chestXRay = chestXRay;
    }

    public Date getMedicaltestDate() {
        return medicaltestDate;
    }

    public void setMedicaltestDate(Date medicaltestDate) {
        this.medicaltestDate = medicaltestDate;
    }

    public Date getChemistrytestDate() {
        return chemistrytestDate;
    }

    public void setChemistrytestDate(Date chemistrytestDate) {
        this.chemistrytestDate = chemistrytestDate;
    }

    public Double getPotassium() {
        return potassium;
    }

    public void setPotassium(Double potassium) {
        this.potassium = potassium;
    }

    public Double getSodium() {
        return sodium;
    }

    public void setSodium(Double sodium) {
        this.sodium = sodium;
    }

    public Double getCalcium() {
        return calcium;
    }

    public void setCalcium(Double calcium) {
        this.calcium = calcium;
    }

    public Double getPhosphorus() {
        return phosphorus;
    }

    public void setPhosphorus(Double phosphorus) {
        this.phosphorus = phosphorus;
    }

    public Double getMagnesium() {
        return magnesium;
    }

    public void setMagnesium(Double magnesium) {
        this.magnesium = magnesium;
    }

    public Double getUrea() {
        return urea;
    }

    public void setUrea(Double urea) {
        this.urea = urea;
    }

    public Double getCreatinine() {
        return creatinine;
    }

    public void setCreatinine(Double creatinine) {
        this.creatinine = creatinine;
    }

    public Double getAlat() {
        return alat;
    }

    public void setAlat(Double alat) {
        this.alat = alat;
    }

    public Double getAsat() {
        return asat;
    }

    public void setAsat(Double asat) {
        this.asat = asat;
    }

    public Double getGgt() {
        return ggt;
    }

    public void setGgt(Double ggt) {
        this.ggt = ggt;
    }

    public Double getPal() {
        return pal;
    }

    public void setPal(Double pal) {
        this.pal = pal;
    }

    public Double getDirectBilirubin() {
        return directBilirubin;
    }

    public void setDirectBilirubin(Double directBilirubin) {
        this.directBilirubin = directBilirubin;
    }

    public Double getTotalBilirubin() {
        return totalBilirubin;
    }

    public void setTotalBilirubin(Double totalBilirubin) {
        this.totalBilirubin = totalBilirubin;
    }

    public Double getAlbumin() {
        return albumin;
    }

    public void setAlbumin(Double albumin) {
        this.albumin = albumin;
    }

    public Double getTroponin() {
        return troponin;
    }

    public void setTroponin(Double troponin) {
        this.troponin = troponin;
    }

    public Double getLipase() {
        return lipase;
    }

    public void setLipase(Double lipase) {
        this.lipase = lipase;
    }

    public Date getCbcTestDate() {
        return cbcTestDate;
    }

    public void setCbcTestDate(Date cbcTestDate) {
        this.cbcTestDate = cbcTestDate;
    }

    public Double getWbc() {
        return wbc;
    }

    public void setWbc(Double wbc) {
        this.wbc = wbc;
    }

    public Double getNeutrophilsAbs() {
        return neutrophilsAbs;
    }

    public void setNeutrophilsAbs(Double neutrophilsAbs) {
        this.neutrophilsAbs = neutrophilsAbs;
    }

    public Double getEosinophilsAbs() {
        return eosinophilsAbs;
    }

    public void setEosinophilsAbs(Double eosinophilsAbs) {
        this.eosinophilsAbs = eosinophilsAbs;
    }

    public Double getRbc() {
        return rbc;
    }

    public void setRbc(Double rbc) {
        this.rbc = rbc;
    }

    public Double getHemoglobin() {
        return hemoglobin;
    }

    public void setHemoglobin(Double hemoglobin) {
        this.hemoglobin = hemoglobin;
    }

    public Double getHematocrit() {
        return hematocrit;
    }

    public void setHematocrit(Double hematocrit) {
        this.hematocrit = hematocrit;
    }

    public Double getMcv() {
        return mcv;
    }

    public void setMcv(Double mcv) {
        this.mcv = mcv;
    }

    public Double getMch() {
        return mch;
    }

    public void setMch(Double mch) {
        this.mch = mch;
    }

    public Double getMchc() {
        return mchc;
    }

    public void setMchc(Double mchc) {
        this.mchc = mchc;
    }

    public Double getRdwCv() {
        return rdwCv;
    }

    public void setRdwCv(Double rdwCv) {
        this.rdwCv = rdwCv;
    }

    public Double getPlatelets() {
        return platelets;
    }

    public void setPlatelets(Double platelets) {
        this.platelets = platelets;
    }

    public Double getMpv() {
        return mpv;
    }

    public void setMpv(Double mpv) {
        this.mpv = mpv;
    }

    public Double getMonocytesPercentage() {
        return monocytesPercentage;
    }

    public void setMonocytesPercentage(Double monocytesPercentage) {
        this.monocytesPercentage = monocytesPercentage;
    }

    public Double getLymphocytesPercentage() {
        return lymphocytesPercentage;
    }

    public void setLymphocytesPercentage(Double lymphocytesPercentage) {
        this.lymphocytesPercentage = lymphocytesPercentage;
    }

    public Double getEosinophilsPercentage() {
        return eosinophilsPercentage;
    }

    public void setEosinophilsPercentage(Double eosinophilsPercentage) {
        this.eosinophilsPercentage = eosinophilsPercentage;
    }

    public Double getBasophilsPercentage() {
        return basophilsPercentage;
    }

    public void setBasophilsPercentage(Double basophilsPercentage) {
        this.basophilsPercentage = basophilsPercentage;
    }

    public Double getNeutrophilsPercentage() {
        return neutrophilsPercentage;
    }

    public void setNeutrophilsPercentage(Double neutrophilsPercentage) {
        this.neutrophilsPercentage = neutrophilsPercentage;
    }

    public Double getLymphocytesAbs() {
        return lymphocytesAbs;
    }

    public void setLymphocytesAbs(Double lymphocytesAbs) {
        this.lymphocytesAbs = lymphocytesAbs;
    }

    public Double getBasophilsAbs() {
        return basophilsAbs;
    }

    public void setBasophilsAbs(Double basophilsAbs) {
        this.basophilsAbs = basophilsAbs;
    }

    public Double getMonocytesAbs() {
        return monocytesAbs;
    }

    public void setMonocytesAbs(Double monocytesAbs) {
        this.monocytesAbs = monocytesAbs;
    }

    public Double getIono() {
        return iono;
    }

    public void setIono(Double iono) {
        this.iono = iono;
    }

    public Double getVitaminD() {
        return vitaminD;
    }

    public void setVitaminD(Double vitaminD) {
        this.vitaminD = vitaminD;
    }

    public Double getUrineTestStrips() {
        return urineTestStrips;
    }

    public void setUrineTestStrips(Double urineTestStrips) {
        this.urineTestStrips = urineTestStrips;
    }

    public Double getFastingBloodGlucose() {
        return fastingBloodGlucose;
    }

    public void setFastingBloodGlucose(Double fastingBloodGlucose) {
        this.fastingBloodGlucose = fastingBloodGlucose;
    }

    public Double getHdl() {
        return hdl;
    }

    public void setHdl(Double hdl) {
        this.hdl = hdl;
    }

    public Double getLdl() {
        return ldl;
    }

    public void setLdl(Double ldl) {
        this.ldl = ldl;
    }

    public Double getTotalCholesterol() {
        return totalCholesterol;
    }

    public void setTotalCholesterol(Double totalCholesterol) {
        this.totalCholesterol = totalCholesterol;
    }

    public Double getTriglycerides() {
        return triglycerides;
    }

    public void setTriglycerides(Double triglycerides) {
        this.triglycerides = triglycerides;
    }

    public Date getSerologytestDate() {
        return serologytestDate;
    }

    public void setSerologytestDate(Date serologytestDate) {
        this.serologytestDate = serologytestDate;
    }



    public String getProctologicExam() {
        return proctologicExam;
    }

    public void setProctologicExam(String proctologicExam) {
        this.proctologicExam = proctologicExam;
    }

    public Double getViralLoad() {
        return viralLoad;
    }

    public void setViralLoad(Double viralLoad) {
        this.viralLoad = viralLoad;
    }

    public Double getCd4Count() {
        return cd4Count;
    }

    public void setCd4Count(Double cd4Count) {
        this.cd4Count = cd4Count;
    }

    public String getGenotypicResistanceTest() {
        return genotypicResistanceTest;
    }

    public void setGenotypicResistanceTest(String genotypicResistanceTest) {
        this.genotypicResistanceTest = genotypicResistanceTest;
    }

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
