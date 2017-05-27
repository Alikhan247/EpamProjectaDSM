package com.epam.adsm.action.implementation.get;

import com.epam.adsm.action.Action;
import com.epam.adsm.action.ActionResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.core.Config;
import java.io.UnsupportedEncodingException;
import java.util.Locale;

import static com.epam.adsm.action.ActionConstants.*;

/**
 * Created by akmatleu on 25.05.17.
 */
public class LocaleLanguageAction implements Action {

    private static final Logger LOG = LoggerFactory.getLogger(LocaleLanguageAction.class);

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {
        String language = request.getParameter(LANG);
        Config.set(request.getSession(), Config.FMT_LOCALE, new Locale(language));
        Cookie cookie = new Cookie(LANG, language);
        cookie.setMaxAge(HOUR * MINUTE * SEC);
        response.addCookie(cookie);
        try {
            request.setCharacterEncoding("UTF8");
        } catch (UnsupportedEncodingException e) {
            LOG.error("Can't set character encoding" ,e);
        }
        return new ActionResult(request.getHeader(REFERER), true);
    }
}
