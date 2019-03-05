/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.controller;

import com.bbt.irs.deploy.IRSDialog;
import com.bbt.irs.entity.MetadataTable;
import com.bbt.irs.entity.TRbRestrictionCodes;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author tkola
 */
public class MainFieldModChildController implements Initializable {

    @FXML
    TableView<MainFieldModChildController.TableFieldDataModel> modifiedFieldTable;
    @FXML
    TableColumn<MainFieldModChildController.TableFieldDataModel, String> tableNameCol;
    @FXML
    TableColumn<MainFieldModChildController.TableFieldDataModel, String> fieldNameCol;
    @FXML
    TableColumn<MainFieldModChildController.TableFieldDataModel, String> restrictionCol;
    ObservableList<MainFieldModChildController.TableFieldDataModel> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initCol();
        loadData();
    }

    private void initCol() {
        tableNameCol.setCellValueFactory(new PropertyValueFactory<>("tableName"));
        fieldNameCol.setCellValueFactory(new PropertyValueFactory<>("tableField"));
        restrictionCol.setCellValueFactory(new PropertyValueFactory<>("fieldRestriction"));

    }

    public void loadData() {
        String tableame = MainFieldModificationController.instance.table.getSelectionModel().getSelectedItem();
        MetadataTable colName = MainFieldModificationController.instance.colname.getSelectionModel().getSelectedItem();
        TRbRestrictionCodes restTpeCode = MainFieldModificationController.instance.restType.getSelectionModel().getSelectedItem();
        MainFieldModChildController.TableFieldDataModel tableFieldDataModel = new MainFieldModChildController.TableFieldDataModel(tableame, colName.getHeaderDesc(), restTpeCode.getRestrictionCode());
        
        boolean result = check4Duplicate( MainFieldModificationController.instance.childStore,tableFieldDataModel);
        if(result){
          MainFieldModificationController.instance.childStore.add(tableFieldDataModel);
        }
        
        for(MainFieldModChildController.TableFieldDataModel s : MainFieldModificationController.instance.childStore){
        list.add(s);
        }
        modifiedFieldTable.setItems(list);
    }
    public boolean check4Duplicate(List<MainFieldModChildController.TableFieldDataModel> ls,MainFieldModChildController.TableFieldDataModel obj){
        boolean result=true;
        for(MainFieldModChildController.TableFieldDataModel s : MainFieldModificationController.instance.childStore){
            String tableName  = s.getTableName();
            String tableName2 =  obj.getTableName();
            String colName = s.getTableField();
            String colName2 = obj.getTableField();
            if(tableName.equals(tableName2)&&colName.equals(colName2)){
              result =  IRSDialog.showConfirmDialog("Duplicate", "There is an existing entry in the collection,Do you want to Overwrite?");
                System.out.println("result is "+result);
              if(result){
                  ls.remove(s);
                  break;
              }
            }
        }
        return result;
    }

    @FXML
    public void addRestriction(ActionEvent event) {
        loadData();
    }

    public static class TableFieldDataModel {

        private final StringProperty tableName;
        private final StringProperty tableField;
        private final StringProperty fieldRestriction;

        public String getTableName() {
            return tableName.get();
        }

        public String getTableField() {
            return tableField.get();
        }

        /**
         * @return the createdBy
         */
        public String getFieldRestriction() {
            return fieldRestriction.get();
        }

        public TableFieldDataModel(String tableName, String tableField, String fieldRestriction) {
            this.tableName = new SimpleStringProperty(tableName);
            this.tableField = new SimpleStringProperty(tableField);
            this.fieldRestriction = new SimpleStringProperty(fieldRestriction);

        }

    }

}
