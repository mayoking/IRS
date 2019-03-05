/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.dao;

import com.bbt.irs.deploy.IRS;
import com.bbt.irs.deploy.IRSDialog;
import com.bbt.irs.deploy.Messages;
import static com.bbt.irs.deploy.Messages.NEWRETURN_CODE;
import static com.bbt.irs.deploy.Messages.UNSYNC_CODE;
import com.bbt.irs.entity.ArtifactTable;
import com.bbt.irs.entity.ExtractionTableInfo;
import com.bbt.irs.entity.MetadataTable;
import com.bbt.irs.entity.TRbModificationType;
import com.bbt.irs.entity.TRbRestrictedField;
import com.bbt.irs.entity.TRbRestrictionData;
import com.bbt.irs.entity.TRbReturnsMatrix;
import com.bbt.irs.entity.TRtnReturns;
import com.bbt.irs.entity.TRtnReturnsWorkCollectionMapping;
import com.bbt.irs.entity.TRtnReturnsWorkCollectionMappingPK;
import com.bbt.irs.entity.TSctDbChanges;
import com.bbt.irs.entity.TSctDbChangesCodeType;
import com.bbt.irs.entity.TSctDbChangesStatus;
import com.bbt.irs.entity.TSctDbChangesType;
import com.bbt.irs.entity.TSctTypeDownload;
import com.bbt.irs.exception.DatabaseException;
import com.bbt.irs.exception.SynchronisationException;
import com.bbt.irs.ui.ExcelView;
import com.bbt.irs.util.Utility;
import com.bbt.irs.vo.BasicInfoVO;
import com.bbt.irs.vo.HeaderInfoVO;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import javafx.util.Pair;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.Root;
import javax.validation.ConstraintViolationException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

/**
 *
 * @author tkola
 */
public class MainDAO implements Messages {

    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(MainDAO.class);
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
        artifact.setCreatedBy(IRS.getInstance().username);
        artifact.setLastModified(Utility.getCurrentTime());
        artifact.setModifiedBy(IRS.getInstance().username);
        em.persist(artifact);
        result = true;
        //em.close();

        return result;

    }

    public boolean addReturnWorkCollectionMapping(LinkedHashMap linkedHashMap) throws DatabaseException {
        boolean result = false;
        BasicInfoVO basicInfo = (BasicInfoVO) linkedHashMap.get(1);
        LinkedHashMap<Integer, LinkedList<HeaderInfoVO>> headerInfos = (LinkedHashMap<Integer, LinkedList<HeaderInfoVO>>) linkedHashMap.get(2);
        try {
            TRtnReturnsWorkCollectionMappingPK test = new TRtnReturnsWorkCollectionMappingPK();
            TRtnReturns resturns = new ReturnsDAO().getReturnsWithrReturnCode(basicInfo.getTemplateCode()).get(0);
            test.setReturnCode(resturns.getReturnCode());
            test.setWorkCollectionId(basicInfo.getWorkCollection().getWorkCollectionId());
            TRtnReturnsWorkCollectionMapping wkcolReturn = new TRtnReturnsWorkCollectionMapping();
            // System.out.println("new ReturnsDAO().getReturnsWithrReturnCode(basicInfo.getTemplateCode()).get(0) " + new ReturnsDAO().getReturnsWithrReturnCode(basicInfo.getTemplateCode()).get(0));
            wkcolReturn.setTRtnReturns(resturns);
            wkcolReturn.setTRtnReturnsWorkCollectionMappingPK(test);
            //wkcolReturn.setRiTypeId(basicInfo.getRitype());
            //wkcolReturn.set(basicInfo.getWorkCollection());
            wkcolReturn.setStartValidityDate(basicInfo.getStartValidityDate());
            wkcolReturn.setCreatedDate(Utility.getCurrentTime());
            wkcolReturn.setCreatedBy(IRS.getInstance().username);
            wkcolReturn.setLastModified(Utility.getCurrentTime());
            wkcolReturn.setModifiedBy(IRS.getInstance().username);
            em.persist(wkcolReturn);
        } catch (Exception e) {
            throw new DatabaseException("Unable to associate returns with work Collection");
        }
        return result;
    }

    public String generateTableName(LinkedHashMap<Integer, LinkedList<HeaderInfoVO>> headerInfos, String templateCode, int i) {
        int number = headerInfos.size();
        String tablename;
        if (number > 1) {
            tablename = templateCode + "_" + i;
        } else {
            tablename = templateCode;
        }
        return tablename;
    }

    public boolean addMetadata(LinkedHashMap linkedHashMap) throws Exception {
        BasicInfoVO basicInfo = (BasicInfoVO) linkedHashMap.get(1);
        LinkedHashMap<Integer, LinkedList<HeaderInfoVO>> headerInfos = (LinkedHashMap<Integer, LinkedList<HeaderInfoVO>>) linkedHashMap.get(2);
        boolean result;

        for (int i = 0; i < headerInfos.size(); i++) {
            //LinkedList<HeaderInfoVO> ls = new Utility().manageMultipleTableInfo(headerInfos, i);//headerInfos.get(i);
            LinkedList<HeaderInfoVO> ls = headerInfos.get(i);
            for (int j = 0; j < ls.size(); j++) {
                HeaderInfoVO headerInfoVO = ls.get(j);
                System.out.println("headerInfoVO.getCellName() " + headerInfoVO.getCellName());
                MetadataTable metadata = new MetadataTable();
                metadata.setReturnCode(basicInfo.getTemplateCode());
                //metadata.setRiTypeCode(basicInfo.getRitype().getRiTypeCode());
                metadata.setXmlName(headerInfoVO.getCellName());
                metadata.setHeaderDesc(headerInfoVO.getCellName());
                metadata.setHeaderPosition(headerInfoVO.getCellNO());
                //metadata.setTableName(IRS.getTableNames().get(i));
                metadata.setTableName(generateTableName(headerInfos, basicInfo.getTemplateCode(), i));
                metadata.setDatatypeId(headerInfoVO.getDataType());
                System.out.println("headerInfoVO.getDataSize().getId() " + headerInfoVO.getDataSize());
                metadata.setDatasizeId(headerInfoVO.getDataSize());
                System.out.println("cellno to determine getActualLabel  " + headerInfoVO.getCellNO());
                metadata.setActualLabel(getActualLabel(headerInfoVO.getCellNO()));
                metadata.setLastModified(Utility.getCurrentTime());
                metadata.setModifiedBy(IRS.getInstance().username);
                metadata.setCreatedDate(Utility.getCurrentTime());
                metadata.setCreatedBy(IRS.getInstance().username);
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
        extractionTableInfo.setCreatedBy(IRS.getInstance().username);
        extractionTableInfo.setLastModified(Utility.getCurrentTime());
        extractionTableInfo.setModifiedBy(IRS.getInstance().username);
        em.persist(extractionTableInfo);

        result = true;

        return result;

    }

    public boolean addReturns(LinkedHashMap linkedHashMap) throws DatabaseException {
        BasicInfoVO basicInfo = (BasicInfoVO) linkedHashMap.get(1);
        LinkedHashMap<Integer, LinkedList<HeaderInfoVO>> headerInfos = (LinkedHashMap<Integer, LinkedList<HeaderInfoVO>>) linkedHashMap.get(2);
        boolean result;
        TRtnReturns trtnReturns = new TRtnReturns(basicInfo.getTemplateCode());
        try {
            trtnReturns.setDescription(basicInfo.getTemplateDesc());
            trtnReturns.setStartValidityDate(basicInfo.getStartValidityDate());
            trtnReturns.setLastModified(Utility.getCurrentTime());
            trtnReturns.setModifiedBy(IRS.getInstance().username);
            trtnReturns.setCreatedDate(Utility.getCurrentTime());
            trtnReturns.setCreatedBy(IRS.getInstance().username);
            trtnReturns.setFrequency(basicInfo.getFrequncyCollections());

            em.persist(trtnReturns);
        } catch (ConstraintViolationException ex) {
            LOGGER.log(Level.FATAL, ex);
            throw new DatabaseException("Duplicate return exist in the  storage");
        } catch (Exception e) {
            LOGGER.log(Level.FATAL, e);
            throw new DatabaseException("Error while saving return");
        }
        result = true;

        return result;

    }

    public boolean populateRequiredtables(LinkedHashMap linkedHashMap) {
        boolean result;
        String returns = ((BasicInfoVO) linkedHashMap.get(1)).getTemplateCode();
        ArtifactDAO artifactDAO = new ArtifactDAO(em);
        ReturnsDAO returnsDAO = new ReturnsDAO(em);
        MetadataTableDAO metadataTableDAO = new MetadataTableDAO(em);
        ExtractionInfoDAO extractionInfoDAO = new ExtractionInfoDAO(em);
        try {

            em.getTransaction().begin();
            addExtractionInfo(linkedHashMap);
            System.out.println("extractionInfo populated");
            addMetadata(linkedHashMap);
            System.out.println("metadataTableDAO populated");
            addReturns(linkedHashMap);
            em.getTransaction().commit();
            mapReturnToWorkCollection4Sysnc(linkedHashMap);
            System.out.println("transaction closed");
            result = true;
        } catch (Exception ex) {

            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();

            }

            if (em.isOpen()) {
                em.close();
            }
            this.rollbackAction(returns);
            IRSDialog.showAlert("TestDAO ", "Unable to save record in the datastore");
            LOGGER.log(Level.FATAL, "Required Tables can not be populated", ex);
            result = false;

            if (em != null) {
                //em.getTransaction().rollback();
                if (em.isOpen()) {
                    em.close();
                }
            }

        }

        return result;
    }

    public boolean mapReturnToWorkCollection4Sysnc(LinkedHashMap linkedHashMap) throws DatabaseException, Exception {
        String returns = ((BasicInfoVO) linkedHashMap.get(1)).getTemplateCode();
        boolean result = true;
        if (em == null) {
            System.out.println("em is null");
            em = IRS.getEm();
        }
        try {
            em.getTransaction().begin();
            addReturnWorkCollectionMapping(linkedHashMap);
            System.out.println("workcollection mapping  populated");
            BasicInfoVO basicInfovo = ((BasicInfoVO) linkedHashMap.get(1));
            String workCollCode = basicInfovo.getWorkCollection().getWorkCollectionCode();
            updateSyncTable4NewReturn(basicInfovo.getTemplateCode(), workCollCode, basicInfovo.getStartValidityDate());
            System.out.println("updateSyncTable4NewReturn   populated");
            em.getTransaction().commit();
        } catch (Exception exception) {
            exception.printStackTrace();
            System.out.println("About to rollback " + returns);
            this.rollbackAction(returns);
            LOGGER.log(Level.FATAL, "Return can not be mapped to workCollection", exception);
            throw new DatabaseException("Return can not be mapped to workCollection");

        }

        return result;
    }

    public boolean updateSyncTable(String returnCode, String workCollCode, Date date, String changeType) throws DatabaseException {
        boolean result = false;
        TRtnReturns returns = new ReturnsDAO().getReturnsWithrReturnCode(returnCode).get(0);

        TSctDbChanges tsctDbChanges = new TSctDbChanges();
        try {
            if (!em.getTransaction().isActive()) {
                em.getTransaction().begin();
            }
            tsctDbChanges.setStatus(new TSctDbChangesStatus(UNSYNC_CODE));
            tsctDbChanges.setChangeBy(returns.getModifiedBy());
            tsctDbChanges.setChangeCode(returns.getReturnCode());
            tsctDbChanges.setChangeDate(returns.getLastModified());
            tsctDbChanges.setCreatedDate(new Date());
            tsctDbChanges.setTypeChanges(new TSctDbChangesType(changeType));
            tsctDbChanges.setChangeCodeType(new TSctDbChangesCodeType("RETURN"));
            tsctDbChanges.setCreatedBy(IRS.getInstance().username);
            tsctDbChanges.setCreatedDate(new Date());
            tsctDbChanges.setValidityDate(date);
            tsctDbChanges.setWorkCollCode(workCollCode);
            em.persist(tsctDbChanges);
            em.getTransaction().commit();
        } catch (ConstraintViolationException ex) {
            throw new DatabaseException("Duplicate entry in the store for Synchronisation");
        } catch (Exception e) {
            throw new DatabaseException("Fatal Error during data Synchronization ");
        }
        return result;
    }

    public boolean add2SyncTable(String returnCode, String workCollCode, Date date) throws DatabaseException {
        boolean result = false;
        TRtnReturns returns = new ReturnsDAO().getReturnsWithrReturnCode(returnCode).get(0);

        TSctDbChanges tsctDbChanges = new TSctDbChanges();
        try {
            if (!em.getTransaction().isActive()) {
                em.getTransaction().begin();
            }
            tsctDbChanges.setStatus(new TSctDbChangesStatus(UNSYNC_CODE));
            tsctDbChanges.setChangeBy(returns.getModifiedBy());
            tsctDbChanges.setChangeCode(returns.getReturnCode());
            tsctDbChanges.setChangeDate(returns.getLastModified());
            tsctDbChanges.setCreatedDate(new Date());
            tsctDbChanges.setTypeChanges(new TSctDbChangesType(NEWRETURN_CODE));
            tsctDbChanges.setChangeCodeType(new TSctDbChangesCodeType("RETURN"));
            tsctDbChanges.setCreatedBy(IRS.getInstance().username);
            tsctDbChanges.setCreatedDate(new Date());
            tsctDbChanges.setValidityDate(date);
            tsctDbChanges.setWorkCollCode(workCollCode);
            em.persist(tsctDbChanges);
            //em.getTransaction().commit();
        } catch (ConstraintViolationException ex) {
            throw new DatabaseException("Duplicate entry in the store for Synchronisation");
        } catch (Exception e) {
            throw new DatabaseException("Fatal Error during data Synchronization ");
        }

        return result;
    }

    public boolean updateSyncTable4NewReturn(String returnCode, String workCollCode, Date date) throws DatabaseException {
        boolean result = false;
        TRtnReturns returns = new ReturnsDAO().getReturnsWithrReturnCode(returnCode).get(0);

        TSctDbChanges tsctDbChanges = new TSctDbChanges();
        try {
            if (!em.getTransaction().isActive()) {
                em.getTransaction().begin();
            }
            tsctDbChanges.setStatus(new TSctDbChangesStatus(UNSYNC_CODE));
            tsctDbChanges.setChangeBy(returns.getModifiedBy());
            tsctDbChanges.setChangeCode(returns.getReturnCode());
            tsctDbChanges.setChangeDate(returns.getLastModified());
            tsctDbChanges.setCreatedDate(new Date());
            tsctDbChanges.setTypeChanges(new TSctDbChangesType(NEWRETURN_CODE));
            tsctDbChanges.setChangeCodeType(new TSctDbChangesCodeType("RETURN"));
            tsctDbChanges.setCreatedBy(IRS.getInstance().username);
            tsctDbChanges.setCreatedDate(new Date());
            tsctDbChanges.setValidityDate(date);
            tsctDbChanges.setWorkCollCode(workCollCode);
            em.persist(tsctDbChanges);
            //em.getTransaction().commit();
        } catch (ConstraintViolationException ex) {
            throw new DatabaseException("Duplicate entry in the store for Synchronisation");
        } catch (Exception e) {
            throw new DatabaseException("Fatal Error during data Synchronization ");
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

    public List<TSctDbChanges> getTSctDbChanges() throws DatabaseException {
        List<TSctDbChanges> list = null;
        try {
            Query query = em.createQuery("select a from TSctDbChanges a where a.status=?1");
            query.setParameter(1, new TSctDbChangesStatus(UNSYNC_CODE));
            list = query.getResultList();
            if (list == null || list.isEmpty()) {
                throw new DatabaseException("No data to synchronize");
            }
        } catch (DatabaseException ex) {
            LOGGER.log(Level.FATAL, ex);
            throw new DatabaseException(ex.getMessage());
        } catch (Exception ex) {
            LOGGER.log(Level.FATAL, ex);
            throw new DatabaseException("Unable to synchronize data check if there is data to synchronize ");
        }
        return list;
    }

    public boolean checkTSctDbChanges(String changecode) throws DatabaseException {
        List<TSctDbChanges> list;
        boolean result = false;
        try {
            Query query = em.createQuery("select a from TSctDbChanges a where a.status=?1 and a.changeCode=?2");
            query.setParameter(1, new TSctDbChangesStatus(UNSYNC_CODE));
            query.setParameter(2, changecode);
            list = query.getResultList();
            if (list == null || list.isEmpty()) {
                result = true;
            }
        } catch (Exception ex) {
            LOGGER.log(Level.FATAL, ex);
            throw new DatabaseException("Unable to synchronize data check if there is data to synchronize ");
        }
        return result;
    }

    public boolean checkTableExistence(String tableName) {
        List<String> list = null;
        boolean result;
        try {
            Query query = em.createNativeQuery("SELECT TABLE_NAME FROM OSMLiteDatabase.INFORMATION_SCHEMA.TABLES WHERE TABLE_TYPE = 'BASE TABLE' and TABLE_NAME=?");
            query.setParameter(1, tableName);
            list = query.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (list == null || list.isEmpty()) {
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    public boolean checkMetadataExisitence(String returnCode) {
        List<Date> list = null;
        boolean result;
        try {
            Query query = em.createQuery("select distinct a.returnCode from MetadataTable a where a.returnCode=?1");
            query.setParameter(1, returnCode);
            list = query.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (list == null || list.isEmpty()) {
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    public boolean checkReturnExisitence(String returnCode) {
        List<Date> list = null;
        boolean result;
        try {
            Query query = em.createQuery("select  a.returnCode from TRtnReturns a where a.returnCode=?1");
            query.setParameter(1, returnCode);
            list = query.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (list == null || list.isEmpty()) {
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    public boolean checkDBChangesExisitence(String returnCode) {
        List<Date> list = null;
        boolean result;
        try {
            Query query = em.createQuery("select  a.changeCode from TSctDbChanges a where a.changeCode=?1");
            query.setParameter(1, returnCode);
            list = query.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (list == null || list.isEmpty()) {
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    public List<Date> getUNSYNCDateDbChanges() {
        List<Date> list = null;
        try {
            Query query = em.createQuery("select distinct a.validityDate from TSctDbChanges a where a.status=?1 order by a.validityDate desc  ");
            query.setParameter(1, new TSctDbChangesStatus(UNSYNC_CODE));
            list = query.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public List<Date> getUNSYNCDateDbChanges2() {
        List<Date> list = null;
        try {
            Query query = em.createQuery("select distinct a.validityDate from TSctDbChanges a where a.status=?1 order by a.validityDate desc  ");
            query.setParameter(1, new TSctDbChangesStatus(UNSYNC_CODE));
            list = query.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (list == null || list.isEmpty()) {
            list = new ArrayList();
            list.add(new Date());

        }
        return list;
    }

    public List<TSctDbChanges> getTSctDbChanges(String status, Date dt) {
        List<TSctDbChanges> list = null;
        try {
            Query query = em.createQuery("select a from TSctDbChanges a where a.status=?1 and a.validityDate=?2 ");
            query.setParameter(1, new TSctDbChangesStatus(status));
            query.setParameter(2, dt);
            list = query.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public List<TSctDbChanges> getTSctDbChangesByidForSync(String id, Date dt) throws SynchronisationException {
        List<TSctDbChanges> list = null;
        try {
            Date test = getUNSYNCDateDbChanges().get(0);
            System.out.println("test " + test);
            System.out.println("dt " + dt);
            if (test != null) {
                if (dt.compareTo(test) != 0) {
                    throw new SynchronisationException("The date can not be changed from other exisitng release date");
                }
            }
            Query query = em.createQuery("select a from TSctDbChanges a where a.changeId=?1 and a.validityDate=?2 ");
            query.setParameter(1, Long.valueOf(id));
            query.setParameter(2, dt);
            list = query.getResultList();
        } catch (PersistenceException ex) {
            LOGGER.log(Level.FATAL, ex);
        }
        return list;
    }

    public boolean updateTSctDbChanges(TSctDbChanges change) {
        boolean result = false;
        try {
            em.getTransaction().begin();
            System.out.println("change id " + change.getChangeId());
            TSctDbChanges ttt = em.find(TSctDbChanges.class, change.getChangeId());
            em.merge(ttt);
            em.getTransaction().commit();
            result = true;
        } catch (Exception ex) {
            LOGGER.log(Level.FATAL, ex);
        }
        return result;
    }

    public List<String> getAllUNSYNCNewReturnsAsString() {
        List<String> list = null;
        try {
            Query query = em.createQuery("select a.changeCode from TSctDbChanges a where a.status=?1 and a.typeChanges=?2   ");
            query.setParameter(1, new TSctDbChangesStatus(UNSYNC_CODE));
            query.setParameter(2, new TSctDbChangesType("NEW_RETURN"));
            list = query.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public List<TSctDbChanges> getAllUNSYNCNewReturns() {
        List<TSctDbChanges> list = null;
        try {
            Query query = em.createQuery("select a from TSctDbChanges a where a.status=?1 and a.typeChanges=?2   ");
            query.setParameter(1, new TSctDbChangesStatus(UNSYNC_CODE));
            query.setParameter(2, new TSctDbChangesType("NEW_RETURN"));
            list = query.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }
        public List<TSctDbChanges> getAllUNSYNCReturns() {
        List<TSctDbChanges> list = null;
        try {
            Query query = em.createQuery("select a from TSctDbChanges a where a.status=?1 ");
            query.setParameter(1, new TSctDbChangesStatus(UNSYNC_CODE));
            //query.setParameter(2, new TSctDbChangesType("NEW_RETURN"));
            list = query.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public boolean extendReleasedate(Date date) throws DatabaseException {
        boolean result = false;
        try {
            List<TSctDbChanges> ls = getAllUNSYNCReturns();
            System.out.println("This is the size of the list " + ls.size());
            extendReleaseDate4NewReturns(date);
            extendReleaseDateDeactivatedReturns(date);
            em.getTransaction().begin();

            ls.stream().map((s) -> {
                s.setValidityDate(date);
                return s;
            }).map((s) -> {
                s.setModifiedBy(IRS.getInstance().username);
                return s;
            }).map((s) -> {
                s.setLastModified(new Date());
                return s;
            }).forEachOrdered((s) -> {
                em.persist(s);
            });
            em.getTransaction().commit();
            result = true;
        } catch (Exception ex) {
            ex.printStackTrace();
            LOGGER.log(Level.FATAL, ex);
            throw new DatabaseException("Error while extending released date");
        }
        return result;
    }

    public boolean extendReleaseDate4NewReturns(Date startValidity) throws DatabaseException {
        SynchronizerDAO sync = new SynchronizerDAO();
        List<TSctDbChanges> ls = sync.getTSctDbChangeType(NEWRETURN_CODE);
        ReturnsDAO retdao = new ReturnsDAO();
        boolean result = retdao.extendValidityDate(ls, startValidity);
        return result;
    }

    public boolean extendReleaseDateDeactivatedReturns(Date endValidity) throws DatabaseException {
        SynchronizerDAO sync = new SynchronizerDAO();
        List<TSctDbChanges> ls = sync.getTSctDbChangeType(DEACTRETURN_CODE);
        ReturnsDAO retdao = new ReturnsDAO();
        boolean result = retdao.extendValidityDate(ls, endValidity);
        return result;
    }

    public List<TRbModificationType> getModTypes() throws DatabaseException {
        List<TRbModificationType> ls = null;
        try {
            Query query = em.createQuery("select a from TRbModificationType a");
            ls = query.getResultList();
        } catch (PersistenceException e) {
            throw new DatabaseException("Error while retrieving Modification Types");
        }
        return ls;
    }

    public void rollbackAction(String returns) {
        System.out.println("inside rollback ");
        EntityManager ems = IRS.getEm();
        try {
            ems.getTransaction().begin();
            this.deleteFromExtractionInfo(returns, ems);
            this.deleteFromMetadata(returns, ems);
            this.deleteFromReturns(returns, ems);
            ems.getTransaction().commit();
            System.out.println("complete rollback ");
        } catch (Exception e) {
            LOGGER.log(Level.FATAL, e);
        }
    }

    public int deleteFromReturns(String returns, EntityManager ems) {
        int result = 0;
        try {
            CriteriaBuilder cb = ems.getCriteriaBuilder();

            // create delete
            CriteriaDelete<TRtnReturns> delete = cb.
                    createCriteriaDelete(TRtnReturns.class);

            // set the root class
            Root e = delete.from(TRtnReturns.class);

            // set where clause
            delete.where(cb.equal(e.get("returnCode"), returns));
            ems.createQuery(delete).executeUpdate();
            System.out.println("complete deletion of returns");
        } catch (Exception ex) {
            LOGGER.log(Level.FATAL, ex);
        }
        return result;
    }

    public int deleteFromMetadata(String returns, EntityManager ems) {
        int result = 0;
        try {
            CriteriaBuilder cb = ems.getCriteriaBuilder();

            // create delete
            CriteriaDelete<MetadataTable> delete = cb.
                    createCriteriaDelete(MetadataTable.class);

            // set the root class
            Root e = delete.from(MetadataTable.class);

            // set where clause
            delete.where(cb.equal(e.get("returnCode"), returns));
            ems.createQuery(delete).executeUpdate();
            System.out.println("complete deletion of metadata");
        } catch (Exception ex) {
            LOGGER.log(Level.FATAL, ex);
        }
        return result;
    }

    public int deleteFromExtractionInfo(String returns, EntityManager ems) {
        int result = 0;
        try {

            CriteriaBuilder cb = ems.getCriteriaBuilder();

            // create delete
            CriteriaDelete<ExtractionTableInfo> delete = cb.
                    createCriteriaDelete(ExtractionTableInfo.class);

            // set the root class
            Root e = delete.from(ExtractionTableInfo.class);

            // set where clause
            delete.where(cb.equal(e.get("templateCode"), returns));
            ems.createQuery(delete).executeUpdate();
//            // perform update
//            this.em.createQuery(delete).executeUpdate();
//            Query query = em.createQuery("delete from ExtractionTableInfo a where a.templateCode=?1  ");
//            query.setParameter(1, returns);
//            result = query.executeUpdate();
            System.out.println("complete deletion of extraction info");
        } catch (Exception ex) {
            System.out.println("exception for deletion");
            ex.printStackTrace();
            LOGGER.log(Level.FATAL, ex);
        }
        return result;
    }

    public TSctTypeDownload getSCTDownload(String riType) throws DatabaseException {
        List<TSctTypeDownload> list = null;
        try {
            Query query = em.createQuery("select a from TSctTypeDownload a where a.sctVersionType in (?1,?2) and a.riTypeCode=?3 order by a.startValidityDate desc   ");
            query.setParameter(1, "I");
            query.setParameter(2, "F");
            query.setParameter(3, riType);
            list = query.getResultList();
            if (list == null || list.isEmpty()) {
                throw new DatabaseException("No Recent SCT Download available for the Ri Type");
            }
        } catch (Exception ex) {
            LOGGER.log(Level.FATAL, ex);
            throw new DatabaseException("Error whil getting SCT for Sync");
        }
        TSctTypeDownload tsctchange = list.get(0);
        return tsctchange;
    }

    public TRbReturnsMatrix getReturn4fromMatrix(String returnCode, String deppendency) throws DatabaseException {
        List<TRbReturnsMatrix> list = null;
        try {
            Query query = em.createQuery("select a from TRbReturnsMatrix a where a.returnCode=?1 and a.dependnecy=?2  ");
            query.setParameter(1, returnCode);
            query.setParameter(2, deppendency);
            list = query.getResultList();
            if (list == null || list.isEmpty()) {
                throw new DatabaseException("No dependency found");
            }
        } catch (Exception ex) {
            LOGGER.log(Level.FATAL, ex);
            throw new DatabaseException("Error while getting SCT for Sync");
        }
        TRbReturnsMatrix tRbReturnsMatrix = list.get(0);
        return tRbReturnsMatrix;
    }
    
    public List<TRbReturnsMatrix> getReturnMatrix(String returnCode) throws DatabaseException {
        List<TRbReturnsMatrix> list = null;
        try {
            Query query = em.createQuery("select a from TRbReturnsMatrix a where a.returnCode=?1");
            query.setParameter(1, returnCode);
            list = query.getResultList();
            if (list == null || list.isEmpty()) {
                throw new DatabaseException("No dependency found");
            }
        } catch (Exception ex) {
            LOGGER.log(Level.FATAL, ex);
            throw new DatabaseException("Error while getting SCT for Sync");
        }
        return list;
    }

    public void deleteProblematicTemplate(String returnCode) throws DatabaseException, Exception {
        EntityManager ems = IRS.getEm();

        Query query = ems.createQuery("Select distinct a.tableName from MetadataTable  a where a.returnCode=?1");
        query.setParameter(1, returnCode);
        List<String> ls = query.getResultList();
        try {
            if (!ls.isEmpty()) {
                CreateTableDAO createTableDAO = new CreateTableDAO();
                createTableDAO.deleteTables(ls, returnCode);
                ems.getTransaction().begin();
                this.deleteExtractionTableInfo(returnCode, ems);
                this.deleteMetadata(returnCode, ems);
                this.deleteTRtnReturnsWorkCollectionMapping(returnCode, ems);
                this.deleteTRtnReturns(returnCode, ems);
                this.deleteArtifactTable(returnCode, ems);
                this.deleteTSctDbChanges(returnCode, ems);
                System.out.println("About to commit");
                ems.getTransaction().commit();
                System.out.println("done deleteing required tables");
                
            }
        } catch (SQLException sQLException) {
             throw new DatabaseException("error while deleting Template");
        } catch (PersistenceException persistenceException) {
              throw new DatabaseException("error while deleting Template");
        } catch (IOException iOException) {
              throw new DatabaseException("error while deleting Template");
        } catch (DatabaseException databaseException) {
              throw new DatabaseException("error while deleting Template");
        }
    }

    public void deleteExtractionTableInfo(String returnCode, EntityManager ems) throws DatabaseException {
        try {
            Query query = ems.createQuery("delete from ExtractionTableInfo a where a.templateCode=?1  ");
            query.setParameter(1, returnCode);
            int result = query.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DatabaseException("error while deleting from ExtractionTableInfo ");
        }
    }

    public void deleteTSctDbChanges(String returnCode, EntityManager ems) throws DatabaseException {
        try {
            Query query = ems.createQuery("delete from TSctDbChanges a where a.changeCode=?1  ");
            query.setParameter(1, returnCode);
            int result = query.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DatabaseException("error while deleting from ExtractionTableInfo ");
        }
    }

    public void deleteMetadata(String returnCode, EntityManager ems) throws DatabaseException {
        try {
            Query query = ems.createQuery("delete from MetadataTable a where a.returnCode=?1");
            query.setParameter(1, returnCode);
            int result = query.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DatabaseException("error while deleting from Metadata");
        }
    }

    public void deleteTRtnReturnsWorkCollectionMapping(String returnCode, EntityManager ems) throws DatabaseException {
        try {
            Query query = ems.createQuery("delete from TRtnReturnsWorkCollectionMapping a where a.tRtnReturns.returnCode=?1");
            query.setParameter(1, returnCode);
            int result = query.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DatabaseException("error while deleteing from TRtnReturnsWorkCollectionMapping");
        }
    }

    public void deleteTRtnReturns(String returnCode, EntityManager ems) throws DatabaseException {
        try {
            Query query = ems.createQuery("delete from TRtnReturns a where a.returnCode=?1");
            query.setParameter(1, returnCode);
            int result = query.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DatabaseException("error while deleting from TRtnReturns");
        }
    }

    public void deleteArtifactTable(String returnCode, EntityManager ems) throws DatabaseException {
        try {
            Query query = ems.createQuery("delete from ArtifactTable a where a.returnCode=?1");
            query.setParameter(1, returnCode);
            int result = query.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DatabaseException("error while deleting from ArtifactTable");
        }
    }
    
     public List<TRbRestrictedField> getTRbRestrictedField4Connector(String fieldname,String returnCode) throws DatabaseException {
        List<TRbRestrictedField> list = null;
        try {
            Query query = em.createQuery("select a from TRbRestrictedField a where a.returnField=?1 and a.returnCode=?2");
            query.setParameter(1, fieldname);
            query.setParameter(2, returnCode);
            list = query.getResultList();
            if (list == null ) {
                throw new DatabaseException("No Restriction found");
            }
        } catch (Exception ex) {
            LOGGER.log(Level.FATAL, ex);
            throw new DatabaseException("Error while getting SCT for Sync");
        }
        return list;
    }
     
      public List<TRbRestrictionData> getTRbRestrictedData(String restrictionCode) throws DatabaseException {
        List<TRbRestrictionData> list = null;
        try {
            Query query = em.createQuery("select a from TRbRestrictionData a where a.restrictionCode.restrictionCode=?1");
            query.setParameter(1, restrictionCode);
            list = query.getResultList();
            if (list == null) {
                throw new DatabaseException("No restriction data found");
            }
        } catch (Exception ex) {
            LOGGER.log(Level.FATAL, ex);
            throw new DatabaseException("Error while getting restriction data");
        }
        return list;
    }

}
