package com.epam.adsm.model;

/**
 * Created by akmatleu on 15.05.17.
 */
public class Receipt extends BaseEntity{

    private Patient patient;
    private float drugDoze;
    private boolean receipt_status;
    private java.time.LocalDate recieptDate;
    private Drug drug;

    public Receipt() {}

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

    public boolean isReceipt_status() {
        return receipt_status;
    }

    public void setReceipt_status(boolean receipt_status) {
        this.receipt_status = receipt_status;
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

    public void setReceiptDate(java.time.LocalDate receiptDate) {
        this.recieptDate = receiptDate;
    }

}
