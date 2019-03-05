/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.controller;

import com.bbt.irs.ui.HBoxGenerator;
import com.bbt.irs.ui.HBoxGenerator4AbsoluteOperator;
import com.bbt.irs.vo.AssertionRuleCompVO;
import com.bbt.irs.vo.ColumnandTypeVO;
import com.bbt.irs.vo.RequestVO;
import com.cbn.irs.logicconnector.LogicConnector;
import com.cbn.irs.ruleoperators.RuleOperator;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 *
 * @author tkola
 */
public class AbsoluteController extends ComplexUIController implements Initializable{

  String workColCode;
    String returnCode;

    String itemCodecolPlaceHolder = "section/item_code/%s/text()";
    String interItemCodeColPlaceHolder = "%s/section/item_code/%s/text()";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        generateInitialUI4ComplexOperator(workColCode, returnCode);
    }

    @Override
    public boolean validateOperands(Map<Integer, HBoxGenerator> ifmap, HBoxGenerator hboxGenerator, LogicConnector logicConn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean validateOperands(Map<Integer, HBoxGenerator> thenmap, HBoxGenerator hboxGenerator, RuleOperator ruleOperator) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void validateAverageOperands() {

    }

    @Override
    public void generateInitialUI4ComplexOperator(String worKCollcode, String returnCode) {
        this.returnCode = returnCode;
        this.workColCode = worKCollcode;
        RequestVO reqvo = new RequestVO();
        reqvo.setWorkCollectionCode(worKCollcode);
        reqvo.setReturnCode(returnCode);
        HBoxGenerator hboxGenerator = new HBoxGenerator4AbsoluteOperator(lastYLayout, reqvo);
        hboxGenerator.generateCompositeHBox();
        vbox4ifOperator.getChildren().addAll(hboxGenerator.getHboxContainer());
        complexandifCounter = complexandifCounter + 1;
        hboxGenerator.setCounterID(complexandifCounter);
        complexandIfMap.put(complexandifCounter, hboxGenerator);
    }

    @FXML
    public void addOperands() {
        generateInitialUI4ComplexOperator(workColCode, returnCode);
    }

    @Override
    public void submitaction(ActionEvent e) {
        AssertionRuleCompVO compvo = new AssertionRuleCompVO();
        boolean isInter = AssertionBuilderUIController.instance.isIsInter();
        HBoxGenerator hboxgen = complexandIfMap.get(1);
        String assertion = null;
        ColumnandTypeVO colunName = (ColumnandTypeVO) hboxgen.getColumnCombo().getSelectionModel().getSelectedItem();
        if (isInter) {
             assertion = "abs(" + String.format(itemCodecolPlaceHolder, colunName.getColumnName()) + ")";
        } else {
             assertion = "abs(" + String.format(interItemCodeColPlaceHolder,returnCode, colunName.getColumnName()) + ")";
        }
        String message = "absolute value of field[" + colunName.getColumnName() + "]";
//         String finalMsg = ifmsg + thenMsg + " else 0 ";
//        String finalassert = compvoIf.getAssertion() + compvoThen.getAssertion() + " else 0 ";
//        String finalerror = compvoIf.getErrorMessage() + compvoThen.getErrorMessage();
        AssertionBuilderUIController.instance.getAssertionRuleCompVO().setDisPlayMessage(message);
        AssertionBuilderUIController.instance.getAssertionRuleCompVO().setAssertion(assertion);
        AssertionBuilderUIController.instance.getAssertionRuleCompVO().setErrorMessage(message);
        getReviewUI(message);
        AssertionBuilderUIController.instance.setIfOperatorActionReview(true);
        AssertionBuilderUIController.instance.setComplexUIController(this);
    }
}
