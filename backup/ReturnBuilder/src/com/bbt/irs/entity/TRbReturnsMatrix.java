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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "t_rb_returns_matrix")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TRbReturnsMatrix.findAll", query = "SELECT t FROM TRbReturnsMatrix t")
    , @NamedQuery(name = "TRbReturnsMatrix.findByReturnMatrixItemId", query = "SELECT t FROM TRbReturnsMatrix t WHERE t.returnMatrixItemId = :returnMatrixItemId")
    , @NamedQuery(name = "TRbReturnsMatrix.findByReturnCode", query = "SELECT t FROM TRbReturnsMatrix t WHERE t.returnCode = :returnCode")
    , @NamedQuery(name = "TRbReturnsMatrix.findByDependnecy", query = "SELECT t FROM TRbReturnsMatrix t WHERE t.dependnecy = :dependnecy")
    , @NamedQuery(name = "TRbReturnsMatrix.findByCreatedDate", query = "SELECT t FROM TRbReturnsMatrix t WHERE t.createdDate = :createdDate")
    , @NamedQuery(name = "TRbReturnsMatrix.findByCreatedBy", query = "SELECT t FROM TRbReturnsMatrix t WHERE t.createdBy = :createdBy")
    , @NamedQuery(name = "TRbReturnsMatrix.findByLastModified", query = "SELECT t FROM TRbReturnsMatrix t WHERE t.lastModified = :lastModified")
    , @NamedQuery(name = "TRbReturnsMatrix.findByModifiedBy", query = "SELECT t FROM TRbReturnsMatrix t WHERE t.modifiedBy = :modifiedBy")})
public class TRbReturnsMatrix implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "return_matrix_item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer returnMatrixItemId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "return_code")
    private String returnCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1024)
    @Column(name = "dependnecy")
    private String dependnecy;
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
    @Column(name = "last_modified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModified;
    @Size(max = 255)
    @Column(name = "modified_by")
    private String modifiedBy;
    @JoinColumn(name = "status_code", referencedColumnName = "status_code")
    @ManyToOne
    private TRbRetrunMatrixStatus statusCode;

    public TRbReturnsMatrix() {
    }

    public TRbReturnsMatrix(Integer returnMatrixItemId) {
        this.returnMatrixItemId = returnMatrixItemId;
    }

    public TRbReturnsMatrix(Integer returnMatrixItemId, String returnCode, String dependnecy, Date createdDate, String createdBy) {
        this.returnMatrixItemId = returnMatrixItemId;
        this.returnCode = returnCode;
        this.dependnecy = dependnecy;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
    }

    public Integer getReturnMatrixItemId() {
        return returnMatrixItemId;
    }

    public void setReturnMatrixItemId(Integer returnMatrixItemId) {
        this.returnMatrixItemId = returnMatrixItemId;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getDependnecy() {
        return dependnecy;
    }

    public void setDependnecy(String dependnecy) {
        this.dependnecy = dependnecy;
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

    public TRbRetrunMatrixStatus getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(TRbRetrunMatrixStatus statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (returnMatrixItemId != null ? returnMatrixItemId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TRbReturnsMatrix)) {
            return false;
        }
        TRbReturnsMatrix other = (TRbReturnsMatrix) object;
        if ((this.returnMatrixItemId == null && other.returnMatrixItemId != null) || (this.returnMatrixItemId != null && !this.returnMatrixItemId.equals(other.returnMatrixItemId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bbt.irs.entity.TRbReturnsMatrix[ returnMatrixItemId=" + returnMatrixItemId + " ]";
    }
    
}
