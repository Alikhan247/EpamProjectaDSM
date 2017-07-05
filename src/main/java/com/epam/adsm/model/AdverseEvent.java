package com.epam.adsm.model;

public class AdverseEvent extends BaseEntity {

    private String adverseName;
    private java.time.LocalDate adverseDate;
    private String adverseComment;
    private Boolean adverseAlcohol;
    private Boolean adverseDrug;
    private Staff staff;
    private Patient patient;
    private String adverseStatus;

    public AdverseEvent() {
    }

    public String getAdverseName() {
        return adverseName;
    }

    public void setAdverseName(String adverseName) {
        this.adverseName = adverseName;
    }

    public java.time.LocalDate getAdverseDate() {
        return adverseDate;
    }

    public void setAdverseDate(java.time.LocalDate adverseDate) {
        this.adverseDate = adverseDate;
    }

    public String getAdverseComment() {
        return adverseComment;
    }

    public void setAdverseComment(String adverseComment) {
        this.adverseComment = adverseComment;
    }

    public Boolean getAdverseAlcohol() {
        return adverseAlcohol;
    }

    public void setAdverseAlcohol(Boolean adverseAlcohol) {
        this.adverseAlcohol = adverseAlcohol;
    }

    public Boolean getAdverseDrug() {
        return adverseDrug;
    }

    public void setAdverseDrug(Boolean adverseDrug) {
        this.adverseDrug = adverseDrug;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getAdverseStatus() {
        return adverseStatus;
    }

    public void setAdverseStatus(String adverseStatus) {
        this.adverseStatus = adverseStatus;
    }

}
