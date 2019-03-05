/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.controller;

import com.bbt.irs.deploy.IRSDialog;
import com.bbt.irs.deploy.Messages;
import static com.bbt.irs.deploy.Messages.FXMLPATH;
import static com.bbt.irs.deploy.Messages.INFO;
import com.bbt.irs.entity.TRtnReturns;
import com.bbt.irs.vo.AssertionRuleCompVO;
import com.bbt.irs.vo.ItemCodeAndColumnsVO;
import com.cbn.irs.connector.Connector;
import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;

/**
 *
 * @author tkola This class initialises combobox for selecting type of actions
 * <li>Edit</li>
 * <li>New</li>
 */
public class AssertionBuilderUIController implements Initializable, Messages {

    /**
     * @return the isInter
     */
    public boolean isIsInter() {
        return isInter;
    }

    /**
     * @param isInter the isInter to set
     */
    public void setIsInter(boolean isInter) {
        this.isInter = isInter;
    }

    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(AssertionRuleInterfaceController.class);
    @FXML
    AnchorPane builderAnchor;
    @FXML
    ComboBox valType;
    @FXML
    private ComboBox ruleBuilder;
    /// Map cellObjectMap;
    //static methods start
    /**
     * For the cycle of writing assertion rule this class will only be
     * instantiated once through the variabe
     */
    public static AssertionBuilderUIController instance;
    /**
     * for ensuring that LHS expression has been filled (it may not be
     * neccessary again due to default landing page)
     */
    private static boolean lhsFilled;

    //static methods end
    private RuleBuilderAbstractController lhsController;
    private Connector connector;
    private RHSController rhsController;
    private StandardIfController standardIfController;
    private ColIfOperatorController colIfOperatorController;
    private Stage ifOperatorStage;
    private Scene ifOperatorScene;
    private ItemCodeAndColumnsVO itemCodeAndColumnsVO;
    private boolean ifOperatorActionReview = false;
    private AssertionRuleCompVO assertionRuleCompVO = new AssertionRuleCompVO();
    private String selectedValType;
    private boolean onlyBooleanEquation;
    private String alias;
    private ComplexUIController complexUIController;
    private String message;
    private String errorMessage;
    private String assertion;
    private boolean isLhsStatic=false;
    private boolean isRhsStatic=false;
    public TRtnReturns selectReturnLHS;
    private String interReturCode;
    private String dependencyReturCode;
    

    double xanchor1 = 48.0;
    double xanchor2 = 157.0;
    double xanchor3 = 267.0;
    double xanchor4 = 366.0;
    double xanchor5 = 480.0;

    double bottomanchotdifferential = 31;

    double labelWidth = 92.0;
    double labelHeight = 27.0;
    double comboWidth = 104.0;
    double comboHeight = 27.0;
    double operatorWidth = 39.0;
    double operatorHeight = 25.0;

    private AnchorPane lhs;
    private AnchorPane rhs;
    private AnchorPane cellif;

    private AnchorPane collif;
    private boolean isInter;

    public enum AssertionType {
        INTER("Inter Validation"), INTRA("Intra Validation");

        /**
         * @return the actions
         */
        public String getActions() {
            return actions;
        }

        /**
         * @param actions the actions to set
         */
        public void setActions(String actions) {
            this.actions = actions;
        }

        private String actions;

        private AssertionType(String actions) {
            this.actions = actions;
        }
    };

    enum uiBuilder {
        LHS("Left Hand"), RHS("Right Hand");

        /**
         * @return the actions
         */
        public String getActions() {
            return actions;
        }

        /**
         * @param actions the actions to set
         */
        public void setActions(String actions) {
            this.actions = actions;
        }

        private String actions;

        private uiBuilder(String actions) {
            this.actions = actions;
        }
    };

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            ObservableList obsList = FXCollections.observableArrayList();
            obsList.setAll(AssertionBuilderUIController.AssertionType.INTER.getActions(), AssertionBuilderUIController.AssertionType.INTRA.getActions());
            valType.setItems(obsList);

            ObservableList ruleBuilderObsList = FXCollections.observableArrayList();
            ruleBuilderObsList.setAll(AssertionBuilderUIController.uiBuilder.LHS.getActions(), AssertionBuilderUIController.uiBuilder.RHS.getActions());
            ruleBuilder.setItems(ruleBuilderObsList);
            instance = this;
        } catch (Exception ex) {
            LOGGER.log(org.apache.logging.log4j.Level.FATAL, ex);
        }
        addListener2ValType();
        addListener2RuleBuilder();
        instance = this;
        setInstance(this);
    }

    private void addListener2ValType() {

        valType.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                selectedValType = newValue;
                ruleBuilder.getSelectionModel().select(AssertionBuilderUIController.uiBuilder.LHS.getActions());
                if(newValue.equals(AssertionType.INTER.getActions())){
                    isInter = true;
                }else{
                    isInter = false;
                }
            }

        });
    }

    private void addListener2RuleBuilder() {
        ruleBuilder.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue observable, String oldValue, String newValue) {
                System.out.println("newvalue is " + newValue);
                FXMLLoader loader = new FXMLLoader();

                if (newValue.equalsIgnoreCase(uiBuilder.LHS.getActions()) && getLhsController() == null) {
                    try {
                        //if (selectedValType.equals(AssertionType.INTRA.getActions())) {
                            Parent root = (Parent) loader.load(getClass().getResourceAsStream(FXMLPATH + "lhs.fxml"));
                            AnchorPane lhs = (AnchorPane) root;
                            lhsController = loader.getController();
                            builderAnchor.getChildren().add(lhs);
                            ((LHSController) lhsController).getInstance().setAssertionBuilderUIController(getInstance());
                            System.out.println("completed ");
//                        }else{
//                            Parent root = (Parent) loader.load(getClass().getResourceAsStream(FXMLPATH + "interlhs.fxml"));
//                            AnchorPane lhs = (AnchorPane) root;
//                            lhsController = loader.getController();
//                            builderAnchor.getChildren().addAll(lhs);
//                            ((InterLHSController) lhsController).getInstance().setAssertionBuilderUIController(getInstance());
//                            System.out.println("completed ");
//
//                        }
                    } catch (IOException ex) {
                        Logger.getLogger(AssertionBuilderUIController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (Exception ex) {
                        Logger.getLogger(AssertionBuilderUIController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (newValue.equalsIgnoreCase(uiBuilder.RHS.getActions()) && getRhsController() == null) {
//                   
                    if (!lhsFilled) {
                        IRSDialog.showAlert(INFO, LHSMUSTNOTBLANK);
                    }
                }
            }

        }
        );
    }

    public void setHorizontalGap(Node node) {
        AnchorPane.setLeftAnchor(node, 48.0);
        AnchorPane.setRightAnchor(node, 65.0);
    }

    /**
     * @return the instance
     */
    public AssertionBuilderUIController getInstance() {
        if (instance == null) {
            instance = new AssertionBuilderUIController();
        }

        return instance;
    }

    /**
     * @param instance the instance to set
     */
    public static void setInstance(AssertionBuilderUIController instance) {
        instance = instance;
    }

    /**
     * @return the connector
     */
    public Connector getConnector() {
        return connector;
    }

    /**
     * @return the lhsController
     */
    public RuleBuilderAbstractController getLhsController() {
        return lhsController;
    }

    /**
     * @return the rhsController
     */
    public RuleBuilderAbstractController getRhsController() {
        return rhsController;
    }

    /**
     * @param connector the connector to set
     */
    public void setConnector(Connector connector) {
        this.connector = connector;
    }

    /**
     * @param lhsController the lhsController to set
     */
    public void setLhsController(LHSController lhsController) {
        this.lhsController = lhsController;
    }

    /**
     * @param rhsController the rhsController to set
     */
    public void setRhsController(RHSController rhsController) {
        this.rhsController = rhsController;
    }

    /**
     * @return the lhs
     */
    public AnchorPane getLhs() {
        return lhs;
    }

    /**
     * @return the rhs
     */
    public AnchorPane getRhs() {
        return rhs;
    }

    /**
     * @param lhs the lhs to set
     */
    public void setLhs(AnchorPane lhs) {
        this.lhs = lhs;
    }

    /**
     * @param rhs the rhs to set
     */
    public void setRhs(AnchorPane rhs) {
        this.rhs = rhs;
    }

    /**
     * @return the lhsFilled
     */
    public static boolean isLhsFilled() {
        return lhsFilled;
    }

    /**
     * @param aLhsFilled the lhsFilled to set
     */
    public static void setLhsFilled(boolean aLhsFilled) {
        lhsFilled = aLhsFilled;
    }

    /**
     * @return the ruleBuilder
     */
    public ComboBox getRuleBuilder() {
        return ruleBuilder;
    }

    /**
     * @return the ifOperatorStage
     */
    public Stage getIfOperatorStage() {
        return ifOperatorStage;
    }

    /**
     * @param ifOperatorStage the ifOperatorStage to set
     */
    public void setIfOperatorStage(Stage ifOperatorStage) {
        this.ifOperatorStage = ifOperatorStage;
    }

    /**
     * @return the itemCodeAndColumnsVO
     */
    public ItemCodeAndColumnsVO getItemCodeAndColumnsVO() {
        return itemCodeAndColumnsVO;
    }

    /**
     * @param itemCodeAndColumnsVO the itemCodeAndColumnsVO to set
     */
    public void setItemCodeAndColumnsVO(ItemCodeAndColumnsVO itemCodeAndColumnsVO) {
        this.itemCodeAndColumnsVO = itemCodeAndColumnsVO;
    }

    /**
     * @return the colIfOperatorController
     */
    public ColIfOperatorController getColIfOperatorController() {
        return colIfOperatorController;
    }

    /**
     * @param colIfOperatorController the colIfOperatorController to set
     */
    public void setColIfOperatorController(ColIfOperatorController colIfOperatorController) {
        this.colIfOperatorController = colIfOperatorController;
    }

    /**
     * @return the standardIfController
     */
    public StandardIfController getStandardIfController() {
        return standardIfController;
    }

    /**
     * @param standardIfController the standardIfController to set
     */
    public void setStandardIfController(StandardIfController standardIfController) {
        this.standardIfController = standardIfController;
    }

    /**
     * @return the cellif
     */
    public AnchorPane getCellif() {
        return cellif;
    }

    /**
     * @return the collif
     */
    public AnchorPane getCollif() {
        return collif;
    }

    /**
     * @param cellif the cellif to set
     */
    public void setCellif(AnchorPane cellif) {
        this.cellif = cellif;
    }

    /**
     * @param collif the collif to set
     */
    public void setCollif(AnchorPane collif) {
        this.collif = collif;
    }

    /**
     * @return the ifOperatorActionReview
     */
    public boolean isIfOperatorActionReview() {
        return ifOperatorActionReview;
    }

    /**
     * @param ifOperatorActionReview the ifOperatorActionReview to set
     */
    public void setIfOperatorActionReview(boolean ifOperatorActionReview) {
        this.ifOperatorActionReview = ifOperatorActionReview;
    }

    /**
     * @return the ifOperatorScene
     */
    public Scene getIfOperatorScene() {
        return ifOperatorScene;
    }

    /**
     * @param ifOperatorScene the ifOperatorScene to set
     */
    public void setIfOperatorScene(Scene ifOperatorScene) {
        this.ifOperatorScene = ifOperatorScene;
    }

    /**
     * @return the selectedValType
     */
    public String getSelectedValType() {
        return selectedValType;
    }

    /**
     * @param selectedValType the selectedValType to set
     */
    public void setSelectedValType(String selectedValType) {
        this.selectedValType = selectedValType;
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
     * @return the onlyBooleanEquation
     */
    public boolean isOnlyBooleanEquation() {
        return onlyBooleanEquation;
    }

    /**
     * @param onlyBooleanEquation the onlyBooleanEquation to set
     */
    public void setOnlyBooleanEquation(boolean onlyBooleanEquation) {
        this.onlyBooleanEquation = onlyBooleanEquation;
    }

    /**
     * @return the alias
     */
    public String getAlias() {
        return alias;
    }

    /**
     * @param alias the alias to set
     */
    public void setAlias(String alias) {
        this.alias = alias;
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
     * @return the assertion
     */
    public String getAssertion() {
        return assertion;
    }

    /**
     * @return the errorMessage
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param assertion the assertion to set
     */
    public void setAssertion(String assertion) {
        this.assertion = assertion;
    }

    /**
     * @param errorMessage the errorMessage to set
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the isLhsStatic
     */
    public boolean isIsLhsStatic() {
        return isLhsStatic;
    }

    /**
     * @return the isRhsStatic
     */
    public boolean isIsRhsStatic() {
        return isRhsStatic;
    }

    /**
     * @param isLhsStatic the isLhsStatic to set
     */
    public void setIsLhsStatic(boolean isLhsStatic) {
        this.isLhsStatic = isLhsStatic;
    }

    /**
     * @param isRhsStatic the isRhsStatic to set
     */
    public void setIsRhsStatic(boolean isRhsStatic) {
        this.isRhsStatic = isRhsStatic;
    }

    /**
     * @return the interReturCode
     */
    public String getInterReturCode() {
        return interReturCode;
    }

    /**
     * @param interReturCode the interReturCode to set
     */
    public void setInterReturCode(String interReturCode) {
        this.interReturCode = interReturCode;
    }

    /**
     * @return the dependencyReturCode
     */
    public String getDependencyReturCode() {
        return dependencyReturCode;
    }

    /**
     * @param dependencyReturCode the dependencyReturCode to set
     */
    public void setDependencyReturCode(String dependencyReturCode) {
        this.dependencyReturCode = dependencyReturCode;
    }

}
