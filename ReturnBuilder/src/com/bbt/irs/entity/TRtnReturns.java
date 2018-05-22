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
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tkola
 */
@Entity
@Table(name = "t_rtn_returns")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TRtnReturns.findAll", query = "SELECT t FROM TRtnReturns t")
    , @NamedQuery(name = "TRtnReturns.findByReturnCode", query = "SELECT t FROM TRtnReturns t WHERE t.returnCode = :returnCode")
    , @NamedQuery(name = "TRtnReturns.findByDescription", query = "SELECT t FROM TRtnReturns t WHERE t.description = :description")
    , @NamedQuery(name = "TRtnReturns.findByStartValidityDate", query = "SELECT t FROM TRtnReturns t WHERE t.startValidityDate = :startValidityDate")
    , @NamedQuery(name = "TRtnReturns.findByEndValidityDate", query = "SELECT t FROM TRtnReturns t WHERE t.endValidityDate = :endValidityDate")
    , @NamedQuery(name = "TRtnReturns.findByCreatedDate", query = "SELECT t FROM TRtnReturns t WHERE t.createdDate = :createdDate")
    , @NamedQuery(name = "TRtnReturns.findByCreatedBy", query = "SELECT t FROM TRtnReturns t WHERE t.createdBy = :createdBy")
    , @NamedQuery(name = "TRtnReturns.findByLastModified", query = "SELECT t FROM TRtnReturns t WHERE t.lastModified = :lastModified")
    , @NamedQuery(name = "TRtnReturns.findByModifiedBy", query = "SELECT t FROM TRtnReturns t WHERE t.modifiedBy = :modifiedBy")})
public class TRtnReturns implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "return_code")
    private String returnCode;
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @Column(name = "start_validity_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startValidityDate;
    @Basic(optional = false)
    @Column(name = "end_validity_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endValidityDate;
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(name = "created_by")
    private String createdBy;
    @Basic(optional = false)
    @Column(name = "last_modified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModified;
    @Basic(optional = false)
    @Column(name = "modified_by")
    private String modifiedBy;
    @JoinColumn(name = "frequency", referencedColumnName = "freq_unit")
    @ManyToOne(optional = false)
    private TLkupFrequency frequency;

    public TRtnReturns() {
    }

    public TRtnReturns(String returnCode) {
        this.returnCode = returnCode;
    }

    public TRtnReturns(String returnCode, Date startValidityDate, Date endValidityDate, Date lastModified, String modifiedBy) {
        this.returnCode = returnCode;
        this.startValidityDate = startValidityDate;
        this.endValidityDate = endValidityDate;
        this.lastModified = lastModified;
        this.modifiedBy = modifiedBy;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartValidityDate() {
        return startValidityDate;
    }

    public void setStartValidityDate(Date startValidityDate) {
        this.startValidityDate = startValidityDate;
    }

    public Date getEndValidityDate() {
        return endValidityDate;
    }

    public void setEndValidityDate(Date endValidityDate) {
        this.endValidityDate = endValidityDate;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
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

    public TLkupFrequency getFrequency() {
        return frequency;
    }

    public void setFrequency(TLkupFrequency frequency) {
        this.frequency = frequency;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (returnCode != null ? returnCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TRtnReturns)) {
            return false;
        }
        TRtnReturns other = (TRtnReturns) object;
        if ((this.returnCode == null && other.returnCode != null) || (this.returnCode != null && !this.returnCode.equals(other.returnCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return description;
    }
    
}
