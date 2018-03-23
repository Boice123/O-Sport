package com.jsj141.osport.config;

import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.jsj141.osport.dao.AdminDao;
import com.jsj141.osport.dao.UserDao;
import com.jsj141.osport.dao.TripDao;
import com.jsj141.osport.dao.ShopDao;
import com.jsj141.osport.dao.TrainDao;
import com.jsj141.osport.dao.TriptimeDao;
import com.jsj141.osport.dao.TriporderDao;
import com.jsj141.osport.dao.TriporderitemDao;
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
    private ShopDao shopDao;

    @Autowired
    private TripDao tripDao;

    @Autowired
    private TrainDao trainDao;

    @Autowired
    private TriptimeDao triptimeDao;

    @Autowired
    private TriporderDao triporderDao;

    @Autowired
    private TriporderitemDao triporderitemDao;

    public Producer getCaptchaProducer() {
        return captchaProducer;
    }

    public void setCaptchaProducer(Producer captchaProducer) {
        this.captchaProducer = captchaProducer;
    }

    public AdminDao getAdminDao() {
        return adminDao;
    }

    public void setAdminsDao(AdminDao adminDao) {
        this.adminDao = adminDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public ShopDao getShopDao() {
        return shopDao;
    }

    public void setShopDao(ShopDao shopDao) {
        this.shopDao = shopDao;
    }

    public TripDao getTripDao() {
        return tripDao;
    }

    public void setTripDao(TripDao tripDao) {
        this.tripDao = tripDao;
    }

    public TrainDao getTrainDao() {
        return trainDao;
    }

    public void setTrainDao(TrainDao trainDao) {
        this.trainDao = trainDao;
    }


    public TriptimeDao getTriptimeDao() {
        return triptimeDao;
    }

    public void setTriptimeDao(TriptimeDao triptimeDao) {
        this.triptimeDao = triptimeDao;
    }


    public TriporderDao getTriporderDao() {
        return triporderDao;
    }

    public void setTriporderDao(TriporderDao triporderDao) {
        this.triporderDao = triporderDao;
    }

    public TriporderitemDao getTriporderitemDao() {
        return triporderitemDao;
    }

    public void setTriporderitemDao(TriporderitemDao triporderitemDao) {
        this.triporderitemDao = triporderitemDao;
    }

}
