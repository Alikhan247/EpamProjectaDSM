package com.epam.adsm.action.implementation.get;

import com.epam.adsm.action.Action;
import com.epam.adsm.action.ActionResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.core.Config;

import java.util.Locale;

import static com.epam.adsm.action.ActionConstants.*;

public class ShowLoginAction implements Action {
    private static final Logger LOG = LoggerFactory.getLogger(ShowEventDetails.class);

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(LANG)) {
                    return new ActionResult(LOGIN_PAGE);
                }
            }
        }
        Config.set(request.getSession(), Config.FMT_LOCALE, new Locale(LANG_ENG));
        Cookie cookie = new Cookie(LANG, LANG_ENG);
        cookie.setMaxAge(HOUR * MINUTE * SEC);
        response.addCookie(cookie);
        LOG.info("add default language to cookie");
        return new ActionResult(LOGIN_PAGE);
    }
}
