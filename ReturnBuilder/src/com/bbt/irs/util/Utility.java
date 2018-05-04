/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.util;

import com.bbt.dialogfx.DialogFX;
import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Pair;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import static jdk.nashorn.internal.objects.NativeRegExp.source;
import org.w3c.dom.Document;

/**
 *
 * @author opeyemi
 */
public class Utility {

    /**
     *
     */
    protected static DialogFX dialogFX = new DialogFX();

    public static int showDialog(String title, String message, boolean error) {
        String messages = String.format(message, title);
        if (error) {
            dialogFX.setType(DialogFX.Type.ERROR);
        } else {
            dialogFX.setType(DialogFX.Type.INFO);
        }
        dialogFX.setTitleText(title);
        dialogFX.setMessage(messages);
        return dialogFX.showDialog();

    }

    public static int showDialog(String message, boolean error) {
        String messages = String.format(message);
        String tittle = null;
        if (error) {
            dialogFX.setType(DialogFX.Type.ERROR);
            tittle = "ERROR!!!";
        } else {
            dialogFX.setType(DialogFX.Type.INFO);
            tittle = "Info";
        }
        dialogFX.setTitleText(tittle);
        dialogFX.setMessage(messages);

        return dialogFX.showDialog();

    }

    public static int convertAlphabetToNumber(String alphabet) {
        int result = 0;
        switch (alphabet) {
            case "A":
                result = 1;
                break;
            case "B":
                result = 2;
                break;
            case "C":
                result = 3;
                break;
            case "D":
                result = 4;
                break;
            case "E":
                result = 5;
                break;
            case "F":
                result = 6;
                break;
            case "G":
                result = 7;
                break;
            case "H":
                result = 8;
                break;
            case "I":
                result = 9;
                break;
            case "J":
                result = 10;
                break;
            case "K":
                result = 11;
                break;
            case "L":
                result = 12;
                break;
            case "M":
                result = 13;
                break;
            case "N":
                result = 14;
                break;
            case "O":
                result = 15;
                break;
            case "P":
                result = 16;
                break;
            case "Q":
                result = 17;
                break;
            case "R":
                result = 18;
                break;
            case "S":
                result = 19;
                break;
            case "T":
                result = 20;
                break;
            case "U":
                result = 21;
                break;
            case "V":
                result = 22;
                break;
            case "W":
                result = 23;
                break;
            case "X":
                result = 24;
                break;
            case "Y":
                result = 25;
                break;
            case "Z":
                result = 26;
                break;

        }
        return result;
    }

    public static String convertExcelCellToCoordinate(String cellName) {
        int index = getCellNoIndex(cellName);
        System.out.println("test " + index);
        int xCordinate = NumberIndexToColumnIndex(cellName.substring(0, index));
        int yCoordinate = Integer.valueOf(cellName.substring(index));
        String coordinate = xCordinate + "," + yCoordinate;
        System.out.println("coordinate " + coordinate);
        return coordinate;
    }

    public static Pair convertExcelCellToPair(String cellNo) {
        int index = getCellNoIndex(cellNo);
        int xCordinate = Utility.NumberIndexToColumnIndex(cellNo.substring(0, index));
        int yCoordinate = Integer.valueOf(cellNo.substring(index));
        System.out.printf("xcorodinate is %s yCoordinate is  ", xCordinate, yCoordinate);
        Pair pair = new Pair(yCoordinate - 1, xCordinate);
        return pair;
    }

    public static Date getCurrentTime() {
        Date date = new Date();

        return date;
    }

    public static byte[] convertDocument2Byte(Document doc) {
        byte[] array = null;
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            StreamResult result = new StreamResult(bos);
            Transformer transformer = TransformerFactory.newInstance()
                    .newTransformer();
            DOMSource source = new DOMSource(doc);
            transformer.transform(source, result);
            array = bos.toByteArray();
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, ex);
        }

        return array;
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

    public static int getCellNoIndex(final CharSequence input) {
        int i;
        for (i = 0; i < input.length(); i++) {
            final char c = input.charAt(i);
            if (c > 47 && c < 58) {
                break;
            }
        }
        return i;
    }

    public String getLabel(String cellno) {

        return null;
    }
    
    public static String getExcelColumnName(int number) {
        final StringBuilder sb = new StringBuilder();

        int num = number - 1;
        while (num >= 0) {
            int numChar = (num % 26) + 65;
            sb.append((char) numChar);
            num = (num / 26) - 1;
        }
        return sb.reverse().toString();
    }
    
    public String getCellLabel(String cellName){
        
        return null;
    }
    
//    public static void main(String [] args){
//        System.out.println("convertExcelCellToPair "+convertExcelCellToPair("A8"));
//    }
}
