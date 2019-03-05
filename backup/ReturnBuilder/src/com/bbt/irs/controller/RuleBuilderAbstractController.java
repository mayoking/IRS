/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.controller;

import com.bbt.irs.deploy.IRSDialog;
import com.bbt.irs.deploy.Messages;
import static com.bbt.irs.deploy.Messages.COLUMNFIELDBLANK;
import static com.bbt.irs.deploy.Messages.INFO;
import static com.bbt.irs.deploy.Messages.ITEMCODEFIELDBLANK;
import static com.bbt.irs.deploy.Messages.OPENBRACKETNOTPRESENT;
import static com.bbt.irs.deploy.Messages.REMOVETHEPRECEDINGOPERANDS;
import com.bbt.irs.entity.TRtnReturns;
import com.bbt.irs.entity.TRtnWorkCollectionDefn;
import com.bbt.irs.ui.HBoxGenerator;
import com.bbt.irs.vo.AssertionRuleCompVO;
import com.bbt.irs.vo.ColumnandTypeVO;
import com.bbt.irs.vo.RequestVO;
import com.cbn.irs.logicoperator.LogicOperator;
import com.cbn.irs.ruleoperators.AdditionOperator;
import com.cbn.irs.ruleoperators.AverageOperator;
import com.cbn.irs.ruleoperators.CloseBracket;
import com.cbn.irs.ruleoperators.Division;
import com.cbn.irs.ruleoperators.IfOperator;
import com.cbn.irs.ruleoperators.MaximumOperator;
import com.cbn.irs.ruleoperators.Multiplication;
import com.cbn.irs.ruleoperators.NoOperator;
import com.cbn.irs.ruleoperators.OpenBracket;
import com.cbn.irs.ruleoperators.RuleOperator;
import com.cbn.irs.ruleoperators.Subtraction;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author tkola
 */
public class RuleBuilderAbstractController implements Messages {

    @FXML
    VBox operatorVbox;
    @FXML
    protected Label ifErrorText;
    @FXML
    protected Label thenErrorText;
    @FXML
    protected AnchorPane cellif;
    @FXML
    protected AnchorPane collif;
    AssertionBuilderUIController assertionBuilderUIController;
    RuleBuilderAbstractController instance;
    double lastYLayout = 263.0;
    Integer counter = 0;
    Integer complexandifCounter = 0;
    Integer thenCounter = 0;
    double ydifferential = 31.0;
    protected HBoxGenerator hboxgenb4ifButtonClicked;
    protected Map<Integer, HBoxGenerator> complexandIfMap = new LinkedHashMap();
    protected Map<Integer, HBoxGenerator> thenMap = new LinkedHashMap();
    protected Map<Integer, HBoxGenerator> elseMap = new LinkedHashMap();
    protected Map<Integer, HBoxGenerator> map = new LinkedHashMap();
    @FXML
    ComboBox<TRtnWorkCollectionDefn> workColl;
    @FXML
    private ComboBox<TRtnReturns> returns;

    @FXML
    public void addButton(ActionEvent e) {
        RuleOperator operator = new AdditionOperator();
        processOperatorUIDisplay(operator);

    }

    public void generateCellORCol() {
        RequestVO requestVO = new RequestVO();
        requestVO.setReturnCode(returns.getSelectionModel().getSelectedItem().getReturnCode());
        System.out.println("requestvo returncode " + requestVO.getReturnCode());
        requestVO.setWorkCollectionCode(workColl.getSelectionModel().getSelectedItem().getWorkCollectionCode());
        HBoxGenerator anchorPaneHBoxGenerator = new HBoxGenerator(lastYLayout, requestVO);
        anchorPaneHBoxGenerator.setLhsControllerInstance(instance);
        anchorPaneHBoxGenerator.generateCompositeHBox();
        operatorVbox.getChildren().add(anchorPaneHBoxGenerator.getHboxContainer());
        counter = counter + 1;
        map.put(counter, anchorPaneHBoxGenerator);
        anchorPaneHBoxGenerator.setCounterID(counter);
        lastYLayout = lastYLayout + ydifferential;
    }

    public void processOperatorUIDisplay(RuleOperator operator) {
        System.out.println("map size after " + map.size());
        HBoxGenerator anchorPaneHBoxGenerator = map.get(map.size());
        anchorPaneHBoxGenerator.setOperator(operator);
        boolean result = validateOperand(anchorPaneHBoxGenerator);
        if (!result) {
            return;
        }
        anchorPaneHBoxGenerator.setOperator(operator);
        anchorPaneHBoxGenerator.getHboxContainer().getChildren().add(anchorPaneHBoxGenerator.getOperator().getUI());
        generateCellORCol();
        addAcction2OperatorButton(anchorPaneHBoxGenerator, operator);

    }

    @FXML
    public void subtractButton(ActionEvent e) {
        RuleOperator operator = new Subtraction();

        processOperatorUIDisplay(operator);

    }

    @FXML
    public void multiplicationButton(ActionEvent e) {
        RuleOperator operator = new Multiplication();

        processOperatorUIDisplay(operator);

    }

    @FXML
    public void divisionButton(ActionEvent e) {
        RuleOperator operator = new Division();

        processOperatorUIDisplay(operator);

    }

    @FXML
    public void openBracketButton(ActionEvent e) {
        RuleOperator operator = new OpenBracket();

        processOperatorUIDisplay(operator);

    }

    @FXML
    public void closeBracketButton(ActionEvent e) {
        RuleOperator operator = new CloseBracket();
        boolean result = ((CloseBracket) operator).validateOpenBracket(map, counter);
        if (!result) {
            IRSDialog.showAlert(INFO, OPENBRACKETNOTPRESENT);
            return;
        }
        processOperatorUIDisplay(operator);

    }

    @FXML
    public void ifButton(ActionEvent e) {
        RuleOperator operator = new IfOperator();
        if (map.isEmpty()) {
            IRSDialog.showAlert(INFO, "WorkCollection and Return need to be selected");
            return;
        }
        System.out.println("ifbutton map size " + map.size());
        hboxgenb4ifButtonClicked = map.get(map.size());
        complexandifCounter = complexandifCounter + 1;
        HBoxGenerator hboxGenerator = complexandIfMap.put(complexandifCounter, hboxgenb4ifButtonClicked);
        hboxgenb4ifButtonClicked.setComplexOperator(operator);
        operator.getComplexUI(this, map, operatorVbox, counter);
    }

    @FXML
    public void avgButton(ActionEvent e) {
        RuleOperator operator = new AverageOperator();
        if (map.isEmpty()) {
            IRSDialog.showAlert(INFO, "WorkCollection and Return need to be selected");
            return;
        }
        System.out.println("avgbutton map size " + map.size());
        hboxgenb4ifButtonClicked = map.get(map.size());
        complexandifCounter = complexandifCounter + 1;
        HBoxGenerator hboxGenerator = complexandIfMap.put(complexandifCounter, hboxgenb4ifButtonClicked);
        hboxgenb4ifButtonClicked.setComplexOperator(operator);
        operator.getComplexUI(this, map, operatorVbox, counter);
    }

    @FXML
    public void maxButton(ActionEvent e) {
        RuleOperator operator = new MaximumOperator();
        if (map.isEmpty()) {
            IRSDialog.showAlert(INFO, "WorkCollection and Return need to be selected");
            return;
        }
        System.out.println("avgbutton map size " + map.size());
        hboxgenb4ifButtonClicked = map.get(map.size());
        complexandifCounter = complexandifCounter + 1;
        HBoxGenerator hboxGenerator = complexandIfMap.put(complexandifCounter, hboxgenb4ifButtonClicked);
        hboxgenb4ifButtonClicked.setComplexOperator(operator);
        operator.getComplexUI(this, map, operatorVbox, counter);
    }

    @FXML
    public void absButton(ActionEvent e) {
        RuleOperator operator = new AverageOperator();
        if (map.isEmpty()) {
            IRSDialog.showAlert(INFO, "WorkCollection and Return need to be selected");
            return;
        }
        System.out.println("avgbutton map size " + map.size());
        hboxgenb4ifButtonClicked = map.get(map.size());
        complexandifCounter = complexandifCounter + 1;
        HBoxGenerator hboxGenerator = complexandIfMap.put(complexandifCounter, hboxgenb4ifButtonClicked);
        hboxgenb4ifButtonClicked.setComplexOperator(operator);
        operator.getComplexUI(this, map, operatorVbox, counter);
    }

    /**
     *
     * @param anchorPaneHBoxGenerator
     * @return
     */
    public boolean validateOperand(HBoxGenerator anchorPaneHBoxGenerator) {
        boolean test = anchorPaneHBoxGenerator.isIsStatic();
        boolean result = false;
        String itemCode = null;
        ColumnandTypeVO columnandTypeVO = null;

        if (!anchorPaneHBoxGenerator.isIsComplexOperator()) {
            if (test) {
                itemCode = (String) anchorPaneHBoxGenerator.getItemcodeCombo().getSelectionModel().getSelectedItem();
            }
            columnandTypeVO = (ColumnandTypeVO) anchorPaneHBoxGenerator.getColumnCombo().getSelectionModel().getSelectedItem();
        }
        String typeofOperand = null;
        if (anchorPaneHBoxGenerator.getOperator() != null) {
            typeofOperand = anchorPaneHBoxGenerator.getOperator().getOperatorType();
        }
        if (typeofOperand != null && (anchorPaneHBoxGenerator.isIsComplexOperator() || typeofOperand.equalsIgnoreCase("if"))) {
        } else {
            System.out.println("itemcode is " + itemCode);
            System.out.println("test is " + test);
            if ((itemCode == null || itemCode.isEmpty()) && test) {
                IRSDialog.showAlert(INFO, ITEMCODEFIELDBLANK);
                return result;
            } else if (columnandTypeVO == null) {
                IRSDialog.showAlert(INFO, COLUMNFIELDBLANK);
                return result;
            }
        }
        result = true;
        return result;
    }

    public void addAcction2OperatorButton(HBoxGenerator anchorPaneHBoxGenerator, RuleOperator operator) {
        operator.getUI().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("being called hmmmm " + anchorPaneHBoxGenerator.getCounterID());
                boolean result = IRSDialog.showConfirmDialog(INFO, REMOVETHEPRECEDINGOPERANDS);
                if (result) {
                    System.out.println("map.size " + map.size());
                    HBox hbox = map.get(anchorPaneHBoxGenerator.getCounterID()).getHboxContainer();
                    int id = anchorPaneHBoxGenerator.getCounterID();
                    map.remove(anchorPaneHBoxGenerator.getCounterID());
                    if (operator.isIsComplexOperator()) {
                        operatorVbox.getChildren().remove(hbox);
                    } else {
                        operatorVbox.getChildren().remove(hbox);
                    }
                    reorderMap(id);
                }

            }
        }
        );

        //LeftHandside code being moved to abstract method
    }

    public void reorderMap(int id) {
        System.out.println("size of the map b4 reorder " + map.size());
        int lastOriginalEntry = 0;
        Set<Integer> ls = map.keySet();
        Integer[] object = map.keySet().toArray(new Integer[0]);
        object = sort(object);
        for (int i = 0; i < object.length; i++) {
            int key = object[i];
            HBoxGenerator entry = map.get(key);
            if (key > id) {
                entry.setCounterID(key - 1);
                map.put(key - 1, entry);
                map.remove(key);
                lastOriginalEntry = key;
            } else if (id == key) {
                IRSDialog.showAlert(INFO, "issue of deletion");
            }

        }
        counter = map.size();
    }

    public Integer[] sort(Integer[] array) {
        int temp;
        for (int i = 1; i < array.length; i++) {
            for (int j = i; j > 0; j--) {
                if (array[j] < array[j - 1]) {
                    temp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = temp;
                }
            }
        }

        return array;
    }

    public void compareOperands(Map<Integer, HBoxGenerator> mapp, HBoxGenerator hboxGenerator) {
        Set<Integer> ls = mapp.keySet();
        Integer[] object = mapp.keySet().toArray(new Integer[0]);
        object = sort(object);
        String itemCode2Compare = (String) hboxGenerator.getItemcodeCombo().getSelectionModel().getSelectedItem();
        ColumnandTypeVO columnValue = (ColumnandTypeVO) hboxGenerator.getColumnCombo().getSelectionModel().getSelectedItem();
        String fieldName = columnValue.getColumnName();
        int cnt = hboxGenerator.getCounterID();
        for (int i = 0; i < object.length; i++) {
            if (i == cnt) {
                continue;
            }
            HBoxGenerator hboxGen = mapp.get(i);
            String itemCode2CompareWith = (String) hboxGen.getItemcodeCombo().getSelectionModel().getSelectedItem();
            ColumnandTypeVO columnValueWith = (ColumnandTypeVO) hboxGen.getColumnCombo().getSelectionModel().getSelectedItem();
            String fieldNameWith = columnValueWith.getColumnName();
            if (itemCode2CompareWith.equalsIgnoreCase(itemCode2Compare) && (fieldNameWith.equalsIgnoreCase(fieldName))) {
                IRSDialog.showAlert(INFO, "You can not select the same cell for conditional statement ");
                return;
            }

        }
    }

    public AssertionRuleCompVO processIf(String returnCode, Map<Integer, HBoxGenerator> ifmap) {
        String placehold = "section/item_code[@code = '%s']/%s/text() %s %s %s";
        String startMsgFrag = "if (";
        String msgFrg = "cell(%s,%s) %s %s %s ";
        String endMsgFrag = " ) then ";
        String error = "Error: condition cell(%s,%s) %s %s %s ";
        String logicConnNotLiteral = "cell(%s,%s) ";
        String logicAssertConnNotLiteral = "section/item_code[@code = '%s']/%s/text() ";
        StringBuilder strMsg = new StringBuilder();
        StringBuilder strError = new StringBuilder();
        StringBuilder assertion = new StringBuilder();
        Integer size = complexandIfMap.size();
        AssertionRuleCompVO compvo = new AssertionRuleCompVO();
        System.out.println("this is the size of if map " + complexandIfMap.size());
        for (Map.Entry<Integer, HBoxGenerator> entry : ifmap.entrySet()) {
            HBoxGenerator hboxgen = entry.getValue();
            String itemCode = (String) hboxgen.getItemcodeCombo().getSelectionModel().getSelectedItem();
            ColumnandTypeVO columnandTypeVO = (ColumnandTypeVO) hboxgen.getColumnCombo().getSelectionModel().getSelectedItem();

            String logicCOnnectorType = hboxgen.getLogicConnector().getConnectorType();
            String textValue;
            String assertTextValue;
            if (hboxgen.getLogicConnector().isLiteral()) {
                textValue = hboxgen.getLogicConnector().getLogicConnectorTextField().getText();
                assertTextValue = textValue;
            } else {
                String itemCodeLogicCon = (String) hboxgen.getLogicConnector().getItemcodecombo().getSelectionModel().getSelectedItem();
                ColumnandTypeVO columnandTypeVOLogicConn = (ColumnandTypeVO) hboxgen.getLogicConnector().getColcombo().getSelectionModel().getSelectedItem();
                textValue = String.format(logicConnNotLiteral, itemCodeLogicCon, columnandTypeVOLogicConn);
                assertTextValue = String.format(logicAssertConnNotLiteral, itemCodeLogicCon, columnandTypeVOLogicConn);
            }

            LogicOperator logicOPerator = hboxgen.getLogicOperator();
            String logicOperatorType = "";

            if (logicOPerator != null) {
                logicOperatorType = " " + logicOPerator.getLogicOperatorType() + " ";
            }
            String msg = String.format(msgFrg, itemCode, columnandTypeVO.getColumnName(), logicCOnnectorType, textValue, logicOperatorType);
            String assertz = String.format(placehold, itemCode, columnandTypeVO.getColumnName(), logicCOnnectorType, assertTextValue, logicOperatorType);
// strMsg.append(msg);
            if (hboxgen.getCounterID() == 1) {
                strMsg.append(startMsgFrag);
                assertion.append(startMsgFrag);
            }
            strMsg.append(msg);
            assertion.append(assertz);
            if (hboxgen.getCounterID() == size) {
                strMsg.append(endMsgFrag);
                assertion.append(endMsgFrag);
            }

        }
        compvo.setDisPlayMessage(strMsg.toString());
        compvo.setAssertion(assertion.toString());
        compvo.setErrorMessage(strMsg.toString());
        return compvo;
    }
    
    public AssertionRuleCompVO processIf2(String returnCode, Map<Integer, HBoxGenerator> ifmap) {
        String placehold = "section/item_code[@code = '%s']/%s/text() %s %s %s";
        String startMsgFrag = "when (";
        String msgFrg = "%s[%s}) %s %s %s ";
        String endMsgFrag = " )  ";
        String error = "Error: condition cell(%s,%s) %s %s %s ";
        String logicConnNotLiteral = "%s[%s]) ";
        String logicAssertConnNotLiteral = "section/item_code[@code = '%s']/%s/text() ";
        StringBuilder strMsg = new StringBuilder();
        StringBuilder strError = new StringBuilder();
        StringBuilder assertion = new StringBuilder();
        Integer size = complexandIfMap.size();
        AssertionRuleCompVO compvo = new AssertionRuleCompVO();
        System.out.println("this is the size of if map " + complexandIfMap.size());
        for (Map.Entry<Integer, HBoxGenerator> entry : ifmap.entrySet()) {
            HBoxGenerator hboxgen = entry.getValue();
            String itemCode = (String) hboxgen.getItemcodeCombo().getSelectionModel().getSelectedItem();
            ColumnandTypeVO columnandTypeVO = (ColumnandTypeVO) hboxgen.getColumnCombo().getSelectionModel().getSelectedItem();

            String logicCOnnectorType = hboxgen.getLogicConnector().getConnectorType();
            String textValue;
            String assertTextValue;
            if (hboxgen.getLogicConnector().isLiteral()) {
                textValue = hboxgen.getLogicConnector().getLogicConnectorTextField().getText();
                assertTextValue = textValue;
            } else {
                String itemCodeLogicCon = (String) hboxgen.getLogicConnector().getItemcodecombo().getSelectionModel().getSelectedItem();
                ColumnandTypeVO columnandTypeVOLogicConn = (ColumnandTypeVO) hboxgen.getLogicConnector().getColcombo().getSelectionModel().getSelectedItem();
                textValue = String.format(logicConnNotLiteral, itemCodeLogicCon, columnandTypeVOLogicConn);
                assertTextValue = String.format(logicAssertConnNotLiteral, itemCodeLogicCon, columnandTypeVOLogicConn);
            }

            LogicOperator logicOPerator = hboxgen.getLogicOperator();
            String logicOperatorType = "";

            if (logicOPerator != null) {
                logicOperatorType = " " + logicOPerator.getLogicOperatorType() + " ";
            }
            String msg = String.format(msgFrg, itemCode, columnandTypeVO.getColumnName(), logicCOnnectorType, textValue, logicOperatorType);
            String assertz = String.format(placehold, itemCode, columnandTypeVO.getColumnName(), logicCOnnectorType, assertTextValue, logicOperatorType);
// strMsg.append(msg);
            if (hboxgen.getCounterID() == 1) {
                strMsg.append(startMsgFrag);
                assertion.append(startMsgFrag);
            }
            strMsg.append(msg);
            assertion.append(assertz);
            if (hboxgen.getCounterID() == size) {
                strMsg.append(endMsgFrag);
                assertion.append(endMsgFrag);
            }

        }
        compvo.setDisPlayMessage(strMsg.toString());
        compvo.setAssertion(assertion.toString());
        compvo.setErrorMessage(strMsg.toString());
        return compvo;
    }

    public AssertionRuleCompVO processThenMap(String returnCode, Map<Integer, HBoxGenerator> mapp) {
        System.out.println("This is the size of the thenmap " + mapp.size());
        StringBuilder message = new StringBuilder();
        StringBuilder strError = new StringBuilder();
        StringBuilder strAssert = new StringBuilder();
        AssertionRuleCompVO compvo = new AssertionRuleCompVO();
        for (Map.Entry<Integer, HBoxGenerator> entry : mapp.entrySet()) {
            HBoxGenerator anchorpaneHBoxGenerator = entry.getValue();

            RuleOperator ruleOperator = anchorpaneHBoxGenerator.getOperator();
            if (ruleOperator == null) {
                RuleOperator operator = new NoOperator();
                anchorpaneHBoxGenerator.setOperator(operator);
            }
            boolean isLast = false;
            if (anchorpaneHBoxGenerator.getCounterID() == mapp.size()) {
                isLast = true;
            }
            boolean test = AssertionBuilderUIController.instance.isIsInter();
            compvo = anchorpaneHBoxGenerator.getOperator().processInfo(returnCode, anchorpaneHBoxGenerator.getCounterID(), anchorpaneHBoxGenerator, false, false, test);

            message.append(compvo.getDisPlayMessage());
            strAssert.append(compvo.getAssertion());
            strError.append(compvo.getErrorMessage());
        }
        compvo.setDisPlayMessage(message.toString());
        compvo.setAssertion(strAssert.toString().replace("<xs:assert test=\"", ""));
        compvo.setErrorMessage(strError.toString().replace("=\"Error:- " +returnCode.toUpperCase()+":", ""));

        return compvo;
    }

    protected void showError(Label label, String error) {
        if (label != null) {
            label.setText(error);
            label.setStyle("-fx-color: red");
        }

    }

    public boolean createRule() {
        boolean result = false;

        return result;
    }

    /**
     * @return the returns
     */
    public ComboBox<TRtnReturns> getReturns() {
        return returns;
    }

}
