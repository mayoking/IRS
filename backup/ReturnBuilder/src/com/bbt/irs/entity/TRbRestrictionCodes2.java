/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.entity;

import com.bbt.irs.vo.*;
import com.bbt.irs.entity.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "t_rb_restriction_codes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TRbRestrictionCodes.findAll", query = "SELECT t FROM TRbRestrictionCodes t")
    , @NamedQuery(name = "TRbRestrictionCodes.findByRestrictionCode", query = "SELECT t FROM TRbRestrictionCodes t WHERE t.restrictionCode = :restrictionCode")
    , @NamedQuery(name = "TRbRestrictionCodes.findByRestrictionDesc", query = "SELECT t FROM TRbRestrictionCodes t WHERE t.restrictionDesc = :restrictionDesc")
    , @NamedQuery(name = "TRbRestrictionCodes.findByCreatedDate", query = "SELECT t FROM TRbRestrictionCodes t WHERE t.createdDate = :createdDate")
    , @NamedQuery(name = "TRbRestrictionCodes.findByLastModified", query = "SELECT t FROM TRbRestrictionCodes t WHERE t.lastModified = :lastModified")
    , @NamedQuery(name = "TRbRestrictionCodes.findByModifiedBy", query = "SELECT t FROM TRbRestrictionCodes t WHERE t.modifiedBy = :modifiedBy")
    , @NamedQuery(name = "TRbRestrictionCodes.findByValidityDate", query = "SELECT t FROM TRbRestrictionCodes t WHERE t.validityDate = :validityDate")
    , @NamedQuery(name = "TRbRestrictionCodes.findByEndValidityDate", query = "SELECT t FROM TRbRestrictionCodes t WHERE t.endValidityDate = :endValidityDate")})
public class TRbRestrictionCodes2 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "restriction_code")
    private String restrictionCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "restriction_desc")
    private String restrictionDesc;
    @Basic(optional = false)
    @NotNull
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(name = "last_modified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModified;
    @Size(max = 255)
    @Column(name = "modified_by")
    private String modifiedBy;
    @Basic(optional = false)
    @NotNull
    @Column(name = "validity_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date validityDate;
    @Column(name = "end_validity_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endValidityDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "restrictionCode")
    private Collection<TRbRestrictedField> tRbRestrictedFieldCollection;
    @JoinColumn(name = "restriction_type", referencedColumnName = "restriction_type")
    @ManyToOne(optional = false)
    private TRbRestrictionType restrictionType;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "restrictionCode")
    private Collection<TRbRestrictionData> tRbRestrictionDataCollection;

    public TRbRestrictionCodes2() {
    }

    public TRbRestrictionCodes2(String restrictionCode) {
        this.restrictionCode = restrictionCode;
    }

    public TRbRestrictionCodes2(String restrictionCode, String restrictionDesc, Date createdDate, Date validityDate) {
        this.restrictionCode = restrictionCode;
        this.restrictionDesc = restrictionDesc;
        this.createdDate = createdDate;
        this.validityDate = validityDate;
    }

    public String getRestrictionCode() {
        return restrictionCode;
    }

    public void setRestrictionCode(String restrictionCode) {
        this.restrictionCode = restrictionCode;
    }

    public String getRestrictionDesc() {
        return restrictionDesc;
    }

    public void setRestrictionDesc(String restrictionDesc) {
        this.restrictionDesc = restrictionDesc;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
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

    public Date getValidityDate() {
        return validityDate;
    }

    public void setValidityDate(Date validityDate) {
        this.validityDate = validityDate;
    }

    public Date getEndValidityDate() {
        return endValidityDate;
    }

    public void setEndValidityDate(Date endValidityDate) {
        this.endValidityDate = endValidityDate;
    }

    @XmlTransient
    public Collection<TRbRestrictedField> getTRbRestrictedFieldCollection() {
        return tRbRestrictedFieldCollection;
    }

    public void setTRbRestrictedFieldCollection(Collection<TRbRestrictedField> tRbRestrictedFieldCollection) {
        this.tRbRestrictedFieldCollection = tRbRestrictedFieldCollection;
    }

    public TRbRestrictionType getRestrictionType() {
        return restrictionType;
    }

    public void setRestrictionType(TRbRestrictionType restrictionType) {
        this.restrictionType = restrictionType;
    }

    @XmlTransient
    public Collection<TRbRestrictionData> getTRbRestrictionDataCollection() {
        return tRbRestrictionDataCollection;
    }

    public void setTRbRestrictionDataCollection(Collection<TRbRestrictionData> tRbRestrictionDataCollection) {
        this.tRbRestrictionDataCollection = tRbRestrictionDataCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (restrictionCode != null ? restrictionCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TRbRestrictionCodes2)) {
            return false;
        }
        TRbRestrictionCodes2 other = (TRbRestrictionCodes2) object;
        if ((this.restrictionCode == null && other.restrictionCode != null) || (this.restrictionCode != null && !this.restrictionCode.equals(other.restrictionCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bbt.irs.entity.TRbRestrictionCodes[ restrictionCode=" + restrictionCode + " ]";
    }
    
}
