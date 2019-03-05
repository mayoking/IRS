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
public class TemplateFieldModVO {

    private String WorkCollectionCode;
    private String tableName;
    private String returnCode;
    private String fieldName;
    private String restrictionCode;
    private String username;

    /**
     * @return the fieldName
     */
    public String getFieldName() {
        return fieldName;
    }

    /**
     * @return the restrictionCode
     */
    public String getRestrictionCode() {
        return restrictionCode;
    }

    /**
     * @return the returnCode
     */
    public String getReturnCode() {
        return returnCode;
    }

    /**
     * @return the tableName
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @return the WorkCollectionCode
     */
    public String getWorkCollectionCode() {
        return WorkCollectionCode;
    }

    /**
     * @param fieldName the fieldName to set
     */
    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    /**
     * @param restrictionCode the restrictionCode to set
     */
    public void setRestrictionCode(String restrictionCode) {
        this.restrictionCode = restrictionCode;
    }

    /**
     * @param returnCode the returnCode to set
     */
    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    /**
     * @param tableName the tableName to set
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @param WorkCollectionCode the WorkCollectionCode to set
     */
    public void setWorkCollectionCode(String WorkCollectionCode) {
        this.WorkCollectionCode = WorkCollectionCode;
    }

}
