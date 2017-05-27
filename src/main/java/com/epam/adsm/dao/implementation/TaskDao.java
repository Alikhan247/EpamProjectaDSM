package com.epam.adsm.dao.implementation;

import com.epam.adsm.dao.Dao;
import com.epam.adsm.dao.DaoException;
import com.epam.adsm.model.Task;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by akmatleu on 17.05.17.
 */
public class TaskDao extends Dao implements EntityDao<Task> {

    private static final String CREATE_TASK = "INSERT INTO public.task(\n"+
            "           task_prototype_id, event_id, task_progress)\n"+
            "    VALUES ( ?, ?, ?)";
    private static final String UPDATE_TASK_PROGRESS = "UPDATE public.task SET task_progress=100 WHERE task_id=?";

    @Override
    public Task create(Task task) throws DaoException {
        try(PreparedStatement statement = getConnection().prepareStatement(CREATE_TASK,PreparedStatement.RETURN_GENERATED_KEYS)){
            statement.setInt(1,task.getTaskPrototype().getId());
            statement.setInt(2,task.getEvent().getId());
            statement.setInt(3,task.getTaskProgress());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            while (resultSet.next()){
                task.setId(resultSet.getInt(1));
            }
            resultSet.close();
        }catch (SQLException e){
            throw new DaoException("Cannot create task from db",e);
        }
        return task;
    }

    @Override
    public Task findById(int id) throws DaoException {

        return null;
    }

    @Override
    public void update(Task task) throws DaoException {
        try(PreparedStatement statement = getConnection().prepareStatement(UPDATE_TASK_PROGRESS)){
            statement.setInt(1,task.getId());
            statement.execute();
        }catch (SQLException e){
            throw new DaoException("Cannot update taks in db",e);
        }

    }

    @Override
    public void delete(Task task) throws DaoException {

    }
}
