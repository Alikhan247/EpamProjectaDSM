package com.epam.adsm.connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by akmatleu on 08.05.17.
 */
public class ConnectionPoolException extends Exception {
    private static final Logger LOG = LoggerFactory.getLogger(ConnectionPoolException.class);

    public ConnectionPoolException(Exception e) {
        super(e);
        LOG.error("Catch connection pool Exception",e);
    }

    public ConnectionPoolException(String message,Exception e) {
        super(message,e);
        LOG.error(message,e);
    }

}
