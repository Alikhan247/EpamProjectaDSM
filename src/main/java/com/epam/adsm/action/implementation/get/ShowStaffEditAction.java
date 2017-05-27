package com.epam.adsm.action.implementation.get;

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
public class ShowStaffEditAction implements Action {
    private static final Logger LOG = LoggerFactory.getLogger(ShowStaffEditAction.class);
    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {

        int id = Integer.parseInt(request.getParameter("id"));
        Staff staff = null;

        CordinatorService cordinatorService = new CordinatorService();
        try {
           staff = cordinatorService.findStaffById(id);
        }catch (ServiceExeption e){
            LOG.error("Cannot find staff with id"+id,e);
        }

        request.setAttribute(STAFF,staff);

        return new ActionResult(STAFF_EDIT_PAGE);
    }
}
