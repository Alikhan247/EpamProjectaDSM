package com.epam.adsm.service;

import com.epam.adsm.dao.DaoException;
import com.epam.adsm.dao.DaoFactory;
import com.epam.adsm.dao.implementation.*;
import com.epam.adsm.model.*;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
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

    private static int COMPLETE = 100;
    private static int INCOMPLETE = 0;
    private static double ALL_TASKS = 341.0;
    private static int PROCENT = 100;

    public void createStaff(Staff staff) throws ServiceExeption {
        try(DaoFactory daoFactory = new DaoFactory()){
            StaffDAO staffDAO = daoFactory.getDao(StaffDAO.class);
            daoFactory.startTransaction();
            staffDAO.create(staff);
            daoFactory.commitTransaction();
        }catch (DaoException e){
            throw  new ServiceExeption("Cannot create dao for new staff",e);
        }
    }

    public void createPatientAndDiagnosis(Patient patient, Diagnosis diagnosis) throws ServiceExeption {

        try(DaoFactory daoFactory = new DaoFactory()){
            PatientDAO patientDAO = daoFactory.getDao(PatientDAO.class);
            DiagnosisDAO diagnosisDAO = daoFactory.getDao(DiagnosisDAO.class);
            daoFactory.startTransaction();
            patientDAO.create(patient);
            diagnosisDAO.create(diagnosis);
            daoFactory.commitTransaction();

        }catch (DaoException e){
            throw new ServiceExeption("Cannot create dao fro new patient",e);
        }
    }

    public void createResearch(Research research) throws ServiceExeption {
        List<EventPrototype> eventPrototypes;
        List<Protocol> taskPrototypesForEvent;
        try(DaoFactory daoFactory = new DaoFactory()){
            try{
            ResearchDao researchDao = daoFactory.getDao(ResearchDao.class);
            EventPrototypeDao eventPrototype = daoFactory.getDao(EventPrototypeDao.class);
            EventDao eventDao = daoFactory.getDao(EventDao.class);
            ProtocolDao protocolDao = daoFactory.getDao(ProtocolDao.class);
            TaskDao taskDao = daoFactory.getDao(TaskDao.class);
            daoFactory.startTransaction();
            research = researchDao.create(research);
            eventPrototypes = eventPrototype.getAllEventsPrototype();
            LOG.info("Take events prototypes from dao");
            for (EventPrototype prototype : eventPrototypes){
                Event event = new Event();
                LOG.info("event prototype id issss = "+prototype.getId());
                event.setEventDate(research.getEnrollmentDate().plusDays(prototype.getEventInterval()));
                if(event.getEventDate().isBefore(java.time.LocalDate.now())){
                    event.setEventProgress(COMPLETE);
                }else {
                event.setEventProgress(INCOMPLETE);
                }
                event.setEventPrototype(prototype);
                event.setResearch(research);
                event = eventDao.create(event);
                LOG.info("Created event "+event.toString());
                taskPrototypesForEvent = protocolDao.getAllTasksForEvent(prototype);
                LOG.info("Task prototype id = " + taskPrototypesForEvent.toString());
                Task task = new Task();
                    for (Protocol protocol : taskPrototypesForEvent) {
                        task.setTaskPrototype(protocol.getTaskPrototype());
                        task.setEvent(event);
                        if(event.getEventProgress()==INCOMPLETE) {
                            task.setTaskProgress(INCOMPLETE);
                        }else {
                            task.setTaskProgress(COMPLETE);
                        }
                        task = taskDao.create(task);
                        LOG.info("Created task for event" + task.toString());
                    }
            }
            daoFactory.commitTransaction();
            recalculateResearchProgress(research);
            } catch (DaoException e){
                daoFactory.rollbackTransaction();
                throw new ServiceExeption("Cannot create new research",e);
            }

        } catch (DaoException e){
            throw new ServiceExeption("Cannot create dao for new research ",e);
        }
    }

    public void recalculateResearchProgress(Research research) throws ServiceExeption {
        double result;
        List<Event> events;
        int countCompletedTasks = 0;
        try(DaoFactory daoFactory = new DaoFactory()){
            try {
                EventDao eventDao = daoFactory.getDao(EventDao.class);
                ResearchDao researchDao = daoFactory.getDao(ResearchDao.class);
                daoFactory.startTransaction();
                events = eventDao.getAllEventsFromResearch(research);
                for (Event event : events) {
                    event = eventDao.findById(event.getId());
                    for(Integer progress : event.getTaskProgress()){
                        if(progress==100){
                            countCompletedTasks++;
                        }
                    }
                }
                result = countCompletedTasks / ALL_TASKS*PROCENT;
                research.setResearchProgress(result);
                researchDao.update(research);
                daoFactory.commitTransaction();
            }catch (DaoException e){
                daoFactory.rollbackTransaction();
                throw new ServiceExeption("Cannot recalculate and update research progress"+research.getId(),e);
            }
        }catch (DaoException e){
            throw  new ServiceExeption("Canno recalculate research progress for research:"+research.getId(),e);
        }
    }

    public Research findResearchByPatientCode(String patientCode) throws ServiceExeption {
        Research research;
        try(DaoFactory daoFactory = new DaoFactory()){
            ResearchDao researchDao = daoFactory.getDao(ResearchDao.class);
            daoFactory.startTransaction();
            research = researchDao.findByPatientCode(patientCode);
            daoFactory.commitTransaction();
        }catch (DaoException e){
            throw new ServiceExeption("Cannot find reasearch from dao",e);
        }
        return research;
    }
    public Research findResearchByEventId(int id) throws ServiceExeption {
        Research research;
        try(DaoFactory daoFactory = new DaoFactory()){
            ResearchDao researchDao = daoFactory.getDao(ResearchDao.class);
            daoFactory.startTransaction();
           research = researchDao.findByEventId(id);
           daoFactory.commitTransaction();
        }catch (DaoException e){
            throw  new ServiceExeption("Cannot find research by event id from dao",e);
        }
        return research;
    }

    public DiagnosisDate getDiagnosisDate() throws ServiceExeption {
        DiagnosisDate diagnosisDate = new DiagnosisDate();
        try(DaoFactory daoFactory = new DaoFactory()){

            DiagnosisDateDao diagnosisDateDao = daoFactory.getDao(DiagnosisDateDao.class);
            daoFactory.startTransaction();
            diagnosisDate = diagnosisDateDao.getDiagnosisDate();
            daoFactory.commitTransaction();
        }catch (DaoException e){
            throw new ServiceExeption("cannot get diagnosis data from dao",e);
        }
        return diagnosisDate;
    }

    public List<Staff> getAllDoctors() throws ServiceExeption {
        List<Staff> doctors = new ArrayList<>();
        try(DaoFactory daoFactory = new DaoFactory()){
            StaffDAO staffDAO = daoFactory.getDao(StaffDAO.class);
            daoFactory.startTransaction();
            for(Staff staff : staffDAO.getAllStaff()){
                   if(staff.getRole().equals(DOCTOR)){
                       doctors.add(staff);
                   }
            }
            daoFactory.commitTransaction();
        }catch (DaoException e){
            throw new ServiceExeption("Cannot get all doctors",e);
        }
        return doctors;
    }

    public List<Staff> getAllStaff() throws ServiceExeption {
        List<Staff> staffList = new ArrayList<>();
        try(DaoFactory daoFactory = new DaoFactory()){
            StaffDAO staffDAO = daoFactory.getDao(StaffDAO.class);
            daoFactory.startTransaction();
            staffList = staffDAO.getAllStaff();
            daoFactory.commitTransaction();
        }catch (DaoException e){
            throw new ServiceExeption("Cannot get staff list from dao",e);
        }
        return staffList;
    }

    public Staff findStaffById(int id) throws  ServiceExeption {
        Staff staff;
        try(DaoFactory daoFactory = new DaoFactory()) {
            StaffDAO staffDAO = daoFactory.getDao(StaffDAO.class);
            daoFactory.startTransaction();
            staff = staffDAO.findById(id);
            daoFactory.commitTransaction();
        }catch (DaoException e){
            throw new ServiceExeption("Cannot find staff by id from dao",e);
        }
        return staff;
    }

    public void updateResearch(Research research) throws ServiceExeption{
        try (DaoFactory daoFactory = new DaoFactory()){
            ResearchDao researchDao = daoFactory.getDao(ResearchDao.class);
            daoFactory.startTransaction();
            researchDao.setUpdateResearchStatus(research);
            daoFactory.commitTransaction();
        }catch (DaoException e){
            throw new ServiceExeption("Cannot update research",e);
        }

    }

    public void updateStaff(Staff staff) throws ServiceExeption {
        try (DaoFactory daoFactory = new DaoFactory()){
            StaffDAO staffDAO = daoFactory.getDao(StaffDAO.class);
            daoFactory.startTransaction();
            staffDAO.update(staff);
            daoFactory.commitTransaction();
        }catch (DaoException e){
            throw new ServiceExeption("Cannot update staff",e);
        }
    }
}
