/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.dao;

import com.bbt.irs.deploy.IRS;
import com.bbt.irs.deploy.IRSDialog;
import com.bbt.irs.entity.ArtifactTable;
import com.bbt.irs.entity.ExtractionTableInfo;
import com.bbt.irs.entity.MetadataTable;
import com.bbt.irs.entity.TRtnReturns;
import com.bbt.irs.entity.TRtnReturnsWorkCollectionMapping;
import com.bbt.irs.ui.ExcelView;
import com.bbt.irs.util.Utility;
import com.bbt.irs.vo.BasicInfoVO;
import com.bbt.irs.vo.HeaderInfoVO;
import java.io.File;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import javafx.util.Pair;
import javax.persistence.EntityManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

/**
 *
 * @author tkola
 */
public class TestDAO {

    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(TestDAO.class);
    // EntityManagerFactory emf = IRS.getEntityManagerFactory(); //Persistence.createEntityManagerFactory("ReturnBuilderPU");
    EntityManager em = IRS.getEm();

    public boolean addArtifacts(LinkedHashMap linkedHashMap) throws Exception {
        BasicInfoVO basicInfo = (BasicInfoVO) linkedHashMap.get(1);
        LinkedHashMap<Integer, LinkedList<HeaderInfoVO>> headerInfos = (LinkedHashMap<Integer, LinkedList<HeaderInfoVO>>) linkedHashMap.get(2);
        boolean result;
        System.out.println("em  " + em.getProperties().get("javax.persistence.jdbc.url"));

        ArtifactTable artifact = new ArtifactTable();
        artifact.setReturnCode(basicInfo.getTemplateCode());
        artifact.setSampleXml(Utility.convertDocument2Byte(IRS.getDocument()));
        artifact.setXlsPath(new File(IRS.getExcelPath()).getName());
        artifact.setCreatedDate(Utility.getCurrentTime());
        artifact.setCreatedBy("SYSTEM");
        artifact.setLastModified(Utility.getCurrentTime());
        artifact.setModifiedBy("SYSTEM");
        em.persist(artifact);
        result = true;
        //em.close();

        return result;

    }
    
    public boolean addReturnWorkCollectionMapping(LinkedHashMap linkedHashMap){
        boolean result=false;
         BasicInfoVO basicInfo = (BasicInfoVO) linkedHashMap.get(1);
        LinkedHashMap<Integer, LinkedList<HeaderInfoVO>> headerInfos = (LinkedHashMap<Integer, LinkedList<HeaderInfoVO>>) linkedHashMap.get(2);

        TRtnReturnsWorkCollectionMapping wkcolReturn = new TRtnReturnsWorkCollectionMapping();
        wkcolReturn.setReturnCode(basicInfo.getTemplateCode());
        wkcolReturn.setRiTypeId(basicInfo.getRitype().getRiTypeId());
        wkcolReturn.setStartValidityDate(Utility.getCurrentTime());
      //  wkcolReturn.setWorkCollectionCode(basicInfo.getFrequncyCollections().getWorkCollectionCode());
        wkcolReturn.setCreatedDate(Utility.getCurrentTime());
        wkcolReturn.setEndValidityDate(Utility.getCurrentTime());
        wkcolReturn.setCreatedBy("SYSTEM");
        wkcolReturn.setLastModified(Utility.getCurrentTime());
        wkcolReturn.setModifiedBy("SYSTEM");
        em.persist(wkcolReturn);
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
                metadata.setDatatypeId(headerInfoVO.getDataType());
                System.out.println("headerInfoVO.getDataSize().getId() " + headerInfoVO.getDataSize());
                metadata.setDatasizeId(headerInfoVO.getDataSize());
                System.out.println("cellno to determine getActualLabel  " + headerInfoVO.getCellNO());
                metadata.setActualTable(getActualLabel(headerInfoVO.getCellNO()));
                metadata.setLastModified(Utility.getCurrentTime());
                metadata.setModifiedBy("SYSTEM");
                metadata.setCreatedDate(Utility.getCurrentTime());
                metadata.setCreatedBy("SYSTEM");
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

        boolean result;
        ExtractionTableInfo extractionTableInfo = new ExtractionTableInfo();
        extractionTableInfo.setTemplateCode(basicInfo.getTemplateCode());
        extractionTableInfo.setTemplateName(basicInfo.getTemplateCode());
        extractionTableInfo.setTemplateType(basicInfo.getTemplateType().getTemplateId());
        extractionTableInfo.setCreatedDate(Utility.getCurrentTime());
        extractionTableInfo.setCreatedBy("SYSTEM");
        extractionTableInfo.setLastModified(Utility.getCurrentTime());
        extractionTableInfo.setModifiedBy("SYSTEM");
        em.persist(extractionTableInfo);

        result = true;

        return result;

    }

    public boolean addReturns(LinkedHashMap linkedHashMap) throws Exception {
        BasicInfoVO basicInfo = (BasicInfoVO) linkedHashMap.get(1);
        LinkedHashMap<Integer, LinkedList<HeaderInfoVO>> headerInfos = (LinkedHashMap<Integer, LinkedList<HeaderInfoVO>>) linkedHashMap.get(2);
        boolean result;
        TRtnReturns trtnReturns = new TRtnReturns(basicInfo.getTemplateCode());
        trtnReturns.setDescription(basicInfo.getTemplateDesc());
        trtnReturns.setLastModified(Utility.getCurrentTime());
        trtnReturns.setModifiedBy("SYSTEM");
        trtnReturns.setCreatedDate(Utility.getCurrentTime());
        trtnReturns.setCreatedBy("SYSTEM");
        trtnReturns.setEndValidityDate(Utility.getCurrentTime());
        trtnReturns.setStartValidityDate(Utility.getCurrentTime());
        trtnReturns.setFrequency(basicInfo.getFrequncyCollections());

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
            // em = emf.createEntityManager();
            em.getTransaction().begin();
            addExtractionInfo(linkedHashMap);
            System.out.println("extractionInfo populated");
            addArtifacts(linkedHashMap);
            System.out.println("artifactDAO populated");
            addMetadata(linkedHashMap);
            System.out.println("metadataTableDAO populated");
            addReturns(linkedHashMap);
            System.out.println("returnsDAO populated");
//            addReturnWorkCollectionMapping(linkedHashMap);
//            System.out.println("returnworkcollectionmapping populated");

            em.getTransaction().commit();
//            em.flush();

            System.out.println("transaction commited");
            em.close();
            System.out.println("transaction closed");
            result = true;
        } catch (Exception ex) {
            IRSDialog.showAlert("TestDAO ", "Unable to save record in the datastore");
            result = false;

            if (em != null) {
                em.getTransaction().rollback();
                em.close();
            }
            LOGGER.log(Level.FATAL, "Required Tables can not be populated", ex);

        }
        return result;
    }

    public String getActualLabel(String cellno) {
        Pair pair = Utility.convertExcelCellToPair(cellno);
        int numRows = (int) pair.getKey();
        System.out.println("numRows ActualLabel " + numRows);
        System.out.println("pair.getValue() ActualLabel " + pair.getValue());
        Row row = IRS.getPoisheet().getRow(numRows);
        Cell cell = row.getCell((int) pair.getValue());
        String label = ExcelView.getCellValueAsString(cell);
        System.out.println("getActualLabel " + label);
        return label;
    }
}
