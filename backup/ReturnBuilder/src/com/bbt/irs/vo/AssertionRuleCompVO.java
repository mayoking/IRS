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
public class AssertionRuleCompVO {

    /**
     * @return the disPlayMessage
     */
    public String getDisPlayMessage() {
        return disPlayMessage;
    }

    /**
     * @param disPlayMessage the disPlayMessage to set
     */
    public void setDisPlayMessage(String disPlayMessage) {
        this.disPlayMessage = disPlayMessage;
    }

    private String assertion;
    private String errorMessage;
    private String disPlayMessage;

    /**
     * @return the assertion
     */
    public String getAssertion() {
        return assertion;
    }

    /**
     * @return the errorMessage
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * @param assertion the assertion to set
     */
    public void setAssertion(String assertion) {
        this.assertion = assertion;
    }

    /**
     * @param errorMessage the errorMessage to set
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
