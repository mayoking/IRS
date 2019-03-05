/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.vo;

/**
 *
 * @author tkola
 */
public class ColumnandTypeVO {

    /**
     * @return the columnName
     */
    public String getColumnName() {
        return columnName;
    }

    /**
     * @return the dataType
     */
    public int getDataType() {
        return dataType;
    }

    /**
     * @param columnName the columnName to set
     */
    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    /**
     * @param dataType the dataType to set
     */
    public void setDataType(int dataType) {
        this.dataType = dataType;
    }
    private String columnName;
    private int    dataType;
    
    @Override
    public String toString(){
        return columnName;
    }
}
