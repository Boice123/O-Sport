package com.jsj141.osport.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * @author AutoGenerator
 * 
 */
public class Diarysecondeval implements Serializable {

    private String evalid;

    private String content;

    private String firstevalid;

    private String fromusername;

    private String tousername;

    private String evaltime;

    public String getEvalid() {
        return evalid;
    }

    public void setEvalid(String evalid){
        this.evalid = evalid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content){
        this.content = content;
    }

    public String getFirstevalid() {
        return firstevalid;
    }

    public void setFirstevalid(String firstevalid){
        this.firstevalid = firstevalid;
    }

    public String getFromusername() {
        return fromusername;
    }

    public void setFromusername(String fromusername){
        this.fromusername = fromusername;
    }

    public String getTousername() {
        return tousername;
    }

    public void setTousername(String tousername){
        this.tousername = tousername;
    }

    public String getEvaltime() {
        return evaltime;
    }

    public void setEvaltime(String evaltime){
        this.evaltime = evaltime;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}