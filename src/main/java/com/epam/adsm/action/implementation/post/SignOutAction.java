package com.epam.adsm.action.implementation.post;

import com.epam.adsm.action.Action;
import com.epam.adsm.action.ActionResult;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.epam.adsm.action.ActionConstants.*;

public class SignOutAction implements Action {

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {
        boolean isRedirect = true;
        HttpSession session = request.getSession();
        session.invalidate();
        return new ActionResult(LOGIN_PAGE, isRedirect);
    }
}
