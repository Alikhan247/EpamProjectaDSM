package com.epam.adsm.dao.implementation;

import com.epam.adsm.dao.DaoException;

public interface EntityDao<T> {

    T create(T t) throws DaoException;
    T findById(int id) throws  DaoException;
    void update(T t) throws  DaoException;
}
