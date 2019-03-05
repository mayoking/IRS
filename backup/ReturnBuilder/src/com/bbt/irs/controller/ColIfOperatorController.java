/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.controller;

import com.bbt.irs.ui.HBoxGenerator;
import com.bbt.irs.ui.HBoxGenerator4CollumnIfOpearator;
import com.bbt.irs.vo.ColumnandTypeVO;
import com.bbt.irs.vo.RequestVO;
import com.cbn.irs.logicconnector.LogicConnector;
import com.cbn.irs.ruleoperators.RuleOperator;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

/**
 *
 * @author tkola
 */
public class ColIfOperatorController extends ComplexUIController implements Initializable {

    Map<Integer, String> logicConnsValue;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        counter = 0;
        LHSController lhsController = (LHSController) AssertionBuilderUIController.instance.getLhsController();
        worKCollcode = lhsController.workColl.getSelectionModel().getSelectedItem().getWorkCollectionCode();
        returnCode = lhsController.getReturns().getSelectionModel().getSelectedItem().getReturnCode();
        generateInitialUI4ComplexOperator(worKCollcode, returnCode);
        instancez = (ComplexUIController) this;
    }

    @Override
    public void generateInitialUI4ComplexOperator(String worKCollcode, String returnCode) {
        RequestVO reqvo = new RequestVO();
        reqvo.setWorkCollectionCode(worKCollcode);
        reqvo.setReturnCode(returnCode);
        HBoxGenerator hboxGenerator = new HBoxGenerator4CollumnIfOpearator(lastYLayout, reqvo);
        hboxGenerator.generateCompositeHBox();
        vbox4ifOperator.getChildren().addAll(hboxGenerator.getHboxContainer());
        complexandifCounter = complexandifCounter + 1;
        hboxGenerator.setCounterID(complexandifCounter);
        complexandIfMap.put(complexandifCounter, hboxGenerator);
    }

    @Override
    public void generateInitialUI4SpeciaComplexOperator(String worKCollcode, String returnCode, VBox vbox, Map map, Integer counterz) {
        System.out.println("size " + map.size());
        RequestVO reqvo = new RequestVO();
        reqvo.setWorkCollectionCode(worKCollcode);
        reqvo.setReturnCode(returnCode);
        HBoxGenerator hboxGenerator = new HBoxGenerator4CollumnIfOpearator(lastYLayout, reqvo);
        hboxGenerator.generateCompositeHBox();
        vbox.getChildren().addAll(hboxGenerator.getHboxContainer());
        //counterz = counterz + 1;
        hboxGenerator.setCounterID(counterz);
        map.put(counterz, hboxGenerator);
    }

    @Override
    public boolean validateOperands(Map<Integer, HBoxGenerator> ifmap, HBoxGenerator hboxGenerator, LogicConnector logicConn) {
        System.out.println("inside validateOperands");
        boolean result = false;
        HBoxGenerator4CollumnIfOpearator hboxGenerator4CellIfOperator = (HBoxGenerator4CollumnIfOpearator) hboxGenerator;
        // String itemcode = (String) hboxGenerator4CollIfOperator.getItemcodeCombo().getSelectionModel().getSelectedItem();
        ColumnandTypeVO columnValue = (ColumnandTypeVO) hboxGenerator4CellIfOperator.getColumnCombo().getSelectionModel().getSelectedItem();
        System.out.println("columnValue " + columnValue);
        if (columnValue == null) {
            showError(ifErrorText, "Column can not be blank");
            return result;
        }

        if (!compareOperandsAndLogicConnectorWithLogicOperator(ifmap, hboxGenerator, logicConn)) {
            return result;
        }
        clearError(ifErrorText);
        result = true;
        return result;
    }

    @Override
    public boolean validateOperands(Map<Integer, HBoxGenerator> thenmap, HBoxGenerator hboxGenerator, RuleOperator ruleOperator) {
        boolean result = false;
        HBoxGenerator4CollumnIfOpearator hboxGenerator4CollIfOperator = (HBoxGenerator4CollumnIfOpearator) hboxGenerator;
        System.out.println("HBoxGenerator4CollumnIfOpearator " + hboxGenerator4CollIfOperator);
        //String itemcode = (String) hboxGenerator4CollIfOperator.getItemcodeCombo().getSelectionModel().getSelectedItem();
        ColumnandTypeVO columnValue = (ColumnandTypeVO) hboxGenerator4CollIfOperator.getColumnCombo().getSelectionModel().getSelectedItem();
        if (columnValue == null) {
            showError(ifErrorText, "Column can not be blank");
            return result;
        }

        clearError(ifErrorText);
        result = true;
        return result;
    }

    public boolean compareOperandsAndLogicConnectorWithLogicOperator(Map<Integer, HBoxGenerator> mapp, HBoxGenerator hboxGenerator, LogicConnector logicConnector) {
        boolean result = true;
        Integer[] object = mapp.keySet().toArray(new Integer[0]);
        object = sort(object);
        //String itemCode2Compare = (String) hboxGenerator.getItemcodeCombo().getSelectionModel().getSelectedItem();
        ColumnandTypeVO columnValue = (ColumnandTypeVO) hboxGenerator.getColumnCombo().getSelectionModel().getSelectedItem();
        String fieldName = columnValue.getColumnName();
        int cnt = hboxGenerator.getCounterID();
        String logicConnectorType = logicConnector.getConnectorType();
        for (int i = 1; i < object.length; i++) {
            if (i == cnt) {
                continue;
            }
            HBoxGenerator hboxGen = mapp.get(i);
            //String itemCode2CompareWith = (String) hboxGen.getItemcodeCombo().getSelectionModel().getSelectedItem();
            ColumnandTypeVO columnValueWith = (ColumnandTypeVO) hboxGen.getColumnCombo().getSelectionModel().getSelectedItem();
            String fieldNameWith = columnValueWith.getColumnName();
            String logicConnectorTypeWith = hboxGen.getLogicConnector().getConnectorType();
            String logicOperatorTypeWith = hboxGen.getLogicOperator().getLogicOperatorType();
            System.out.println("logicOperatorTypeWith " + logicOperatorTypeWith);
//            if ((fieldNameWith.equalsIgnoreCase(fieldName))) {
//                System.out.println("logicConnectorType " + logicConnectorType);
//                if (logicConnectorTypeWith.equalsIgnoreCase(logicConnectorType) && logicOperatorTypeWith.equalsIgnoreCase("and")) {
//                    showError(ifErrorText, "You can not select the same Cell with the same logic connector for LogicOperator AND ");
//                    result = false;
//                    return result;
//                }
//            }

        }
        return result;
    }

    public String generateAssertionColIf(Map<Integer, HBoxGenerator> ifmapp, Map<Integer, HBoxGenerator> thenmapp) {
        int cnter = ifmapp.size();
        HBoxGenerator4CollumnIfOpearator hboxgen = (HBoxGenerator4CollumnIfOpearator) thenmapp.get(1);
        ColumnandTypeVO columnandTypeVO = (ColumnandTypeVO) hboxgen.getColumnCombo().getSelectionModel().getSelectedItem();
        StringBuilder assertonBuilder = new StringBuilder();
        String returncode = AssertionBuilderUIController.instance.getItemCodeAndColumnsVO().getReturnCode();
        String startcolIf = "sum(%s/section/item_code/%s";
        String repeatedPlaceHolder = "[parent::item_code[";
        String firstIntermediaryClosure = "%s/text()";
        String repeatedConnectclosure = "/text()";
        String thenStatementClosure = "/text())";
        logicConnsValue = new HashMap();
        assertonBuilder.append(String.format(startcolIf, returncode, columnandTypeVO.getColumnName()));
        for (int i = 0; i < ifmapp.size(); i++) {
            HBoxGenerator4CollumnIfOpearator hboxgenIf = (HBoxGenerator4CollumnIfOpearator) ifmapp.get(i + 1);
            ColumnandTypeVO columnandTypeVOIf = (ColumnandTypeVO) hboxgenIf.getColumnCombo().getSelectionModel().getSelectedItem();
            ColumnandTypeVO logConColumnandTypeVOIf = null;
            repeatedConnectclosure = repeatedConnectclosure + hboxgenIf.getLogicConnector().getConnectorType()+"'%s']]";
            firstIntermediaryClosure = firstIntermediaryClosure + hboxgenIf.getLogicConnector().getConnectorType()+"'%s']]";
            if (hboxgenIf.getLogicConnector().isLiteral()) {
                logConColumnandTypeVOIf = new ColumnandTypeVO();
                logConColumnandTypeVOIf.setColumnName(hboxgenIf.getLogicConnector().getLogicConnectorTextField().getText());
                logConColumnandTypeVOIf.setDataType(1);
            } else {
                logConColumnandTypeVOIf = (ColumnandTypeVO) hboxgenIf.getLogicConnector().getColcombo().getSelectionModel().getSelectedItem();
            }
            logicConnsValue.put(i, logConColumnandTypeVOIf.getColumnName());
            assertonBuilder.append(repeatedPlaceHolder);
            if (i == (ifmapp.size() - 1)) {
                String lastLoop = processLastIf(columnandTypeVOIf.getColumnName(), logConColumnandTypeVOIf.getColumnName(), firstIntermediaryClosure, repeatedConnectclosure, thenStatementClosure, ifmapp.size());
                assertonBuilder.append(lastLoop);
            } else {
                assertonBuilder.append(columnandTypeVOIf.getColumnName());
            }

        }
        return assertonBuilder.toString();
    }

    @FXML
    @Override
    public void submitaction(ActionEvent e) {

        String finalassert = generateAssertionColIf(complexandIfMap, thenMap);
        String finalMsg = generateMessage(complexandIfMap, thenMap);
        String finalerror = finalMsg;//String.format("sum[%s] when %s=%s ", AssertionBuilderUIController.instance.getItemCodeAndColumnsVO().getReturnCode());
        AssertionBuilderUIController.instance.getAssertionRuleCompVO().setDisPlayMessage(finalMsg);
        AssertionBuilderUIController.instance.getAssertionRuleCompVO().setAssertion(finalassert);
        AssertionBuilderUIController.instance.getAssertionRuleCompVO().setErrorMessage(finalMsg);

        getAssertionRulecompVO().setDisPlayMessage(finalMsg);
        getAssertionRulecompVO().setAssertion(finalassert);
        getAssertionRulecompVO().setErrorMessage(finalerror + finalMsg);

        getReviewUI(finalMsg);
        AssertionBuilderUIController.instance.setIfOperatorActionReview(true);
        System.out.println("final message is " + finalMsg);
        System.out.println("final message is " + finalassert);
        System.out.println("final message is " + finalerror + finalMsg);
        AssertionBuilderUIController.instance.setComplexUIController(this);
    }

    public String generateMessageold(Map<Integer, HBoxGenerator> ifmapp, Map<Integer, HBoxGenerator> thenmapp) {
        HBoxGenerator4CollumnIfOpearator hboxgen = (HBoxGenerator4CollumnIfOpearator) thenmapp.get(1);
        ColumnandTypeVO columnandTypeVO = (ColumnandTypeVO) hboxgen.getColumnCombo().getSelectionModel().getSelectedItem();
        StringBuilder assertonMessageBuilder = new StringBuilder();
        String startMessage = "if ";
        String repeatedmessagePlaceHolder = "%s is equal to %s ";
        String appendEndMessage = "then do summation of %s ";
        String operatorMessage = "and ";
        assertonMessageBuilder.append(startMessage);
        String returncode = AssertionBuilderUIController.instance.getItemCodeAndColumnsVO().getReturnCode();
        for (int i = 0; i < ifmapp.size(); i++) {
            HBoxGenerator4CollumnIfOpearator hboxgenIf = (HBoxGenerator4CollumnIfOpearator) ifmapp.get(i + 1);
            ColumnandTypeVO columnandTypeVOIf = (ColumnandTypeVO) hboxgenIf.getColumnCombo().getSelectionModel().getSelectedItem();
            System.out.println("hboxgenIf.getLogicConnector().getColcombo() " + hboxgenIf.getLogicConnector());
            ColumnandTypeVO logConColumnandTypeVOIf = (ColumnandTypeVO) hboxgenIf.getLogicConnector().getColcombo().getSelectionModel().getSelectedItem();

            if (i == ifmapp.size() - 1) {
                assertonMessageBuilder.append(String.format(repeatedmessagePlaceHolder, columnandTypeVOIf.getColumnName(), logConColumnandTypeVOIf.getColumnName()));
                assertonMessageBuilder.append(String.format(appendEndMessage, columnandTypeVO.getColumnName()));
            } else {
                assertonMessageBuilder.append(String.format(repeatedmessagePlaceHolder, columnandTypeVOIf.getColumnName(), logConColumnandTypeVOIf.getColumnName()));
                assertonMessageBuilder.append(operatorMessage);
            }

        }
        return assertonMessageBuilder.toString();
    }

    public String generateMessage(Map<Integer, HBoxGenerator> ifmapp, Map<Integer, HBoxGenerator> thenmapp) {
        HBoxGenerator4CollumnIfOpearator hboxgen = (HBoxGenerator4CollumnIfOpearator) thenmapp.get(1);
        ColumnandTypeVO columnandTypeVO = (ColumnandTypeVO) hboxgen.getColumnCombo().getSelectionModel().getSelectedItem();
        StringBuilder assertonErrorMessageBuilder = new StringBuilder();
        assertonErrorMessageBuilder.append(String.format("sum of [%s] when ", columnandTypeVO.getColumnName()));
        String operatorMessage = "and ";
        String repeatedmessagePlaceHolder = " Field [%s] ";
        for (int i = 0; i < ifmapp.size(); i++) {
            HBoxGenerator4CollumnIfOpearator hboxgenIf = (HBoxGenerator4CollumnIfOpearator) ifmapp.get(i + 1);
            ColumnandTypeVO columnandTypeVOIf = (ColumnandTypeVO) hboxgenIf.getColumnCombo().getSelectionModel().getSelectedItem();
            ColumnandTypeVO logConColumnandTypeVOIf = null;
            if (hboxgenIf.getLogicConnector().isLiteral()) {
                logConColumnandTypeVOIf = new ColumnandTypeVO();
                logConColumnandTypeVOIf.setColumnName(hboxgenIf.getLogicConnector().getLogicConnectorTextField().getText());
                logConColumnandTypeVOIf.setDataType(1);
            } else {
                logConColumnandTypeVOIf = (ColumnandTypeVO) hboxgenIf.getLogicConnector().getColcombo().getSelectionModel().getSelectedItem();
            }
            repeatedmessagePlaceHolder =repeatedmessagePlaceHolder +hboxgenIf.getLogicConnector().getConnectorDescription() + " %s";
            if (i == ifmapp.size() - 1) {
                assertonErrorMessageBuilder.append(String.format(repeatedmessagePlaceHolder, columnandTypeVOIf.getColumnName(), logConColumnandTypeVOIf.getColumnName()));
                //assertonErrorMessageBuilder.append(String.format(appendEndMessage, columnandTypeVO.getColumnName()));
            } else {
                assertonErrorMessageBuilder.append(String.format(repeatedmessagePlaceHolder, columnandTypeVOIf.getColumnName(), logConColumnandTypeVOIf.getColumnName()));
                assertonErrorMessageBuilder.append(operatorMessage);
            }
        }
        return assertonErrorMessageBuilder.toString();
    }

    public String processLastIf(String comb, String logicalComb, String intermediaryClosure, String repeatedConnectclosure, String thenStatementClosure, int ifcounter) {
        StringBuilder str = new StringBuilder();
        str.append(String.format(intermediaryClosure, comb, logicalComb));
        int testConcounter = ifcounter - 2; //the map to use it for start from 0
        for (int i = 0; i < ifcounter; i++) {
            if (i == ifcounter - 1) {
                str.append(thenStatementClosure);
            } else {
                str.append(String.format(repeatedConnectclosure, logicConnsValue.get(testConcounter)));
                testConcounter = testConcounter - 1;
            }
        }

        return str.toString();
    }

}
