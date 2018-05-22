/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.vo;

import com.bbt.irs.entity.Datasize;
import com.bbt.irs.entity.Datatype;

/**
 *
 * @author opeyemi
 */
public class HeaderInfoVO {

    /**
     * @return the fromVerticalMerged
     */
    public String getFromVerticalMerged() {
        return fromVerticalMerged;
    }

    /**
     * @param fromVerticalMerged the fromVerticalMerged to set
     */
    public void setFromVerticalMerged(String fromVerticalMerged) {
        this.fromVerticalMerged = fromVerticalMerged;
    }

    /**
     * @return the toVerticalMerged
     */
    public String getToVerticalMerged() {
        return toVerticalMerged;
    }

    /**
     * @param toVerticalMerged the toVerticalMerged to set
     */
    public void setToVerticalMerged(String toVerticalMerged) {
        this.toVerticalMerged = toVerticalMerged;
    }

    /**
     * @return the fromHorizontalMerged
     */
    public String getFromHorizontalMerged() {
        return fromHorizontalMerged;
    }

    /**
     * @param fromHorizontalMerged the fromHorizontalMerged to set
     */
    public void setFromHorizontalMerged(String fromHorizontalMerged) {
        this.fromHorizontalMerged = fromHorizontalMerged;
    }

    /**
     * @return the toHorizontalMerged
     */
    public String getToHorizontalMerged() {
        return toHorizontalMerged;
    }

    /**
     * @param toHorizontalMerged the toHorizontalMerged to set
     */
    public void setToHorizontalMerged(String toHorizontalMerged) {
        this.toHorizontalMerged = toHorizontalMerged;
    }

    /**
     * @return the actualLabel
     */
    public String getActualLabel() {
        return actualLabel;
    }

    /**
     * @param actualLabel the actualLabel to set
     */
    public void setActualLabel(String actualLabel) {
        this.actualLabel = actualLabel;
    }

    /**
     * @return the cellNO
     */
    public String getCellNO() {
        return cellNO;
    }

    /**
     * @param cellNO the cellNO to set
     */
    public void setCellNO(String cellNO) {
        this.cellNO = cellNO;
    }

    /**
     * @return the cellName
     */
    public String getCellName() {
        return cellName;
    }

    /**
     * @param cellName the cellName to set
     */
    public void setCellName(String cellName) {
        this.cellName = cellName;
    }

    /**
     * @return the dataType
     */
    public Datatype getDataType() {
        return dataType;
    }

    /**
     * @param dataType the dataType to set
     */
    public void setDataType(Datatype dataType) {
        this.dataType = dataType;
    }

    /**
     * @return the dataSize
     */
    public Datasize getDataSize() {
        return dataSize;
    }

    /**
     * @param dataSize the dataSize to set
     */
    public void setDataSize(Datasize dataSize) {
        this.dataSize = dataSize;
    }
    private String cellNO;
    private String cellName;
    private Datatype dataType;
    private Datasize dataSize;
    private String actualLabel;
    private String fromVerticalMerged;
    private String toVerticalMerged;
    private String fromHorizontalMerged;
    private String toHorizontalMerged;
}
