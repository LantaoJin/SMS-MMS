/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.alanjin.smsmms.frontend;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "member", catalog = "SMS-MMS", schema = "")
@NamedQueries({
    @NamedQuery(name = "Member1.findAll", query = "SELECT m FROM Member1 m"),
    @NamedQuery(name = "Member1.findByKeyId", query = "SELECT m FROM Member1 m WHERE m.keyId = :keyId"),
    @NamedQuery(name = "Member1.findByMemId", query = "SELECT m FROM Member1 m WHERE m.memId = :memId"),
    @NamedQuery(name = "Member1.findByName", query = "SELECT m FROM Member1 m WHERE m.name = :name"),
    @NamedQuery(name = "Member1.findByGender", query = "SELECT m FROM Member1 m WHERE m.gender = :gender"),
    @NamedQuery(name = "Member1.findByBirthday", query = "SELECT m FROM Member1 m WHERE m.birthday = :birthday"),
    @NamedQuery(name = "Member1.findByZip", query = "SELECT m FROM Member1 m WHERE m.zip = :zip"),
    @NamedQuery(name = "Member1.findByAddress", query = "SELECT m FROM Member1 m WHERE m.address = :address"),
    @NamedQuery(name = "Member1.findByTel", query = "SELECT m FROM Member1 m WHERE m.tel = :tel"),
    @NamedQuery(name = "Member1.findByPhone", query = "SELECT m FROM Member1 m WHERE m.phone = :phone"),
    @NamedQuery(name = "Member1.findByEmail", query = "SELECT m FROM Member1 m WHERE m.email = :email"),
    @NamedQuery(name = "Member1.findByEdu", query = "SELECT m FROM Member1 m WHERE m.edu = :edu"),
    @NamedQuery(name = "Member1.findByIndustry", query = "SELECT m FROM Member1 m WHERE m.industry = :industry"),
    @NamedQuery(name = "Member1.findByTitle", query = "SELECT m FROM Member1 m WHERE m.title = :title"),
    @NamedQuery(name = "Member1.findByExpert", query = "SELECT m FROM Member1 m WHERE m.expert = :expert"),
    @NamedQuery(name = "Member1.findByJoindate", query = "SELECT m FROM Member1 m WHERE m.joindate = :joindate"),
    @NamedQuery(name = "Member1.findByLastdate", query = "SELECT m FROM Member1 m WHERE m.lastdate = :lastdate"),
    @NamedQuery(name = "Member1.findByDisable", query = "SELECT m FROM Member1 m WHERE m.disable = :disable"),
    @NamedQuery(name = "Member1.findByFeesum", query = "SELECT m FROM Member1 m WHERE m.feesum = :feesum")})
public class Member1 implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "keyId")
    private Integer keyId;
    @Basic(optional = false)
    @Column(name = "memId")
    private String memId;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "gender")
    private boolean gender;
    @Basic(optional = false)
    @Column(name = "birthday")
    @Temporal(TemporalType.DATE)
    private Date birthday;
    @Column(name = "zip")
    private String zip;
    @Column(name = "address")
    private String address;
    @Column(name = "tel")
    private String tel;
    @Column(name = "phone")
    private String phone;
    @Column(name = "email")
    private String email;
    @Column(name = "edu")
    private String edu;
    @Column(name = "industry")
    private String industry;
    @Column(name = "title")
    private String title;
    @Column(name = "expert")
    private String expert;
    @Basic(optional = false)
    @Column(name = "joindate")
    @Temporal(TemporalType.DATE)
    private Date joindate;
    @Column(name = "lastdate")
    @Temporal(TemporalType.DATE)
    private Date lastdate;
    @Column(name = "disable")
    @Temporal(TemporalType.DATE)
    private Date disable;
    @Basic(optional = false)
    @Column(name = "feesum")
    private long feesum;

    public Member1() {
    }

    public Member1(Integer keyId) {
        this.keyId = keyId;
    }

    public Member1(Integer keyId, String memId, String name, boolean gender, Date birthday, Date joindate, long feesum) {
        this.keyId = keyId;
        this.memId = memId;
        this.name = name;
        this.gender = gender;
        this.birthday = birthday;
        this.joindate = joindate;
        this.feesum = feesum;
    }

    public Integer getKeyId() {
        return keyId;
    }

    public void setKeyId(Integer keyId) {
        this.keyId = keyId;
    }

    public String getMemId() {
        return memId;
    }

    public void setMemId(String memId) {
        this.memId = memId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEdu() {
        return edu;
    }

    public void setEdu(String edu) {
        this.edu = edu;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getExpert() {
        return expert;
    }

    public void setExpert(String expert) {
        this.expert = expert;
    }

    public Date getJoindate() {
        return joindate;
    }

    public void setJoindate(Date joindate) {
        this.joindate = joindate;
    }

    public Date getLastdate() {
        return lastdate;
    }

    public void setLastdate(Date lastdate) {
        this.lastdate = lastdate;
    }

    public Date getDisable() {
        return disable;
    }

    public void setDisable(Date disable) {
        this.disable = disable;
    }

    public long getFeesum() {
        return feesum;
    }

    public void setFeesum(long feesum) {
        this.feesum = feesum;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (keyId != null ? keyId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Member1)) {
            return false;
        }
        Member1 other = (Member1) object;
        if ((this.keyId == null && other.keyId != null) || (this.keyId != null && !this.keyId.equals(other.keyId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.alanjin.smsmms.frontend.Member1[ keyId=" + keyId + " ]";
    }
    
}
