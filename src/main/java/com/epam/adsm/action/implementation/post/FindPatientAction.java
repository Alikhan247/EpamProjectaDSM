package com.epam.adsm.action.implementation.post;

import com.epam.adsm.action.Action;
import com.epam.adsm.action.ActionResult;
import com.epam.adsm.model.Diagnosis;
import com.epam.adsm.model.Patient;
import com.epam.adsm.model.Research;
import com.epam.adsm.service.PatientService;
import com.epam.adsm.service.ServiceExeption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static com.epam.adsm.action.ActionConstants.*;

/**
 * Created by akmatleu on 23.05.17.
 */
public class FindPatientAction implements Action {
    private static final Logger LOG = LoggerFactory.getLogger(FindPatientAction.class);
    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {

        Patient patient=null;
        String patientCode = request.getParameter(PATIENT_CODE);
        PatientService patientService = new PatientService();

        try{
            patient = patientService.findPatientByCode(patientCode);

        }catch (ServiceExeption e){
            LOG.error("cannot get staff list from service",e);
        }

        request.setAttribute(PATIENT,patient);

        return new ActionResult(HOME_PAGE);
    }
}
