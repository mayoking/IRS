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
@Table(name = "t_sct_db_changes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TSctDbChanges.findAll", query = "SELECT t FROM TSctDbChanges t")
    , @NamedQuery(name = "TSctDbChanges.findByChangeId", query = "SELECT t FROM TSctDbChanges t WHERE t.changeId = :changeId")
    , @NamedQuery(name = "TSctDbChanges.findByChangeCode", query = "SELECT t FROM TSctDbChanges t WHERE t.changeCode = :changeCode")
    , @NamedQuery(name = "TSctDbChanges.findByChangeDate", query = "SELECT t FROM TSctDbChanges t WHERE t.changeDate = :changeDate")
    , @NamedQuery(name = "TSctDbChanges.findByChangeBy", query = "SELECT t FROM TSctDbChanges t WHERE t.changeBy = :changeBy")
    , @NamedQuery(name = "TSctDbChanges.findByCreatedDate", query = "SELECT t FROM TSctDbChanges t WHERE t.createdDate = :createdDate")
    , @NamedQuery(name = "TSctDbChanges.findByCreatedBy", query = "SELECT t FROM TSctDbChanges t WHERE t.createdBy = :createdBy")
    , @NamedQuery(name = "TSctDbChanges.findByLastModified", query = "SELECT t FROM TSctDbChanges t WHERE t.lastModified = :lastModified")
    , @NamedQuery(name = "TSctDbChanges.findByModifiedBy", query = "SELECT t FROM TSctDbChanges t WHERE t.modifiedBy = :modifiedBy")
    , @NamedQuery(name = "TSctDbChanges.findByValidityDate", query = "SELECT t FROM TSctDbChanges t WHERE t.validityDate = :validityDate")
    , @NamedQuery(name = "TSctDbChanges.findByWorkCollCode", query = "SELECT t FROM TSctDbChanges t WHERE t.workCollCode = :workCollCode")})
public class TSctDbChanges implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "change_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer changeId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "change_code")
    private String changeCode;
    @Basic(optional = false)
    @NotNull
    @Column(name = "change_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date changeDate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "change_by")
    private String changeBy;
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
    @Column(name = "validity_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date validityDate;
    @Size(max = 40)
    @Column(name = "work_coll_code")
    private String workCollCode;
    @JoinColumn(name = "change_code_type", referencedColumnName = "type_id")
    @ManyToOne(optional = false)
    private TSctDbChangesCodeType changeCodeType;
    @JoinColumn(name = "status", referencedColumnName = "status_id")
    @ManyToOne(optional = false)
    private TSctDbChangesStatus status;
    @JoinColumn(name = "type_changes", referencedColumnName = "type_id")
    @ManyToOne(optional = false)
    private TSctDbChangesType typeChanges;

    public TSctDbChanges() {
    }

    public TSctDbChanges(Integer changeId) {
        this.changeId = changeId;
    }

    public TSctDbChanges(Integer changeId, String changeCode, Date changeDate, String changeBy, Date createdDate, String createdBy) {
        this.changeId = changeId;
        this.changeCode = changeCode;
        this.changeDate = changeDate;
        this.changeBy = changeBy;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
    }

    public Integer getChangeId() {
        return changeId;
    }

    public void setChangeId(Integer changeId) {
        this.changeId = changeId;
    }

    public String getChangeCode() {
        return changeCode;
    }

    public void setChangeCode(String changeCode) {
        this.changeCode = changeCode;
    }

    public Date getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(Date changeDate) {
        this.changeDate = changeDate;
    }

    public String getChangeBy() {
        return changeBy;
    }

    public void setChangeBy(String changeBy) {
        this.changeBy = changeBy;
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

    public Date getValidityDate() {
        return validityDate;
    }

    public void setValidityDate(Date validityDate) {
        this.validityDate = validityDate;
    }

    public String getWorkCollCode() {
        return workCollCode;
    }

    public void setWorkCollCode(String workCollCode) {
        this.workCollCode = workCollCode;
    }

    public TSctDbChangesCodeType getChangeCodeType() {
        return changeCodeType;
    }

    public void setChangeCodeType(TSctDbChangesCodeType changeCodeType) {
        this.changeCodeType = changeCodeType;
    }

    public TSctDbChangesStatus getStatus() {
        return status;
    }

    public void setStatus(TSctDbChangesStatus status) {
        this.status = status;
    }

    public TSctDbChangesType getTypeChanges() {
        return typeChanges;
    }

    public void setTypeChanges(TSctDbChangesType typeChanges) {
        this.typeChanges = typeChanges;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (changeId != null ? changeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TSctDbChanges)) {
            return false;
        }
        TSctDbChanges other = (TSctDbChanges) object;
        if ((this.changeId == null && other.changeId != null) || (this.changeId != null && !this.changeId.equals(other.changeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bbt.irs.entity.TSctDbChanges[ changeId=" + changeId + " ]";
    }

}
