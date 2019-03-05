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
import javax.persistence.CascadeType;
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
@Table(name = "t_rb_restriction_type")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TRbRestrictionType.findAll", query = "SELECT t FROM TRbRestrictionType t")
    , @NamedQuery(name = "TRbRestrictionType.findByRestrictionType", query = "SELECT t FROM TRbRestrictionType t WHERE t.restrictionType = :restrictionType")
    , @NamedQuery(name = "TRbRestrictionType.findByRestrictionTypeDesc", query = "SELECT t FROM TRbRestrictionType t WHERE t.restrictionTypeDesc = :restrictionTypeDesc")
    , @NamedQuery(name = "TRbRestrictionType.findByItemDescription", query = "SELECT t FROM TRbRestrictionType t WHERE t.itemDescription = :itemDescription")
    , @NamedQuery(name = "TRbRestrictionType.findByCreatedDate", query = "SELECT t FROM TRbRestrictionType t WHERE t.createdDate = :createdDate")
    , @NamedQuery(name = "TRbRestrictionType.findByCreatedBy", query = "SELECT t FROM TRbRestrictionType t WHERE t.createdBy = :createdBy")
    , @NamedQuery(name = "TRbRestrictionType.findByLastModified", query = "SELECT t FROM TRbRestrictionType t WHERE t.lastModified = :lastModified")
    , @NamedQuery(name = "TRbRestrictionType.findByModifiedBy", query = "SELECT t FROM TRbRestrictionType t WHERE t.modifiedBy = :modifiedBy")})
public class TRbRestrictionType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "restriction_type")
    private String restrictionType;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "restriction_type_desc")
    private String restrictionTypeDesc;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "item_description")
    private String itemDescription;
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
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "restrictionType")
//    private Collection<TRbRestrictionCodes> tRbRestrictionCodesCollection;

    public TRbRestrictionType() {
    }

    public TRbRestrictionType(String restrictionType) {
        this.restrictionType = restrictionType;
    }

    public TRbRestrictionType(String restrictionType, String restrictionTypeDesc, String itemDescription, Date createdDate, String createdBy) {
        this.restrictionType = restrictionType;
        this.restrictionTypeDesc = restrictionTypeDesc;
        this.itemDescription = itemDescription;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
    }

    public String getRestrictionType() {
        return restrictionType;
    }

    public void setRestrictionType(String restrictionType) {
        this.restrictionType = restrictionType;
    }

    public String getRestrictionTypeDesc() {
        return restrictionTypeDesc;
    }

    public void setRestrictionTypeDesc(String restrictionTypeDesc) {
        this.restrictionTypeDesc = restrictionTypeDesc;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
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

//    @XmlTransient
//    public Collection<TRbRestrictionCodes> getTRbRestrictionCodesCollection() {
//        return tRbRestrictionCodesCollection;
//    }
//
//    public void setTRbRestrictionCodesCollection(Collection<TRbRestrictionCodes> tRbRestrictionCodesCollection) {
//        this.tRbRestrictionCodesCollection = tRbRestrictionCodesCollection;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (restrictionType != null ? restrictionType.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TRbRestrictionType)) {
            return false;
        }
        TRbRestrictionType other = (TRbRestrictionType) object;
        if ((this.restrictionType == null && other.restrictionType != null) || (this.restrictionType != null && !this.restrictionType.equals(other.restrictionType))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bbt.irs.entity.TRbRestrictionType[ restrictionType=" + restrictionType + " ]";
    }
    
}
