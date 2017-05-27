package com.epam.adsm.action.implementation.get;

import com.epam.adsm.action.Action;
import com.epam.adsm.action.ActionResult;
import com.epam.adsm.model.Drug;
import com.epam.adsm.model.Patient;
import com.epam.adsm.service.ChemisterService;
import com.epam.adsm.service.CordinatorService;
import com.epam.adsm.service.PatientService;
import com.epam.adsm.service.ServiceExeption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static com.epam.adsm.action.ActionConstants.*;

/**
 * Created by akmatleu on 22.05.17.
 */
public class ShowRecieptAction implements Action {
    private static final Logger LOG = LoggerFactory.getLogger(GetStaffListAction.class);
    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {

        List<Drug> drugs = null;
        Patient patient = new Patient();

        PatientService patientService = new PatientService();
        ChemisterService chemisterService = new ChemisterService();

        try{
            patient = patientService.findPatientByCode(request.getParameter(PATIENT_CODE));
            drugs = chemisterService.getAllDrugs();
        }catch (ServiceExeption e){
            LOG.error("cannot get staff list from service",e);
        }

        request.setAttribute(DRUGS,drugs);
        request.setAttribute(PATIENT,patient);


        return new ActionResult(CREATE_RECEIPT);
    }
}
