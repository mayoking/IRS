/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.vo;

import com.bbt.irs.entity.TRbDatasize;
import com.bbt.irs.entity.TRbDatatype;



/**
 *
 * @author tkola
 */
public class ColumnVO {

    /**
     * @return the columnName
     */
    public String getColumnName() {
        return columnName;
    }

    /**
     * @return the dataSize
     */
    public TRbDatasize getDataSize() {
        return dataSize;
    }

    /**
     * @return the dataType
     */
    public TRbDatatype getDataType() {
        return dataType;
    }

    /**
     * @return the excelHeader
     */
    public String getExcelHeader() {
        return excelHeader;
    }

    /**
     * @param columnName the columnName to set
     */
    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    /**
     * @param dataSize the dataSize to set
     */
    public void setDataSize(TRbDatasize dataSize) {
        this.dataSize = dataSize;
    }

    /**
     * @param dataType the dataType to set
     */
    public void setDataType(TRbDatatype dataType) {
        this.dataType = dataType;
    }

    /**
     * @param excelHeader the excelHeader to set
     */
    public void setExcelHeader(String excelHeader) {
        this.excelHeader = excelHeader;
    }
    private String columnName;
    private String excelHeader;
    private TRbDatatype    dataType;
    private TRbDatasize    dataSize;
}
