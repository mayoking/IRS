/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.vo;

import java.util.Date;

/**
 *
 * @author tkola
 */
public class SCTPackagerVO {

    /**
     * @return the releaseNote
     */
    public String getReleaseNote() {
        return releaseNote;
    }

    /**
     * @param releaseNote the releaseNote to set
     */
    public void setReleaseNote(String releaseNote) {
        this.releaseNote = releaseNote;
    }

      /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the futuredate
     */
    public Date getFuturedate() {
        return futuredate;
    }

    /**
     * @return the riType
     */
    public String getRiType() {
        return riType;
    }

    /**
     * @param futuredate the futuredate to set
     */
    public void setFuturedate(Date futuredate) {
        this.futuredate = futuredate;
    }

    /**
     * @param riType the riType to set
     */
    public void setRiType(String riType) {
        this.riType = riType;
    }
    private String riType;
    private Date futuredate;
    private String username;
    private String releaseNote;
}
