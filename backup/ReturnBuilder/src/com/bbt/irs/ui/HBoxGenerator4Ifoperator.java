/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.ui;

import com.bbt.irs.controller.AssertionBuilderUIController;
import com.bbt.irs.deploy.IRSDialog;
import static com.bbt.irs.ui.HBoxGenerator.itemCodeAndColumnsVO;
import com.bbt.irs.vo.RequestVO;
import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

/**
 *
 * @author tkola
 */
public class HBoxGenerator4Ifoperator extends HBoxGenerator {

    //dimension 4 HBox controls
//    double labelWidth = 92.0;
//    double labelHeight = 27.0;
//    double comboWidth = 104.0;
//    double comboHeight = 27.0;
//    double operatorWidth = 39.0;
//    double operatorHeight = 25.0;
    double logicButtonWidth = 40.0;
    double logicButtonHeight = 25.0;

    //Declaration of controls for this HBOX
    Label label;
//    protected ComboBox combobox;
//    protected ComboBox itemCodeCombo;
    protected Button logicConnectorButton;
    protected TextField textField;
    protected Button logicOperatorButton;

    public HBoxGenerator4Ifoperator(double lastYLayout, RequestVO requestVO) {
        super(lastYLayout, requestVO);
        label = new Label("Field");
        columnCombo = new ComboBox();
        columnCombo.setId("comboborder");
        itemcodeCombo = new ComboBox();
        itemcodeCombo.setId("comboborder");
        textField = new TextField();
        logicConnectorButton = new Button();
        logicOperatorButton = new Button();
        setControlDimension();

    }

    public final void setControlDimension() {
        label.setPrefSize(labelWidth, labelHeight);
        columnCombo.setPrefSize(comboWidth, comboHeight);
        itemcodeCombo.setPrefSize(comboWidth, comboHeight);
        logicConnectorButton.setPrefSize(logicButtonWidth, logicButtonHeight);
        logicOperatorButton.setPrefSize(logicButtonWidth, logicButtonHeight);
    }

    @Override
    public void generateCompositeHBox() {
        hboxContainer = new HBox();
        hboxContainer.setSpacing(hSpacing);
        hboxContainer.setLayoutX(xLayout);
        hboxContainer.setLayoutY(lastYLayout + ydifferential);
        hboxContainer.getChildren().addAll(label, columnCombo);
        boolean result;
        itemCodeAndColumnsVO = AssertionBuilderUIController.instance.getItemCodeAndColumnsVO();
        if (itemCodeAndColumnsVO != null) {//need to test in case in future if is not restricted to starting operator
            System.out.println("==============itemCodeAndColumnsVO is not null ============");
            columnCombo.setItems(FXCollections.observableList(itemCodeAndColumnsVO.getColNames()));
        } else {
            result = getRequiredData();
            if (result) {
                columnCombo.setItems(FXCollections.observableList(itemCodeAndColumnsVO.getColNames()));
            } else {
                IRSDialog.showAlert(INFO, "The return field can not be populated");
            }
        }
        this.setHboxContainer(hboxContainer);

    }

   
    /**
     * @return the logicConnectorButton
     */
    public Button getLogicConnectorButton() {
        return logicConnectorButton;
    }

    /**
     * @return the logicOperatorButton
     */
    public Button getLogicOperatorButton() {
        return logicOperatorButton;
    }

    /**
     * @return the textField
     */
    public TextField getTextField() {
        return textField;
    }

  

    /**
     * @param logicConnectorButton the logicConnectorButton to set
     */
    public void setLogicConnectorButton(Button logicConnectorButton) {
        this.logicConnectorButton = logicConnectorButton;
    }

    /**
     * @param logicOperatorButton the logicOperatorButton to set
     */
    public void setLogicOperatorButton(Button logicOperatorButton) {
        this.logicOperatorButton = logicOperatorButton;
    }

    /**
     * @param textField the textField to set
     */
    public void setTextField(TextField textField) {
        this.textField = textField;
    }

  
}
