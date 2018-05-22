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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "t_rtn_work_collection")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TRtnWorkCollection.findAll", query = "SELECT t FROM TRtnWorkCollection t")
    , @NamedQuery(name = "TRtnWorkCollection.findByWorkCollectionId", query = "SELECT t FROM TRtnWorkCollection t WHERE t.workCollectionId = :workCollectionId")
    , @NamedQuery(name = "TRtnWorkCollection.findByEntity", query = "SELECT t FROM TRtnWorkCollection t WHERE t.entity = :entity")
    , @NamedQuery(name = "TRtnWorkCollection.findByStartValidityDate", query = "SELECT t FROM TRtnWorkCollection t WHERE t.startValidityDate = :startValidityDate")
    , @NamedQuery(name = "TRtnWorkCollection.findByEndValidityDate", query = "SELECT t FROM TRtnWorkCollection t WHERE t.endValidityDate = :endValidityDate")
    , @NamedQuery(name = "TRtnWorkCollection.findByWorkCollectionCode", query = "SELECT t FROM TRtnWorkCollection t WHERE t.workCollectionCode = :workCollectionCode")
    , @NamedQuery(name = "TRtnWorkCollection.findByDescription", query = "SELECT t FROM TRtnWorkCollection t WHERE t.description = :description")
    , @NamedQuery(name = "TRtnWorkCollection.findByDeliveryDeadlineDay", query = "SELECT t FROM TRtnWorkCollection t WHERE t.deliveryDeadlineDay = :deliveryDeadlineDay")
    , @NamedQuery(name = "TRtnWorkCollection.findByDeliveryDeadlineHr", query = "SELECT t FROM TRtnWorkCollection t WHERE t.deliveryDeadlineHr = :deliveryDeadlineHr")
    , @NamedQuery(name = "TRtnWorkCollection.findByDeliveryDeadlineMin", query = "SELECT t FROM TRtnWorkCollection t WHERE t.deliveryDeadlineMin = :deliveryDeadlineMin")
    , @NamedQuery(name = "TRtnWorkCollection.findByCreatedDate", query = "SELECT t FROM TRtnWorkCollection t WHERE t.createdDate = :createdDate")
    , @NamedQuery(name = "TRtnWorkCollection.findByCreatedBy", query = "SELECT t FROM TRtnWorkCollection t WHERE t.createdBy = :createdBy")
    , @NamedQuery(name = "TRtnWorkCollection.findByLastModified", query = "SELECT t FROM TRtnWorkCollection t WHERE t.lastModified = :lastModified")
    , @NamedQuery(name = "TRtnWorkCollection.findByModifiedBy", query = "SELECT t FROM TRtnWorkCollection t WHERE t.modifiedBy = :modifiedBy")})
public class TRtnWorkCollection implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "work_collection_id")
    private Integer workCollectionId;
    @Basic(optional = false)
    @Column(name = "entity")
    private String entity;
    @Basic(optional = false)
    @Column(name = "start_validity_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startValidityDate;
    @Basic(optional = false)
    @Column(name = "end_validity_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endValidityDate;
    @Basic(optional = false)
    @Column(name = "work_collection_code")
    private String workCollectionCode;
    @Column(name = "description")
    private String description;
    @Column(name = "delivery_deadline_day")
    private Integer deliveryDeadlineDay;
    @Column(name = "delivery_deadline_hr")
    private Integer deliveryDeadlineHr;
    @Column(name = "delivery_deadline_min")
    private Integer deliveryDeadlineMin;
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
    @JoinColumn(name = "ri_type_id", referencedColumnName = "ri_type_id")
    @ManyToOne(optional = false)
    private TCoreRiType riTypeId;
    @JoinColumn(name = "frequency", referencedColumnName = "freq_unit")
    @ManyToOne(optional = false)
    private TLkupFrequency frequency;

    public TRtnWorkCollection() {
    }

    public TRtnWorkCollection(Integer workCollectionId) {
        this.workCollectionId = workCollectionId;
    }

    public TRtnWorkCollection(Integer workCollectionId, String entity, Date startValidityDate, Date endValidityDate, String workCollectionCode, Date lastModified, String modifiedBy) {
        this.workCollectionId = workCollectionId;
        this.entity = entity;
        this.startValidityDate = startValidityDate;
        this.endValidityDate = endValidityDate;
        this.workCollectionCode = workCollectionCode;
        this.lastModified = lastModified;
        this.modifiedBy = modifiedBy;
    }

    public Integer getWorkCollectionId() {
        return workCollectionId;
    }

    public void setWorkCollectionId(Integer workCollectionId) {
        this.workCollectionId = workCollectionId;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
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

    public String getWorkCollectionCode() {
        return workCollectionCode;
    }

    public void setWorkCollectionCode(String workCollectionCode) {
        this.workCollectionCode = workCollectionCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public TCoreRiType getRiTypeId() {
        return riTypeId;
    }

    public void setRiTypeId(TCoreRiType riTypeId) {
        this.riTypeId = riTypeId;
    }

    public TLkupFrequency getFrequency() {
        return frequency;
    }

    public void setFrequency(TLkupFrequency frequency) {
        this.frequency = frequency;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (workCollectionId != null ? workCollectionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TRtnWorkCollection)) {
            return false;
        }
        TRtnWorkCollection other = (TRtnWorkCollection) object;
        if ((this.workCollectionId == null && other.workCollectionId != null) || (this.workCollectionId != null && !this.workCollectionId.equals(other.workCollectionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.description;
    }
    
}
