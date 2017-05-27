package com.epam.adsm.action.implementation.get;

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

/**
 * Created by akmatleu on 22.05.17.
 */
public class ShowPatientEditAction implements Action {
    private static final Logger LOG = LoggerFactory.getLogger(ShowStaffEditAction.class);

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {
        String patientCode = request.getParameter("id");

        Patient patient = null;
        Research research = null;
        PatientService patientService = new PatientService();
        CordinatorService cordinatorService = new CordinatorService();
        try {
            patient = patientService.findPatientByCode(patientCode);
            research = cordinatorService.findResearchByPatientCode(patientCode);
        }catch (ServiceExeption e){
            LOG.error("Cannot find patient with code"+patientCode,e);
        }

        request.setAttribute(PATIENT,patient);
        request.setAttribute(RESEARCH,research);

        return new ActionResult(PATIENT_EDIT_PAGE);
    }
}
