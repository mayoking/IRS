/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table(name = "t_rb_restricted_field")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TRbRestrictedField.findAll", query = "SELECT t FROM TRbRestrictedField t")
    , @NamedQuery(name = "TRbRestrictedField.findByRestrictedFieldId", query = "SELECT t FROM TRbRestrictedField t WHERE t.restrictedFieldId = :restrictedFieldId")
    , @NamedQuery(name = "TRbRestrictedField.findByReturnCode", query = "SELECT t FROM TRbRestrictedField t WHERE t.returnCode = :returnCode")
    , @NamedQuery(name = "TRbRestrictedField.findByReturnField", query = "SELECT t FROM TRbRestrictedField t WHERE t.returnField = :returnField")
    , @NamedQuery(name = "TRbRestrictedField.findByVersionCode", query = "SELECT t FROM TRbRestrictedField t WHERE t.versionCode = :versionCode")
    , @NamedQuery(name = "TRbRestrictedField.findByCreatedDate", query = "SELECT t FROM TRbRestrictedField t WHERE t.createdDate = :createdDate")
    , @NamedQuery(name = "TRbRestrictedField.findByCreatedBy", query = "SELECT t FROM TRbRestrictedField t WHERE t.createdBy = :createdBy")
    , @NamedQuery(name = "TRbRestrictedField.findByLastModified", query = "SELECT t FROM TRbRestrictedField t WHERE t.lastModified = :lastModified")
    , @NamedQuery(name = "TRbRestrictedField.findByModifiedBy", query = "SELECT t FROM TRbRestrictedField t WHERE t.modifiedBy = :modifiedBy")})
public class TRbRestrictedField implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "restricted_field_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer restrictedFieldId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "return_code")
    private String returnCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "return_field")
    private String returnField;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "version_code")
    private String versionCode;
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
    @JoinColumn(name = "restriction_code", referencedColumnName = "restriction_code")
    @ManyToOne(optional = false)
    private TRbRestrictionCodes restrictionCode;

    public TRbRestrictedField() {
    }

    public TRbRestrictedField(Integer restrictedFieldId) {
        this.restrictedFieldId = restrictedFieldId;
    }

    public TRbRestrictedField(Integer restrictedFieldId, String returnCode, String returnField, String versionCode, Date createdDate, String createdBy) {
        this.restrictedFieldId = restrictedFieldId;
        this.returnCode = returnCode;
        this.returnField = returnField;
        this.versionCode = versionCode;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
    }

    public Integer getRestrictedFieldId() {
        return restrictedFieldId;
    }

    public void setRestrictedFieldId(Integer restrictedFieldId) {
        this.restrictedFieldId = restrictedFieldId;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnField() {
        return returnField;
    }

    public void setReturnField(String returnField) {
        this.returnField = returnField;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
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

    public TRbRestrictionCodes getRestrictionCode() {
        return restrictionCode;
    }

    public void setRestrictionCode(TRbRestrictionCodes restrictionCode) {
        this.restrictionCode = restrictionCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (restrictedFieldId != null ? restrictedFieldId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TRbRestrictedField)) {
            return false;
        }
        TRbRestrictedField other = (TRbRestrictedField) object;
        if ((this.restrictedFieldId == null && other.restrictedFieldId != null) || (this.restrictedFieldId != null && !this.restrictedFieldId.equals(other.restrictedFieldId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return returnField ;
    }
    
}
