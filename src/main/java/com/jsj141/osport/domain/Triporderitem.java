package com.jsj141.osport.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * @author AutoGenerator
 * 
 */
public class Triporderitem implements Serializable {

    private String triporderitemid;

    private String tripid;

    private String triptimeid;

    private int people;

    public String getTriporderitemid() {
        return triporderitemid;
    }

    public void setTriporderitemid(String triporderitemid){
        this.triporderitemid = triporderitemid;
    }

    public String getTripid() {
        return tripid;
    }

    public void setTripid(String tripid){
        this.tripid = tripid;
    }

    public String getTriptimeid() {
        return triptimeid;
    }

    public void setTriptimeid(String triptimeid){
        this.triptimeid = triptimeid;
    }

    public int getPeople() {
        return people;
    }

    public void setPeople(int people){
        this.people = people;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}