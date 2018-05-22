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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author opeyemi
 */
@Entity
@Table(name = "t_rb_datasize")
@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "Datasize.findAll", query = "SELECT d FROM Datasize d")
//    , @NamedQuery(name = "Datasize.findById", query = "SELECT d FROM Datasize d WHERE d.id = :id")
//    , @NamedQuery(name = "Datasize.findByDataSizeDesc", query = "SELECT d FROM Datasize d WHERE d.dataSizeDesc = :dataSizeDesc")
//    , @NamedQuery(name = "Datasize.findByDataSize", query = "SELECT d FROM Datasize d WHERE d.dataSize = :dataSize")})
public class Datasize implements Serializable {

   

    @Id
    @Basic(optional = false)
    @Column(name = "datasize_id")
    private Integer datasizeId;
    @Basic(optional = false)
    @Column(name = "datasize")
    private String datasize;
    @Basic(optional = false)
    @Column(name = "datasize_desc")
    private String datasizeDesc;
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
 

    private static final long serialVersionUID = 1L;
    

    public Datasize() {
    }

   


    public Datasize(Integer datasizeId) {
        this.datasizeId = datasizeId;
    }

//    public Datasize(Integer datasizeId, String datasize, String datasizeDesc, Date lastModified, String modifiedBy) {
//        this.datasizeId = datasizeId;
//        this.datasize = datasize;
//        this.datasizeDesc = datasizeDesc;
//        this.lastModified = lastModified;
//        this.modifiedBy = modifiedBy;
//    }

    public Integer getDatasizeId() {
        return datasizeId;
    }

    public void setDatasizeId(Integer datasizeId) {
        this.datasizeId = datasizeId;
    }

    public String getDatasize() {
        return datasize;
    }

    public void setDatasize(String datasize) {
        this.datasize = datasize;
    }

    public String getDatasizeDesc() {
        return datasizeDesc;
    }

    public void setDatasizeDesc(String datasizeDesc) {
        this.datasizeDesc = datasizeDesc;
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

   

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (datasizeId != null ? datasizeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Datasize)) {
            return false;
        }
        Datasize other = (Datasize) object;
        if ((this.datasizeId == null && other.datasizeId != null) || (this.datasizeId != null && !this.datasizeId.equals(other.datasizeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return datasizeDesc;
    }


    
}
