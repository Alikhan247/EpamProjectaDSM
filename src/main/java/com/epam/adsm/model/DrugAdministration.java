package com.epam.adsm.model;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import java.sql.Date;

/**
 * Created by akmatleu on 15.05.17.
 */
public class DrugAdministration  extends BaseEntity{

    private Reciept reciept;
    private Patient patient;
    private String drugStatus;
    private java.time.LocalDate drugAdministraionDate;
    private String drugAdministaionComment;


    public DrugAdministration() {}


    public Reciept getReciept() {
        return reciept;
    }

    public void setReciept(Reciept reciept) {
        this.reciept = reciept;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getDrugStatus() {
        return drugStatus;
    }

    public void setDrugStatus(String drugStatus) {
        this.drugStatus = drugStatus;
    }

    public java.time.LocalDate getDrugAdministraionDate() {
        return drugAdministraionDate;
    }

    public void setDrugAdministraionDate(java.time.LocalDate drugAdministraionDate) {
        this.drugAdministraionDate = drugAdministraionDate;
    }

    public String getDrugAdministaionComment() {
        return drugAdministaionComment;
    }

    public void setDrugAdministaionComment(String drugAdministaionComment) {
        this.drugAdministaionComment = drugAdministaionComment;
    }

    @Override
    public String toString() {
        return "DrugAdministration{" +
                ", drugStatus='" + drugStatus + '\'' +
                ", drugAdministraionDate=" + drugAdministraionDate +
                ", drugAdministaionComment='" + drugAdministaionComment + '\'' +
                '}';
    }
}
