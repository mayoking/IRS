/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.vo;

import com.bbt.irs.entity.TCoreRiType;
import com.bbt.irs.entity.TRtnWorkCollectionSchedule;
import com.bbt.irs.entity.TemplateType;

/**
 *
 * @author opeyemi
 */
public class BasicInfoVO {

    /**
     * @return the templateCode
     */
    public String getTemplateCode() {
        return templateCode;
    }

    /**
     * @param templateCode the templateCode to set
     */
    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }

    /**
     * @return the templateDesc
     */
    public String getTemplateDesc() {
        return templateDesc;
    }

    /**
     * @param templateDesc the templateDesc to set
     */
    public void setTemplateDesc(String templateDesc) {
        this.templateDesc = templateDesc;
    }

    /**
     * @return the numTables
     */
    public String getNumTables() {
        return numTables;
    }

    /**
     * @param numTables the numTables to set
     */
    public void setNumTables(String numTables) {
        this.numTables = numTables;
    }

    /**
     * @return the workCollections
     */
    public TRtnWorkCollectionSchedule getWorkCollections() {
        return workCollections;
    }

    /**
     * @param workCollections the workCollections to set
     */
    public void setWorkCollections(TRtnWorkCollectionSchedule workCollections) {
        this.workCollections = workCollections;
    }

    /**
     * @return the templateType
     */
    public TemplateType getTemplateType() {
        return templateType;
    }

    /**
     * @param templateType the templateType to set
     */
    public void setTemplateType(TemplateType templateType) {
        this.templateType = templateType;
    }

    /**
     * @return the Ritype
     */
    public TCoreRiType getRitype() {
        return Ritype;
    }

    /**
     * @param Ritype the Ritype to set
     */
    public void setRitype(TCoreRiType Ritype) {
        this.Ritype = Ritype;
    }

   

    private String templateCode;
    private String templateDesc;
    private String numTables;
    private TRtnWorkCollectionSchedule workCollections;
    private TemplateType templateType;
    private TCoreRiType Ritype;
}
