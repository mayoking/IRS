/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.util;

public class PropertiesLoader {

    /**
     * This method is called by the application at startup and is not meant to
     * be used otherwise. To obtain an instance of the ApplicationProperties
     * during application run, call
     * ApplicationPropertes.getApplicationProperties().
     */
    public static void loadApplicationProperties() {

        ApplicationProperties props = new ApplicationProperties();
//        props.setDefaultCameraName();//"Logitech HD Pro Webcam C910"
//        props.setDefaultPrinterName("Printer");
//        props.setIcaoProfile("C:/Users/Emm/Pictures/Aware/FASYLGroup_CustomProfile.xml");
        ApplicationProperties.setApplicationProperties(props);
    }

    public static int getExcelColumnNumber(String column) {
        int result = 0;
        for (int i = 0; i < column.length(); i++) {
            result *= 26;
            result += column.charAt(i) - 'A' + 1;
        }
        return result;
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

    public static String stripNonDigits(
            final CharSequence input) {
        final StringBuilder sb = new StringBuilder(
                input.length());
        for (int i = 0; i < input.length(); i++) {
            final char c = input.charAt(i);
            if (c > 47 && c < 58) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static int getCellNoIndex(final CharSequence input) {
        int i;
        for ( i = 0; i < input.length(); i++) {
            final char c = input.charAt(i);
            if (c > 47 && c < 58) {
                  break;
            }
        }
        return i;
    }

    public static void main(String[] args) {
        System.out.println("getExcelColumnName " + getExcelColumnName(55));
        System.out.println("getExcelColumnNumber " + getExcelColumnNumber("AA"));
        System.out.println("getCellNoIndex "+getCellNoIndex("AA55"));
    }

}
