package com.epam.adsm.service;

import com.epam.adsm.dao.DaoException;
import com.epam.adsm.dao.DaoFactory;
import com.epam.adsm.dao.implementation.*;
import com.epam.adsm.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static com.epam.adsm.action.ActionConstants.*;

/**
 * Created by akmatleu on 13.05.17.
 */
public class CordinatorService {
    private static final Logger LOG = LoggerFactory.getLogger(CordinatorService.class);
    private static final int COMPLETE = 100;
    private static final double INCOMPLETE_DOUBLE = 0;
    private static final int INCOMPLETE = 0;
    private static final double ALL_TASKS = 341.0;
    private static final int PERCENT = 100;

    public void createStaff(Staff staff) throws ServiceExeption {
        try (DaoFactory daoFactory = new DaoFactory()) {
            StaffDao staffDao = daoFactory.getDao(StaffDao.class);
            daoFactory.startTransaction();
            staffDao.create(staff);
            daoFactory.commitTransaction();
        } catch (DaoException e) {
            LOG.error("Cannot create  staff by staffDao", e);
            throw new ServiceExeption("Cannot createDrugAdministration dao for new staff", e);
        }
    }

    public void createPatientAndDiagnosis(Patient patient, Diagnosis diagnosis) throws ServiceExeption {
        try (DaoFactory daoFactory = new DaoFactory()) {
            PatientDao patientDao = daoFactory.getDao(PatientDao.class);
            DiagnosisDao diagnosisDao = daoFactory.getDao(DiagnosisDao.class);
            daoFactory.startTransaction();
            patientDao.create(patient);
            diagnosisDao.createDiagnosis(diagnosis);
            daoFactory.commitTransaction();
        } catch (DaoException e) {
            LOG.error("Cannot create patient and diagnosis from dao", e);
            throw new ServiceExeption("Cannot createDrugAdministration dao fro new patient", e);
        }
    }

    public void createResearch(Research research) throws ServiceExeption {
        List<EventPrototype> eventPrototypes;
        List<Protocol> taskPrototypesForEvent;
        try (DaoFactory daoFactory = new DaoFactory()) {
            ResearchDao researchDao = daoFactory.getDao(ResearchDao.class);
            EventPrototypeDao eventPrototype = daoFactory.getDao(EventPrototypeDao.class);
            EventDao eventDao = daoFactory.getDao(EventDao.class);
            ProtocolDao protocolDao = daoFactory.getDao(ProtocolDao.class);
            TaskDao taskDao = daoFactory.getDao(TaskDao.class);
            daoFactory.startTransaction();
            research = researchDao.create(research);
            eventPrototypes = eventPrototype.getAllEventsPrototype();
            for (EventPrototype prototype : eventPrototypes) {
                Event event = getEventForResearch(research, prototype);
                event = eventDao.create(event);
                taskPrototypesForEvent = protocolDao.getAllTasksForEvent(prototype);
                Task task = new Task();
                for (Protocol protocol : taskPrototypesForEvent) {
                    task.setTaskPrototype(protocol.getTaskPrototype());
                    task.setEvent(event);
                    calculateTaskProgress(event, task);
                    task = taskDao.createTask(task);
                }
            }
            daoFactory.commitTransaction();
            recalculateResearchProgress(research);
        } catch (DaoException e) {
            LOG.error("Cannot create research from dao", e);
            throw new ServiceExeption("Cannot create dao for new research ", e);
        }
    }

    private Event getEventForResearch(Research research, EventPrototype prototype) {
        Event event = new Event();
        event.setEventDate(research.getEnrollmentDate().plusDays(prototype.getEventInterval()));
        calculateEventProgress(event);
        event.setEventPrototype(prototype);
        event.setResearch(research);
        return event;
    }

    private void calculateEventProgress(Event event) {
        if (event.getEventDate().isBefore(java.time.LocalDate.now())) {
            event.setEventProgress(COMPLETE);
        } else {
            event.setEventProgress(INCOMPLETE);
        }
    }

    private void calculateTaskProgress(Event event, Task task) {
        if (event.getEventProgress() == INCOMPLETE_DOUBLE) {
            task.setTaskProgress(INCOMPLETE);
        } else {
            task.setTaskProgress(COMPLETE);
        }
    }

    public void recalculateResearchProgress(Research research) throws ServiceExeption {
        double result;
        List<Event> events;
        int countCompletedTasks = 0;
        try (DaoFactory daoFactory = new DaoFactory()) {
            try {
                EventDao eventDao = daoFactory.getDao(EventDao.class);
                ResearchDao researchDao = daoFactory.getDao(ResearchDao.class);
                daoFactory.startTransaction();
                events = eventDao.getAllEventsFromResearch(research);
                for (Event event : events) {
                    event = eventDao.findById(event.getId());
                    for (Integer progress : event.getTaskProgress()) {
                        if (progress == COMPLETE) {
                            countCompletedTasks++;
                        }
                    }
                }
                result = countCompletedTasks / ALL_TASKS * PERCENT;
                research.setResearchProgress(result);
                researchDao.update(research);
                daoFactory.commitTransaction();
            } catch (DaoException e) {
                daoFactory.rollbackTransaction();
                throw new ServiceExeption("Cannot recalculate and updateTask research progress" + research.getId(), e);
            }
        } catch (DaoException e) {
            throw new ServiceExeption("Cannot recalculate research progress for research:" + research.getId(), e);
        }
    }

    public boolean isPhoneNumberAvailable(String phoneNumber) throws ServiceExeption {
        Staff staff;
        boolean availablePhone = true;
        try (DaoFactory daoFactory = new DaoFactory()) {
            StaffDao staffDao = daoFactory.getDao(StaffDao.class);
            daoFactory.startTransaction();
            staff = staffDao.findByPhone(phoneNumber);
            daoFactory.commitTransaction();
        } catch (DaoException e) {
            LOG.error("Cannot check is phone available or not from dao", e);
            throw new ServiceExeption("Cannot check phone number from dao", e);
        }
        if (staff != null) {
            availablePhone = false;
        }
        return availablePhone;
    }

    public Research findResearchByPatientCode(String patientCode) throws ServiceExeption {
        Research research;
        try (DaoFactory daoFactory = new DaoFactory()) {
            ResearchDao researchDao = daoFactory.getDao(ResearchDao.class);
            daoFactory.startTransaction();
            research = researchDao.findByPatientCode(patientCode);
            daoFactory.commitTransaction();
        } catch (DaoException e) {
            LOG.error("Cannot find research by patient code from dao", e);
            throw new ServiceExeption("Cannot find research from dao", e);
        }
        return research;
    }

    public Research findResearchByEventId(int id) throws ServiceExeption {
        Research research;
        try (DaoFactory daoFactory = new DaoFactory()) {
            ResearchDao researchDao = daoFactory.getDao(ResearchDao.class);
            daoFactory.startTransaction();
            research = researchDao.findByEventId(id);
            daoFactory.commitTransaction();
        } catch (DaoException e) {
            LOG.error("Cannot find research by event id from dao", e);
            throw new ServiceExeption("Cannot find research by event id from dao", e);
        }
        return research;
    }

    public DiagnosisDate getDiagnosisDate() throws ServiceExeption {
        DiagnosisDate diagnosisDate;
        try (DaoFactory daoFactory = new DaoFactory()) {
            DiagnosisDateDao diagnosisDateDao = daoFactory.getDao(DiagnosisDateDao.class);
            daoFactory.startTransaction();
            diagnosisDate = diagnosisDateDao.getDiagnosisDate();
            daoFactory.commitTransaction();
        } catch (DaoException e) {
            LOG.error("Cannot get diagnosis date from dao", e);
            throw new ServiceExeption("cannot get diagnosis data from dao", e);
        }
        return diagnosisDate;
    }

    public List<Staff> getAllDoctors() throws ServiceExeption {
        List<Staff> doctors = new ArrayList<>();
        try (DaoFactory daoFactory = new DaoFactory()) {
            StaffDao staffDao = daoFactory.getDao(StaffDao.class);
            daoFactory.startTransaction();
            for (Staff staff : staffDao.getAllStaff()) {
                if (staff.getRole().equals(DOCTOR)) {
                    doctors.add(staff);
                }
            }
            daoFactory.commitTransaction();
        } catch (DaoException e) {
            LOG.error("Cannot get all doctors from dao", e);
            throw new ServiceExeption("Cannot get all doctors", e);
        }
        return doctors;
    }

    public List<Staff> getAllStaff() throws ServiceExeption {
        List<Staff> staffList;
        try (DaoFactory daoFactory = new DaoFactory()) {
            StaffDao staffDao = daoFactory.getDao(StaffDao.class);
            daoFactory.startTransaction();
            staffList = staffDao.getAllStaff();
            daoFactory.commitTransaction();
        } catch (DaoException e) {
            LOG.error("Cannot get all staff", e);
            throw new ServiceExeption("Cannot get staff list from dao", e);
        }
        return staffList;
    }

    public Staff findStaffById(int id) throws ServiceExeption {
        Staff staff;
        try (DaoFactory daoFactory = new DaoFactory()) {
            StaffDao staffDao = daoFactory.getDao(StaffDao.class);
            daoFactory.startTransaction();
            staff = staffDao.findById(id);
            daoFactory.commitTransaction();
        } catch (DaoException e) {
            LOG.error("Cannot find staff by id", e);
            throw new ServiceExeption("Cannot find staff by id from dao", e);
        }
        return staff;
    }

    public void updateResearch(Research research) throws ServiceExeption {
        try (DaoFactory daoFactory = new DaoFactory()) {
            ResearchDao researchDao = daoFactory.getDao(ResearchDao.class);
            daoFactory.startTransaction();
            researchDao.setUpdateResearchStatus(research);
            daoFactory.commitTransaction();
        } catch (DaoException e) {
            LOG.error("Cannot update research from dao", e);
            throw new ServiceExeption("Cannot update research", e);
        }
    }

    public void updateStaff(Staff staff) throws ServiceExeption {
        try (DaoFactory daoFactory = new DaoFactory()) {
            StaffDao staffDao = daoFactory.getDao(StaffDao.class);
            daoFactory.startTransaction();
            staffDao.update(staff);
            daoFactory.commitTransaction();
        } catch (DaoException e) {
            LOG.error("Cannot update staff from dao", e);
            throw new ServiceExeption("Cannot update staff", e);
        }
    }
}
