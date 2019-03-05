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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "t_rtn_work_collection_mapping")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TRtnWorkCollectionMapping.findAll", query = "SELECT t FROM TRtnWorkCollectionMapping t")
    , @NamedQuery(name = "TRtnWorkCollectionMapping.findByWorkCollectionId", query = "SELECT t FROM TRtnWorkCollectionMapping t WHERE t.tRtnWorkCollectionMappingPK.workCollectionId = :workCollectionId")
    , @NamedQuery(name = "TRtnWorkCollectionMapping.findByRiTypeId", query = "SELECT t FROM TRtnWorkCollectionMapping t WHERE t.tRtnWorkCollectionMappingPK.riTypeId = :riTypeId")
    , @NamedQuery(name = "TRtnWorkCollectionMapping.findByDeliveryDeadlineDay", query = "SELECT t FROM TRtnWorkCollectionMapping t WHERE t.deliveryDeadlineDay = :deliveryDeadlineDay")
    , @NamedQuery(name = "TRtnWorkCollectionMapping.findByDeliveryDeadlineHr", query = "SELECT t FROM TRtnWorkCollectionMapping t WHERE t.deliveryDeadlineHr = :deliveryDeadlineHr")
    , @NamedQuery(name = "TRtnWorkCollectionMapping.findByDeliveryDeadlineMin", query = "SELECT t FROM TRtnWorkCollectionMapping t WHERE t.deliveryDeadlineMin = :deliveryDeadlineMin")
    , @NamedQuery(name = "TRtnWorkCollectionMapping.findByStartValidityDate", query = "SELECT t FROM TRtnWorkCollectionMapping t WHERE t.startValidityDate = :startValidityDate")
    , @NamedQuery(name = "TRtnWorkCollectionMapping.findByEndValidityDate", query = "SELECT t FROM TRtnWorkCollectionMapping t WHERE t.endValidityDate = :endValidityDate")
    , @NamedQuery(name = "TRtnWorkCollectionMapping.findByCreatedDate", query = "SELECT t FROM TRtnWorkCollectionMapping t WHERE t.createdDate = :createdDate")
    , @NamedQuery(name = "TRtnWorkCollectionMapping.findByCreatedBy", query = "SELECT t FROM TRtnWorkCollectionMapping t WHERE t.createdBy = :createdBy")
    , @NamedQuery(name = "TRtnWorkCollectionMapping.findByLastModified", query = "SELECT t FROM TRtnWorkCollectionMapping t WHERE t.lastModified = :lastModified")
    , @NamedQuery(name = "TRtnWorkCollectionMapping.findByModifiedBy", query = "SELECT t FROM TRtnWorkCollectionMapping t WHERE t.modifiedBy = :modifiedBy")})
public class TRtnWorkCollectionMapping implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TRtnWorkCollectionMappingPK tRtnWorkCollectionMappingPK;
    @Column(name = "delivery_deadline_day")
    private Integer deliveryDeadlineDay;
    @Column(name = "delivery_deadline_hr")
    private Integer deliveryDeadlineHr;
    @Column(name = "delivery_deadline_min")
    private Integer deliveryDeadlineMin;
    @Basic(optional = false)
    @NotNull
    @Column(name = "start_validity_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startValidityDate;
    @Column(name = "end_validity_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endValidityDate;
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
    @JoinColumn(name = "ri_type_id", referencedColumnName = "ri_type_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TCoreRiType tCoreRiType;
    @JoinColumn(name = "work_collection_id", referencedColumnName = "work_collection_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TRtnWorkCollectionDefn tRtnWorkCollectionDefn;

    public TRtnWorkCollectionMapping() {
    }

    public TRtnWorkCollectionMapping(TRtnWorkCollectionMappingPK tRtnWorkCollectionMappingPK) {
        this.tRtnWorkCollectionMappingPK = tRtnWorkCollectionMappingPK;
    }

    public TRtnWorkCollectionMapping(TRtnWorkCollectionMappingPK tRtnWorkCollectionMappingPK, Date startValidityDate, Date createdDate, String createdBy) {
        this.tRtnWorkCollectionMappingPK = tRtnWorkCollectionMappingPK;
        this.startValidityDate = startValidityDate;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
    }

    public TRtnWorkCollectionMapping(int workCollectionId, int riTypeId) {
        this.tRtnWorkCollectionMappingPK = new TRtnWorkCollectionMappingPK(workCollectionId, riTypeId);
    }

    public TRtnWorkCollectionMappingPK getTRtnWorkCollectionMappingPK() {
        return tRtnWorkCollectionMappingPK;
    }

    public void setTRtnWorkCollectionMappingPK(TRtnWorkCollectionMappingPK tRtnWorkCollectionMappingPK) {
        this.tRtnWorkCollectionMappingPK = tRtnWorkCollectionMappingPK;
    }

    public Integer getDeliveryDeadlineDay() {
        return deliveryDeadlineDay;
    }

    public void setDeliveryDeadlineDay(Integer deliveryDeadlineDay) {
        this.deliveryDeadlineDay = deliveryDeadlineDay;
    }

    public Integer getDeliveryDeadlineHr() {
        return deliveryDeadlineHr;
    }

    public void setDeliveryDeadlineHr(Integer deliveryDeadlineHr) {
        this.deliveryDeadlineHr = deliveryDeadlineHr;
    }

    public Integer getDeliveryDeadlineMin() {
        return deliveryDeadlineMin;
    }

    public void setDeliveryDeadlineMin(Integer deliveryDeadlineMin) {
        this.deliveryDeadlineMin = deliveryDeadlineMin;
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

    public TCoreRiType getTCoreRiType() {
        return tCoreRiType;
    }

    public void setTCoreRiType(TCoreRiType tCoreRiType) {
        this.tCoreRiType = tCoreRiType;
    }

    public TRtnWorkCollectionDefn getTRtnWorkCollectionDefn() {
        return tRtnWorkCollectionDefn;
    }

    public void setTRtnWorkCollectionDefn(TRtnWorkCollectionDefn tRtnWorkCollectionDefn) {
        this.tRtnWorkCollectionDefn = tRtnWorkCollectionDefn;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tRtnWorkCollectionMappingPK != null ? tRtnWorkCollectionMappingPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TRtnWorkCollectionMapping)) {
            return false;
        }
        TRtnWorkCollectionMapping other = (TRtnWorkCollectionMapping) object;
        if ((this.tRtnWorkCollectionMappingPK == null && other.tRtnWorkCollectionMappingPK != null) || (this.tRtnWorkCollectionMappingPK != null && !this.tRtnWorkCollectionMappingPK.equals(other.tRtnWorkCollectionMappingPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bbt.irs.entity.TRtnWorkCollectionMapping[ tRtnWorkCollectionMappingPK=" + tRtnWorkCollectionMappingPK + " ]";
    }
    
}
