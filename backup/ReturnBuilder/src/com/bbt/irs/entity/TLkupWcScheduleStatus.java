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
@Table(name = "t_lkup_wc_schedule_status")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TLkupWcScheduleStatus.findAll", query = "SELECT t FROM TLkupWcScheduleStatus t")
    , @NamedQuery(name = "TLkupWcScheduleStatus.findByStatusId", query = "SELECT t FROM TLkupWcScheduleStatus t WHERE t.statusId = :statusId")
    , @NamedQuery(name = "TLkupWcScheduleStatus.findByStatusName", query = "SELECT t FROM TLkupWcScheduleStatus t WHERE t.statusName = :statusName")
    , @NamedQuery(name = "TLkupWcScheduleStatus.findByStatusDesc", query = "SELECT t FROM TLkupWcScheduleStatus t WHERE t.statusDesc = :statusDesc")
    , @NamedQuery(name = "TLkupWcScheduleStatus.findByCreatedDate", query = "SELECT t FROM TLkupWcScheduleStatus t WHERE t.createdDate = :createdDate")
    , @NamedQuery(name = "TLkupWcScheduleStatus.findByCreatedBy", query = "SELECT t FROM TLkupWcScheduleStatus t WHERE t.createdBy = :createdBy")})
public class TLkupWcScheduleStatus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "status_id")
    private Integer statusId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "status_name")
    private String statusName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1024)
    @Column(name = "status_desc")
    private String statusDesc;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "statusId")
    private Collection<TRtnWorkCollectionSchedule> tRtnWorkCollectionScheduleCollection;

    public TLkupWcScheduleStatus() {
    }

    public TLkupWcScheduleStatus(Integer statusId) {
        this.statusId = statusId;
    }

    public TLkupWcScheduleStatus(Integer statusId, String statusName, String statusDesc, Date createdDate, String createdBy) {
        this.statusId = statusId;
        this.statusName = statusName;
        this.statusDesc = statusDesc;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
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

    @XmlTransient
    public Collection<TRtnWorkCollectionSchedule> getTRtnWorkCollectionScheduleCollection() {
        return tRtnWorkCollectionScheduleCollection;
    }

    public void setTRtnWorkCollectionScheduleCollection(Collection<TRtnWorkCollectionSchedule> tRtnWorkCollectionScheduleCollection) {
        this.tRtnWorkCollectionScheduleCollection = tRtnWorkCollectionScheduleCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (statusId != null ? statusId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TLkupWcScheduleStatus)) {
            return false;
        }
        TLkupWcScheduleStatus other = (TLkupWcScheduleStatus) object;
        if ((this.statusId == null && other.statusId != null) || (this.statusId != null && !this.statusId.equals(other.statusId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bbt.irs.entity.TLkupWcScheduleStatus[ statusId=" + statusId + " ]";
    }
    
}
