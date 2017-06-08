package com.epam.adsm.dao.implementation;

import com.epam.adsm.dao.Dao;
import com.epam.adsm.dao.DaoException;
import com.epam.adsm.model.Diagnosis;
import com.epam.adsm.model.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by akmatleu on 11.05.17.
 */
public class DiagnosisDao extends Dao {

    private static final Logger LOG = LoggerFactory.getLogger(DiagnosisDao.class);
    private static final String INSERT_DIAGNOSIS = "INSERT INTO public.diagnosis(\n" +
            "            risk_factor, localization_disease, prevalence, \n" +
            "            clinical_form, mbt_status, patient_type, dst_status, patient_id)\n" +
            "    VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String GET_ALL_DIAGNOSIS = "SELECT * FROM public.diagnosis";

    public List<Diagnosis> getAllDiagnosis() throws DaoException {
        List<Diagnosis> diagnosisList = new ArrayList<>();
        Diagnosis diagnosis;
        try (PreparedStatement statement = getConnection().prepareStatement(GET_ALL_DIAGNOSIS)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                diagnosis = pickDiagnosisFromResultSet(resultSet);
                diagnosisList.add(diagnosis);
            }
            resultSet.close();
        } catch (SQLException e) {
            LOG.error("Cannot get all diagnosis from database",e);
            throw new DaoException("Cannot get all diagnosis from database", e);
        }
        return diagnosisList;
    }

    public Diagnosis createDiagnosis(Diagnosis diagnosis) throws DaoException {
        try (PreparedStatement statement = getConnection().prepareStatement(INSERT_DIAGNOSIS, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, diagnosis.getRiskFactor());
            statement.setString(2, diagnosis.getLocalizationDisease());
            statement.setString(3, diagnosis.getPrevalence());
            statement.setString(4, diagnosis.getClinicalForm());
            statement.setString(5, diagnosis.getMbtStatus());
            statement.setString(6, diagnosis.getPatientType());
            statement.setString(7, diagnosis.getDstStatus());
            statement.setString(8, diagnosis.getPatient().getPatientCode());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                diagnosis.setId(id);
            }
            resultSet.close();
        } catch (SQLException e) {
            LOG.error("Cannot createDrugAdministration diagnosis in database",e);
            throw new DaoException("Cannot insert diagnosis in database", e);
        }
        return diagnosis;
    }

    private Diagnosis pickDiagnosisFromResultSet(ResultSet resultSet) throws DaoException {
        Diagnosis diagnosis = new Diagnosis();
        Patient patient = new Patient();
        try {
            diagnosis.setId(resultSet.getInt(1));
            diagnosis.setRiskFactor(resultSet.getString(2));
            diagnosis.setLocalizationDisease(resultSet.getString(3));
            diagnosis.setPrevalence(resultSet.getString(4));
            diagnosis.setClinicalForm(resultSet.getString(5));
            diagnosis.setMbtStatus(resultSet.getString(6));
            diagnosis.setPatientType(resultSet.getString(7));
            diagnosis.setDstStatus(resultSet.getString(8));
            patient.setPatientCode(resultSet.getString(9));
            diagnosis.setPatient(patient);
        } catch (SQLException e) {
            LOG.error("Cannot createDrugAdministration diganosis from result set",e);
            throw new DaoException("Cannot createDrugAdministration diagnosis from resultSet", e);
        }
        return diagnosis;
    }

}
