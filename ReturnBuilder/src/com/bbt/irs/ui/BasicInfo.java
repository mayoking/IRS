/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.ui;

import com.bbt.irs.deploy.IRS;
import com.bbt.irs.deploy.IRSDialog;
import com.bbt.irs.controller.MainController;
import static com.bbt.irs.deploy.ErrorNameDesc.FIELD_BLANK;
import com.bbt.irs.dao.DataSizeDAO;
import com.bbt.irs.dao.DataTypeDAO;
import com.bbt.irs.dao.FrequencyDAO;
import com.bbt.irs.dao.RITypeDAO;
import com.bbt.irs.dao.TemplateTypeDAO;
import com.bbt.irs.deploy.ErrorNameDesc;
import com.bbt.irs.deploy.Messages;
import com.bbt.irs.entity.Datasize;
import com.bbt.irs.entity.Datatype;
import com.bbt.irs.entity.TCoreRiType;
import com.bbt.irs.entity.TLkupFrequency;
import com.bbt.irs.entity.TRtnWorkCollection;

import com.bbt.irs.entity.TemplateType;
import com.bbt.irs.vo.BasicInfoVO;
import java.io.IOException;
import java.util.List;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.apache.logging.log4j.LogManager;

/**
 *
 * @author opeyemi
 */
public class BasicInfo implements Messages, ErrorNameDesc {
    
    private AnchorPane pane = new AnchorPane();
    public AnchorPane pane1 = new AnchorPane();
    ScrollPane scroll = new ScrollPane();
    
    BorderPane bpane = new BorderPane();
    GridPane gpane = new GridPane();
    
    VBox vboxMain = new VBox();
    VBox vbox = new VBox();
    HBox hbox = new HBox();
    HBox hbox1 = new HBox();
    HBox hbox2 = new HBox();
    HBox hbox3 = new HBox();
    HBox hbox4 = new HBox();
    
    Button btn = new Button("Next");
    Button btn1 = new Button("Back");
    Label tempalteCodeLabel = new Label("Template Code");
    Label templateDescLabel = new Label(DESC_LABEL);
    Label numTablesLabel = new Label(NO_NUM_TABLES_LABEL);
    Label workCollectionsLabel = new Label("Frequency");
    Label templateTypeLabel = new Label("Template Type");
    Label RITypeLabel = new Label("RI Type");
    Label affiliateLabel = new Label("Affiliates");
    
    private TextField affiliate = new TextField();
    
    private TextField tempalteCode = new TextField();
    
    private TextField templateDesc = new TextField();
    TextField numTables = new TextField();
    List<TCoreRiType> riTypes;
    List<Datasize> dataSize;
    List<Datatype> dataType;
    List<TemplateType> templateTypes;
    List<TLkupFrequency> workcollections;
    private ObservableList<TLkupFrequency> collections;
    
    private ObservableList<TemplateType> typesofTemplate;
    
    private ObservableList<TCoreRiType> typesofRI;
    
    ComboBox workCollections;
    ComboBox templateType;
    ComboBox Ritype;
    Separator separator = new Separator();
    private boolean isaffiliate = false;
    
    public BasicInfo() {
        try {
            loadComboBox();
            setComboBox();
        } catch (Exception ex) {
            IRSDialog.showAlert(ERROR, ERROR_DROPDOWN);
            LOGGER.log(org.apache.logging.log4j.Level.FATAL, ERROR_DROPDOWN, ex);
        }
        populateUI();
        setLayOut();
        templateType.valueProperty().addListener(new ChangeListener<TemplateType>() {
            @Override
            public void changed(ObservableValue ov, TemplateType t, TemplateType t1) {
                if (t1.getTemplateId() == 4) {
                    hbox4.getChildren().addAll(affiliateLabel, affiliate);
                    vbox.getChildren().add(hbox4);
                    isaffiliate = true;
                    
                } else {
                    System.out.println("testing ");
                    String test = affiliate.getText();
                    
                    System.out.println("test " + test);
                    if (isaffiliate) {
                        System.out.println("About to remove hbox4");
                        hbox4.getChildren().removeAll(affiliate, affiliateLabel);
                        vbox.getChildren().remove(hbox4);
                    }
                    isaffiliate = false;
                }
            }
        });
        controlsValidator();
        
        btn.setOnAction((ActionEvent event) -> {
            processNextButton();
        });
        addStyleClass();
    }

    /**
     * Used to set the Styleclass of containers and controls.
     */
    public final void addStyleClass() {
        hbox.getStyleClass().add("hbox");
        hbox1.getStyleClass().add("hbox");
        hbox2.getStyleClass().add("hbox");
        hbox3.getStyleClass().add("hbox");
        hbox4.getStyleClass().add("hbox");
        getTempalteCode().getStyleClass().add("text-field");
        getTemplateDesc().getStyleClass().add("text-field");
        affiliate.getStyleClass().add("text-field");
        numTables.getStyleClass().add("text-field");
        workCollections.getStyleClass().add("combobox");
        templateType.getStyleClass().add("combobox");
        Ritype.getStyleClass().add("combobox");
        tempalteCodeLabel.getStyleClass().add("label9");
        templateDescLabel.getStyleClass().add("label9");
        affiliateLabel.getStyleClass().add("label9");
        RITypeLabel.getStyleClass().add("label9");
        numTablesLabel.getStyleClass().add("label9");
        workCollectionsLabel.getStyleClass().add("label9");
        templateTypeLabel.getStyleClass().add("label9");
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
        if (isaffiliate) {
            if (this.affiliate.getText() == null || this.affiliate.getText().isEmpty()) {
                IRSDialog.showAlert(affiliateLabel.getText(), FIELD_BLANK);
                result = false;
            }
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
        if (isaffiliate) {
            info.setAfiliate(affiliate.getText());
        }
        return info;
    }

    /**
     * This method is called when next button is clicked
     */
    public void processNextButton() {
        try {
            if (validateBasicInfo()) {
                BasicInfoVO info = setBasicInfoObject();
                IRS.getLinkedHashMap().put(1, info);
                MainController.getInstance().loadXLS();
                //MainController.getInstance().loadStaticHeaderForm();
            }
        } catch (IOException ex) {
            IRSDialog.showAlert("Error",
                    "Error while processing the request");
            LOGGER.log(org.apache.logging.log4j.Level.FATAL, "Error while processing the request", ex);
        }
    }

    /**
     * It is used to:
     * <li>Set the container with all their children</li>
     *
     */
    public final void populateUI() {
        scroll.setFitToWidth(true);
        scroll.setFitToHeight(true);
        hbox.getChildren().add(btn);
        hbox3.getChildren().addAll(tempalteCodeLabel, tempalteCode, templateDescLabel, templateDesc);
        hbox2.getChildren().addAll(workCollectionsLabel, workCollections, templateTypeLabel, templateType);
        hbox1.getChildren().addAll(RITypeLabel, Ritype, numTablesLabel, numTables);
        vbox.getChildren().addAll(hbox1, hbox2, hbox3);
        vboxMain.getChildren().addAll(vbox, separator, hbox);
        gpane.getChildren().add(vboxMain);
        bpane.setTop(gpane);
        gpane.setAlignment(Pos.CENTER);
        pane1.getChildren().add(bpane);
    }
    
    public final void setLayOut() {
        AnchorPane.setTopAnchor(bpane, 0.0);
        AnchorPane.setRightAnchor(bpane, 0.0);
        AnchorPane.setBottomAnchor(bpane, 0.0);
        AnchorPane.setLeftAnchor(bpane, 0.0);
        AnchorPane.setTopAnchor(pane1, 0.0);
        AnchorPane.setRightAnchor(pane1, 0.0);
        AnchorPane.setBottomAnchor(pane1, 0.0);
        AnchorPane.setLeftAnchor(pane1, 0.0);
        AnchorPane.setTopAnchor(scroll, 0.0);
        AnchorPane.setRightAnchor(scroll, 0.0);
        AnchorPane.setBottomAnchor(scroll, 0.0);
        AnchorPane.setLeftAnchor(scroll, 0.0);
        pane.setPrefWidth(1150.0);
        scroll.setContent(pane1);
        pane.getChildren().add(scroll);
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
        if (isaffiliate) {
            templateDesc.setTextFormatter(
                    new TextFormatter<>((change) -> {
                        change.setText(change.getText().toUpperCase().replace("*", ""));
                        return change;
                    }
                    ));
        }
    }

    /**
     * @return the tempalteCode
     */
    public TextField getTempalteCode() {
        return tempalteCode;
    }

    /**
     * @param tempalteCode the tempalteCode to set
     */
    public void setTempalteCode(TextField tempalteCode) {
        this.tempalteCode = tempalteCode;
    }

    /**
     * @return the templateDesc
     */
    public TextField getTemplateDesc() {
        return templateDesc;
    }

    /**
     * @param templateDesc the templateDesc to set
     */
    public void setTemplateDesc(TextField templateDesc) {
        this.templateDesc = templateDesc;
    }
    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(BasicInfo.class);

    /**
     * @return the pane
     */
    public AnchorPane getPane() {
        return pane;
    }

    /**
     * @param pane the pane to set
     */
    public void setPane(AnchorPane pane) {
        this.pane = pane;
    }
    
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
        
        typesofTemplate
                = FXCollections.observableArrayList(
                        templateTypes);
        
        typesofRI
                = FXCollections.observableList(riTypes);
        workCollections = new ComboBox(collections);
        templateType = new ComboBox(typesofTemplate);
        Ritype = new ComboBox(typesofRI);
    }
    
}
