package com.epam.adsm.action.implementation.get;

import com.epam.adsm.action.Action;
import com.epam.adsm.action.ActionResult;
import com.epam.adsm.model.Event;
import com.epam.adsm.service.PatientService;
import com.epam.adsm.service.ServiceExeption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.List;

import static com.epam.adsm.action.ActionConstants.*;

/**
 * Created by akmatleu on 23.05.17.
 */
public class ShowPatientHomeAction implements Action {
    private static final Logger LOG = LoggerFactory.getLogger(ShowPatientHomeAction.class);
    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {
        List<Event> events;
        PatientService patientService = new PatientService();
        try {
            HttpSession session = request.getSession();
            String patientCode = String.valueOf(session.getAttribute(PATIENT_CODE));
            events = patientService.getAllPatientEvents(patientCode);
            request.setAttribute(PATIENT_EVENTS,events);
        }catch (ServiceExeption e){
            LOG.error("Cannot ged events from patient service",e);
        }

        return new  ActionResult(HOME_PAGE);
    }
}
