package com.jsj141.osport.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * @author AutoGenerator
 * 
 */
public class Train implements Serializable {

    private String trainid;

    private String trainname;

    private String traindescription;

    private String trainnotice;

    private double trainprice;

    private int maxpeople;

    private String trainpublishtime;

    private String trainimg;

    private String shopid;

    private int traintrading;

    public String getTrainid() {
        return trainid;
    }

    public void setTrainid(String trainid){
        this.trainid = trainid;
    }

    public String getTrainname() {
        return trainname;
    }

    public void setTrainname(String trainname){
        this.trainname = trainname;
    }

    public String getTraindescription() {
        return traindescription;
    }

    public void setTraindescription(String traindescription){
        this.traindescription = traindescription;
    }

    public String getTrainnotice() {
        return trainnotice;
    }

    public void setTrainnotice(String trainnotice){
        this.trainnotice = trainnotice;
    }

    public double getTrainprice() {
        return trainprice;
    }

    public void setTrainprice(double trainprice){
        this.trainprice = trainprice;
    }

    public int getMaxpeople() {
        return maxpeople;
    }

    public void setMaxpeople(int maxpeople){
        this.maxpeople = maxpeople;
    }

    public String getTrainpublishtime() {
        return trainpublishtime;
    }

    public void setTrainpublishtime(String trainpublishtime){
        this.trainpublishtime = trainpublishtime;
    }

    public String getTrainimg() {
        return trainimg;
    }

    public void setTrainimg(String trainimg){
        this.trainimg = trainimg;
    }

    public String getShopid() {
        return shopid;
    }

    public void setShopid(String shopid){
        this.shopid = shopid;
    }

    public int getTraintrading() { return traintrading; }

    public void setTraintrading(int traintrading){
        this.traintrading = traintrading;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}