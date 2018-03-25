package com.jsj141.osport.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * @author AutoGenerator
 * 
 */
public class Clubactivity implements Serializable {

    private String clubactivityid;

    private String clubactivityimg;

    private String clubactivitytitle;

    private String clubactivitycontent;

    private String clubactivitytime;

    private int clubactivitypeople;

    private String clubid;

    public String getClubactivityid() {
        return clubactivityid;
    }

    public void setClubactivityid(String clubactivityid){
        this.clubactivityid = clubactivityid;
    }

    public String getClubactivityimg() {
        return clubactivityimg;
    }

    public void setClubactivityimg(String clubactivityimg){
        this.clubactivityimg = clubactivityimg;
    }

    public String getClubactivitytitle() {
        return clubactivitytitle;
    }

    public void setClubactivitytitle(String clubactivitytitle){
        this.clubactivitytitle = clubactivitytitle;
    }

    public String getClubactivitycontent() {
        return clubactivitycontent;
    }

    public void setClubactivitycontent(String clubactivitycontent){
        this.clubactivitycontent = clubactivitycontent;
    }

    public String getClubactivitytime() {
        return clubactivitytime;
    }

    public void setClubactivitytime(String clubactivitytime){
        this.clubactivitytime = clubactivitytime;
    }

    public int getClubactivitypeople() {
        return clubactivitypeople;
    }

    public void setClubactivitypeople(int clubactivitypeople){
        this.clubactivitypeople = clubactivitypeople;
    }

    public String getClubid() {
        return clubid;
    }

    public void setClubid(String clubid){
        this.clubid = clubid;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}