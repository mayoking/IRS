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
public class TableModificationVO {

    /**
     * @return the tableName
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * @param tableName the tableName to set
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    /**
     * @return the ls
     */
    public List<ColumnVO> getLs() {
        return ls;
    }

    /**
     * @return the returnCode
     */
    public String getReturnCode() {
        return returnCode;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @return the workCollectionCode
     */
    public String getWorkCollectionCode() {
        return workCollectionCode;
    }

    /**
     * @param ls the ls to set
     */
    public void setLs(List<ColumnVO> ls) {
        this.ls = ls;
    }

    /**
     * @param returnCode the returnCode to set
     */
    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @param workCollectionCode the workCollectionCode to set
     */
    public void setWorkCollectionCode(String workCollectionCode) {
        this.workCollectionCode = workCollectionCode;
    }
    private String workCollectionCode;
    private String returnCode;
    private String username;
    private String tableName;
    private List<ColumnVO> ls;
}
