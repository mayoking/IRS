/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author opeyemi
 */
@Entity
@Table(name = "datasize")
@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "Datasize.findAll", query = "SELECT d FROM Datasize d")
//    , @NamedQuery(name = "Datasize.findById", query = "SELECT d FROM Datasize d WHERE d.id = :id")
//    , @NamedQuery(name = "Datasize.findByDataSizeDesc", query = "SELECT d FROM Datasize d WHERE d.dataSizeDesc = :dataSizeDesc")
//    , @NamedQuery(name = "Datasize.findByDataSize", query = "SELECT d FROM Datasize d WHERE d.dataSize = :dataSize")})
public class Datasize implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "data_size_desc")
    private String dataSizeDesc;
    @Basic(optional = false)
    @Column(name = "data_size")
    private String dataSize;

    public Datasize() {
    }

    public Datasize(Integer id) {
        this.id = id;
    }

    public Datasize(Integer id, String dataSizeDesc, String dataSize) {
        this.id = id;
        this.dataSizeDesc = dataSizeDesc;
        this.dataSize = dataSize;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDataSizeDesc() {
        return dataSizeDesc;
    }

    public void setDataSizeDesc(String dataSizeDesc) {
        this.dataSizeDesc = dataSizeDesc;
    }

    public String getDataSize() {
        return dataSize;
    }

    public void setDataSize(String dataSize) {
        this.dataSize = dataSize;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Datasize)) {
            return false;
        }
        Datasize other = (Datasize) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return dataSizeDesc;
    }
    
}
