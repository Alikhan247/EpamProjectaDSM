package com.epam.adsm.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by akmatleu on 20.05.17.
 */
public class DiagnosisDate {

    private List<String> riskFactors = new ArrayList<>();
    private List<String> clinicalForm = new ArrayList<>();
    private List<String> typePatient = new ArrayList<>();
    private List<String> dstStatus = new ArrayList<>();
    List<String> localizations = new ArrayList<>();
    List<String> releavences = new ArrayList<>();
    List<String> mbtStatus = new ArrayList<>();

    public DiagnosisDate(){}

    public List<String> getLocalizations() {
        return localizations;
    }

    public void setLocalizations(List<String> localizations) {
        this.localizations = localizations;
    }

    public List<String> getReleavences() {
        return releavences;
    }

    public void setReleavences(List<String> releavences) {
        this.releavences = releavences;
    }

    public List<String> getMbtStatus() {
        return mbtStatus;
    }

    public void setMbtStatus(List<String> mbtStatus) {
        this.mbtStatus = mbtStatus;
    }

    public List<String> getRiskFactors() {
        return riskFactors;
    }

    public List<String> getClinicalForm() {
        return clinicalForm;
    }

    public List<String> getTypePatient() {
        return typePatient;
    }

    public List<String> getDstStatus() {
        return dstStatus;
    }

    public void setRiskFactors(List<String> riskFactors) {
        this.riskFactors = riskFactors;
    }

    public void setClinicalForm(List<String> clinicalForm) {
        this.clinicalForm = clinicalForm;
    }

    public void setTypePatient(List<String> typePatient) {
        this.typePatient = typePatient;
    }

    public void setDstStatus(List<String> dstStatus) {
        this.dstStatus = dstStatus;
    }
}
