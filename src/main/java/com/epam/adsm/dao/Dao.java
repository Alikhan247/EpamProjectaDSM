package com.epam.adsm.dao;

import java.sql.Connection;

/**
 * Created by akmatleu on 08.05.17.
 */
public abstract class Dao {

    private Connection connection;

    public Dao(){

    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
