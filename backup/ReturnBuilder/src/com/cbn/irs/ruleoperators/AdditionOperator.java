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

/**
 *
 * @author tkola
 */
public class AdditionOperator extends RuleOperator implements Messages {

    @Override
    public String getOperatorType() {
        return "+";
    }

    /**
     *
     * @param returnCode
     * @param order
     * @param anchorPaneHBoxGenerator
     * @param isrhs
     * @param islast
     * @return
     */
    @Override
    public AssertionRuleCompVO processInfo(String returnCode, int order, HBoxGenerator anchorPaneHBoxGenerator, boolean isrhs, boolean islast, boolean isInter) {
        boolean isstatic = anchorPaneHBoxGenerator.isIsStatic();
        AssertionRuleCompVO assertionRuleCompVO ;
        if (isInter) {
            interIntradesc = INTERVALIDATION;
            if (isstatic) {
                assertionRuleCompVO = generateInfo4static(returnCode, order, anchorPaneHBoxGenerator, isrhs, islast, isInter);
            } else {
                assertionRuleCompVO = generateInfo4dynamic(returnCode, order, anchorPaneHBoxGenerator, isrhs, islast, isInter);
            }
        } else {
            interIntradesc = INTRAVALIDATION;
            assertionRuleCompVO = generateInfo4static(returnCode, order, anchorPaneHBoxGenerator, isrhs, islast, isInter);
        }
        return assertionRuleCompVO;
    }

    private AssertionRuleCompVO generateInfo4static(String returnCode, int order, HBoxGenerator anchorPaneHBoxGenerator, boolean isrhs, boolean islast, boolean isInter) {
        StringBuilder result = new StringBuilder();
        StringBuilder response = new StringBuilder();
        StringBuilder msg = new StringBuilder();
        StringBuilder str = new StringBuilder();
        String itemCode = (String) anchorPaneHBoxGenerator.getItemcodeCombo().getSelectionModel().getSelectedItem();
        ColumnandTypeVO columnandTypeVO = (ColumnandTypeVO) anchorPaneHBoxGenerator.getColumnCombo().getSelectionModel().getSelectedItem();
        String columnName = columnandTypeVO.getColumnName().replace("|", "-");
        try {
//            str.append(" itemCode  ").append(itemCode)
//                    .append(" of column ").append(columnName).append(" plus ");
            str.append(itemCode).append(" [ ").append(columnName).append(" ] ").append(" plus ");

            if (order == 1) {
                msg.append(str.toString());
                String assertFrag;
                if (isInter) {
                    assertFrag = String.format(interItemCodeColPlaceHolder, returnCode, itemCode, columnName);

                } else {

                    assertFrag = String.format(itemCodeColPlaceHolder, itemCode, columnName) + getOperatorType();
                    //System.out.println("assertfrag "+assertFrag);
                }
                if (!isrhs) {
                    response.append(startLHS).append((assertFrag));
                    result.append(String.format(startMessages, returnCode)).append(str.toString());
                } else {
                    response.append(startRHS).append(assertFrag);
                    result.append(String.format(startRHSMessages,returnCode)).append(str.toString());
                }

            } else {
                msg.append(str.toString());
                result.append(str.toString());
                String assertFrag = String.format(itemCodeColPlaceHolder, itemCode, columnName) + getOperatorType();
                response.append(assertFrag);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("this is the msg " + msg.toString());
        AssertionRuleCompVO assertionRuleCompVO = new AssertionRuleCompVO();
        assertionRuleCompVO.setAssertion(response.toString());
        assertionRuleCompVO.setErrorMessage(result.toString());
        assertionRuleCompVO.setDisPlayMessage(msg.toString());
        return assertionRuleCompVO;
    }

    private AssertionRuleCompVO generateInfo4dynamic(String returnCode, int order, HBoxGenerator anchorPaneHBoxGenerator, boolean isrhs, boolean islast, boolean isInter) {
        StringBuilder result = new StringBuilder();
        StringBuilder response = new StringBuilder();
        StringBuilder msg = new StringBuilder();
        StringBuilder str = new StringBuilder();
        ColumnandTypeVO columnandTypeVO = (ColumnandTypeVO) anchorPaneHBoxGenerator.getColumnCombo().getSelectionModel().getSelectedItem();
        String columnName = columnandTypeVO.getColumnName().replace("|", "-");
        try {
            str.append("sum of Field [").append(columnName).append("]").append(" plus ");
            if (order == 1) {
                msg.append(str.toString());
                String assertFrag = String.format(interItemColPlaceHolderDynamic, returnCode, columnName) + getOperatorType();
                if (!isrhs) {
                    response.append(startLHS).append((assertFrag));
                    result.append(String.format(startMessages, returnCode)).append(str.toString());
                } else {
                    response.append(startRHS).append(assertFrag);
                    //result.append(str.toString());
                    result.append(String.format(startRHSMessages,returnCode)).append(str.toString());
                }

            } else {
                msg.append(str.toString());
                result.append(str.toString());
                String assertFrag = String.format(interItemColPlaceHolderDynamic, returnCode, columnName) + getOperatorType();
                response.append(assertFrag);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("this is the msg " + msg.toString());
        AssertionRuleCompVO assertionRuleCompVO = new AssertionRuleCompVO();
        assertionRuleCompVO.setAssertion(response.toString());
        assertionRuleCompVO.setErrorMessage(result.toString());
        assertionRuleCompVO.setDisPlayMessage(msg.toString());
        return assertionRuleCompVO;
    }

}
