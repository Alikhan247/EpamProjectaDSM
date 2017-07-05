package com.epam.adsm.dao;

import com.epam.adsm.connection.ConnectionPool;
import com.epam.adsm.connection.ConnectionPoolException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class DaoFactory implements AutoCloseable {

    private static final Logger LOG = LoggerFactory.getLogger(DaoFactory.class);
    private ConnectionPool connectionPool;
    private Connection connection = null;

    public DaoFactory() {
        connectionPool = ConnectionPool.getInstance();
        try {
            connection = connectionPool.getConnectionFromPool();
        } catch (ConnectionPoolException e) {
            LOG.error("Cannot det connection from pool", e);
        }
    }

    public <T extends Dao> T getDao(Class<T> tClass) throws DaoException {
        T t;
        try {
            t = tClass.newInstance();
            t.setConnection(connection);
        } catch (InstantiationException | IllegalAccessException e) {
            LOG.error("Cannot make new instance of Dao", e);
            throw new DaoException("Cannot make new instance of Dao", e);
        }
        return t;
    }

    public void startTransaction() throws DaoException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            LOG.error("Cannot start transaction", e);
            throw new DaoException("Cannot start transaction", e);
        }
    }

    public void commitTransaction() throws DaoException {
        try {
            connection.commit();
            connection.setAutoCommit(true);
            LOG.debug("Commit transaction changes");
        } catch (SQLException e) {
            LOG.error("Cannot commit transaction", e);
            throw new DaoException("Cannot commit transaction", e);
        }
    }

    public void rollbackTransaction() throws DaoException {
        try {
            connection.rollback();
            LOG.debug("Rollback transaction changes");
        } catch (SQLException e) {
            LOG.error("Cannot rollback transaction changes", e);
            throw new DaoException("Cannot rollback transaction changes", e);
        }
    }

    @Override
    public void close() throws DaoException {
        try {
            connectionPool.returnConnectionToPool(connection);
        } catch (ConnectionPoolException e) {
            LOG.error("Cannot return connection to pool", e);
            throw new DaoException("Cannot return connection to pool", e);
        }
    }
}
