package com.epam.adsm.action.implementation.post;

import com.epam.adsm.action.Action;
import com.epam.adsm.action.ActionResult;
import com.epam.adsm.model.Event;
import com.epam.adsm.model.Research;
import com.epam.adsm.model.Task;
import com.epam.adsm.service.CordinatorService;
import com.epam.adsm.service.DoctorService;
import com.epam.adsm.service.ServiceExeption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.epam.adsm.action.ActionConstants.*;

/**
 * Created by akmatleu on 24.05.17.
 */
public class ChangeProgressAction implements Action {

    private static final Logger LOG = LoggerFactory.getLogger(ChangeProgressAction.class);

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {
        Task task = new Task();
        Research research;
        String eventId = request.getParameter(EVENT_ID);
        task.setId(Integer.parseInt(request.getParameter(TASK_ID)));


        DoctorService doctorService = new DoctorService();
        CordinatorService cordinatorService = new CordinatorService();
        try {
            research = cordinatorService.findResearchByEventId(Integer.parseInt(request.getParameter(EVENT_ID)));
            doctorService.updateTaskProgress(task,research);
        }catch (ServiceExeption e){
            LOG.error("Cannot update task"+task.toString());
        }

        return new  ActionResult("/do/event/details?id="+eventId,true);
    }
}
