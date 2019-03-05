/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.controller;

import com.bbt.irs.dao.ReturnsDAO;
import com.bbt.irs.dao.WorkCollectionDAO;
import com.bbt.irs.deploy.Messages;
import static com.bbt.irs.deploy.Messages.FXMLPATH;
import com.bbt.irs.entity.TRtnReturns;
import com.bbt.irs.entity.TRtnWorkCollectionDefn;
import com.bbt.irs.exception.WorkCollectionNotFoundException;
import com.bbt.irs.ui.HBoxGenerator;
import com.cbn.irs.connector.Connector;
import com.cbn.irs.connector.EqualConnector;
import com.cbn.irs.connector.GreaterThanConnector;
import com.cbn.irs.connector.GreaterThanOrEqualToConnector;
import com.cbn.irs.connector.LessThanConnector;
import com.cbn.irs.connector.LessThanOrEqualToConnector;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

/**
 *
 * @author tkola
 */
public class LHSController extends RuleBuilderAbstractController implements Initializable, Messages {

    @FXML
    protected AnchorPane lhs;
    @FXML
    protected HBox lhsWorkCollandReturn;//fx:id="lhsWorkCollandReturn"
    @FXML
    Button add;
    protected AssertionBuilderUIController assertionBuilderUIController;
    protected RHSController rhsController;
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            instance = this;
            List<TRtnWorkCollectionDefn> ls = new WorkCollectionDAO().getWorkCollection();
            workColl.setItems(FXCollections.observableArrayList(ls));
            addListener2LHSWorkColl();
            addListener2LHSReturn();
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
                        //TRtnReturns retunrss = AssertionBuilderUIController.instance.selectReturnLHS;
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
                if (operatorVbox != null && operatorVbox.getChildren() != null) {
                    operatorVbox.getChildren().clear();
                    AssertionBuilderUIController.instance.selectReturnLHS=newValue;
                    System.out.println("clear ");
                    if(( AssertionBuilderUIController.instance!=null))
                     AssertionBuilderUIController.instance.setInterReturCode(newValue.getReturnCode());
                }
                generateCellORCol();
                System.out.println("process completed");
            }

        }
        );
    }

    @FXML
    public void equaltoConnector(ActionEvent e) {
        HBoxGenerator anchorPaneHBoxGenerator = map.get(map.size());
        boolean resultz = validateOperand(anchorPaneHBoxGenerator);
        if (!resultz) {
            return;
        }
        Connector connector = new EqualConnector();
        assertionBuilderUIController.getInstance().setLhsController(this);
        assertionBuilderUIController.getInstance().setConnector(connector);

        switch2RighthandSide();

    }

    @FXML
    public void greaterThanConnector(ActionEvent e) {
        HBoxGenerator anchorPaneHBoxGenerator = map.get(map.size());
        boolean resultz = validateOperand(anchorPaneHBoxGenerator);
        if (!resultz) {
            return;
        }
        Connector connector = new GreaterThanConnector();
        assertionBuilderUIController.getInstance().setLhsController(this);
        assertionBuilderUIController.getInstance().setConnector(connector);
        switch2RighthandSide();

    }

    @FXML
    public void greaterThanOrEqualToConnector(ActionEvent e) {
        HBoxGenerator anchorPaneHBoxGenerator = map.get(map.size());
        boolean resultz = validateOperand(anchorPaneHBoxGenerator);
        if (!resultz) {
            return;
        }
        Connector connector = new GreaterThanOrEqualToConnector();
        assertionBuilderUIController.getInstance().setLhsController(this);
        assertionBuilderUIController.getInstance().setConnector(connector);
        switch2RighthandSide();

    }

    @FXML
    public void lessThanConnector(ActionEvent e) {
        HBoxGenerator anchorPaneHBoxGenerator = map.get(map.size() - 1);
        boolean resultz = validateOperand(anchorPaneHBoxGenerator);
        if (!resultz) {
            return;
        }
        Connector connector = new LessThanConnector();
        assertionBuilderUIController.getInstance().setLhsController(this);
        assertionBuilderUIController.getInstance().setConnector(connector);
        switch2RighthandSide();

    }

    @FXML
    public void lessThanOrEqualToConnector(ActionEvent e) {
        HBoxGenerator anchorPaneHBoxGenerator = map.get(map.size() - 1);
        boolean resultz = validateOperand(anchorPaneHBoxGenerator);
        if (!resultz) {
            return;
        }
        Connector connector = new LessThanOrEqualToConnector();
        assertionBuilderUIController.getInstance().setLhsController(this);
        assertionBuilderUIController.getInstance().setConnector(connector);
        switch2RighthandSide();

    }

    public void switch2RighthandSide() {
        FXMLLoader loader = new FXMLLoader();
        try {
            boolean test = AssertionBuilderUIController.instance.isIsInter();
            assertionBuilderUIController.getInstance().setLhs(lhs);
            assertionBuilderUIController.getInstance().setLhsController(this);
            Parent root;
            AnchorPane rhs;
            if (assertionBuilderUIController.getInstance().getRhs() == null) {
                root = (Parent) loader.load(getClass().getResourceAsStream(FXMLPATH + "rhs.fxml"));
                rhs = (AnchorPane) root;
                rhsController = (RHSController) loader.getController();

            } else {
                rhs = assertionBuilderUIController.getInstance().getRhs();
                rhsController = (RHSController) assertionBuilderUIController.getInstance().getRhsController();

            }
            assertionBuilderUIController.getInstance().builderAnchor.getChildren().remove(lhs);
            assertionBuilderUIController.getInstance().builderAnchor.getChildren().addAll(rhs);
            assertionBuilderUIController.getInstance().getRuleBuilder().getSelectionModel().select(AssertionBuilderUIController.uiBuilder.RHS.getActions());
            assertionBuilderUIController.getInstance().setRhs(rhs);
            rhsController.setAssertionBuilderUIController(assertionBuilderUIController);
            // assertionBuilderUIController.setLhsFilled(true);
            AssertionBuilderUIController.setLhsFilled(true);
            if (!assertionBuilderUIController.getInstance().isIsInter()) {
                setRHS4Intra(rhsController);
            }else{
                setRHS4Inter(rhsController);
            }
            System.out.println("completed ");
        } catch (IOException ex) {
            Logger.getLogger(AssertionBuilderUIController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(AssertionBuilderUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setRHS4Intra(RHSController rhsCont) {
        String validationTypeSelected = (String) assertionBuilderUIController.getInstance().valType.getSelectionModel().getSelectedItem();
        String validationtype = AssertionBuilderUIController.AssertionType.INTRA.getActions();
        // LHSController lhscontroller = (LHSController) assertionBuilderUIController.getInstance().getLhsController();
        TRtnWorkCollectionDefn lhsworkcoll = workColl.getSelectionModel().getSelectedItem();
        TRtnReturns lhsreturnz = getReturns().getSelectionModel().getSelectedItem();
        if (validationTypeSelected.equals(validationtype)) {
            rhsCont.workColl.getSelectionModel().select(lhsworkcoll);
            rhsCont.getReturns().getSelectionModel().select(lhsreturnz);
        }
    }

    public void setRHS4Inter(RHSController rhsCont) {
        String validationTypeSelected = (String) assertionBuilderUIController.getInstance().valType.getSelectionModel().getSelectedItem();
        String validationtype = AssertionBuilderUIController.AssertionType.INTER.getActions();
        // LHSController lhscontroller = (LHSController) assertionBuilderUIController.getInstance().getLhsController();
        TRtnWorkCollectionDefn lhsworkcoll = workColl.getSelectionModel().getSelectedItem();
        TRtnReturns lhsreturnz = getReturns().getSelectionModel().getSelectedItem();

            rhsCont.workColl.getSelectionModel().select(lhsworkcoll);
            //rhsCont.returns.getSelectionModel().select(lhsreturnz);

    }

    /**
     * @return the instance
     */
    public LHSController getInstance() {
        return (LHSController) instance;
    }

    /**
     * @param instance the instance to set
     */
    public void setInstance(LHSController instance) {
        this.instance = instance;
    }

    /**
     * @return the lhs
     */
    public AnchorPane getLhs() {
        return lhs;
    }

    /**
     * @param lhs the lhs to set
     */
    public void setLhs(AnchorPane lhs) {
        this.lhs = lhs;
    }

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

    /**
     * @return the rhsController
     */
    public RHSController getRhsController() {
        return rhsController;
    }

    /**
     * @param rhsController the rhsController to set
     */
    public void setRhsController(RHSController rhsController) {
        this.rhsController = rhsController;
    }

}
