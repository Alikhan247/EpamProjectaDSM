package com.epam.adsm.action.implementation.get;

import com.epam.adsm.action.Action;
import com.epam.adsm.action.ActionResult;
import com.epam.adsm.model.Drug;
import com.epam.adsm.model.Reciept;
import com.epam.adsm.service.ChemisterService;
import com.epam.adsm.service.ServiceExeption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

import static com.epam.adsm.action.ActionConstants.*;

/**
 * Created by akmatleu on 23.05.17.
 */
public class ShowDrugAdminAction implements Action {

    private static final Logger LOG = LoggerFactory.getLogger(ShowDrugAdminAction.class);

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {

        List<Reciept> reciepts = new ArrayList<>();
        List<Drug> drugs = null;
        String patientCode = request.getParameter(PATIENT_CODE);
        System.out.println(patientCode);

        ChemisterService chemisterService = new ChemisterService();
        try {
            reciepts = chemisterService.getAllRecieptsByPatientCode(patientCode);
            drugs = chemisterService.getAllDrugs();

            request.setAttribute(RECEIPTS, reciepts);
            request.setAttribute(DRUGS, drugs);
        } catch (ServiceExeption e) {
            LOG.error("Cannot find receipt by patientCode" + patientCode, e);
        }


        return new ActionResult(DRUG_ADMINISTRATION_PAGE);
    }
}