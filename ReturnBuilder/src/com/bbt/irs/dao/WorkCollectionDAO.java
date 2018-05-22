/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.dao;

import static com.bbt.irs.dao.DAOConstants.GETWORKCOLLECTION;
import com.bbt.irs.deploy.IRS;
import com.bbt.irs.entity.TRtnWorkCollection;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author opeyemi
 */
public class WorkCollectionDAO implements DAOConstants {
     EntityManagerFactory emf;

    public List<TRtnWorkCollection> getWorkCollection()throws Exception {
        List<TRtnWorkCollection> list;
        Query query = IRS.getEm().createQuery(GETWORKCOLLECTION);
        list = query.getResultList();

        return list;
    }
}
