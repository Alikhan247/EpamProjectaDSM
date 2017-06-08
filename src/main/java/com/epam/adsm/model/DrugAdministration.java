package com.epam.adsm.model;

/**
 * Created by akmatleu on 15.05.17.
 */
public class DrugAdministration extends BaseEntity {

    private Receipt receipt;
    private Patient patient;
    private String drugStatus;
    private java.time.LocalDate drugAdministraionDate;

    public DrugAdministration() {}

    public Receipt getReceipt() {
        return receipt;
    }

    public void setReceipt(Receipt receipt) {
        this.receipt = receipt;
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

}
