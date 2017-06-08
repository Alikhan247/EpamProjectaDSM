package com.epam.adsm.service;

import com.epam.adsm.dao.DaoException;
import com.epam.adsm.dao.DaoFactory;
import com.epam.adsm.dao.implementation.AdverseEventDao;
import com.epam.adsm.dao.implementation.PatientDao;
import com.epam.adsm.dao.implementation.TaskDao;
import com.epam.adsm.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by akmatleu on 13.05.17.
 */
public class DoctorService {
    private static final Logger LOG = LoggerFactory.getLogger(DoctorService.class);

    public List<Patient> getDoctorSchedule(Staff staff) throws ServiceExeption {
        List<Patient> patientList;
        try (DaoFactory daoFactory = new DaoFactory()) {
            PatientDao patientDao = daoFactory.getDao(PatientDao.class);
            daoFactory.startTransaction();
            patientList = patientDao.getDoctorSchedule(staff);
            daoFactory.commitTransaction();
        } catch (DaoException e) {
            LOG.error("Cannot get schedule doctor"+staff.getId(),e);
            throw new ServiceExeption("Cannot get doctor schedule from dao", e);
        }
        return patientList;
    }

    public List<Patient> getDoctorPatients(Staff staff) throws ServiceExeption {
        List<Patient> patients;
        try (DaoFactory daoFactory = new DaoFactory()) {
            PatientDao patientDao = daoFactory.getDao(PatientDao.class);
            daoFactory.startTransaction();
            patients = patientDao.getDoctorPatients(staff);
            daoFactory.commitTransaction();
        } catch (DaoException e) {
            LOG.error("Cannot get doctor-"+staff.getId()+" patients",e);
            throw new ServiceExeption("Cannot get doctor-" + staff.getId() + " patients", e);
        }
        return patients;
    }

    public List<AdverseEvent> getDoctorAdverseEvents(Staff doctor) throws ServiceExeption {
        List<AdverseEvent> adverseEvents;
        try (DaoFactory daoFactory = new DaoFactory()) {
            AdverseEventDao adverseEventDao = daoFactory.getDao(AdverseEventDao.class);
            adverseEvents = adverseEventDao.getDoctorPatientsAdverseEvents(doctor);
        } catch (DaoException e) {
            LOG.error("Cannot get adverse events for doctor-"+doctor.getId(),e);
            throw new ServiceExeption("Cannot get adverse events for doctor-" + doctor.getId(), e);
        }
        return adverseEvents;
    }

    public void updateAdverseEventStatus(AdverseEvent adverseEvent) throws ServiceExeption{
        try(DaoFactory daoFactory = new DaoFactory()){
         AdverseEventDao adverseEventDao = daoFactory.getDao(AdverseEventDao.class);
         adverseEventDao.update(adverseEvent);
        }catch (DaoException e){
            LOG.error("Cannot update adverse event status from dao",e);
            throw new ServiceExeption("Cannot update adverse event status"+adverseEvent.getId(),e);
        }
    }

    public void updateTaskProgress(Task task, Research research) throws ServiceExeption {
        try (DaoFactory daoFactory = new DaoFactory()) {
            TaskDao taskDao = daoFactory.getDao(TaskDao.class);
            daoFactory.startTransaction();
            taskDao.updateTask(task);
            daoFactory.commitTransaction();
            CordinatorService cordinatorService = new CordinatorService();
            cordinatorService.recalculateResearchProgress(research);
        } catch (DaoException e) {
            LOG.error("Cannot update task progress from dao",e);
            throw new ServiceExeption("Cannot update  task from dao", e);
        }
    }
}
