/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.dao;

import com.bbt.irs.deploy.IRS;
import com.bbt.irs.entity.ExtractionTableInfo;
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
public class ExtractionInfoDAO {

    EntityManager em;
    public ExtractionInfoDAO(EntityManager em)  {
        this.em = em;
    }

    public boolean addExtractionInfo(LinkedHashMap linkedHashMap) throws Exception {
        BasicInfoVO basicInfo = (BasicInfoVO) linkedHashMap.get(1);
        LinkedHashMap<Integer, LinkedList<HeaderInfoVO>> headerInfos = (LinkedHashMap<Integer, LinkedList<HeaderInfoVO>>) linkedHashMap.get(2);

        boolean result ;
            ExtractionTableInfo extractionTableInfo = new ExtractionTableInfo();
            extractionTableInfo.setTemplateCode(basicInfo.getTemplateCode());
            extractionTableInfo.setTemplateName(basicInfo.getTemplateCode());
            extractionTableInfo.setTemplateType(basicInfo.getTemplateType().getTemplateId());
            extractionTableInfo.setCreatedBy("System");
            extractionTableInfo.setCreatedDate(Utility.getCurrentTime());
            extractionTableInfo.setLastModified(Utility.getCurrentTime());
            extractionTableInfo.setModifiedBy("SYSTEM");
            IRS.getEm().persist(extractionTableInfo);

            result = true;

       
        return result;

    }
}
