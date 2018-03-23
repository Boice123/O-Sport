package com.jsj141.osport.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * @author AutoGenerator
 * 
 */
public class Triptime implements Serializable {

    private String triptimeid;

    private String triptime;

    private String tripid;

    private int triptimemaxpeople;

    private int triptimestatus;

    public String getTriptimeid() {
        return triptimeid;
    }

    public void setTriptimeid(String triptimeid){
        this.triptimeid = triptimeid;
    }

    public String getTriptime() {
        return triptime;
    }

    public void setTriptime(String triptime){
        this.triptime = triptime;
    }

    public String getTripid() {
        return tripid;
    }

    public void setTripid(String tripid){
        this.tripid = tripid;
    }

    public void setTriptimemaxpeople(int triptimemaxpeople) {
        this.triptimemaxpeople = triptimemaxpeople;
    }

    public int getTriptimemaxpeople() {
        return triptimemaxpeople;
    }

    public int getTriptimestatus() {
        return triptimestatus;
    }

    public void setTriptimestatus(int triptimestatus) {
        this.triptimestatus = triptimestatus;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}