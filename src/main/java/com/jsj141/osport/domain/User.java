package com.jsj141.osport.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * @author AutoGenerator
 * 
 */
public class User implements Serializable {

    private String userid;

    private String username;

    private String password;

    private String tel;

    private String registerTime;

    private String userimg;

    private String realname;

    private String realid;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid){
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel){
        this.tel = tel;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime){
        this.registerTime = registerTime;
    }

    public String getUserimg() {
        return userimg;
    }

    public void setUserimg(String userimg) {
        this.userimg = userimg;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname){
        this.realname = realname;
    }

    public String getRealid() {
        return realid;
    }

    public void setRealid(String realid){
        this.realid = realid;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}