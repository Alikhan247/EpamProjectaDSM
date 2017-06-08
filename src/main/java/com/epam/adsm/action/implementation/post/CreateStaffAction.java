package com.epam.adsm.action.implementation.post;

import com.epam.adsm.service.CordinatorService;
import com.epam.adsm.service.ServiceExeption;
import com.epam.adsm.action.Action;
import com.epam.adsm.action.ActionConstants;

import com.epam.adsm.action.ActionResult;
import com.epam.adsm.model.Staff;
import com.epam.adsm.service.ValidationAndEncoderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Properties;

import static com.epam.adsm.action.ActionConstants.*;

/**
 * Created by akmatleu on 18.05.17.
 */
public class CreateStaffAction implements Action {
    private static final Logger LOG = LoggerFactory.getLogger(CreateStaffAction.class);
    private boolean invalid = false;

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {
        boolean isRedirect = true;
        CordinatorService cordinatorService = new CordinatorService();
        Properties properties = new Properties();
        Staff staff = new Staff();
        try {
            properties.load(CreateStaffAction.class.getClassLoader().getResourceAsStream(VALIDATION_PROPERTIES));
        } catch (IOException e) {
            LOG.error("cannot load validation properties", e);
        }
        String name = request.getParameter(ActionConstants.NAME);
        String surname = request.getParameter(ActionConstants.SURNAME);
        String phoneNumber = request.getParameter(ActionConstants.PHONE);
        String role = request.getParameter(ActionConstants.ROLE);
        String password = request.getParameter(ActionConstants.PASSWORD);
        String confirmedPassword = request.getParameter(ActionConstants.CONFIRMED_PASS);
        if (name.isEmpty() || surname.isEmpty() || phoneNumber.isEmpty() || password.isEmpty() || confirmedPassword.isEmpty()) {
            invalid = true;
            request.setAttribute(ERROR, ERROR_NULL_FIELDS);
        } else if (!confirmedPassword.equals(password) || !ValidationAndEncoderService.isValidValue(password, properties.getProperty(PASSWORD_VALIDATION))) {
            invalid = true;
            request.setAttribute(ERROR, ERROR_PASSWORD);
        }
        try {
            if (!cordinatorService.isPhoneNumberAvailable(phoneNumber)) {
                invalid = true;
                request.setAttribute(ERROR, ERROR_PHONE_EXIST);
            }
        } catch (ServiceExeption e) {
            LOG.error("Cannot check phone number in bd", e);
        }
        if (invalid) {
            invalid = false;
            return new ActionResult(CREATE_STAFF_PAGE);
        }
        staff.setName(name);
        staff.setSurname(surname);
        staff.setPhoneNumber(phoneNumber);
        staff.setRole(role);
        staff.setPassword(ValidationAndEncoderService.encodePassword(password));
        staff.setActivity_status(true);
        try {
            cordinatorService.createStaff(staff);
        } catch (ServiceExeption serviceExeption) {
            LOG.error("Cannot createDiagnosis staff by cordinator Service", serviceExeption);
        }
        return new ActionResult(HOME_PAGE, isRedirect);
    }
}
