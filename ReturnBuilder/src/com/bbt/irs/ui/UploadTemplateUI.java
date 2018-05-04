/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.ui;

import com.bbt.irs.controller.MainController;
import com.bbt.irs.controller.MainController;
import com.bbt.irs.deploy.IRS;
import com.bbt.irs.ui.ExcelView;
import com.bbt.irs.vo.BasicInfoVO;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.controlsfx.control.spreadsheet.SpreadsheetView;

/**
 *
 * @author opeyemi
 */
public class UploadTemplateUI {
private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(UploadTemplateUI.class);
    /**
     * @return the excelPath
     */
    public String getExcelPath() {
        return excelPath;
    }

    /**
     * @param excelPath the excelPath to set
     */
    public void setExcelPath(String excelPath) {
        this.excelPath = excelPath;
    }
    
    private FileChooser fileChooser = new FileChooser();
    private String excelPath = null;
    
    
    public AnchorPane root = new AnchorPane();
    ScrollPane scroll = new ScrollPane();
    AnchorPane subRoot =  new AnchorPane();
    BorderPane bpane = new BorderPane();
    GridPane   gpane = new GridPane();
    
    VBox       vboxMain = new VBox();
    VBox       vbox = new VBox();
    HBox        hbox = new HBox();
    HBox       hbox1 = new HBox();
    
    Button btn = new Button("Submit");
    Button btn1 = new Button("Upload XLS");
    Button btn2 = new Button("Back");
    TextField xlsPath = new TextField();
    Separator separator = new Separator();
    
    public UploadTemplateUI(){
        
        btn1.setOnAction(this::upload4XLS);
        
        hbox.getChildren().addAll(btn2,btn);
        hbox1.getChildren().addAll(xlsPath,btn1);
        vbox.getChildren().addAll(hbox1,separator,hbox);
        gpane.setAlignment(Pos.CENTER);
        gpane.getChildren().add(vbox);
       // gpane.setAlignment(Pos.CENTER);
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
        subRoot.setPrefWidth(1150.0);
        scroll.setContent(subRoot);
        root.getChildren().add(scroll);
        
        addStyleClass();
        
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    //readXLS();
                    MainController.getInstance().loadStaticHeaderForm();
                } catch (IOException ex) {
                   LOGGER.log(Level.FATAL,"Unable to load loadStaticHeaderForm", ex);
                }
            }
        });
        
         btn2.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                //btn2.getScene().setRoot(IRS.getBasicInfoRoot());
              AnchorPane previous=   ((AnchorPane)IRS.scene.lookup("#centerPane"));
              previous.getChildren().clear();
              previous.getChildren().add(IRS.getBasicInfoRoot());
            }
        });
        
    }
    
    public final void addStyleClass() {
        hbox.getStyleClass().add("hbox");
        hbox1.getStyleClass().add("hbox");
        btn.getStyleClass().add("button");
        btn1.getStyleClass().add("button");
        xlsPath.getStyleClass().add("text-field");
        
    }
    /**
     This method is used to set the path of the to the XLS to upload
     
     **/
     public void upload4XLS(javafx.event.ActionEvent actionEvent) {
        System.out.println("About to get File chooser");
        getFileChooser().setTitle("Upload XLS");
        File file = getFileChooser().showOpenDialog(MainController.getInstance().getMainStage1());
        if (file != null) {
            setExcelPath(file.getAbsolutePath());
            xlsPath.setText(getExcelPath());
            IRS.setExcelPath(getExcelPath());

        }
    }
    
    /**
     * @return the fileChooser
     */
    public FileChooser getFileChooser() {
        return fileChooser;
    }

    /**
     * @param fileChooser the fileChooser to set
     */
    public void setFileChooser(FileChooser fileChooser) {
        this.fileChooser = fileChooser;
    }
    
    public void readXLS(){
        try {
            ExcelView view = new ExcelView(getExcelPath(),0,true);
            SpreadsheetView xlsView =  view.getView(getExcelPath());
            MainController.getInstance().viewXLS(xlsView);
        } catch (Exception ex) {
           LOGGER.log(Level.FATAL, "readXLS failed", ex);
        }
    }

    
}
