package com.epam.adsm.action.implementation.post;

import com.epam.adsm.action.Action;
import com.epam.adsm.action.ActionResult;
import com.epam.adsm.model.DrugAdministration;
import com.epam.adsm.model.Patient;
import com.epam.adsm.model.Receipt;
import com.epam.adsm.service.ChemisterService;
import com.epam.adsm.service.ServiceExeption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.epam.adsm.action.ActionConstants.*;

/**
 * Created by akmatleu on 23.05.17.
 */
public class CreateDrugAdministrationAction implements Action {
    private static final Logger LOG = LoggerFactory.getLogger(CreateDrugAdministrationAction.class);

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {
        boolean isRedirect = true;
        DrugAdministration drugAdministration = new DrugAdministration();
        Patient patient = new Patient();
        Receipt receipt = new Receipt();
        String patientCode = request.getParameter(PATIENT_CODE);
        System.out.println(patientCode);
        patient.setPatientCode(patientCode);
        receipt.setId(Integer.parseInt(request.getParameter(RECEIPT_ID)));
        drugAdministration.setPatient(patient);
        drugAdministration.setReceipt(receipt);
        drugAdministration.setDrugStatus(request.getParameter(DRUG_STATUS));
        drugAdministration.setDrugAdministraionDate(java.time.LocalDate.now());
        ChemisterService chemisterService = new ChemisterService();
        try {
            chemisterService.createDrugAdministraion(drugAdministration);
        } catch (ServiceExeption e) {
            LOG.error("Cannot create drug administration from service", e);
        }
        return new ActionResult(CREATE_ADMINISTRATION_PATIENT_PAGE + patientCode, isRedirect);
    }
}
