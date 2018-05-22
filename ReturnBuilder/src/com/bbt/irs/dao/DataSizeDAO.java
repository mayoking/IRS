/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.dao;

import com.bbt.irs.deploy.IRS;
import com.bbt.irs.entity.Datasize;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author opeyemi
 */
public class DataSizeDAO implements DAOConstants {
    EntityManagerFactory emf;

    public List<Datasize> getDataSize()throws Exception {
        List<Datasize> list;
////        this.emf = Persistence.createEntityManagerFactory("ReturnBuilderPU");
//        EntityManager em = emf.createEntityManager();
        Query query = IRS.getEm().createQuery(GETDATASIZE);
        list = query.getResultList();

        return list;
    }
}
