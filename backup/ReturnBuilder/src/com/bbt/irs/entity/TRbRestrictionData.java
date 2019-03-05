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
@Table(name = "t_rb_restriction_data")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TRbRestrictionData.findAll", query = "SELECT t FROM TRbRestrictionData t")
    , @NamedQuery(name = "TRbRestrictionData.findByRestrictionId", query = "SELECT t FROM TRbRestrictionData t WHERE t.restrictionId = :restrictionId")
    , @NamedQuery(name = "TRbRestrictionData.findByItemCode", query = "SELECT t FROM TRbRestrictionData t WHERE t.itemCode = :itemCode")
    , @NamedQuery(name = "TRbRestrictionData.findByItemDescription", query = "SELECT t FROM TRbRestrictionData t WHERE t.itemDescription = :itemDescription")
    , @NamedQuery(name = "TRbRestrictionData.findByCreatedDate", query = "SELECT t FROM TRbRestrictionData t WHERE t.createdDate = :createdDate")
    , @NamedQuery(name = "TRbRestrictionData.findByCreatedBy", query = "SELECT t FROM TRbRestrictionData t WHERE t.createdBy = :createdBy")
    , @NamedQuery(name = "TRbRestrictionData.findByLastModified", query = "SELECT t FROM TRbRestrictionData t WHERE t.lastModified = :lastModified")
    , @NamedQuery(name = "TRbRestrictionData.findByModifiedBy", query = "SELECT t FROM TRbRestrictionData t WHERE t.modifiedBy = :modifiedBy")})
public class TRbRestrictionData implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "restriction_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer restrictionId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "item_code")
    private String itemCode;
    @Size(max = 4000)
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
    @JoinColumn(name = "restriction_code", referencedColumnName = "restriction_code")
    @ManyToOne(optional = false)
    private TRbRestrictionCodes restrictionCode;

    public TRbRestrictionData() {
    }

    public TRbRestrictionData(Integer restrictionId) {
        this.restrictionId = restrictionId;
    }

    public TRbRestrictionData(Integer restrictionId, String itemCode, Date createdDate, String createdBy) {
        this.restrictionId = restrictionId;
        this.itemCode = itemCode;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
    }

    public Integer getRestrictionId() {
        return restrictionId;
    }

    public void setRestrictionId(Integer restrictionId) {
        this.restrictionId = restrictionId;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
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

    public TRbRestrictionCodes getRestrictionCode() {
        return restrictionCode;
    }

    public void setRestrictionCode(TRbRestrictionCodes restrictionCode) {
        this.restrictionCode = restrictionCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (restrictionId != null ? restrictionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TRbRestrictionData)) {
            return false;
        }
        TRbRestrictionData other = (TRbRestrictionData) object;
        if ((this.restrictionId == null && other.restrictionId != null) || (this.restrictionId != null && !this.restrictionId.equals(other.restrictionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.itemCode.toUpperCase();
    }
    
}
