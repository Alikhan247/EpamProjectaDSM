package com.epam.adsm.model;

import java.sql.Date;
import java.time.LocalDate;

/**
 * Created by akmatleu on 10.05.17.
 */
public class Patient extends Person {

    private String patientCode;
    private LocalDate dateOfBirthday;
    private String initial;
    private String patientSex;
    private Boolean confirmed;
    private Staff doctor;

    private Event eventDay;
    private Diagnosis diagnosis;

    public Patient(){}


    public String getPatientCode() {
        return patientCode;
    }

    public void setPatientCode(String patientCode) {
        this.patientCode = patientCode;
    }

    public LocalDate getDateOfBirthday() {
        return dateOfBirthday;
    }

    public void setDateOfBirthday(LocalDate dateOfBirthday) {
        this.dateOfBirthday = dateOfBirthday;
    }

    public String getInitial() {
        return initial;
    }

    public void setInitial(String initial) {
        this.initial = initial;
    }

    public String getPatientSex() {
        return patientSex;
    }

    public void setPatientSex(String patientSex) {
        this.patientSex = patientSex;
    }

    public Boolean getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(Boolean confirmed) {
        this.confirmed = confirmed;
    }

    public Staff getDoctor() {
        return doctor;
    }

    public void setDoctor(Staff doctor) {
        this.doctor = doctor;
    }

    public Event getEventDay() {
        return eventDay;
    }

    public void setEventDay(Event eventDay) {
        this.eventDay = eventDay;
    }

    public Diagnosis getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(Diagnosis diagnosis) {
        this.diagnosis = diagnosis;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "patientCode='" + patientCode + '\'' +
                ", dateOfBirthday=" + dateOfBirthday +
                ", initial='" + initial + '\'' +
                ", patientSex='" + patientSex + '\'' +
                ", confirmed=" + confirmed +
                '}';
    }
}
