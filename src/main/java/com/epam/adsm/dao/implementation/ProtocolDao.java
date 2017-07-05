package com.epam.adsm.dao.implementation;

import com.epam.adsm.dao.Dao;
import com.epam.adsm.dao.DaoException;
import com.epam.adsm.model.EventPrototype;
import com.epam.adsm.model.Protocol;
import com.epam.adsm.model.TaskPrototype;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProtocolDao extends Dao {
    private static final Logger LOG = LoggerFactory.getLogger(ProtocolDao.class);
    private static final String GET_ALL_TASKS_PROTOTYPE_ID_BY_EVENT_PROTOTYPE = "SELECT * FROM public.protocol_events_tasks\n" +
            "WHERE event_prototype_id = ?";

    public List<Protocol> getAllTasksForEvent(EventPrototype eventPrototypeId) throws DaoException {
        List<Protocol> taskPrototypes = new ArrayList<>();
        try (PreparedStatement statement = getConnection().prepareStatement(GET_ALL_TASKS_PROTOTYPE_ID_BY_EVENT_PROTOTYPE)) {
            statement.setInt(1, eventPrototypeId.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                taskPrototypes.add(pickTaskPrototypeIdFromResultSet(resultSet));
            }
            resultSet.close();
        } catch (SQLException e) {
            LOG.error("Cannot get all tasks for event", e);
            throw new DaoException("Cannot get all task prototypes for event", e);
        }
        return taskPrototypes;
    }

    private Protocol pickTaskPrototypeIdFromResultSet(ResultSet resultSet) throws DaoException {
        Protocol protocol = new Protocol();
        try {
            TaskPrototype taskPrototype = new TaskPrototype();
            taskPrototype.setId(resultSet.getInt(3));
            protocol.setTaskPrototype(taskPrototype);
        } catch (SQLException e) {
            LOG.error("Cannot pick task prototype from result set", e);
            throw new DaoException("Cannot pick task prototype from resultSet", e);
        }
        return protocol;
    }
}
