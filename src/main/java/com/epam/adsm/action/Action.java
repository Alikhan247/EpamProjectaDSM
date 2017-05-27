package com.epam.adsm.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by akmatleu on 17.05.17.
 */
public interface Action {
    ActionResult execute(HttpServletRequest request, HttpServletResponse response);
}
