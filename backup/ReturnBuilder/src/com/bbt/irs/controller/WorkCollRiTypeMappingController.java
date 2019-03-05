/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.controller;

import com.bbt.irs.dao.RITypeDAO;
import com.bbt.irs.deploy.IRSDialog;
import com.bbt.irs.deploy.Messages;
import static com.bbt.irs.deploy.Messages.FXMLPATH;
import com.bbt.irs.entity.TCoreRiType;
import com.bbt.irs.exception.WorkCollectionNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author tkola
 */
public class WorkCollRiTypeMappingController implements Initializable, Messages {

    @FXML
    ComboBox<TCoreRiType> riTypeCollection;
    @FXML
    AnchorPane modificationPane;
    @FXML
    TableView childtableView;
    WorkCollRiTypeMapChildController workCollRiTypeMapChildController;
    public static WorkCollRiTypeMappingController instance;
    
    public WorkCollRiTypeMappingController(){
       instance  = this;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            List<TCoreRiType> ls = new RITypeDAO().getRIype();
            System.out.println("ls  "+ls.size());
            riTypeCollection.setItems(FXCollections.observableArrayList(ls));
            instance=this;
            addListener2eriTypeCol();
        } catch (Exception ex) {
            IRSDialog.showAlert("ERROR", "WorkCollection can not be loaded");
            Logger.getLogger(WorkCollRiTypeMappingController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addListener2eriTypeCol() {
        instance=this;
        riTypeCollection.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<TCoreRiType>() {
            @Override
            public void changed(ObservableValue observable, TCoreRiType oldValue, TCoreRiType newValue) {
                try {
                    loadChildFXML();
                } catch (IOException ex) {
                    Logger.getLogger(WorkCollRiTypeMappingController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        );

    }

    public void loadChildFXML() throws IOException {

        FXMLLoader loader = new FXMLLoader();

        Parent root = (Parent) loader.load(getClass().getResourceAsStream(FXMLPATH + "workcolmanageChild.fxml"));
        workCollRiTypeMapChildController = (WorkCollRiTypeMapChildController) loader.getController();
        AnchorPane.setTopAnchor(root, 0.0);
        AnchorPane.setRightAnchor(root, 0.0);
        AnchorPane.setBottomAnchor(root, 0.0);
        AnchorPane.setLeftAnchor(root, 0.0);
        modificationPane.getChildren().clear();
        modificationPane.getChildren().add((AnchorPane) root);
    }

}
