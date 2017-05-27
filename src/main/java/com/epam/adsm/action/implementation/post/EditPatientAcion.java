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

/**
 * Created by akmatleu on 22.05.17.
 */
public class EditPatientAcion implements Action {

    private static final Logger LOG = LoggerFactory.getLogger(EditStaffAction.class);

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {
        Patient patient = new Patient();
        Research research = new Research();

        patient.setPatientCode(request.getParameter("id"));
        patient.setPhoneNumber(request.getParameter(PHONE));


        PatientService patientService = new PatientService();
        CordinatorService cordinatorService = new CordinatorService();

        try {
            patientService.updatePatient(patient);
            research = cordinatorService.findResearchByPatientCode(patient.getPatientCode());
            research.setActivationStatus(Boolean.valueOf(request.getParameter(RESEARCH_STATUS)));
            cordinatorService.updateResearch(research);

        }catch (ServiceExeption e){
            LOG.error("Cannot update patient with code="+patient.getPatientCode(),e);
        }
        return new ActionResult("/do/patient/edit?id="+patient.getPatientCode(),true);
    }


}
