package com.jsj141.osport.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * @author AutoGenerator
 * 
 */
public class Shop implements Serializable {

    private String shopid;

    private String shopname;

    private int shopstatus;

    private String shoppublishtime;

    private String userid;

    private String shopimg;

    public String getShopid() {
        return shopid;
    }

    public void setShopid(String shopid){
        this.shopid = shopid;
    }

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname){
        this.shopname = shopname;
    }

    public int getShopstatus() {
        return shopstatus;
    }

    public void setShopstatus(int shopstatus){
        this.shopstatus = shopstatus;
    }

    public String getShoppublishtime() {
        return shoppublishtime;
    }

    public void setShoppublishtime(String shoppublishtime){
        this.shoppublishtime = shoppublishtime;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid){
        this.userid = userid;
    }

    public String getShopimg() {
        return shopimg;
    }

    public void setShopimg(String shopimg){
        this.shopimg = shopimg;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}