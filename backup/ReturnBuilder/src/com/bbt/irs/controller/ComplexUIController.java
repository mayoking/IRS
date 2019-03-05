/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * or open the template in the editor.
 */
package com.bbt.irs.controller;

import com.bbt.irs.deploy.IRSDialog;
import com.bbt.irs.deploy.Messages;
import static com.bbt.irs.deploy.Messages.FXMLPATH;
import static com.bbt.irs.deploy.Messages.INFO;
import static com.bbt.irs.deploy.Messages.OPENBRACKETNOTPRESENT;
import com.bbt.irs.ui.HBoxGenerator;
import com.bbt.irs.vo.AssertionRuleCompVO;
import com.bbt.irs.vo.ColumnandTypeVO;
import com.cbn.irs.logicconnector.LogicConnector;
import com.cbn.irs.logicconnector.LogicEqualTo;
import com.cbn.irs.logicconnector.LogicGreaterThan;
import com.cbn.irs.logicconnector.LogicGreaterThanOrEqualTo;
import com.cbn.irs.logicconnector.LogicLessThan;
import com.cbn.irs.logicconnector.LogicLessThanOrEqualTo;
import com.cbn.irs.logicconnector.LogicNotEqualTo;
import com.cbn.irs.logicoperator.AndLogicOperator;
import com.cbn.irs.logicoperator.LogicOperator;
import com.cbn.irs.logicoperator.ORLogicOperator;
import com.cbn.irs.logicoperator.ThenLogicOperator;
import com.cbn.irs.ruleoperators.AdditionOperator;
import com.cbn.irs.ruleoperators.CloseBracket;
import com.cbn.irs.ruleoperators.ColumnSum;
import com.cbn.irs.ruleoperators.Division;
import com.cbn.irs.ruleoperators.IfOperator;
import com.cbn.irs.ruleoperators.Multiplication;
import com.cbn.irs.ruleoperators.OpenBracket;
import com.cbn.irs.ruleoperators.RuleOperator;
import java.io.IOException;
import java.util.Map;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author tkola
 */
public abstract class ComplexUIController extends RuleBuilderAbstractController implements Messages {

    public abstract boolean validateOperands(Map<Integer, HBoxGenerator> ifmap, HBoxGenerator hboxGenerator, LogicConnector logicConn);

    public abstract boolean validateOperands(Map<Integer, HBoxGenerator> thenmap, HBoxGenerator hboxGenerator, RuleOperator ruleOperator);
    @FXML
    protected VBox vbox4ifOperator;
    @FXML
    protected VBox thenVbox;
    private String ifExpressionGenMsg;
    public Integer elseCounter;
    String worKCollcode;
    String returnCode;
    private Stage ifOperatorStage;
    String itemCodeColPlaceHolder = "section/item_code[@code = '%s']/%s/text()";
    protected ComplexUIController instancez;
    private AssertionRuleCompVO assertionRulecompVO = new AssertionRuleCompVO();

    public void processLogicConnectorButtons(LogicConnector logicConn) {
        HBoxGenerator hboxGenerator = complexandIfMap.get(complexandIfMap.size());
        System.out.println("hboxGenerator " + hboxGenerator);
        if (hboxGenerator.getLogicConnector() != null) {
            showError(ifErrorText, "Two Logical Connectors cannot follow each other");
            return;
        }
        boolean result = validateOperands(complexandIfMap, hboxGenerator, logicConn);
        if (!result) {
            return;
        }

        clearError(ifErrorText);
        String option = logicConn.getUIOptions();
        if (option.equals(LOGICCONNECTOROPTIONFIELD)) {
            ColumnandTypeVO columnName = ((ColumnandTypeVO) hboxGenerator.getColumnCombo().getSelectionModel().getSelectedItem());
            logicConn.populateLogicConnectorCombos(columnName.getColumnName(), columnName.getDataType());
            logicConn.setLiteral(false);
            if (AssertionBuilderUIController.instance.getItemCodeAndColumnsVO().getTemplateType().equals("1")) {
                hboxGenerator.getHboxContainer().getChildren().addAll(logicConn.getLogicConnectorButton(), logicConn.getItemcodecombo(), logicConn.getColcombo(), logicConn.getClearButton());
            } else {
                hboxGenerator.getHboxContainer().getChildren().addAll(logicConn.getLogicConnectorButton(), logicConn.getColcombo(), logicConn.getClearButton());
            }

        } else if (option.equals(LOGICCONNECTOROPTIONILITERAL)) {
            logicConn.setLiteral(true);
            hboxGenerator.getHboxContainer().getChildren().addAll(logicConn.getLogicConnectorButton(), logicConn.getLogicConnectorTextField(), logicConn.getClearButton());

        }
        logicConn.setHboxGenerator(hboxGenerator);
        hboxGenerator.setLogicConnector(logicConn);
        System.out.println("This is the id to put in the if map " + hboxGenerator.getCounterID());
        complexandIfMap.put(hboxGenerator.getCounterID(), hboxGenerator);//to save any modifcation done on hboxgenerator in this method

    }

    @FXML
    public void logicEqualToConnector() {
        LogicConnector logicConn = new LogicEqualTo();
        processLogicConnectorButtons(logicConn);
    }

    @FXML
    public void logicNotEqualToConnector() {
        LogicConnector logicConn = new LogicNotEqualTo();
        processLogicConnectorButtons(logicConn);
    }

    @FXML
    public void logicGreaterThanConnector() {
        LogicConnector logicConn = new LogicGreaterThan();
        processLogicConnectorButtons(logicConn);
    }

    @FXML
    public void logicLessThanConnector() {
        LogicConnector logicConn = new LogicLessThan();
        processLogicConnectorButtons(logicConn);
    }

    @FXML
    public void logicLessThanOrEqualtoConnector() {
        LogicConnector logicConn = new LogicLessThanOrEqualTo();
        processLogicConnectorButtons(logicConn);
    }

    @FXML
    public void logicGreaterThanOrEqualtoConnector() {
        LogicConnector logicConn = new LogicGreaterThanOrEqualTo();
        processLogicConnectorButtons(logicConn);
    }

    public void processLogicOperatorButton(LogicOperator logicOperator) {
        HBoxGenerator hboxGenerator = complexandIfMap.get(complexandIfMap.size());
        String res = logicOperator.validateLogicConnector(hboxGenerator.getLogicConnector(), thenMap);
        if (res.equalsIgnoreCase("success")) {
            clearError(ifErrorText);
            logicOperator.generateLogicOperatorUI();
            hboxGenerator.setLogicOperator(logicOperator);
            hboxGenerator.getHboxContainer().getChildren().addAll(logicOperator.getLogicOperatorUI(), logicOperator.getClearButton());
            generateInitialUI4ComplexOperator(worKCollcode, returnCode);
            logicOperator.setIfMapp(complexandIfMap);
            logicOperator.setOperatorVbox4LogicOperator(vbox4ifOperator);
        } else {
            showError(ifErrorText, res);
        }
    }

    @FXML
    public void logicOperatorAnd() {
        LogicOperator and = new AndLogicOperator();
        processLogicOperatorButton(and);
    }

    @FXML
    public void logicOperatorOR() {
        LogicOperator or = new ORLogicOperator();
        processLogicOperatorButton(or);
    }

    @FXML
    public void logicOperatorThen() {
        LogicOperator then = new ThenLogicOperator();
        HBoxGenerator hboxGenerator4If = complexandIfMap.get(complexandIfMap.size());
        if (hboxGenerator4If == null) {
            showError(ifErrorText, "No Conditional Statement Found");
        } else {
            LogicConnector lgConn = hboxGenerator4If.getLogicConnector();
            String res = then.validateLogicConnector(lgConn, thenMap);
            if (res.equalsIgnoreCase("success")) {
                clearError(ifErrorText);
                thenCounter = thenCounter + 1;
                generateInitialUI4SpeciaComplexOperator(worKCollcode, returnCode, thenVbox, thenMap, thenCounter);
            } else {
                showError(ifErrorText, res);
            }
        }

    }

    public void generateInitialUI4ComplexOperator(String worKCollcode, String returnCode) {

    }

    public void generateInitialUI4SpeciaComplexOperator(String worKCollcode, String returnCode, VBox vbox, Map map, Integer counterz) {

    }

    @FXML
    public void csum() {
        RuleOperator csum = new ColumnSum();
        HBoxGenerator hboxGenerator = thenMap.get(map.size());
        hboxGenerator.getHboxContainer().getChildren().add(csum.getUI());
        hboxGenerator.setOperator(csum);
    }

    @Override
    protected void showError(Label label, String error) {

        if (label != null) {
            label.setText(error);
            label.setTextFill(Color.web("#800000"));

            FadeTransition ft = new FadeTransition(Duration.millis(500), label);
            ft.setFromValue(1.0);
            ft.setToValue(0.0);
            ft.setAutoReverse(true);
            ft.setCycleCount(4);
        }
    }

    protected void clearError(Label label) {
        if (label != null) {
            label.setText("");
        }
    }

    public void processIfOperator(RuleOperator operator) {
        HBoxGenerator hboxGenerator = thenMap.get(thenMap.size());
        hboxGenerator.setOperator(operator);
        boolean result = validateOperands(thenMap, hboxGenerator, operator);
        if (!result) {
            return;
        }
        clearError(thenErrorText);
        hboxGenerator.getHboxContainer().getChildren().addAll(hboxGenerator.getOperator().getUI());
        thenCounter = thenCounter + 1;
        generateInitialUI4SpeciaComplexOperator(worKCollcode, returnCode, thenVbox, thenMap, thenCounter);

    }

    /**
     *
     * @param e
     */
    @FXML
    @Override
    public void addButton(ActionEvent e) {
        RuleOperator operator = new AdditionOperator();
        processIfOperator(operator);
    }

    @FXML
    @Override
    public void subtractButton(ActionEvent e) {
        RuleOperator operator = new AdditionOperator();
        processIfOperator(operator);
    }

    @FXML
    @Override
    public void multiplicationButton(ActionEvent e) {
        RuleOperator operator = new Multiplication();
        processIfOperator(operator);
    }

    @FXML
    @Override
    public void divisionButton(ActionEvent e) {
        RuleOperator operator = new Division();
        processIfOperator(operator);
    }

    @FXML
    @Override
    public void openBracketButton(ActionEvent e) {
        RuleOperator operator = new OpenBracket();
        processIfOperator(operator);
    }

    @FXML
    @Override
    public void closeBracketButton(ActionEvent e) {
        RuleOperator operator = new CloseBracket();
        boolean result = ((CloseBracket) operator).validateOpenBracket(map, counter);
        if (!result) {
            IRSDialog.showAlert(INFO, OPENBRACKETNOTPRESENT);
            return;
        }
        processIfOperator(operator);

    }

    @FXML
    public void submitaction(ActionEvent e) {
        AssertionRuleCompVO compvoIf = processIf(returnCode, complexandIfMap);
        String ifmsg = compvoIf.getDisPlayMessage();
        AssertionRuleCompVO compvoThen = processThenMap(returnCode, thenMap);
        String thenMsg = compvoThen.getDisPlayMessage();
        String finalMsg = ifmsg + thenMsg + " else 0 ";
        String finalassert = compvoIf.getAssertion() + compvoThen.getAssertion() + " else 0 ";
//        String finalerror = compvoIf.getErrorMessage() + compvoThen.getErrorMessage();
        String finalerror = compvoThen.getErrorMessage() + processIf2(returnCode, complexandIfMap).getErrorMessage();
        AssertionBuilderUIController.instance.getAssertionRuleCompVO().setDisPlayMessage(finalMsg);
        AssertionBuilderUIController.instance.getAssertionRuleCompVO().setAssertion(finalassert);
        AssertionBuilderUIController.instance.getAssertionRuleCompVO().setErrorMessage(finalerror);
        getReviewUI(finalMsg);
        AssertionBuilderUIController.instance.setIfOperatorActionReview(true);
        System.out.println("final message is " + finalMsg);
        System.out.println("final message is " + finalassert);
        System.out.println("final message is " + finalerror);
        AssertionBuilderUIController.instance.setComplexUIController(this);
    }

    public void getReviewUI(String finalMsg) {

        try {

            FXMLLoader loader = new FXMLLoader();
            Parent root = (Parent) loader.load(getClass().getResourceAsStream(FXMLPATH + "ifreviewpage.fxml"));
            IfReviewController cont = (IfReviewController) loader.getController();
            cont.message.setText(finalMsg);
            //cont.message.setTextFill(Color.web("#000000"));
            cont.setOperator(new IfOperator());//need to look for how to set theexisitng if operator
            ifOperatorStage = AssertionBuilderUIController.instance.getIfOperatorStage();// new Stage(StageStyle.DECORATED);//
            ifOperatorStage.centerOnScreen();
            ifOperatorStage.setTitle("Review Page");
            Scene ifOPeratorScene = new Scene(root);
            ifOperatorStage.setScene(ifOPeratorScene);
            AssertionBuilderUIController.instance.setIfOperatorStage(ifOperatorStage);
            System.out.println("show and wait2 released");

        } catch (IOException ex) {
            ex.printStackTrace();
            IRSDialog.showAlert("ERROR", "Error,Unable to load the Requested UI");
        }
    }

    /**
     * @return the ifExpressionGenMsg
     */
    public String getIfExpressionGenMsg() {
        return ifExpressionGenMsg;
    }

    /**
     * @param ifExpressionGenMsg the ifExpressionGenMsg to set
     */
    public void setIfExpressionGenMsg(String ifExpressionGenMsg) {
        this.ifExpressionGenMsg = ifExpressionGenMsg;
    }

    /**
     * @return the instancez
     */
    public ComplexUIController getInstancez() {
        return instancez;
    }

    /**
     * @param instancez the instancez to set
     */
    public void setInstancez(ComplexUIController instancez) {
        this.instancez = instancez;
    }

    /**
     * @return the assertionRulecompVO
     */
    public AssertionRuleCompVO getAssertionRulecompVO() {
        return assertionRulecompVO;
    }

    /**
     * @param assertionRulecompVO the assertionRulecompVO to set
     */
    public void setAssertionRulecompVO(AssertionRuleCompVO assertionRulecompVO) {
        this.assertionRulecompVO = assertionRulecompVO;
    }

}
