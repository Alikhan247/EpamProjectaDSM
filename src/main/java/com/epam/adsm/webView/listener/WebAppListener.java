package com.epam.adsm.webView.listener;

import com.epam.adsm.connection.ConnectionPool;
import com.epam.adsm.connection.ConnectionPoolException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Created by akmatleu on 18.05.17.
 */
@WebListener
public class WebAppListener implements ServletContextListener{

    private static final Logger LOG = LoggerFactory.getLogger(WebAppListener.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        ConnectionPool connectionPool = new ConnectionPool();
        ConnectionPool.InstanceHolder.setInstance(connectionPool);
        servletContext.setAttribute("pool",connectionPool);
        LOG.info("Connection pool ready");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        ConnectionPool connectionPool = (ConnectionPool) servletContext.getAttribute("pool");
        try{
            connectionPool.close();
        }catch (ConnectionPoolException e) {
            LOG.error("Cannot close connections in pool",e);
        }


    }
}
