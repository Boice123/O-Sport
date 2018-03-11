package com.jsj141.osport.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * @author AutoGenerator
 * 
 */
public class Triporderitem implements Serializable {

    private int triporderitemid;

    private int tripid;

    private int people;

    public int getTriporderitemid() {
        return triporderitemid;
    }

    public void setTriporderitemid(int triporderitemid){
        this.triporderitemid = triporderitemid;
    }

    public int getTripid() {
        return tripid;
    }

    public void setTripid(int tripid){
        this.tripid = tripid;
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