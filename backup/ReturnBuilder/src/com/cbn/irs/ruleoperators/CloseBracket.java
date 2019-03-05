/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cbn.irs.ruleoperators;

import com.bbt.irs.deploy.Messages;
import com.bbt.irs.ui.HBoxGenerator;
import com.bbt.irs.vo.AssertionRuleCompVO;
import com.bbt.irs.vo.ColumnandTypeVO;
import java.util.Map;

/**
 *
 * @author tkola
 */
public class CloseBracket extends RuleOperator implements OperatorConstants, Messages {

    @Override
    public String getOperatorType() {

        return CLOSEBRACKET_SIGN;
    }

    public boolean validateOpenBracket(Map<Integer, HBoxGenerator> map, Integer currentCounter) {
        boolean result = false;
        for (int i = 1; i < currentCounter; i++) {
            if (map.get(i).getOperator().getOperatorType().equalsIgnoreCase(OPENBRACKET_SIGN)) {
                result = true;
            }
            if (map.get(i).getOperator().getOperatorType().equalsIgnoreCase(CLOSEBRACKET_SIGN)) {
                result = false;
            }
        }
        return result;
    }

    /**
     *
     * @param returnCode
     * @param order
     * @param anchorPaneHBoxGenerator
     * @param isrhs
     * @param islast
     * @param isInter
     * @return
     */
    @Override
    public AssertionRuleCompVO processInfo(String returnCode, int order, HBoxGenerator anchorPaneHBoxGenerator, boolean isrhs, boolean islast,boolean isInter) {
        String result = null;
        String response = null;
        StringBuilder str = new StringBuilder();
        StringBuilder msg = new StringBuilder();
        
        String itemCode = (String) anchorPaneHBoxGenerator.getItemcodeCombo().getSelectionModel().getSelectedItem();
        ColumnandTypeVO columnandTypeVO = (ColumnandTypeVO) anchorPaneHBoxGenerator.getColumnCombo().getSelectionModel().getSelectedItem();
        str.append(" itemCode  ").append(itemCode)
                    .append(" of column ").append(columnandTypeVO.getColumnName()).append(" ");

        if (order == 1) {

           msg.append(str.toString());
            String assertFrag = String.format(itemCodeColPlaceHolder, itemCode, columnandTypeVO.getColumnName()) + getOperatorType();
            if (!isrhs) {
                response = startLHS+(assertFrag);
                result = String.format(startMessages, returnCode) + str.toString();
            } else {
                response = assertFrag;
                result = str.toString();
            }

        } else {
//            str.append(" ItemCode  ").append(itemCode)
//                    .append(" of Element ").append(columnandTypeVO.getColumnName()).append(" multiply by ");
            msg.append(str.toString());
             result = str.toString();
            String assertFrag = String.format(itemCodeColPlaceHolder, itemCode, columnandTypeVO.getColumnName()) + getOperatorType();
            response = assertFrag;
        }
        AssertionRuleCompVO assertionRuleCompVO = new AssertionRuleCompVO();
        assertionRuleCompVO.setAssertion(response);
        assertionRuleCompVO.setErrorMessage(result);
        assertionRuleCompVO.setDisPlayMessage(msg.toString());
        return assertionRuleCompVO;
    }

}
