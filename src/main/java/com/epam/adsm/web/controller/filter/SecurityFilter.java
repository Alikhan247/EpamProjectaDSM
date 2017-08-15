package com.epam.adsm.web.controller.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.epam.adsm.action.ActionConstants.*;

public class SecurityFilter implements Filter {
    private static final Logger LOG = LoggerFactory.getLogger(SecurityFilter.class);
    private static final List<String> COORDINATOR_PAGES = new ArrayList<>();
    private static final List<String> DOCTOR_PAGES = new ArrayList<>();
    private static final List<String> CHEMISTER_PAGES = new ArrayList<>();
    private static final List<String> PATIENT_PAGES = new ArrayList<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        COORDINATOR_PAGES.add("/createStaff");
        COORDINATOR_PAGES.add("/createPatient");
        COORDINATOR_PAGES.add("/getStaffList");
        COORDINATOR_PAGES.add("/getPatientList");
        COORDINATOR_PAGES.add("/staff/");
        COORDINATOR_PAGES.add("/patient/");
        COORDINATOR_PAGES.add("/createReceipt");
        CHEMISTER_PAGES.add("/createDrugAdministration");
        CHEMISTER_PAGES.add("/createAdministration/");
        CHEMISTER_PAGES.add("/createAdverseEvent/");
        CHEMISTER_PAGES.add("/createAdverseEvent");
        DOCTOR_PAGES.add("/event/details");
        DOCTOR_PAGES.add("/doctorSchedule");
        DOCTOR_PAGES.add("/getMyPatients");
        DOCTOR_PAGES.add("/getMyPatientsAdverse");
        PATIENT_PAGES.add("/patientHomePage");
        PATIENT_PAGES.add("/patientEventDetails");
        PATIENT_PAGES.add("/event/details");
        PATIENT_PAGES.add("/createAdverseEvent/create");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String path = request.getPathInfo();
        for (String coordinatorPage : COORDINATOR_PAGES) {
            if (path.startsWith(coordinatorPage)) {
                if (request.getSession().getAttribute(ROLE).equals(COORDINATOR)) {
                    filterChain.doFilter(request, response);
                    return;
                } else {
                    LOG.info("It's not coordinator role, redirect log in page");
                    response.sendRedirect(LOGIN_PAGE);
                    return;
                }
            }
        }
        for (String doctorPage : DOCTOR_PAGES) {
            if (path.startsWith(doctorPage)) {
                if (request.getSession().getAttribute(ROLE).equals(DOCTOR) || (doctorPage.equals("/event/details") && request.getSession().getAttribute(ROLE).equals(PATIENT_ROLE))) {
                    filterChain.doFilter(request, response);
                    return;
                } else {
                    LOG.info("It' not doctor role, redirect log in page");
                    response.sendRedirect(LOGIN_PAGE);
                    return;
                }
            }
        }
        for (String chemisterPage : CHEMISTER_PAGES) {
            if (path.startsWith(chemisterPage)) {
                if (request.getSession().getAttribute(ROLE).equals(CHEMISTRY) || (chemisterPage.equals("/createAdverseEvent/") && request.getSession().getAttribute(ROLE).equals(PATIENT_ROLE))) {
                    filterChain.doFilter(request, response);
                    return;
                } else {
                    LOG.info("It's not drug delivery role, redirect log in page");
                    response.sendRedirect(LOGIN_PAGE);
                    return;
                }
            }
        }
        for (String patientPage : PATIENT_PAGES) {
            if (path.startsWith(patientPage)) {
                if (request.getSession().getAttribute(ROLE).equals(PATIENT_ROLE)) {
                    filterChain.doFilter(request, response);
                    return;
                } else {
                    LOG.info("It's not patient role, redirect log in page");
                    response.sendRedirect(LOGIN_PAGE);
                    return;
                }
            }
        }
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        COORDINATOR_PAGES.clear();
        CHEMISTER_PAGES.clear();
        DOCTOR_PAGES.clear();
        PATIENT_PAGES.clear();
    }
}
