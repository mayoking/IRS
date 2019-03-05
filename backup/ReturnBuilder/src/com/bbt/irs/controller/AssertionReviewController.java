/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.controller;

import static com.bbt.irs.deploy.ErrorNameDesc.ERROR;
import static com.bbt.irs.deploy.ErrorNameDesc.PROCESSINEXCEPTION;
import com.bbt.irs.deploy.IRS;
import com.bbt.irs.deploy.IRSDialog;
import com.bbt.irs.entity.TRtnReturns;
import com.bbt.irs.entity.TRtnWorkCollectionDefn;
import com.bbt.irs.rest.client.RBRestClient;
import com.bbt.irs.ui.HBoxGenerator;
import com.bbt.irs.util.Utility;
import com.bbt.irs.vo.AssertionRuleCompVO;
import com.bbt.irs.vo.ColumnandTypeVO;
import com.bbt.irs.vo.ComplexOperandVO;
import com.bbt.irs.vo.OperandsVO;
import com.bbt.irs.vo.ResponseVO;
import com.bbt.irs.vo.RuleSetVO;
import com.bbt.irs.vo.RuleVO;
import com.cbn.irs.logicconnector.LogicConnector;
import com.cbn.irs.ruleoperators.NoOperator;
import com.cbn.irs.ruleoperators.RuleOperator;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.ws.rs.ProcessingException;

/**
 *
 * @author tkola
 */
public class AssertionReviewController implements Initializable {

    @FXML
    Text message;
    @FXML
    Text assertionMessages;
    @FXML
    Text errorMessages;
    private RuleOperator operator;
    RuleSetVO ruleSetVO;
    String userid;

    private Stage popupStage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void createRule(ActionEvent e) {

        AssertionBuilderUIController assertionRuleBuilder = AssertionBuilderUIController.instance;
        RuleVO ruleVO = new RuleVO();
        userid = IRS.getInstance().username;
        ruleSetVO = new RuleSetVO();
        String returncode ;
        if (!assertionRuleBuilder.isIsInter()) {
            returncode = ((TRtnReturns) assertionRuleBuilder.getLhsController().getReturns().getSelectionModel().getSelectedItem()).getReturnCode();
        } else {
            returncode=((TRtnWorkCollectionDefn) assertionRuleBuilder.getLhsController().workColl.getSelectionModel().getSelectedItem()).getWorkCollectionCode();
        }
        ruleSetVO.setReturnCode(returncode);
        ruleSetVO.setWorkCollectionCode(((TRtnWorkCollectionDefn) assertionRuleBuilder.getLhsController().workColl.getSelectionModel().getSelectedItem()).getWorkCollectionCode());
        ruleSetVO.setUserid(userid);
        ruleSetVO.setInterReturnCode(assertionRuleBuilder.getInterReturCode());
        ruleSetVO.setDepenednecyReturnCode(assertionRuleBuilder.getDependencyReturCode());
        LHSController lhsController = (LHSController) assertionRuleBuilder.getLhsController();
        RHSController rhsController = (RHSController) assertionRuleBuilder.getRhsController();
        Map<Integer, HBoxGenerator> lhsMapp = lhsController.map;
        Map<Integer, HBoxGenerator> rhsMapp = rhsController.map;
        List<OperandsVO> operandsListLeft = processOperands(lhsMapp, false);
        List<OperandsVO> operandsListRight = processOperands(rhsMapp, true);

        ruleVO.setAlias(assertionRuleBuilder.getAlias());
        ruleVO.setRuleType((String) assertionRuleBuilder.valType.getSelectionModel().getSelectedItem());
        AssertionRuleCompVO assertionruleCompVO = assertionRuleBuilder.getAssertionRuleCompVO();
        ruleVO.setRuleAssertion(assertionRuleBuilder.getAssertion());
        ruleVO.setOnlyBoolean(assertionRuleBuilder.isOnlyBooleanEquation());
        ruleVO.setRuleMessage(assertionRuleBuilder.getMessage());
        ruleVO.setRuleErrormessage(assertionRuleBuilder.getErrorMessage());
        ruleVO.setConnect(assertionRuleBuilder.getConnector().getConnectorType());
        ruleVO.setOperandsListLeft(operandsListLeft);
        ruleVO.setOperandsListRight(operandsListRight);
        ruleVO.setUserid(userid);
        List<RuleVO> rulevos = new ArrayList();
        rulevos.add(ruleVO);
        ruleSetVO.setRules(rulevos);
        new Utility().progressModal(createWorker());

    }

    public Task createWorker() {
        return new Task() {
            @Override
            protected Object call() throws Exception {
                try {

                    RBRestClient rbRestClient = new RBRestClient();
                    ResponseVO res = rbRestClient.assertrulesubmission(ruleSetVO);
                    if (res.getResponseCode() == 0) {
                        IRSDialog.showAlert("Info", "Rule successfully created.");

                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                popupStage.close();
                                try {
                                    MainController.getInstance().writeAssertion();
                                } catch (IOException ex) {
                                    Logger.getLogger(AssertionReviewController.class.getName()).log(Level.SEVERE, null, ex);
                                }

                            }
                        });
                    } else {
                        IRSDialog.showAlert("ERROR", res.getResponseDesc());
                        // LOGGER.log(Level.ERROR, res.getResponseDesc());
                    }
                } catch (ProcessingException ex) {
                    IRSDialog.showAlert(ERROR, PROCESSINEXCEPTION);
                    //LOGGER.log(Level.FATAL, PROCESSINEXCEPTION);
                    ex.printStackTrace();
                } catch (Exception ex) {
                    IRSDialog.showAlert(ERROR, ex.getMessage());
                    ex.printStackTrace();
                    // LOGGER.log(Level.FATAL, ex);
                }
                return null;
            }

        };

    }

    @FXML
    public void backButton(ActionEvent e) {
        popupStage.close();
    }

    public List<ComplexOperandVO> processComplexOperand(Map<Integer, HBoxGenerator> ifMapp, Map<Integer, HBoxGenerator> thenMapp) {
        List<ComplexOperandVO> complexOperandList = new ArrayList();
        for (Map.Entry<Integer, HBoxGenerator> entry : ifMapp.entrySet()) {
            ComplexOperandVO complexOperandVO = new ComplexOperandVO();
            HBoxGenerator hboxgen = entry.getValue();
            complexOperandVO.setIsIfpart(true);
            complexOperandVO.setIsthenPart(false);
            LogicConnector logicConnector = hboxgen.getLogicConnector();
            complexOperandVO.setIsLiteral(hboxgen.getLogicConnector().isLiteral());
            complexOperandVO.setLogicConnectorType(hboxgen.getLogicConnector().getConnectorType());
            if (hboxgen.getLogicOperator() != null) {
                complexOperandVO.setLogicalOperatorType(hboxgen.getLogicOperator().getLogicOperatorType());
            }
            ComboBox box = logicConnector.getColcombo();
            ComboBox itemBox = logicConnector.getItemcodecombo();
            ColumnandTypeVO col ;
            if (box != null) {
                col = (ColumnandTypeVO) logicConnector.getColcombo().getSelectionModel().getSelectedItem();
                complexOperandVO.setLogicConnectorCol(col == null ? null : col.getColumnName());
            }
            if (itemBox != null) {
                String itemCode = (String) logicConnector.getItemcodecombo().getSelectionModel().getSelectedItem();
                complexOperandVO.setLogicConnectoritemCode(itemCode == null ? null : itemCode);
            }
            TextField textField = logicConnector.getLogicConnectorTextField();
            complexOperandVO.setLogicConnectorliteralText(textField == null ? null : textField.getText());

            complexOperandVO.setColumnName(((ColumnandTypeVO) hboxgen.getColumnCombo().getSelectionModel().getSelectedItem()).getColumnName());
            complexOperandVO.setItemCode((String) hboxgen.getItemcodeCombo().getSelectionModel().getSelectedItem());
////            complexOperandVO.setIscomplexOperator(hboxgen.isIsComplexOperator());
            complexOperandVO.setOrderNumber(entry.getKey());
            //           complexOperandVO.setComplexOperatorType(hboxgen.getComplexOperator().getOperatorType());
            //operandsVO.setFollowingOperatorType(followingOperands);
            if (hboxgen.getOperator() != null) {
                complexOperandVO.setInitialOperatorType(hboxgen.getOperator().getOperatorType());
            }
            complexOperandVO.setUserid(userid);
            complexOperandList.add(complexOperandVO);
        }

        for (Map.Entry<Integer, HBoxGenerator> entry : thenMapp.entrySet()) {
            ComplexOperandVO complexOperandVO = new ComplexOperandVO();
            HBoxGenerator hboxgen = entry.getValue();
            complexOperandVO.setIsIfpart(false);
            complexOperandVO.setIsthenPart(true);
            complexOperandVO.setColumnName(((ColumnandTypeVO) hboxgen.getColumnCombo().getSelectionModel().getSelectedItem()).getColumnName());
            complexOperandVO.setItemCode((String) hboxgen.getItemcodeCombo().getSelectionModel().getSelectedItem());
            complexOperandVO.setIscomplexOperator(hboxgen.isIsComplexOperator());
            complexOperandVO.setOrderNumber(entry.getKey());
            //complexOperandVO.setComplexOperatorType(hboxgen.getComplexOperator().getOperatorType());
            //operandsVO.setFollowingOperatorType(followingOperands);
            RuleOperator operatorType = hboxgen.getOperator();
            if(operatorType==null){
                operatorType = new NoOperator();
            }
            complexOperandVO.setInitialOperatorType(operatorType.getOperatorType());
            
            //complexOperandList.add(complexOperandVO);
            complexOperandVO.setUserid(userid);
            complexOperandList.add(complexOperandVO);
        }
        System.out.println("This is the size of complex being sent " + complexOperandList.size());
        return complexOperandList;
    }

    public List<OperandsVO> processOperands(Map<Integer, HBoxGenerator> mapp, boolean isRight) {
        List<OperandsVO> operandsList = new ArrayList();
        for (Map.Entry<Integer, HBoxGenerator> entry : mapp.entrySet()) {
            OperandsVO operandsVO = new OperandsVO();
            HBoxGenerator hboxgen = entry.getValue();
            if (!hboxgen.isIsComplexOperator()) {
                operandsVO.setColumnName(((ColumnandTypeVO) hboxgen.getColumnCombo().getSelectionModel().getSelectedItem()).getColumnName());
                operandsVO.setItemCode((String) hboxgen.getItemcodeCombo().getSelectionModel().getSelectedItem());
            }
            operandsVO.setIscomplexOperator(hboxgen.isIsComplexOperator());
            operandsVO.setOrderNumber(entry.getKey());
            operandsVO.setIsLHS(!isRight);
            operandsVO.setIsRHS(isRight);
            if (hboxgen.isIsComplexOperator()) {
                operandsVO.setComplexOperatorType(hboxgen.getComplexOperator().getOperatorType());
            }

            //operandsVO.setFollowingOperatorType(followingOperands);
            operandsVO.setInitialOperatorType(hboxgen.getOperator().getOperatorType());
            if (hboxgen.isIsComplexOperator()) {
                Map<Integer, HBoxGenerator> ifMapp = hboxgen.getComplexUIController().complexandIfMap;
                Map<Integer, HBoxGenerator> thenMapp = hboxgen.getComplexUIController().thenMap;
                List<ComplexOperandVO> complexOperand = processComplexOperand(ifMapp, thenMapp);
                System.out.println("This is the total size " + complexOperand.size());
                operandsVO.setComplexOperand(complexOperand);
            }
            operandsList.add(operandsVO);
        }
        return operandsList;
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
     * @return the popupStage
     */
    public Stage getPopupStage() {
        return popupStage;
    }

    /**
     * @param popupStage the popupStage to set
     */
    public void setPopupStage(Stage popupStage) {
        this.popupStage = popupStage;
    }

}
