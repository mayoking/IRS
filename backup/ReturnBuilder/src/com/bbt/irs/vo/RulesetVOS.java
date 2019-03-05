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
public class RulesetVOS {
  
    private List<RuleSetVO> rulesets;
    private String errorMessage;
    private Integer errorCode;
     /**
     * @return the rulesets
     */
    public List<RuleSetVO> getRulesets() {
        return rulesets;
    }

    /**
     * @param rulesets the rulesets to set
     */
    public void setRulesets(List<RuleSetVO> rulesets) {
        this.rulesets = rulesets;
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
}
