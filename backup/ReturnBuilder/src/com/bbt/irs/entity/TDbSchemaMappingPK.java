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
public class TDbSchemaMappingPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "schema_name")
    private String schemaName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "work_collection_id")
    private int workCollectionId;

    public TDbSchemaMappingPK() {
    }

    public TDbSchemaMappingPK(String schemaName, int workCollectionId) {
        this.schemaName = schemaName;
        this.workCollectionId = workCollectionId;
    }

    public String getSchemaName() {
        return schemaName;
    }

    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }

    public int getWorkCollectionId() {
        return workCollectionId;
    }

    public void setWorkCollectionId(int workCollectionId) {
        this.workCollectionId = workCollectionId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (schemaName != null ? schemaName.hashCode() : 0);
        hash += (int) workCollectionId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TDbSchemaMappingPK)) {
            return false;
        }
        TDbSchemaMappingPK other = (TDbSchemaMappingPK) object;
        if ((this.schemaName == null && other.schemaName != null) || (this.schemaName != null && !this.schemaName.equals(other.schemaName))) {
            return false;
        }
        if (this.workCollectionId != other.workCollectionId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bbt.irs.entity.TDbSchemaMappingPK[ schemaName=" + schemaName + ", workCollectionId=" + workCollectionId + " ]";
    }
    
}
