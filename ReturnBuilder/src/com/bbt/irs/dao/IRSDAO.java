/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.dao;

import com.bbt.irs.util.Utility;
import java.util.LinkedHashMap;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

/**
 *
 * @author opeyemi
 */
public class IRSDAO {

    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(IRSDAO.class);

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ReturnBuilderPU");
    EntityManager em = emf.createEntityManager();

    public boolean populateRequiredtables(LinkedHashMap linkedHashMap) {
        boolean result;
        ArtifactDAO artifactDAO = new ArtifactDAO(em);
        ReturnsDAO returnsDAO = new ReturnsDAO(em);
        MetadataTableDAO metadataTableDAO = new MetadataTableDAO(em);
        ExtractionInfoDAO extractionInfoDAO = new ExtractionInfoDAO(em);
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            extractionInfoDAO.addExtractionInfo(linkedHashMap);
            System.out.println("extractionInfo populated");
            artifactDAO.addArtifacts(linkedHashMap);
            System.out.println("artifactDAO populated");
            metadataTableDAO.addMetadata(linkedHashMap);
            System.out.println("metadataTableDAO populated");
            returnsDAO.addReturns(linkedHashMap);
            System.out.println("returnsDAO populated");
            
            em.getTransaction().commit();
//            em.flush();

            System.out.println("transaction commited");;
            em.close();
            System.out.println("transaction closed");
            result = true;
        } catch (Exception ex) {
            Utility.showDialog("Unable to save the information to data store", true);
            System.out.println("there is exception");
            ex.printStackTrace();
            result = false;

            if (em != null) {
                em.getTransaction().rollback();
                em.close();
            }
            LOGGER.log(Level.FATAL, "Required Tables can not be populated", ex);

        }
        return result;
    }

}
