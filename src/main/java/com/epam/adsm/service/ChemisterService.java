package com.epam.adsm.service;

import com.epam.adsm.dao.DaoException;
import com.epam.adsm.dao.DaoFactory;
import com.epam.adsm.dao.implementation.AdverseEventDao;
import com.epam.adsm.dao.implementation.DrugAdministraionDao;
import com.epam.adsm.dao.implementation.DrugDao;
import com.epam.adsm.dao.implementation.RecieptDao;
import com.epam.adsm.model.AdverseEvent;
import com.epam.adsm.model.Drug;
import com.epam.adsm.model.DrugAdministration;
import com.epam.adsm.model.Receipt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

public class ChemisterService {
    private static final Logger LOG = LoggerFactory.getLogger(ChemisterService.class);

    public List<Receipt> getAllRecieptsByPatientCode(String patientCode) throws ServiceExeption {
        List<Receipt> receipts;
        try (DaoFactory daoFactory = new DaoFactory()) {
            RecieptDao recieptDao = daoFactory.getDao(RecieptDao.class);
            daoFactory.startTransaction();
            receipts = recieptDao.getReciepts(patientCode);
            LOG.info("put receipts in list");
            daoFactory.commitTransaction();
        } catch (DaoException e) {
            LOG.error("Cannot get all receipts for patient from ReceiptDao", e);
            throw new ServiceExeption("Cannot get  all receipts for patient from dao ", e);
        }
        return receipts;
    }

    public List<Drug> getAllDrugs() throws ServiceExeption {
        List<Drug> drugs;
        try (DaoFactory daoFactory = new DaoFactory()) {
            DrugDao drugDao = daoFactory.getDao(DrugDao.class);
            daoFactory.startTransaction();
            drugs = drugDao.getAllDrugs();
            daoFactory.commitTransaction();
        } catch (DaoException e) {
            LOG.error("Cannot get drugs list from drugDao", e);
            throw new ServiceExeption("Cannot get drugs list from dao", e);
        }
        return drugs;
    }

    public void createDrugAdministraion(DrugAdministration drugAdministration) throws ServiceExeption {
        try (DaoFactory daoFactory = new DaoFactory()) {
            DrugAdministraionDao drugAdministraionDao = daoFactory.getDao(DrugAdministraionDao.class);
            daoFactory.startTransaction();
            drugAdministraionDao.createDrugAdministration(drugAdministration);
            daoFactory.commitTransaction();
        } catch (DaoException e) {
            LOG.error("Cannot create drug administration from drugAdministrationDao", e);
            throw new ServiceExeption("Cannot create drugAdministration from drugAdministrationDao", e);
        }
    }

    public void createAdverseEvent(AdverseEvent adverseEvent) throws ServiceExeption {
        try (DaoFactory daoFactory = new DaoFactory()) {
            AdverseEventDao adverseEventDao = daoFactory.getDao(AdverseEventDao.class);
            daoFactory.startTransaction();
            adverseEventDao.create(adverseEvent);
            daoFactory.commitTransaction();
        } catch (DaoException e) {
            LOG.error("cannot create adverse event from adverse event dao", e);
            throw new ServiceExeption("Cannot create adverse event from dao", e);
        }
    }

    public void createReceipt(Receipt receipt) throws ServiceExeption {
        try (DaoFactory daoFactory = new DaoFactory()) {
            RecieptDao recieptDao = daoFactory.getDao(RecieptDao.class);
            daoFactory.startTransaction();
            recieptDao.createReceipt(receipt);
            daoFactory.commitTransaction();
        } catch (DaoException e) {
            LOG.error("Cannot create receipt from receiptDao", e);
            throw new ServiceExeption("cannot create receipt from receiptDao", e);
        }
    }
}
