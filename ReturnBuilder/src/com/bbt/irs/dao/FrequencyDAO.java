/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.dao;

import com.bbt.irs.deploy.IRS;
import com.bbt.irs.entity.Datasize;
import com.bbt.irs.entity.TLkupFrequency;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author tkola
 */
public class FrequencyDAO implements DAOConstants {
     public List<TLkupFrequency> getFrequency()throws Exception {
        List<TLkupFrequency> list;
        Query query = IRS.getEm().createQuery(FREQUENCY);
        list = query.getResultList();

        return list;
    }
}
