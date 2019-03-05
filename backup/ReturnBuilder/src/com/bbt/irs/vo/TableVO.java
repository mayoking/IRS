/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.vo;

import com.bbt.irs.ui.CompositeVBox;
import java.util.List;

/**
 *
 * @author tkola
 */
public class TableVO {

    private int tableID;
    private String tableDesc;
    private List<CompositeVBox> colInfo;
    private List<ColObject> excelColList;

    /**
     * @return the tableID
     */
    public int getTableID() {
        return tableID;
    }

    /**
     * @param tableID the tableID to set
     */
    public void setTableID(int tableID) {
        this.tableID = tableID;
    }

    /**
     * @return the tableDesc
     */
    public String getTableDesc() {
        return tableDesc;
    }

    /**
     * @param tableDesc the tableDesc to set
     */
    public void setTableDesc(String tableDesc) {
        this.tableDesc = tableDesc;
    }

    /**
     * @return the colInfo
     */
    public List<CompositeVBox> getColInfo() {
        return colInfo;
    }

    /**
     * @param colInfo the colInfo to set
     */
    public void setColInfo(List<CompositeVBox> colInfo) {
        this.colInfo = colInfo;
    }

    @Override
    public String toString() {
        return this.tableDesc;
    }

    /**
     * @return the excelColList
     */
    public List<ColObject> getExcelColList() {
        return excelColList;
    }

    /**
     * @param excelColList the excelColList to set
     */
    public void setExcelColList(List<ColObject> excelColList) {
        this.excelColList = excelColList;
    }

}
