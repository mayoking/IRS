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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tkola
 */
@Entity
@Table(name = "t_rtn_work_collection_schedule")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TRtnWorkCollectionSchedule.findAll", query = "SELECT t FROM TRtnWorkCollectionSchedule t")
    , @NamedQuery(name = "TRtnWorkCollectionSchedule.findByScheduleId", query = "SELECT t FROM TRtnWorkCollectionSchedule t WHERE t.scheduleId = :scheduleId")
    , @NamedQuery(name = "TRtnWorkCollectionSchedule.findByWorkCollectionDate", query = "SELECT t FROM TRtnWorkCollectionSchedule t WHERE t.workCollectionDate = :workCollectionDate")
    , @NamedQuery(name = "TRtnWorkCollectionSchedule.findByScheduleDeadline", query = "SELECT t FROM TRtnWorkCollectionSchedule t WHERE t.scheduleDeadline = :scheduleDeadline")
    , @NamedQuery(name = "TRtnWorkCollectionSchedule.findByWorkflowId", query = "SELECT t FROM TRtnWorkCollectionSchedule t WHERE t.workflowId = :workflowId")
    , @NamedQuery(name = "TRtnWorkCollectionSchedule.findByResubmitReq", query = "SELECT t FROM TRtnWorkCollectionSchedule t WHERE t.resubmitReq = :resubmitReq")
    , @NamedQuery(name = "TRtnWorkCollectionSchedule.findByResubmitCount", query = "SELECT t FROM TRtnWorkCollectionSchedule t WHERE t.resubmitCount = :resubmitCount")
    , @NamedQuery(name = "TRtnWorkCollectionSchedule.findByValidationCount", query = "SELECT t FROM TRtnWorkCollectionSchedule t WHERE t.validationCount = :validationCount")
    , @NamedQuery(name = "TRtnWorkCollectionSchedule.findByIsValid", query = "SELECT t FROM TRtnWorkCollectionSchedule t WHERE t.isValid = :isValid")
    , @NamedQuery(name = "TRtnWorkCollectionSchedule.findByCreatedDate", query = "SELECT t FROM TRtnWorkCollectionSchedule t WHERE t.createdDate = :createdDate")
    , @NamedQuery(name = "TRtnWorkCollectionSchedule.findByCreatedBy", query = "SELECT t FROM TRtnWorkCollectionSchedule t WHERE t.createdBy = :createdBy")
    , @NamedQuery(name = "TRtnWorkCollectionSchedule.findByLastModified", query = "SELECT t FROM TRtnWorkCollectionSchedule t WHERE t.lastModified = :lastModified")
    , @NamedQuery(name = "TRtnWorkCollectionSchedule.findByModifiedBy", query = "SELECT t FROM TRtnWorkCollectionSchedule t WHERE t.modifiedBy = :modifiedBy")})
public class TRtnWorkCollectionSchedule implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "schedule_id")
    private Long scheduleId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "work_collection_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date workCollectionDate;
    @Column(name = "schedule_deadline")
    @Temporal(TemporalType.TIMESTAMP)
    private Date scheduleDeadline;
    @Column(name = "workflow_id")
    private Integer workflowId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "resubmit_req")
    private boolean resubmitReq;
    @Basic(optional = false)
    @NotNull
    @Column(name = "resubmit_count")
    private int resubmitCount;
    @Basic(optional = false)
    @NotNull
    @Column(name = "validation_count")
    private int validationCount;
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_valid")
    private boolean isValid;
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
    @JoinColumn(name = "ri_id", referencedColumnName = "ri_id")
    @ManyToOne(optional = false)
    private TCoreReportingInstitution riId;
    @JoinColumn(name = "status_id", referencedColumnName = "status_id")
    @ManyToOne(optional = false)
    private TLkupWcScheduleStatus statusId;
    @JoinColumn(name = "work_collection_id", referencedColumnName = "work_collection_id")
    @ManyToOne(optional = false)
    private TRtnWorkCollectionDefn workCollectionId;

    public TRtnWorkCollectionSchedule() {
    }

    public TRtnWorkCollectionSchedule(Long scheduleId) {
        this.scheduleId = scheduleId;
    }

    public TRtnWorkCollectionSchedule(Long scheduleId, Date workCollectionDate, boolean resubmitReq, int resubmitCount, int validationCount, boolean isValid, Date createdDate, String createdBy) {
        this.scheduleId = scheduleId;
        this.workCollectionDate = workCollectionDate;
        this.resubmitReq = resubmitReq;
        this.resubmitCount = resubmitCount;
        this.validationCount = validationCount;
        this.isValid = isValid;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
    }

    public Long getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Long scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Date getWorkCollectionDate() {
        return workCollectionDate;
    }

    public void setWorkCollectionDate(Date workCollectionDate) {
        this.workCollectionDate = workCollectionDate;
    }

    public Date getScheduleDeadline() {
        return scheduleDeadline;
    }

    public void setScheduleDeadline(Date scheduleDeadline) {
        this.scheduleDeadline = scheduleDeadline;
    }

    public Integer getWorkflowId() {
        return workflowId;
    }

    public void setWorkflowId(Integer workflowId) {
        this.workflowId = workflowId;
    }

    public boolean getResubmitReq() {
        return resubmitReq;
    }

    public void setResubmitReq(boolean resubmitReq) {
        this.resubmitReq = resubmitReq;
    }

    public int getResubmitCount() {
        return resubmitCount;
    }

    public void setResubmitCount(int resubmitCount) {
        this.resubmitCount = resubmitCount;
    }

    public int getValidationCount() {
        return validationCount;
    }

    public void setValidationCount(int validationCount) {
        this.validationCount = validationCount;
    }

    public boolean getIsValid() {
        return isValid;
    }

    public void setIsValid(boolean isValid) {
        this.isValid = isValid;
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

    public TCoreReportingInstitution getRiId() {
        return riId;
    }

    public void setRiId(TCoreReportingInstitution riId) {
        this.riId = riId;
    }

    public TLkupWcScheduleStatus getStatusId() {
        return statusId;
    }

    public void setStatusId(TLkupWcScheduleStatus statusId) {
        this.statusId = statusId;
    }

    public TRtnWorkCollectionDefn getWorkCollectionId() {
        return workCollectionId;
    }

    public void setWorkCollectionId(TRtnWorkCollectionDefn workCollectionId) {
        this.workCollectionId = workCollectionId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (scheduleId != null ? scheduleId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TRtnWorkCollectionSchedule)) {
            return false;
        }
        TRtnWorkCollectionSchedule other = (TRtnWorkCollectionSchedule) object;
        if ((this.scheduleId == null && other.scheduleId != null) || (this.scheduleId != null && !this.scheduleId.equals(other.scheduleId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bbt.irs.entity.TRtnWorkCollectionSchedule[ scheduleId=" + scheduleId + " ]";
    }
    
}
