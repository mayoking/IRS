/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.controller;

import com.bbt.irs.dao.RestrictionDataDAO;
import com.bbt.irs.deploy.IRS;
import static com.bbt.irs.deploy.IRS.getExcelPath;
import com.bbt.irs.deploy.IRSDialog;
import com.bbt.irs.exception.DatabaseException;
import com.bbt.irs.util.ExcelView;
import com.bbt.irs.vo.RefactorUploadObject;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

/**
 *
 * @author tkola
 */
public class UploadRestrictionData implements Initializable {
@FXML
TextField restrictioncode;
@FXML
TextField restrictionDescription;
@FXML
TextField uploadpathtextbox;
@FXML
ComboBox browseButton;

private FileChooser fileChooser = new FileChooser();
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
    @FXML
    public void upload4XLS(javafx.event.ActionEvent actionEvent) {
        fileChooser.setTitle("Upload Excel");

        File file = fileChooser.showOpenDialog(MainController.getInstance().getMainStage1());
        if (file != null) {
            uploadpathtextbox.setText(file.getAbsolutePath());
            IRS.setExcelPath(getExcelPath());

        }
    }
    
    @FXML
    public void uploadExcel(){
        String path = uploadpathtextbox.getText();
        String restrictionCode = restrictioncode.getText();
        String restrictionDesc = restrictionDescription.getText();
        if(path==null || path.isEmpty()){
            IRSDialog.showAlert("Info", "Select the path of the file to upload");
        }
         if(restrictionCode==null || restrictionCode.isEmpty()){
            IRSDialog.showAlert("Info", "Restriction code can not be blank ");
        }
        try {
            ExcelView processExcel = new ExcelView(path, 0);
            List<RefactorUploadObject> ls = processExcel.readExcelUploadObject(path);
            boolean result = new RestrictionDataDAO().addRestritionData(ls, restrictionCode, restrictionDesc);
            if (result) {
                IRSDialog.showAlert("Info", "Upload Successfully done");
            } else {
                IRSDialog.showAlert("Info", "Unable to upload the restriction data");
            }
        } catch (IOException iOException) {
            IRSDialog.showAlert("ERROR", "Fatal Error !!!");
        } catch (DatabaseException ex) {
              IRSDialog.showAlert("ERROR", ex.getMessage());
        Logger.getLogger(UploadRestrictionData.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    
}
