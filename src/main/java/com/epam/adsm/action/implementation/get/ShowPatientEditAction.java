package com.epam.adsm.action.implementation.get;

import com.epam.adsm.action.Action;
import com.epam.adsm.action.ActionResult;
import com.epam.adsm.model.DiagnosisDate;
import com.epam.adsm.model.Patient;
import com.epam.adsm.model.Research;
import com.epam.adsm.service.CordinatorService;
import com.epam.adsm.service.PatientService;
import com.epam.adsm.service.ServiceExeption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import static com.epam.adsm.action.ActionConstants.*;

public class ShowPatientEditAction implements Action {
    private static final Logger LOG = LoggerFactory.getLogger(ShowStaffEditAction.class);

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {
        String patientCode = request.getParameter(PATIENT_CODE);
        Patient patient = null;
        Research research = null;
        PatientService patientService = new PatientService();
        CordinatorService cordinatorService = new CordinatorService();
        try {
            patient = patientService.findPatientByCode(patientCode);
            research = cordinatorService.findResearchByPatientCode(patientCode);
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals(LANG)) {
                        DiagnosisDate diagnosisDate = cordinatorService.getDiagnosisDate(cookie.getValue());
                        List<String> activaionStatuses = diagnosisDate.getActivationStatusOption();
                        request.setAttribute(ACTIVATION_STATUSES, activaionStatuses);
                    }
                }
            }
        } catch (ServiceExeption e) {
            LOG.error("Cannot find patient with code" + patientCode, e);
        }
        request.setAttribute(PATIENT, patient);
        request.setAttribute(RESEARCH, research);
        return new ActionResult(PATIENTS_EDIT_PAGE);
    }
}
