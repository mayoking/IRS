/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.dao;

import com.bbt.irs.deploy.IRS;
import com.bbt.irs.entity.ArtifactTable;
import com.bbt.irs.util.Utility;
import com.bbt.irs.vo.BasicInfoVO;
import com.bbt.irs.vo.HeaderInfoVO;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;


/**
 *
 * @author opeyemi
 */
public class ArtifactDAO  {
    EntityManagerFactory emf;
    EntityManager em;
    
    public ArtifactDAO(EntityManager em ){
        this.em  = em;
    }
   
    public boolean addArtifacts(LinkedHashMap linkedHashMap) throws Exception {
        BasicInfoVO basicInfo = (BasicInfoVO) linkedHashMap.get(1);
        LinkedHashMap<Integer, LinkedList<HeaderInfoVO>> headerInfos = (LinkedHashMap<Integer, LinkedList<HeaderInfoVO>>) linkedHashMap.get(2);
        boolean result;
       
     
            ArtifactTable artifact = new ArtifactTable();
            artifact.setReturnCode(basicInfo.getTemplateCode());
            artifact.setSampleXml(Utility.convertDocument2Byte(IRS.getDocument()));
            artifact.setLastModified(Utility.getCurrentTime());
            artifact.setCreatedDate(Utility.getCurrentTime());
            artifact.setCreatedBy("SYSTEM");
            artifact.setLastModified(Utility.getCurrentTime());
            artifact.setModifiedBy("SYSTEM");
            artifact.setXlsPath(IRS.getExcelPath());
            em.persist(artifact);
            result = true;
            //em.close();
        
        return result;        
        
    }
}
