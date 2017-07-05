package com.epam.adsm.dao.implementation;

import com.epam.adsm.dao.Dao;
import com.epam.adsm.dao.DaoException;
import com.epam.adsm.model.Drug;
import com.epam.adsm.model.Patient;
import com.epam.adsm.model.Receipt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecieptDao extends Dao {
    private static final Logger LOG = LoggerFactory.getLogger(RecieptDao.class);
    private static final String CREATE_RECIEPT = "INSERT INTO public.reciept(\n" +
            "            patient_id, drud_doze, reciept_status, reciept_date, \n" +
            "            drug_id)\n" +
            "    VALUES ( ?, ?, ?, ?, ?)";
    private static final String GET_RECIEPT_BY_PATIENT_ID = "SELECT * FROM public.reciept WHERE patient_id = ?";

    public Receipt createReceipt(Receipt receipt) throws DaoException {
        try (PreparedStatement statement = getConnection().prepareStatement(CREATE_RECIEPT, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, receipt.getPatient().getPatientCode());
            statement.setFloat(2, receipt.getDrugDoze());
            statement.setBoolean(3, receipt.isReceipt_status());
            statement.setDate(4, Date.valueOf(receipt.getRecieptDate()));
            statement.setInt(5, receipt.getDrug().getId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            while (resultSet.next()) {
                receipt.setId(resultSet.getInt(1));
            }
            resultSet.close();
        } catch (SQLException e) {
            LOG.error("Cannot createDiagnosis receipt in database", e);
            throw new DaoException("Cannot createDiagnosis receipt in database", e);
        }
        return receipt;
    }

    public List<Receipt> getReciepts(String patientId) throws DaoException {
        List<Receipt> receipts = new ArrayList<>();
        Receipt receipt = null;
        try (PreparedStatement statement = getConnection().prepareStatement(GET_RECIEPT_BY_PATIENT_ID)) {
            statement.setString(1, patientId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                receipt = pickRecieptFromResultSet(resultSet);
                receipts.add(receipt);
            }
            resultSet.close();
        } catch (SQLException e) {
            LOG.error("Cannot get receipts for patient from database", e);
            throw new DaoException("Cannot get receipts by patient id from database", e);
        }
        return receipts;
    }

    private Receipt pickRecieptFromResultSet(ResultSet resultSet) throws DaoException {
        Receipt receipt = new Receipt();
        try {
            Patient patient = new Patient();
            patient.setPatientCode(resultSet.getString(2));
            Drug drug = new Drug();
            drug.setId(resultSet.getInt(6));
            receipt.setId(resultSet.getInt(1));
            receipt.setPatient(patient);
            receipt.setDrugDoze(resultSet.getFloat(3));
            receipt.setReceipt_status(resultSet.getBoolean(4));
            receipt.setReceiptDate(resultSet.getDate(5).toLocalDate());
            receipt.setDrug(drug);
        } catch (SQLException e) {
            LOG.error("Cannot pick receipt from result set", e);
            throw new DaoException("Cannot pick receipt from resultSet", e);
        }
        return receipt;
    }
}
