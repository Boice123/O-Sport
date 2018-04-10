package com.jsj141.osport.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * @author AutoGenerator
 * 
 */
public class Evaluate implements Serializable {

    private String evaluateid;

    private String tripid;

    private String triporderid;

    private String userid;

    private String content;

    private String evaluatetime;

    public String getEvaluateid() {
        return evaluateid;
    }

    public void setEvaluateid(String evaluateid){
        this.evaluateid = evaluateid;
    }

    public String getTripid() {
        return tripid;
    }

    public void setTripid(String tripid){
        this.tripid = tripid;
    }

    public String getTriporderid() {
        return triporderid;
    }

    public void setTriporderid(String triporderid){
        this.triporderid = triporderid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid){
        this.userid = userid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getEvaluatetime() {
        return evaluatetime;
    }

    public void setEvaluatetime(String evaluatetime) {
        this.evaluatetime = evaluatetime;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}