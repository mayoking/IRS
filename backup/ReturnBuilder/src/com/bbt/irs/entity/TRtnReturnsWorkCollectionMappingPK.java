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
import javax.validation.constraints.Size;

/**
 *
 * @author tkola
 */
@Embeddable
public class TRtnReturnsWorkCollectionMappingPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "work_collection_id")
    private int workCollectionId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "return_code")
    private String returnCode;

    public TRtnReturnsWorkCollectionMappingPK() {
    }

    public TRtnReturnsWorkCollectionMappingPK(int workCollectionId, String returnCode) {
        this.workCollectionId = workCollectionId;
        this.returnCode = returnCode;
    }

    public int getWorkCollectionId() {
        return workCollectionId;
    }

    public void setWorkCollectionId(int workCollectionId) {
        this.workCollectionId = workCollectionId;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) workCollectionId;
        hash += (returnCode != null ? returnCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TRtnReturnsWorkCollectionMappingPK)) {
            return false;
        }
        TRtnReturnsWorkCollectionMappingPK other = (TRtnReturnsWorkCollectionMappingPK) object;
        if (this.workCollectionId != other.workCollectionId) {
            return false;
        }
        if ((this.returnCode == null && other.returnCode != null) || (this.returnCode != null && !this.returnCode.equals(other.returnCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bbt.irs.entity.TRtnReturnsWorkCollectionMappingPK[ workCollectionId=" + workCollectionId + ", returnCode=" + returnCode + " ]";
    }
    
}
