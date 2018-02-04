package com.jsj141.osport.config;

import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.jsj141.osport.dao.AdminsDao;

/**
 * 门面类
 */
@Component
public class Facade {
    @Autowired
    private Producer captchaProducer;

    @Autowired
    private AdminsDao adminsDao;

    public Producer getCaptchaProducer() {
        return captchaProducer;
    }

    public void setCaptchaProducer(Producer captchaProducer) {
        this.captchaProducer = captchaProducer;
    }

    public AdminsDao getAdminsDao() {
        return adminsDao;
    }

    public void setAdminsDao(AdminsDao adminsDao) {
        this.adminsDao = adminsDao;
    }
}
