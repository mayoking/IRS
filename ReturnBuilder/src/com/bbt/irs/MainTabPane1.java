/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ListChangeListener;
import javafx.collections.ListChangeListener.Change;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class MainTabPane1 implements Initializable, TabPaneController {

    private static MainTabPane1 instance;
    /**
     * This was added as a result of preventing woman to enter more than on
     * spouse*
     */
    public static boolean isMan = false;
    @FXML
    private TabPane tabPane1;
    

    private int invalidTab = -1;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        instance = this;
        loadTabs();

       // MainController.getInstance().setResidentInfo("");
        System.out.println("Main tabpane initialized.");
    }

    public static MainTabPane1 getInstance() {
        return instance;
    }

    @Override
    public void gotoNextTab() {
        int i = tabPane1.getSelectionModel().getSelectedIndex();
        tabPane1.getSelectionModel().getSelectedItem().setDisable(true);
        tabPane1.getSelectionModel().selectNext();
        tabPane1.getSelectionModel().getSelectedItem().setDisable(false);


    }

    @Override
    public void gotoPrevTab() {
        int i = tabPane1.getSelectionModel().getSelectedIndex();
        tabPane1.getSelectionModel().getSelectedItem().setDisable(true);
         System.out.println("previous tab "+i);
        if(i!=0){
            tabPane1.getSelectionModel().select(i-1);
            tabPane1.getSelectionModel().getSelectedItem().setDisable(false);
           
        }
        //tabPane.getSelectionModel().selectPrevious();
        //tabPane.getSelectionModel().getSelectedItem().setDisable(false);


    }

    public void goToTab(int index) {

        tabPane1.getSelectionModel().select(index);
    }

    public void disableSpouseTab() {
       // spouseTab.setDisable(true);
    }

    public void enableSpouseTab() {
       // spouseTab.setDisable(false);
    }

    public boolean checkSpouseTab() {
        boolean result = false;
       return result;

    }
    
     


    private void loadTabs() {
        try {
            loadPersonalInfoTab();
            loadContactInfoTab();
            loadWorkInfoTab();
            loadOtherInfoTab();
//            loadSpouseInfoTab();
//            loadDependantTab();
           // loadPhotoTab();
            loadSignatureTab();
            loadFingerPrintTab();
            loadSummaryTab();
            addListener();
        } catch (IOException ex) {
            Logger.getLogger(MainTabPane.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadPersonalInfoTab() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(""));
       // Parent root = (Parent) loader.load(getClass().getResourceAsStream("personal_info.fxml"));
        //personalInfoTab.setContent(root);
    }

    private void loadContactInfoTab() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(""));
       // Parent root = (Parent) loader.load(getClass().getResourceAsStream("contact_info.fxml"));
      //  contactInfoTab.setContent(root);
      //  contactInfoTab.setDisable(true);
    }

    private void loadWorkInfoTab() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(""));
        //Parent root = (Parent) loader.load(getClass().getResourceAsStream("work_info.fxml"));
       // workInfoTab.setContent(root);
       // workInfoTab.setDisable(true);
    }

    private void loadOtherInfoTab() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(""));
      
    }


    private void loadPhotoTab() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(""));
       // Parent root = (Parent) loader.load(getClass().getResourceAsStream("photo.fxml"));
        //photoTab.setContent(root);
       // photoController = loader.getController();
       // photoTab.setDisable(true);
    }

    private void loadSignatureTab() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(""));
       // Parent root = (Parent) loader.load(getClass().getResourceAsStream("signature.fxml"));
        //signatureTab.setContent(root);
        //signatureTab.setDisable(true);
       // System.out.println("Signature loaded.");
    }

    private void loadFingerPrintTab() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(""));
        //Parent root = (Parent) loader.load(getClass().getResourceAsStream("fingerprint.fxml"));
       // fingerPrintTab.setContent(root);
       /// fingerPrintTab.setDisable(true);
    }

    private void loadSummaryTab() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(""));
       // Parent root = (Parent) loader.load(getClass().getResourceAsStream("summary.fxml"));
       /// summaryTab.setContent(root);
        //summaryController = (SummaryController) loader.getController();
        //summaryTab.setDisable(true);
    }

    public void addListener() {
        tabPane1.getTabs().addListener(new ListChangeListener<Tab>() {
            @Override
            public void onChanged(Change<? extends Tab> change) {
                System.out.println("about call tabpane change listener");
                final ObservableList<Tab> tabs = tabPane1.getTabs();
                if (tabs.get(4).isSelected()) {
                }
            }
        });
    }

    @Override
    public Tab getPhotoTab() {
        return null;
    }

    @Override
    public void cleanUp() {
        
    }

    @Override
    public Tab getContactInfoTab() {
        return null;
    }

    @Override
    public Tab getWorkInfoTab() {
        return null;
    }

    public Tab getPersonalInfoTab() {
        return null;
    }

    @Override
    public Tab getFingerPrintTab() {
        return null;
    }

    @Override
    public Tab getOtherInfoTab() {
        return null;
    }

    @Override
    public int getSelectedTab() {
        int tabs =0;

        return tabs;
    }

    public int getInvalidTab() {
        return invalidTab;
    }

    public void setInvalidTab(int it) {
        invalidTab = it;
    }
    
    public void resetInvalidTab(){
        System.out.println("Invalid tab reset.");
        invalidTab = -1;
    }
}
