package com.epam.adsm.dao.implementation;

import com.epam.adsm.dao.Dao;
import com.epam.adsm.dao.DaoException;
import com.epam.adsm.model.Drug;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by akmatleu on 09.05.17.
 */
public class DrugDao extends Dao implements EntityDao<Drug> {

    private static final String INSERT_DRUG = "INSERT INTO public.drug(drug_name) VALUES (?)";
    private static final String FIND_BY_ID = "SELECT * FROM drug WHERE drug_id = ?";
    private static final String UPDATE_NAME_DRUG = "UPDATE public.drug SET drug_name=? WHERE drug_id=?";
    private static final String DELETE_DRUG_BY_ID = "DELETE FROM public.drug WHERE drug_id =?";
    private static final String GET_ALL_DRUGS = "SELECT * FROM public.drug ORDER BY drug_name ASC";

    @Override
    public Drug create(Drug drug) throws DaoException {
        try (PreparedStatement statement = getConnection().prepareStatement(INSERT_DRUG, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setString(1,drug.getName());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                drug.setId(id);
            }
            resultSet.close();
        } catch (SQLException e){
            throw new DaoException("Cannot create drug in database",e);
        }
        return drug;
    }

    @Override
    public Drug findById(int id) throws DaoException {
        Drug drug =null;
        try (PreparedStatement statement = getConnection().prepareStatement(FIND_BY_ID)) {
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                drug = pickDrugFromResultSet(drug,resultSet);
            }
            resultSet.close();
        }catch (SQLException e) {
            throw new DaoException("Cannot find by id - "+id,e);
        }
        return drug;
    }

    @Override
    public void update(Drug drug) throws DaoException {
        try(PreparedStatement statement = getConnection().prepareStatement(UPDATE_NAME_DRUG)){
            statement.setString(1,drug.getName());
            statement.setInt(2,drug.getId());
            statement.execute();
        }catch (SQLException e){
            throw new DaoException("Cannot update drug name",e);
        }
    }

    @Override
    public void delete(Drug drug) throws DaoException {
        try(PreparedStatement statement = getConnection().prepareStatement(DELETE_DRUG_BY_ID)){
            statement.setInt(1,drug.getId());
            statement.execute();
        }catch (SQLException e){
            throw new DaoException("Cannot delete drug",e);
        }

    }

    public List<Drug> getAllDrugs() throws DaoException {
        List<Drug> drugsList = new ArrayList<>();
        Drug drug = null;
        try(PreparedStatement statement = getConnection().prepareStatement(GET_ALL_DRUGS)){
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                drug = pickDrugFromResultSet(drug,resultSet);
                drugsList.add(drug);
            }
            resultSet.close();
        }catch (SQLException e){
            throw new DaoException("Cannot get all drugs",e);
        }
        return drugsList;
    }

    private Drug pickDrugFromResultSet(Drug drug,ResultSet resultSet) throws DaoException {
        drug = new Drug();
        try {
            drug.setId(resultSet.getInt(1));
            drug.setName(resultSet.getString(2));
        }catch (SQLException e){
            throw new DaoException("Cannot create drug from resultSet",e);
        }
        return drug;
    }
}
