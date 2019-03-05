/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.controller;

import com.bbt.irs.deploy.IRS;
import com.bbt.irs.deploy.IRSDialog;
import static com.bbt.irs.deploy.Messages.FXMLPATH;
import com.bbt.irs.exception.WebservicesException;
import com.bbt.irs.rest.client.RBRestClient;
import com.bbt.irs.vo.RuleSetVO;
import com.bbt.irs.vo.RuleVO;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author tkola
 */
public class ModifyviewassertionController implements Initializable {

    ObservableList<AssertionRuleView> ruleList = FXCollections.observableArrayList();
    List<RuleVO> rulevo;
    @FXML
    private StackPane rootPane;
    @FXML
    private TableView<AssertionRuleView> tableView;
    @FXML
    private TableColumn<AssertionRuleView, String> aliasCol;
    @FXML
    private TableColumn<AssertionRuleView, String> connectCol;
    @FXML
    private TableColumn<AssertionRuleView, String> ruleMessageCol;
    private Stage modifyViewstage;
    
    private static String ruleSetCode;
    List<RuleVO> ls;
    private Map<String,RuleVO> actualVOMap;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initCol();

//        loadData();
    }

    private void initCol() {

        aliasCol.setCellValueFactory(new PropertyValueFactory<>("alias"));
        connectCol.setCellValueFactory(new PropertyValueFactory<>("connect"));
        ruleMessageCol.setCellValueFactory(new PropertyValueFactory<>("ruleMessage"));

    }

    public void loadData() {
        try {
            System.out.println("loaddata ruleSetCode " + ruleSetCode);
            RBRestClient rbRestClient = new RBRestClient();
            RuleSetVO rulesetvo = rbRestClient.assertrulesview(ruleSetCode);
            rulevo = rulesetvo.getRules();
//            ruleList = FXCollections.observableArrayList();
            ruleList.clear();
            tableView.getItems().clear();
            actualVOMap = new HashMap();
            if(rulevo==null){
                IRSDialog.showAlert("INFO", "No rule associated with the rule set");
                return;
            }
            for (RuleVO r : rulevo) {
                System.out.println("r.getErrorMessage() "+r.getErrorMessage());
                AssertionRuleView assertionRuleView = new AssertionRuleView(r.getAlias(), r.getConnect(), r.getRuleMessage(), r.getRuleErrormessage(), r.getRuleAssertion());
                ruleList.add(assertionRuleView);
                actualVOMap.put(r.getAlias(), r);
            }
        } catch (WebservicesException ex) {
            Logger.getLogger(ModifyviewassertionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("size of "+ruleList.size());
        
        tableView.setItems(ruleList);
    }

    @FXML
    public void refreshAssertion(ActionEvent e) {
       loadData();
    }

    @FXML
    public void viewRuleDetail(ActionEvent e) {
        AssertionRuleView selectedRuleForDetail = tableView.getSelectionModel().getSelectedItem();
        try {

            if (selectedRuleForDetail == null) {
                IRSDialog.showAlert("No rule selected", "Please select a rule for detail.");
                return;
            }
            FXMLLoader loader = new FXMLLoader();
            Parent root = (Parent) loader.load(getClass().getResourceAsStream(FXMLPATH + "assertionruledetailview.fxml"));
            Assertionruledetailviewcontroller cont = (Assertionruledetailviewcontroller) loader.getController();
            cont.loadDetail(selectedRuleForDetail);
            
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.centerOnScreen();
            stage.initOwner(IRS.getInstance().getMainStage());
            stage.setTitle("Rule Details ");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            
            stage.showAndWait();

        } catch (IOException ex) {
            ex.printStackTrace();
            IRSDialog.showAlert("ERROR", "Error,Unable to load the Requested UI");
        }
    }

    @FXML
    public void editAssertion(ActionEvent e) {
        AssertionRuleView selectedRuleForDetail = tableView.getSelectionModel().getSelectedItem();
        try {

            if (selectedRuleForDetail == null) {
                IRSDialog.showAlert("No rule selected", "Please select a rule for detail.");
                return;
            }
            FXMLLoader loader = new FXMLLoader();
            Parent root = (Parent) loader.load(getClass().getResourceAsStream(FXMLPATH + "assertionruleremoval.fxml"));
            AssertionRemovalController cont = (AssertionRemovalController) loader.getController();
            cont.loadDetail(selectedRuleForDetail);
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.centerOnScreen();
            cont.setActualVOMap(actualVOMap);
            cont.setRemovalStage(stage);
            stage.initOwner(IRS.getInstance().getMainStage());
            stage.setTitle("Rule Details ");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.showAndWait();

        } catch (IOException ex) {
            ex.printStackTrace();
            IRSDialog.showAlert("ERROR", "Error,Unable to load the Requested UI");
        }
    }

    public static class AssertionRuleView {

        private final SimpleStringProperty alias;
        private final SimpleStringProperty connect;
        private final SimpleStringProperty ruleMessage;
        private final SimpleStringProperty ruleErrorMessage;
        private final SimpleStringProperty ruleAssertionMessage;

        public AssertionRuleView(String alias, String connect, String ruleMessage, String ruleErrorMessage, String ruleAssertionMessage) {
            this.alias = new SimpleStringProperty(alias);
            this.connect = new SimpleStringProperty(connect);
            this.ruleMessage = new SimpleStringProperty(ruleMessage);
            this.ruleErrorMessage = new SimpleStringProperty(ruleErrorMessage);
            this.ruleAssertionMessage = new SimpleStringProperty(ruleAssertionMessage);
        }

        /**
         * @return the alias
         */
        public String getAlias() {
            return alias.get();
        }

        /**
         * @return the connect
         */
        public String getConnect() {
            return connect.get();
        }

        /**
         * @return the ruleMessage
         */
        public String getRuleMessage() {
            return ruleMessage.get();
        }

        /**
         * @return the ruleAssertionMessage
         */
        public String getRuleAssertionMessage() {
            return ruleAssertionMessage.get();
        }

        /**
         * @return the ruleErrorMessage
         */
        public String getRuleErrorMessage() {
            return ruleErrorMessage.get();
        }

    }

    /**
     * @return the ruleSetCode
     */
    public String getRuleSetCode() {
        return ruleSetCode;
    }

    /**
     * @param ruleSetCode the ruleSetCode to set
     */
    public void setRuleSetCode(String ruleSetCode) {
        this.ruleSetCode = ruleSetCode;
    }

    /**
     * @return the modifyViewstage
     */
    public Stage getModifyViewstage() {
        return modifyViewstage;
    }

    /**
     * @param modifyViewstage the modifyViewstage to set
     */
    public void setModifyViewstage(Stage modifyViewstage) {
        this.modifyViewstage = modifyViewstage;
    }

}
