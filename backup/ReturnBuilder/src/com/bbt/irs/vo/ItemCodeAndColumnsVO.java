/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.vo;

import java.util.List;

/**
 *
 * @author tkola
 */
public class ItemCodeAndColumnsVO {

    /**
     * @return the templateType
     */
    public String getTemplateType() {
        return templateType;
    }

    /**
     * @param templateType the templateType to set
     */
    public void setTemplateType(String templateType) {
        this.templateType = templateType;
    }

    /**
     * @return the tableNames
     */
    public List<String> getTableNames() {
        return tableNames;
    }

    /**
     * @param tableNames the tableNames to set
     */
    public void setTableNames(List<String> tableNames) {
        this.tableNames = tableNames;
    }

    /**
     * @return the colNames
     */
    public List<ColumnandTypeVO> getColNames() {
        return colNames;
    }

    /**
     * @return the itemCodes
     */
    public List<String> getItemCodes() {
        return itemCodes;
    }

    /**
     * @return the returnCode
     */
    public String getReturnCode() {
        return returnCode;
    }

    /**
     * @return the returnName
     */
    public String getReturnName() {
        return returnName;
    }

    /**
     * @param colNames the colNames to set
     */
    public void setColNames(List<ColumnandTypeVO> colNames) {
        this.colNames = colNames;
    }

    /**
     * @param itemCodes the itemCodes to set
     */
    public void setItemCodes(List<String> itemCodes) {
        this.itemCodes = itemCodes;
    }

    /**
     * @param returnCode the returnCode to set
     */
    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    /**
     * @param returnName the returnName to set
     */
    public void setReturnName(String returnName) {
        this.returnName = returnName;
    }
    private String returnCode;
    private String returnName;
    private List<String> itemCodes;
    private List<ColumnandTypeVO> colNames;
    private List<String> tableNames;
    private String templateType;
//    private Map<String,List<String>> tableFieldMap;
}
