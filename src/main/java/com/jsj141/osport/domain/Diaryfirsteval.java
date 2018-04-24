package com.jsj141.osport.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.jsj141.osport.domain.Diarysecondeval;
import java.util.List;
import java.io.Serializable;

/**
 * @author AutoGenerator
 * 
 */
public class Diaryfirsteval implements Serializable {

    private String evalid;

    private String content;

    private String clubdiaryid;

    private String userid;

    private String evaltime;

    private User user;

    private List<Diarysecondeval> secondevalList;

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

    public String getClubdiaryid() {
        return clubdiaryid;
    }

    public void setClubdiaryid(String clubdiaryid){
        this.clubdiaryid = clubdiaryid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid){
        this.userid = userid;
    }

    public String getEvaltime() {
        return evaltime;
    }

    public void setEvaltime(String evaltime) {
        this.evaltime = evaltime;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public List<Diarysecondeval> getSecondevalList() {
        return secondevalList;
    }

    public void setSecondevalList(List<Diarysecondeval> secondevalList) {
        this.secondevalList = secondevalList;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}