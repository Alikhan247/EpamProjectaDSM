package com.epam.adsm.dao.implementation;

import com.epam.adsm.dao.Dao;
import com.epam.adsm.dao.DaoException;
import com.epam.adsm.model.EventPrototype;
import com.epam.adsm.model.Protocol;
import com.epam.adsm.model.TaskPrototype;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by akmatleu on 17.05.17.
 */
public class ProtocolDao extends Dao implements EntityDao<Protocol> {

    private static final String GET_ALL_TASKS_PROTOTYPE_ID_BY_EVENT_PROTOTYPE = "SELECT * FROM public.protocol_events_tasks\n" +
            "WHERE event_prototype_id = ?";



    public List<Protocol> getAllTasksForEvent(EventPrototype eventPrototypeId) throws DaoException {
        List<Protocol> taskPrototypes = new ArrayList<>();
        try(PreparedStatement statement = getConnection().prepareStatement(GET_ALL_TASKS_PROTOTYPE_ID_BY_EVENT_PROTOTYPE)){
            statement.setInt(1,eventPrototypeId.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                taskPrototypes.add(pickTaskPrototypeIdFromResultSet(resultSet));
            }
            resultSet.close();
        }catch (SQLException e){
            throw new DaoException("Cannot get all task prototypes for event",e);
        }
        return taskPrototypes;
    }

    @Override
    public Protocol create(Protocol protocol) throws DaoException {
        return null;
    }

    @Override
    public Protocol findById(int id) throws DaoException {
        return null;
    }

    @Override
    public void update(Protocol protocol) throws DaoException {

    }

    @Override
    public void delete(Protocol protocol) throws DaoException {

    }


    private Protocol pickTaskPrototypeIdFromResultSet(ResultSet resultSet) throws DaoException {
        Protocol protocol = new Protocol();
        try {
            EventPrototype eventPrototype = new EventPrototype();
            TaskPrototype taskPrototype = new TaskPrototype();
            eventPrototype.setId(resultSet.getInt(2));
            taskPrototype.setId(resultSet.getInt(3));
            protocol.setEventPrototype(eventPrototype);
            protocol.setTaskPrototype(taskPrototype);
        }catch (SQLException e){
            throw new DaoException("Cannot create task prototypes from resultSet",e);
        }
        return protocol;
    }

}
