package com.epam.adsm.dao.implementation;

import com.epam.adsm.dao.Dao;
import com.epam.adsm.dao.DaoException;
import com.epam.adsm.model.Event;
import com.epam.adsm.model.EventPrototype;
import com.epam.adsm.model.Research;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by akmatleu on 17.05.17.
 */
public class EventDao extends Dao implements EntityDao<Event> {
    private static final Logger LOG = LoggerFactory.getLogger(EventDao.class);
    private static final String CREATE_EVENT = "INSERT INTO public.event(\n" +
            "             event_date, event_progress, research_id, event_prototype_id)\n" +
            "    VALUES ( ?, ?, ?, ?)";
    private static final String GET_RESEARCH_EVENTS = "SELECT event_id, event_date, event_progress, research_id, event_prototype_id\n" +
            "  FROM public.event WHERE research_id = ?";
    private static final String GET_RESEARCH_EVENT = "SELECT task_prototype.task_name, task.task_progress, event.event_date,task.task_id\n" +
            "FROM task_prototype\n" +
            "JOIN task\n" +
            "ON task.task_prototype_id = task_prototype.task_prototype_id\n" +
            "JOIN event\n" +
            "ON event.event_id = task.event_id\n" +
            "WHERE event.event_id = ?";

    public List<Event> getAllEventsFromResearch(Research research) throws DaoException {
        List<Event> allEvents = new ArrayList<>();
        Event event;
        try (PreparedStatement statement = getConnection().prepareStatement(GET_RESEARCH_EVENTS)) {
            statement.setInt(1, research.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                event = pickEventFromResultSet(resultSet);
                allEvents.add(event);
            }
            resultSet.close();
        } catch (SQLException e) {
            LOG.error("Cannot get all research events from database", e);
            throw new DaoException("Cannot get all events from database", e);
        }
        return allEvents;
    }

    @Override
    public Event create(Event event) throws DaoException {
        try (PreparedStatement statement = getConnection().prepareStatement(CREATE_EVENT, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setDate(1, Date.valueOf(event.getEventDate()));
            statement.setDouble(2, event.getEventProgress());
            statement.setInt(3, event.getResearch().getId());
            statement.setInt(4, event.getEventPrototype().getId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            while (resultSet.next()) {
                event.setId(resultSet.getInt(1));
            }
            resultSet.close();
        } catch (SQLException e) {
            LOG.error("Cannot create event in database", e);
            throw new DaoException("Cannot create event in database", e);
        }
        return event;
    }

    @Override
    public Event findById(int id) throws DaoException {
        Event event = new Event();
        List<String> tasksName = new ArrayList<>();
        List<Integer> tasksProgress = new ArrayList<>();
        List<Integer> tasksId = new ArrayList<>();
        java.time.LocalDate eventDate = null;
        try (PreparedStatement statement = getConnection().prepareStatement(GET_RESEARCH_EVENT)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tasksName.add(resultSet.getString(1));
                tasksProgress.add(resultSet.getInt(2));
                eventDate = (resultSet.getDate(3).toLocalDate());
                tasksId.add(resultSet.getInt(4));
            }
            event.setTasksName(tasksName);
            event.setTaskProgress(tasksProgress);
            event.setEventDate(eventDate);
            event.setTaskId(tasksId);
            resultSet.close();
        } catch (SQLException e) {
            LOG.error("Cannot find event by id", e);
            throw new DaoException("Cannot find event by id -" + id, e);
        }
        return event;
    }

    @Override
    public void update(Event event) throws DaoException {
    }

    private Event pickEventFromResultSet(ResultSet resultSet) throws DaoException {
        Event event = new Event();
        Research research = new Research();
        EventPrototype eventPrototype = new EventPrototype();
        try {
            event.setId(resultSet.getInt(1));
            event.setEventDate(resultSet.getDate(2).toLocalDate());
            event.setEventProgress(resultSet.getDouble(5));
            research.setId(resultSet.getInt(3));
            event.setResearch(research);
            eventPrototype.setId(resultSet.getInt(4));
            event.setEventPrototype(eventPrototype);
        } catch (SQLException e) {
            LOG.error("Cannot pick event from result set", e);
            throw new DaoException("Cannot pick event from resultSet", e);
        }
        return event;
    }

}
