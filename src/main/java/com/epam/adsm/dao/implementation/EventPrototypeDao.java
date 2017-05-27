package com.epam.adsm.dao.implementation;

import com.epam.adsm.dao.Dao;
import com.epam.adsm.dao.DaoException;
import com.epam.adsm.model.EventPrototype;
import org.joda.time.Duration;
import org.joda.time.Interval;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by akmatleu on 13.05.17.
 */
public class EventPrototypeDao extends Dao implements EntityDao<EventPrototype> {

    private static final String CREATE_EVENT_PROTOTYPE = "INSERT INTO public.event_prototype(event_prototype_name) VALUES (?)";
    private static final String FIND_BY_ID = "SELECT * FROM public.event_prototype WHERE event_prototype_id=?";
    private static final String GET_ALL_EVENTS_PROTOTYPE = "SELECT * FROM public.event_prototype ORDER BY event_prototype_id ASC";

    public List<EventPrototype> getAllEventsPrototype() throws DaoException{
        List<EventPrototype> eventPrototypes = new ArrayList<>();
        try(PreparedStatement statement = getConnection().prepareStatement(GET_ALL_EVENTS_PROTOTYPE)){
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                eventPrototypes.add(pickEventprototypeFromResultSet(resultSet));
            }
            resultSet.close();
        }catch (SQLException e){
            throw  new DaoException("Cannot get all events prototype",e);
        }
        return eventPrototypes;
    }
    @Override
    public EventPrototype create(EventPrototype eventPrototype) throws DaoException {
        try(PreparedStatement statement = getConnection().prepareStatement(CREATE_EVENT_PROTOTYPE,PreparedStatement.RETURN_GENERATED_KEYS)){
            statement.setString(1,eventPrototype.getEventPrototypeName());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            while (resultSet.next()){
                int id = resultSet.getInt(1);
                eventPrototype.setId(id);
            }
            resultSet.close();
        }catch (SQLException e){
            throw new DaoException("Cannot create event prototype",e);
        }
        return eventPrototype;
    }

    @Override
    public EventPrototype findById(int id) throws DaoException {
        EventPrototype eventPrototype = new EventPrototype();
        try(PreparedStatement statement = getConnection().prepareStatement(FIND_BY_ID)){
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                eventPrototype = pickEventprototypeFromResultSet(resultSet);
            }
            resultSet.close();
        }catch (SQLException e){
            throw new DaoException("Cannot find event prototype in database",e);
        }
        return eventPrototype;
    }

    @Override
    public void update(EventPrototype eventPrototype) throws DaoException {

    }

    @Override
    public void delete(EventPrototype eventPrototype) throws DaoException {

    }

    private EventPrototype pickEventprototypeFromResultSet(ResultSet resultSet) throws DaoException {
        EventPrototype eventPrototype = new EventPrototype();
        try {
            eventPrototype.setId(resultSet.getInt(1));
            eventPrototype.setEventPrototypeName(resultSet.getString(2));
            eventPrototype.setEventInterval(resultSet.getInt(3));
        }catch (SQLException e){
            throw new DaoException("Cannot create event prototype from result set",e);
        }
        return eventPrototype;
    }

}
