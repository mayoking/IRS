/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.ui;

import com.bbt.irs.controller.AssertionBuilderUIController;
import com.bbt.irs.controller.ComplexUIController;
import com.bbt.irs.controller.RuleBuilderAbstractController;
import com.bbt.irs.deploy.ErrorNameDesc;
import com.bbt.irs.deploy.IRSDialog;
import com.bbt.irs.deploy.Messages;
import com.bbt.irs.exception.WebservicesException;
import com.bbt.irs.vo.AssertionRuleCompVO;
import com.bbt.irs.vo.ColumnandTypeVO;
import com.bbt.irs.vo.ItemCodeAndColumnsVO;
import com.bbt.irs.vo.RequestVO;
import com.cbn.irs.assertion.AssertionComboLoader;
import com.cbn.irs.connector.Connector;
import com.cbn.irs.logicconnector.LogicConnector;
import com.cbn.irs.logicoperator.LogicOperator;
import com.cbn.irs.ruleoperators.RuleOperator;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

/**
 *
 * @author tkola
 */
public class HBoxGenerator implements ErrorNameDesc, Messages {

    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(HBoxGenerator.class);
    HBox hboxContainer;
    private Label itemcodeLabel;
    private Label columnLabel;
    protected ComboBox<ColumnandTypeVO> columnCombo;
    protected ComboBox<String> itemcodeCombo;
    private Button hboxCompButton;
    private static HBoxGenerator instance;
    private boolean isComplexOperator = false;
    private Scene complexUIScene;
    private AnchorPane complexUIAnchorPane;
    private ComplexUIController complexUIController;
    private Stage complexuiStage;
    private RuleOperator operator;
    private Connector connector;
    private LogicConnector logicConnector;
    private LogicOperator logicOperator;
    private RuleOperator complexOperator;
    private AssertionRuleCompVO assertionRuleCompVO;
    private boolean isStatic = false;
//dimension 4 HBox controls
    double labelWidth = 92.0;
    double labelHeight = 27.0;
    double comboWidth = 104.0;
    double comboHeight = 27.0;
    double operatorWidth = 39.0;
    double operatorHeight = 25.0;

    double hSpacing = 5.0;
    double ydifferential = 31.0;
    double xLayout = 48.0;
    double lastYLayout;
    public static ItemCodeAndColumnsVO itemCodeAndColumnsVO;//need to redesign
    RequestVO requestVO;

    private RuleBuilderAbstractController lhsControllerInstance;
    private int counterID;

    public HBoxGenerator(double lastYLayout, RequestVO requestVO) {

        this.requestVO = requestVO;
        System.out.println("inside HBoxGenerator constructor requestvo " + requestVO.getReturnCode());
        itemcodeLabel = new Label("Item Code");
        itemcodeLabel.setPrefSize(labelWidth, labelHeight);
        itemcodeLabel.setMinSize(labelWidth, labelHeight);
        columnLabel = new Label("Column");
        columnLabel.setPrefSize(labelWidth, labelHeight);
        columnLabel.setMinSize(labelWidth, labelHeight);
        columnCombo = new ComboBox();
        columnCombo.setPrefSize(comboWidth, comboHeight);
        columnCombo.setMinSize(comboWidth, comboHeight);
        columnCombo.getStyleClass().add("codecomboborder");
        itemcodeCombo = new ComboBox();
        itemcodeCombo.setPrefSize(comboWidth, comboHeight);
        itemcodeCombo.setMinSize(comboWidth, comboHeight);
        itemcodeCombo.getStyleClass().add("codecomboborder");
        this.lastYLayout = lastYLayout;
    }

    public void addListener2ComboBox() {
        boolean result = getRequiredData();
        if (result) {
            columnCombo.setItems(FXCollections.observableList(itemCodeAndColumnsVO.getColNames()));
            itemcodeCombo.setItems(FXCollections.observableList(itemCodeAndColumnsVO.getItemCodes()));
        }
    }

    public boolean getRequiredData() {
        boolean result = false;
        ItemCodeAndColumnsVO test = AssertionBuilderUIController.instance.getItemCodeAndColumnsVO();
        if (test == null || (requestVO != null && !test.getReturnCode().equalsIgnoreCase(requestVO.getReturnCode()))) {
            try {
                itemCodeAndColumnsVO = new AssertionComboLoader(requestVO).getData();
            } catch (WebservicesException ex) {
                IRSDialog.showAlert(ALERTERRORTITTLE, ERROR_DROPDOWN);
                LOGGER.error(Level.ERROR, ex);
            }
        }
        if (itemCodeAndColumnsVO != null && itemCodeAndColumnsVO.getReturnCode() != null) {
            AssertionBuilderUIController.instance.setItemCodeAndColumnsVO(itemCodeAndColumnsVO);
            result = true;
        }
        return result;
    }

    public void generateCompositeHBox() {
        hboxContainer = new HBox();
        hboxContainer.setSpacing(hSpacing);
        hboxContainer.setLayoutX(xLayout);
        hboxContainer.setLayoutY(lastYLayout + ydifferential);
        hboxContainer.getChildren().addAll(itemcodeLabel, itemcodeCombo, columnLabel, columnCombo);
        boolean result;
        result = getRequiredData();
        if (result) {
            List<ColumnandTypeVO> ls2 = new ArrayList();
            for (ColumnandTypeVO l : itemCodeAndColumnsVO.getColNames()) {
                if (l.getDataType() == 2) {
                    ls2.add(l);
                } else {
                }
            }
            columnCombo.setItems(FXCollections.observableList(ls2));
            List<String> ls = itemCodeAndColumnsVO.getItemCodes();
            System.out.println("itemCodeAndColumnsVO.getTemplateType() " + itemCodeAndColumnsVO.getTemplateType());
            if (itemCodeAndColumnsVO.getTemplateType().equals("1")) {
                isStatic = true;
                this.setIsStatic(true);
                itemcodeCombo.setItems(FXCollections.observableList(ls));
            } else {
                isStatic = false;
                this.setIsStatic(false);
                itemcodeCombo.setDisable(true);
            }
        }
        setHboxContainer(hboxContainer);
        addListener2ColumnCombo();
        AssertionBuilderUIController.instance.setItemCodeAndColumnsVO(itemCodeAndColumnsVO);
        instance = this;
    }

    public void loadComplexUIHistory() {

        FXMLLoader loader = new FXMLLoader();
        // Parent root = (Parent) loader.load(getClass().getResourceAsStream(FXMLPATH + "ifoperator.fxml"));
        complexuiStage = new Stage(StageStyle.DECORATED);
        complexuiStage.centerOnScreen();
        complexuiStage.setTitle("Select Type of If Statement");
        // complexUIScene = new Scene(root);
        complexuiStage.setScene(complexUIScene);
        AssertionBuilderUIController.instance.setIfOperatorStage(complexuiStage);
        complexuiStage.showAndWait();
        System.out.println("I am released history");
        //updateParent(callingParent, mapp, operatorVbox, this,counterzz);
    }

    public void generateCompositeHBox4ComplexOperator(String labelz) {
        hboxContainer = new HBox();
        hboxContainer.setLayoutX(143.0);
        hboxContainer.setSpacing(hSpacing);
        hboxContainer.setLayoutY(lastYLayout + ydifferential);
        hboxCompButton = new Button(labelz);
        hboxCompButton.setPrefSize(311.0, 25.0);
        Label label = new Label();
        label.setPrefSize(93, 25);
        hboxContainer.getChildren().addAll(label, hboxCompButton);
        setHboxContainer(hboxContainer);
        addListener2ComplexButton();
        instance = this;
    }

    public void addListener2ComplexButton() {
        hboxCompButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                loadComplexUIHistory();
            }

        });
    }

    private void addListener2ColumnCombo() {
        columnCombo.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<ColumnandTypeVO>() {
            @Override
            public void changed(ObservableValue<? extends ColumnandTypeVO> observable, ColumnandTypeVO oldValue, ColumnandTypeVO newValue) {
                if (newValue.getDataType() != 2) {
                    columnCombo.getSelectionModel().clearSelection();
                    IRSDialog.showAlert(INFO, NOT_NUMERIC_COLUMN);
                    //return;
                }
            }

        }
        );
    }

    /**
     * @return the operator
     */
    public RuleOperator getOperator() {
        return operator;
    }

    /**
     * @param operator the operator to set
     */
    public void setOperator(RuleOperator operator) {
        this.operator = operator;
    }

    /**
     * @return the columnCombo
     */
    public ComboBox getColumnCombo() {
        return columnCombo;
    }

    /**
     * @return the columnLabel
     */
    public Label getColumnLabel() {
        return columnLabel;
    }

    /**
     * @return the hboxContainer
     */
    public HBox getHboxContainer() {
        return hboxContainer;
    }

    /**
     * @return the itemcodeCombo
     */
    public ComboBox getItemcodeCombo() {
        return itemcodeCombo;
    }

    /**
     * @return the itemcodeLabel
     */
    public Label getItemcodeLabel() {
        return itemcodeLabel;
    }

    /**
     * @param columnCombo the columnCombo to set
     */
    public void setColumnCombo(ComboBox columnCombo) {
        this.columnCombo = columnCombo;
    }

    /**
     * @param columnLabel the columnLabel to set
     */
    public void setColumnLabel(Label columnLabel) {
        this.columnLabel = columnLabel;
    }

    /**
     * @param hboxContainer the hboxContainer to set
     */
    public void setHboxContainer(HBox hboxContainer) {
        this.hboxContainer = hboxContainer;
    }

    /**
     * @param itemcodeCombo the itemcodeCombo to set
     */
    public void setItemcodeCombo(ComboBox itemcodeCombo) {
        this.itemcodeCombo = itemcodeCombo;
    }

    /**
     * @param itemcodeLabel the itemcodeLabel to set
     */
    public void setItemcodeLabel(Label itemcodeLabel) {
        this.itemcodeLabel = itemcodeLabel;
    }

    /**
     * @return the lhsControllerInstance
     */
    public RuleBuilderAbstractController getLhsControllerInstance() {
        return lhsControllerInstance;
    }

    /**
     * @param lhsControllerInstance the lhsControllerInstance to set
     */
    public void setLhsControllerInstance(RuleBuilderAbstractController lhsControllerInstance) {
        this.lhsControllerInstance = lhsControllerInstance;
    }

    /**
     * @return the counterID
     */
    public int getCounterID() {
        return counterID;
    }

    /**
     * @param counterID the counterID to set
     */
    public void setCounterID(int counterID) {
        this.counterID = counterID;
    }

    /**
     * @return the hboxCompButton
     */
    public Button getHboxCompButton() {
        return hboxCompButton;
    }

    /**
     * @param hboxCompButton the hboxCompButton to set
     */
    public void setHboxCompButton(Button hboxCompButton) {
        this.hboxCompButton = hboxCompButton;
    }

    /**
     * @return the connector
     */
    public Connector getConnector() {
        return connector;
    }

    /**
     * @return the logicConnector
     */
    public LogicConnector getLogicConnector() {
        return logicConnector;
    }

    /**
     * @return the logicOperator
     */
    public LogicOperator getLogicOperator() {
        return logicOperator;
    }

    /**
     * @param connector the connector to set
     */
    public void setConnector(Connector connector) {
        this.connector = connector;
    }

    /**
     * @param logicConnector the logicConnector to set
     */
    public void setLogicConnector(LogicConnector logicConnector) {
        this.logicConnector = logicConnector;
    }

    /**
     * @param logicOperator the logicOperator to set
     */
    public void setLogicOperator(LogicOperator logicOperator) {
        this.logicOperator = logicOperator;
    }

    /**
     * @return the complexUIScene
     */
    public Scene getComplexUIScene() {
        return complexUIScene;
    }

    /**
     * @param complexUIScene the complexUIScene to set
     */
    public void setComplexUIScene(Scene complexUIScene) {
        this.complexUIScene = complexUIScene;
    }

    /**
     * @return the isComplexOperator
     */
    public boolean isIsComplexOperator() {
        return isComplexOperator;
    }

    /**
     * @param isComplexOperator the isComplexOperator to set
     */
    public void setIsComplexOperator(boolean isComplexOperator) {
        this.isComplexOperator = isComplexOperator;
    }

    /**
     * @return the complexUIAnchorPane
     */
    public AnchorPane getComplexUIAnchorPane() {
        return complexUIAnchorPane;
    }

    /**
     * @param complexUIAnchorPane the complexUIAnchorPane to set
     */
    public void setComplexUIAnchorPane(AnchorPane complexUIAnchorPane) {
        this.complexUIAnchorPane = complexUIAnchorPane;
    }

    /**
     * @return the complexuiStage
     */
    public Stage getComplexuiStage() {
        return complexuiStage;
    }

    /**
     * @param complexuiStage the complexuiStage to set
     */
    public void setComplexuiStage(Stage complexuiStage) {
        this.complexuiStage = complexuiStage;
    }

    /**
     * @return the complexUIController
     */
    public ComplexUIController getComplexUIController() {
        return complexUIController;
    }

    /**
     * @param complexUIController the complexUIController to set
     */
    public void setComplexUIController(ComplexUIController complexUIController) {
        this.complexUIController = complexUIController;
    }

    /**
     * @return the complexOperator
     */
    public RuleOperator getComplexOperator() {
        return complexOperator;
    }

    /**
     * @param complexOperator the complexOperator to set
     */
    public void setComplexOperator(RuleOperator complexOperator) {
        this.complexOperator = complexOperator;
    }

    /**
     * @return the assertionRuleCompVO
     */
    public AssertionRuleCompVO getAssertionRuleCompVO() {
        return assertionRuleCompVO;
    }

    /**
     * @param assertionRuleCompVO the assertionRuleCompVO to set
     */
    public void setAssertionRuleCompVO(AssertionRuleCompVO assertionRuleCompVO) {
        this.assertionRuleCompVO = assertionRuleCompVO;
    }

    /**
     * @return the isStatic
     */
    public boolean isIsStatic() {
        return isStatic;
    }

    /**
     * @param isStatic the isStatic to set
     */
    public void setIsStatic(boolean isStatic) {
        this.isStatic = isStatic;
    }

}
