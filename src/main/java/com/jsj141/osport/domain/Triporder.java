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

    private double tripordertotal;

    private String tripordertime;

    private String userid;

    private String triporderstatus;

    private String tripid;

    private int people;

    private Trip trip;

    private User user;

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip){
        this.trip = trip;
    }

    public String getTriporderid() {
        return triporderid;
    }

    public void setTriporderid(String triporderid){
        this.triporderid = triporderid;
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

    public void setTripordertime(String tripordertime) {
        this.tripordertime = tripordertime;
    }

    public String getTriporderstatus() {
        return triporderstatus;
    }

    public void setTriporderstatus(String triporderstatus) {
        this.triporderstatus = triporderstatus;
    }

    public String getTripid() {
        return tripid;
    }

    public int getPeople() {
        return people;
    }

    public void setTripid(String tripid) {
        this.tripid = tripid;
    }

    public void setPeople(int people) {
        this.people = people;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}