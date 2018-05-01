/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs;

import javafx.scene.control.Tab;

public interface TabPaneController {
    //public SummaryController getSummaryController();
    public Tab getPersonalInfoTab();
    public Tab getPhotoTab();
    public Tab getContactInfoTab();
    public Tab getWorkInfoTab();
    public Tab getFingerPrintTab();
    public Tab getOtherInfoTab();
    public void cleanUp();
    public void gotoNextTab();
    public void gotoPrevTab();
    public int getSelectedTab();
}
