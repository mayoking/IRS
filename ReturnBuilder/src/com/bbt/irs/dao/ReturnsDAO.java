/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.dao;

import com.bbt.irs.deploy.IRS;
import com.bbt.irs.entity.TRtnReturns;
import com.bbt.irs.util.Utility;
import com.bbt.irs.vo.BasicInfoVO;
import com.bbt.irs.vo.HeaderInfoVO;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import javax.persistence.EntityManager;

/**
 *
 * @author opeyemi
 */
public class ReturnsDAO {

    EntityManager em;

    public ReturnsDAO(EntityManager em) {
        this.em = em;
    }

    public boolean addReturns(LinkedHashMap linkedHashMap)  throws Exception  {
        BasicInfoVO basicInfo = (BasicInfoVO) linkedHashMap.get(1);
        LinkedHashMap<Integer, LinkedList<HeaderInfoVO>> headerInfos = (LinkedHashMap<Integer, LinkedList<HeaderInfoVO>>) linkedHashMap.get(2);
        boolean result;
            TRtnReturns trtnReturns = new TRtnReturns(basicInfo.getTemplateCode());
            trtnReturns.setDescription(basicInfo.getTemplateDesc());
            trtnReturns.setCreatedBy("System");
            trtnReturns.setCreatedDate(Utility.getCurrentTime());
            trtnReturns.setLastModified(Utility.getCurrentTime());
            trtnReturns.setModifiedBy("SYSTEM");
            //em.getTransaction().begin();
            IRS.getEm().persist(trtnReturns);
            //em.getTransaction().commit();
            result = true;
            //em.close();
        
        return result;

    }
}
