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
@Table(name = "t_rb_datatype")
@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "Datatype.findAll", query = "SELECT d FROM Datatype d")
//    , @NamedQuery(name = "Datatype.findById", query = "SELECT d FROM Datatype d WHERE d.id = :id")
//    , @NamedQuery(name = "Datatype.findByDatatypeDesc", query = "SELECT d FROM Datatype d WHERE d.datatypeDesc = :datatypeDesc")
//    , @NamedQuery(name = "Datatype.findByDatatype", query = "SELECT d FROM Datatype d WHERE d.datatype = :datatype")})
public class Datatype implements Serializable {



    @Id
    @Basic(optional = false)
    @Column(name = "datatype_id")
    private Integer datatypeId;
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

    @Basic(optional = false)
    @Column(name = "datatype_desc")
    private String datatypeDesc;
    @Basic(optional = false)
    @Column(name = "datatype")
    private String datatype;

    public Datatype() {
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
    public String toString() {
        return datatypeDesc;
    }


    public Datatype(Integer datatypeId, Date lastModified, String modifiedBy) {
        this.datatypeId = datatypeId;
        this.lastModified = lastModified;
        this.modifiedBy = modifiedBy;
    }

    public Integer getDatatypeId() {
        return datatypeId;
    }

    public void setDatatypeId(Integer datatypeId) {
        this.datatypeId = datatypeId;
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

    

   

    
    
}
