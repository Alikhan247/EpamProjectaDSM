package com.epam.adsm.service;

import com.epam.adsm.dao.DaoException;
import com.epam.adsm.dao.DaoFactory;
import com.epam.adsm.dao.implementation.*;
import com.epam.adsm.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by akmatleu on 21.05.17.
 */
public class PatientService {

    private static final Logger LOG = LoggerFactory.getLogger(CordinatorService.class);

    public Patient findPatientByCode(String patientCode) throws  ServiceExeption {
        Patient patient;
        try(DaoFactory daoFactory = new DaoFactory()) {
            PatientDAO patientDAO = daoFactory.getDao(PatientDAO.class);
            daoFactory.startTransaction();
            patient = patientDAO.findByPatientCode(patientCode);
            daoFactory.commitTransaction();
        }catch (DaoException e){
            throw new ServiceExeption("Cannot find staff by id from dao",e);
        }
        return patient;
    }


    public void updatePatient(Patient patient) throws ServiceExeption {
        try (DaoFactory daoFactory = new DaoFactory()){
            PatientDAO patientDAO = daoFactory.getDao(PatientDAO.class);
            daoFactory.startTransaction();
            patientDAO.update(patient);
            daoFactory.commitTransaction();
        }catch (DaoException e){
            throw new ServiceExeption("Cannot update patient",e);
        }
    }

    public List<Event> getAllPatientEvents(String patientCode) throws ServiceExeption{
        List<Event> events;
        Research research;
        try(DaoFactory daoFactory = new DaoFactory()){
            EventDao eventDao = daoFactory.getDao(EventDao.class);
            ResearchDao researchDao = daoFactory.getDao(ResearchDao.class);
            daoFactory.startTransaction();
            research = researchDao.findByPatientCode(patientCode);
            events = eventDao.getAllEventsFromResearch(research);
            daoFactory.commitTransaction();
        }catch (DaoException e){
            throw new ServiceExeption("Cannot get all events for patient"+patientCode,e);
        }
        return events;
    }

    public Event getEventById(int id) throws ServiceExeption{
        Event event = null;
        try(DaoFactory daoFactory = new DaoFactory()){
            EventDao eventDao = daoFactory.getDao(EventDao.class);
            event = eventDao.findById(id);
        }catch (DaoException e){
            throw new ServiceExeption("Cannot find event from dao",e);
        }
        return event;
    }

    public List<Patient> getAllPatients() throws ServiceExeption {
        List<Patient> patientList;
        Staff doctor;
        try(DaoFactory daoFactory = new DaoFactory()){
            PatientDAO patientDAO = daoFactory.getDao(PatientDAO.class);
            StaffDAO staffDAO = daoFactory.getDao(StaffDAO.class);
            daoFactory.startTransaction();
            patientList = patientDAO.getAllConfirmedPatients();
            for(Patient patient : patientList){
                doctor = staffDAO.findById(patient.getDoctor().getId());
                patient.setDoctor(doctor);
            }
            daoFactory.commitTransaction();
        }catch (DaoException e){
            throw new ServiceExeption("Cannot get patient list from dao",e);
        }
        return patientList;
    }

    public List<Diagnosis> getAllDiagnosis() throws ServiceExeption {
        List<Diagnosis> diagnosisList;

        try(DaoFactory daoFactory = new DaoFactory()){
            DiagnosisDAO diagnosisDAO = daoFactory.getDao(DiagnosisDAO.class);
            daoFactory.startTransaction();
            diagnosisList = diagnosisDAO.getAllDiagnosis();
        }catch (DaoException e){
            throw new ServiceExeption("Cannot get diagnosis list from dao",e);
        }
        return diagnosisList;
    }

    public List<Research> getAllResearch() throws ServiceExeption {
        List<Research> researchList;
        try(DaoFactory daoFactory = new DaoFactory()){
            ResearchDao researchDao = daoFactory.getDao(ResearchDao.class);
            daoFactory.startTransaction();
            researchList = researchDao.getAllResearch();
        }catch (DaoException e){
            throw new ServiceExeption("Cannot get research list from dao",e);
        }
        return researchList;
    }

}
