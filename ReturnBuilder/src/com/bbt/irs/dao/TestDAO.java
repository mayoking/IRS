/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.dao;

import com.bbt.irs.deploy.IRS;
import com.bbt.irs.entity.ArtifactTable;
import com.bbt.irs.entity.ExtractionTableInfo;
import com.bbt.irs.entity.MetadataTable;
import com.bbt.irs.entity.TRtnReturns;
import com.bbt.irs.util.Utility;
import com.bbt.irs.vo.BasicInfoVO;
import com.bbt.irs.vo.HeaderInfoVO;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

/**
 *
 * @author tkola
 */
public class TestDAO {
    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(TestDAO.class);
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ReturnBuilderPU");
    EntityManager em = emf.createEntityManager();
    
     public boolean addArtifacts(LinkedHashMap linkedHashMap) throws Exception {
        BasicInfoVO basicInfo = (BasicInfoVO) linkedHashMap.get(1);
        LinkedHashMap<Integer, LinkedList<HeaderInfoVO>> headerInfos = (LinkedHashMap<Integer, LinkedList<HeaderInfoVO>>) linkedHashMap.get(2);
        boolean result;
       
     
            ArtifactTable artifact = new ArtifactTable();
            artifact.setReturnCode(basicInfo.getTemplateCode());
            artifact.setSampleXml(Utility.convertDocument2Byte(IRS.getDocument()));
            artifact.setXlsPath("");
            em.persist(artifact);
            result = true;
            //em.close();
        
        return result;        
        
    }
     
     public boolean addMetadata(LinkedHashMap linkedHashMap) throws Exception {
        BasicInfoVO basicInfo = (BasicInfoVO) linkedHashMap.get(1);
        LinkedHashMap<Integer, LinkedList<HeaderInfoVO>> headerInfos = (LinkedHashMap<Integer, LinkedList<HeaderInfoVO>>) linkedHashMap.get(2);
        boolean result;

        for (int i = 0; i < headerInfos.size(); i++) {
            LinkedList<HeaderInfoVO> ls = headerInfos.get(i + 1);
            for (int j = 0; j < ls.size(); j++) {
                HeaderInfoVO headerInfoVO = ls.get(j);
                System.out.println("headerInfoVO.getCellName() " + headerInfoVO.getCellName());
                MetadataTable metadata = new MetadataTable();
                //metadata.setPathXls("");
                metadata.setReturnCode(basicInfo.getTemplateCode());
                metadata.setRiTypeCode(basicInfo.getRitype().getRiTypeCode());
                //metadata.setSampleXml(Utility.convertDocument2Byte(IRS.getDocument()));
                metadata.setXmlName(headerInfoVO.getCellName());
                metadata.setHeaderDesc(headerInfoVO.getCellName());
                metadata.setHeaderPosition(headerInfoVO.getCellNO());
                metadata.setTableName(IRS.getTableNames().get(i));
                metadata.setDataType(headerInfoVO.getDataType().getId());
                metadata.setDataType(headerInfoVO.getDataSize().getId());

                em.persist(metadata);
            }
        }
        //em.getTransaction().commit();
        result = true;

        return result;

    }
     
     public boolean addExtractionInfo(LinkedHashMap linkedHashMap) throws Exception {
        BasicInfoVO basicInfo = (BasicInfoVO) linkedHashMap.get(1);
        LinkedHashMap<Integer, LinkedList<HeaderInfoVO>> headerInfos = (LinkedHashMap<Integer, LinkedList<HeaderInfoVO>>) linkedHashMap.get(2);

        boolean result ;
            ExtractionTableInfo extractionTableInfo = new ExtractionTableInfo();
            extractionTableInfo.setTemplateCode(basicInfo.getTemplateCode());
            extractionTableInfo.setTemplateName(basicInfo.getTemplateCode());
            extractionTableInfo.setTemplateType(basicInfo.getTemplateType().getId());
            em.persist(extractionTableInfo);

            result = true;

       
        return result;

    }
     
      public boolean addReturns(LinkedHashMap linkedHashMap)  throws Exception  {
        BasicInfoVO basicInfo = (BasicInfoVO) linkedHashMap.get(1);
        LinkedHashMap<Integer, LinkedList<HeaderInfoVO>> headerInfos = (LinkedHashMap<Integer, LinkedList<HeaderInfoVO>>) linkedHashMap.get(2);
        boolean result = false;
            TRtnReturns trtnReturns = new TRtnReturns(basicInfo.getTemplateCode(), Utility.getCurrentTime(), basicInfo.getWorkCollections().getFrequency());
            trtnReturns.setDescription(basicInfo.getTemplateDesc());
            trtnReturns.setEndValidityDate(Utility.getCurrentTime());
            trtnReturns.setLastModified(Utility.getCurrentTime());
            trtnReturns.setModifiedBy("SYSTEM");
            //em.getTransaction().begin();
            em.persist(trtnReturns);
            //em.getTransaction().commit();
            result = true;
            //em.close();
        
        return result;

    }
      
       public boolean populateRequiredtables(LinkedHashMap linkedHashMap) {
        boolean result;
        ArtifactDAO artifactDAO = new ArtifactDAO(em);
        ReturnsDAO returnsDAO = new ReturnsDAO(em);
        MetadataTableDAO metadataTableDAO = new MetadataTableDAO(em);
        ExtractionInfoDAO extractionInfoDAO = new ExtractionInfoDAO(em);
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            addExtractionInfo(linkedHashMap);
            System.out.println("extractionInfo populated");
            addArtifacts(linkedHashMap);
            System.out.println("artifactDAO populated");
            addMetadata(linkedHashMap);
            System.out.println("metadataTableDAO populated");
            addReturns(linkedHashMap);
            System.out.println("returnsDAO populated");
            
            em.getTransaction().commit();
//            em.flush();

            System.out.println("transaction commited");;
            em.close();
            System.out.println("transaction closed");
            result = true;
        } catch (Exception ex) {
            Utility.showDialog("Unable to save the information to data store", true);
            System.out.println("there is exception");
            ex.printStackTrace();
            result = false;

            if (em != null) {
                em.getTransaction().rollback();
                em.close();
            }
            LOGGER.log(Level.FATAL, "Required Tables can not be populated", ex);

        }
        return result;
    }
}
