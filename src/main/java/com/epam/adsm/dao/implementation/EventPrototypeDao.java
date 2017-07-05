package com.epam.adsm.dao.implementation;

import com.epam.adsm.dao.Dao;
import com.epam.adsm.dao.DaoException;
import com.epam.adsm.model.EventPrototype;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class EventPrototypeDao extends Dao {

    private static final Logger LOG = LoggerFactory.getLogger(EventPrototypeDao.class);
    private static final String GET_ALL_EVENTS_PROTOTYPE = "SELECT * FROM public.event_prototype ORDER BY event_prototype_id ASC";

    public List<EventPrototype> getAllEventsPrototype() throws DaoException {
        List<EventPrototype> eventPrototypes = new ArrayList<>();
        try (PreparedStatement statement = getConnection().prepareStatement(GET_ALL_EVENTS_PROTOTYPE)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                eventPrototypes.add(pickEventprototypeFromResultSet(resultSet));
            }
            resultSet.close();
        } catch (SQLException e) {
            LOG.error("Cannot get all event prototypes from database", e);
            throw new DaoException("Cannot get all event prototypes from database", e);
        }
        return eventPrototypes;
    }

    private EventPrototype pickEventprototypeFromResultSet(ResultSet resultSet) throws DaoException {
        EventPrototype eventPrototype = new EventPrototype();
        try {
            eventPrototype.setId(resultSet.getInt(1));
            eventPrototype.setEventInterval(resultSet.getInt(3));
        } catch (SQLException e) {
            LOG.error("Cannot pick event prototype from result set", e);
            throw new DaoException("Cannot pick event prototype from result set", e);
        }
        return eventPrototype;
    }
}
