package com.jsj141.osport.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * @author AutoGenerator
 * 
 */
public class Clubuseritem implements Serializable {

    private String clubuseritemid;

    private String clubid;

    private String userid;

    public String getClubuseritemid() {
        return clubuseritemid;
    }

    public void setClubuseritemid(String clubuseritemid){
        this.clubuseritemid = clubuseritemid;
    }

    public String getClubid() {
        return clubid;
    }

    public void setClubid(String clubid){
        this.clubid = clubid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid){
        this.userid = userid;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}