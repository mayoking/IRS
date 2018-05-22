/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.ui;

import com.bbt.irs.deploy.IRS;
import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Pair;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.controlsfx.control.spreadsheet.GridBase;
import org.controlsfx.control.spreadsheet.SpreadsheetCell;
import org.controlsfx.control.spreadsheet.SpreadsheetCellType;
import org.controlsfx.control.spreadsheet.SpreadsheetView;

/**
 *
 * @author opeyemi
 */
public class ExcelView {

    /**
     * Path to Excel
     */
    private String filePath;

    private int sheetIndex;

    private boolean editible;

    private FileInputStream inStream;
    private XSSFWorkbook poiWorkbook;
    private XSSFSheet poiSheet;

    private SpreadsheetView theView;

    public ExcelView(String path, int sheetIndex, boolean editable) {
        filePath = path;
        this.editible = editable;
        this.sheetIndex = sheetIndex;
    }

    public ExcelView(String path, int sheetIndex) {
        filePath = path;
        this.editible = false;
        this.sheetIndex = sheetIndex;
    }

    private void initializeView(String path) throws Exception {
        GridBase grid = excelToGrid(path);
        theView = new SpreadsheetView(grid);
        List<Pair<Integer, Integer>> ls = IRS.getHeadersPairs();
        theView.getSelectionModel().selectCells(ls);
        theView.setEditable(editible);
    }

    public SpreadsheetView getView(String path) throws Exception {
        initializeView(path);
        return theView;
    }

//    public void showInNewWindow(){
//        Parent root;
//        try {
//
//        initializeView();
//
//        root = theView;
//        Stage stage = new Stage();
//        stage.setTitle(new File(filePath).getName());
//        stage.setScene(new Scene(root, 450, 450));
//
//        stage.getIcons().addAll(ResourceLoader.getIcons("Excel.ico"));      
//
//        stage.show();
//
//    }
//    catch (Exception e) {
//        e.printStackTrace();
//    }
//}
    /**
     * Updates the values in the view. This may happen after the Excel file has
     * been modified after the initial reading.
     *
     * @param path
     * @throws Exception
     */
    public void updateView(String path) throws Exception {
        GridBase newgrid = excelToGrid(path);

        theView.setGrid(newgrid);
    }

    /**
     * Creates a {@link GridBase} object from the excel file located at the path
     *
     * @return
     * @throws Exception - when opening the file
     */
    private GridBase excelToGrid(String path) throws Exception {

        // Read the Excel document and collect the rows
        openBook(path);
        poiSheet = poiWorkbook.getSheetAt(sheetIndex);
        IRS.setPoisheet(poiSheet);
        int[] size = getSize();
        GridBase grid = new GridBase(size[0], size[1]);

        ObservableList<ObservableList<SpreadsheetCell>> rows = FXCollections.observableArrayList();

        Row poiRow;
        Cell cell;
        String value=null;
        FormulaEvaluator evaluator = poiWorkbook.getCreationHelper().createFormulaEvaluator();

        for (int row = 0; row < grid.getRowCount(); ++row) {
            final ObservableList<SpreadsheetCell> list = FXCollections.observableArrayList();
            poiRow = poiSheet.getRow(row);
            for (int column = 0; column < grid.getColumnCount(); ++column) {
                System.out.println("column 33333 " + column);
                if (poiRow != null) {
                    cell = poiRow.getCell(column, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    value = getCellValueAsString(cell);//ExcelUtils.cellStringValue(evaluator,cell);
                }
                list.add(SpreadsheetCellType.STRING.createCell(row, column, 1, 1, value));
            }
            rows.add(list);
        }
        grid.setRows(rows);

        closeBook();

        return grid;
    }

    /**
     * Calculates the number of rows and columns in the sheet by looping and
     * reading all the things :)
     *
     * @return the size as int[{rows, cols}]
     */
    private int[] getSize() {

        int numRows = 0;
        int numCols = 0;

        int nullRowCounter = 0;
        int nullColCounter = 0;

        int maxNullRows = 6;
        int maxNullCols = 6;

        Row row;
        Cell cell;
        int localColCounter;

        while (true) {

            row = poiSheet.getRow(numRows);
            numRows++;

            // Check row...
            if (row == null) {
                nullRowCounter++;
            } else {
                nullRowCounter = 0;
                // If row not null, check columns...
                localColCounter = 0;
                while (true) {
                    cell = row.getCell(localColCounter);
                    localColCounter++;
                    if (cell == null) {
                        nullColCounter++;
                    } else {
                        nullColCounter = 0;
                    }

                    if (nullColCounter == maxNullCols) {
                        // reached max null cells
                        localColCounter -= maxNullCols;

                        if (localColCounter > numCols) {
                            numCols = localColCounter;
                        }

                        break;
                        // go to next row...
                    }

                }
            }

            if (nullRowCounter == maxNullRows) {
                // reached max null rows
                numRows -= maxNullRows;

                break;

            }
        }
        System.out.println("numrows " + numRows + "  " + numCols);
        IRS.setTotalExcelRowno(numRows);
        IRS.setTotalExcelColno(numCols);
        return new int[]{numRows, numCols};

    }

    private void openBook(String filePathz) throws Exception {
        try {
            filePath = filePathz;
            File myFile = new File(filePath);
            inStream = new FileInputStream(myFile);

            poiWorkbook = new XSSFWorkbook(inStream);

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    private void closeBook() throws Exception {

        try {
            poiWorkbook.close();
            inStream.close();

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static String getCellValueAsString(Cell cell) {
        String strCellValue = "xxx";
        if (cell != null) {
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_STRING:
                    strCellValue = cell.toString();
                    break;
                case Cell.CELL_TYPE_NUMERIC:
                    if (DateUtil.isCellDateFormatted(cell)) {
                        SimpleDateFormat dateFormat = new SimpleDateFormat(
                                "dd/MM/yyyy");
                        strCellValue = dateFormat.format(cell.getDateCellValue());
                    } else {
                        Double value = cell.getNumericCellValue();
                        Long longValue = value.longValue();
                        strCellValue = new String(longValue.toString());
                    }
                    break;
                case Cell.CELL_TYPE_BOOLEAN:
                    strCellValue = new String(new Boolean(
                            cell.getBooleanCellValue()).toString());
                    break;
                case Cell.CELL_TYPE_BLANK:
                    strCellValue = "";
                    break;
            }
        }
        return strCellValue;
    }

    public static int NumberIndexToColumnIndex(String columnName) {
        columnName = columnName.toUpperCase();
        int value = 0;
        for (int i = 0; i < columnName.length(); i++) {
            int delta = columnName.charAt(i) - 64;
            System.out.println("columnName.charAt(i) " + columnName.charAt(i));
            System.out.println("delta " + delta);
            value = value * 26 + delta;
        }
        return value - 1;
    }

    public static void main(String[] rgs) {
        System.out.println(NumberIndexToColumnIndex("BZ9"));
    }

}
