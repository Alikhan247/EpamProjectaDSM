package com.epam.adsm.action.implementation.get;

import com.epam.adsm.action.Action;
import com.epam.adsm.action.ActionResult;
import com.epam.adsm.model.DiagnosisDate;
import com.epam.adsm.service.CordinatorService;
import com.epam.adsm.service.ServiceExeption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.core.Config;
import java.util.List;
import java.util.Locale;

import static com.epam.adsm.action.ActionConstants.*;

public class ShowCreateStaffAction implements Action {
    private static final Logger LOG = LoggerFactory.getLogger(ShowCreateStaffAction.class);

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            CordinatorService cordinatorService = new CordinatorService();
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals(LANG)) {
                        DiagnosisDate diagnosisDate = cordinatorService.getDiagnosisDate(cookie.getValue());
                        List<String> roleOptions = diagnosisDate.getRoleOption();
                        request.setAttribute(ROLE_OPTIONS, roleOptions);
                    }
                }
            }
        } catch (ServiceExeption e) {
            LOG.error("Cannot get list of role options from coordinator service", e);
        }
        return new ActionResult(CREATE_STAFF_PAGE);
    }
}
