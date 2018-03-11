package com.jsj141.osport.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * @author AutoGenerator
 * 
 */
public class Triporder implements Serializable {

    private int triporderid;

    private int triporderitemid;

    private double tripordertotal;

    private String triporderime;

    private int userid;

    public int getTriporderid() {
        return triporderid;
    }

    public void setTriporderid(int triporderid){
        this.triporderid = triporderid;
    }

    public int getTriporderitemid() {
        return triporderitemid;
    }

    public void setTriporderitemid(int triporderitemid){
        this.triporderitemid = triporderitemid;
    }

    public double getTripordertotal() {
        return tripordertotal;
    }

    public void setTripordertotal(double tripordertotal){
        this.tripordertotal = tripordertotal;
    }

    public String getTriporderime() {
        return triporderime;
    }

    public void setTriporderime(String triporderime){
        this.triporderime = triporderime;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid){
        this.userid = userid;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}