/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.dao;

import com.bbt.irs.deploy.IRS;
import com.bbt.irs.entity.TCoreRiType;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

/**
 *
 * @author opeyemi
 */
public class RITypeDAO implements DAOConstants {
    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(RITypeDAO.class);
    EntityManagerFactory emf;

    public List<TCoreRiType> getRIype() throws Exception {
        List<TCoreRiType> list=null;
        try{
        
        this.emf = Persistence.createEntityManagerFactory("ReturnBuilderPU");
//        EntityManager em = emf.createEntityManager();
        Query query = IRS.getEm().createQuery(GETRITYPE);
        list = query.getResultList();
        }catch(Exception ex){
            LOGGER.log(Level.FATAL,"RI Type can not be loaded",ex);
        }

        return list;
    }
}
