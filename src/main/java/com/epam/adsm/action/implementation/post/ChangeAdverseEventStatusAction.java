package com.epam.adsm.action.implementation.post;

import com.epam.adsm.action.Action;
import com.epam.adsm.action.ActionResult;
import com.epam.adsm.model.AdverseEvent;
import com.epam.adsm.service.DoctorService;
import com.epam.adsm.service.ServiceExeption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.epam.adsm.action.ActionConstants.*;

/**
 * Created by akmatleu on 25.05.17.
 */
public class ChangeAdverseEventStatusAction implements Action {
    private static final Logger LOG = LoggerFactory.getLogger(ChangeAdverseEventStatusAction.class);

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {
        boolean isRedirect = true;
        AdverseEvent adverseEvent = new AdverseEvent();
        String adverseEventId = request.getParameter(ADVERSE_EVENT_ID);
        adverseEvent.setId(Integer.parseInt(adverseEventId));
        adverseEvent.setAdverseStatus(request.getParameter(ADVERSE_EVENT_STATUS));
        DoctorService doctorService = new DoctorService();
        try {
            doctorService.updateAdverseEventStatus(adverseEvent);
        } catch (ServiceExeption e) {
            LOG.error("Cannot update adverse event status from service", e);
        }
        return new ActionResult(DOCTOR_PATIENTS_ADVERSE_PAGE, isRedirect);
    }
}
