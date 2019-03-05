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
@Table(name = "t_rb_modification_type")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TRbModificationType.findAll", query = "SELECT t FROM TRbModificationType t")
    , @NamedQuery(name = "TRbModificationType.findByModificationType", query = "SELECT t FROM TRbModificationType t WHERE t.modificationType = :modificationType")
    , @NamedQuery(name = "TRbModificationType.findByModificationTypeDesc", query = "SELECT t FROM TRbModificationType t WHERE t.modificationTypeDesc = :modificationTypeDesc")
    , @NamedQuery(name = "TRbModificationType.findByCreatedDate", query = "SELECT t FROM TRbModificationType t WHERE t.createdDate = :createdDate")
    , @NamedQuery(name = "TRbModificationType.findByCreatedBy", query = "SELECT t FROM TRbModificationType t WHERE t.createdBy = :createdBy")
    , @NamedQuery(name = "TRbModificationType.findByLastModified", query = "SELECT t FROM TRbModificationType t WHERE t.lastModified = :lastModified")
    , @NamedQuery(name = "TRbModificationType.findByModifiedBy", query = "SELECT t FROM TRbModificationType t WHERE t.modifiedBy = :modifiedBy")})
public class TRbModificationType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "modification_type")
    private String modificationType;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "modification_type_desc")
    private String modificationTypeDesc;
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

    public TRbModificationType() {
    }

    public TRbModificationType(String modificationType) {
        this.modificationType = modificationType;
    }

    public TRbModificationType(String modificationType, String modificationTypeDesc, Date createdDate, String createdBy) {
        this.modificationType = modificationType;
        this.modificationTypeDesc = modificationTypeDesc;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
    }

    public String getModificationType() {
        return modificationType;
    }

    public void setModificationType(String modificationType) {
        this.modificationType = modificationType;
    }

    public String getModificationTypeDesc() {
        return modificationTypeDesc;
    }

    public void setModificationTypeDesc(String modificationTypeDesc) {
        this.modificationTypeDesc = modificationTypeDesc;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (modificationType != null ? modificationType.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TRbModificationType)) {
            return false;
        }
        TRbModificationType other = (TRbModificationType) object;
        if ((this.modificationType == null && other.modificationType != null) || (this.modificationType != null && !this.modificationType.equals(other.modificationType))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.getModificationType() + " -- "+this.getModificationTypeDesc();
    }
    
}
