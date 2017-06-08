package com.epam.adsm.dao.implementation;

import com.epam.adsm.dao.DaoException;

/**
 * Created by akmatleu on 09.05.17.
 */
public interface EntityDao<T> {

    T create(T t) throws DaoException;
    T findById(int id) throws  DaoException;
    void update(T t) throws  DaoException;
}
