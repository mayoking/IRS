/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.deploy;


import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.collections.ListChangeListener;
import javafx.collections.ListChangeListener.Change;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;



public class MainTabPane implements Initializable, TabPaneController, ErrorNameDesc {

    private static MainTabPane instance;
    /**
     * This was added as a result of preventing woman to enter more than on
     * spouse*
     */
    public static boolean isMan = false;
    @FXML
    private TabPane tabPane;
    @FXML
    private Tab personalInfoTab;
    @FXML
    private Tab contactInfoTab;
    @FXML
    private Tab workInfoTab;
    @FXML
    private Tab otherInfoTab;
    @FXML
    private Tab spouseTab;
    @FXML
    private Tab dependantTab;
    @FXML
    private Tab photoTab;
    @FXML
    private Tab signatureTab;
    @FXML
    private Tab fingerPrintTab;
    @FXML
    private Tab summaryTab;

    private int invalidTab = -1;
    private Logger logger = Logger.getLogger(LOGGERNAME);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        instance = this;
        loadTabs();
       // residentInfo = new ResidentInfoVo();
       // MainController.getInstance().setResidentInfo(residentInfo);
        logger.info("Main tabpane initialized.");
    }

    public static MainTabPane getInstance() {
        return instance;
    }

    @Override
    public void gotoNextTab() {
        int i = tabPane.getSelectionModel().getSelectedIndex();
        tabPane.getSelectionModel().getSelectedItem().setDisable(true);
        tabPane.getSelectionModel().selectNext();
        tabPane.getSelectionModel().getSelectedItem().setDisable(false);


    }

    @Override
    public void gotoPrevTab() {
        int i = tabPane.getSelectionModel().getSelectedIndex();
        tabPane.getSelectionModel().getSelectedItem().setDisable(true);
        logger.info("previous tab " + i);
        if (i != 0) {
            tabPane.getSelectionModel().select(i - 1);
            tabPane.getSelectionModel().getSelectedItem().setDisable(false);

        }
        //tabPane.getSelectionModel().selectPrevious();
        //tabPane.getSelectionModel().getSelectedItem().setDisable(false);


    }

    public void goToTab(int index) {

        tabPane.getSelectionModel().select(index);
    }

    public void disableSpouseTab() {
        spouseTab.setDisable(true);
    }

    public void enableSpouseTab() {
        spouseTab.setDisable(false);
    }

    public boolean checkSpouseTab() {
        boolean result = false;
        if (spouseTab.disableProperty().get()) {
            result = true;
        }
        return result;

    }

  

    private void loadTabs() {
      
    }
    
    private void enableAllTabs(){
        personalInfoTab.setDisable(false);
        contactInfoTab.setDisable(false);
        workInfoTab.setDisable(false);
        otherInfoTab.setDisable(false);
        photoTab.setDisable(false);
        signatureTab.setDisable(false);
        fingerPrintTab.setDisable(false);
        summaryTab.setDisable(false);
    }

    

//    private void loadSpouseInfoTab() throws IOException{
//        final FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(getClass().getResource(""));
//        Parent root = (Parent) loader.load(getClass().getResourceAsStream("spouse.fxml"));
//        spouseTab.setContent(root);
//        spouseTab.onSelectionChangedProperty();
//        spouseTab.setOnSelectionChanged(new EventHandler() {
//            @Override
//            public void handle(Event event) {
//                if (spouseTab.isSelected()) {
//                 SpouseController tttt =  loader.getController();
//                 tttt.manageSex();
//                }
//                
//            }
//        });
////        spouseTab.setDisable(true);
//    }
//    
//    private void loadDependantTab() throws IOException{
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(getClass().getResource(""));
//        Parent root = (Parent) loader.load(getClass().getResourceAsStream("dependant.fxml"));
//        dependantTab.setContent(root);
////        dependantTab.setDisable(true);
//    }
//    
//    private void loadPhotoTab() throws IOException {
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(getClass().getResource(""));
//        Parent root = (Parent) loader.load(getClass().getResourceAsStream("photo.fxml"));
//        photoTab.setContent(root);
//        photoController = loader.getController();
//        photoTab.setDisable(true);
//    }

//    private void loadSignatureTab() throws IOException {
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(getClass().getResource(""));
//        Parent root = (Parent) loader.load(getClass().getResourceAsStream("signature.fxml"));
//        signatureTab.setContent(root);
//        signatureTab.setDisable(true);
//        logger.info("Signature loaded.");
//    }

//    private void loadFingerPrintTab() throws IOException {
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(getClass().getResource(""));
//        Parent root = (Parent) loader.load(getClass().getResourceAsStream("fingerprint.fxml"));
//        fingerPrintTab.setContent(root);
//        fingerPrintTab.setDisable(true);
//    }

//    private void loadSummaryTab() throws IOException {
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(getClass().getResource(""));
//        Parent root = (Parent) loader.load(getClass().getResourceAsStream("summary.fxml"));
//        summaryTab.setContent(root);
//        summaryController = (SummaryController) loader.getController();
//        summaryTab.setDisable(true);
//    }

    public void addListener() {
        tabPane.getTabs().addListener(new ListChangeListener<Tab>() {
            @Override
            public void onChanged(Change<? extends Tab> change) {
                logger.info("about call tabpane change listener");
                final ObservableList<Tab> tabs = tabPane.getTabs();
                if (tabs.get(4).isSelected()) {
                }
            }
        });
    }

    @Override
    public Tab getPhotoTab() {
        return photoTab;
    }

    @Override
    public void cleanUp() {
     
    }

    @Override
    public Tab getContactInfoTab() {
        return this.contactInfoTab;
    }

    @Override
    public Tab getWorkInfoTab() {
        return this.workInfoTab;
    }

    public Tab getPersonalInfoTab() {
        return this.personalInfoTab;
    }

    @Override
    public Tab getFingerPrintTab() {
        return this.fingerPrintTab;
    }

    @Override
    public Tab getOtherInfoTab() {
        return this.otherInfoTab;
    }

    @Override
    public int getSelectedTab() {
        int tabs = tabPane.getSelectionModel().getSelectedIndex();

        return tabs;
    }

    public int getInvalidTab() {
        return invalidTab;
    }

    public void setInvalidTab(int it) {
        invalidTab = it;
    }

    public void resetInvalidTab() {
        logger.info("Invalid tab reset.");
        invalidTab = -1;
    }
}
