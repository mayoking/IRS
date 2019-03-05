/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cbn.irs.ruleoperators;

import com.bbt.irs.controller.AssertionBuilderUIController;
import com.bbt.irs.controller.ComplexUIController;
import com.bbt.irs.controller.RuleBuilderAbstractController;
import com.bbt.irs.deploy.IRSDialog;
import static com.bbt.irs.deploy.Messages.INFO;
import com.bbt.irs.ui.HBoxGenerator;
import com.bbt.irs.ui.CompoundUI4Operator;
import com.bbt.irs.vo.AssertionRuleCompVO;
import com.bbt.irs.vo.ItemCodeAndColumnsVO;
import com.bbt.irs.vo.RequestVO;
import java.util.Map;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author tkola
 */
public abstract class RuleOperator {

    public boolean isLast;

    public abstract String getOperatorType();

    public abstract AssertionRuleCompVO processInfo(String returnCode, int order, HBoxGenerator anchorPaneHBoxGenerator, boolean isrhs, boolean islast, boolean isInter);

    public AssertionRuleCompVO processComplexinfo(String returnCode, int order, HBoxGenerator anchorPaneHBoxGenerator, boolean isrhs, boolean isInter) {
        String startAssert = "<xs:assert test=\"";
        //String startErrorMessage = "xerces:message=\"---Assertion Failed for " + returnCode;
        String startErrorMessage = "xerces:message=\"Error: " + returnCode + " ";
        AssertionRuleCompVO assertionRuleCompVOold = anchorPaneHBoxGenerator.getAssertionRuleCompVO();
        AssertionRuleCompVO assertionRuleCompVO = new AssertionRuleCompVO();

        String assertionMessage;
        String errorMessage;
        System.out.println("About to process assertion rulecompvo ");

        if (assertionRuleCompVOold != null) {
            assertionRuleCompVO.setAssertion(assertionRuleCompVOold.getAssertion());
            assertionRuleCompVO.setDisPlayMessage(assertionRuleCompVOold.getDisPlayMessage());
            assertionRuleCompVO.setErrorMessage("(" + assertionRuleCompVOold.getErrorMessage() + ")");
            System.out.println("assertionRuleCompVO not null ");
            assertionMessage = assertionRuleCompVO.getAssertion();
            errorMessage = assertionRuleCompVO.getErrorMessage();
        } else {
            IRSDialog.showAlert(INFO, "No complex rule setup for the operator");
            return null;
        }
        if (order == 1) {
            if (!isrhs) {
                assertionRuleCompVO.setAssertion(startAssert + assertionMessage);
                assertionRuleCompVO.setErrorMessage(startErrorMessage + errorMessage);
            } else {
                assertionRuleCompVO.setAssertion((startRHS) + assertionMessage);//+(endRHS)+(assertEndtag)
                assertionRuleCompVO.setErrorMessage(String.format(startRHSMessages, returnCode) + (errorMessage) + " ");//+ (errorMsgEndTag)
                RuleOperator operatortype = anchorPaneHBoxGenerator.getOperator();
                if (!isLast) {
                    String test = assertionRuleCompVO.getAssertion();
                    String operator = (operatortype == null ? "" : (operatortype.getOperatorType())) + " ";
                    assertionRuleCompVO.setAssertion(test + " " + operator);
                }
            }
        } else {
            if (isrhs) {

                assertionRuleCompVO.setAssertion(assertionMessage);//+ endRHS +assertEndtag

                assertionRuleCompVO.setErrorMessage(errorMessage);//+ errorMsgEndTag
            } else {
                assertionRuleCompVO.setAssertion(assertionMessage);//+ endLHS 
            }
            RuleOperator operatortype = anchorPaneHBoxGenerator.getOperator();
            System.out.println("operatortype not 1st order operatortype.getOperatorType() " + operatortype == null ? "" : operatortype.getOperatorType());
            if (!isLast) {

                assertionRuleCompVO.setAssertion(assertionRuleCompVO.getAssertion() + " " + operatortype == null ? "" : operatortype.getOperatorType() + " ");
            }
        }
        if (isLast) {
            if (isrhs) {
                assertionRuleCompVO.setAssertion(assertionRuleCompVO.getAssertion() + endRHS + assertEndtag);
                assertionRuleCompVO.setErrorMessage(assertionRuleCompVO.getErrorMessage() + errorMsgEndTag);
            } else {
                assertionRuleCompVO.setAssertion(assertionRuleCompVO.getAssertion() + endLHS);
            }
        } else {
            assertionRuleCompVO.setErrorMessage(assertionRuleCompVO.getErrorMessage() + " " + anchorPaneHBoxGenerator.getOperator().getOperatorType() + " ");
        }
        return assertionRuleCompVO;
    }
    private Button button;
    private boolean isComplexOperator;
    private AnchorPane complexUIFXML;
    Scene ifOPeratorScene;
    Stage ifOperatorStage;
    private ComplexUIController complexUIController;
    protected String interIntradesc;
    protected String startLHS = "<xs:assert test=\"number(";
    protected String startRHS = "number(";
    protected String endLHS = ")";
    protected String endRHS = ")";
    protected String startMessages = "xerces:message=\"Error:- %s: ";
    protected String startRightMessages = " %s: ";
    protected String startRHSMessages = " %s: ";
    protected String startMessages1 = "xerces:message=\"--- %s Failed between return(s) %s of column name %s ";
    String itemCodeColPlaceHolder = "section/item_code[@code = '%s']/%s/text()";
    String interItemColPlaceHolderDynamic = "sum(%s/section/item_code/%s/text())";
    String interItemCodeColPlaceHolder = "%s/section/item_code[@code = '%s']/%s/text()";
    String assertEndtag = "\"/>";
    String errorMsgEndTag = "\"";

    public Button getUI() {
        if (button == null) {
            button = new Button(getOperatorType());
        }

        return button;
    }

    public void getComplexUI(RuleBuilderAbstractController callingParent, Map<Integer, HBoxGenerator> mapp, VBox operatorVbox, Integer conuterzz) {

    }

    public void updateParent(RuleBuilderAbstractController callingParent, Map<Integer, HBoxGenerator> mapp, VBox operatorVbox, RuleOperator operator, Integer countrzz, String buttonLabel) {
        System.out.println("mappp " + mapp.size());
        HBoxGenerator hbox = mapp.get(mapp.size());
        ItemCodeAndColumnsVO test = AssertionBuilderUIController.instance.getItemCodeAndColumnsVO();
        RequestVO requestvo = new RequestVO();
        requestvo.setReturnCode(test.getReturnCode());
        // requestvo.setWorkCollectionCode(AssertionBuilderUIController.instance.);//issue
        HBoxGenerator anchorPaneHBoxGenerator1 = new HBoxGenerator(0, requestvo);
        Integer counterz = hbox.getCounterID();
        System.out.println("counter " + counterz);
        mapp.remove(mapp.size());
        operatorVbox.getChildren().remove(hbox.getHboxContainer());
        anchorPaneHBoxGenerator1.generateCompositeHBox4ComplexOperator(buttonLabel);
        anchorPaneHBoxGenerator1.setComplexUIScene(AssertionBuilderUIController.instance.getIfOperatorScene());
        anchorPaneHBoxGenerator1.setComplexuiStage(AssertionBuilderUIController.instance.getIfOperatorStage());
        isComplexOperator = true;
        anchorPaneHBoxGenerator1.setIsComplexOperator(true);
        anchorPaneHBoxGenerator1.setComplexOperator(this);
        anchorPaneHBoxGenerator1.setComplexUIController(AssertionBuilderUIController.instance.getComplexUIController());
        anchorPaneHBoxGenerator1.setCounterID(counterz);
        System.out.println("AssertionBuilderUIController.instance.getAssertionRuleCompVO() " + AssertionBuilderUIController.instance.getAssertionRuleCompVO().getAssertion());
        AssertionRuleCompVO compvo1 = AssertionBuilderUIController.instance.getAssertionRuleCompVO();
        AssertionRuleCompVO compvo = new AssertionRuleCompVO();//AssertionBuilderUIController.instance.getAssertionRuleCompVO();
        compvo.setAssertion(compvo1.getAssertion());
        compvo.setDisPlayMessage(compvo1.getDisPlayMessage());
        compvo.setErrorMessage(compvo1.getErrorMessage());
        anchorPaneHBoxGenerator1.setAssertionRuleCompVO(compvo);
        //anchorPaneHBoxGenerator1.setAssertionRuleCompVO(anchorPaneHBoxGenerator1.getComplexUIController());
        System.out.println("counterz " + counterz);
        mapp.put(counterz, anchorPaneHBoxGenerator1);
        operatorVbox.getChildren().add(anchorPaneHBoxGenerator1.getHboxContainer());
        anchorPaneHBoxGenerator1.setOperator(operator);

    }

    public CompoundUI4Operator generateCompoundUI() {
        CompoundUI4Operator compoundUI4Operator = new CompoundUI4Operator();

        return compoundUI4Operator;
    }

    public Button generateUI() {
        Button button = new Button();

        return button;
    }

    /**
     * @return the button
     */
    public Button getButton() {
        return button;
    }

    /**
     * @param button the button to set
     */
    public void setButton(Button button) {
        this.button = button;
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
     * @return the complexUIFXML
     */
    public AnchorPane getComplexUIFXML() {
        return complexUIFXML;
    }

    /**
     * @param complexUIFXML the complexUIFXML to set
     */
    public void setComplexUIFXML(AnchorPane complexUIFXML) {
        this.complexUIFXML = complexUIFXML;
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
     * @return the ifOPeratorScene
     */
    public Scene getIfOPeratorScene() {
        return ifOPeratorScene;
    }

    /**
     * @param ifOPeratorScene the ifOPeratorScene to set
     */
    public void setIfOPeratorScene(Scene ifOPeratorScene) {
        this.ifOPeratorScene = ifOPeratorScene;
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
}
