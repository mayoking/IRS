/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.dao;

import com.bbt.irs.IRS;
import com.bbt.irs.entity.MetadataTable;
import com.bbt.irs.vo.BasicInfoVO;
import com.bbt.irs.vo.HeaderInfoVO;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import javax.persistence.EntityManager;

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
                metadata.setDataType(headerInfoVO.getDataType().getId());
                metadata.setDataType(headerInfoVO.getDataSize().getId());

                em.persist(metadata);
            }
        }
        //em.getTransaction().commit();
        result = true;

        return result;

    }
}
