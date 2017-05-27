package com.epam.adsm.action.implementation.get;

import com.epam.adsm.action.Action;
import com.epam.adsm.action.ActionResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.epam.adsm.action.ActionConstants.*;

/**
 * Created by akmatleu on 23.05.17.
 */
public class ShowAdverseAction implements Action {
    private static final Logger LOG = LoggerFactory.getLogger(ShowAdverseAction.class);
    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {



        return new ActionResult(ADVERSE_EVENT_PAGE);
    }
}
