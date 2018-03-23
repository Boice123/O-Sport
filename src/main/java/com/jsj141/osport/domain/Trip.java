package com.jsj141.osport.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * @author AutoGenerator
 * 
 */
public class Trip implements Serializable {

    private String tripid;

    private String tripname;

    private String tripdescription;

    private String tripnotice;

    private double tripprice;

    private int maxpeople;

    private String trippublishtime;

    private String tripimg;

    private String shopid;

    private int triptrading;

    private String tripprovice;

    private String tripcity;

    public String getTripid() {
        return tripid;
    }

    public void setTripid(String tripid){
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

    public String getTripimg() {
        return tripimg;
    }

    public void setTripimg(String tripimg){
        this.tripimg = tripimg;
    }

    public String getShopid() {
        return shopid;
    }

    public void setShopid(String shopid){
        this.shopid = shopid;
    }

    public int getTriptrading() {
        return triptrading;
    }

    public void setTriptrading(int triptrading){
        this.triptrading = triptrading;
    }

    public String getTripprovice() {
        return tripprovice;
    }

    public String getTripcity() {
        return tripcity;
    }

    public void setTripprovice(String tripprovice) {
        this.tripprovice = tripprovice;
    }

    public void setTripcity(String tripcity) {
        this.tripcity = tripcity;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}