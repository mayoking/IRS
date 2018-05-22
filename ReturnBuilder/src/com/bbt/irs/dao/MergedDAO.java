/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.dao;

import com.bbt.irs.deploy.IRS;
import com.bbt.irs.entity.Datasize;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author tkola
 */
public class MergedDAO implements DAOConstants {
    
     public List<Datasize> getDataSize()throws Exception {
        List<Datasize> list;
        Query query = IRS.getEm().createQuery(GETMERGEDTYPE);
        list = query.getResultList();

        return list;
    }
}
