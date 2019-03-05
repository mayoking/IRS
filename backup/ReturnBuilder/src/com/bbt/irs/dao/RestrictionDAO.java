/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.dao;

import com.bbt.irs.deploy.IRS;
import com.bbt.irs.entity.TRbRestrictionCodes;
import com.bbt.irs.exception.DatabaseException;
import java.util.List;
import javax.persistence.Query;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

/**
 *
 * @author tkola
 */
public class RestrictionDAO {

    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(WorkCollectionDAO.class);

    public List<TRbRestrictionCodes> getRestrictonCodes() throws DatabaseException {
        List<TRbRestrictionCodes> list;
        try {
            Query query = IRS.getEm().createQuery("select a from TRbRestrictionCodes a  ");
            list = query.getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.ERROR, e);
            throw new DatabaseException("Unable to get restriction Codes");
        }

        return list;
    }

    public TRbRestrictionCodes getRestrictonCodes(String restrictionCode) throws DatabaseException {
        List<TRbRestrictionCodes> list;
        TRbRestrictionCodes codes;
        try {
            Query query = IRS.getEm().createQuery("select a from TRbRestrictionCodes a where a.restrictionCode=?1 ");
            query.setParameter(1, restrictionCode);
            list = query.getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.ERROR, e);
            throw new DatabaseException("Unable to get restriction Codes");
        }
        if (list.isEmpty()) {
            codes = null;
        } else {
            codes = list.get(0);
        }
            return codes;
        }
    }
