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
public class AssertionResponseVO {

    /**
     * @return the desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * @return the errorCode
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * @return the sysAssertion
     */
    public String getSysAssertion() {
        return sysAssertion;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param desc the desc to set
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * @param errorCode the errorCode to set
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * @param sysAssertion the sysAssertion to set
     */
    public void setSysAssertion(String sysAssertion) {
        this.sysAssertion = sysAssertion;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }
    private String username;
    private String sysAssertion;
    private String desc;
    private String errorCode;
}
