package com.epam.adsm.dao.implementation;

import com.epam.adsm.dao.Dao;
import com.epam.adsm.dao.DaoException;
import com.epam.adsm.model.DrugAdministration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DrugAdministraionDao extends Dao {
    private static final Logger LOG = LoggerFactory.getLogger(DrugAdministraionDao.class);
    private static final String CREATE_DRUG_ADMINISTRATION = "INSERT INTO public.drug_administration(\n" +
            "            recepiet_id, patient_id, drug_status, \n" +
            "            drug_administration_date)\n" +
            "    VALUES (?,?,?,?)";

    public DrugAdministration createDrugAdministration(DrugAdministration drugAdministration) throws DaoException {
        try (PreparedStatement statement = getConnection().prepareStatement(CREATE_DRUG_ADMINISTRATION, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, drugAdministration.getReceipt().getId());
            statement.setString(2, drugAdministration.getPatient().getPatientCode());
            statement.setString(3, drugAdministration.getDrugStatus());
            statement.setDate(4, Date.valueOf(drugAdministration.getDrugAdministraionDate()));
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            while (resultSet.next()) {
                drugAdministration.setId(resultSet.getInt(1));
            }
            resultSet.close();
        } catch (SQLException e) {
            LOG.error("Cannot createDiagnosis drug administration in database", e);
            throw new DaoException("Cannot createDiagnosis drug administration in database", e);
        }
        return drugAdministration;
    }
}
