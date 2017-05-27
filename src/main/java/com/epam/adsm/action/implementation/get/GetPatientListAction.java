package com.epam.adsm.action.implementation.get;

import com.epam.adsm.action.Action;
import com.epam.adsm.action.ActionResult;
import com.epam.adsm.model.Diagnosis;
import com.epam.adsm.model.Patient;
import com.epam.adsm.model.Research;
import com.epam.adsm.model.Staff;
import com.epam.adsm.service.CordinatorService;
import com.epam.adsm.service.DoctorService;
import com.epam.adsm.service.PatientService;
import com.epam.adsm.service.ServiceExeption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static com.epam.adsm.action.ActionConstants.*;

/**
 * Created by akmatleu on 21.05.17.
 */
public class GetPatientListAction implements Action {
    private static final Logger LOG = LoggerFactory.getLogger(GetPatientListAction.class);

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {
        List<Patient> patientList;
        List<Diagnosis> diagnosisList;
        List<Research> researchList;
        List<Patient> doctorPatients;
        PatientService patientService = new PatientService();


        try {
            HttpSession session = request.getSession();
            if (session.getAttribute(ROLE).equals(COORDINATOR)) {
                patientList = patientService.getAllPatients();
                diagnosisList = patientService.getAllDiagnosis();
                researchList = patientService.getAllResearch();
                request.setAttribute(ALL_PATIENTS, patientList);
                request.setAttribute(ALL_DIAGNOSIS, diagnosisList);
                request.setAttribute(ALL_RESEARCH, researchList);
                return new ActionResult(GET_ALL_PATIENTS_PAGE);
            } else {
                Staff doctor = new Staff();
                patientList = new ArrayList<>();
                diagnosisList = new ArrayList<>();

                String doctorId = String.valueOf(session.getAttribute(STAFF_ID));
                doctor.setId(Integer.parseInt(doctorId));
                DoctorService doctorService = new DoctorService();
                doctorPatients = doctorService.getDoctorPatients(doctor);
                for (Patient patient : doctorPatients) {
                    patientList.add(patient);
                    diagnosisList.add(patient.getDiagnosis());
                }

                request.setAttribute(ALL_PATIENTS, patientList);
                request.setAttribute(ALL_DIAGNOSIS, diagnosisList);
            }
        } catch (ServiceExeption e) {
            LOG.error("cannot get staff list from service", e);
        }

        return new ActionResult(DOCTOR_PATIENTS_PAGE);
    }
}
