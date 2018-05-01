/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author opeyemi
 */
@Embeddable
public class TRtnReturnsPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "return_code")
    private String returnCode;
    @Basic(optional = false)
    @Column(name = "start_validity_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startValidityDate;
    @Basic(optional = false)
    @Column(name = "frequency")
    private String frequency;

    public TRtnReturnsPK() {
    }

    public TRtnReturnsPK(String returnCode, Date startValidityDate, String frequency) {
        this.returnCode = returnCode;
        this.startValidityDate = startValidityDate;
        this.frequency = frequency;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public Date getStartValidityDate() {
        return startValidityDate;
    }

    public void setStartValidityDate(Date startValidityDate) {
        this.startValidityDate = startValidityDate;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (returnCode != null ? returnCode.hashCode() : 0);
        hash += (startValidityDate != null ? startValidityDate.hashCode() : 0);
        hash += (frequency != null ? frequency.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TRtnReturnsPK)) {
            return false;
        }
        TRtnReturnsPK other = (TRtnReturnsPK) object;
        if ((this.returnCode == null && other.returnCode != null) || (this.returnCode != null && !this.returnCode.equals(other.returnCode))) {
            return false;
        }
        if ((this.startValidityDate == null && other.startValidityDate != null) || (this.startValidityDate != null && !this.startValidityDate.equals(other.startValidityDate))) {
            return false;
        }
        if ((this.frequency == null && other.frequency != null) || (this.frequency != null && !this.frequency.equals(other.frequency))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bbt.irs.entity.TRtnReturnsPK[ returnCode=" + returnCode + ", startValidityDate=" + startValidityDate + ", frequency=" + frequency + " ]";
    }
    
}
