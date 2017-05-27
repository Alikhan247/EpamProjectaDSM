package com.epam.adsm.connection;

import org.postgresql.Driver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by akmatleu on 08.05.17.
 */
public class ConnectionPool {
    private static final Logger LOG = LoggerFactory.getLogger(ConnectionPool.class);
    private static final int CONNECTION_POOL_SIZE = 15;

    private String url;
    private String username;
    private String password;

    private BlockingQueue<Connection> queueConnections = new ArrayBlockingQueue<>(CONNECTION_POOL_SIZE);

    public ConnectionPool() {
        try {
            loadProperties();
            loadDriver();
            init();
        }catch (ConnectionPoolException e){
            LOG.error("Cannot create new instance of connection pool",e);
        }
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private void loadProperties() throws ConnectionPoolException {
        Properties properties = new Properties();
        try{
            properties.load(ConnectionPool.class.getClassLoader().getResourceAsStream("database/database.properties"));
            LOG.info("Load property for connect to DB");
        } catch (IOException e) {
            throw new ConnectionPoolException("Cannot load properties",e);
        }
        if (!properties.isEmpty()) {
            LOG.info("Set info about DB to instance");
            setUrl(properties.getProperty("url"));
            setUsername(properties.getProperty("username"));
            setPassword(properties.getProperty("password"));
        } else  {
            LOG.error("roperty haven't any parameters");
        }

    }

    public static ConnectionPool getInstance() { return  InstanceHolder.instance; }

    // Prover nuzhna li ita function
    private void loadDriver() throws ConnectionPoolException {
        try {
            LOG.info("Create new driver");
            Driver driver = new Driver();
            DriverManager.registerDriver(driver);
        } catch (SQLException e) {
            throw new ConnectionPoolException("Cannot get driver manager",e);
        }
    }

    private void  init() throws  ConnectionPoolException {
        try {
            for (int i = 0 ; i < CONNECTION_POOL_SIZE ; i++ ){
                Connection connection = createNewConnectionForPool();
                if (connection != null) {
                    queueConnections.put(connection);
                }
            }
        }catch (InterruptedException e) {
            throw  new ConnectionPoolException("Cannot init connection pool",e);
        }
    }

    private Connection createNewConnectionForPool() throws ConnectionPoolException {
        Connection connection = null;
        try
        {
            connection = DriverManager.getConnection(url, username, password);
            LOG.info("Connection successful created");
        }
        catch(SQLException e)
        {
            throw new ConnectionPoolException("Cannot create new connection for pool",e);
        }
        return connection;
    }

    public  Connection getConnectionFromPool() throws ConnectionPoolException
    {
        Connection connection = null;
        try {
            connection = queueConnections.take();
            LOG.info("Get connection from pool");
        } catch (InterruptedException e) {
            throw new ConnectionPoolException("Cannot take connection from pool",e);
        }
        return connection;
    }

    public  void returnConnectionToPool(Connection connection) throws ConnectionPoolException
    {
        //Adding the connection from the client back to the connection pool
        try {
            queueConnections.put(connection);
            LOG.info("Connection back to pool");
        } catch (InterruptedException e) {
            throw new ConnectionPoolException("Cannot return connection to pool",e);
        }
    }

    public void close() throws ConnectionPoolException {
        closeAllConnections(queueConnections);
    }

    private void closeAllConnections (BlockingQueue<Connection> connections) throws ConnectionPoolException {
        for (Connection connection : connections) {
            try {
                connection.close();
                LOG.info("Connection was closed");
            } catch (SQLException e) {
                throw  new ConnectionPoolException("Cannot close connection",e );
            }
        }
    }


    public static class InstanceHolder {
        static ConnectionPool instance;

        public static void setInstance(ConnectionPool connectionPool) {instance = connectionPool;}
    }
    
}
