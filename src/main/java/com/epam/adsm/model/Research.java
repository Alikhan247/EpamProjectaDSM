package com.epam.adsm.model;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import java.sql.Date;

/**
 * Created by akmatleu on 13.05.17.
 */
public class Research extends BaseEntity{

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
        researchProgress=researchProgress*100;
        researchProgress=Math.round(researchProgress);
        researchProgress=researchProgress/100;
        this.researchProgress = researchProgress;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @Override
    public String toString() {
        return "Research{" +
                ", enrollmentDate=" + enrollmentDate +
                ", activationDate=" + activationDate +
                ", activationStatus=" + activationStatus +
                ", researchProgress=" + researchProgress +
                '}';
    }
}
