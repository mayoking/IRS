/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author opeyemi
 */
@Entity
@Table(name = "t_rtn_work_collection_schedule")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TRtnWorkCollectionSchedule.findAll", query = "SELECT t FROM TRtnWorkCollectionSchedule t")
    , @NamedQuery(name = "TRtnWorkCollectionSchedule.findByWorkCollectionId", query = "SELECT t FROM TRtnWorkCollectionSchedule t WHERE t.tRtnWorkCollectionSchedulePK.workCollectionId = :workCollectionId")
    , @NamedQuery(name = "TRtnWorkCollectionSchedule.findByEntity", query = "SELECT t FROM TRtnWorkCollectionSchedule t WHERE t.tRtnWorkCollectionSchedulePK.entity = :entity")
    , @NamedQuery(name = "TRtnWorkCollectionSchedule.findByStartValidityDate", query = "SELECT t FROM TRtnWorkCollectionSchedule t WHERE t.tRtnWorkCollectionSchedulePK.startValidityDate = :startValidityDate")
    , @NamedQuery(name = "TRtnWorkCollectionSchedule.findByEndValidityDate", query = "SELECT t FROM TRtnWorkCollectionSchedule t WHERE t.endValidityDate = :endValidityDate")
    , @NamedQuery(name = "TRtnWorkCollectionSchedule.findByWorkCollectionCode", query = "SELECT t FROM TRtnWorkCollectionSchedule t WHERE t.workCollectionCode = :workCollectionCode")
    , @NamedQuery(name = "TRtnWorkCollectionSchedule.findByDescription", query = "SELECT t FROM TRtnWorkCollectionSchedule t WHERE t.description = :description")
    , @NamedQuery(name = "TRtnWorkCollectionSchedule.findByRiTypeCode", query = "SELECT t FROM TRtnWorkCollectionSchedule t WHERE t.tRtnWorkCollectionSchedulePK.riTypeCode = :riTypeCode")
    , @NamedQuery(name = "TRtnWorkCollectionSchedule.findByFrequency", query = "SELECT t FROM TRtnWorkCollectionSchedule t WHERE t.frequency = :frequency")
    , @NamedQuery(name = "TRtnWorkCollectionSchedule.findByDeliveryDeadlineDay", query = "SELECT t FROM TRtnWorkCollectionSchedule t WHERE t.deliveryDeadlineDay = :deliveryDeadlineDay")
    , @NamedQuery(name = "TRtnWorkCollectionSchedule.findByDeliveryDeadlineHr", query = "SELECT t FROM TRtnWorkCollectionSchedule t WHERE t.deliveryDeadlineHr = :deliveryDeadlineHr")
    , @NamedQuery(name = "TRtnWorkCollectionSchedule.findByDeliveryDeadlineMin", query = "SELECT t FROM TRtnWorkCollectionSchedule t WHERE t.deliveryDeadlineMin = :deliveryDeadlineMin")
    , @NamedQuery(name = "TRtnWorkCollectionSchedule.findByLastModified", query = "SELECT t FROM TRtnWorkCollectionSchedule t WHERE t.lastModified = :lastModified")
    , @NamedQuery(name = "TRtnWorkCollectionSchedule.findByModifiedBy", query = "SELECT t FROM TRtnWorkCollectionSchedule t WHERE t.modifiedBy = :modifiedBy")})
public class TRtnWorkCollectionSchedule implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TRtnWorkCollectionSchedulePK tRtnWorkCollectionSchedulePK;
    @Column(name = "end_validity_date")
    private String endValidityDate;
    @Column(name = "work_collection_code")
    private String workCollectionCode;
    @Column(name = "description")
    private String description;
    @Column(name = "frequency")
    private String frequency;
    @Column(name = "delivery_deadline_day")
    private String deliveryDeadlineDay;
    @Column(name = "delivery_deadline_hr")
    private String deliveryDeadlineHr;
    @Column(name = "delivery_deadline_min")
    private String deliveryDeadlineMin;
    @Column(name = "last_modified")
    private String lastModified;
    @Column(name = "modified_by")
    private String modifiedBy;

    public TRtnWorkCollectionSchedule() {
    }

    public TRtnWorkCollectionSchedule(TRtnWorkCollectionSchedulePK tRtnWorkCollectionSchedulePK) {
        this.tRtnWorkCollectionSchedulePK = tRtnWorkCollectionSchedulePK;
    }

    public TRtnWorkCollectionSchedule(int workCollectionId, String entity, String startValidityDate, String riTypeCode) {
        this.tRtnWorkCollectionSchedulePK = new TRtnWorkCollectionSchedulePK(workCollectionId, entity, startValidityDate, riTypeCode);
    }

    public TRtnWorkCollectionSchedulePK getTRtnWorkCollectionSchedulePK() {
        return tRtnWorkCollectionSchedulePK;
    }

    public void setTRtnWorkCollectionSchedulePK(TRtnWorkCollectionSchedulePK tRtnWorkCollectionSchedulePK) {
        this.tRtnWorkCollectionSchedulePK = tRtnWorkCollectionSchedulePK;
    }

    public String getEndValidityDate() {
        return endValidityDate;
    }

    public void setEndValidityDate(String endValidityDate) {
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

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getDeliveryDeadlineDay() {
        return deliveryDeadlineDay;
    }

    public void setDeliveryDeadlineDay(String deliveryDeadlineDay) {
        this.deliveryDeadlineDay = deliveryDeadlineDay;
    }

    public String getDeliveryDeadlineHr() {
        return deliveryDeadlineHr;
    }

    public void setDeliveryDeadlineHr(String deliveryDeadlineHr) {
        this.deliveryDeadlineHr = deliveryDeadlineHr;
    }

    public String getDeliveryDeadlineMin() {
        return deliveryDeadlineMin;
    }

    public void setDeliveryDeadlineMin(String deliveryDeadlineMin) {
        this.deliveryDeadlineMin = deliveryDeadlineMin;
    }

    public String getLastModified() {
        return lastModified;
    }

    public void setLastModified(String lastModified) {
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
        hash += (tRtnWorkCollectionSchedulePK != null ? tRtnWorkCollectionSchedulePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TRtnWorkCollectionSchedule)) {
            return false;
        }
        TRtnWorkCollectionSchedule other = (TRtnWorkCollectionSchedule) object;
        if ((this.tRtnWorkCollectionSchedulePK == null && other.tRtnWorkCollectionSchedulePK != null) || (this.tRtnWorkCollectionSchedulePK != null && !this.tRtnWorkCollectionSchedulePK.equals(other.tRtnWorkCollectionSchedulePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return description;
    }
    
}
