package com.epam.adsm.dao.implementation;

import com.epam.adsm.dao.Dao;
import com.epam.adsm.dao.DaoException;
import com.epam.adsm.model.Drug;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DrugDao extends Dao {

    private static final Logger LOG = LoggerFactory.getLogger(DrugDao.class);
    private static final String GET_ALL_DRUGS = "SELECT * FROM public.drug ORDER BY drug_name ASC";

    public List<Drug> getAllDrugs() throws DaoException {
        List<Drug> drugsList = new ArrayList<>();
        Drug drug;
        try(PreparedStatement statement = getConnection().prepareStatement(GET_ALL_DRUGS)){
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                drug = pickDrugFromResultSet(resultSet);
                drugsList.add(drug);
            }
            resultSet.close();
        }catch (SQLException e){
            LOG.error("Cannot get all drugs from database",e);
            throw new DaoException("Cannot get all drugs from database",e);
        }
        return drugsList;
    }

    private Drug pickDrugFromResultSet(ResultSet resultSet) throws DaoException {
        Drug drug = new Drug();
        try {
            drug.setId(resultSet.getInt(1));
            drug.setName(resultSet.getString(2));
        }catch (SQLException e){
            LOG.error("Cannot pick drug from resultSet",e);
            throw new DaoException("Cannot pick drug from resultSet",e);
        }
        return drug;
    }
}
