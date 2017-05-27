package com.epam.adsm.dao.implementation;

import com.epam.adsm.dao.Dao;
import com.epam.adsm.dao.DaoException;
import com.epam.adsm.model.DrugAdministration;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 * Created by akmatleu on 15.05.17.
 */
public class DrugAdministraionDao extends Dao implements EntityDao<DrugAdministration> {

    private static final String CREATE_DRUG_ADMINISTRAION = "INSERT INTO public.drug_administration(\n" +
            "            recepiet_id, patient_id, drug_status, \n" +
            "            drug_administration_date,drug_administration_comment)\n" +
            "    VALUES (?, ?, ?, ?,?)";



    @Override
    public DrugAdministration create(DrugAdministration drugAdministration) throws DaoException {
        try(PreparedStatement statement = getConnection().prepareStatement(CREATE_DRUG_ADMINISTRAION,PreparedStatement.RETURN_GENERATED_KEYS)){
            statement.setInt(1,drugAdministration.getReciept().getId());
            statement.setString(2,drugAdministration.getPatient().getPatientCode());
            statement.setString(3,drugAdministration.getDrugStatus());
            statement.setDate(4, Date.valueOf(drugAdministration.getDrugAdministraionDate()));
            statement.setString(5,drugAdministration.getDrugAdministaionComment());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            while (resultSet.next()){
                drugAdministration.setId(resultSet.getInt(1));
            }
            resultSet.close();
        }catch (SQLException e) {
            throw  new DaoException("Cannot create drug administraion in db",e);
        }
        return drugAdministration;
    }

    @Override
    public DrugAdministration findById(int id) throws DaoException {
        return null;
    }

    @Override
    public void update(DrugAdministration drugAdministration) throws DaoException {

    }

    @Override
    public void delete(DrugAdministration drugAdministration) throws DaoException {

    }
}
