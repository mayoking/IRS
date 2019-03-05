/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.controller;

import com.bbt.irs.dao.ReturnsDAO;
import com.bbt.irs.dao.WorkCollectionDAO;
import com.bbt.irs.deploy.IRS;
import com.bbt.irs.deploy.IRSDialog;
import static com.bbt.irs.deploy.Messages.CSSPATH;
import static com.bbt.irs.deploy.Messages.FXMLPATH;
import com.bbt.irs.entity.TRtnReturns;
import com.bbt.irs.entity.TRtnWorkCollectionDefn;
import com.bbt.irs.exception.DatabaseException;
import com.bbt.irs.exception.WorkCollectionNotFoundException;
import com.bbt.irs.ui.HBoxGenerator;
import com.bbt.irs.vo.AssertionRuleCompVO;
import com.cbn.irs.ruleoperators.NoOperator;
import com.cbn.irs.ruleoperators.RuleOperator;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author tkola
 */
public class RHSController extends RuleBuilderAbstractController implements Initializable {

    @FXML
    private AnchorPane rhs;
    @FXML
    HBox lhsWorkCollandReturn;//fx:id="lhsWorkCollandReturn"
    @FXML
    Button add;

    StringBuffer message = new StringBuffer();
    StringBuffer assertion = new StringBuffer();
    StringBuffer errorMessage = new StringBuffer();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            instance = this;
            List<TRtnWorkCollectionDefn> ls = new WorkCollectionDAO().getWorkCollection();
            workColl.setItems(FXCollections.observableArrayList(ls));
            addListener2LHSWorkColl();
            addListener2LHSReturn();
            workColl.setDisable(true);
            if (!AssertionBuilderUIController.instance.getSelectedValType().equals("Inter Validation")) {
                getReturns().setDisable(true);
            }
            AssertionBuilderUIController.instance.setRhsController(this);
        } catch (Exception ex) {
            Logger.getLogger(LHSController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addListener2LHSWorkColl() {
        workColl.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<TRtnWorkCollectionDefn>() {
            @Override
            public void changed(ObservableValue<? extends TRtnWorkCollectionDefn> observable, TRtnWorkCollectionDefn oldValue, TRtnWorkCollectionDefn newValue) {
                try {
                    List<TRtnReturns> trtnReturns;
                    if (AssertionBuilderUIController.instance.getSelectedValType().equals("Inter Validation")) {
                        trtnReturns = new ReturnsDAO().getAllActiveReturns(newValue.getWorkCollectionCode());
                        TRtnReturns retunrss = AssertionBuilderUIController.instance.selectReturnLHS;
                        getReturns().setItems(FXCollections.observableArrayList(trtnReturns));
                    } else {
                        trtnReturns = new ReturnsDAO().getAllStaticReturns(newValue.getWorkCollectionCode());
                    }
                    getReturns().setItems(FXCollections.observableArrayList(trtnReturns));
                } catch (WorkCollectionNotFoundException ex) {
                    Logger.getLogger(LHSController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
        );
    }

    public void addListener2LHSReturn() {
        getReturns().getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<TRtnReturns>() {
            @Override
            public void changed(ObservableValue<? extends TRtnReturns> observable, TRtnReturns oldValue, TRtnReturns newValue) {
                operatorVbox.getChildren().clear();
                if (map != null) {
                    map.clear();
                    counter = 0;
                }
                generateCellORCol();
                AssertionBuilderUIController.instance.setDependencyReturCode(newValue.getReturnCode());
                System.out.println("process completed");
            }

        }
        );
    }

    @FXML
    public void submitAssertionRule() {
        message.setLength(0);
        assertion.setLength(0);
        errorMessage.setLength(0);
        System.out.println("Assert");
        HBoxGenerator anchorPaneHBoxGenerator = map.get(map.size());
        boolean resultz = validateOperand(anchorPaneHBoxGenerator);
        if (resultz) {
            AssertionBuilderUIController.instance.setRhsController(this);
            LHSController lhsController = (LHSController) assertionBuilderUIController.getInstance().getLhsController();
            Map<Integer, HBoxGenerator> mapp = lhsController.map;
            int size = mapp.size();
            TRtnReturns lhsReturn = lhsController.getReturns().getSelectionModel().getSelectedItem();
            processMap(lhsReturn.getReturnCode(), mapp, false, size);
            message.append(" ").append(assertionBuilderUIController.getInstance().getConnector().getConnectorDescription()).append(" ");
            errorMessage.append(" ").append(assertionBuilderUIController.getInstance().getConnector().getNegativeConnectorDescription()).append(" ");
            assertion.append(" ").append(assertionBuilderUIController.getInstance().getConnector().getConnectorType());
            TRtnReturns rhsReturn = getReturns().getSelectionModel().getSelectedItem();
            processMap(rhsReturn.getReturnCode(), map, true, map.size());
            System.out.println("message is " + message.toString());
            System.out.println("assertion is " + assertion.toString());
            try {
                popupReview();
            } catch (DatabaseException ex) {
                IRSDialog.showAlert("ERROR", "Error,Unable to submit the request");
                Logger.getLogger(RHSController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void processMap(String returnCode, Map<Integer, HBoxGenerator> mapp, boolean isrhs, int size) {
        for (Map.Entry<Integer, HBoxGenerator> entry : mapp.entrySet()) {
            HBoxGenerator anchorpaneHBoxGenerator = entry.getValue();
            AssertionRuleCompVO compvo;
            RuleOperator ruleOperator = anchorpaneHBoxGenerator.getOperator();
            if (ruleOperator == null) {
                System.out.println("rule operator is null ===================== hmm ");
                RuleOperator operator = new NoOperator();
                anchorpaneHBoxGenerator.setOperator(operator);
            }
            boolean isLast = false;
            if (anchorpaneHBoxGenerator.getCounterID() == size) {
                isLast = true;
            }
            //this point need to be modified for complex operator
            System.out.println("anchorpaneHBoxGenerator.getOperator() " + anchorpaneHBoxGenerator.getOperator().getOperatorType());
            boolean test = AssertionBuilderUIController.instance.isIsInter();
            if (anchorpaneHBoxGenerator.isIsComplexOperator()) {
                System.out.println("anchorpaneHBoxGenerator ----- " + anchorpaneHBoxGenerator.getAssertionRuleCompVO().getAssertion());
                compvo = anchorpaneHBoxGenerator.getComplexOperator().processInfo(returnCode, anchorpaneHBoxGenerator.getCounterID(), anchorpaneHBoxGenerator, isrhs, isLast, test);
            } else {
                compvo = anchorpaneHBoxGenerator.getOperator().processInfo(returnCode, anchorpaneHBoxGenerator.getCounterID(), anchorpaneHBoxGenerator, isrhs, isLast, test);
            }
            System.out.println("compvo " + compvo);
            message.append(compvo.getDisPlayMessage());
            errorMessage.append(compvo.getErrorMessage());
            assertion.append(compvo.getAssertion());
            AssertionBuilderUIController.instance.setMessage(message.toString());
            AssertionBuilderUIController.instance.setAssertion(assertion.toString());
            AssertionBuilderUIController.instance.setErrorMessage(errorMessage.toString());
            AssertionBuilderUIController.instance.setAssertionRuleCompVO(compvo);
        }
    }

    @FXML
    public void previous() {
        switch2LefthandSide();
    }

    /**
     * @return the lhs
     */
    /**
     * @return the assertionBuilderUIController
     */
    public AssertionBuilderUIController getAssertionBuilderUIController() {
        return assertionBuilderUIController;
    }

    /**
     * @param assertionBuilderUIController the assertionBuilderUIController to
     * set
     */
    public void setAssertionBuilderUIController(AssertionBuilderUIController assertionBuilderUIController) {
        this.assertionBuilderUIController = assertionBuilderUIController;
    }

    public void switch2LefthandSide() {
        FXMLLoader loader = new FXMLLoader();
        try {
            System.out.println("null assertionBuilderUIController " + assertionBuilderUIController);
            System.out.println("null assertionBuilderUIController.getInstance() " + assertionBuilderUIController.getInstance());
            assertionBuilderUIController.getInstance().setRhsController(this);
            assertionBuilderUIController.getInstance().setRhs(rhs);
            Parent root;
            LHSController lhsController = null;
            AnchorPane lhs;
            if (assertionBuilderUIController.getInstance().getLhsController() == null) {
                root = (Parent) loader.load(getClass().getResourceAsStream(FXMLPATH + "lhs.fxml"));
                lhs = (AnchorPane) root;
                lhsController = loader.getController();
            } else {
                lhs = assertionBuilderUIController.getInstance().getLhs();
                lhsController = (LHSController) assertionBuilderUIController.getInstance().getLhsController();
            }

            assertionBuilderUIController.getInstance().builderAnchor.getChildren().remove(rhs);
            assertionBuilderUIController.getInstance().builderAnchor.getChildren().addAll(lhs);
            assertionBuilderUIController.getInstance().getRuleBuilder().getSelectionModel().select(AssertionBuilderUIController.uiBuilder.LHS.getActions());

            System.out.println("completed ");
        } catch (IOException ex) {
            Logger.getLogger(AssertionBuilderUIController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(AssertionBuilderUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void popupReview() throws DatabaseException {

        try {
            //FXMLLoader loader = new FXMLLoader(getClass().getResource("/library/assistant/ui/addbook/syncadd.fxml"));
            FXMLLoader loader = new FXMLLoader();
            Parent root = (Parent) loader.load(getClass().getResourceAsStream(FXMLPATH + "assertionrulereview.fxml"));

            AssertionReviewController controller = (AssertionReviewController) loader.getController();
            controller.assertionMessages.setText(assertion.toString());
            controller.errorMessages.setText(errorMessage.toString().replaceAll("xerces:message", ""));
            controller.message.setText(message.toString());
            Stage stage = new Stage(StageStyle.DECORATED);
            controller.setPopupStage(stage);
            stage.setTitle("Review");
            Scene reviewScene = new Scene(root);
            IRS.scene.getStylesheets().add(getClass().getResource(CSSPATH + "mystyle.css").toExternalForm());
            //reviewScene.getStylesheets().add(getClass().getResource(CSSPATH + "mystyle.css").toExternalForm());
            stage.setScene(reviewScene);
            stage.show();
            assertion.setLength(0);
            errorMessage.setLength(0);
            message.setLength(0);
        } catch (IOException ex) {
            IRSDialog.showAlert("ERROR", "Error,Unable to show Review");
        }
    }

}
