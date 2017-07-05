package com.epam.adsm.model;

public class Diagnosis  extends  BaseEntity {

    private String riskFactor;
    private String localizationDisease;
    private String prevalence;
    private String clinicalForm;
    private String mbtStatus;
    private String patientType;
    private String dstStatus;
    private Patient patient;

    public Diagnosis(){}

    public String getRiskFactor() {
        return riskFactor;
    }

    public void setRiskFactor(String riskFactor) {
        this.riskFactor = riskFactor;
    }

    public String getLocalizationDisease() {
        return localizationDisease;
    }

    public void setLocalizationDisease(String localizationDisease) {
        this.localizationDisease = localizationDisease;
    }

    public String getPrevalence() {
        return prevalence;
    }

    public void setPrevalence(String prevalence) {
        this.prevalence = prevalence;
    }

    public String getClinicalForm() {
        return clinicalForm;
    }

    public void setClinicalForm(String clinicalForm) {
        this.clinicalForm = clinicalForm;
    }

    public String getMbtStatus() {
        return mbtStatus;
    }

    public void setMbtStatus(String mbtStatus) {
        this.mbtStatus = mbtStatus;
    }

    public String getPatientType() {
        return patientType;
    }

    public void setPatientType(String patientType) {
        this.patientType = patientType;
    }

    public String getDstStatus() {
        return dstStatus;
    }

    public void setDstStatus(String dstStatus) {
        this.dstStatus = dstStatus;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

}
