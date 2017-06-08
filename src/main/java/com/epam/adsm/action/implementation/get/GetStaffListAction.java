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
import java.util.List;

import static com.epam.adsm.action.ActionConstants.*;

/**
 * Created by akmatleu on 21.05.17.
 */
public class GetStaffListAction implements Action {
    private static final Logger LOG = LoggerFactory.getLogger(GetStaffListAction.class);

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {
        List<Staff> staffList = null;
        CordinatorService cordinatorService = new CordinatorService();
        try {
            staffList = cordinatorService.getAllStaff();
        } catch (ServiceExeption e) {
            LOG.error("cannot get staff list from service", e);
        }
        request.setAttribute(ALL_STAFF, staffList);
        return new ActionResult(GET_ALL_STAFF_PAGE);
    }
}
