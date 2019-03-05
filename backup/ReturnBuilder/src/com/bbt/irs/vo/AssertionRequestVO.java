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
public class AssertionRequestVO {

    /**
     * @return the isInter
     */
    public boolean isIsInter() {
        return isInter;
    }

    /**
     * @param isInter the isInter to set
     */
    public void setIsInter(boolean isInter) {
        this.isInter = isInter;
    }

    /**
     * @return the returnCode
     */
    public String getReturnCode() {
        return returnCode;
    }

    /**
     * @return the userAssertion
     */
    public String getUserAssertion() {
        return userAssertion;
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
     * @param returnCode the returnCode to set
     */
    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    /**
     * @param userAssertion the userAssertion to set
     */
    public void setUserAssertion(String userAssertion) {
        this.userAssertion = userAssertion;
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
    private String username;
    private String userAssertion;
    private String workCollectionCode;
    private String returnCode;
    private boolean isInter;
}
