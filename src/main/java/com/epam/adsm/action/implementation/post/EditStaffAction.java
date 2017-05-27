package com.epam.adsm.action.implementation.post;

import com.epam.adsm.action.Action;
import com.epam.adsm.action.ActionResult;
import com.epam.adsm.model.Staff;
import com.epam.adsm.service.CordinatorService;
import com.epam.adsm.service.ServiceExeption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.epam.adsm.action.ActionConstants.*;

/**
 * Created by akmatleu on 21.05.17.
 */
public class EditStaffAction implements Action {
    private static final Logger LOG = LoggerFactory.getLogger(EditStaffAction.class);

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {
        Staff staff = new Staff();

        staff.setId(Integer.parseInt(request.getParameter("id")));
        staff.setPhoneNumber(request.getParameter(PHONE));
        staff.setActivity_status(Boolean.valueOf(request.getParameter(STATUS)));

        CordinatorService cordinatorService = new CordinatorService();
        try {
            cordinatorService.updateStaff(staff);
        }catch (ServiceExeption e){
         LOG.error("Cannot update staff with id="+staff.getId(),e);
        }
        return new ActionResult("/do/staff/edit?id="+staff.getId(),true);
    }
}
