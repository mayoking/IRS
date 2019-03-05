/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.vo;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author tkola
 */
public  class ChangeTableDataModel {

    private final StringProperty changeCode;
    private final StringProperty changeDate;
//    private StringProperty changeBy;
//    private StringProperty createdDate;
    private final StringProperty createdBy;
//    private StringProperty lastModified;
    private StringProperty modifiedBy;
    private final StringProperty status;
    private final StringProperty typeChanges;

    public ChangeTableDataModel(String changeCode, String typeChanges,  String status,String changeDate, String createdBy, String modifiedBy, String lastModified) {
        this.changeCode = new SimpleStringProperty(changeCode);
        this.typeChanges = new SimpleStringProperty(typeChanges);
        this.changeDate = new SimpleStringProperty(changeDate);
        this.status = new SimpleStringProperty(status);
        this.createdBy = new SimpleStringProperty(createdBy);
        this.modifiedBy = new SimpleStringProperty(modifiedBy);//lastModified
//        this.modifiedBy = new SimpleStringProperty(lastModified);

    }
    
       public String changeCode() {
            return changeCode.get();
        }

        public String typeChanges() {
            return typeChanges.get();
        }

        public String changeDate() {
            return changeDate.get();
        }

        public String status() {
            return status.get();
        }

        public String createdBy() {
            return createdBy.get();
        }
        
          public String modifiedBy() {
            return modifiedBy.get();
        }


}
