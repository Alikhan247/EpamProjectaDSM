package com.epam.adsm.action.implementation.post;

import com.epam.adsm.action.Action;
import com.epam.adsm.action.ActionResult;
import com.epam.adsm.model.AdverseEvent;
import com.epam.adsm.model.Patient;
import com.epam.adsm.model.Staff;
import com.epam.adsm.service.ChemisterService;
import com.epam.adsm.service.PatientService;
import com.epam.adsm.service.ServiceExeption;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Date;

import static com.epam.adsm.action.ActionConstants.*;

/**
 * Created by akmatleu on 23.05.17.
 */
public class CreateAdverseEventAction implements Action {
    private static final Logger LOG = LoggerFactory.getLogger(CreateAdverseEventAction.class);

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {
        AdverseEvent adverseEvent = new AdverseEvent();
        adverseEvent.setAdverseName(request.getParameter(ADVERSE_EVENT));
        Date adverseDate = Date.valueOf(request.getParameter(ADVERSE_DATE));
        adverseEvent.setAdverseDate(adverseDate.toLocalDate());
        adverseEvent.setAdverseComment(request.getParameter(ADVERSE_COMMENT));
        adverseEvent.setAdverseAlcohol(Boolean.valueOf(request.getParameter(ADVERSE_ALCOHOL)));
        adverseEvent.setAdverseDrug(Boolean.valueOf(request.getParameter(ADVERSE_DRUG)));
        String patientCode = request.getParameter(PATIENT_CODE);
        PatientService patientService = new PatientService();
        ChemisterService chemisterService = new ChemisterService();
        Patient patient;
        Staff doctor;
        try {
            patient = patientService.findPatientByCode(patientCode);
            doctor = patient.getDoctor();
            adverseEvent.setPatient(patient);
            adverseEvent.setStaff(doctor);
            chemisterService.createAdverseEvent(adverseEvent);
        } catch (ServiceExeption e) {
            LOG.error("Cannot create adverse event for patient " + patientCode + " from service", e);
        }
        return new ActionResult(HOME_PAGE);
    }
}
