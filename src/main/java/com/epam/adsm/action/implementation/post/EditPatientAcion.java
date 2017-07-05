package com.epam.adsm.action.implementation.post;

import com.epam.adsm.action.Action;
import com.epam.adsm.action.ActionResult;
import com.epam.adsm.model.Patient;
import com.epam.adsm.model.Research;
import com.epam.adsm.service.CordinatorService;
import com.epam.adsm.service.PatientService;
import com.epam.adsm.service.ServiceExeption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.epam.adsm.action.ActionConstants.*;

public class EditPatientAcion implements Action {
    private static final Logger LOG = LoggerFactory.getLogger(EditStaffAction.class);

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {
        boolean isRedirect = true;
        Patient patient = new Patient();
        Research research;
        patient.setPatientCode(request.getParameter(PATIENT_CODE));
        patient.setPhoneNumber(request.getParameter(PHONE));
        PatientService patientService = new PatientService();
        CordinatorService cordinatorService = new CordinatorService();
        try {
            patientService.updatePatient(patient);
            research = cordinatorService.findResearchByPatientCode(patient.getPatientCode());
            research.setActivationStatus(Boolean.valueOf(request.getParameter(RESEARCH_STATUS)));
            cordinatorService.updateResearch(research);
        } catch (ServiceExeption e) {
            LOG.error("Cannot edit patient with code=" + patient.getPatientCode(), e);
        }
        return new ActionResult(PATIENT_EDIT_PAGE + patient.getPatientCode(), isRedirect);
    }
}
