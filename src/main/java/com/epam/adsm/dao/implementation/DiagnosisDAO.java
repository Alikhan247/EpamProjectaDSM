package com.epam.adsm.dao.implementation;

import com.epam.adsm.dao.Dao;
import com.epam.adsm.dao.DaoException;
import com.epam.adsm.model.Diagnosis;
import com.epam.adsm.model.Patient;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by akmatleu on 11.05.17.
 */
public class DiagnosisDAO extends Dao implements EntityDao<Diagnosis> {

    private static final String INSERT_DIAGNOSIS = "INSERT INTO public.diagnosis(\n" +
            "            risk_factor, localization_disease, prevalence, \n" +
            "            clinical_form, mbt_status, patient_type, dst_status, patient_id)\n" +
            "    VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String FIND_BY_ID = "SELECT * FROM public.diagnosis WHERE diagnosis_id = ?";

    private static final String GET_ALL_DIAGNOSIS = "SELECT * FROM public.diagnosis";


    public List<Diagnosis> getAllDiagnosis() throws DaoException {
        List<Diagnosis> diagnosisList = new ArrayList<>();
        Diagnosis diagnosis = null;
        try(PreparedStatement statement = getConnection().prepareStatement(GET_ALL_DIAGNOSIS)){
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                diagnosis = pickDiagnosisFromResultSet(diagnosis,resultSet);
                diagnosisList.add(diagnosis);
            }
            resultSet.close();
        }catch (SQLException e){
            throw new DaoException("Cannot get all drugs",e);
        }
        return diagnosisList;

    }


    @Override
    public Diagnosis create(Diagnosis diagnosis) throws DaoException {
        try (PreparedStatement statement = getConnection().prepareStatement(INSERT_DIAGNOSIS, PreparedStatement.RETURN_GENERATED_KEYS)){
            statement.setString(1,diagnosis.getRiskFactor());
            statement.setString(2,diagnosis.getLocalizationDisease());
            statement.setString(3,diagnosis.getPrevalence());
            statement.setString(4,diagnosis.getClinicalForm());
            statement.setString(5,diagnosis.getMbtStatus());
            statement.setString(6,diagnosis.getPatientType());
            statement.setString(7,diagnosis.getDstStatus());
            statement.setString(8,diagnosis.getPatient().getPatientCode());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            while (resultSet.next()){
              int id = resultSet.getInt(1);
              diagnosis.setId(id);
            }
            resultSet.close();
        }catch (SQLException e){
            throw new DaoException("Cannot insert diagnosis in db",e);
        }
        return diagnosis;
    }

    @Override
    public Diagnosis findById(int id) throws DaoException {
       Diagnosis diagnosis = new Diagnosis();
       try (PreparedStatement statement = getConnection().prepareStatement(FIND_BY_ID)){
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                diagnosis = pickDiagnosisFromResultSet(diagnosis,resultSet);
            }
            resultSet.close();
       }catch (SQLException e){
           throw  new DaoException("Cannot find diagnosis from db",e);
       }
        return diagnosis;
    }

    @Override
    public void update(Diagnosis diagnosis) throws DaoException {

    }

    @Override
    public void delete(Diagnosis diagnosis) throws DaoException {

    }


    private Diagnosis pickDiagnosisFromResultSet(Diagnosis diagnosis,ResultSet resultSet) throws DaoException {
        diagnosis = new Diagnosis();
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
        }catch (SQLException e){
            throw new DaoException("Cannot create diagnosis from resultSet",e);
        }
        return diagnosis;
    }


}
