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
public class RequestVO {

    /**
     * @return the returnCode
     */
    public String getReturnCode() {
        return returnCode;
    }

    /**
     * @return the workCollectionCode
     */
    public String getWorkCollectionCode() {
        return workCollectionCode;
    }

    /**
     * @param returnCode the returnCode to set
     */
    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    /**
     * @param workCollectionCode the workCollectionCode to set
     */
    public void setWorkCollectionCode(String workCollectionCode) {
        this.workCollectionCode = workCollectionCode;
    }
    private String returnCode;
    private String workCollectionCode;
}
