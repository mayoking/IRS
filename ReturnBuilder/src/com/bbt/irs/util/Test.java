/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.util;

import static com.bbt.irs.util.Utility.convertExcelCellToPair;
import static com.bbt.irs.util.Utility.getCellNoIndex;
import javafx.util.Pair;

/**
 *
 * @author tkola
 */
public class Test {
 public static void main(String [] args){
        System.out.println("convertExcelCellToPair "+convertExcelCellToPair("A8"));
    }
    
    public static Pair convertExcelCellToPair(String cellNo) {
        int index = getCellNoIndex(cellNo);
        int xCordinate = Utility.NumberIndexToColumnIndex(cellNo.substring(0, index));
        int yCoordinate = Integer.valueOf(cellNo.substring(index));
        System.out.printf("xcorodinate is %s yCoordinate is  ", xCordinate, yCoordinate);
        Pair pair = new Pair(yCoordinate - 1, xCordinate - 1);
        return pair;
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
        return i+1;
    }
    
     public static String convertExcelCellToCoordinate(String cellName) {
        int xCordinate = Utility.convertAlphabetToNumber(cellName.substring(0, 1));
        int yCoordinate = Integer.valueOf(cellName.substring(1));
        String coordinate = xCordinate + "," + yCoordinate;
        return coordinate;
    }
}
