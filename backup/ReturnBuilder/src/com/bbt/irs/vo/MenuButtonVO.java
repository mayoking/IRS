/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.vo;

import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

/**
 *
 * @author tkola
 */
public class MenuButtonVO extends Accordion {

//    @FXML
//    private Text text;
    @FXML
    private Text text;
    @FXML
    private Image image;
    @FXML
    public Button addReturnMatrix;
    @FXML
    public Button manageReturnMatrix;
    @FXML
    public Button processedresultclick;
    @FXML
    public Button reloadClick;
//    @FXML
//    private ImageView imageView;
//    @FXML
//    private Button button;
    @FXML
    public ImageView imageView;
    @FXML
    public Button button;
    @FXML
    public Button basicInfoClick;
    @FXML
    public Button uploadTempClick;
    @FXML
    public Button confirmInputClick;
    @FXML
    public Button viewstaticHClick;
    @FXML
    public Button loadSCTPackageClick;
    @FXML
    public Button generateXmlXsdClick;
    @FXML
    public Button associateRiTypeClick;
    @FXML
    public Button viewxmlXsdClick;
    @FXML
    public Button updateXmlxsdClick;
    @FXML
    public Button deactivateReturnClick;
    @FXML
    public Button loadSCTGeneratorClick;
    @FXML
    public Button updateReturnClick;
    @FXML
    public Button restriction;
    @FXML
    public Button writeAssertion;
    @FXML
    public Button modifyTemplateItemCodes;
    @FXML
    public Button loaddelete;
    @FXML
    public Button workcollReturnMapping;
    @FXML
    public TitledPane x2;
    @FXML
    public TitledPane x4;
    public List<Button> appMenuList = new ArrayList();

    public void addMenuButtons() {
        appMenuList.add(addReturnMatrix);
        appMenuList.add(associateRiTypeClick);
        appMenuList.add(basicInfoClick);
        appMenuList.add(confirmInputClick);
        appMenuList.add(deactivateReturnClick);
        appMenuList.add(generateXmlXsdClick);
        appMenuList.add(loadSCTGeneratorClick);
        appMenuList.add(loadSCTPackageClick);
        appMenuList.add(manageReturnMatrix);
        appMenuList.add(processedresultclick);
        appMenuList.add(reloadClick);
        appMenuList.add(restriction);
        appMenuList.add(updateReturnClick);
        appMenuList.add(updateXmlxsdClick);
        appMenuList.add(viewstaticHClick);
        appMenuList.add(viewxmlXsdClick);
        appMenuList.add(writeAssertion);
        appMenuList.add(modifyTemplateItemCodes);
        appMenuList.add(loaddelete);//
        appMenuList.add(workcollReturnMapping);

    }

}
