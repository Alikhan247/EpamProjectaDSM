package com.epam.adsm.dao.implementation;

import com.epam.adsm.dao.Dao;
import com.epam.adsm.dao.DaoException;
import com.epam.adsm.model.DiagnosisDate;
import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by akmatleu on 20.05.17.
 */
public class DiagnosisDateDao extends Dao implements EntityDao<DiagnosisDate> {


    private static final String GET_All_DATE = "SELECT factor_risk, localizatoin, releavence, clinical_form, mbt_status, \n" +
            "       patient_type, dst_status\n" +
            "  FROM public.list_date";



    public DiagnosisDate getDiagnosisDate() throws DaoException {
        DiagnosisDate diagnosisDate = null;
        try(PreparedStatement statement = getConnection().prepareStatement(GET_All_DATE,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE)){
            ResultSet resultSet = statement.executeQuery();
            resultSet.beforeFirst();
            while (resultSet.next()){
                resultSet.first();
           diagnosisDate = getColumnDateFromResultSet(diagnosisDate,resultSet);
            }
            resultSet.close();
        }catch (SQLException e){
            throw new DaoException("Cannot get diagnosis date from db ",e);
        }
        return diagnosisDate;
    }


    @Override
    public DiagnosisDate create(DiagnosisDate diagnosisDate) throws DaoException {
        return null;
    }

    @Override
    public DiagnosisDate findById(int id) throws DaoException {
        return null;
    }

    @Override
    public void update(DiagnosisDate diagnosisDate) throws DaoException {

    }

    @Override
    public void delete(DiagnosisDate diagnosisDate) throws DaoException {

    }

    private DiagnosisDate getColumnDateFromResultSet(DiagnosisDate diagnosisDate,ResultSet resultSet) throws DaoException{
        diagnosisDate = new DiagnosisDate();
        List<String> clinicalForm = new ArrayList<>();
        List<String> riskFacror = new ArrayList<>();
        List<String> dstStatus = new ArrayList<>();
        List<String> localizations = new ArrayList<>();
        List<String> releavences = new ArrayList<>();
        List<String> mbtStatus = new ArrayList<>();
        List<String> patientTypes = new ArrayList<>();
        try{
            resultSet.beforeFirst();
            while (resultSet.next()){

                if(resultSet.getString(1)!=null){

                    riskFacror.add(resultSet.getString(1));
                }

                if(resultSet.getString(2)!=null){
                    localizations.add(resultSet.getString(2));
                }

                if(resultSet.getString(3)!=null){
                    releavences.add(resultSet.getString(3));
                }
                if(resultSet.getString(4)!=null) {
                    clinicalForm.add(resultSet.getString(4));
                }

                if(resultSet.getString(5)!=null){

                    mbtStatus.add(resultSet.getString(5));
                }
                if(resultSet.getString(6)!=null){

                    patientTypes.add(resultSet.getString(6));
                }
                if(resultSet.getString(7)!=null){
                    dstStatus.add(resultSet.getString(7));
                }
            }

            diagnosisDate.setClinicalForm(clinicalForm);
            diagnosisDate.setRiskFactors(riskFacror);
            diagnosisDate.setDstStatus(dstStatus);
            diagnosisDate.setTypePatient(patientTypes);
            diagnosisDate.setLocalizations(localizations);
            diagnosisDate.setReleavences(releavences);
            diagnosisDate.setMbtStatus(mbtStatus);

        }catch (SQLException e){
            throw new DaoException("Cannot take date column from result set",e);
        }
        return diagnosisDate;
    }

}
