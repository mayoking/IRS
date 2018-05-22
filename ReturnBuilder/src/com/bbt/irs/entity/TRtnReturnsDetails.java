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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tkola
 */
@Entity
@Table(name = "t_rtn_returns_details")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TRtnReturnsDetails.findAll", query = "SELECT t FROM TRtnReturnsDetails t")
    , @NamedQuery(name = "TRtnReturnsDetails.findByReturnItemId", query = "SELECT t FROM TRtnReturnsDetails t WHERE t.returnItemId = :returnItemId")
    , @NamedQuery(name = "TRtnReturnsDetails.findByReturnCode", query = "SELECT t FROM TRtnReturnsDetails t WHERE t.returnCode = :returnCode")
    , @NamedQuery(name = "TRtnReturnsDetails.findByItemCode", query = "SELECT t FROM TRtnReturnsDetails t WHERE t.itemCode = :itemCode")
    , @NamedQuery(name = "TRtnReturnsDetails.findByStartValidityDate", query = "SELECT t FROM TRtnReturnsDetails t WHERE t.startValidityDate = :startValidityDate")
    , @NamedQuery(name = "TRtnReturnsDetails.findByEndValidityDate", query = "SELECT t FROM TRtnReturnsDetails t WHERE t.endValidityDate = :endValidityDate")
    , @NamedQuery(name = "TRtnReturnsDetails.findByDescription1", query = "SELECT t FROM TRtnReturnsDetails t WHERE t.description1 = :description1")
    , @NamedQuery(name = "TRtnReturnsDetails.findByDescription2", query = "SELECT t FROM TRtnReturnsDetails t WHERE t.description2 = :description2")
    , @NamedQuery(name = "TRtnReturnsDetails.findByDescription3", query = "SELECT t FROM TRtnReturnsDetails t WHERE t.description3 = :description3")
    , @NamedQuery(name = "TRtnReturnsDetails.findByCreatedDate", query = "SELECT t FROM TRtnReturnsDetails t WHERE t.createdDate = :createdDate")
    , @NamedQuery(name = "TRtnReturnsDetails.findByCreatedBy", query = "SELECT t FROM TRtnReturnsDetails t WHERE t.createdBy = :createdBy")
    , @NamedQuery(name = "TRtnReturnsDetails.findByLastModified", query = "SELECT t FROM TRtnReturnsDetails t WHERE t.lastModified = :lastModified")
    , @NamedQuery(name = "TRtnReturnsDetails.findByModifiedBy", query = "SELECT t FROM TRtnReturnsDetails t WHERE t.modifiedBy = :modifiedBy")})
public class TRtnReturnsDetails implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "return_item_id")
    private Integer returnItemId;
    @Basic(optional = false)
    @Column(name = "return_code")
    private String returnCode;
    @Basic(optional = false)
    @Column(name = "item_code")
    private String itemCode;
    @Basic(optional = false)
    @Column(name = "start_validity_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startValidityDate;
    @Basic(optional = false)
    @Column(name = "end_validity_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endValidityDate;
    @Column(name = "description_1")
    private String description1;
    @Column(name = "description_2")
    private String description2;
    @Column(name = "description_3")
    private String description3;
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

    public TRtnReturnsDetails() {
    }

    public TRtnReturnsDetails(Integer returnItemId) {
        this.returnItemId = returnItemId;
    }

    public TRtnReturnsDetails(Integer returnItemId, String returnCode, String itemCode, Date startValidityDate, Date endValidityDate, Date lastModified, String modifiedBy) {
        this.returnItemId = returnItemId;
        this.returnCode = returnCode;
        this.itemCode = itemCode;
        this.startValidityDate = startValidityDate;
        this.endValidityDate = endValidityDate;
        this.lastModified = lastModified;
        this.modifiedBy = modifiedBy;
    }

    public Integer getReturnItemId() {
        return returnItemId;
    }

    public void setReturnItemId(Integer returnItemId) {
        this.returnItemId = returnItemId;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
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

    public String getDescription1() {
        return description1;
    }

    public void setDescription1(String description1) {
        this.description1 = description1;
    }

    public String getDescription2() {
        return description2;
    }

    public void setDescription2(String description2) {
        this.description2 = description2;
    }

    public String getDescription3() {
        return description3;
    }

    public void setDescription3(String description3) {
        this.description3 = description3;
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
        hash += (returnItemId != null ? returnItemId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TRtnReturnsDetails)) {
            return false;
        }
        TRtnReturnsDetails other = (TRtnReturnsDetails) object;
        if ((this.returnItemId == null && other.returnItemId != null) || (this.returnItemId != null && !this.returnItemId.equals(other.returnItemId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bbt.irs.entity.TRtnReturnsDetails[ returnItemId=" + returnItemId + " ]";
    }
    
}
