/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cbn.irs.ruleoperators;

import com.bbt.irs.ui.HBoxGenerator;
import com.bbt.irs.vo.AssertionRuleCompVO;

/**
 *
 * @author tkola
 */
public class ColumnSum extends RuleOperator{

    @Override
    public String getOperatorType() {
        return "csum";
    }

    @Override
    public AssertionRuleCompVO processInfo(String returnCode, int order, HBoxGenerator anchorPaneHBoxGenerator, boolean isrhs, boolean islast,boolean isInter) {
        
        return null;
    }
    
}
