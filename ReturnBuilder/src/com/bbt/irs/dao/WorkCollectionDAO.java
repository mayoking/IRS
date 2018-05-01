/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.dao;

import static com.bbt.irs.dao.DAOConstants.GETWORKCOLLECTION;
import com.bbt.irs.entity.TRtnWorkCollectionSchedule;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author opeyemi
 */
public class WorkCollectionDAO implements DAOConstants {
     EntityManagerFactory emf;

    public List<TRtnWorkCollectionSchedule> getWorkCollection() {
        List<TRtnWorkCollectionSchedule> list;
        this.emf = Persistence.createEntityManagerFactory("ReturnBuilderPU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery(GETWORKCOLLECTION);
        list = query.getResultList();

        return list;
    }
}
