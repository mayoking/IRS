/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author tkola
 */
@Entity
@Table(name = "t_calendar")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TCalendar.findAll", query = "SELECT t FROM TCalendar t")
    , @NamedQuery(name = "TCalendar.findByCalendar", query = "SELECT t FROM TCalendar t WHERE t.calendar = :calendar")
    , @NamedQuery(name = "TCalendar.findByDescription", query = "SELECT t FROM TCalendar t WHERE t.description = :description")
    , @NamedQuery(name = "TCalendar.findByCalendarStartDate", query = "SELECT t FROM TCalendar t WHERE t.calendarStartDate = :calendarStartDate")
    , @NamedQuery(name = "TCalendar.findByCalendarEndDate", query = "SELECT t FROM TCalendar t WHERE t.calendarEndDate = :calendarEndDate")
    , @NamedQuery(name = "TCalendar.findByIsValid", query = "SELECT t FROM TCalendar t WHERE t.isValid = :isValid")
    , @NamedQuery(name = "TCalendar.findByIsMondayWorkday", query = "SELECT t FROM TCalendar t WHERE t.isMondayWorkday = :isMondayWorkday")
    , @NamedQuery(name = "TCalendar.findByIsTuesdayWorkday", query = "SELECT t FROM TCalendar t WHERE t.isTuesdayWorkday = :isTuesdayWorkday")
    , @NamedQuery(name = "TCalendar.findByIsWednesdayWorkday", query = "SELECT t FROM TCalendar t WHERE t.isWednesdayWorkday = :isWednesdayWorkday")
    , @NamedQuery(name = "TCalendar.findByIsThursdayWorkday", query = "SELECT t FROM TCalendar t WHERE t.isThursdayWorkday = :isThursdayWorkday")
    , @NamedQuery(name = "TCalendar.findByIsFridayWorkday", query = "SELECT t FROM TCalendar t WHERE t.isFridayWorkday = :isFridayWorkday")
    , @NamedQuery(name = "TCalendar.findByIsSaturdayWorkday", query = "SELECT t FROM TCalendar t WHERE t.isSaturdayWorkday = :isSaturdayWorkday")
    , @NamedQuery(name = "TCalendar.findByIsSundayWorkday", query = "SELECT t FROM TCalendar t WHERE t.isSundayWorkday = :isSundayWorkday")
    , @NamedQuery(name = "TCalendar.findByLastDetailUpdate", query = "SELECT t FROM TCalendar t WHERE t.lastDetailUpdate = :lastDetailUpdate")
    , @NamedQuery(name = "TCalendar.findByLastModified", query = "SELECT t FROM TCalendar t WHERE t.lastModified = :lastModified")
    , @NamedQuery(name = "TCalendar.findByModifiedBy", query = "SELECT t FROM TCalendar t WHERE t.modifiedBy = :modifiedBy")})
public class TCalendar implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "calendar")
    private String calendar;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1024)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "calendar_start_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date calendarStartDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "calendar_end_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date calendarEndDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_valid")
    private boolean isValid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_monday_workday")
    private short isMondayWorkday;
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_tuesday_workday")
    private short isTuesdayWorkday;
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_wednesday_workday")
    private short isWednesdayWorkday;
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_thursday_workday")
    private short isThursdayWorkday;
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_friday_workday")
    private short isFridayWorkday;
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_saturday_workday")
    private short isSaturdayWorkday;
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_sunday_workday")
    private short isSundayWorkday;
    @Basic(optional = false)
    @NotNull
    @Column(name = "last_detail_update")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastDetailUpdate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "last_modified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModified;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "modified_by")
    private String modifiedBy;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "calendar")
    private Collection<TEntity> tEntityCollection;

    public TCalendar() {
    }

    public TCalendar(String calendar) {
        this.calendar = calendar;
    }

    public TCalendar(String calendar, String description, Date calendarStartDate, Date calendarEndDate, boolean isValid, short isMondayWorkday, short isTuesdayWorkday, short isWednesdayWorkday, short isThursdayWorkday, short isFridayWorkday, short isSaturdayWorkday, short isSundayWorkday, Date lastDetailUpdate, Date lastModified, String modifiedBy) {
        this.calendar = calendar;
        this.description = description;
        this.calendarStartDate = calendarStartDate;
        this.calendarEndDate = calendarEndDate;
        this.isValid = isValid;
        this.isMondayWorkday = isMondayWorkday;
        this.isTuesdayWorkday = isTuesdayWorkday;
        this.isWednesdayWorkday = isWednesdayWorkday;
        this.isThursdayWorkday = isThursdayWorkday;
        this.isFridayWorkday = isFridayWorkday;
        this.isSaturdayWorkday = isSaturdayWorkday;
        this.isSundayWorkday = isSundayWorkday;
        this.lastDetailUpdate = lastDetailUpdate;
        this.lastModified = lastModified;
        this.modifiedBy = modifiedBy;
    }

    public String getCalendar() {
        return calendar;
    }

    public void setCalendar(String calendar) {
        this.calendar = calendar;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCalendarStartDate() {
        return calendarStartDate;
    }

    public void setCalendarStartDate(Date calendarStartDate) {
        this.calendarStartDate = calendarStartDate;
    }

    public Date getCalendarEndDate() {
        return calendarEndDate;
    }

    public void setCalendarEndDate(Date calendarEndDate) {
        this.calendarEndDate = calendarEndDate;
    }

    public boolean getIsValid() {
        return isValid;
    }

    public void setIsValid(boolean isValid) {
        this.isValid = isValid;
    }

    public short getIsMondayWorkday() {
        return isMondayWorkday;
    }

    public void setIsMondayWorkday(short isMondayWorkday) {
        this.isMondayWorkday = isMondayWorkday;
    }

    public short getIsTuesdayWorkday() {
        return isTuesdayWorkday;
    }

    public void setIsTuesdayWorkday(short isTuesdayWorkday) {
        this.isTuesdayWorkday = isTuesdayWorkday;
    }

    public short getIsWednesdayWorkday() {
        return isWednesdayWorkday;
    }

    public void setIsWednesdayWorkday(short isWednesdayWorkday) {
        this.isWednesdayWorkday = isWednesdayWorkday;
    }

    public short getIsThursdayWorkday() {
        return isThursdayWorkday;
    }

    public void setIsThursdayWorkday(short isThursdayWorkday) {
        this.isThursdayWorkday = isThursdayWorkday;
    }

    public short getIsFridayWorkday() {
        return isFridayWorkday;
    }

    public void setIsFridayWorkday(short isFridayWorkday) {
        this.isFridayWorkday = isFridayWorkday;
    }

    public short getIsSaturdayWorkday() {
        return isSaturdayWorkday;
    }

    public void setIsSaturdayWorkday(short isSaturdayWorkday) {
        this.isSaturdayWorkday = isSaturdayWorkday;
    }

    public short getIsSundayWorkday() {
        return isSundayWorkday;
    }

    public void setIsSundayWorkday(short isSundayWorkday) {
        this.isSundayWorkday = isSundayWorkday;
    }

    public Date getLastDetailUpdate() {
        return lastDetailUpdate;
    }

    public void setLastDetailUpdate(Date lastDetailUpdate) {
        this.lastDetailUpdate = lastDetailUpdate;
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

    @XmlTransient
    public Collection<TEntity> getTEntityCollection() {
        return tEntityCollection;
    }

    public void setTEntityCollection(Collection<TEntity> tEntityCollection) {
        this.tEntityCollection = tEntityCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (calendar != null ? calendar.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TCalendar)) {
            return false;
        }
        TCalendar other = (TCalendar) object;
        if ((this.calendar == null && other.calendar != null) || (this.calendar != null && !this.calendar.equals(other.calendar))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bbt.irs.entity.TCalendar[ calendar=" + calendar + " ]";
    }
    
}
