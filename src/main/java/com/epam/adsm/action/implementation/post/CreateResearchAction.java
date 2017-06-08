package com.epam.adsm.action.implementation.post;

import com.epam.adsm.action.Action;
import com.epam.adsm.action.ActionConstants;
import com.epam.adsm.action.ActionResult;
import com.epam.adsm.action.implementation.get.CreatePatientAction;
import com.epam.adsm.model.*;
import com.epam.adsm.service.CordinatorService;
import com.epam.adsm.service.PatientService;
import com.epam.adsm.service.ServiceExeption;
import com.epam.adsm.service.ValidationAndEncoderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Properties;

import static com.epam.adsm.action.ActionConstants.*;

/**
 * Created by akmatleu on 20.05.17.
 */
public class CreateResearchAction implements Action {
    private static final Logger LOG = LoggerFactory.getLogger(CreateStaffAction.class);
    private boolean invalid = false;

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {
        boolean isRedirect = true;
        CordinatorService cordinatorService = new CordinatorService();
        PatientService patientService = new PatientService();
        Staff staff = new Staff();
        Patient patient = new Patient();
        Diagnosis diagnosis = new Diagnosis();
        Research research = new Research();
        Properties properties = new Properties();
        try {
            properties.load(CreateStaffAction.class.getClassLoader().getResourceAsStream(VALIDATION_PROPERTIES));
        } catch (IOException e) {
            LOG.error("cannot load validation properties", e);
        }
        staff.setId(Integer.parseInt(request.getParameter(ActionConstants.STAFF_ID)));
        String patientCode = request.getParameter(ActionConstants.PATIENT_CODE);
        String initial = request.getParameter(ActionConstants.INITIAL);
        String pass = request.getParameter(ActionConstants.PASSWORD);
        String confirmedPassword = request.getParameter(ActionConstants.CONFIRMED_PASS);
        String email = request.getParameter(ActionConstants.EMAIL);
        Date dateBirthday = Date.valueOf(request.getParameter(ActionConstants.DATE_BIRTHDAY));
        String phoneNumber = request.getParameter(ActionConstants.PHONE);
        String riskFactor = request.getParameter(ActionConstants.RISK_FACTOR);
        String gender = request.getParameter(ActionConstants.GENDER);
        String localization = request.getParameter(ActionConstants.LOCALIZATION);
        String prevalence = request.getParameter(ActionConstants.PREVALENCE);
        String clinicalForm = request.getParameter(ActionConstants.CLINICAL_FORM);
        String mbtStatus = request.getParameter(ActionConstants.MBT_STATUS);
        String patientType = request.getParameter(ActionConstants.PATIENT_TYPE);
        String dstStatus = request.getParameter(ActionConstants.DST_STATUS);
        if (patientCode.isEmpty() || initial.isEmpty() || phoneNumber.isEmpty() || pass.isEmpty() || confirmedPassword.isEmpty() || email.isEmpty()) {
            invalid = true;
            request.setAttribute(ERROR, ERROR_NULL_FIELDS);
        } else if (!confirmedPassword.equals(pass) || !ValidationAndEncoderService.isValidValue(pass, properties.getProperty(PASSWORD_VALIDATION))) {
            invalid = true;
            request.setAttribute(ERROR, ERROR_PASSWORD);
        } else {
            try {
                if (!patientService.isPatientPhoneNumberAvailable(phoneNumber)) {
                    invalid = true;
                    request.setAttribute(ERROR, ERROR_PHONE_EXIST);
                }
            } catch (ServiceExeption e) {
                LOG.error("Cannot check phone number in bd", e);
            }
        }
        if (invalid) {
            invalid = false;
            CreatePatientAction createPatientAction = new CreatePatientAction();
            createPatientAction.execute(request, response);
            return new ActionResult(CREATE_PATIENT_PAGE);
        }
        createPatientAndDiagnosis(patientCode, initial, pass, email, dateBirthday, phoneNumber, gender, staff, riskFactor,
                localization, prevalence, clinicalForm, mbtStatus, patientType, dstStatus, patient, diagnosis);
        try {
            cordinatorService.createPatientAndDiagnosis(patient, diagnosis);
            Date enrollmentDate = Date.valueOf(request.getParameter(ENROLLMENT_DATE));
            research.setEnrollmentDate(enrollmentDate.toLocalDate());
            research.setActivationDate(LocalDate.now());
            research.setActivationStatus(true);
            research.setResearchProgress(INCOMPLETE);
            research.setPatient(patient);
            cordinatorService.createResearch(research);

        } catch (ServiceExeption serviceExeption) {
            LOG.error("Cannot create research from coordinator service", serviceExeption);
        }
        return new ActionResult(CREATE_RECEIPT + "?patientCode=" + patientCode, isRedirect);
    }

    private void createPatientAndDiagnosis(String patientCode, String initial, String password, String email,
                                           Date dateBirthday, String phoneNumber, String gender, Staff staff,
                                           String riskFactor, String localization, String prevalence, String clinicalForm,
                                           String mbtStatus, String patientType, String dstStatus, Patient patient,
                                           Diagnosis diagnosis) {
        patient.setPatientCode(patientCode);
        patient.setInitial(initial);
        patient.setPassword(ValidationAndEncoderService.encodePassword(password));
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
    }
}
