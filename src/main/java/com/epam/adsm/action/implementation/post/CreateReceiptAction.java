package com.epam.adsm.action.implementation.post;

import com.epam.adsm.action.Action;
import com.epam.adsm.action.ActionResult;
import com.epam.adsm.model.Drug;
import com.epam.adsm.model.Patient;
import com.epam.adsm.model.Reciept;
import com.epam.adsm.service.ChemisterService;
import com.epam.adsm.service.ServiceExeption;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.Date;
import java.text.SimpleDateFormat;

import static com.epam.adsm.action.ActionConstants.*;

/**
 * Created by akmatleu on 22.05.17.
 */
public class CreateReceiptAction implements Action {

    private static final Logger LOG = LoggerFactory.getLogger(CreateReceiptAction.class);

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {

        Reciept reciept = new Reciept();
        Patient patient = new Patient();
        Drug drug = new Drug();

        patient.setPatientCode(request.getParameter(PATIENT_CODE));
        String code = patient.getPatientCode();
        HttpSession session = request.getSession();
        session.setAttribute(PATIENT_TYPE, patient.getPatientCode());
        reciept.setPatient(patient);
        drug.setId(Integer.parseInt(request.getParameter(DRUG_ID)));
        reciept.setDrug(drug);
        reciept.setReciept_status(true);
        reciept.setDrugDoze(Float.valueOf(request.getParameter(DRUG_DOZE)));
        reciept.setRecieptDate(java.time.LocalDate.now());
        ChemisterService chemisterService = new ChemisterService();
        try{
            chemisterService.createReceipt(reciept);
        }catch (ServiceExeption e){
            LOG.error("Cannot create recepiet from Action",e);
        }
        return new ActionResult(CREATE_RECEIPT+"?patientCode="+code,true);
    }
}
