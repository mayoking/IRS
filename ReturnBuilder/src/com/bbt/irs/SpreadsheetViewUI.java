/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs;

import com.bbt.irs.dao.TestDAO;
import com.bbt.irs.util.Utility;
import com.bbt.irs.xml.XMLGenerator;
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
import org.controlsfx.control.spreadsheet.SpreadsheetView;

/**
 *
 * @author opeyemi
 */
public class SpreadsheetViewUI {

    AnchorPane root = new AnchorPane();
    ScrollPane scroll = new ScrollPane();
    AnchorPane subRoot = new AnchorPane();
    BorderPane bpane = new BorderPane();
    GridPane gpane = new GridPane();

    VBox vbox = new VBox();

    Button btn = new Button("Submit");
    Button btn1 = new Button("Back");
    Separator separator = new Separator();

    public SpreadsheetViewUI(SpreadsheetView view) {
        HBox hbox = new HBox();
        hbox.getStyleClass().add("hbox");
        hbox.getChildren().addAll(btn1,btn);
        vbox.getChildren().addAll(view, separator,hbox);
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
        subRoot.setPrefWidth(1150.0);
        scroll.setContent(subRoot);
        root.getChildren().add(scroll);

        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("size of IRS.linkedHashMap " + IRS.linkedHashMap.size());
                boolean result = TableGenerator.generateTable(IRS.linkedHashMap);
                if (result) {

                    XMLGenerator xmlGenerator = new XMLGenerator();
                    result = xmlGenerator.generateXML4romExcel(IRS.linkedHashMap);
                }
                if (result) {

                    result = populateStaticTables();
                    System.out.println(" result for table populator " + result);
                }
                System.out.println(" result for over all processes " + result);
                if (result) {
                   // Utility.showDialog("Creation of Returns", "The return has been created successfully", false);
                    IRSDialog.showAlert("Creation of Returns", "The return has been created successfully");
                }else{
                    //Utility.showDialog("Creation of Returns", "Error!!! while creating the return", true);
                    IRSDialog.showAlert("Creation of Returns", "Error!!! while creating the return");
                    
                }
            }
        });
        
        btn1.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                //btn2.getScene().setRoot(IRS.getBasicInfoRoot());
              AnchorPane previous=   ((AnchorPane)IRS.scene.lookup("#centerPane"));
              previous.getChildren().clear();
              previous.getChildren().add(IRS.getHeaderInfo());
            }
        });
    }
/**
 * The tables required to be populated are done through the method.
     * @return 
 **/
    public boolean populateStaticTables() {
//        IRSDAO irsDAO = new IRSDAO();
        TestDAO irsDAO = new TestDAO();
        boolean result = irsDAO.populateRequiredtables(IRS.linkedHashMap);
        return result;
    }

}
