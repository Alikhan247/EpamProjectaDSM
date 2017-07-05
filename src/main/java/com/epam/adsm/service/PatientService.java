package com.epam.adsm.service;

import com.epam.adsm.dao.DaoException;
import com.epam.adsm.dao.DaoFactory;
import com.epam.adsm.dao.implementation.*;
import com.epam.adsm.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class PatientService {

    private static final Logger LOG = LoggerFactory.getLogger(CordinatorService.class);

    public Patient findPatientByCode(String patientCode) throws ServiceExeption {
        Patient patient;
        try (DaoFactory daoFactory = new DaoFactory()) {
            PatientDao patientDao = daoFactory.getDao(PatientDao.class);
            daoFactory.startTransaction();
            patient = patientDao.findByPatientCode(patientCode);
            daoFactory.commitTransaction();
        } catch (DaoException e) {
            LOG.error("Cannot find patient by patient code" + patientCode, e);
            throw new ServiceExeption("Cannot find patient by patient code from dao", e);
        }
        return patient;
    }

    public void updatePatient(Patient patient) throws ServiceExeption {
        try (DaoFactory daoFactory = new DaoFactory()) {
            PatientDao patientDao = daoFactory.getDao(PatientDao.class);
            daoFactory.startTransaction();
            patientDao.update(patient);
            daoFactory.commitTransaction();
        } catch (DaoException e) {
            LOG.error("Cannot update patient from dao", e);
            throw new ServiceExeption("Cannot update patient from dao", e);
        }
    }

    public List<Event> getAllPatientEvents(String patientCode) throws ServiceExeption {
        List<Event> events;
        Research research;
        try (DaoFactory daoFactory = new DaoFactory()) {
            EventDao eventDao = daoFactory.getDao(EventDao.class);
            ResearchDao researchDao = daoFactory.getDao(ResearchDao.class);
            daoFactory.startTransaction();
            research = researchDao.findByPatientCode(patientCode);
            events = eventDao.getAllEventsFromResearch(research);
            daoFactory.commitTransaction();
        } catch (DaoException e) {
            LOG.error("Cannot get all events for patient" + patientCode, e);
            throw new ServiceExeption("Cannot get all events for patient" + patientCode, e);
        }
        return events;
    }

    public Event getEventById(int id) throws ServiceExeption {
        Event event = null;
        try (DaoFactory daoFactory = new DaoFactory()) {
            EventDao eventDao = daoFactory.getDao(EventDao.class);
            event = eventDao.findById(id);
        } catch (DaoException e) {
            LOG.error("Cannot find event by id " + id + " from dao", e);
            throw new ServiceExeption("Cannot find event from dao", e);
        }
        return event;
    }

    public List<Patient> getAllPatients() throws ServiceExeption {
        List<Patient> patientList;
        Staff doctor;
        try (DaoFactory daoFactory = new DaoFactory()) {
            PatientDao patientDao = daoFactory.getDao(PatientDao.class);
            StaffDao staffDao = daoFactory.getDao(StaffDao.class);
            daoFactory.startTransaction();
            patientList = patientDao.getAllConfirmedPatients();
            for (Patient patient : patientList) {
                doctor = staffDao.findById(patient.getDoctor().getId());
                patient.setDoctor(doctor);
            }
            daoFactory.commitTransaction();
        } catch (DaoException e) {
            LOG.error("Cannot get all patients from dao", e);
            throw new ServiceExeption("Cannot get patient list from dao", e);
        }
        return patientList;
    }

    public List<Diagnosis> getAllDiagnosis() throws ServiceExeption {
        List<Diagnosis> diagnosisList;

        try (DaoFactory daoFactory = new DaoFactory()) {
            DiagnosisDao diagnosisDao = daoFactory.getDao(DiagnosisDao.class);
            daoFactory.startTransaction();
            diagnosisList = diagnosisDao.getAllDiagnosis();
        } catch (DaoException e) {
            LOG.error("Cannot get all diagnosis from dao", e);
            throw new ServiceExeption("Cannot get diagnosis list from dao", e);
        }
        return diagnosisList;
    }

    public boolean isPatientPhoneNumberAvailable(String phoneNumber) throws ServiceExeption {
        Patient patient;
        boolean availablePhone = true;
        try (DaoFactory daoFactory = new DaoFactory()) {
            PatientDao patientDao = daoFactory.getDao(PatientDao.class);
            daoFactory.startTransaction();
            patient = patientDao.findByPhone(phoneNumber);
            daoFactory.commitTransaction();
        } catch (DaoException e) {
            LOG.error("Cannot check is patient phone available or not from dao", e);
            throw new ServiceExeption("Cannot check patient phone number from dao", e);
        }
        if (patient != null) {
            availablePhone = false;
        }
        return availablePhone;
    }

    public List<Research> getAllResearch() throws ServiceExeption {
        List<Research> researchList;
        try (DaoFactory daoFactory = new DaoFactory()) {
            ResearchDao researchDao = daoFactory.getDao(ResearchDao.class);
            daoFactory.startTransaction();
            researchList = researchDao.getAllResearch();
        } catch (DaoException e) {
            LOG.error("Cannot get all research from dao", e);
            throw new ServiceExeption("Cannot get research list from dao", e);
        }
        return researchList;
    }
}
