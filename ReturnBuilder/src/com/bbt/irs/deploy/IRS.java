/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.deploy;

import com.bbt.irs.controller.LoginController;
import com.bbt.irs.util.ConfigUtility;
import com.bbt.irs.util.ReadProperty4romXML;
import com.bbt.irs.util.Utility;
import com.bbt.irs.vo.HeaderInfoVO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Pair;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.w3c.dom.Document;

/**
 *
 * @author opeyemi
 */
public class IRS extends Application implements Messages, ErrorNameDesc {

    /**
     * @return the createdTableNames
     */
    public static List<String> getCreatedTableNames() {
        return createdTableNames;
    }

    /**
     * @param aCreatedTableNames the createdTableNames to set
     */
    public static void setCreatedTableNames(List<String> aCreatedTableNames) {
        createdTableNames = aCreatedTableNames;
    }

    /**
     * @return the em
     */
    public static EntityManager getEm() {
        if(em==null || !em.isOpen()){
            try {
                em = ReadProperty4romXML.populatePersistenceFile();
            } catch (Exception ex) {
                LOGGER.log(Level.FATAL, "Unable to populate persistence file", ex);;
            }
        }
        return em;
    }

    /**
     * @return the entityManagerFactory
     */
    public static EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }


    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(IRS.class);

    /**
     * @return the headerInfo
     */
    public static AnchorPane getHeaderInfo() {
        return headerInfo;
    }

    /**
     * @param aHeaderInfo the headerInfo to set
     */
    public static void setHeaderInfo(AnchorPane aHeaderInfo) {
        headerInfo = aHeaderInfo;
    }

    /**
     * @return the uploadXlsUI
     */
    public static AnchorPane getUploadXlsUI() {
        return uploadXlsUI;
    }

    /**
     * @param aUploadXlsUI the uploadXlsUI to set
     */
    public static void setUploadXlsUI(AnchorPane aUploadXlsUI) {
        uploadXlsUI = aUploadXlsUI;
    }

    /**
     * @return the basicInfoRoot
     */
    public static AnchorPane getBasicInfoRoot() {
        return basicInfoRoot;
    }

    /**
     * @param aBasicInfoRoot the basicInfoRoot to set
     */
    public static void setBasicInfoRoot(AnchorPane aBasicInfoRoot) {
        basicInfoRoot = aBasicInfoRoot;
    }

    /**
     * @return the headerInfoCompleted
     */
    public static boolean isHeaderInfoCompleted() {
        return headerInfoCompleted;
    }

    /**
     * @param aHeaderInfoCompleted the headerInfoCompleted to set
     */
    public static void setHeaderInfoCompleted(boolean aHeaderInfoCompleted) {
        headerInfoCompleted = aHeaderInfoCompleted;
    }

    /**
     * @return the excelPath
     */
    public static String getExcelPath() {
        return excelPath;
    }

    /**
     * @param aExcelPath the excelPath to set
     */
    public static void setExcelPath(String aExcelPath) {
        excelPath = aExcelPath;
    }

    /**
     * @return the tableNames
     */
    public static LinkedList<String> getTableNames() {
        return tableNames;
    }

    /**
     * @param aTableNames the tableNames to set
     */
    public static void setTableNames(LinkedList<String> aTableNames) {
        tableNames = aTableNames;
    }

    /**
     * @return the document
     */
    public static Document getDocument() {
        return document;
    }

    /**
     * @param aDocument the document to set
     */
    public static void setDocument(Document aDocument) {
        document = aDocument;
    }

    /**
     * @return the poisheet
     */
    public static XSSFSheet getPoisheet() {
        return poisheet;
    }

    /**
     * @param aPoisheet the poisheet to set
     */
    public static void setPoisheet(XSSFSheet aPoisheet) {
        poisheet = aPoisheet;
    }

    /**
     * @return the totalExcelRowno
     */
    public static int getTotalExcelRowno() {
        return totalExcelRowno;
    }

    /**
     * @param aTotalExcelRowno the totalExcelRowno to set
     */
    public static void setTotalExcelRowno(int aTotalExcelRowno) {
        totalExcelRowno = aTotalExcelRowno;
    }

    /**
     * @return the totalExcelColno
     */
    public static int getTotalExcelColno() {
        return totalExcelColno;
    }

    /**
     * @param aTotalExcelColno the totalExcelColno to set
     */
    public static void setTotalExcelColno(int aTotalExcelColno) {
        totalExcelColno = aTotalExcelColno;
    }

    /**
     * @return the finalCounter
     */
    public static int getFinalCounter() {
        return finalCounter;
    }

    /**
     * @param aFinalCounter the finalCounter to set
     */
    public static void setFinalCounter(int aFinalCounter) {
        finalCounter = aFinalCounter;
    }

    /**
     * @return the allHeaderInfo
     */
    public static LinkedHashMap<Integer, LinkedList<HeaderInfoVO>> getAllHeaderInfo() {
        return allHeaderInfo;
    }

    /**
     * @param aAllHeaderInfo the allHeaderInfo to set
     */
    public static void setAllHeaderInfo(LinkedHashMap<Integer, LinkedList<HeaderInfoVO>> aAllHeaderInfo) {
        allHeaderInfo = aAllHeaderInfo;
    }

    /**
     * @return the linkedHashMap
     */
    public static LinkedHashMap getLinkedHashMap() {
        return linkedHashMap;
    }

    /**
     * @param aLinkedHashMap the linkedHashMap to set
     */
    public static void setLinkedHashMap(LinkedHashMap aLinkedHashMap) {
        linkedHashMap = aLinkedHashMap;
    }

   private static void Configure() {
        try {
            ConfigUtility.loadProperties();
        } catch (IOException ex) {
            IRSDialog.showAlert(ERROR, ERROR_LOAD_PROPERTY_FILE);
            LOGGER.log(Level.FATAL, ERROR_LOAD_PROPERTY_FILE, ex);
        }
        try {
            //        entityManagerFactory = ReadProperty4romXML.populatePersistenceFile();//LOGGER
            em = ReadProperty4romXML.populatePersistenceFile();//LOGGER
            
        } catch (Exception ex) {
            IRSDialog.showAlert("Error", "Unable to read or save property of XML");
           LOGGER.log(Level.FATAL, "Unable to read or save property of XML", ex);
        }
        
    }

    @Override
    public void start(Stage stage) throws Exception {

        boolean lock = false;
        boolean open = false;

        instance = this;
        this.loginStage = stage;
        // lock = Authenticator.checkApplicationLock();
        if (lock) {
            open = IRSDialog.showConfirmDialog("Error", "Application Locked.\r\n Do you want to unlock?");
            System.out.println("ADADADA" + open);
            if (open) {
                final Stage mystage = IRSDialog.createDialogStage();
                FXMLLoader loader = new FXMLLoader();

                Parent root = (Parent) loader.load(getClass().getResourceAsStream("/com/bbt/irs/fxml/dialogUnlockAppLock.fxml"));

                Scene scene = new Scene(root);
                scene.getStylesheets().add(getClass().getResource("mystyle.css").toExternalForm());
                scene.getStylesheets().add("/com/bbt/irs/teststyle.css");
                mystage.setScene(scene);
                mystage.setHeight(400.0D);
                mystage.setWidth(600.0D);
                mystage.showAndWait();

            }
            return;
        }

        loadLoginStage();
        Configure();

    }

    public static IRS getInstance() {
        return instance;
    }

    /**
     * @return the mainStage
     */
    public Stage getMainStage() {
        return mainStage;
    }

    /**
     * @param mainStage the mainStage to set
     */
    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    public boolean loadLoginStage() throws IOException {

        boolean out;
        if (getMainStage() != null) {
            out = new IRSDialog().showConfirmDialog("User Logout", "Do you want to sign out of the application?");
            if (out) {
                mainStage.close();
                mainStage = null;

            } else {
                return false;
            }

        }
        FXMLLoader loader = new FXMLLoader();
        Parent root = (Parent) loader.load(getClass().getResourceAsStream(FXMLPATH + "login.fxml"));
        loginController = loader.getController();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/com/bbt/irs/resources/css/mystyle_1.css").toExternalForm());
        scene.getStylesheets().add(getClass().getResource("/com/bbt/irs/resources/css/test.css").toExternalForm());
        scene.getStylesheets().add(getClass().getResource("/com/bbt/irs/resources/css/calendarstyle.css").toExternalForm());
        scene.getStylesheets().add(getClass().getResource("/com/bbt/irs/resources/css/control.css").toExternalForm());

        loginStage.setScene(scene);
        loginStage.setWidth(560);
        loginStage.setHeight(400);
        loginStage.setResizable(false);
        setApplicationStartEventHandlers(loginStage);
        setApplicationCloseEventHandlers(loginStage);
        setApplicationStopEventHandlers(loginStage);
        loginStage.show();
//        try {
//            //        entityManagerFactory = ReadProperty4romXML.populatePersistenceFile();//LOGGER
//            em = ReadProperty4romXML.populatePersistenceFile();//LOGGER
//            
//        } catch (Exception ex) {
//            IRSDialog.showAlert("Error", "Unable to read or save property of XML");
//           LOGGER.log(Level.FATAL, "Unable to read or save property of XML", ex);
//        }
        return true;
    }

    public void enterApplication() throws Exception {

        try {

            FXMLLoader loader = new FXMLLoader();
            Parent root = (Parent) loader.load(getClass().getResourceAsStream(FXMLPATH + "main.fxml"));
            scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource(CSSPATH + "calendar_styles.css").toExternalForm());
            scene.getStylesheets().add(getClass().getResource(CSSPATH + "mystyle.css").toExternalForm());
            scene.getStylesheets().add(getClass().getResource(CSSPATH + "test.css").toExternalForm());
            scene.getStylesheets().add(getClass().getResource(CSSPATH + "calendarstyle.css").toExternalForm());
            scene.getStylesheets().add(CSSPATH + "teststyle.css");
            Stage stage = new Stage();
            setMainStage(stage);
            stage.setScene(scene);
            setApplicationStopEventHandlers(stage);
            stage.show();
            Screen screen = Screen.getPrimary();
            Rectangle2D bounds = screen.getVisualBounds();
            stage.setX(bounds.getMinX());
            stage.setY(bounds.getMinY());
            stage.setHeight(bounds.getHeight());
            stage.setWidth(bounds.getWidth());
            stage.setResizable(false);
////            IdleMonitor.start();
//            //stage.setFullScreen(true);
            loginStage.close();

        } catch (Exception ex) {
            //logger.info(ex);
            ex.printStackTrace();
            IRSDialog.showAlert("Error", "Error while starting application.");
        }
    }

    private void setApplicationStartEventHandlers(Stage stage) {
        stage.setOnShowing(new EventHandler() {
            @Override
            public void handle(Event t) {
                // logger.info("Showing stage.");
            }
        });
    }

    private void setApplicationCloseEventHandlers(Stage stage) {

        stage.setOnCloseRequest(new EventHandler() {
            boolean getout = false;

            @Override
            public void handle(Event t) {
                //logger.info("GGGGGGGGGGGGG" + t.getEventType());
                getout = new IRSDialog().showConfirmDialog("User Logout", "Do you want to close the application?");
                if (getout) {

                    // logger.info("Hiding stage.");
                    mainStage = null;
                } else {
                    if (mainStage != null) {
                        mainStage.showAndWait();
                    }
                    return;
                }
            }
        });
    }

    private void setApplicationStopEventHandlers(Stage stage) {
        stage.setOnCloseRequest(new EventHandler() {
            boolean getout = false;

            @Override
            public void handle(Event t) {
                System.out.println(t.getEventType());
                System.out.println(t.getTarget());
                getout = new IRSDialog().showConfirmDialog("User Logout", "Do you want to close the application?");
                if (getout) {
                    // logger.info("Hiding stage.");

                    if (mainStage != null) {
                        mainStage.close();
                        mainStage = null;
                    }
                } else {
                    mainStage.showAndWait();
                    return;
                }
            }
        });
    }

    public static List<Pair<Integer, Integer>> getHeadersPairs() {
        List<Pair<Integer, Integer>> ls = new ArrayList();
        System.out.println("");
        for (int i = 0; i < getAllHeaderInfo().size(); i++) {
            LinkedList<HeaderInfoVO> linkedList = getAllHeaderInfo().get(i + 1);
            System.out.println("pairs size " + linkedList.size());
            linkedList.forEach((linkedList1) -> {
                ls.add(Utility.convertExcelCellToPair(linkedList1.getCellNO()));
            });
        }
        return ls;
    }
    private Stage mainStage;
    private LoginController loginController;
    private static IRS instance;
    public static String loginUser;
    public static Stage loginStage;

    /**
     * It contains the object based on the order of the front end interface
     */
    public static LinkedHashMap linkedHashMap = new LinkedHashMap();
    public static ArrayList<HeaderInfoVO> list = new ArrayList();
    public static int numberOfTables = 0;
    private static LinkedHashMap<Integer, LinkedList<HeaderInfoVO>> allHeaderInfo = new LinkedHashMap();
    private static int finalCounter = 1;
    private static int totalExcelRowno;
    private static int totalExcelColno;
    private static XSSFSheet poisheet;
    private static Document document;
    private static LinkedList<String> tableNames = new LinkedList();
    private static String excelPath;
    private static boolean headerInfoCompleted = false;
    private static AnchorPane basicInfoRoot;
    private static AnchorPane uploadXlsUI;
    private static AnchorPane headerInfo;
    private static  EntityManagerFactory entityManagerFactory=null;
    private static  EntityManager em;
    private static  List<String> createdTableNames;
    /**
     * This was introduced to make previous button to work
     */
    private static LinkedList<AnchorPane> headerInfoList = new LinkedList();
    public static Scene scene;

    /**
     * @return the headerInfoList
     */
    public static LinkedList<AnchorPane> getHeaderInfoList() {
        return headerInfoList;
    }

    /**
     * @param aHeaderInfoList the headerInfoList to set
     */
    public static void setHeaderInfoList(LinkedList<AnchorPane> aHeaderInfoList) {
        headerInfoList = aHeaderInfoList;
    }
}
