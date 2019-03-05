/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.controller;

import com.bbt.irs.ui.HBoxGenerator;
import com.bbt.irs.ui.HBoxGenerator4CellIfOperator;
import com.bbt.irs.vo.ColumnandTypeVO;
import com.bbt.irs.vo.RequestVO;
import com.cbn.irs.logicconnector.LogicConnector;
import com.cbn.irs.ruleoperators.RuleOperator;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

/**
 *
 * @author tkola
 */
public class StandardIfController extends ComplexUIController implements Initializable {

    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        counter = 0;
        LHSController lhsController = (LHSController) AssertionBuilderUIController.instance.getLhsController();
        worKCollcode = lhsController.workColl.getSelectionModel().getSelectedItem().getWorkCollectionCode();
        returnCode = lhsController.getReturns().getSelectionModel().getSelectedItem().getReturnCode();
        generateInitialUI4ComplexOperator(worKCollcode, returnCode);
        AssertionBuilderUIController.instance.setCellif(cellif);
        AssertionBuilderUIController.instance.setStandardIfController(this);
        assertionBuilderUIController = AssertionBuilderUIController.instance;
        
    }

    @Override
    public void generateInitialUI4ComplexOperator(String worKCollcode, String returnCode) {
        RequestVO reqvo = new RequestVO();
        reqvo.setWorkCollectionCode(worKCollcode);
        reqvo.setReturnCode(returnCode);
        HBoxGenerator hboxGenerator = new HBoxGenerator4CellIfOperator(lastYLayout, reqvo);
        hboxGenerator.generateCompositeHBox();
        vbox4ifOperator.getChildren().addAll(hboxGenerator.getHboxContainer());
        complexandifCounter = complexandifCounter + 1;
        hboxGenerator.setCounterID(complexandifCounter);
        complexandIfMap.put(complexandifCounter, hboxGenerator);
    }

    @Override
    public void generateInitialUI4SpeciaComplexOperator(String worKCollcode, String returnCode, VBox vbox, Map map, Integer counterz) {
       System.out.println("size "+map.size());
        RequestVO reqvo = new RequestVO();
        reqvo.setWorkCollectionCode(worKCollcode);
        reqvo.setReturnCode(returnCode);
        HBoxGenerator hboxGenerator = new HBoxGenerator4CellIfOperator(lastYLayout, reqvo);
        hboxGenerator.generateCompositeHBox();
        vbox.getChildren().addAll(hboxGenerator.getHboxContainer());
        //counterz = counterz + 1;
        hboxGenerator.setCounterID(counterz);
        map.put(counterz, hboxGenerator);
    }

    @Override
    public boolean validateOperands(Map<Integer, HBoxGenerator> ifmap, HBoxGenerator hboxGenerator,LogicConnector logicConn) {
        boolean result = false;
        HBoxGenerator4CellIfOperator hboxGenerator4CellIfOperator = (HBoxGenerator4CellIfOperator) hboxGenerator;
        String itemcode = (String) hboxGenerator4CellIfOperator.getItemcodeCombo().getSelectionModel().getSelectedItem();
        ColumnandTypeVO columnValue = (ColumnandTypeVO) hboxGenerator4CellIfOperator.getColumnCombo().getSelectionModel().getSelectedItem();
        if (columnValue == null) {
            showError(ifErrorText,"Column can not be blank");
            return result;
        }
        if (itemcode == null || itemcode.isEmpty()) {
            showError(ifErrorText, "Item code can not be blank");
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
    public boolean validateOperands(Map<Integer, HBoxGenerator> thenmap, HBoxGenerator hboxGenerator,RuleOperator ruleOperator) {
        boolean result = false;
        HBoxGenerator4CellIfOperator hboxGenerator4CellIfOperator = (HBoxGenerator4CellIfOperator) hboxGenerator;
        System.out.println("hboxGenerator4CellIfOperator "+hboxGenerator4CellIfOperator);
        String itemcode = (String) hboxGenerator4CellIfOperator.getItemcodeCombo().getSelectionModel().getSelectedItem();
        ColumnandTypeVO columnValue = (ColumnandTypeVO) hboxGenerator4CellIfOperator.getColumnCombo().getSelectionModel().getSelectedItem();
        if (columnValue == null) {
            showError(ifErrorText,"Column can not be blank");
            return result;
        }
        if (itemcode == null || itemcode.isEmpty()) {
            showError(ifErrorText, "Item code can not be blank");
            return result;
        }
        
//        if (!compareOperandsAndLogicConnectorWithLogicOperator(thenmap, hboxGenerator, ruleOperator)) {
//          return result;
//        }
        clearError(ifErrorText);
        result = true;
        return result;
    }
    
    

    public boolean compareOperandsAndLogicConnectorWithLogicOperator(Map<Integer, HBoxGenerator> mapp, HBoxGenerator hboxGenerator, LogicConnector logicConnector) {
        boolean result = true;
        Integer[] object = mapp.keySet().toArray(new Integer[0]);
        object = sort(object);
        String itemCode2Compare = (String) hboxGenerator.getItemcodeCombo().getSelectionModel().getSelectedItem();
        ColumnandTypeVO columnValue = (ColumnandTypeVO) hboxGenerator.getColumnCombo().getSelectionModel().getSelectedItem();
        String fieldName = columnValue.getColumnName();
        int cnt = hboxGenerator.getCounterID();
        String logicConnectorType = logicConnector.getConnectorType();
        for (int i = 1; i < object.length; i++) {
            if (i == cnt) {
                continue;
            }
            HBoxGenerator hboxGen = mapp.get(i);
            String itemCode2CompareWith = (String) hboxGen.getItemcodeCombo().getSelectionModel().getSelectedItem();
            ColumnandTypeVO columnValueWith = (ColumnandTypeVO) hboxGen.getColumnCombo().getSelectionModel().getSelectedItem();
            String fieldNameWith = columnValueWith.getColumnName();
            String logicConnectorTypeWith = hboxGen.getLogicConnector().getConnectorType();
            String logicOperatorTypeWith = hboxGen.getLogicOperator().getLogicOperatorType();
            System.out.println("logicOperatorTypeWith "+logicOperatorTypeWith);
            if (itemCode2CompareWith.equalsIgnoreCase(itemCode2Compare) && (fieldNameWith.equalsIgnoreCase(fieldName))) {
                System.out.println("logicConnectorType "+logicConnectorType);
                if (logicConnectorTypeWith.equalsIgnoreCase(logicConnectorType) && logicOperatorTypeWith.equalsIgnoreCase("and")) {
                    showError(ifErrorText, "You can not select the same Cell with the same logic connector for LogicOperator AND ");
                    result = false;
                    return result;
                }
            }

        }
        return result;
    }
    
    
//    public boolean compareOperandsAndLogicConnectorWithLogicOperator(Map<Integer, HBoxGenerator> mapp, HBoxGenerator hboxGenerator, RuleOperator ruleOperator) {
//        boolean result = true;
//        Integer[] object = mapp.keySet().toArray(new Integer[0]);
//        object = sort(object);
//        String itemCode2Compare = (String) hboxGenerator.getItemcodeCombo().getSelectionModel().getSelectedItem();
//        ColumnandTypeVO columnValue = (ColumnandTypeVO) hboxGenerator.getColumnCombo().getSelectionModel().getSelectedItem();
//        String fieldName = columnValue.getColumnName();
//        int cnt = hboxGenerator.getCounterID();
//        String ruleOperatorType = ruleOperator.getOperatorType();
//        for (int i = 1; i < object.length; i++) {
//            if (i == cnt) {
//                continue;
//            }
//            HBoxGenerator hboxGen = mapp.get(i);
//            String itemCode2CompareWith = (String) hboxGen.getItemcodeCombo().getSelectionModel().getSelectedItem();
//            ColumnandTypeVO columnValueWith = (ColumnandTypeVO) hboxGen.getColumnCombo().getSelectionModel().getSelectedItem();
//            String fieldNameWith = columnValueWith.getColumnName();
//            String logicConnectorTypeWith = hboxGen.getOperator().getOperatorType();
//            System.out.println("logicOperatorTypeWith "+logicConnectorTypeWith);
//            if (itemCode2CompareWith.equalsIgnoreCase(itemCode2Compare) && (fieldNameWith.equalsIgnoreCase(fieldName))) {
//                System.out.println("logicConnectorType "+ruleOperatorType);
//                if (logicConnectorTypeWith.equalsIgnoreCase(ruleOperatorType) && logicOperatorTypeWith.equalsIgnoreCase("and")) {
//                    showError(ifErrorText, "You can not select the same Cell with the same logic connector for LogicOperator AND ");
//                    result = false;
//                    return result;
//                }
//            }
//
//        }
//        return result;
//    }

}
