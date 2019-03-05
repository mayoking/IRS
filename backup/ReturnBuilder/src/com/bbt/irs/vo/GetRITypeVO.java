/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.vo;

import com.bbt.irs.entity.TCoreRiType;
import com.bbt.irs.entity.TLkupFrequency;
import com.bbt.irs.entity.TemplateType;

/**
 *
 * @author opeyemi
 */
public class GetRITypeVO 
{
    /**
     * @return the afiliate
     */
    public String getAfiliate() {
        return afiliate;
    }

    /**
     * @param afiliate the afiliate to set
     */
    public void setAfiliate(String afiliate) {
        this.afiliate = afiliate;
    }

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
    public TLkupFrequency getFrequncyCollections() {
        return frequncyCollections;
    }

    /**
     * @param frequncyCollections the workCollections to set
     */
    public void setFrequncyCollections(TLkupFrequency frequncyCollections) {
        this.frequncyCollections = frequncyCollections;
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
    private String afiliate;
    private TLkupFrequency frequncyCollections;
    private TemplateType templateType;
    private TCoreRiType Ritype;
}
