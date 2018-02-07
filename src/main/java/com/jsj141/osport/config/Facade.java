package com.jsj141.osport.config;

import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.jsj141.osport.dao.AdminDao;

/**
 * 门面类
 */
@Component
public class Facade {
    @Autowired
    private Producer captchaProducer;

    @Autowired
    private AdminDao adminDao;

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
}
