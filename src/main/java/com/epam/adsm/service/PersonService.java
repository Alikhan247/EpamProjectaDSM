package com.epam.adsm.service;

import com.epam.adsm.dao.DaoException;
import com.epam.adsm.dao.DaoFactory;
import com.epam.adsm.dao.implementation.PatientDAO;
import com.epam.adsm.dao.implementation.StaffDAO;
import com.epam.adsm.model.Patient;
import com.epam.adsm.model.Staff;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by akmatleu on 19.05.17.
 */
public class PersonService {

    private static final Logger LOG = LoggerFactory.getLogger(PersonService.class);


    public Staff findStaffByPhoneAndPassword(String phoneNumber,String password) throws ServiceExeption {
        Staff staff = null;
        try(DaoFactory daoFactory = new DaoFactory()) {
            StaffDAO staffDAO = daoFactory.getDao(StaffDAO.class);
            daoFactory.startTransaction();
            staff = staffDAO.findByPhoneAndPassword(phoneNumber,password);
            daoFactory.commitTransaction();
        }catch (DaoException e) {
            LOG.info("Cannot find staff  by password and phone number");
            throw new ServiceExeption("Cannot find by login and password",e);
        }
        return staff;
    }

    public Patient findPatientByPhoneAndPassword(String phoneNumber,String password) throws ServiceExeption {
        Patient patient = null;
        try(DaoFactory daoFactory = new DaoFactory()) {
            PatientDAO patientDAO = daoFactory.getDao(PatientDAO.class);
            daoFactory.startTransaction();
            patient = patientDAO.findByPhoneAndPassword(phoneNumber,password);
            daoFactory.commitTransaction();
        }catch (DaoException e){
            LOG.info("Cannot find Patient by password and phone number");
            throw new ServiceExeption("Cannot find by login and password patient",e);
        }
        return patient;
    }



}
