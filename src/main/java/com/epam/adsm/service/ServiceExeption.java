package com.epam.adsm.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by akmatleu on 15.05.17.
 */
public class ServiceExeption extends Exception {
    private static final Logger LOG = LoggerFactory.getLogger(ServiceExeption.class);

    public ServiceExeption(Exception e) {
        super(e);
        LOG.error("Catch service exception",e);
    }

    public ServiceExeption(String message,Exception e) {
        super(message,e);
        LOG.error(message,e);
    }
}
