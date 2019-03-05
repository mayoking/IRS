/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.controller;

import com.bbt.irs.deploy.IRS;
import com.bbt.irs.deploy.IRSDialog;
import com.bbt.irs.util.Utility;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.DirectoryChooser;

/**
 *
 * @author tkola
 */
public class XMLXSDActualController implements Initializable {

    @FXML
    private TextArea xsd;
    @FXML
    private TextArea xml;
    @FXML
    Button downloadxsd;
    @FXML
    Button downloadxml;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("This is the xml " + IRS.getInstance().xsd);
        xsd.setText(IRS.getInstance().xsd);
        xml.setText(IRS.getInstance().xml);
    }

    @FXML
    public void downloadxsd() {
        DirectoryChooser folderChooser = new DirectoryChooser();
        File file = folderChooser.showDialog(IRS.getInstance().getMainStage());
        String path = file.getAbsolutePath();
        Utility.writeToFile(xsd.getText(), file.getAbsolutePath()+File.separator+ XSDXMLViewController.xsdname);
        IRSDialog.showAlert("Info", "The XSD has been downloaded to the chosen directory");
    }

    @FXML
    public void downloadxml() {
         DirectoryChooser folderChooser = new DirectoryChooser();
        File file = folderChooser.showDialog(IRS.getInstance().getMainStage());
        String path = file.getAbsolutePath();
        Utility.writeToFile(xml.getText(), file.getAbsolutePath()+File.separator+ XSDXMLViewController.xmlname);
        IRSDialog.showAlert("Info", "The XML has been downloaded to the chosen directory");
       
    }

}
