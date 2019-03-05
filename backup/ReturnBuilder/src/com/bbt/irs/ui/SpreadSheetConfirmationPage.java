/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.ui;

import com.bbt.irs.controller.MainController;
import com.bbt.irs.deploy.IRS;
import com.bbt.irs.deploy.IRSDialog;
import com.bbt.irs.deploy.Messages;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.apache.logging.log4j.LogManager;
import org.controlsfx.control.spreadsheet.SpreadsheetView;

/**
 *
 * @author tkola
 */
public class SpreadSheetConfirmationPage implements Messages {

    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(TablesUI.class);

    /**
     * @return the instance
     */
    public static SpreadSheetConfirmationPage getInstance() {
        if (instance == null) {
            instance = new SpreadSheetConfirmationPage();
        }
        return instance;
    }

    public static void setInstance(SpreadSheetConfirmationPage instance) {
        SpreadSheetConfirmationPage.instance = instance;
    }
    private static SpreadSheetConfirmationPage instance;
    public AnchorPane root = new AnchorPane();
    ScrollPane scroll = new ScrollPane();
    AnchorPane subRoot = new AnchorPane();
    BorderPane bpane = new BorderPane();
    GridPane gpane = new GridPane();

    VBox vbox = new VBox();

    Button submtButton = new Button("Submit");
    Button backButton = new Button("Back");
    Separator separator = new Separator();
    // ComboBox  combo = new ComboBox();

    private SpreadSheetConfirmationPage() {
        
        SpreadsheetView view = IRS.uploadTemplateUI.getXlsView4Upload();//(SpreadSheetView view)
        HBox hbox = new HBox();
        hbox.getStyleClass().add("hbox");
        hbox.getChildren().addAll(backButton, submtButton);
        vbox.getChildren().addAll(view, separator, hbox);
        gpane.getChildren().add(vbox);
        gpane.setAlignment(Pos.CENTER);
        bpane.setTop(gpane);
        subRoot.getChildren().add(bpane);

        AnchorPane.setTopAnchor(bpane, 0.0);
        AnchorPane.setRightAnchor(bpane, 0.0);
        AnchorPane.setBottomAnchor(bpane, 0.0);
        AnchorPane.setLeftAnchor(bpane, 0.0);
        AnchorPane.setTopAnchor(subRoot, 0.0);
        AnchorPane.setRightAnchor(subRoot, 0.0);
        AnchorPane.setBottomAnchor(subRoot, 0.0);
        AnchorPane.setLeftAnchor(subRoot, 0.0);
        AnchorPane.setTopAnchor(scroll, 0.0);
        AnchorPane.setRightAnchor(scroll, 0.0);
        AnchorPane.setBottomAnchor(scroll, 0.0);
        AnchorPane.setLeftAnchor(scroll, 0.0);
        subRoot.setPrefWidth(870.0);
        scroll.setContent(subRoot);
        root.getChildren().add(scroll);
        /**
         * ******************************action part ************
         */
        submtButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                processNextButton(event);
            }

        });

        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                processBackButton(event);
            }

        });

    }

    public void processBackButton(ActionEvent event) {
        UploadTemplateUI ui  = (UploadTemplateUI) IRS.pageHistory.get(UPLOAINFOHISTORY);
        AnchorPane previousContent = ui.root;
        if (previousContent != null) {
//            IRS.getInstance().activateButton(false, true, false, false, false, false, false, false, false, false, false, false, false);
            UploadTemplateUI.setInstance((UploadTemplateUI)ui);
            AnchorPane previous = ((AnchorPane) IRS.scene.lookup("#centerPane"));
            previous.getChildren().clear();
            previous.getChildren().add(previousContent);
           IRS.getInstance().activateButton(IRS.getInstance().appMenuList,(Button)event.getSource());      
           
        } else {
            IRSDialog.showAlert("Info", "There is no existing history for upload page");
        }
    }

    public void processNextButton(ActionEvent event) {
        try {
            
            IRS.spreadSheetUI = SpreadSheetConfirmationPage.instance;
            IRS.pageHistory.put(CONFIRMFOHISTORY, SpreadSheetConfirmationPage.instance);
            MainController.getInstance().loadTableUIPage();
            IRS.getInstance().activateButton(IRS.getInstance().appMenuList,(Button)event.getSource()); 
//            IRS.getInstance().activateButton(false, false, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false);
        } catch (IOException ex) {
            IRSDialog.showAlert("Error", " Unable to display the column Info ");
            LOGGER.log(org.apache.logging.log4j.Level.FATAL, "Unable to display the column Info", ex);

        }
    }
}
