/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.deploy;

/**
 *
 * @author tkola
 */
public class MenuHighlight {

    private static String activeTopMenuClicked;
    private static String activeSubMneuclicked;
    private static String prevActiveTopMenuClicked;
    private static String prevActiveSubMneuclicked;

    /**
     * @return the activeSubMneuclicked
     */
    public static String getActiveSubMneuclicked() {
        return activeSubMneuclicked;
    }

    /**
     * @return the activeTopMenuClicked
     */
    public static String getActiveTopMenuClicked() {
        return activeTopMenuClicked;
    }

    /**
     * @return the prevActiveSubMneuclicked
     */
    public static String getPrevActiveSubMneuclicked() {
        return prevActiveSubMneuclicked;
    }

    /**
     * @return the prevActiveTopMenuClicked
     */
    public static String getPrevActiveTopMenuClicked() {
        return prevActiveTopMenuClicked;
    }

    /**
     * @param aActiveSubMneuclicked the activeSubMneuclicked to set
     */
    public static void setActiveSubMneuclicked(String aActiveSubMneuclicked) {
        activeSubMneuclicked = aActiveSubMneuclicked;
    }

    /**
     * @param aActiveTopMenuClicked the activeTopMenuClicked to set
     */
    public static void setActiveTopMenuClicked(String aActiveTopMenuClicked) {
        activeTopMenuClicked = aActiveTopMenuClicked;
    }

    /**
     * @param aPrevActiveSubMneuclicked the prevActiveSubMneuclicked to set
     */
    public static void setPrevActiveSubMneuclicked(String aPrevActiveSubMneuclicked) {
        prevActiveSubMneuclicked = aPrevActiveSubMneuclicked;
    }

    /**
     * @param aPrevActiveTopMenuClicked the prevActiveTopMenuClicked to set
     */
    public static void setPrevActiveTopMenuClicked(String aPrevActiveTopMenuClicked) {
        prevActiveTopMenuClicked = aPrevActiveTopMenuClicked;
    }

}
