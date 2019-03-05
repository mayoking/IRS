/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.dao;

import com.bbt.irs.deploy.IRS;
import com.bbt.irs.entity.TRbRestrictionCodes;
import com.bbt.irs.entity.TRbRestrictionData;
import com.bbt.irs.entity.TRbRestrictionType;
import com.bbt.irs.exception.DatabaseException;
import com.bbt.irs.vo.RefactorUploadObject;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import org.apache.logging.log4j.LogManager;

/**
 *
 * @author tkola
 */
public class RestrictionDataDAO {

    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(WorkCollectionDAO.class);
    EntityManagerFactory emf;

    public List<TRbRestrictionData> getRestrictionData() throws Exception {
        List<TRbRestrictionData> list;
        Query query = IRS.getEm().createQuery("select a from TRbRestrictionData a");
        list = query.getResultList();

        return list;
    }

    public TRbRestrictionType getrestrionType() {
        List<TRbRestrictionType> list;
        Query query = IRS.getEm().createQuery("select a from TRbRestrictionType a where a.restrictionType=?1");
        query.setParameter(1, "Data");
        list = query.getResultList();

        return list.get(0);
    }

    public boolean addRestritionData(List<RefactorUploadObject> ls, String restCode, String restrictionDescription) throws DatabaseException {
        boolean result = false;
        EntityManager em = IRS.getEm();
        try {
            System.out.println("about to start ");
            em.getTransaction().begin();
            TRbRestrictionCodes tTRbRestrictionCodes = new TRbRestrictionCodes();
            tTRbRestrictionCodes.setValidityDate(new Date());
            tTRbRestrictionCodes.setCreatedDate(new Date());
            tTRbRestrictionCodes.setRestrictionType(getrestrionType());
            tTRbRestrictionCodes.setRestrictionCode(restCode.toUpperCase());
            tTRbRestrictionCodes.setRestrictionDesc(restrictionDescription.toUpperCase());
            tTRbRestrictionCodes.setModifiedBy(IRS.getInstance().username);
            em.persist(tTRbRestrictionCodes);
            em.getTransaction().commit();
            em.getTransaction().begin();
            for (RefactorUploadObject s : ls) {
                System.out.println("ssssss " + s.getItemCode());
                TRbRestrictionData tRbRestrictionData = new TRbRestrictionData();
                tRbRestrictionData.setCreatedBy(IRS.getInstance().username);
                tRbRestrictionData.setCreatedDate(new Date());
                tRbRestrictionData.setItemCode(s.getItemCode());
                tRbRestrictionData.setItemDescription(s.getItemDescription());
                tRbRestrictionData.setRestrictionCode(tTRbRestrictionCodes);
                em.persist(tRbRestrictionData);

            }
            em.getTransaction().commit();
            System.out.println("trx commited");
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            throw new DatabaseException("Unable to upload Information ");
            
        }
        result = true;
        return result;
    }
}
