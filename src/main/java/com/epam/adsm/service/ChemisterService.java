package com.epam.adsm.service;

import com.epam.adsm.dao.DaoException;
import com.epam.adsm.dao.DaoFactory;
import com.epam.adsm.dao.implementation.AdverseEventDAO;
import com.epam.adsm.dao.implementation.DrugAdministraionDao;
import com.epam.adsm.dao.implementation.DrugDao;
import com.epam.adsm.dao.implementation.RecieptDao;
import com.epam.adsm.model.AdverseEvent;
import com.epam.adsm.model.Drug;
import com.epam.adsm.model.DrugAdministration;
import com.epam.adsm.model.Reciept;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by akmatleu on 13.05.17.
 */
public class ChemisterService {
    private static final Logger LOG = LoggerFactory.getLogger(ChemisterService.class);

    public List<Reciept> getAllRecieptsByPatientCode(String patientCode) throws ServiceExeption {
        List<Reciept> reciepts = new ArrayList<>();
        try(DaoFactory daoFactory = new DaoFactory()) {
            RecieptDao recieptDao = daoFactory.getDao(RecieptDao.class);
            daoFactory.startTransaction();
            reciepts = recieptDao.getReciepts(patientCode);
            LOG.info("put reciepts in list");
            daoFactory.commitTransaction();
        }catch (DaoException e) {
            throw new ServiceExeption("Cannot get  all reciepts for patient from dao ",e);
        }
        return reciepts;
    }


    public List<Drug> getAllDrugs() throws ServiceExeption {
        List<Drug> drugs;
        try(DaoFactory daoFactory = new DaoFactory()){
            DrugDao drugDao = daoFactory.getDao(DrugDao.class);
            daoFactory.startTransaction();
            drugs = drugDao.getAllDrugs();
            daoFactory.commitTransaction();
        }catch (DaoException e){
            throw new ServiceExeption("Cannot get drugs list from dao",e);
        }
        return drugs;

    }


    public void createDrugAdministraion(DrugAdministration drugAdministration) throws ServiceExeption {
            try(DaoFactory daoFactory = new DaoFactory()){
                DrugAdministraionDao drugAdministraionDao = daoFactory.getDao(DrugAdministraionDao.class);
                daoFactory.startTransaction();
                drugAdministraionDao.create(drugAdministration);
                daoFactory.commitTransaction();
            }catch (DaoException e){
                throw new ServiceExeption("Cannot create dao for drug administration",e);
            }
    }


    public void createAdverseEvent(AdverseEvent adverseEvent) throws ServiceExeption {
        try(DaoFactory daoFactory = new DaoFactory()){
            AdverseEventDAO adverseEventDAO = daoFactory.getDao(AdverseEventDAO.class);
            daoFactory.startTransaction();
            adverseEventDAO.create(adverseEvent);
            daoFactory.commitTransaction();
        }catch (DaoException e){
            throw new ServiceExeption("Cannot create dao for adverse event",e);
        }


    }

    public void createReceipt(Reciept reciept) throws ServiceExeption{
        try(DaoFactory daoFactory = new DaoFactory()){
            RecieptDao recieptDao = daoFactory.getDao(RecieptDao.class);
            daoFactory.startTransaction();
            recieptDao.create(reciept);
            daoFactory.commitTransaction();
        }catch (DaoException e){
            throw new ServiceExeption("cannot create dao for receipt",e);
        }
    }

}
