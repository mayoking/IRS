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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author opeyemi
 */
@Entity
@Table(name = "datatype")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Datatype.findAll", query = "SELECT d FROM Datatype d")
    , @NamedQuery(name = "Datatype.findById", query = "SELECT d FROM Datatype d WHERE d.id = :id")
    , @NamedQuery(name = "Datatype.findByDatatypeDesc", query = "SELECT d FROM Datatype d WHERE d.datatypeDesc = :datatypeDesc")
    , @NamedQuery(name = "Datatype.findByDatatype", query = "SELECT d FROM Datatype d WHERE d.datatype = :datatype")})
public class Datatype implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "datatype_desc")
    private String datatypeDesc;
    @Basic(optional = false)
    @Column(name = "datatype")
    private String datatype;

    public Datatype() {
    }

    public Datatype(Integer id) {
        this.id = id;
    }

    public Datatype(Integer id, String datatypeDesc, String datatype) {
        this.id = id;
        this.datatypeDesc = datatypeDesc;
        this.datatype = datatype;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDatatypeDesc() {
        return datatypeDesc;
    }

    public void setDatatypeDesc(String datatypeDesc) {
        this.datatypeDesc = datatypeDesc;
    }

    public String getDatatype() {
        return datatype;
    }

    public void setDatatype(String datatype) {
        this.datatype = datatype;
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
        if (!(object instanceof Datatype)) {
            return false;
        }
        Datatype other = (Datatype) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return datatypeDesc;
    }
    
}
