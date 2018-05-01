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
}
