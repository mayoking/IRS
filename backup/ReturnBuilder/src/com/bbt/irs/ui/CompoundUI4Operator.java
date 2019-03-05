/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.ui;

import com.bbt.irs.deploy.IRS;
import com.bbt.irs.deploy.IRSDialog;
import static com.bbt.irs.deploy.Messages.CSSPATH;
import static com.bbt.irs.deploy.Messages.FXMLPATH;
import com.bbt.irs.exception.DatabaseException;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author tkola
 */
public class CompoundUI4Operator {
    
    
    
    public AnchorPane loadCompoundUI(){
        
        return null;
    }
        private void getIfUI() throws DatabaseException {

        try {
            //FXMLLoader loader = new FXMLLoader(getClass().getResource("/library/assistant/ui/addbook/syncadd.fxml"));
            FXMLLoader loader = new FXMLLoader();
            Parent root = (Parent) loader.load(getClass().getResourceAsStream(FXMLPATH + "assertionrulereview.fxml"));            
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Review");
            Scene reviewScene = new Scene(root);
            IRS.scene.getStylesheets().add(getClass().getResource(CSSPATH + "mystyle.css").toExternalForm());
            //reviewScene.getStylesheets().add(getClass().getResource(CSSPATH + "mystyle.css").toExternalForm());
            stage.setScene(reviewScene);
            stage.show();

//            stage.setOnCloseRequest((e) -> {
//                handleRefresh(new ActionEvent());
//            });

        } catch (IOException ex) {
            IRSDialog.showAlert("ERROR", "Error,Unable to show Review");
        }
    }
    
}
