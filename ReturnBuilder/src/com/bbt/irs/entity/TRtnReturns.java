/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author opeyemi
 */
@Entity
@Table(name = "t_rtn_returns")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TRtnReturns.findAll", query = "SELECT t FROM TRtnReturns t")
    , @NamedQuery(name = "TRtnReturns.findByReturnCode", query = "SELECT t FROM TRtnReturns t WHERE t.tRtnReturnsPK.returnCode = :returnCode")
    , @NamedQuery(name = "TRtnReturns.findByDescription", query = "SELECT t FROM TRtnReturns t WHERE t.description = :description")
    , @NamedQuery(name = "TRtnReturns.findByStartValidityDate", query = "SELECT t FROM TRtnReturns t WHERE t.tRtnReturnsPK.startValidityDate = :startValidityDate")
    , @NamedQuery(name = "TRtnReturns.findByEndValidityDate", query = "SELECT t FROM TRtnReturns t WHERE t.endValidityDate = :endValidityDate")
    , @NamedQuery(name = "TRtnReturns.findByFrequency", query = "SELECT t FROM TRtnReturns t WHERE t.tRtnReturnsPK.frequency = :frequency")
    , @NamedQuery(name = "TRtnReturns.findByLastModified", query = "SELECT t FROM TRtnReturns t WHERE t.lastModified = :lastModified")
    , @NamedQuery(name = "TRtnReturns.findByModifiedBy", query = "SELECT t FROM TRtnReturns t WHERE t.modifiedBy = :modifiedBy")})
public class TRtnReturns implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TRtnReturnsPK tRtnReturnsPK;
    @Column(name = "description")
    private String description;
    @Column(name = "end_validity_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endValidityDate;
    @Column(name = "last_modified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModified;
    @Column(name = "modified_by")
    private String modifiedBy;

    public TRtnReturns() {
    }

    public TRtnReturns(TRtnReturnsPK tRtnReturnsPK) {
        this.tRtnReturnsPK = tRtnReturnsPK;
    }

    public TRtnReturns(String returnCode, Date startValidityDate, String frequency) {
        this.tRtnReturnsPK = new TRtnReturnsPK(returnCode, startValidityDate, frequency);
    }

    public TRtnReturnsPK getTRtnReturnsPK() {
        return tRtnReturnsPK;
    }

    public void setTRtnReturnsPK(TRtnReturnsPK tRtnReturnsPK) {
        this.tRtnReturnsPK = tRtnReturnsPK;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getEndValidityDate() {
        return endValidityDate;
    }

    public void setEndValidityDate(Date endValidityDate) {
        this.endValidityDate = endValidityDate;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tRtnReturnsPK != null ? tRtnReturnsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TRtnReturns)) {
            return false;
        }
        TRtnReturns other = (TRtnReturns) object;
        if ((this.tRtnReturnsPK == null && other.tRtnReturnsPK != null) || (this.tRtnReturnsPK != null && !this.tRtnReturnsPK.equals(other.tRtnReturnsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bbt.irs.entity.TRtnReturns[ tRtnReturnsPK=" + tRtnReturnsPK + " ]";
    }
    
}
