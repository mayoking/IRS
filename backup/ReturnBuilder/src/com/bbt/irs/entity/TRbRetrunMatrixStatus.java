/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author tkola
 */
@Entity
@Table(name = "t_rb_retrun_matrix_status")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TRbRetrunMatrixStatus.findAll", query = "SELECT t FROM TRbRetrunMatrixStatus t")
    , @NamedQuery(name = "TRbRetrunMatrixStatus.findByStatusCode", query = "SELECT t FROM TRbRetrunMatrixStatus t WHERE t.statusCode = :statusCode")
    , @NamedQuery(name = "TRbRetrunMatrixStatus.findByStatusName", query = "SELECT t FROM TRbRetrunMatrixStatus t WHERE t.statusName = :statusName")
    , @NamedQuery(name = "TRbRetrunMatrixStatus.findByStatusDesc", query = "SELECT t FROM TRbRetrunMatrixStatus t WHERE t.statusDesc = :statusDesc")
    , @NamedQuery(name = "TRbRetrunMatrixStatus.findByCreatedDate", query = "SELECT t FROM TRbRetrunMatrixStatus t WHERE t.createdDate = :createdDate")
    , @NamedQuery(name = "TRbRetrunMatrixStatus.findByCreatedBy", query = "SELECT t FROM TRbRetrunMatrixStatus t WHERE t.createdBy = :createdBy")})
public class TRbRetrunMatrixStatus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "status_code")
    private String statusCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "status_name")
    private String statusName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1024)
    @Column(name = "status_desc")
    private String statusDesc;
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
    @OneToMany(mappedBy = "statusCode")
    private Collection<TRbReturnsMatrix> tRbReturnsMatrixCollection;

    public TRbRetrunMatrixStatus() {
    }

    public TRbRetrunMatrixStatus(String statusCode) {
        this.statusCode = statusCode;
    }

    public TRbRetrunMatrixStatus(String statusCode, String statusName, String statusDesc, Date createdDate, String createdBy) {
        this.statusCode = statusCode;
        this.statusName = statusName;
        this.statusDesc = statusDesc;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
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

    @XmlTransient
    public Collection<TRbReturnsMatrix> getTRbReturnsMatrixCollection() {
        return tRbReturnsMatrixCollection;
    }

    public void setTRbReturnsMatrixCollection(Collection<TRbReturnsMatrix> tRbReturnsMatrixCollection) {
        this.tRbReturnsMatrixCollection = tRbReturnsMatrixCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (statusCode != null ? statusCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TRbRetrunMatrixStatus)) {
            return false;
        }
        TRbRetrunMatrixStatus other = (TRbRetrunMatrixStatus) object;
        if ((this.statusCode == null && other.statusCode != null) || (this.statusCode != null && !this.statusCode.equals(other.statusCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bbt.irs.entity.TRbRetrunMatrixStatus[ statusCode=" + statusCode + " ]";
    }
    
}
