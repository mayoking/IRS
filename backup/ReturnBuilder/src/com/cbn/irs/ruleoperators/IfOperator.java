/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cbn.irs.ruleoperators;

import com.bbt.irs.controller.AssertionBuilderUIController;
import com.bbt.irs.controller.RuleBuilderAbstractController;
import com.bbt.irs.deploy.IRS;
import com.bbt.irs.deploy.IRSDialog;
import com.bbt.irs.deploy.Messages;
import static com.bbt.irs.deploy.Messages.FXMLPATH;
import com.bbt.irs.ui.HBoxGenerator;
import com.bbt.irs.vo.AssertionRuleCompVO;
import com.bbt.irs.vo.ItemCodeAndColumnsVO;
import java.io.IOException;
import java.util.Map;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author tkola
 */
public class IfOperator extends RuleOperator implements Messages {

//    public boolean isLast;

    @Override
    public String getOperatorType() {
        return "if";
    }

    @Override
    public AssertionRuleCompVO processInfo(String returnCode, int order, HBoxGenerator anchorPaneHBoxGenerator, boolean isrhs, boolean islast, boolean isInter) {
        this.isLast = islast;
        System.out.println("processing complex operator in if operator " + anchorPaneHBoxGenerator.getAssertionRuleCompVO().getAssertion());
        System.out.println("processing complex operator in if operator2 " + anchorPaneHBoxGenerator.getAssertionRuleCompVO().getDisPlayMessage());
        return processComplexinfo(returnCode, order, anchorPaneHBoxGenerator, isrhs, isInter);
    }

    @Override
    public AssertionRuleCompVO processComplexinfo(String returnCode, int order, HBoxGenerator anchorPaneHBoxGenerator, boolean isrhs, boolean isInter) {
        String startAssert = "<xs:assert test=\"";
        //String startErrorMessage = "xerces:message=\"---Assertion Failed for " + returnCode;
        String startErrorMessage = "xerces:message=\"Error: " + returnCode + " ";
        AssertionRuleCompVO assertionRuleCompVOold = anchorPaneHBoxGenerator.getAssertionRuleCompVO();
        AssertionRuleCompVO assertionRuleCompVO = new AssertionRuleCompVO();

        String assertionMessage;
        String errorMessage;
        System.out.println("About to process assertion rulecompvo ");

        if (assertionRuleCompVOold != null) {
            assertionRuleCompVO.setAssertion(assertionRuleCompVOold.getAssertion());
            assertionRuleCompVO.setDisPlayMessage(assertionRuleCompVOold.getDisPlayMessage());
            assertionRuleCompVO.setErrorMessage("(" + assertionRuleCompVOold.getErrorMessage() + ")");
            System.out.println("assertionRuleCompVO not null ");
            assertionMessage = assertionRuleCompVO.getAssertion();
            errorMessage = assertionRuleCompVO.getErrorMessage();
        } else {
            IRSDialog.showAlert(INFO, "No complex rule setup for the operator");
            return null;
        }
        if (order == 1) {
            if (!isrhs) {
                assertionRuleCompVO.setAssertion(startAssert + assertionMessage);
                assertionRuleCompVO.setErrorMessage(startErrorMessage + errorMessage);
            } else {
                assertionRuleCompVO.setAssertion((startRHS) + assertionMessage);//+(endRHS)+(assertEndtag)
                assertionRuleCompVO.setErrorMessage(String.format(startRHSMessages, returnCode) + (errorMessage) + " ");//+ (errorMsgEndTag)
                RuleOperator operatortype = anchorPaneHBoxGenerator.getOperator();
                if (!isLast) {
                    String test = assertionRuleCompVO.getAssertion();
                    String operator = (operatortype == null ? "" : (operatortype.getOperatorType())) + " ";
                    assertionRuleCompVO.setAssertion(test + " " + operator);
                }
            }
        } else {
            if (isrhs) {

                assertionRuleCompVO.setAssertion(assertionMessage);//+ endRHS +assertEndtag

                assertionRuleCompVO.setErrorMessage(errorMessage);//+ errorMsgEndTag
            } else {
                assertionRuleCompVO.setAssertion(assertionMessage);//+ endLHS 
            }
            RuleOperator operatortype = anchorPaneHBoxGenerator.getOperator();
            System.out.println("operatortype not 1st order operatortype.getOperatorType() " + operatortype == null ? "" : operatortype.getOperatorType());
            if (!isLast) {

                assertionRuleCompVO.setAssertion(assertionRuleCompVO.getAssertion() + " " + operatortype == null ? "" : operatortype.getOperatorType() + " ");
            }
        }
        if (isLast) {
            if (isrhs) {
                assertionRuleCompVO.setAssertion(assertionRuleCompVO.getAssertion() + endRHS + assertEndtag);
                assertionRuleCompVO.setErrorMessage(assertionRuleCompVO.getErrorMessage() + errorMsgEndTag);
            } else {
                assertionRuleCompVO.setAssertion(assertionRuleCompVO.getAssertion() + endLHS);
            }
        } else {
            assertionRuleCompVO.setErrorMessage(assertionRuleCompVO.getErrorMessage() + " " + anchorPaneHBoxGenerator.getOperator().getOperatorType() + " ");
        }
        return assertionRuleCompVO;
    }

    @Override
    public void getComplexUI(RuleBuilderAbstractController callingParent, Map<Integer, HBoxGenerator> mapp, VBox operatorVbox, Integer counterzz) {

        try {
            AssertionBuilderUIController.instance.setIfOperatorActionReview(false);
            ItemCodeAndColumnsVO itemcodeandColumnsVO = AssertionBuilderUIController.instance.getItemCodeAndColumnsVO();
            FXMLLoader loader = new FXMLLoader();
            Parent root;
            ifOperatorStage = new Stage(StageStyle.DECORATED);
            ifOperatorStage.centerOnScreen();
            ifOperatorStage.initOwner(IRS.getInstance().getMainStage());
            
            if (itemcodeandColumnsVO.getTemplateType().equalsIgnoreCase("1")) {
                root = (Parent) loader.load(getClass().getResourceAsStream(FXMLPATH + "cellif.fxml"));
                ifOperatorStage.setTitle("Standard If Statement");
            } else {
                root = (Parent) loader.load(getClass().getResourceAsStream(FXMLPATH + "collif.fxml"));
                ifOperatorStage.setTitle("Column If Statement");
            }

            ifOPeratorScene = new Scene(root);
            ifOperatorStage.setScene(ifOPeratorScene);
            AssertionBuilderUIController.instance.setIfOperatorStage(ifOperatorStage);
            ifOperatorStage.showAndWait();
            AssertionBuilderUIController.instance.setIfOperatorStage(ifOperatorStage);
            AssertionBuilderUIController.instance.setIfOperatorScene(ifOPeratorScene);
            if (AssertionBuilderUIController.instance.isIfOperatorActionReview()) {
                updateParent(callingParent, mapp, operatorVbox, this, counterzz,"If Statement");
                AssertionBuilderUIController.instance.setIfOperatorActionReview(false);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
            IRSDialog.showAlert("ERROR", "Error,Unable to load the Requested UI");
        }
    }
//    @Override
//    public void getComplexUI(RuleBuilderAbstractController callingParent, Map<Integer, HBoxGenerator> mapp, VBox operatorVbox, Integer counterzz) {
//
//        try {
//            AssertionBuilderUIController.instance.setIfOperatorActionReview(false);
//            FXMLLoader loader = new FXMLLoader();
//            Parent root = (Parent) loader.load(getClass().getResourceAsStream(FXMLPATH + "ifoperator.fxml"));
//            ifOperatorStage = new Stage(StageStyle.DECORATED);
//            ifOperatorStage.centerOnScreen();
//            ifOperatorStage.initOwner(IRS.getInstance().getMainStage());
//            ifOperatorStage.setTitle("Select Type of If Statement");
//            ifOPeratorScene = new Scene(root);
//            ifOperatorStage.setScene(ifOPeratorScene);
//            AssertionBuilderUIController.instance.setIfOperatorStage(ifOperatorStage);
//            ifOperatorStage.showAndWait();
//
//            if (AssertionBuilderUIController.instance.isIfOperatorActionReview()) {
//                updateParent(callingParent, mapp, operatorVbox, this, counterzz);
//                AssertionBuilderUIController.instance.setIfOperatorActionReview(false);
//            }
//
//        } catch (IOException ex) {
//            ex.printStackTrace();
//            IRSDialog.showAlert("ERROR", "Error,Unable to load the Requested UI");
//        }
//    }

}
