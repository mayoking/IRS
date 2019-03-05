/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.util;

import com.bbt.irs.vo.RefactorUploadObject;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.controlsfx.control.spreadsheet.GridBase;

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
    private Integer minRows = 0;
    private static List<String> listofUploadObjects = new ArrayList();
    private Integer minColHeaders = 0;
    private static int[] totalRowsCols;
    
    private FileInputStream inStream;
    private XSSFWorkbook poiWorkbook;
    private XSSFSheet poiSheet;
    
    public ExcelView(String path, int sheetIndex, boolean editable) {
        filePath = path;
        this.editible = editable;
        this.sheetIndex = sheetIndex;
    }
    
    public ExcelView(String path, int sheetIndex) throws IOException {
        filePath = path;
        this.editible = false;
        this.sheetIndex = sheetIndex;
        excelToGrid(path);
    }
    
    private void initializeView(List ls) throws IOException {
        excelToGrid(filePath);
    }
    
    public void getView(List ls) throws IOException {
        initializeView(ls);
        //return theView;
    }

    /**
     * Creates a {@link GridBase} object from the excel file located at the path
     *
     * @return
     * @throws Exception - when opening the file
     */
    private void excelToGrid(String path) throws IOException {
        listofUploadObjects = new ArrayList();
        // Read the Excel document and collect the rows
        openBook(path);
        setPoiSheet(poiWorkbook.getSheetAt(sheetIndex));
        int[] grid = getSize();
        
        Row poiRow;
        Cell cell;
        String value = null;
        FormulaEvaluator evaluator = poiWorkbook.getCreationHelper().createFormulaEvaluator();
        
        for (int row = 0; row < grid[0]; ++row) {
            poiRow = getPoiSheet().getRow(row);
            for (int column = 0; column < grid[1]; ++column) {
                //System.out.println("column 33333 " + column);

                if (poiRow != null) {
                    
                    cell = poiRow.getCell(column, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    value = getCellValueAsString(cell);//ExcelUtils.cellStringValue(evaluator,cell);
                    if (minRows != 0 && row > minRows) {
                        if (getMinColHeaders() == (column) && !(value == null || value.isEmpty())) {
                            listofUploadObjects.add(value);
                            
                        }
                        
                    }
                    //System.out.println("list of item codes size  "+listofUploadObjects.size());

                }
            }
            
        }
        
        totalRowsCols = this.getSize();
        closeBook();
        
    }
    
    public  List<RefactorUploadObject>  readExcelUploadObject(String path) throws IOException {
        List<RefactorUploadObject> listofUploadObject  = new ArrayList();
        // Read the Excel document and collect the rows
        openBook(path);
        setPoiSheet(poiWorkbook.getSheetAt(sheetIndex));
        int[] grid = getSize();
        
        Row poiRow;
        Cell cell;
        String value;
        FormulaEvaluator evaluator = poiWorkbook.getCreationHelper().createFormulaEvaluator();
        
        for (int row = 1; row < grid[0]; ++row) {
            poiRow = getPoiSheet().getRow(row);
            if (poiRow != null) {
                RefactorUploadObject refactorUploadObject = new RefactorUploadObject();
                cell = poiRow.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                value = getCellValueAsString(cell);//ExcelUtils.cellStringValue(evaluator,cell);
                System.out.println("value --- "+value);
                if(value==null){
                    continue;
                }
                refactorUploadObject.setItemCode(value);
                cell = poiRow.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                value = getCellValueAsString(cell);//ExcelUtils.cellStringValue(evaluator,cell);
                refactorUploadObject.setItemDescription(value);
                
                listofUploadObject.add(refactorUploadObject);
                
            }
            
        }
        
        totalRowsCols = this.getSize();
        closeBook();
        return listofUploadObject;
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
            
            row = getPoiSheet().getRow(numRows);
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
        return new int[]{numRows, numCols};
        
    }
    
    private void openBook(String filePathz) throws FileNotFoundException, IOException {
        
        filePath = filePathz;
        File myFile = new File(filePath);
        inStream = new FileInputStream(myFile);
        
        poiWorkbook = new XSSFWorkbook(inStream);
        
    }
    
    private void closeBook() throws IOException {
        
        poiWorkbook.close();
        inStream.close();
        
    }
    
    public static String getCellValueAsString(Cell cell) {
        String strCellValue = "";
        if (cell != null) {
            switch (cell.getCellTypeEnum()) {
                case STRING:
                     System.out.println("from the platform of string "+cell.toString()+" --- "+cell);
                    strCellValue = cell.toString();
                    break;
                case NUMERIC:
                    if (DateUtil.isCellDateFormatted(cell)) {
                        SimpleDateFormat dateFormat = new SimpleDateFormat(
                                "YYYY-MM-dd");
                        strCellValue = dateFormat.format(cell.getDateCellValue());
                    } else {
                        System.out.println("from the pplatform of numeric "+cell.getNumericCellValue());
                        Double value = cell.getNumericCellValue();
                        //DecimalFormat pattern = new DecimalFormat("#,#,#,#,#,#,#,#,#,#");	
                        Long longValue = value.longValue();
                        strCellValue = cell.getNumericCellValue()+"";
                    }
                    break;
                case BOOLEAN:
                    strCellValue = Boolean.toString(cell.getBooleanCellValue());
                    break;
                case BLANK:
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
            value = value * 26 + delta;
        }
        return value - 1;
    }
    
    public static void main(String[] rgs) {
        System.out.println(NumberIndexToColumnIndex("BZ9"));
    }

    /**
     * @return the minRows
     */
    public Integer getMinRows() {
        return minRows;
    }

    /**
     * @param minRows the minRows to set
     */
    public void setMinRows(Integer minRows) {
        this.minRows = minRows;
    }

    /**
     * @return the minColHeaders
     */
    public Integer getMinColHeaders() {
        return minColHeaders;
    }

    /**
     * @param minColHeaders the minColHeaders to set
     */
    public void setMinColHeaders(Integer minColHeaders) {
        this.minColHeaders = minColHeaders;
    }

    /**
     * @return the totalRowsCols
     */
    public static int[] getTotalRowsCols() {
        return totalRowsCols;
    }

    /**
     * @param aTotalRowsCols the totalRowsCols to set
     */
    public static void setTotalRowsCols(int[] aTotalRowsCols) {
        totalRowsCols = aTotalRowsCols;
    }

    /**
     * @return the poiSheet
     */
    public XSSFSheet getPoiSheet() {
        return poiSheet;
    }

    /**
     * @param poiSheet the poiSheet to set
     */
    public void setPoiSheet(XSSFSheet poiSheet) {
        this.poiSheet = poiSheet;
    }

    /**
     * @return the listofUploadObjects
     */
    public static List<String> getListofUploadObjects() {
        return listofUploadObjects;
    }

    /**
     * @param aListOfItemCodes the listofUploadObjects to set
     */
    public static void setListofUploadObjects(List<String> aListOfItemCodes) {
        listofUploadObjects = aListOfItemCodes;
    }
    
}
