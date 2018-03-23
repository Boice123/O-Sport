package com.jsj141.osport.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * @author AutoGenerator
 * 
 */
public class Triporder implements Serializable {

    private String triporderid;

    private String triporderitemid;

    private double tripordertotal;

    private String tripordertime;

    private String userid;

    private String username;

    private int triporderstatus;

    public String getTriporderid() {
        return triporderid;
    }

    public void setTriporderid(String triporderid){
        this.triporderid = triporderid;
    }

    public String getTriporderitemid() {
        return triporderitemid;
    }

    public void setTriporderitemid(String triporderitemid){
        this.triporderitemid = triporderitemid;
    }

    public double getTripordertotal() {
        return tripordertotal;
    }

    public void setTripordertotal(double tripordertotal){
        this.tripordertotal = tripordertotal;
    }

    public String getTripordertime() {
        return tripordertime;
    }

    public void setTriporderime(String tripordertime){
        this.tripordertime = tripordertime;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid){
        this.userid = userid;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setTripordertime(String tripordertime) {
        this.tripordertime = tripordertime;
    }

    public int getTriporderstatus() {
        return triporderstatus;
    }

    public void setTriporderstatus(int triporderstatus) {
        this.triporderstatus = triporderstatus;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}