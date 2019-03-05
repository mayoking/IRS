/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cbn.irs.ruleoperators;

import com.bbt.irs.deploy.IRSDialog;
import static com.bbt.irs.deploy.Messages.INFO;
import com.bbt.irs.ui.HBoxGenerator;
import com.bbt.irs.vo.AssertionRuleCompVO;
import com.bbt.irs.vo.ColumnandTypeVO;

/**
 *
 * @author tkola
 */
public class NoOperator extends RuleOperator {

    public boolean isLast;

    @Override
    public String getOperatorType() {

        return null;
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
        this.isLast = islast;
        if (anchorPaneHBoxGenerator.isIsComplexOperator()) {
            System.out.println("processing complex operator in no operator");
            return processComplexinfo(returnCode, order, anchorPaneHBoxGenerator, isrhs, isInter);
        }
        boolean isstatic = anchorPaneHBoxGenerator.isIsStatic();
        AssertionRuleCompVO assertionRuleCompVO = null;
        if (isInter) {
            if (isstatic) {
                assertionRuleCompVO = generateInfo4static(returnCode, order, anchorPaneHBoxGenerator, isrhs, islast, isInter);
            } else {
                assertionRuleCompVO = generateInfo4dynamic(returnCode, order, anchorPaneHBoxGenerator, isrhs, islast, isInter);
            }
        } else {
            assertionRuleCompVO = generateInfo4static(returnCode, order, anchorPaneHBoxGenerator, isrhs, islast, isInter);
        }
        return assertionRuleCompVO;
    }

    public AssertionRuleCompVO generateInfo4static(String returnCode, int order, HBoxGenerator anchorPaneHBoxGenerator, boolean isrhs, boolean islast, boolean isInter) {
        if (anchorPaneHBoxGenerator.isIsComplexOperator()) {

            return processComplexinfo(returnCode, order, anchorPaneHBoxGenerator, isrhs, isInter);
        }
        StringBuilder result = new StringBuilder();
        StringBuilder response = new StringBuilder();
        StringBuilder str = new StringBuilder();
        String itemCode = (String) anchorPaneHBoxGenerator.getItemcodeCombo().getSelectionModel().getSelectedItem();
        ColumnandTypeVO columnandTypeVO = (ColumnandTypeVO) anchorPaneHBoxGenerator.getColumnCombo().getSelectionModel().getSelectedItem();
        if (isInter) {
//            str.append(" itemCode  ").append(itemCode)
//                    .append(" of column ").append(columnandTypeVO.getColumnName().replace("|", "-"));
            str.append(itemCode).append(" [ ").append(columnandTypeVO.getColumnName().replace("|", "-")).append(" ] ");
        } else {
//            str.append(" itemCode  ").append(itemCode)
//                    .append(" of column ").append(columnandTypeVO.getColumnName().replace("|", "-"));
            str.append(itemCode).append(" [ ").append(columnandTypeVO.getColumnName().replace("|", "-")).append(" ] ");
        }
        StringBuilder msg = new StringBuilder();
        if (order == 1) {
            msg.append(str.toString());
            String assertFrag;
            if (isInter) {
                assertFrag = String.format(interItemCodeColPlaceHolder, returnCode.toLowerCase(), itemCode, columnandTypeVO.getColumnName());

            } else {
                assertFrag = String.format(itemCodeColPlaceHolder, itemCode, columnandTypeVO.getColumnName().replace("|", "-"));
                //System.out.println("assertfrag "+assertFrag);
            }
            if (!isrhs) {
                response.append(startLHS).append(assertFrag).append(endLHS);
                System.out.println("response oo " + response.toString());
                result.append(String.format(startMessages, returnCode)).append(str.toString());
            } else {
                response.append(startRHS).append(assertFrag).append(endRHS).append(assertEndtag);
                result.append(String.format(startRHSMessages, returnCode)).append(errorMsgEndTag);
            }

        } else {
            msg.append(str.toString());
            result.append(str.toString());
            String assertFrag;
            if (isInter) {
                assertFrag = String.format(interItemCodeColPlaceHolder, returnCode.toLowerCase(), itemCode, columnandTypeVO.getColumnName().replace("|", "-"));

            } else {
                assertFrag = String.format(itemCodeColPlaceHolder, itemCode, columnandTypeVO.getColumnName().replace("|", "-"));
                //System.out.println("assertfrag "+assertFrag);
            }
            response.append(assertFrag);
            if (isrhs) {
//                response.append(response).append(endRHS).append(assertEndtag);
                response.append(endRHS).append(assertEndtag);
                result.append(errorMsgEndTag);
            } else {
//                response.append(response).append(endLHS);
                response.append(endLHS);
            }
        }

        AssertionRuleCompVO assertionRuleCompVO = new AssertionRuleCompVO();
        assertionRuleCompVO.setAssertion(response.toString());
        assertionRuleCompVO.setErrorMessage(result.toString());
        assertionRuleCompVO.setDisPlayMessage(msg.toString());
        return assertionRuleCompVO;
    }

    public AssertionRuleCompVO generateInfo4dynamic(String returnCode, int order, HBoxGenerator anchorPaneHBoxGenerator, boolean isrhs, boolean islast, boolean isInter) {
        if (anchorPaneHBoxGenerator.isIsComplexOperator()) {

            return processComplexinfo(returnCode, order, anchorPaneHBoxGenerator, isrhs, isInter);
        }
        StringBuilder result = new StringBuilder();
        StringBuilder response = new StringBuilder();
        StringBuilder str = new StringBuilder();
       // String itemCode = (String) anchorPaneHBoxGenerator.getItemcodeCombo().getSelectionModel().getSelectedItem();
        ColumnandTypeVO columnandTypeVO = (ColumnandTypeVO) anchorPaneHBoxGenerator.getColumnCombo().getSelectionModel().getSelectedItem();
        System.out.println("columnandTypeVO no operator "+columnandTypeVO);
        str
                .append("sum of Field [ ").append(columnandTypeVO.getColumnName().replace("|", "-")).append("]");
        StringBuilder msg = new StringBuilder();
        if (order == 1) {
            msg.append(str.toString());
            String assertFrag = String.format(interItemColPlaceHolderDynamic, returnCode.toLowerCase(), columnandTypeVO.getColumnName().replace("|", "-"));
            System.out.println("assertfrag " + assertFrag);
            if (!isrhs) {
                response.append(startLHS).append(assertFrag).append(endLHS);
                System.out.println("response oo " + response.toString());
                result.append(String.format(startMessages, returnCode)).append(str.toString());
            } else {
                response.append(startRHS).append(assertFrag).append(endRHS).append(assertEndtag);
               // result.append(str.toString()).append(errorMsgEndTag);
                result.append(String.format(startRightMessages, returnCode)).append(str.toString()).append(errorMsgEndTag);
            }

        } else {
            msg.append(str.toString());
            result.append(str.toString());
            String assertFrag = String.format(interItemColPlaceHolderDynamic, returnCode.toLowerCase(), columnandTypeVO.getColumnName().replace("|", "-"));
            response.append(assertFrag);
            if (isrhs) {
//                response.append(response).append(endRHS).append(assertEndtag);
                response.append(endRHS).append(assertEndtag);
                result.append(errorMsgEndTag);
            } else {
//                response.append(response).append(endLHS);
                response.append(endLHS);
            }
        }

        AssertionRuleCompVO assertionRuleCompVO = new AssertionRuleCompVO();
        assertionRuleCompVO.setAssertion(response.toString());
        assertionRuleCompVO.setErrorMessage(result.toString());
        assertionRuleCompVO.setDisPlayMessage(msg.toString());
        return assertionRuleCompVO;
    }

    @Override
    public AssertionRuleCompVO processComplexinfo(String returnCode, int order, HBoxGenerator anchorPaneHBoxGenerator, boolean isrhs, boolean isInter) {
        String startAssert = "<xs:assert test=\"";
        String startErrorMessage = "xerces:message=\"---Assertion Failed for " + returnCode;
        AssertionRuleCompVO assertionRuleCompVO = anchorPaneHBoxGenerator.getAssertionRuleCompVO();
        String assertionMessage;
        String errorMessage;
        System.out.println("About to process assertion rulecompvo ");
        if (assertionRuleCompVO != null) {
            System.out.println("assertionRuleCompVO not null ");
            assertionMessage = assertionRuleCompVO.getAssertion();
            errorMessage = assertionRuleCompVO.getErrorMessage();
        } else {
            IRSDialog.showAlert(INFO, "No complex rule setup for the operator");
            return null;
        }
        if (order == 1) {
            if (!isrhs) {
                System.out.println("it is not rhs");
                assertionRuleCompVO.setAssertion(startAssert + assertionMessage);
                assertionRuleCompVO.setErrorMessage(startErrorMessage + errorMessage);
            } else {
                System.out.println("it is not lhs");
                assertionRuleCompVO.setAssertion((startRHS) + assertionMessage + (endRHS) + (assertEndtag));
                assertionRuleCompVO.setErrorMessage((startErrorMessage) + (errorMsgEndTag));
                RuleOperator operatortype = anchorPaneHBoxGenerator.getOperator();
                System.out.println("operatortype == null ? \"\" : operatortype.getOperatorType() " + operatortype == null ? "" : operatortype.getOperatorType());
                if (!isLast) {

                    assertionRuleCompVO.setAssertion(assertionRuleCompVO.getAssertion() + " " + operatortype == null ? "" : operatortype.getOperatorType() + " ");
                }
            }
        } else {
            if (isrhs) {
//                response.append(response).append(endRHS).append(assertEndtag);
                //response.append(endRHS).append(assertEndtag);

                assertionRuleCompVO.setAssertion(assertionMessage + endRHS + assertEndtag);//append(endRHS).append(assertEndtag)
                assertionRuleCompVO.setErrorMessage(errorMessage + errorMsgEndTag);
            } else {
                assertionRuleCompVO.setAssertion(assertionMessage + endLHS);
            }
            RuleOperator operatortype = anchorPaneHBoxGenerator.getOperator();
            System.out.println("operatortype == null ? \"\" : operatortype.getOperatorType() " + operatortype == null ? "" : operatortype.getOperatorType());
            if (!isLast) {

                assertionRuleCompVO.setAssertion(assertionRuleCompVO.getAssertion() + " " + operatortype == null ? "" : operatortype.getOperatorType() + " ");
            }
        }
        return assertionRuleCompVO;
    }

}
