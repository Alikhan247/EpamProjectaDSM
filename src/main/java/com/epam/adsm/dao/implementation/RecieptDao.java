package com.epam.adsm.dao.implementation;

import com.epam.adsm.dao.Dao;
import com.epam.adsm.dao.DaoException;
import com.epam.adsm.model.Drug;
import com.epam.adsm.model.Patient;
import com.epam.adsm.model.Reciept;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by akmatleu on 15.05.17.
 */
public class RecieptDao extends Dao implements EntityDao<Reciept> {

    private static final String CREATE_RECIEPT = "INSERT INTO public.reciept(\n" +
            "            patient_id, drud_doze, reciept_status, reciept_date, \n" +
            "            drug_id)\n" +
            "    VALUES ( ?, ?, ?, ?, ?)";

    private static final String FIND_BY_ID = "SELECT * FROM reciept WHERE reciept_id = ?";
    private static final String GET_RECIEPT_BY_PATIENT_ID = "SELECT * FROM public.reciept WHERE patient_id = ?";


    @Override
    public Reciept create(Reciept reciept) throws DaoException {
        try (PreparedStatement statement = getConnection().prepareStatement(CREATE_RECIEPT,PreparedStatement.RETURN_GENERATED_KEYS)){
            statement.setString(1,reciept.getPatient().getPatientCode());
            statement.setFloat(2,reciept.getDrugDoze());
            statement.setBoolean(3,reciept.isReciept_status());
            statement.setDate(4,Date.valueOf(reciept.getRecieptDate()));
            statement.setInt(5,reciept.getDrug().getId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            while (resultSet.next()){
                reciept.setId(resultSet.getInt(1));
            }
            resultSet.close();
        }catch (SQLException e){
            throw new DaoException("Cannot create reciept from db",e);
        }
        return reciept;
    }

    @Override
    public Reciept findById(int id) throws DaoException {
        Reciept reciept = null;
        try (PreparedStatement statement = getConnection().prepareStatement(FIND_BY_ID)) {
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                reciept = pickRecieptFromResultSet(reciept,resultSet);
            }
            resultSet.close();
        }catch (SQLException e) {
            throw new DaoException("Cannot find by id - "+id,e);
        }
        return reciept;
    }

    @Override
    public void update(Reciept reciept) throws DaoException {

    }

    @Override
    public void delete(Reciept reciept) throws DaoException {

    }

    public List<Reciept> getReciepts(String patientId) throws DaoException {
        List<Reciept> reciepts = new ArrayList<>();
        Reciept reciept = null;
        try (PreparedStatement statement = getConnection().prepareStatement(GET_RECIEPT_BY_PATIENT_ID)) {
            statement.setString(1,patientId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                reciept = pickRecieptFromResultSet(reciept,resultSet);
                reciepts.add(reciept);
            }
            resultSet.close();
        }catch (SQLException e){
            throw new DaoException("Cannot get reciept by patient id from db",e);
        }
        return reciepts;
    }

    private Reciept pickRecieptFromResultSet(Reciept reciept,ResultSet resultSet) throws DaoException{
        reciept = new Reciept();
        try {
            Patient patient = new Patient();
            patient.setPatientCode(resultSet.getString(2));
            Drug drug = new Drug();
            drug.setId(resultSet.getInt(6));

            reciept.setId(resultSet.getInt(1));
            reciept.setPatient(patient);
            reciept.setDrugDoze(resultSet.getFloat(3));
            reciept.setReciept_status(resultSet.getBoolean(4));
            reciept.setRecieptDate(resultSet.getDate(5).toLocalDate());
            reciept.setDrug(drug);
        }catch (SQLException e){
            throw new DaoException("Cannot create drug from resultSet",e);
        }
        return reciept;

    }

}
