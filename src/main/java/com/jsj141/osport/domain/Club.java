package com.jsj141.osport.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * @author AutoGenerator
 * 
 */
public class Club implements Serializable {

    private String clubid;

    private String clubname;

    private String clubtab;

    private String clubimg;

    private int clubpeople;

    private String clubowner;

    private String clubpublishtime;

    public String getClubid() {
        return clubid;
    }

    public void setClubid(String clubid){
        this.clubid = clubid;
    }

    public String getClubname() {
        return clubname;
    }

    public void setClubname(String clubname){
        this.clubname = clubname;
    }

    public String getClubtab() {
        return clubtab;
    }

    public void setClubtab(String clubtab){
        this.clubtab = clubtab;
    }

    public String getClubimg() {
        return clubimg;
    }

    public void setClubimg(String clubimg){
        this.clubimg = clubimg;
    }

    public int getClubpeople() {
        return clubpeople;
    }

    public void setClubpeople(int clubpeople){
        this.clubpeople = clubpeople;
    }

    public String getClubowner() {
        return clubowner;
    }

    public void setClubowner(String clubowner){
        this.clubowner = clubowner;
    }

    public String getClubpublishtime() {
        return clubpublishtime;
    }

    public void setClubpublishtime(String clubpublishtime){
        this.clubpublishtime = clubpublishtime;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}