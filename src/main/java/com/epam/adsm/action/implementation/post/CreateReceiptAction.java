package com.epam.adsm.action.implementation.post;

import com.epam.adsm.action.Action;
import com.epam.adsm.action.ActionResult;
import com.epam.adsm.action.implementation.get.ShowRecieptAction;
import com.epam.adsm.model.Drug;
import com.epam.adsm.model.Patient;
import com.epam.adsm.model.Receipt;
import com.epam.adsm.service.ChemisterService;
import com.epam.adsm.service.ServiceExeption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.epam.adsm.action.ActionConstants.*;

public class CreateReceiptAction implements Action {
    private static final Logger LOG = LoggerFactory.getLogger(CreateReceiptAction.class);

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {
        boolean isRedirect = true;
        Receipt receipt = new Receipt();
        Patient patient = new Patient();
        Drug drug = new Drug();
        patient.setPatientCode(request.getParameter(PATIENT_CODE));
        String code = patient.getPatientCode();
        HttpSession session = request.getSession();
        session.setAttribute(PATIENT_TYPE, patient.getPatientCode());
        receipt.setPatient(patient);
        drug.setId(Integer.parseInt(request.getParameter(DRUG_ID)));
        receipt.setDrug(drug);
        receipt.setReceipt_status(true);
        try{
            receipt.setDrugDoze(Float.valueOf(request.getParameter(DRUG_DOZE)));
        }catch (NumberFormatException e){
            LOG.error("Cannot convert drug doze to float number");
            request.setAttribute(ERROR,ERROR_CONVERT_FLOAT);
            ShowRecieptAction showRecieptAction = new ShowRecieptAction();
            showRecieptAction.execute(request,response);
            return new ActionResult(CREATE_RECEIPT);
        }
        receipt.setReceiptDate(java.time.LocalDate.now());
        ChemisterService chemisterService = new ChemisterService();
        try {
            chemisterService.createReceipt(receipt);
        } catch (ServiceExeption e) {
            LOG.error("Cannot create recepiet from service", e);
        }
        return new ActionResult(CREATE_RECEIPT + "?patientCode=" + code, isRedirect);
    }
}
