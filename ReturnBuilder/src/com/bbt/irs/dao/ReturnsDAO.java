/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.dao;

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
        boolean result = false;
            TRtnReturns trtnReturns = new TRtnReturns(basicInfo.getTemplateCode(), Utility.getCurrentTime(), basicInfo.getWorkCollections().getFrequency());
            trtnReturns.setDescription(basicInfo.getTemplateDesc());
            //em.getTransaction().begin();
            em.persist(trtnReturns);
            //em.getTransaction().commit();
            result = true;
            //em.close();
        
        return result;

    }
}
