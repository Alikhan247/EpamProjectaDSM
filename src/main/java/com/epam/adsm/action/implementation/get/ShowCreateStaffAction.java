package com.epam.adsm.action.implementation.get;

import com.epam.adsm.action.Action;
import com.epam.adsm.action.ActionResult;
import com.epam.adsm.model.DiagnosisDate;
import com.epam.adsm.service.CordinatorService;
import com.epam.adsm.service.ServiceExeption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static com.epam.adsm.action.ActionConstants.*;

/**
 * Created by akmatleu on 08.06.17.
 */
public class ShowCreateStaffAction implements Action {
    private static final Logger LOG = LoggerFactory.getLogger(ShowCreateStaffAction.class);

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            CordinatorService cordinatorService = new CordinatorService();
            DiagnosisDate diagnosisDate = cordinatorService.getDiagnosisDate();
            List<String> roleOptions = diagnosisDate.getRoleOption();
            request.setAttribute(ROLE_OPTIONS, roleOptions);
        } catch (ServiceExeption e) {
            LOG.error("Cannot get list of Doctors or diagnosis date from service", e);
        }
        return new ActionResult(CREATE_STAFF_PAGE);
    }
}
