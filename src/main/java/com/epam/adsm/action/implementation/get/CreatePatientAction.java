package com.epam.adsm.action.implementation.get;

import com.epam.adsm.action.Action;
import com.epam.adsm.action.ActionResult;
import com.epam.adsm.model.DiagnosisDate;
import com.epam.adsm.model.Staff;
import com.epam.adsm.service.CordinatorService;
import com.epam.adsm.service.ServiceExeption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.dc.pr.PRError;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static com.epam.adsm.action.ActionConstants.*;

/**
 * Created by akmatleu on 20.05.17.
 */
public class CreatePatientAction implements Action {
    private static final Logger LOG = LoggerFactory.getLogger(CreatePatientAction.class);

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            CordinatorService cordinatorService = new CordinatorService();
            List<Staff> doctors = cordinatorService.getAllDoctors();
            request.setAttribute(DOCTORS, doctors);
            DiagnosisDate diagnosisDate = cordinatorService.getDiagnosisDate();
            List<String> clinicalForms = diagnosisDate.getClinicalForm();
            List<String> riskFactors = diagnosisDate.getRiskFactors();
            List<String> dstsStatus = diagnosisDate.getDstStatus();
            List<String> localizations = diagnosisDate.getLocalizations();
            List<String> prevalences = diagnosisDate.getReleavences();
            List<String> mbtStatuses = diagnosisDate.getMbtStatus();
            List<String> patientTypes = diagnosisDate.getTypePatient();
            List<String> genders = diagnosisDate.getGender();
            request.setAttribute(RISK_FACTORS, riskFactors);
            request.setAttribute(CLINICAL_FORMS, clinicalForms);
            request.setAttribute(DSTS_STATUS, dstsStatus);
            request.setAttribute(LOCALIZATIONS, localizations);
            request.setAttribute(PREVALENCES, prevalences);
            request.setAttribute(MBT_STATUSES, mbtStatuses);
            request.setAttribute(PATIENTTYPES, patientTypes);
            request.setAttribute(GENDERS,genders);
        } catch (ServiceExeption e) {
            LOG.error("Cannot get list of Doctors or diagnosis date from service", e);
        }
        return new ActionResult(CREATE_PATIENT_PAGE);
    }
}