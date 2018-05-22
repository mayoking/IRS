/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.controller;

import com.bbt.irs.ui.UploadTemplateUI;
import com.bbt.irs.deploy.ErrorNameDesc;
import com.bbt.irs.deploy.IRS;
import com.bbt.irs.deploy.IRSDialog;
import com.bbt.irs.deploy.MainTabPane;
import com.bbt.irs.deploy.MainTabPane1;
import com.bbt.irs.deploy.Messages;
import com.bbt.irs.deploy.TabPaneController;
import com.bbt.irs.deploy.Validation;
import com.bbt.irs.ui.SpreadsheetViewUI;
import com.bbt.irs.ui.HeaderInfo;
import com.bbt.irs.ui.BasicInfo;
import com.bbt.irs.util.Utility;
import com.bbt.irs.vo.BasicInfoVO;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.apache.logging.log4j.Level;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import org.controlsfx.control.spreadsheet.SpreadsheetView;

public class MainController implements Initializable, ErrorNameDesc, Messages {

    private static final Logger LOGGER = LogManager.getLogger(MainController.class);
    @FXML
    private BorderPane topPane;
    @FXML
    private VBox leftPane;
    @FXML
    private AnchorPane centerPane;
    @FXML
    private Label enrollmentId;
    @FXML
    private Label info;
    private Stage mainStage1;
    private MenuController menu;
    private TopBarController top;
    private MainTabPane main;
    private MainTabPane1 main1;
    private static MainController instance;
    private int headerFormCounter = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        instance = this;
        try {
            LOGGER.info("About to load menu and panes");
            loadSideMenu();
            loadBasicInfoPane();
            loadTopPane();
            LOGGER.info("Menu and Pane loaded");
        } catch (IOException ex) {
            IRSDialog.showAlert("Error", UNABLE_INITIALISE_MAIN_CONTROLLER);
            LOGGER.log(Level.FATAL, "Unable to initailise", ex);
        }
    }

    private void loadSideMenu() throws IOException {
        LOGGER.info("About to load side menu ");
        String myString = "ADMINISTRATOR";

        try {

            if (myString.equalsIgnoreCase(ROLE_ADMIN)) {

                FXMLLoader loader = new FXMLLoader();
                Parent root = (Parent) loader.load(getClass().getResource(FXMLPATH+"menu_1.fxml"));
                menu = (MenuController) loader.getController();
                VBox.setVgrow(root, Priority.ALWAYS);
                leftPane.getChildren().add(root);
            }

        } catch (IOException ex) {
            IRSDialog.showAlert("Error", ERROR_MENU);
            LOGGER.log(Level.FATAL, "ERROR_MENU", ex);
        }

    }

    public void loadBasicInfoPane() throws IOException {
        cleanUp();
        String myString = "ADMINISTRATOR";
        try {
            if (myString.equalsIgnoreCase(ROLE_ADMIN)) {
                LoadBasicInfoPane1();
            }
        } catch (IOException ex) {
            IRSDialog.showAlert(ERROR, ERROR_BASIC_INFO);
            LOGGER.log(Level.FATAL, ERROR, ex);
        }

    }

    public void loadTopPane() throws IOException {
        System.out.println("About to load Toppane");
        String myString = "ADMINISTRATOR";

        try {

            if (true) {
                FXMLLoader loader = new FXMLLoader();
               // loader.setLocation(getClass().getResource(""));
                Parent root = (Parent) loader.load(getClass().getResourceAsStream(FXMLPATH+"topbar.fxml"));
                top = (TopBarController) loader.getController();
                top.currentStatus.setText("Welcome   " + " " + " " + "Admin");
                top.userMessage.setText(getInfo());
                topPane.setCenter(root);

            }
        } catch (IOException ex) {
            IRSDialog.showAlert(ERROR, ERROR_TOP_BAR);
            LOGGER.log(Level.FATAL, ERROR_TOP_BAR, ex);

        }

    }

    public void reloadPane() {

        try {
            loadBasicInfoPane();
        } catch (IOException ex) {

        }

    }

    public void loadStaticHeaderForm() throws IOException {
        if (IRS.getLinkedHashMap().get(1) == null) {
            IRSDialog.showAlert("Static Header", BASIC_INFO_NOTFOUND);
            return;
        }
        BasicInfoVO basicInfo = (BasicInfoVO) IRS.getLinkedHashMap().get(1);
        int size = Integer.parseInt(basicInfo.getNumTables());
        for (int i = 0; i < size; i++) {
            IRS.setHeaderInfo(new HeaderInfo().root);
            AnchorPane.setTopAnchor(IRS.getHeaderInfo(), 0.0);
            AnchorPane.setRightAnchor(IRS.getHeaderInfo(), 0.0);
            AnchorPane.setBottomAnchor(IRS.getHeaderInfo(), 0.0);
            AnchorPane.setLeftAnchor(IRS.getHeaderInfo(), 0.0);
            top.currentStatus.setText("");
            top.userMessage.setText(getInfo());
            centerPane.getChildren().clear();
            centerPane.getChildren().add(IRS.getHeaderInfo());
        }
    }

    public void loadBasicInfo() throws IOException {
        IRS.setBasicInfoRoot(new BasicInfo().getPane());
        AnchorPane.setTopAnchor(IRS.getBasicInfoRoot(), 0.0);
        AnchorPane.setRightAnchor(IRS.getBasicInfoRoot(), 0.0);
        AnchorPane.setBottomAnchor(IRS.getBasicInfoRoot(), 0.0);
        AnchorPane.setLeftAnchor(IRS.getBasicInfoRoot(), 0.0);
        top.currentStatus.setText("");
        top.userMessage.setText(getInfo());
        centerPane.getChildren().clear();
        centerPane.getChildren().add(IRS.getBasicInfoRoot());
    }

    /**
     **This method provide interface to load upload UI
     *
     * @throws java.io.IOException
     */
    public void loadXLS() throws IOException {
        if (IRS.getLinkedHashMap().get(1) == null) {
            IRSDialog.showAlert("Basic Info", BASIC_INFO_NOTFOUND);
            return;
        }
        IRS.setUploadXlsUI(new UploadTemplateUI().root);
        AnchorPane.setTopAnchor(IRS.getUploadXlsUI(), 0.0);
        AnchorPane.setRightAnchor(IRS.getUploadXlsUI(), 0.0);
        AnchorPane.setBottomAnchor(IRS.getUploadXlsUI(), 0.0);
        AnchorPane.setLeftAnchor(IRS.getUploadXlsUI(), 0.0);
        top.currentStatus.setText("");
        top.userMessage.setText(getInfo());
        centerPane.getChildren().clear();
        centerPane.getChildren().add(IRS.getUploadXlsUI());
    }

    public void viewXLS(SpreadsheetView view) throws Exception {
        AnchorPane root = new SpreadsheetViewUI(view).root;
        AnchorPane.setTopAnchor(root, 0.0);
        AnchorPane.setRightAnchor(root, 0.0);
        AnchorPane.setBottomAnchor(root, 0.0);
        AnchorPane.setLeftAnchor(root, 0.0);
        top.currentStatus.setText("");
        top.userMessage.setText(getInfo());
        centerPane.getChildren().clear();
        centerPane.getChildren().add(root);
    }

    public static MainController getInstance() {
        return instance;
    }

    public MenuController getMenu() {
        return menu;
    }

    public TopBarController getTop() {
        return top;
    }

    public MainTabPane getMain() {
        return main;
    }

    public TabPaneController getTabPaneController() {

        return MainTabPane.getInstance();

    }

    public static String getMotherboardSN() {
        String result = "";
        try {
            // File file = new File("c:/tope/serial.vbs");
            File file = new File("serial.vbs");
            file.deleteOnExit();
            FileWriter fw = new java.io.FileWriter(file);

            String vbs
                    = "Set objWMIService = GetObject(\"winmgmts:\\\\.\\root\\cimv2\")\n"
                    + "Set colItems = objWMIService.ExecQuery _ \n"
                    + "   (\"Select * from Win32_BaseBoard\") \n"
                    + "For Each objItem in colItems \n"
                    + "    Wscript.Echo objItem.SerialNumber \n"
                    + "    exit for  ' do the first cpu only! \n"
                    + "Next \n";

            fw.write(vbs);
            fw.close();
            Process p = Runtime.getRuntime().exec("cscript //NoLogo " + file.getPath());
            BufferedReader input
                    = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = input.readLine()) != null) {
                result += line;
            }
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.trim().substring(1, 9);
    }
//

    private void cleanUp() {
        TabPaneController controller = getTabPaneController();
        if (controller != null) {
            controller.cleanUp();
        }
    }

    public void removeUserMsg() {
    }

    public String getInfo() {
        Format formatter = new SimpleDateFormat("EEEE, dd MMMM yyyy HH:mm:ss");
        Date date = new Date();
        String s = formatter.format(date);

        String appId = "ver1.0";
        String info = IRS.loginUser + ", You logged in on " + s;

        return info;
    }

    public void LoadBasicInfoPane1() throws IOException {

        cleanUp();

        String myString = null;

        try {

            if (true) {
                FXMLLoader loader = new FXMLLoader();

                Parent root = (Parent) loader.load(getClass().getResourceAsStream(FXMLPATH+"main_tab_pane_1.fxml"));
                main1 = (MainTabPane1) loader.getController();
                AnchorPane.setTopAnchor(root, 0.0);
                AnchorPane.setRightAnchor(root, 0.0);
                AnchorPane.setBottomAnchor(root, 0.0);
                AnchorPane.setLeftAnchor(root, 0.0);
                centerPane.getChildren().clear();
                centerPane.getChildren().add(root);
                //top.currentStatus.setText("Registration ID: " + getEnrollmentId());

            }

        } catch (Exception ex) {
            IRSDialog.showAlert(ERROR, ERROR_BASIC_INFO);
            LOGGER.log(Level.FATAL, ERROR, ex);

        }

    }

    public Stage getMainStage1() {
        return mainStage1;
    }

    /**
     * @param mainStage the mainStage to set
     */
    public void setMainStage1(Stage mainStage1) {
        this.mainStage1 = mainStage1;
    }

    public static String getCheckDigit(String dynamicNumber) {
        int total = 0;
        String result = null;
        if (Validation.isDigit(dynamicNumber)) {
            for (int i = 0; i < dynamicNumber.length(); i++) {
                int num = Integer.parseInt(dynamicNumber.substring(i, i + 1));
                total = total + num;

                LOGGER.info("this is the id checkdigit " + total);
            }
        }
        if (String.valueOf(total).length() > 1) {
            result = String.valueOf(total).substring(0, 1);
        } else {
            result = String.valueOf(total);
        }
        LOGGER.info("total is : " + total);
        return result;
    }

    public String padDigit(String digit) {
        String result = digit;
        if (digit != null && Validation.isDigit(digit)) {
            int numberOfZeros = 6 - digit.length();
            for (int i = 0; i < numberOfZeros; i++) {
                result = "0" + digit;
            }
        }
        return result;
    }

    public static String getMacAddres() {
        String macAddres = null;
        try {
            Process p = Runtime.getRuntime().exec("getmac /fo csv /nh");
            java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(p.getInputStream()));
            String line;
            line = in.readLine();
            String[] result = line.split(",");
            macAddres = result[0].replace('"', ' ').trim();
            System.out.println(result[0].replace('"', ' ').trim());
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(MainController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return macAddres;
    }
}
