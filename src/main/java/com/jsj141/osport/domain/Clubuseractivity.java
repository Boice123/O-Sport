package com.jsj141.osport.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * @author AutoGenerator
 * 
 */
public class Clubuseractivity implements Serializable {

    private String clubuseractivityid;

    private String clubactivityid;

    private String userid;

    public String getClubuseractivityid() {
        return clubuseractivityid;
    }

    public void setClubuseractivityid(String clubuseractivityid){
        this.clubuseractivityid = clubuseractivityid;
    }

    public String getClubactivityid() {
        return clubactivityid;
    }

    public void setClubactivityid(String clubactivityid){
        this.clubactivityid = clubactivityid;
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