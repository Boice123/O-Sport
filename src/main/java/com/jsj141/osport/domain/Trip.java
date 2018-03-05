package com.jsj141.osport.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * @author AutoGenerator
 * 
 */
public class Trip implements Serializable {

    private int tripid;

    private String tripname;

    private String tripdescription;

    private String tripnotice;

    private double tripprice;

    private int maxpeople;

    private String trippublishtime;

    private String tripimgid;

    private int shopid;

    public int getTripid() {
        return tripid;
    }

    public void setTripid(int tripid){
        this.tripid = tripid;
    }

    public String getTripname() {
        return tripname;
    }

    public void setTripname(String tripname){
        this.tripname = tripname;
    }

    public String getTripdescription() {
        return tripdescription;
    }

    public void setTripdescription(String tripdescription){
        this.tripdescription = tripdescription;
    }

    public String getTripnotice() {
        return tripnotice;
    }

    public void setTripnotice(String tripnotice){
        this.tripnotice = tripnotice;
    }

    public double getTripprice() {
        return tripprice;
    }

    public void setTripprice(double tripprice){
        this.tripprice = tripprice;
    }

    public int getMaxpeople() {
        return maxpeople;
    }

    public void setMaxpeople(int maxpeople){
        this.maxpeople = maxpeople;
    }

    public String getTrippublishtime() {
        return trippublishtime;
    }

    public void setTrippublishtime(String trippublishtime){
        this.trippublishtime = trippublishtime;
    }

    public String getTripimgid() {
        return tripimgid;
    }

    public void setTripimgid(String tripimgid){
        this.tripimgid = tripimgid;
    }

    public int getShopid() {
        return shopid;
    }

    public void setShopid(int shopid){
        this.shopid = shopid;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}