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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tkola
 */
@Entity
@Table(name = "t_rb_reason_deactivation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TRbReasonDeactivation.findAll", query = "SELECT t FROM TRbReasonDeactivation t")
    , @NamedQuery(name = "TRbReasonDeactivation.findByReasonId", query = "SELECT t FROM TRbReasonDeactivation t WHERE t.reasonId = :reasonId")
    , @NamedQuery(name = "TRbReasonDeactivation.findByReturnCode", query = "SELECT t FROM TRbReasonDeactivation t WHERE t.returnCode = :returnCode")
    , @NamedQuery(name = "TRbReasonDeactivation.findByReasonDeactivation", query = "SELECT t FROM TRbReasonDeactivation t WHERE t.reasonDeactivation = :reasonDeactivation")
    , @NamedQuery(name = "TRbReasonDeactivation.findByCreatedDate", query = "SELECT t FROM TRbReasonDeactivation t WHERE t.createdDate = :createdDate")
    , @NamedQuery(name = "TRbReasonDeactivation.findByCreatedBy", query = "SELECT t FROM TRbReasonDeactivation t WHERE t.createdBy = :createdBy")})
public class TRbReasonDeactivation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "reason_id")
     @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reasonId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "return_code")
    private String returnCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "reason_deactivation")
    private String reasonDeactivation;
    @Basic(optional = false)
    @NotNull
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "created_by")
    private String createdBy;

    public TRbReasonDeactivation() {
    }

    public TRbReasonDeactivation(Integer reasonId) {
        this.reasonId = reasonId;
    }

    public TRbReasonDeactivation(Integer reasonId, String returnCode, String reasonDeactivation, Date createdDate, String createdBy) {
        this.reasonId = reasonId;
        this.returnCode = returnCode;
        this.reasonDeactivation = reasonDeactivation;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
    }

    public Integer getReasonId() {
        return reasonId;
    }

    public void setReasonId(Integer reasonId) {
        this.reasonId = reasonId;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReasonDeactivation() {
        return reasonDeactivation;
    }

    public void setReasonDeactivation(String reasonDeactivation) {
        this.reasonDeactivation = reasonDeactivation;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reasonId != null ? reasonId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TRbReasonDeactivation)) {
            return false;
        }
        TRbReasonDeactivation other = (TRbReasonDeactivation) object;
        if ((this.reasonId == null && other.reasonId != null) || (this.reasonId != null && !this.reasonId.equals(other.reasonId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bbt.irs.entity.TRbReasonDeactivation[ reasonId=" + reasonId + " ]";
    }
    
}
