package com.epam.adsm.web.controller.servlet;

import com.epam.adsm.action.Action;

import com.epam.adsm.action.ActionFactory;
import com.epam.adsm.action.ActionResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.epam.adsm.action.ActionConstants.*;

/**
 * Created by akmatleu on 17.05.17.
 */


public class FrontController extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(FrontController.class);
    private ActionFactory actionFactory;

    @Override
    public void init() throws ServletException {
        LOG.info("Servlet is working");
        actionFactory = new ActionFactory();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Action action = actionFactory.getAction(req);
        ActionResult result = action.execute(req, resp);
        doForwardOrRederict(result, req, resp);
    }

    private void doForwardOrRederict(ActionResult result, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (result.isRedirect()) {
            response.sendRedirect(result.getPage());
        } else {
            String path = PATH_JSP + result.getPage() + JSP;
            LOG.info("Path for 'forward' - " + path);
            request.getRequestDispatcher(path).forward(request, response);
        }
    }
}
