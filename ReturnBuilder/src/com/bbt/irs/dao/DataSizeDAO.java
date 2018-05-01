/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.dao;

import com.bbt.irs.entity.Datasize;
import com.bbt.irs.vo.DataSize;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author opeyemi
 */
public class DataSizeDAO implements DAOConstants {
    EntityManagerFactory emf;

    public List<Datasize> getDataSize() {
        List<Datasize> list;
        this.emf = Persistence.createEntityManagerFactory("ReturnBuilderPU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery(GETDATASIZE);
        list = query.getResultList();

        return list;
    }
}
