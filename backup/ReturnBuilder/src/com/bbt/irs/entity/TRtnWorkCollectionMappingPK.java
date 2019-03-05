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
import javax.validation.constraints.NotNull;

/**
 *
 * @author tkola
 */
@Embeddable
public class TRtnWorkCollectionMappingPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "work_collection_id")
    private int workCollectionId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ri_type_id")
    private int riTypeId;

    public TRtnWorkCollectionMappingPK() {
    }

    public TRtnWorkCollectionMappingPK(int workCollectionId, int riTypeId) {
        this.workCollectionId = workCollectionId;
        this.riTypeId = riTypeId;
    }

    public int getWorkCollectionId() {
        return workCollectionId;
    }

    public void setWorkCollectionId(int workCollectionId) {
        this.workCollectionId = workCollectionId;
    }

    public int getRiTypeId() {
        return riTypeId;
    }

    public void setRiTypeId(int riTypeId) {
        this.riTypeId = riTypeId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) workCollectionId;
        hash += (int) riTypeId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TRtnWorkCollectionMappingPK)) {
            return false;
        }
        TRtnWorkCollectionMappingPK other = (TRtnWorkCollectionMappingPK) object;
        if (this.workCollectionId != other.workCollectionId) {
            return false;
        }
        if (this.riTypeId != other.riTypeId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bbt.irs.entity.TRtnWorkCollectionMappingPK[ workCollectionId=" + workCollectionId + ", riTypeId=" + riTypeId + " ]";
    }
    
}
