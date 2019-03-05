/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.controller;

import com.bbt.irs.dao.DataSizeDAO;
import com.bbt.irs.dao.DataTypeDAO;
import com.bbt.irs.dao.FrequencyDAO;
import com.bbt.irs.dao.RITypeDAO;
import com.bbt.irs.dao.TemplateTypeDAO;
import static com.bbt.irs.deploy.ErrorNameDesc.ERROR;
import static com.bbt.irs.deploy.ErrorNameDesc.ERROR_DROPDOWN;
import static com.bbt.irs.deploy.ErrorNameDesc.FIELD_BLANK;
import com.bbt.irs.deploy.IRS;
import com.bbt.irs.deploy.IRSDialog;
import com.bbt.irs.entity.TRbDatasize;
import com.bbt.irs.entity.TRbDatatype;
import com.bbt.irs.entity.TCoreRiType;
import com.bbt.irs.entity.TLkupFrequency;
import com.bbt.irs.entity.TemplateType;
import com.bbt.irs.vo.BasicInfoVO;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

/**
 *
 * @author kaytush
 */
public class BasicInfomationController implements Initializable {

    @FXML
    public TextField tempalteCode;

    @FXML
    private Label tempalteCodeLabel;

    @FXML
    private Label templateDescLabel;

    @FXML
    private Label templateTypeLabel;

    @FXML
    private Label RITypeLabel;

    @FXML
    private Label numTablesLabel;

    @FXML
    private Label workCollectionsLabel;

    @FXML
    public Button processNextButton;

    @FXML
    private TextField templateDesc;

    @FXML
    public TextField numTables;

    @FXML
    public ComboBox<TLkupFrequency> workCollections;

    @FXML
    public ComboBox<TemplateType> templateType;

    @FXML
    public ComboBox<TCoreRiType> Ritype;

    List<TCoreRiType> riTypes;
    List<TRbDatasize> dataSize;
    List<TRbDatatype> dataType;
    List<TemplateType> templateTypes;
    List<TLkupFrequency> workcollections;

    private ObservableList<TLkupFrequency> collections;

    private ObservableList<TemplateType> typesofTemplate;

    private ObservableList<TCoreRiType> typesofRI;

    private void loadComboBox() throws Exception {
        riTypes = new RITypeDAO().getRIype();
        dataSize = new DataSizeDAO().getDataSize();
        dataType = new DataTypeDAO().getDataType();
        templateTypes = new TemplateTypeDAO().getTemplateType();
        //workcollections = new WorkCollectionDAO().getWorkCollection();
        workcollections = new FrequencyDAO().getFrequency();

    }

    private void setComboBox() {
        collections
                = FXCollections.observableArrayList(
                        workcollections);
        workCollections.setItems(collections);

        typesofTemplate
                = FXCollections.observableArrayList(
                        templateTypes);
        templateType.setItems(typesofTemplate);

        typesofRI
                = FXCollections.observableList(riTypes);
        Ritype.setItems(typesofRI);

    }

    /**
     * The method validates all the basic info inputs
     *
     * @return
     */
    public boolean validateBasicInfo() {
        boolean result = true;
        if (getTempalteCode().getText() == null || getTempalteCode().getText().isEmpty()) {

            IRSDialog.showAlert(tempalteCodeLabel.getText(), FIELD_BLANK);
            result = false;
        }
        if (this.getTemplateDesc().getText() == null || this.getTemplateDesc().getText().isEmpty()) {
            IRSDialog.showAlert(templateDescLabel.getText(), FIELD_BLANK);
            result = false;
        }

        if (this.templateType.getSelectionModel().getSelectedItem() == null) {
            IRSDialog.showAlert(templateTypeLabel.getText(), FIELD_BLANK);
            result = false;
        }
        if (this.Ritype.getSelectionModel().getSelectedItem() == null) {
            IRSDialog.showAlert(RITypeLabel.getText(), FIELD_BLANK);
            result = false;
        }
        if (this.numTables.getText() == null || this.numTables.getText().isEmpty()) {
            IRSDialog.showAlert(numTablesLabel.getText(), FIELD_BLANK);
            result = false;
        }

        if (this.workCollections.getSelectionModel().getSelectedItem() == null) {
            IRSDialog.showAlert(workCollectionsLabel.getText(), FIELD_BLANK);
            result = false;
        }

        return result;
    }

    public void setControlID() {
        getTempalteCode().setId("tempalteCode");
        this.getTemplateDesc().setId("templateDesc");

    }

    /**
     * This method extract the value from the controls at the front end\n to set
     * the members of BasicInfoVO
     *
     * @return
     */
    private BasicInfoVO setBasicInfoObject() {
        BasicInfoVO info = new BasicInfoVO();
        info.setNumTables(numTables.getText());
        info.setRitype((TCoreRiType) Ritype.getSelectionModel().getSelectedItem());
        info.setTemplateDesc(getTemplateDesc().getText());
        info.setTemplateType((TemplateType) templateType.getSelectionModel().getSelectedItem());
        System.out.println("info.getTemplateType().getId() " + info.getTemplateType().getTemplateId());
        info.setTemplateCode(getTempalteCode().getText());
        info.setFrequncyCollections((TLkupFrequency) workCollections.getSelectionModel().getSelectedItem());
//        if (isaffiliate) {
//            info.setAfiliate(affiliate.getText());
//        }
        return info;
    }

//    @FXML
//    public void processNextButton() {
//
//        this.processNextButton.setOnAction((ActionEvent event) -> {
//            System.out.println("About to validate xml and xls");
//
//            try {
//                if (validateBasicInfo()) {
//                    BasicInfoVO info = setBasicInfoObject();
//                    IRS.getLinkedHashMap().put(1, info);
//                    MainController.getInstance().loadXLS();
//                    //MainController.getInstance().loadStaticHeaderForm();
//                }
//            } catch (IOException ex) {
//                IRSDialog.showAlert(ERROR, ERROR_DROPDOWN);
//                Logger.getLogger(BasicInfomationController.class.getName()).log(Level.SEVERE, null, ex);
//
//            }
//        });
//
//    }
    @FXML
    protected void processNextButton(ActionEvent event) {
        try {
            if (validateBasicInfo()) {
                BasicInfoVO info = setBasicInfoObject();
                IRS.getLinkedHashMap().put(1, info);
                MainController.getInstance().loadXLS();
                //MainController.getInstance().loadStaticHeaderForm();
            }
        } catch (IOException ex) {
            IRSDialog.showAlert(ERROR, ERROR_DROPDOWN);
            Logger.getLogger(BasicInfomationController.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    /**
     * This method validates all the text input for basic info
     */
    public final void controlsValidator() {
        numTables.setTextFormatter(
                new TextFormatter<>((change) -> {
                    change.setText(change.getText().replaceAll("[^\\d.]", ""));
                    return change;
                }
                ));
        tempalteCode.setTextFormatter(
                new TextFormatter<>((change) -> {
                    change.setText(change.getText().toUpperCase().replace(" ", "").replace("*", ""));
                    return change;
                }
                ));
        templateDesc.setTextFormatter(
                new TextFormatter<>((change) -> {
                    change.setText(change.getText().toUpperCase().replace("*", ""));
                    return change;
                }
                ));
//        if (isaffiliate) {
//            templateDesc.setTextFormatter(
//                    new TextFormatter<>((change) -> {
//                        change.setText(change.getText().toUpperCase().replace("*", ""));
//                        return change;
//                    }
//                    ));
//        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            //        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            loadComboBox();
            setComboBox();
        } catch (Exception ex) {
            IRSDialog.showAlert(ERROR, ERROR_DROPDOWN);
            Logger.getLogger(BasicInfomationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        controlsValidator();
    }

    public TextField getTempalteCode() {
        return tempalteCode;
    }

    public TextField getTemplateDesc() {
        return templateDesc;
    }

}
