package com.epam.adsm.action.implementation.get;

import com.epam.adsm.action.Action;
import com.epam.adsm.action.ActionResult;
import com.epam.adsm.model.AdverseEvent;
import com.epam.adsm.model.DiagnosisDate;
import com.epam.adsm.model.Staff;
import com.epam.adsm.service.CordinatorService;
import com.epam.adsm.service.DoctorService;
import com.epam.adsm.service.ServiceExeption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.List;

import static com.epam.adsm.action.ActionConstants.*;

public class ShowDoctorPatientsAdverseAction implements Action {
    private static final Logger LOG = LoggerFactory.getLogger(ShowDoctorPatientsAdverseAction.class);

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {
        List<AdverseEvent> adverseEvents;
        Staff doctor = new Staff();
        HttpSession session = request.getSession();
        String doctorId = String.valueOf(session.getAttribute(STAFF_ID));
        doctor.setId(Integer.parseInt(doctorId));
        DoctorService doctorService = new DoctorService();
        CordinatorService cordinatorService = new CordinatorService();
        try {
            adverseEvents = doctorService.getDoctorAdverseEvents(doctor);
            DiagnosisDate diagnosisDate = cordinatorService.getDiagnosisDate();
            List<String> adverseStatuses = diagnosisDate.getAdverseStatusOption();
            request.setAttribute(ADVERSE_STATUSES,adverseStatuses);
            request.setAttribute(DOCTOR_PATIENTS_ADVERSE_EVENTS, adverseEvents);
        } catch (ServiceExeption e) {
            LOG.error("Cannot get adverse events from service", e);
        }
        return new ActionResult(DOCTOR_PATIENTS_ADVERSE_PAGE);
    }
}
