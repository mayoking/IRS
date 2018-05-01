/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author admin
 */
@Entity
@Table(name = "user_access")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserAccess.findAll", query = "SELECT u FROM UserAccess u"),
    @NamedQuery(name = "UserAccess.findByUserid", query = "SELECT u FROM UserAccess u WHERE u.userid = :userid"),
    @NamedQuery(name = "UserAccess.findByPassword", query = "SELECT u FROM UserAccess u WHERE u.password = :password"),
    @NamedQuery(name = "UserAccess.findByRoleId", query = "SELECT u FROM UserAccess u WHERE u.roleId = :roleId"),
    @NamedQuery(name = "UserAccess.findByGeoId", query = "SELECT u FROM UserAccess u WHERE u.geoId = :geoId"),
    @NamedQuery(name = "UserAccess.findByLocationId", query = "SELECT u FROM UserAccess u WHERE u.locationId = :locationId"),
    @NamedQuery(name = "UserAccess.findByDescription", query = "SELECT u FROM UserAccess u WHERE u.description = :description"),
    @NamedQuery(name = "UserAccess.findByTransactionDate", query = "SELECT u FROM UserAccess u WHERE u.transactionDate = :transactionDate"),
    @NamedQuery(name = "UserAccess.findByAddedBy", query = "SELECT u FROM UserAccess u WHERE u.addedBy = :addedBy"),
    @NamedQuery(name = "UserAccess.findByModifiedDate", query = "SELECT u FROM UserAccess u WHERE u.modifiedDate = :modifiedDate"),
    @NamedQuery(name = "UserAccess.findByModifiedBy", query = "SELECT u FROM UserAccess u WHERE u.modifiedBy = :modifiedBy"),
    @NamedQuery(name = "UserAccess.findByLga", query = "SELECT u FROM UserAccess u WHERE u.lga = :lga"),
    @NamedQuery(name = "UserAccess.findByFirstname", query = "SELECT u FROM UserAccess u WHERE u.firstname = :firstname"),
    @NamedQuery(name = "UserAccess.findByLastname", query = "SELECT u FROM UserAccess u WHERE u.lastname = :lastname"),
    @NamedQuery(name = "UserAccess.findByCountrycode", query = "SELECT u FROM UserAccess u WHERE u.countrycode = :countrycode"),
    @NamedQuery(name = "UserAccess.findByStatecode", query = "SELECT u FROM UserAccess u WHERE u.statecode = :statecode")})
public class UserAccess implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "USERID")
    private String userid;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "ROLE_ID")
    private String roleId;
    @Column(name = "GEO_ID")
    private Integer geoId;
    @Column(name = "LOCATION_ID")
    private String locationId;
    @Column(name = "DESCRIPTION")
    private String description;
    @Basic(optional = false)
    @Column(name = "TRANSACTION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date transactionDate;
    @Column(name = "ADDED_BY")
    private String addedBy;
    @Column(name = "MODIFIED_DATE")
    @Temporal(TemporalType.DATE)
    private Date modifiedDate;
    @Column(name = "MODIFIED_BY")
    private String modifiedBy;
    @Column(name = "LGA")
    private Integer lga;
    @Column(name = "FIRSTNAME")
    private String firstname;
    @Column(name = "LASTNAME")
    private String lastname;
    @Column(name = "COUNTRYCODE")
    private String countrycode;
    @Column(name = "STATECODE")
    private String statecode;
    private String email;
    private String phonenumber;
    private String access;
    public UserAccess() {
    }

    public UserAccess(String userid) {
        this.userid = userid;
    }

    public UserAccess(String userid, Date transactionDate) {
        this.userid = userid;
        this.transactionDate = transactionDate;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public Integer getGeoId() {
        return geoId;
    }

    public void setGeoId(Integer geoId) {
        this.geoId = geoId;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(String addedBy) {
        this.addedBy = addedBy;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Integer getLga() {
        return lga;
    }

    public void setLga(Integer lga) {
        this.lga = lga;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getCountrycode() {
        return countrycode;
    }

    public void setCountrycode(String countrycode) {
        this.countrycode = countrycode;
    }

    public String getStatecode() {
        return statecode;
    }

    public void setStatecode(String statecode) {
        this.statecode = statecode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userid != null ? userid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserAccess)) {
            return false;
        }
        UserAccess other = (UserAccess) object;
        if ((this.userid == null && other.userid != null) || (this.userid != null && !this.userid.equals(other.userid))) {
            return false;
        }
        return true;
    }

    

  //  private static final Map<String, User> USERS = new HashMap<String, User>();

    @Override
    public String toString() {
        return "UserAccess{" + "userid=" + userid + ", password=" + password + ", roleId=" + roleId + ", geoId=" + geoId + ", locationId=" + locationId + ", description=" + description + ", transactionDate=" + transactionDate + ", addedBy=" + addedBy + ", modifiedDate=" + modifiedDate + ", modifiedBy=" + modifiedBy + ", lga=" + lga + ", firstname=" + firstname + ", lastname=" + lastname + ", countrycode=" + countrycode + ", statecode=" + statecode + ", email=" + email + ", phonenumber=" + phonenumber + ", access=" + access + '}';
    }
    
    

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the phonenumber
     */
    public String getPhonenumber() {
        return phonenumber;
    }

    /**
     * @param phonenumber the phonenumber to set
     */
    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    /**
     * @return the access
     */
    public String getAccess() {
        return access;
    }

    /**
     * @param access the access to set
     */
    public void setAccess(String access) {
        this.access = access;
    }
    
}
