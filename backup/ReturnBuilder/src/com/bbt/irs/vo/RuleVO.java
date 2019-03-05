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
public class RuleVO {

    public RuleVO() {

    }
    private List<OperandsVO> operandsListLeft;
    private List<OperandsVO> operandsListRight;
    private boolean onlyBoolean;
    private String ruleMessage;
    private String ruleErrormessage;
    private String ruleAssertion;
    private String ruleType;
    private String alias;
    private String connect;
    private String userid;
    private String versionId;
    private String errorMessage;
    private Integer errorCode;

    /**
     * @return the ruleAssertion
     */
    public String getRuleAssertion() {
        return ruleAssertion;
    }

    /**
     * @return the ruleErrormessage
     */
    public String getRuleErrormessage() {
        return ruleErrormessage;
    }

    /**
     * @return the ruleMessage
     */
    public String getRuleMessage() {
        return ruleMessage;
    }

    /**
     * @param ruleAssertion the ruleAssertion to set
     */
    public void setRuleAssertion(String ruleAssertion) {
        this.ruleAssertion = ruleAssertion;
    }

    /**
     * @param ruleErrormessage the ruleErrormessage to set
     */
    public void setRuleErrormessage(String ruleErrormessage) {
        this.ruleErrormessage = ruleErrormessage;
    }

    /**
     * @param ruleMessage the ruleMessage to set
     */
    public void setRuleMessage(String ruleMessage) {
        this.ruleMessage = ruleMessage;
    }

    /**
     * @return the ruleType
     */
    public String getRuleType() {
        return ruleType;
    }

    /**
     * @param ruleType the ruleType to set
     */
    public void setRuleType(String ruleType) {
        this.ruleType = ruleType;
    }

    /**
     * @return the operandsListLeft
     */
    public List<OperandsVO> getOperandsListLeft() {
        return operandsListLeft;
    }

    /**
     * @return the operandsListRight
     */
    public List<OperandsVO> getOperandsListRight() {
        return operandsListRight;
    }

    /**
     * @return the onlyBoolean
     */
    public boolean isOnlyBoolean() {
        return onlyBoolean;
    }

    /**
     * @param onlyBoolean the onlyBoolean to set
     */
    public void setOnlyBoolean(boolean onlyBoolean) {
        this.onlyBoolean = onlyBoolean;
    }

    /**
     * @param operandsListLeft the operandsListLeft to set
     */
    public void setOperandsListLeft(List<OperandsVO> operandsListLeft) {
        this.operandsListLeft = operandsListLeft;
    }

    /**
     * @param operandsListRight the operandsListRight to set
     */
    public void setOperandsListRight(List<OperandsVO> operandsListRight) {
        this.operandsListRight = operandsListRight;
    }

    /**
     * @return the alias
     */
    public String getAlias() {
        return alias;
    }

    /**
     * @param alias the alias to set
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }

    /**
     * @return the connect
     */
    public String getConnect() {
        return connect;
    }

    /**
     * @param connect the connect to set
     */
    public void setConnect(String connect) {
        this.connect = connect;
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

//    /**
//     * @return the connect
//     */
//    public Connector getConnect() {
//        return connect;
//    }
//
//    /**
//     * @param connect the connect to set
//     */
//    public void setConnect(Connector connect) {
//        this.connect = connect;
//    }

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
}
