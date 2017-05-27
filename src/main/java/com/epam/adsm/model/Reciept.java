package com.epam.adsm.model;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import java.sql.Date;

/**
 * Created by akmatleu on 15.05.17.
 */
public class Reciept  extends BaseEntity{

    private Patient patient;
    private float drugDoze;
    private boolean reciept_status;
    private java.time.LocalDate recieptDate;
    private Drug drug;


    public Reciept() {}

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public float getDrugDoze() {
        return drugDoze;
    }

    public void setDrugDoze(float drugDoze) {
        this.drugDoze = drugDoze;
    }

    public boolean isReciept_status() {
        return reciept_status;
    }

    public void setReciept_status(boolean reciept_status) {
        this.reciept_status = reciept_status;
    }

    public Drug getDrug() {
        return drug;
    }

    public void setDrug(Drug drug) {
        this.drug = drug;
    }

    public java.time.LocalDate getRecieptDate() {
        return recieptDate;
    }

    public void setRecieptDate(java.time.LocalDate recieptDate) {
        this.recieptDate = recieptDate;
    }

    @Override
    public String toString() {
        return "Reciept{" +
                ", drugDoze=" + drugDoze +
                ", reciept_status=" + reciept_status +
                '}';
    }
}
