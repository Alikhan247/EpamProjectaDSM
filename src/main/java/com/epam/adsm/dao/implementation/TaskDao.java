package com.epam.adsm.dao.implementation;

import com.epam.adsm.dao.Dao;
import com.epam.adsm.dao.DaoException;
import com.epam.adsm.model.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by akmatleu on 17.05.17.
 */
public class TaskDao extends Dao {
    private static final Logger LOG = LoggerFactory.getLogger(TaskDao.class);
    private static final String CREATE_TASK = "INSERT INTO public.task(\n" +
            "           task_prototype_id, event_id, task_progress)\n" +
            "    VALUES ( ?, ?, ?)";
    private static final String UPDATE_TASK_PROGRESS = "UPDATE public.task SET task_progress=100 WHERE task_id=?";

    public Task createTask(Task task) throws DaoException {
        try (PreparedStatement statement = getConnection().prepareStatement(CREATE_TASK, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, task.getTaskPrototype().getId());
            statement.setInt(2, task.getEvent().getId());
            statement.setInt(3, task.getTaskProgress());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            while (resultSet.next()) {
                task.setId(resultSet.getInt(1));
            }
            resultSet.close();
        } catch (SQLException e) {
            LOG.error("Cannot createTask task in database", e);
            throw new DaoException("Cannot task in database", e);
        }
        return task;
    }

    public void updateTask(Task task) throws DaoException {
        try (PreparedStatement statement = getConnection().prepareStatement(UPDATE_TASK_PROGRESS)) {
            statement.setInt(1, task.getId());
            statement.execute();
        } catch (SQLException e) {
            LOG.error("Cannot updateTask task in database", e);
            throw new DaoException("Cannot updateTask task in database", e);
        }
    }

}
