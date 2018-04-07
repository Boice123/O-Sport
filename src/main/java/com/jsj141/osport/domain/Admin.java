package com.jsj141.osport.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * @author AutoGenerator
 * 
 */
public class Admin implements Serializable {

    private String adminid;

    private String adminname;

    private String adminpassword;

    private String adminregistertime;

    private String admintel;

    public String getAdminid() {
        return adminid;
    }

    public void setAdminid(String adminid){
        this.adminid = adminid;
    }

    public String getAdminname() {
        return adminname;
    }

    public void setAdminname(String adminname){
        this.adminname = adminname;
    }

    public String getAdminpassword() {
        return adminpassword;
    }

    public void setAdminpassword(String adminpassword){
        this.adminpassword = adminpassword;
    }

    public String getAdminregistertime() {
        return adminregistertime;
    }

    public void setAdminregistertime(String adminregistertime){
        this.adminregistertime = adminregistertime;
    }

    public String getAdmintel() {
        return admintel;
    }

    public void setAdmintel(String admintel){
        this.admintel = admintel;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}