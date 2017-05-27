package com.epam.adsm.service;

import com.epam.adsm.dao.Dao;
import com.epam.adsm.dao.DaoException;
import com.epam.adsm.dao.DaoFactory;
import com.epam.adsm.dao.implementation.AdverseEventDAO;
import com.epam.adsm.dao.implementation.PatientDAO;
import com.epam.adsm.dao.implementation.TaskDao;
import com.epam.adsm.model.*;

import java.util.List;

/**
 * Created by akmatleu on 13.05.17.
 */
public class DoctorService {

    public List<Patient> getDoctorSchedule(Staff staff) throws ServiceExeption {
        List<Patient> patientList;
        try (DaoFactory daoFactory = new DaoFactory()) {
            PatientDAO patientDAO = daoFactory.getDao(PatientDAO.class);
            daoFactory.startTransaction();
            patientList = patientDAO.getDoctorSchedule(staff);
            daoFactory.commitTransaction();
        } catch (DaoException e) {
            throw new ServiceExeption("Cannot get patient list from dao", e);
        }
        return patientList;
    }

    public List<Patient> getDoctorPatients(Staff staff) throws ServiceExeption {
        List<Patient> patients;
        try (DaoFactory daoFactory = new DaoFactory()) {
            PatientDAO patientDAO = daoFactory.getDao(PatientDAO.class);
            daoFactory.startTransaction();
            patients = patientDAO.getDoctorPatients(staff);
            daoFactory.commitTransaction();
        } catch (DaoException e) {
            throw new ServiceExeption("Cannot get doctor-" + staff.getId() + " patients", e);
        }
        return patients;
    }

    public List<AdverseEvent> getDoctorAdverseEvents(Staff doctor) throws ServiceExeption {
        List<AdverseEvent> adverseEvents;
        try (DaoFactory daoFactory = new DaoFactory()) {
            AdverseEventDAO adverseEventDAO = daoFactory.getDao(AdverseEventDAO.class);
            adverseEvents = adverseEventDAO.getDoctorPatientsAdverseEvents(doctor);
        } catch (DaoException e) {
            throw new ServiceExeption("cannot get adverse events for doctor-" + doctor.getId(), e);
        }
        return adverseEvents;
    }

    public void updateAdverseEventStatus(AdverseEvent adverseEvent) throws ServiceExeption{
        try(DaoFactory daoFactory = new DaoFactory()){
         AdverseEventDAO adverseEventDAO = daoFactory.getDao(AdverseEventDAO.class);
         adverseEventDAO.update(adverseEvent);
        }catch (DaoException e){
            throw new ServiceExeption("cannot update adverse event"+adverseEvent.getId(),e);
        }
    }

    public void updateTaskProgress(Task task, Research research) throws ServiceExeption {
        try (DaoFactory daoFactory = new DaoFactory()) {
            TaskDao taskDao = daoFactory.getDao(TaskDao.class);
            daoFactory.startTransaction();
            taskDao.update(task);
            daoFactory.commitTransaction();
            CordinatorService cordinatorService = new CordinatorService();
            cordinatorService.recalculateResearchProgress(research);
        } catch (DaoException e) {
            throw new ServiceExeption("Cannot update task from dao", e);
        }
    }
}
