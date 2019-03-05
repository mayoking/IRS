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
public class RuleSetVO {

 

    public RuleSetVO() {

    }
    private String workCollectionCode;
    private String returnCode;
    private List<RuleVO> rules;
    private String interReturnCode;
    private String depenednecyReturnCode;
    private String userid;
    private String versionId;
    private String errorMessage;
    private Integer errorCode;

    /**
     * @return the returnCode
     */
    public String getReturnCode() {
        return returnCode;
    }

    /**
     * @return the rules
     */
    public List<RuleVO> getRules() {
        return rules;
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
     * @param rules the rules to set
     */
    public void setRules(List<RuleVO> rules) {
        this.rules = rules;
    }

    /**
     * @param workCollectionCode the workCollectionCode to set
     */
    public void setWorkCollectionCode(String workCollectionCode) {
        this.workCollectionCode = workCollectionCode;
    }

    /**
     * @return the userid
     */
    public String getUserid() {
        return userid;
    }

    /**
     * @param userid the userid to set
     */
    public void setUserid(String userid) {
        this.userid = userid;
    }

    /**
     * @return the errorCode
     */
    public Integer getErrorCode() {
        return errorCode;
    }

    /**
     * @return the errorMessage
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * @param errorCode the errorCode to set
     */
    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * @param errorMessage the errorMessage to set
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
       /**
     * @return the versionId
     */
    public String getVersionId() {
        return versionId;
    }

    /**
     * @param versionId the versionId to set
     */
    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }

    /**
     * @return the interReturnCode
     */
    public String getInterReturnCode() {
        return interReturnCode;
    }

    /**
     * @param interReturnCode the interReturnCode to set
     */
    public void setInterReturnCode(String interReturnCode) {
        this.interReturnCode = interReturnCode;
    }

    /**
     * @return the depenednecyReturnCode
     */
    public String getDepenednecyReturnCode() {
        return depenednecyReturnCode;
    }

    /**
     * @param depenednecyReturnCode the depenednecyReturnCode to set
     */
    public void setDepenednecyReturnCode(String depenednecyReturnCode) {
        this.depenednecyReturnCode = depenednecyReturnCode;
    }

}
