package com.epam.adsm.action.implementation.get;

import com.epam.adsm.action.Action;
import com.epam.adsm.action.ActionResult;
import com.epam.adsm.model.DiagnosisDate;
import com.epam.adsm.model.Staff;
import com.epam.adsm.service.CordinatorService;
import com.epam.adsm.service.ServiceExeption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import static com.epam.adsm.action.ActionConstants.*;

public class ShowStaffEditAction implements Action {
    private static final Logger LOG = LoggerFactory.getLogger(ShowStaffEditAction.class);

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter(STAFF_ID));
        Staff staff = null;
        CordinatorService cordinatorService = new CordinatorService();
        try {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals(LANG)) {
                        staff = cordinatorService.findStaffById(id);
                        DiagnosisDate diagnosisDate = cordinatorService.getDiagnosisDate(cookie.getValue());
                        List<String> activaionStatuses = diagnosisDate.getActivationStatusOption();
                        request.setAttribute(ACTIVATION_STATUSES,activaionStatuses);
                    }
                }
            }
        } catch (ServiceExeption e) {
            LOG.error("Cannot find staff with id" + id, e);
        }
        request.setAttribute(STAFF, staff);
        return new ActionResult(STAFF_EDIT_PAGE);
    }
}
