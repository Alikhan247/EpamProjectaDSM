package com.epam.adsm.action.implementation.get;

import com.epam.adsm.action.Action;
import com.epam.adsm.action.ActionResult;
import com.epam.adsm.model.Event;
import com.epam.adsm.service.PatientService;
import com.epam.adsm.service.ServiceExeption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static com.epam.adsm.action.ActionConstants.*;

public class ShowEventDetails implements Action {
    private static final Logger LOG = LoggerFactory.getLogger(ShowEventDetails.class);

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {
        Event event;
        List<String> tasksName;
        List<Integer> tasksProgress;
        List<Integer> tasksId;
        int eventId = Integer.parseInt(request.getParameter(EVENT_ID));
        PatientService patientService = new PatientService();
        try {
            event = patientService.getEventById(eventId);
            tasksName = event.getTasksName();
            tasksProgress = event.getTaskProgress();
            tasksId = event.getTaskId();
            request.setAttribute(EVENT_ID, eventId);
            request.setAttribute(TASKS_NAME, tasksName);
            request.setAttribute(TASKS_PROGRESS, tasksProgress);
            request.setAttribute(TASKS_ID, tasksId);
        } catch (ServiceExeption e) {
            LOG.error("Cannot take event details from events", e);
        }
        return new ActionResult(DETAIL_EVENT_PAGE);
    }
}
