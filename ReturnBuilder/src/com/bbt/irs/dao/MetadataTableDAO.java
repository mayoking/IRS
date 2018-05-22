/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.dao;

import com.bbt.irs.deploy.IRS;
import com.bbt.irs.entity.MetadataTable;
import com.bbt.irs.ui.ExcelView;
import com.bbt.irs.util.Utility;
import com.bbt.irs.vo.BasicInfoVO;
import com.bbt.irs.vo.HeaderInfoVO;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import javafx.util.Pair;
import javax.persistence.EntityManager;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

/**
 *
 * @author opeyemi
 */
public class MetadataTableDAO {

    EntityManager em;

    public MetadataTableDAO(EntityManager em) {
        this.em = em;
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
                metadata.setCreatedBy("System");
                metadata.setCreatedDate(Utility.getCurrentTime());
                metadata.setLastModified(Utility.getCurrentTime());
                metadata.setModifiedBy("SYSTEM");
                System.out.println("headerInfoVO.getDataSize().getId() " + headerInfoVO.getDataSize());
                metadata.setDatasizeId(headerInfoVO.getDataSize());
                System.out.println("cellno to determine getActualLabel  " + headerInfoVO.getCellNO());
                metadata.setActualTable(getActualLabel(headerInfoVO.getCellNO()));

                IRS.getEm().persist(metadata);
            }
        }

        //em.getTransaction().commit();
        result = true;

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
