package com.epam.adsm.service;

import com.epam.adsm.dao.DaoException;
import com.epam.adsm.dao.DaoFactory;
import com.epam.adsm.dao.implementation.PatientDao;
import com.epam.adsm.dao.implementation.StaffDao;
import com.epam.adsm.model.Patient;
import com.epam.adsm.model.Staff;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class PersonService {

    private static final Logger LOG = LoggerFactory.getLogger(PersonService.class);

    public Staff findStaffByPhoneAndPassword(String phoneNumber,String password) throws ServiceExeption {
        Staff staff;
        try(DaoFactory daoFactory = new DaoFactory()) {
            StaffDao staffDao = daoFactory.getDao(StaffDao.class);
            daoFactory.startTransaction();
            staff = staffDao.findByPhoneAndPassword(phoneNumber,password);
            daoFactory.commitTransaction();
        }catch (DaoException e) {
            LOG.error("Cannot find staff  by password and phone number",e);
            throw new ServiceExeption("Cannot find by login and password",e);
        }
        return staff;
    }

    public Patient findPatientByPhoneAndPassword(String phoneNumber,String password) throws ServiceExeption {
        Patient patient;
        try(DaoFactory daoFactory = new DaoFactory()) {
            PatientDao patientDao = daoFactory.getDao(PatientDao.class);
            daoFactory.startTransaction();
            patient = patientDao.findByPhoneAndPassword(phoneNumber,password);
            daoFactory.commitTransaction();
        }catch (DaoException e){
            LOG.error("Cannot find patient by password and phone number",e);
            throw new ServiceExeption("Cannot find by login and password patient",e);
        }
        return patient;
    }
}
