package com.epam.adsm.action.implementation.post;

import com.epam.adsm.service.CordinatorService;
import com.epam.adsm.service.ServiceExeption;
import com.epam.adsm.action.Action;
import com.epam.adsm.action.ActionConstants;

import com.epam.adsm.action.ActionResult;
import com.epam.adsm.model.Staff;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.epam.adsm.action.ActionConstants.HOME_PAGE;

/**
 * Created by akmatleu on 18.05.17.
 */
public class CreateStaffAction implements Action {

    private static final Logger LOG = LoggerFactory.getLogger(CreateStaffAction.class);


    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response)  {

        CordinatorService cordinatorService = new CordinatorService();
        Staff staff = new Staff();

        String name = request.getParameter(ActionConstants.NAME);
        String surname = request.getParameter(ActionConstants.SURNAME);
        String phoneNumber = request.getParameter(ActionConstants.PHONE);
        String role = request.getParameter(ActionConstants.ROLE);
        String pass = request.getParameter(ActionConstants.PASSWORD);

        staff.setName(name);
        staff.setSurname(surname);
        staff.setPhoneNumber(phoneNumber);
        staff.setRole(role);
        staff.setPassword(pass);
        staff.setActivity_status(true);

        try {
            cordinatorService.createStaff(staff);
        } catch (ServiceExeption serviceExeption) {
            LOG.error("Cannot create staff by cordinator Service",serviceExeption);
        }
        return new ActionResult(HOME_PAGE,true);
    }
}
