/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.ui;

import com.bbt.irs.controller.MainController;
import com.bbt.irs.dao.CreateTableDAO;
import com.bbt.irs.dao.MainDAO;
import com.bbt.irs.deploy.ErrorNameDesc;
import com.bbt.irs.deploy.IRS;
import com.bbt.irs.deploy.IRSDialog;
import com.bbt.irs.deploy.Messages;
import com.bbt.irs.deploy.TableGenerator;
import com.bbt.irs.entity.TRbDatasize;
import com.bbt.irs.entity.TRbDatatype;
import com.bbt.irs.exception.DatabaseException;
import com.bbt.irs.exception.WebservicesException;
import com.bbt.irs.rest.client.RBRestClient;
import static com.bbt.irs.ui.ExcelView.getCellValueAsString;
import com.bbt.irs.util.Utility;
import com.bbt.irs.vo.BasicInfoVO;
import com.bbt.irs.vo.ColObject;
import com.bbt.irs.vo.GenWorCollVO;
import com.bbt.irs.vo.HeaderInfoVO;
import com.bbt.irs.vo.ResponseVO;
import com.bbt.irs.vo.TableVO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javax.persistence.PersistenceException;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.core.Response;
import static jdk.nashorn.internal.objects.Global.Infinity;
import org.apache.logging.log4j.LogManager;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFSheet;

/**
 *
 * @author tkola
 */
public final class TablesUI implements Messages, ErrorNameDesc {

    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(TablesUI.class);
    private static TablesUI instance;
    private final LinkedHashMap headerInfoHashmap = new LinkedHashMap();
    /**
     * ***********Javafx ui variables ******************
     */
    public AnchorPane root = new AnchorPane();
    AnchorPane rootnode = new AnchorPane();
    BorderPane borderPane = new BorderPane();
    VBox overallVbox = new VBox();
    HBox btnsHbox = new HBox();
    HBox comboHbox = new HBox();
    Button submitButton = new Button("Submit");
    Button backButton = new Button("Cancel");
    Separator separator = new Separator();
    ComboBox<TableVO> tableSelection = new ComboBox();
    Label tableSelectionLabel = new Label("Select table");
    VBox colVbox = new VBox();
    /**
     * *loadedTableNames is used to keep track of table selections ****
     */
    List<String> loadedTableNames = new ArrayList();
    List<TableVO> collections;

    private TablesUI() {
        //IRS.tablesUI=TablesUI.instance;//this is needed so as to store notify IRS that the page has been opened
        BasicInfoVO info = (BasicInfoVO) IRS.getLinkedHashMap().get(1);
        UploadTemplateUI uplUI = UploadTemplateUI.getInstance();
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        GridPane subRoot = new GridPane();
        collections = generateTableObject(info.getTemplateCode(), Integer.valueOf(info.getNumTables()), uplUI.result);
        disPlayHeaderandCol(collections, "TableUI Constructor");
        tableSelection.setItems(FXCollections.observableArrayList(collections));
        btnsHbox.getStyleClass().add("hbox");
        comboHbox.getStyleClass().add("hbox");
        tableSelection.getStyleClass().add("combobox");
        btnsHbox.getChildren().addAll(backButton, submitButton);
        comboHbox.getChildren().addAll(tableSelectionLabel, tableSelection);
        overallVbox.getChildren().addAll(comboHbox, colVbox, btnsHbox);//, separator, btnsHbox
        subRoot.getChildren().add(overallVbox);
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
//        AnchorPane.setTopAnchor(scrollPane, 0.0);
//        AnchorPane.setRightAnchor(scrollPane, 0.0);
//        AnchorPane.setBottomAnchor(scrollPane, 0.0);
//        AnchorPane.setLeftAnchor(scrollPane, 0.0);
//        rootnode.setPrefWidth(1150.0);
//        scrollPane.setContent(rootnode);

        AnchorPane.setTopAnchor(scrollPane, 19.0);
        AnchorPane.setRightAnchor(scrollPane, 30.0);
        AnchorPane.setLeftAnchor(scrollPane, 25.0);
        rootnode.setPrefWidth(774.0);
        rootnode.setPrefWidth(500.0);
        rootnode.setMinSize(-Infinity, -Infinity);
        rootnode.setMaxSize(-Infinity, -Infinity);
        scrollPane.setContent(rootnode);
        scrollPane.setLayoutX(30);
        scrollPane.setLayoutY(19);
        root.setPrefSize(774.0, 500.0);
        scrollPane.setContent(rootnode);
        root.getChildren().add(scrollPane);

        /**
         * ********************Action Code *********************
         */
        addListener2Combo();//This ensures tha all tables have been checked using loadedTableNames
        submitButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                processNextButton(event);
            }
        });
        backButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                processBackButton(event);
            }
        });

    }

    public void processBackButton(ActionEvent event) {
        AnchorPane previousContent = IRS.spreadSheetUI.root;
        loadedTableNames = new ArrayList();
        if (previousContent != null) {
            AnchorPane previous = ((AnchorPane) IRS.scene.lookup("#centerPane"));
            previous.getChildren().clear();
            previous.getChildren().add(previousContent);
           IRS.getInstance().activateButton(IRS.getInstance().appMenuList,(Button)event.getSource()); 
        } else {
            IRSDialog.showAlert("Info", "There is no existing history for upload page");
        }
    }

    public String validateTableInfoDB(String tableName, String returnCode) {
        MainDAO maindao = new MainDAO();
        String message;
        boolean result = maindao.checkTableExistence(tableName);
        System.out.println("result " + result);
        System.out.println("tablename " + tableName);
        if (result) {
            result = maindao.checkMetadataExisitence(returnCode);
            if (result) {
                result = maindao.checkReturnExisitence(returnCode);
                if (result) {
                    result = maindao.checkDBChangesExisitence(returnCode);
                    if (result) {
                        message = "s";
                    } else {
                        message = "There is an existing return for sysnchronization";
                    }
                } else {
                    message = "There is an exsiting return with the name of the return";
                }
            } else {
                message = "There is an existing metadata information for the return";
            }
        } else {
            message = "There is already tablename with the name of the return";
        }
        return message;
    }

    public String checkInfo(LinkedHashMap data) {
        LinkedHashMap<Integer, LinkedList<HeaderInfoVO>> header = (LinkedHashMap<Integer, LinkedList<HeaderInfoVO>>) data.get(2);

        boolean result;
        int noofTables = header.size();
        BasicInfoVO info = (BasicInfoVO) data.get(1);
        String returnCode = info.getTemplateCode();
        String msg = "";
        for (int i = 0; i < header.size(); i++) {
            System.out.println("headers " + header.get(i).get(i).getCellName());
            if (noofTables > 1) {
                String tableName = "t_rtn_submission_" + info.getTemplateCode() + "_" + i;
                msg = validateTableInfoDB(tableName, returnCode);
            } else {
                String tableName = "t_rtn_submission_" + info.getTemplateCode();
                msg = validateTableInfoDB(tableName, returnCode);
            }
        }

        return msg;
    }

    public void processNextButton(ActionEvent event) {
        boolean goahead = IRSDialog.showConfirmDialog("Submission", "This action will only affect a new version of Smart Client . Do you want to proceed?");
        if (!goahead) {
            return;
        }
        BasicInfo basicInfo = IRS.basicInfo;
        UploadTemplateUI uploadTemplateUI = IRS.uploadTemplateUI;
        SpreadSheetConfirmationPage spreadSheetUI = IRS.spreadSheetUI;
        if ((basicInfo == null || uploadTemplateUI == null) || spreadSheetUI == null) {
            IRSDialog.showAlert("Error", "Please, fill other blank forms");
            return;
        }
        System.out.println("tableSelection.getSelectionModel().getSelectedItem() " + tableSelection.getSelectionModel().getSelectedItem());
        if (tableSelection.getSelectionModel().getSelectedItem() == null) {
            IRSDialog.showAlert("Info", "Select a table to be able to proceed ");
            return;
        }

        boolean result;
        String resultz;

        resultz = generateLinkedHashMap(collections);

        if (!resultz.equals("s")) {
            IRSDialog.showAlert(INFO, resultz);
            return;
        }
        resultz = checkInfo(IRS.linkedHashMap);
        if (!resultz.equals("s")) {
            IRSDialog.showAlert(INFO, resultz);
            return;
        }
        IRS.tablesUI = TablesUI.instance;
        RBRestClient rbRestClient = new RBRestClient();

        try {
            Response response = rbRestClient.uploadExcelFile(IRS.uploadTemplateUI.getExcelPath());
            if (response.getStatus() == 200) {
                result = true;
            } else {
                IRSDialog.showAlert("Error", "Fatal Error!!, Upload Failed ");
                return;
            }
        } catch (ProcessingException ex) {
            IRSDialog.showAlert("Error", "Fatal Error!!, Unable to Upload due to connectivity issue ");
            LOGGER.log(org.apache.logging.log4j.Level.FATAL, "Unable to Upload due to connectivity issue ", ex);
            return;
        } catch (WebservicesException webservicesException) {
            IRSDialog.showAlert("Error", webservicesException.getMessage());
            LOGGER.log(org.apache.logging.log4j.Level.FATAL, webservicesException.getMessage());
            return;
        } catch (IOException ex) {
            IRSDialog.showAlert("Error", "Fatal Error!!, Unable to Upload the excel to server ");
            LOGGER.log(org.apache.logging.log4j.Level.FATAL, "Unable to Upload the excel to server ", ex);
            return;
        }

        try {
            result = TableGenerator.generateTable(IRS.linkedHashMap);
        } catch (DatabaseException ex) {
            IRSDialog.showAlert("Error", ex.getMessage());
            LOGGER.log(org.apache.logging.log4j.Level.FATAL, ex.getMessage());
            //result = false;
            return;
        }
        if (!result) {
            IRSDialog.showAlert("Error", "Table Can not be created successfully");
        }
        if (result) {

            result = populateStaticTables();
            if (result) {
                result = IRSDialog.showConfirmDialog("Creation of Returns", "The return has been created successfully.Do you want to generate XSD and XML?");
                if (result) {
                    if (basicInfo.workCollections == null) {
                        IRSDialog.showAlert(INFO, "WorkCollection not selected");
                    }

                    String workColCode = (String) basicInfo.workCollections.getSelectionModel().getSelectedItem().getWorkCollectionCode();
                    String returnCode = basicInfo.getTempalteCode().getText();
                    result = generateXMLXSD(returnCode, workColCode);
                }

            } else {
                try {
                    CreateTableDAO createTableDAO = new CreateTableDAO();
                    createTableDAO.deleteTables(IRS.getCreatedTableNames(), IRS.basicInfo.getTempalteCode().getText());
                    return;
                } catch (SQLException | PersistenceException | IOException ex) {
                    IRSDialog.showAlert("Error", "Failed To rollback the created tables");
                    LOGGER.log(org.apache.logging.log4j.Level.FATAL, "Failed To rollback the created tables", ex);
                    return;
                }
            }
            System.out.println(" result for table populator " + result);
        }
        System.out.println(" result for over all processes " + result);
        if (result) {
            try {
                MainController.getInstance().loadBasicInfo();
            } catch (IOException ex) {
                IRSDialog.showAlert("Error", "Error while loading Basic Info after successful template creation");
                LOGGER.log(org.apache.logging.log4j.Level.FATAL, "Error while loading Basic Info after successful template creation", ex);
                return;
            }
            IRS.resetApplication();
            IRS.pageHistory.clear();
            IRS.getInstance().activateButton(IRS.getInstance().appMenuList,(Button)event.getSource()); 
            try {
                MainController.getInstance().loadBasicInfo();
            } catch (IOException ex) {
                LOGGER.log(org.apache.logging.log4j.Level.FATAL, ex);
            }
            //IRSDialog.showAlert("Creation of Returns", "The return has been created successfully");
        }
    }

    public void addListener2Combo() {
        tableSelection.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<TableVO>() {
            @Override
            public void changed(ObservableValue observable, TableVO oldValue, TableVO newValue) {
                populateTableUI(newValue);
                if (!loadedTableNames.contains(newValue.getTableDesc())) {
                    loadedTableNames.add(newValue.getTableDesc());
                }
                System.out.println("Listener completed ");
            }
        }
        );
    }

    public boolean generateXMLXSD(String returns, String workCollCode) {
        boolean result = false;
        try {
            GenWorCollVO genWorCollVO = new GenWorCollVO();
            genWorCollVO.setReturns(returns);
            genWorCollVO.setWorkCollectionCode(workCollCode);
            RBRestClient rbRestClient = new RBRestClient();
            ResponseVO res = rbRestClient.generateXMLXSD(genWorCollVO);
            if (res.getResponseCode() == 0) {
                result = true;
                IRSDialog.showAlert("Info", "XSD and XML successfully generated");

            } else {
                result = false;
                IRSDialog.showAlert(ERROR, res.getResponseDesc());
                LOGGER.log(org.apache.logging.log4j.Level.FATAL, res.getResponseDesc());
            }
        } catch (WebservicesException webservicesException) {
            IRSDialog.showAlert("Error", webservicesException.getMessage());
            LOGGER.log(org.apache.logging.log4j.Level.FATAL, webservicesException.getMessage());
        }

        return result;
    }

    public List<TableVO> convertRangeUIListToTableObjects(List<TableRangeUI> rangeUI, List<TableVO> tableVOs) {
        List<TableVO> ls = new ArrayList();
        XSSFSheet poiSheet = IRS.getPoisheet();
        for (int i = 0; i < rangeUI.size(); i++) {
            TableVO tbvo = tableVOs.get(i);
            List<ColObject> colList = new ArrayList();
            String startCell = rangeUI.get(i).getStartRange().getText();
            String endCell = rangeUI.get(i).getEndRange().getText();
            String startCoordinate = Utility.convertExcelCellToCoordinate(startCell);
            String endCoordinate = Utility.convertExcelCellToCoordinate(endCell);
            String[] startarray = startCoordinate.split(",");
            String[] endarray = endCoordinate.split(",");
            for (int j = Integer.valueOf(startarray[0]); j <= Integer.valueOf(endarray[0]); j++) {
                String columnLetter = CellReference.convertNumToColString(j);
                System.out.println("This is the column letter " + columnLetter);
                ColObject colObject = new ColObject();
                String cellref;
                String value;
                Row poiRow = poiSheet.getRow(Integer.valueOf(startarray[1]) - 1);
                if (poiRow != null) {
                    Cell cell = poiRow.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    value = getCellValueAsString(cell);//ExcelUtils.cellStringValue(evaluator,cell);
                    System.out.println("value is " + value);
                    colObject.setColHeader(value);
                }
                cellref = columnLetter + startarray[1];
                colObject.setColRef(cellref);
                colList.add(colObject);
            }
            System.out.println("total collist " + colList);
            tbvo.setExcelColList(colList);
            ls.add(tbvo);
        }
        return ls;
    }

    public List<TableVO> generateListCompositeVbox(List<TableVO> tablevos) {
        List<TableVO> tableVOs = new ArrayList();
        for (int k = 0; k < tablevos.size(); k++) {
            TableVO tablevo = tablevos.get(k);
            //VBox TableVBox = new VBox();
            List<CompositeVBox> ls = new ArrayList();
            for (int i = 0; i < tablevo.getExcelColList().size(); i++) {
                CompositeVBox compositeVBox = new CompositeVBox("CellNo", "Cell Name", "Data Type", "Data Size", i);
                ls.add(compositeVBox);
            }
            System.out.println("ls size ===========  " + ls.size());
            tablevo.setColInfo(ls);
            tableVOs.add(tablevo);
            System.out.println("tableVOs " + tableVOs.size());
        }

//        TableVBox.getChildren().add(vbox);
//        TableVBox.getChildren().add(new Separator());
        return tableVOs;
    }

    public void populateTableUI(TableVO tablevo) {
        if (!loadedTableNames.contains(tablevo.getTableDesc())) {
            setData4CompositeVBox(tablevo);
        }

        List<CompositeVBox> ls = tablevo.getColInfo();
        colVbox.getChildren().clear();
        System.out.println("overallVbox " + ls.size());
        for (int i = 0; i < tablevo.getColInfo().size(); i++) {
            System.out.println("ls.get(i).getvContainer() " + ls.get(0).getvContainer());

            colVbox.getChildren().addAll(ls.get(i).getvContainer(), new Separator());

            //colVbox.getChildren().add(new Separator());
            System.out.println("done ==== populateTableUI ");
        }
    }

    public void setData4CompositeVBox(TableVO tablevo) {
        List<CompositeVBox> ls = tablevo.getColInfo();
        colVbox.getChildren().clear();
        System.out.println("overallVbox " + ls.size());
        for (int i = 0; i < tablevo.getColInfo().size(); i++) {
            String valueHeader = tablevo.getExcelColList().get(i).getColHeader();
            String colRef = tablevo.getExcelColList().get(i).getColRef();
            CompositeVBox vboxElement = tablevo.getColInfo().get(i);
            vboxElement.getCellNo().setText(colRef);
            vboxElement.getCellName().setText((i == 0 ? "ITEM_CODE" : valueHeader));

            vboxElement.getDataTypeCombo().getSelectionModel().select(vboxElement.data.get(0));
            vboxElement.getDataSizeCombo().getSelectionModel().select(vboxElement.dataSize.get(2));
            //colVbox.getChildren().add(new Separator());
            System.out.println("done ==== populateTableUI ");
        }
    }

    public List<TableVO> generateTableObject(String templateCode, int numTables, List<TableRangeUI> tableRangeUi) {
        List<TableVO> names = new ArrayList();
        for (int i = 0; i < numTables; i++) {
            TableVO tablevo = new TableVO();
            tablevo.setTableID(i);
            tablevo.setTableDesc(templateCode + (i == 0 ? "" : "_" + i + ""));
            names.add(tablevo);
        }
        System.out.println("first name " + names.size());
        names = convertRangeUIListToTableObjects(tableRangeUi, names);
        names = generateListCompositeVbox(names);

        return names;
    }

    public String generateLinkedHashMap(List<TableVO> tableVOs) {
        System.out.println("inside generateLinkedHashMap ");
        String result;
        result = validateResult(tableVOs);
        if (!result.equals("s")) {
            return result;
        }

        System.out.println("about to generateLinkedHashMap linkedhashmap ");
        for (int i = 0; i < tableVOs.size(); i++) {
            TableVO tablevo = tableVOs.get(i);
            List<CompositeVBox> colInfo = tablevo.getColInfo();
            System.out.println("tablevo in linked hashmap " + tablevo.getTableDesc());
            System.out.println("tablevo in linked hashmap id " + tablevo.getTableID());
            LinkedList<HeaderInfoVO> headerInfovos = new LinkedList();
            for (int j = 0; j < colInfo.size(); j++) {
                CompositeVBox compositeVBox = colInfo.get(j);
                if (j == 1) {
                    System.out.println("j " + colInfo.get(j));
                    System.out.println("j " + j);
                    System.out.println("j " + colInfo.get(j).getCellNo());
                }
                HeaderInfoVO header = getHeaderVOObject(compositeVBox);
                headerInfovos.add(header);
            }
            System.out.println(i + " table info Generated successfully ");
            headerInfoHashmap.put(i, headerInfovos);
            IRS.getAllHeaderInfo().put(i, headerInfovos);
            IRS.getLinkedHashMap().put(2, IRS.getAllHeaderInfo());
            System.out.println("IRS.getLinkedHashMap() " + IRS.getLinkedHashMap().size());
        }

        result = "s";
        return result;
    }

    public HeaderInfoVO getHeaderVOObject(CompositeVBox compositeVBox) {
        HeaderInfoVO result = new HeaderInfoVO();
        result.setCellNO(compositeVBox.getCellNo().getText());
        result.setCellName(compositeVBox.getCellName().getText());
        result.setDataSize((TRbDatasize) compositeVBox.getDataSizeCombo().getSelectionModel().getSelectedItem());
        result.setDataType((TRbDatatype) compositeVBox.getDataTypeCombo().getSelectionModel().getSelectedItem());
        return result;
    }

    public String validateResult(List<TableVO> tableVOs) {
        String result = "";
        System.out.println("Inside validate result ");
        for (int i = 0; i < tableVOs.size(); i++) {
            TableVO tablevo = tableVOs.get(i);
            System.out.println("This is the tableid " + tablevo.getTableDesc());
            result = validateHeaders(tablevo);
//            List<CompositeVBox> colInfo = tablevo.getColInfo();
//            for (int j = 0; j < colInfo.size(); j++) {
//                CompositeVBox compositeVBox = colInfo.get(j);
//                if (!validateCompositeBox(compositeVBox)) {
//
//                    return false;
//                }
//            }
            System.out.println("***************8 Completed validation of table " + tablevo.getTableDesc());
        }
        System.out.println("validation completed susccessfully!!!!!" + result);
        return result;
    }

    public boolean checkForXMLCompliantTable(String tableColumn) {

        return tableColumn.matches("^[aA-zZ]+([aA-zZ][0-9]_)?");
    }

    public String validateHeaders(TableVO tablevo) {
        String result = "s";
        List<CompositeVBox> colInfo = tablevo.getColInfo();
        for (int i = 0; i < colInfo.size(); i++) {
            CompositeVBox vbox = colInfo.get(i);
            System.out.println("validateHeaders ====== " + vbox.getCellName().getText());
            if (!validateCompositeBox(vbox)) {
                result = "Cell " + vbox.getCellNo().getText() + "is blank";
                return result;
            }
            String columnHeader = vbox.getCellName().getText();
            if (!columnHeader.matches("^[aA-zZ]+[aA-zZ0-9_]+?")) {
                result = "Cell " + vbox.getCellNo().getText() + "contains illegal character";
                return result;
            }
            for (int j = i + 1; j < colInfo.size(); j++) {
                CompositeVBox vboxInner = colInfo.get(i + 1);
                String columnHeaderInner = vboxInner.getCellName().getText();
                if (columnHeader.equals(columnHeaderInner)) {
                    result = "Cell " + vbox.getCellNo().getText() + " is a duplicate of " + vboxInner.getCellNo().getText() + "in selection " + tablevo.getTableDesc();
                    return result;
                }
            }
        }
        return result;
    }

    private boolean validateCompositeBox(CompositeVBox compositeVBox) {
        BasicInfoVO info = (BasicInfoVO) IRS.getLinkedHashMap().get(1);
        int numTables = Integer.valueOf(info.getNumTables());
        if (numTables > this.loadedTableNames.size()) {
            IRSDialog.showAlert(ERROR, "Ensure all tables selections are reviewed");
            return false;
        }
        if (compositeVBox.getCellNo().getText() == null || compositeVBox.getCellNo().getText().isEmpty()) {
            IRSDialog.showAlert(ERROR, FIELD_BLANK);
            return false;

        }
        if (compositeVBox.getCellName().getText() == null || compositeVBox.getCellName().getText().isEmpty()) {
            IRSDialog.showAlert(ERROR, FIELD_BLANK);
            return false;

        }

        System.out.println("compositeVBox.getCellNo() " + compositeVBox.getCellNo());
        if (compositeVBox.getDataTypeCombo() == null || compositeVBox.getDataTypeCombo().getSelectionModel() == null || compositeVBox.getDataTypeCombo().getSelectionModel().getSelectedItem() == null) {
            IRSDialog.showAlert("Data Type", FIELD_BLANK);
            System.out.println("This is combo 1 " + compositeVBox.getDataTypeCombo());
            System.out.println("This is combo1.getSelectionModel() 1 " + compositeVBox.getDataTypeCombo().getSelectionModel());
            return false;
        }

        if (compositeVBox.getCellName().getText() == null || compositeVBox.getCellName().getText().isEmpty()) {
            IRSDialog.showAlert("Cell Name", FIELD_BLANK);
            return false;

        }

        if (compositeVBox.getDataTypeCombo() == null || compositeVBox.getDataTypeCombo().getSelectionModel() == null || compositeVBox.getDataTypeCombo().getSelectionModel().getSelectedItem() == null) {
            IRSDialog.showAlert("Data Size", FIELD_BLANK);
            return false;
        }

        return true;
    }

    /**
     * @return the instance
     */
    public static TablesUI getInstance() {
        if (instance == null) {
            instance = new TablesUI();
        }
        return instance;
    }

    /**
     * @param aInstance the instance to set
     */
    public static void setInstance(TablesUI aInstance) {
        instance = aInstance;
    }

    /**
     * The tables required to be populated are done through the method.
     *
     * @return
     *
     */
    public boolean populateStaticTables() {
        MainDAO irsDAO = new MainDAO();
        boolean result = irsDAO.populateRequiredtables(IRS.linkedHashMap);
//        if(result){
//            result = irsDAO.mapReturnToWorkCollection4Sysnc(headerInfoHashmap);
//        }
        return result;
    }

    public void disPlayHeaderandCol(List<TableVO> col, String method) {
        for (TableVO vo : col) {
            System.out.println("vo " + vo.getTableDesc());
            System.out.println("vo " + vo.getTableID());
            System.out.println("vo " + vo.getExcelColList());
            for (ColObject cols : vo.getExcelColList()) {
                System.out.println("cols from getExcelColList " + cols.getColHeader());
            }
            for (CompositeVBox cols : vo.getColInfo()) {
                System.out.println("cols from CompositeVBox " + cols.getCellName());
            }
            System.out.println("vo " + vo.getColInfo());
            System.out.println("======================print out ended for the table ====================================================");
        }
        System.out.println("****************method ***********888888 " + method);
    }

}
