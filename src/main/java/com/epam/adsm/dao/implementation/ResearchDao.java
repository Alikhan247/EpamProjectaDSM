package com.epam.adsm.dao.implementation;

import com.epam.adsm.dao.Dao;
import com.epam.adsm.dao.DaoException;
import com.epam.adsm.model.Patient;
import com.epam.adsm.model.Research;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by akmatleu on 13.05.17.
 */
public class ResearchDao extends Dao implements EntityDao<Research> {
    private static final Logger LOG = LoggerFactory.getLogger(ResearchDao.class);
    private static final String CREATE_RESEARCH = "INSERT INTO public.research(\n" +
            "            enrollment_date, activation_date, activation_status, \n" +
            "            research_progress, patient_id)\n" +
            "    VALUES ( ?, ?, ?, ?, ?)";
    private static final String GET_ALL_RESEARCH = "SELECT * FROM public.research";
    private static final String FIND_BY_ID_PATIENT_CODE = "SELECT * FROM public.research WHERE patient_id = ?";
    private static final String FIND_BY_EVENT_ID = "SELECT research_id FROM event WHERE event_id = ?";
    private static final String FIND_BY_ID = "SELECT * FROM public.research WHERE research_id = ?";
    private static final String UPDATE_RESEARCH_PROGRESS = "UPDATE public.research SET research_progress=? WHERE research_id=?";
    private static final String UPDATE_RESEARCH_STATUS = "UPDATE public.research SET activation_status=? WHERE research_id=?";

    public Research findByEventId(int id) throws DaoException {
        Research research;
        try (PreparedStatement statement = getConnection().prepareStatement(FIND_BY_EVENT_ID)) {
            statement.setInt(1, id);
            int researchId = 0;
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                researchId = resultSet.getInt(1);
            }
            research = findById(researchId);
            resultSet.close();
        } catch (SQLException e) {
            LOG.error("Cannot find research by event id from database", e);
            throw new DaoException("Cannot find research  by event id from database", e);
        }
        return research;
    }

    public List<Research> getAllResearch() throws DaoException {
        List<Research> researchList = new ArrayList<>();
        Research research = null;
        try (PreparedStatement statement = getConnection().prepareStatement(GET_ALL_RESEARCH)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                research = pickResearchFromResultSet(research, resultSet);
                researchList.add(research);
            }
            resultSet.close();
        } catch (SQLException e) {
            LOG.error("Cannot get all research from database", e);
            throw new DaoException("Cannot get all patient from database", e);
        }
        return researchList;
    }

    @Override
    public Research create(Research research) throws DaoException {
        try (PreparedStatement statement = getConnection().prepareStatement(CREATE_RESEARCH, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setDate(1, Date.valueOf(research.getEnrollmentDate()));
            statement.setDate(2, Date.valueOf(research.getActivationDate()));
            statement.setBoolean(3, research.getActivationStatus());
            statement.setDouble(4, research.getResearchProgress());
            statement.setString(5, research.getPatient().getPatientCode());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            while (resultSet.next()) {
                research.setId(resultSet.getInt(1));
            }
            resultSet.close();
        } catch (SQLException e) {
            LOG.error("Cannot createDiagnosis research in database", e);
            throw new DaoException("Cannot createDiagnosis  research in db", e);
        }
        return research;
    }

    public Research findByPatientCode(String patientCode) throws DaoException {
        Research research = new Research();
        try (PreparedStatement statement = getConnection().prepareStatement(FIND_BY_ID_PATIENT_CODE)) {
            statement.setString(1, patientCode);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                research = pickResearchFromResultSet(research, resultSet);
            }
            resultSet.close();
        } catch (SQLException e) {
            LOG.error("Cannot find research by patient code in database", e);
            throw new DaoException("Cannot find research by patient code in database", e);
        }
        return research;
    }

    @Override
    public Research findById(int id) throws DaoException {
        Research research = null;
        try (PreparedStatement statement = getConnection().prepareStatement(FIND_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                research = pickResearchFromResultSet(research, resultSet);
            }
        } catch (SQLException e) {
            LOG.error("Cannot find research by id from database", e);
            throw new DaoException("Cannot find research by id from database", e);
        }
        return research;
    }

    @Override
    public void update(Research research) throws DaoException {
        try (PreparedStatement statement = getConnection().prepareStatement(UPDATE_RESEARCH_PROGRESS)) {
            statement.setDouble(1, research.getResearchProgress());
            statement.setInt(2, research.getId());
            statement.execute();
        } catch (SQLException e) {
            LOG.error("Cannot updateTask research in database", e);
            throw new DaoException("Cannot updateTask research in database", e);
        }
    }

    public void setUpdateResearchStatus(Research research) throws DaoException {
        try (PreparedStatement statement = getConnection().prepareStatement(UPDATE_RESEARCH_STATUS)) {
            statement.setBoolean(1, research.getActivationStatus());
            statement.setInt(2, research.getId());
            statement.execute();
        } catch (SQLException e) {
            LOG.error("Cannot updateTask research status in database", e);
            throw new DaoException("Cannot updateTask research status in database", e);
        }
    }

    private Research pickResearchFromResultSet(Research research, ResultSet resultSet) throws DaoException {
        research = new Research();
        Patient patient = new Patient();
        try {
            research.setId(resultSet.getInt(1));
            research.setEnrollmentDate(resultSet.getDate(2).toLocalDate());
            research.setActivationDate(resultSet.getDate(3).toLocalDate());
            research.setActivationStatus(resultSet.getBoolean(4));
            research.setResearchProgress(resultSet.getDouble(6));
            patient.setPatientCode(resultSet.getString(5));
            research.setPatient(patient);
        } catch (SQLException e) {
            LOG.error("Cannot pick research from result set", e);
            throw new DaoException("Cannot pick research from resultSet", e);
        }
        return research;
    }
}
