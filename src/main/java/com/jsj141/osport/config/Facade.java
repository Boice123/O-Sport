package com.jsj141.osport.config;

import com.google.code.kaptcha.Producer;
import com.jsj141.osport.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 门面类
 */
@Component
public class Facade {
    @Autowired
    private Producer captchaProducer;

    @Autowired
    private AdminDao adminDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private TripDao tripDao;

    @Autowired
    private TriporderDao triporderDao;

    @Autowired
    private ClubDao clubDao;

    @Autowired
    private ClubuseritemDao clubuseritemDao;

    @Autowired
    private ClubdiaryDao clubdiarydao;

    @Autowired
    private ClubactivityDao clubactivitydao;

    @Autowired
    private ClubuseractivityDao clubuseractivityDao;

    @Autowired
    private EvaluateDao evaluateDao;

    @Autowired
    private DiaryfirstevalDao diaryfirstevalDao;

    @Autowired
    private DiarysecondevalDao diarysecondevalDao;

    public Producer getCaptchaProducer() {
        return captchaProducer;
    }

    public void setCaptchaProducer(Producer captchaProducer) {
        this.captchaProducer = captchaProducer;
    }

    public AdminDao getAdminDao() {
        return adminDao;
    }

    public void setAdminDao(AdminDao adminDao) {
        this.adminDao = adminDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public TripDao getTripDao() {
        return tripDao;
    }

    public void setTripDao(TripDao tripDao) {
        this.tripDao = tripDao;
    }

    public TriporderDao getTriporderDao() {
        return triporderDao;
    }

    public void setTriporderDao(TriporderDao triporderDao) {
        this.triporderDao = triporderDao;
    }

    public ClubDao getClubDao() {
        return clubDao;
    }

    public void setClubDao(ClubDao clubDao) {
        this.clubDao = clubDao;
    }

    public ClubuseritemDao getClubuseritemDao() {
        return clubuseritemDao;
    }

    public void setClubuseritemDao(ClubuseritemDao clubuseritemDao) {
        this.clubuseritemDao = clubuseritemDao;
    }

    public ClubdiaryDao getClubdiaryDao() {
        return clubdiarydao;
    }

    public void setClubdiaryDao(ClubdiaryDao clubdiaryDao) {
        this.clubdiarydao = clubdiaryDao;
    }

    public ClubactivityDao getClubactivityDao() {
        return clubactivitydao;
    }

    public void setClubactivityDao(ClubactivityDao clubactivityDao) {
        this.clubactivitydao = clubactivityDao;
    }

    public ClubuseractivityDao getClubuseractivityDao() {
        return clubuseractivityDao;
    }

    public void setClubuseractivityDao(ClubuseractivityDao clubuseractivityDao) {
        this.clubuseractivityDao = clubuseractivityDao;
    }

    public EvaluateDao getEvaluateDao() {
        return evaluateDao;
    }

    public void setEvaluateDao(EvaluateDao evaluateDao) {
        this.evaluateDao = evaluateDao;
    }

    public DiaryfirstevalDao getDiaryfirstevalDao() {
        return diaryfirstevalDao;
    }

    public void setDiaryfirstevalDao(DiaryfirstevalDao diaryfirstevalDao) {
        this.diaryfirstevalDao = diaryfirstevalDao;
    }

    public DiarysecondevalDao getDiarysecondevalDao() {
        return diarysecondevalDao;
    }

    public void setDiarysecondevalDao(DiarysecondevalDao diarysecondevalDao) {
        this.diarysecondevalDao = diarysecondevalDao;
    }
}
