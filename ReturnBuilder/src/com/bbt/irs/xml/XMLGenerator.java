/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.xml;

import com.bbt.irs.ExcelView;
import com.bbt.irs.IRS;
import com.bbt.irs.util.Utility;
import com.bbt.irs.vo.BasicInfoVO;
import com.bbt.irs.vo.HeaderInfoVO;
import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import javafx.util.Pair;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author opeyemi
 */
public class XMLGenerator {

    Workbook workbook;
    FileInputStream fileInputStream = null;
    private int numberOfTables;
    private LinkedList listRowNumbervar = new LinkedList();
    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(XMLGenerator.class);
    public XMLGenerator() {

    }

    public boolean generateXML4romExcel(LinkedHashMap linkedHashMap) {
        boolean result;
        try {
            BasicInfoVO basicInfo = (BasicInfoVO) linkedHashMap.get(1);
            LinkedHashMap<Integer, LinkedList<HeaderInfoVO>> ls = (LinkedHashMap<Integer, LinkedList<HeaderInfoVO>>) linkedHashMap.get(2);
            // HeaderInfoVO headerInfo = ls.get(ls)
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();
            Element mainRootElement = document.createElement("template");
            //document.appendChild(mainRootElement);
            Element rootElement = document.createElement("package");
            //mainRootElement.appendChild(rootElement);
            Element packageName = document.createElement("package_code");
            packageName.appendChild(document.createTextNode(basicInfo.getRitype().getRiTypeCode()));
            rootElement.appendChild(packageName);
            Element header = document.createElement("header");
            Element bankCode = document.createElement("bank_code");
            bankCode.appendChild(document.createTextNode("0"));
            header.appendChild(bankCode);
            Element asAT = document.createElement("as_at");
            asAT.appendChild(document.createTextNode("0"));
            header.appendChild(asAT);
            rootElement.appendChild(header);
            Element docum = document.createElement("document");
            if (ls.size() > 1) {
                this.listRowNumbervar = this.listRowNumber(ls);
            }
            Element MBR = document.createElement(basicInfo.getTemplateCode());
            for (Integer i = 0; i < ls.size(); i++) {
                System.out.println("ls " + ls);
                System.out.println("ls " + ls.isEmpty());
                LinkedList<HeaderInfoVO> headerInfos = ls.get(i + 1);
                Element table = document.createElement("section");
                table.setAttribute("table_code", i.toString());

                Integer lastRow = 0;
                if (i + 1 < ls.size()) {
                    lastRow = (Integer) listRowNumbervar.get(i + 1);
                } else {
                    lastRow = IRS.getTotalExcelRowno() - 1;
                }
                System.out.println("size " + ls.size());
                System.out.printf("mastrow is %s and minimumrow is %s", lastRow, this.getMinimumRowNumber(ls));
                for (Integer j = this.getMinimumRowNumber(ls) + 1; j < lastRow; j++) {
                    Row poiRow = IRS.getPoisheet().getRow(j);
                    int counter = 0;
                    Element elem = document.createElement("code");
                    System.out.println("getMinimumColumnNumber(ls) " + getMinimumColumnNumber(ls));
                    System.out.println("headerInfos.size() " + headerInfos.size());
                    for (Integer k = getMinimumColumnNumber(ls); k < headerInfos.size(); k++) {
                        Cell cell = poiRow.getCell(k);
                        if (counter == 0) {

                            elem.setAttribute("item_code", ExcelView.getCellValueAsString(cell));
                            elem.setAttribute("header", headerInfos.get(counter).getCellName());
                           
                        } else {
                            Element otherElem = generateOtherElement(document, headerInfos.get(counter), j.toString(), cell);
                            elem.appendChild(otherElem);
                        }

                        counter++;
                    }

                    table.appendChild(elem);

                }
                   MBR.appendChild(table);
            }
            
            docum.appendChild(MBR);
            rootElement.appendChild(docum);
            mainRootElement.appendChild(rootElement);
            document.appendChild(mainRootElement);
            IRS.setDocument(document);
            printXMLDoc(document);
            
        } catch (ParserConfigurationException | IOException ex) {
            LOGGER.log(Level.FATAL, "Error while Generating XML", ex);
        }
        result = true;
        return result;
    }

    public void printXMLDoc(Document doc) throws IOException {
        OutputFormat format = new OutputFormat(doc);
        format.setIndenting(true);
        XMLSerializer serializer = new XMLSerializer(System.out, format);
        serializer.serialize(doc);
        LOGGER.info(serializer);
    }

    public Element generateItemCode(Document document, String header, String no) {
        Element otherElem = document.createElement("item");
        otherElem.setAttribute("itemcode", no);
        otherElem.setAttribute("header", header);
        return otherElem;
    }

    public Element generateOtherElement(Document document, HeaderInfoVO headerinfo, String no, Cell cell) {
        Element otherElem ;
        if (headerinfo.getDataType().getDatatypeDesc().equals("Number")) {
            otherElem = document.createElement("data_num");

        } else if (headerinfo.getDataType().getDatatypeDesc().equals("Text")) {
            otherElem = document.createElement("data_str");
        } else {
            otherElem = document.createElement("data_date");
        }

        otherElem.setAttribute("code", no);
        otherElem.appendChild(document.createTextNode(ExcelView.getCellValueAsString(cell)));
        otherElem.setAttribute("header", headerinfo.getCellName());

        return otherElem;
    }

   

    public Integer getMinimumRowNumber(LinkedHashMap<Integer, LinkedList<HeaderInfoVO>> ls) {
        LinkedList<HeaderInfoVO> ls2 = (LinkedList<HeaderInfoVO>) ls.get(1);
        Pair pair = Utility.convertExcelCellToPair(ls2.get(0).getCellNO());
        return (Integer) pair.getKey();

    }

    public Integer getMinimumColumnNumber(LinkedHashMap<Integer, LinkedList<HeaderInfoVO>> ls) {
        LinkedList<HeaderInfoVO> ls2 = (LinkedList<HeaderInfoVO>) ls.get(1);
        System.out.println("ls2.get(0).getCellNO() "+ls2.get(0).getCellNO());
        Pair pair = Utility.convertExcelCellToPair(ls2.get(0).getCellNO());
        System.out.println("pair.getValue() "+pair.getValue());
        return (Integer) pair.getValue();

    }

    public LinkedList<Integer> listRowNumber(LinkedHashMap<Integer, LinkedList<HeaderInfoVO>> linkedHashMap) {
        numberOfTables = linkedHashMap.size();
        LinkedList<Integer> listRowNum = new LinkedList();
        Integer minRowNum;

        for (int i = 0; i < numberOfTables; i++) {
            //LinkedHashMap<Integer, LinkedList<HeaderInfoVO>> ls = (LinkedHashMap<Integer, LinkedList<HeaderInfoVO>>) linkedHashMap.get(i+1);
            minRowNum = getMinimumRowNumber(linkedHashMap);
            listRowNum.addFirst(minRowNum);
        }
        return listRowNum;
    }


}
