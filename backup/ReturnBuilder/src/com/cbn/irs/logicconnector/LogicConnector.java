/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cbn.irs.logicconnector;

import com.bbt.irs.controller.AssertionBuilderUIController;
import com.bbt.irs.dao.MainDAO;
import com.bbt.irs.deploy.IRSDialog;
import com.bbt.irs.deploy.Messages;
import com.bbt.irs.entity.TRbRestrictedField;
import com.bbt.irs.entity.TRbRestrictionData;
import com.bbt.irs.exception.DatabaseException;
import com.bbt.irs.ui.HBoxGenerator;
import static com.bbt.irs.ui.HBoxGenerator.itemCodeAndColumnsVO;
import com.bbt.irs.vo.ColumnandTypeVO;
import com.bbt.irs.vo.ItemCodeAndColumnsVO;
import com.cbn.irs.connector.Connector;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.StageStyle;

/**
 *
 * @author tkola
 */
public abstract class LogicConnector extends Connector implements Messages {

    /**
     * @return the literal
     */
    public boolean isLiteral() {
        return literal;
    }

    /**
     * @param literal the literal to set
     */
    public void setLiteral(boolean literal) {
        this.literal = literal;
    }

    @Override
    public abstract String getConnectorType();

    @Override
    public abstract String getConnectorDescription();

    private Button logicConnectorButton;
    private Button clearButton;
    private TextField logicConnectorTextField;
    private ComboBox itemcodecombo;
    private ComboBox colcombo;
    private HBoxGenerator hboxGenerator;

    //dimension
    double logicButtonWidth = 40.0;
    double logicButtonHeight = 25.0;
    double comboWidth = 104.0;
    double comboHeight = 27.0;

    private boolean literal;
    private boolean isRestrictionCode;

    /**
     *
     * @param hboxGen is usually used to decorate existing operator layout
     * @return
     */
    public abstract HBoxGenerator addLogicOperatorUI(HBoxGenerator hboxGen);

    public String getUIOptions() {
        List<String> choices = new ArrayList<>();
        choices.add(LOGICCONNECTOROPTIONILITERAL);
        choices.add(LOGICCONNECTOROPTIONFIELD);

        ChoiceDialog<String> dialog = new ChoiceDialog<>(LOGICCONNECTOROPTIONILITERAL, choices);
        //dialog.getStyleClass().add("codecomboborder");;
        dialog.getDialogPane().getStyleClass().add("codecomboborder");
        dialog.getDialogPane().getStylesheets().add(getClass().getResource(CSSPATH + "mystyle.css").toExternalForm());
        dialog.initStyle(StageStyle.UTILITY);
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Logic EqualTo Options");
        dialog.setHeaderText("Choose what you want to compare with");
        dialog.setContentText("Select an Option");
        Optional<String> result = dialog.showAndWait();

//        if (result.isPresent()) {
//            System.out.println("Your choice: " + result.get());
//        }
        return result.get();
    }

    public String validateLogicConnector(HBoxGenerator hboxGenerator) {
        String result;
        if (hboxGenerator.getConnector() != null) {
            result = "Two logical connectors can not follow each other";
        } else {
            result = "success";
        }
        return result;
    }

    public final void setControlDimension() {
        logicConnectorButton.setPrefSize(logicButtonWidth, logicButtonHeight);

    }

    public void generateLogicConnectorUI() {
        logicConnectorButton = new Button(getConnectorType());
        setControlDimension();
        logicConnectorTextField = new TextField("Enter the literal");
    }

    /**
     * @return the logicConnectorButton
     */
    public Button getLogicConnectorButton() {
        if (logicConnectorButton == null) {
            logicConnectorButton = new Button(getConnectorType());
        }
        setControlDimension();

        return logicConnectorButton;
    }

    public void populateLogicConnectorCombos(String fieldName, int dataType) {
        ItemCodeAndColumnsVO item = AssertionBuilderUIController.instance.getItemCodeAndColumnsVO();
        List<TRbRestrictedField> restrictedField = null;
        List<TRbRestrictionData> restrictionData = null;
        List<ColumnandTypeVO> restrictionItems = null;
        if (item.getTemplateType().equals("1")) {
            itemcodecombo = new ComboBox(FXCollections.observableArrayList(item.getItemCodes()));
            itemcodecombo.getStyleClass().add("codecomboborder");
            itemcodecombo.setPrefSize(comboWidth, comboHeight);
            itemcodecombo.setMinSize(comboWidth, comboHeight);
        } else {
            String returnCode = AssertionBuilderUIController.instance.getRhsController().getReturns().getSelectionModel().getSelectedItem().getReturnCode();
            try {
                MainDAO maindao = new MainDAO();
                restrictedField = maindao.getTRbRestrictedField4Connector(fieldName, returnCode);
                if (restrictedField != null && !restrictedField.isEmpty()) {
                    System.out.println("restrictedField.get(0).getRestrictionCode().getRestrictionCode() " + restrictedField.get(0).getRestrictionCode().getRestrictionCode());
                    restrictionData = maindao.getTRbRestrictedData(restrictedField.get(0).getRestrictionCode().getRestrictionCode());
                    restrictionItems = new ArrayList();
                    for (TRbRestrictionData t : restrictionData) {

                        ColumnandTypeVO columnandTypeVO = new ColumnandTypeVO();
                        columnandTypeVO.setColumnName(t.getItemCode().toUpperCase());
                        columnandTypeVO.setDataType(1);
                        restrictionItems.add(columnandTypeVO);
                        this.setIsRestrictionCode(true);
                    }
                } else {
                    this.setIsRestrictionCode(false);
                }
            } catch (DatabaseException ex) {
                IRSDialog.showAlert(INFO, ex.getMessage());
                Logger.getLogger(LogicConnector.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (restrictionData != null && !restrictionData.isEmpty()) {
            colcombo = new ComboBox(FXCollections.observableArrayList(restrictionItems));
        } else {
            List<ColumnandTypeVO> ls2 = new ArrayList();
            for (ColumnandTypeVO l : itemCodeAndColumnsVO.getColNames()) {
                if (l.getDataType() == dataType) {
                    ls2.add(l);
                } else {

                }
            }
            colcombo = new ComboBox(FXCollections.observableArrayList(ls2));
        }
        colcombo.getStyleClass().add("codecomboborder");
        colcombo.setPrefSize(comboWidth, comboHeight);
        colcombo.setMinSize(comboWidth, comboHeight);

    }

    /**
     * @return the logicConnectorTextField
     */
    public TextField getLogicConnectorTextField() {
        if (logicConnectorTextField == null) {
            logicConnectorTextField = new TextField();
            logicConnectorTextField.setPromptText("Enter the literal");//
        }
        return logicConnectorTextField;
    }

    /**
     * @param logicConnectorButton the logicConnectorButton to set
     */
    public void setLogicConnectorButton(Button logicConnectorButton) {
        this.logicConnectorButton = logicConnectorButton;
    }

    /**
     * @param logicConnectorTextField the logicConnectorTextField to set
     */
    public void setLogicConnectorTextField(TextField logicConnectorTextField) {
        this.logicConnectorTextField = logicConnectorTextField;
    }

    /**
     * @return the itemcodecombo
     */
    public ComboBox getItemcodecombo() {
        return itemcodecombo;
    }

    /**
     * @return the colcombo
     */
    public ComboBox getColcombo() {
        return colcombo;
    }

    /**
     * @param itemcodecombo the itemcodecombo to set
     */
    public void setItemcodecombo(ComboBox itemcodecombo) {
        this.itemcodecombo = itemcodecombo;
    }

    /**
     * @param colcombo the colcombo to set
     */
    public void setColcombo(ComboBox colcombo) {
        this.colcombo = colcombo;
    }

    /**
     * @return the clearButton
     */
    public Button getClearButton() {
        if (clearButton == null) {

            clearButton = new Button("clr");
            setClearButton(clearButton);
            clearButton.setPrefSize(logicButtonWidth, logicButtonHeight);
            clearButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                    if (logicConnectorButton != null) {
                        hboxGenerator.getHboxContainer().getChildren().remove(logicConnectorButton);
                    }
                    if (logicConnectorTextField != null) {
                        hboxGenerator.getHboxContainer().getChildren().remove(logicConnectorTextField);
                    }
                    if (itemcodecombo != null) {
                        hboxGenerator.getHboxContainer().getChildren().remove(itemcodecombo);
                    }
                    if (colcombo != null) {
                        hboxGenerator.getHboxContainer().getChildren().remove(colcombo);
                    }
                    hboxGenerator.setLogicConnector(null);
                    hboxGenerator.getHboxContainer().getChildren().remove(clearButton);

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
     * @return the hboxGenerator
     */
    public HBoxGenerator getHboxGenerator() {
        return hboxGenerator;
    }

    /**
     * @param hboxGenerator the hboxGenerator to set
     */
    public void setHboxGenerator(HBoxGenerator hboxGenerator) {
        this.hboxGenerator = hboxGenerator;
    }

    /**
     * @return the isRestrictionCode
     */
    public boolean isIsRestrictionCode() {
        return isRestrictionCode;
    }

    /**
     * @param isRestrictionCode the isRestrictionCode to set
     */
    public void setIsRestrictionCode(boolean isRestrictionCode) {
        this.isRestrictionCode = isRestrictionCode;
    }

}
