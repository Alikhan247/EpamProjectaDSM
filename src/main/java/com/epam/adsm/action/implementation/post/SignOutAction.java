package com.epam.adsm.action.implementation.post;

import com.epam.adsm.action.Action;
import com.epam.adsm.action.ActionResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.epam.adsm.action.ActionConstants.*;

/**
 * Created by akmatleu on 20.05.17.
 */
public class SignOutAction implements Action {
    private static final Logger LOG = LoggerFactory.getLogger(SignOutAction.class);
    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute(ROLE);
        LOG.info("Person with role = {} sign out.", role);
        session.invalidate();
        return new ActionResult(LOGIN_PAGE, true);
    }
}
