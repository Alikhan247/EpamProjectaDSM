package com.epam.adsm.dao.implementation;

import com.epam.adsm.dao.Dao;
import com.epam.adsm.dao.DaoException;
import com.epam.adsm.model.DiagnosisDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DiagnosisDateDao extends Dao {

    private static final Logger LOG = LoggerFactory.getLogger(DiagnosisDateDao.class);
    private static final String GET_All_DATE = "SELECT factor_risk, localizatoin, releavence, clinical_form, mbt_status, \n" +
            "       patient_type, dst_status, administration_option, gender, role_option, adverse_status_option, activation_status_option\n" +
            "  FROM public.list_date";

    public DiagnosisDate getDiagnosisDate() throws DaoException {
        DiagnosisDate diagnosisDate = null;
        try (PreparedStatement statement = getConnection().prepareStatement(GET_All_DATE, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            ResultSet resultSet = statement.executeQuery();
            resultSet.beforeFirst();
            while (resultSet.next()) {
                resultSet.first();
                diagnosisDate = getColumnDateFromResultSet(resultSet);
            }
            resultSet.close();
        } catch (SQLException e) {
            LOG.error("Cannot get diagnosis date from database", e);
            throw new DaoException("Cannot get diagnosis date from database", e);
        }
        return diagnosisDate;
    }

    private DiagnosisDate getColumnDateFromResultSet(ResultSet resultSet) throws DaoException {
        DiagnosisDate diagnosisDate = new DiagnosisDate();
        List<String> clinicalForm = new ArrayList<>();
        List<String> riskFacror = new ArrayList<>();
        List<String> dstStatus = new ArrayList<>();
        List<String> localizations = new ArrayList<>();
        List<String> releavences = new ArrayList<>();
        List<String> mbtStatus = new ArrayList<>();
        List<String> patientTypes = new ArrayList<>();
        List<String> administrationOption = new ArrayList<>();
        List<String> gender = new ArrayList<>();
        List<String> roleOption = new ArrayList<>();
        List<String> adverseStatusOption = new ArrayList<>();
        List<String> activationStatusOption = new ArrayList<>();
        try {
            resultSet.beforeFirst();
            while (resultSet.next()) {
                if (resultSet.getString(1) != null) {
                    riskFacror.add(resultSet.getString(1));
                }
                if (resultSet.getString(2) != null) {
                    localizations.add(resultSet.getString(2));
                }
                if (resultSet.getString(3) != null) {
                    releavences.add(resultSet.getString(3));
                }
                if (resultSet.getString(4) != null) {
                    clinicalForm.add(resultSet.getString(4));
                }
                if (resultSet.getString(5) != null) {
                    mbtStatus.add(resultSet.getString(5));
                }
                if (resultSet.getString(6) != null) {
                    patientTypes.add(resultSet.getString(6));
                }
                if (resultSet.getString(7) != null) {
                    dstStatus.add(resultSet.getString(7));
                }
                if (resultSet.getString(8)!= null) {
                    administrationOption.add(resultSet.getString(8));
                }
                if (resultSet.getString(9)!= null) {
                    gender.add(resultSet.getString(9));
                }
                if (resultSet.getString(10)!= null) {
                    roleOption.add(resultSet.getString(10));
                }
                if (resultSet.getString(11)!=null) {
                    adverseStatusOption.add(resultSet.getString(11));
                }
                if (resultSet.getString(12)!=null) {
                    activationStatusOption.add(resultSet.getString(12));
                }
            }
            diagnosisDate.setClinicalForm(clinicalForm);
            diagnosisDate.setRiskFactors(riskFacror);
            diagnosisDate.setDstStatus(dstStatus);
            diagnosisDate.setTypePatient(patientTypes);
            diagnosisDate.setLocalizations(localizations);
            diagnosisDate.setReleavences(releavences);
            diagnosisDate.setMbtStatus(mbtStatus);
            diagnosisDate.setAdministrationOption(administrationOption);
            diagnosisDate.setGender(gender);
            diagnosisDate.setRoleOption(roleOption);
            diagnosisDate.setAdverseStatusOption(adverseStatusOption);
            diagnosisDate.setActivationStatusOption(activationStatusOption);
        } catch (SQLException e) {
            LOG.error("Cannot get column date from result set", e);
            throw new DaoException("Cannot take date column from result set", e);
        }
        return diagnosisDate;
    }

}
