package com.jsj141.osport.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * @author AutoGenerator
 * 
 */
public class Clubdiary implements Serializable {

    private String clubdiaryid;

    private String clubdiaryimg;

    private String clubdiarytitle;

    private String clubdiarycontent;

    private String clubdiarytime;

    private String userid;

    private String username;

    private String clubid;

    public String getClubdiaryid() {
        return clubdiaryid;
    }

    public void setClubdiaryid(String clubdiaryid){
        this.clubdiaryid = clubdiaryid;
    }

    public String getClubdiaryimg() {
        return clubdiaryimg;
    }

    public void setClubdiaryimg(String clubdiaryimg){
        this.clubdiaryimg = clubdiaryimg;
    }

    public String getClubdiarytitle() {
        return clubdiarytitle;
    }

    public void setClubdiarytitle(String clubdiarytitle){
        this.clubdiarytitle = clubdiarytitle;
    }

    public String getClubdiarycontent() {
        return clubdiarycontent;
    }

    public void setClubdiarycontent(String clubdiarycontent){
        this.clubdiarycontent = clubdiarycontent;
    }

    public String getClubdiarytime() {
        return clubdiarytime;
    }

    public void setClubdiarytime(String clubdiarytime){
        this.clubdiarytime = clubdiarytime;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid){
        this.userid = userid;
    }

    public String getClubid() {
        return clubid;
    }

    public void setClubid(String clubid){
        this.clubid = clubid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}