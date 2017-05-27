package com.epam.adsm.action.implementation.get;

import com.epam.adsm.action.Action;
import com.epam.adsm.action.ActionResult;
import com.epam.adsm.model.AdverseEvent;
import com.epam.adsm.model.Staff;
import com.epam.adsm.service.DoctorService;
import com.epam.adsm.service.ServiceExeption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.List;

import static com.epam.adsm.action.ActionConstants.*;

/**
 * Created by akmatleu on 25.05.17.
 */
public class ShowDoctorPatientsAdverseAction implements Action {
    Logger LOG = LoggerFactory.getLogger(ShowDoctorPatientsAdverseAction.class);

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {

        List<AdverseEvent> adverseEvents = null;
        Staff doctor = new Staff();
        HttpSession session = request.getSession();

        String doctorId = String.valueOf(session.getAttribute(STAFF_ID));
        doctor.setId(Integer.parseInt(doctorId));
        DoctorService doctorService = new DoctorService();

        try{
           adverseEvents = doctorService.getDoctorAdverseEvents(doctor);
            request.setAttribute(DOCTOR_PATIENTS_ADVERSE_EVENTS,adverseEvents);
            System.out.println(adverseEvents.toString());
        }catch (ServiceExeption e){
            LOG.error("Cannot get adverse events from service",e);
        }

        return new ActionResult(DOCTOR_PATIENTS_ADVERSE_PAGE);
    }
}
