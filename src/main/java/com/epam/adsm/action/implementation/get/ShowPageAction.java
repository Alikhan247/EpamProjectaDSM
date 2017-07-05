package com.epam.adsm.action.implementation.get;

import com.epam.adsm.action.Action;
import com.epam.adsm.action.ActionResult;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowPageAction implements Action {

    private ActionResult actionResult;

    public ShowPageAction(String page) {
        actionResult = new ActionResult(page);
    }

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {
        return actionResult;
    }
}
