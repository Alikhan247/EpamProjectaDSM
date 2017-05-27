package com.epam.adsm.action.implementation.get;

import com.epam.adsm.action.Action;
import com.epam.adsm.action.ActionResult;
import com.epam.adsm.model.Event;
import com.epam.adsm.model.Patient;
import com.epam.adsm.model.Staff;
import com.epam.adsm.service.DoctorService;
import com.epam.adsm.service.ServiceExeption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

import static com.epam.adsm.action.ActionConstants.*;

/**
 * Created by akmatleu on 24.05.17.
 */
public class ShowDoctorScheduleAction implements Action {
    private static final Logger LOG = LoggerFactory.getLogger(ShowDoctorScheduleAction.class);

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {

        Staff doctor = new Staff();
        List<Patient> patients;

        HttpSession session = request.getSession();
        String doctorId = String.valueOf(session.getAttribute(STAFF_ID));
        doctor.setId(Integer.parseInt(doctorId));
        try {
            DoctorService doctorService = new DoctorService();
            patients = doctorService.getDoctorSchedule(doctor);

            request.setAttribute(ALL_PATIENTS, patients);
        } catch (ServiceExeption e) {
            LOG.error("Cannot get schedule for doctor" + doctorId, e);
        }

        return new ActionResult(DOCTOR_SCHEDULE_PAGE);
    }
}
