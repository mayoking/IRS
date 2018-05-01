/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.util;


public class PropertiesLoader {
    /**
     * This method is called by the application at startup and is not meant to be used otherwise.
     * To obtain an instance of the ApplicationProperties during application run, call
     * ApplicationPropertes.getApplicationProperties().
     */
    public static void loadApplicationProperties(){
        
        ApplicationProperties props = new ApplicationProperties();
//        props.setDefaultCameraName();//"Logitech HD Pro Webcam C910"
//        props.setDefaultPrinterName("Printer");
//        props.setIcaoProfile("C:/Users/Emm/Pictures/Aware/FASYLGroup_CustomProfile.xml");
        ApplicationProperties.setApplicationProperties(props);
    }
    
    
}
