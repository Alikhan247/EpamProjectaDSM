package com.epam.adsm.dao.implementation;

import com.epam.adsm.dao.Dao;
import com.epam.adsm.dao.DaoException;
import com.epam.adsm.model.TaskPrototype;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by akmatleu on 13.05.17.
 */
public class TaskPrototypeDao extends Dao implements EntityDao<TaskPrototype> {

    private static final String CREATE_TASK_PROTOTYPE = "INSERT INTO public.task_prototype(task_name) VALUES (?)";
    private static final String FIND_BY_ID = "SELECT * FROM public.task_prototype WHERE task_prototype_id=?";
    private static final String GET_ALL_TASKS_PROTOTYPE_ID_BY_EVENT_PROTOTYPE = "SELECT * FROM public.protocol_events_tasks\n" +
            "WHERE event_prototype_id = ?";





    @Override
    public TaskPrototype create(TaskPrototype taskPrototype) throws DaoException {
        return null;
    }

    @Override
    public TaskPrototype findById(int id) throws DaoException {
        return null;
    }

    @Override
    public void update(TaskPrototype taskPrototype) throws DaoException {

    }

    @Override
    public void delete(TaskPrototype taskPrototype) throws DaoException {

    }



    private TaskPrototype pickTaskPrototypeIdFromResultSet(ResultSet resultSet) throws DaoException {
        TaskPrototype taskPrototype = new TaskPrototype();
        try {
            taskPrototype.setId(resultSet.getInt(2));
        }catch (SQLException e){
            throw new DaoException("Cannot create task prototypes from resultSet",e);
        }
        return taskPrototype;
    }
}
