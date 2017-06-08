package com.epam.adsm.model;

/**
 * Created by akmatleu on 13.05.17.
 */
public class Research extends BaseEntity {

    private static final double PERCENT = 100.0;
    private java.time.LocalDate enrollmentDate;
    private java.time.LocalDate activationDate;
    private Boolean activationStatus;
    private double researchProgress;
    private Patient patient;

    public java.time.LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(java.time.LocalDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public java.time.LocalDate getActivationDate() {
        return activationDate;
    }

    public void setActivationDate(java.time.LocalDate activationDate) {
        this.activationDate = activationDate;
    }

    public Boolean getActivationStatus() {
        return activationStatus;
    }

    public void setActivationStatus(Boolean activationStatus) {
        this.activationStatus = activationStatus;
    }

    public double getResearchProgress() {
        return researchProgress;
    }

    public void setResearchProgress(double researchProgress) {
        researchProgress = researchProgress * PERCENT;
        researchProgress = Math.round(researchProgress);
        researchProgress = researchProgress / PERCENT;
        this.researchProgress = researchProgress;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

}
