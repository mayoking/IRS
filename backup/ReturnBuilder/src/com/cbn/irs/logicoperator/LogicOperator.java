/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cbn.irs.logicoperator;

import com.bbt.irs.controller.AssertionBuilderUIController;
import com.bbt.irs.deploy.Messages;
import com.bbt.irs.ui.HBoxGenerator;
import com.bbt.irs.vo.ColumnandTypeVO;
import com.cbn.irs.logicconnector.LogicConnector;
import java.util.Map;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

/**
 *
 * @author tkola
 */
public abstract class LogicOperator implements Messages {

    private VBox operatorVbox4LogicOperator;

    public abstract String getLogicOperatorType();

    private Button logicOperatorUI;
    private Button clearButton;
    private LogicConnector connector;
    private Map<Integer, HBoxGenerator> ifMapp;

    //dimension
    double logicButtonWidth = 40.0;
    double logicButtonHeight = 25.0;

    public String validateLogicConnector(LogicConnector connector, Map thenmapp) {
        if (connector == null) {
            //showError(thenErrorText, "No value to compare");
            return "No Logic Connector Available";
        }
        this.connector = connector;
        if (!thenmapp.isEmpty()) {
            //IRSDialog.showAlert(INFO, "AND,OR and THEN Logical Operator can not be clicked after THEN Logic Operator");
            return "AND,OR and THEN Logical Operator can not be clicked after THEN Logic Operator";
        }
        String value;
        boolean test = connector.isLiteral();
        if (!test) {
            String itemCode = null;
            ColumnandTypeVO columnandTypeVO;
            String tempType = AssertionBuilderUIController.instance.getItemCodeAndColumnsVO().getTemplateType();
            if (tempType.equals("1") && !connector.isIsRestrictionCode()) {
                itemCode = (String) connector.getItemcodecombo().getSelectionModel().getSelectedItem();
            }
            columnandTypeVO = (ColumnandTypeVO) connector.getColcombo().getSelectionModel().getSelectedItem();
            if (itemCode != null && columnandTypeVO != null) {
                HBoxGenerator hboxgen = connector.getHboxGenerator();
                String itemCodeHbox = (String) hboxgen.getItemcodeCombo().getSelectionModel().getSelectedItem();
                ColumnandTypeVO columnandTypeVOHbox = (ColumnandTypeVO) hboxgen.getColumnCombo().getSelectionModel().getSelectedItem();
                if (itemCode.equalsIgnoreCase(itemCodeHbox) && columnandTypeVO.getColumnName().equals(columnandTypeVOHbox.getColumnName())) {
                    return "same cell can not be comapred in a conditional if statement";
                } else {
                    return "success";
                }
            } else if ((itemCode == null && columnandTypeVO != null) && (connector.isIsRestrictionCode() ||!tempType.equals("1"))) {
                HBoxGenerator hboxgen = connector.getHboxGenerator();
                ColumnandTypeVO columnandTypeVOHbox = (ColumnandTypeVO) hboxgen.getColumnCombo().getSelectionModel().getSelectedItem();
                if (columnandTypeVO.getColumnName().equals(columnandTypeVOHbox.getColumnName())) {
                    return "same cell can not be compared in a conditional if statement";
                } else {
                    value = "success";
                }
            } else {
                return "itemcode/column can not be blank";
            }
        } else {
            value = connector.getLogicConnectorTextField().getText();
        }

        if (value == null || value.isEmpty()) {
            //IRSDialog.showAlert(INFO, "No value to compare");
            return "The literal text field can not be blank";
        }

        return "success";
    }

    public void generateLogicOperatorUI() {
        logicOperatorUI = new Button(getLogicOperatorType());
        logicOperatorUI.setPrefSize(logicButtonWidth, logicButtonHeight);
        System.out.println("connector.getClearButton() " + connector);
        //connector.getHboxGenerator().getHboxContainer().getChildren().remove(connector.getClearButton());
    }

    /**
     *
     * @return the clearButton
     */
    public Button getClearButton() {
        if (clearButton == null) {
            clearButton = new Button("clr");
            clearButton.setPrefSize(logicButtonWidth, logicButtonHeight);
            clearButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    //HBoxGenerator hbox =ifMapp.get(ifMapp.size());
                    operatorVbox4LogicOperator.getChildren().remove(ifMapp.size());
                    ifMapp.remove(ifMapp.size());
                    if (connector.getLogicConnectorButton() != null) {
                        connector.getHboxGenerator().getHboxContainer().getChildren().remove(connector.getLogicConnectorButton());
                    }
                    if (connector.getLogicConnectorTextField() != null) {
                        connector.getHboxGenerator().getHboxContainer().getChildren().remove(connector.getLogicConnectorTextField());
                    }
                    if (connector.getItemcodecombo() != null) {
                        connector.getHboxGenerator().getHboxContainer().getChildren().remove(connector.getItemcodecombo());
                    }
                    if (connector.getColcombo() != null) {
                        connector.getHboxGenerator().getHboxContainer().getChildren().remove(connector.getColcombo());
                    }
                    if (logicOperatorUI != null) {
                        connector.getHboxGenerator().getHboxContainer().getChildren().remove(logicOperatorUI);
                    }
                    // connector.getHboxGenerator().setLogicConnector(null);
                    connector.getHboxGenerator().getHboxContainer().getChildren().remove(clearButton);

                }

            });
        }
        return clearButton;
    }

    /**
     * @param clearButton the clearButton to set
     */
    public void setClearButton(Button clearButton) {
        this.clearButton = clearButton;
    }

    /**
     *
     * @return the logicOperatorUI
     */
    public Button getLogicOperatorUI() {
        if (connector.getClearButton() != null) {
            System.out.println("removing logic connector button");
            connector.getHboxGenerator().getHboxContainer().getChildren().remove(connector.getClearButton());
        }
        return logicOperatorUI;
    }

    /**
     * @param logicOperatorUI the logicOperatorUI to set
     */
    public void setLogicOperatorUI(Button logicOperatorUI) {
        this.logicOperatorUI = logicOperatorUI;
    }

    /**
     * @return the ifMapp
     */
    public Map<Integer, HBoxGenerator> getIfMapp() {
        return ifMapp;
    }

    /**
     * @param ifMapp the ifMapp to set
     */
    public void setIfMapp(Map<Integer, HBoxGenerator> ifMapp) {
        this.ifMapp = ifMapp;
    }

    /**
     * @return the operatorVbox4LogicOperator
     */
    public VBox getOperatorVbox4LogicOperator() {
        return operatorVbox4LogicOperator;
    }

    /**
     * @param operatorVbox4LogicOperator the operatorVbox4LogicOperator to set
     */
    public void setOperatorVbox4LogicOperator(VBox operatorVbox4LogicOperator) {
        this.operatorVbox4LogicOperator = operatorVbox4LogicOperator;
    }

}
