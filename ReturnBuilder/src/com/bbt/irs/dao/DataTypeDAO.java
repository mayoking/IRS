/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.dao;

import com.bbt.irs.deploy.IRS;
import com.bbt.irs.entity.Datatype;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author opeyemi
 */
public class DataTypeDAO implements DAOConstants {
    EntityManagerFactory emf;

    public List<Datatype> getDataType() throws Exception  {
        List<Datatype> list;
        Query query = IRS.getEm().createQuery(GETDATATYPE);
        list = query.getResultList();

        return list;
    }
}
