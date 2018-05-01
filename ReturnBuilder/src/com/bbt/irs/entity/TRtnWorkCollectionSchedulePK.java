/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author opeyemi
 */
@Embeddable
public class TRtnWorkCollectionSchedulePK implements Serializable {

    @Basic(optional = false)
    @Column(name = "work_collection_id")
    private int workCollectionId;
    @Basic(optional = false)
    @Column(name = "entity")
    private String entity;
    @Basic(optional = false)
    @Column(name = "start_validity_date")
    private String startValidityDate;
    @Basic(optional = false)
    @Column(name = "ri_type_code")
    private String riTypeCode;

    public TRtnWorkCollectionSchedulePK() {
    }

    public TRtnWorkCollectionSchedulePK(int workCollectionId, String entity, String startValidityDate, String riTypeCode) {
        this.workCollectionId = workCollectionId;
        this.entity = entity;
        this.startValidityDate = startValidityDate;
        this.riTypeCode = riTypeCode;
    }

    public int getWorkCollectionId() {
        return workCollectionId;
    }

    public void setWorkCollectionId(int workCollectionId) {
        this.workCollectionId = workCollectionId;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public String getStartValidityDate() {
        return startValidityDate;
    }

    public void setStartValidityDate(String startValidityDate) {
        this.startValidityDate = startValidityDate;
    }

    public String getRiTypeCode() {
        return riTypeCode;
    }

    public void setRiTypeCode(String riTypeCode) {
        this.riTypeCode = riTypeCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) workCollectionId;
        hash += (entity != null ? entity.hashCode() : 0);
        hash += (startValidityDate != null ? startValidityDate.hashCode() : 0);
        hash += (riTypeCode != null ? riTypeCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TRtnWorkCollectionSchedulePK)) {
            return false;
        }
        TRtnWorkCollectionSchedulePK other = (TRtnWorkCollectionSchedulePK) object;
        if (this.workCollectionId != other.workCollectionId) {
            return false;
        }
        if ((this.entity == null && other.entity != null) || (this.entity != null && !this.entity.equals(other.entity))) {
            return false;
        }
        if ((this.startValidityDate == null && other.startValidityDate != null) || (this.startValidityDate != null && !this.startValidityDate.equals(other.startValidityDate))) {
            return false;
        }
        if ((this.riTypeCode == null && other.riTypeCode != null) || (this.riTypeCode != null && !this.riTypeCode.equals(other.riTypeCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bbt.irs.entity.TRtnWorkCollectionSchedulePK[ workCollectionId=" + workCollectionId + ", entity=" + entity + ", startValidityDate=" + startValidityDate + ", riTypeCode=" + riTypeCode + " ]";
    }
    
}
