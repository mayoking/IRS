/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.ui;

import com.bbt.irs.deploy.IRS;
import com.bbt.irs.entity.TRbDatasize;
import com.bbt.irs.entity.TRbDatatype;
import com.bbt.irs.util.Utility;
import com.bbt.irs.vo.BasicInfoVO;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import org.apache.logging.log4j.LogManager;

/**
 *
 * @author tkola
 */
public class HeaderInfoUI {

    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(UploadTemplateUI.class);
    private static List<LinkedHashMap> linekdHashMaps = new ArrayList();
    public ComboBox tableSelection = new ComboBox();
    List<TRbDatasize> dataSizes = null;
    List<TRbDatatype> dataType = null;

    public VBox vBoxContainer = new VBox();
    public AnchorPane root = new AnchorPane();
    AnchorPane rootnode = new AnchorPane();
    BorderPane borderPane = new BorderPane();
    VBox vbox = new VBox();
    
    List<CompositeVBox> result;

    public HeaderInfoUI() {
        BasicInfoVO info = (BasicInfoVO) IRS.getLinkedHashMap().get(1);
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        GridPane subRoot = new GridPane();
        List<String> collections = Utility.generateTableNames(info.getTemplateCode(), Integer.valueOf(info.getNumTables()));
        tableSelection.setItems(FXCollections.observableArrayList(collections));

        //vboxMain.getChildren().addAll(vbox, hbox);
        subRoot.getChildren().add(vBoxContainer);
        borderPane.setTop(subRoot);
        subRoot.setAlignment(Pos.CENTER);
        rootnode.getChildren().add(borderPane);
        AnchorPane.setTopAnchor(rootnode, 0.0);
        AnchorPane.setRightAnchor(rootnode, 0.0);
        AnchorPane.setBottomAnchor(rootnode, 0.0);
        AnchorPane.setLeftAnchor(rootnode, 0.0);
        AnchorPane.setTopAnchor(borderPane, 0.0);
        AnchorPane.setRightAnchor(borderPane, 0.0);
        AnchorPane.setBottomAnchor(borderPane, 0.0);
        AnchorPane.setLeftAnchor(borderPane, 0.0);
        AnchorPane.setTopAnchor(scrollPane, 0.0);
        AnchorPane.setRightAnchor(scrollPane, 0.0);
        AnchorPane.setBottomAnchor(scrollPane, 0.0);
        AnchorPane.setLeftAnchor(scrollPane, 0.0);
        rootnode.setPrefWidth(1150.0);
        scrollPane.setContent(rootnode);
        root.getChildren().add(scrollPane);

    }
    
    public void generateRangeVBox(int numTables) {
        System.out.println("number of tables is " + numTables);
        result = new ArrayList();
        
        for (int i = 0; i < numTables; i++) {
            result.add(new CompositeVBox("CellNo", "Cell Name", "Data Type", "Data Size",i));
        }
    }
    public final void setRangeVBox() {
        BasicInfoVO info = (BasicInfoVO) IRS.getLinkedHashMap().get(1);
        generateRangeVBox(Integer.parseInt(info.getNumTables()));
        System.out.println("about to add range container ");
        for (int i = 0; i < result.size(); i++) {
            System.out.println("adding range container");
            vBoxContainer.getChildren().addAll(result.get(i).getvContainer(),new Separator());
        }
    }

}
