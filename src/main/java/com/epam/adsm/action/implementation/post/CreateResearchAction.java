package com.epam.adsm.action.implementation.post;

import com.epam.adsm.action.Action;
import com.epam.adsm.action.ActionConstants;
import com.epam.adsm.action.ActionResult;
import com.epam.adsm.model.Diagnosis;
import com.epam.adsm.model.Patient;
import com.epam.adsm.model.Research;
import com.epam.adsm.model.Staff;
import com.epam.adsm.service.CordinatorService;
import com.epam.adsm.service.ServiceExeption;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.Charset;
import java.sql.Date;
import java.time.LocalDate;

import static com.epam.adsm.action.ActionConstants.*;

/**
 * Created by akmatleu on 20.05.17.
 */
public class CreateResearchAction implements Action {
    private static final Logger LOG = LoggerFactory.getLogger(CreateStaffAction.class);

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {
        CordinatorService cordinatorService = new CordinatorService();
        Staff staff = new Staff();
        Patient patient = new Patient();
        Diagnosis diagnosis = new Diagnosis();
        Research research = new Research();

        staff.setId(Integer.parseInt(request.getParameter(ActionConstants.STAFF_ID)));


        String patientCode = request.getParameter(ActionConstants.PATIENT_CODE);
        String initial = request.getParameter(ActionConstants.INITIAL);
        String pass = request.getParameter(ActionConstants.PASSWORD);
        String email = request.getParameter(ActionConstants.EMAIL);
        Date   dateBirthday =  Date.valueOf(request.getParameter(ActionConstants.DATE_BIRTHDAY));
        String phoneNumber = request.getParameter(ActionConstants.PHONE);
        String riskFactor = request.getParameter(ActionConstants.RISK_FACTOR);
        String gender = request.getParameter(ActionConstants.GENDER);

        String localization = request.getParameter(ActionConstants.LOCALIZATION);
        String prevalence = request.getParameter(ActionConstants.PREVALENCE);
        String clinicalForm = request.getParameter(ActionConstants.CLINICAL_FORM);
        String mbtStatus = request.getParameter(ActionConstants.MBT_STATUS);
        String patientType = request.getParameter(ActionConstants.PATIENT_TYPE);
        String dstStatus = request.getParameter(ActionConstants.DST_STATUS);

        patient.setPatientCode(patientCode);
        patient.setInitial(initial);
        patient.setPassword(pass);
        patient.setEmail(email);
        patient.setDateOfBirthday(dateBirthday.toLocalDate());
        patient.setPhoneNumber(phoneNumber);
        patient.setPatientSex(gender);
        patient.setDoctor(staff);
        patient.setConfirmed(true);

        diagnosis.setRiskFactor(riskFactor);
        diagnosis.setLocalizationDisease(localization);
        diagnosis.setPrevalence(prevalence);
        diagnosis.setClinicalForm(clinicalForm);
        diagnosis.setMbtStatus(mbtStatus);
        diagnosis.setPatientType(patientType);
        diagnosis.setDstStatus(dstStatus);
        diagnosis.setPatient(patient);


        try {
            cordinatorService.createPatientAndDiagnosis(patient,diagnosis);
            Date enrollmentDate = Date.valueOf(request.getParameter(ENROLLMENT_DATE));
            research.setEnrollmentDate(enrollmentDate.toLocalDate());
            research.setActivationDate(LocalDate.now());
            research.setActivationStatus(true);
            research.setResearchProgress(INCOMPLETE);
            research.setPatient(patient);
            cordinatorService.createResearch(research);

        } catch (ServiceExeption serviceExeption) {
            LOG.error("Cannot create staff by cordinator Service",serviceExeption);
        }




        return new ActionResult(CREATE_RECEIPT+"?patientCode="+patientCode,true);
    }
}
