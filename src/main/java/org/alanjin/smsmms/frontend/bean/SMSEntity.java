/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.alanjin.smsmms.frontend.bean;

/**
 *
 * @author Alan Jin
 */
public class SMSEntity {
    private String memId;
    private String name;
    private String sexString;
    private String phone;
    
    public SMSEntity(String memId, String name, String sexString, String phone) {
        this.memId = memId;
        this.name = name;
        this.sexString = sexString;
        this.phone = phone;
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

    public String getSexString() {
        return sexString;
    }

    public void setSexString(String sexString) {
        this.sexString = sexString;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + (this.memId != null ? this.memId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SMSEntity other = (SMSEntity) obj;
        if ((this.memId == null) ? (other.memId != null) : !this.memId.equals(other.memId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SMSEntity{" + "memId=" + memId + ", name=" + name + ", phone=" + phone + '}';
    }
    
}
