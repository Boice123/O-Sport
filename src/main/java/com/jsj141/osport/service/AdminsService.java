package com.jsj141.osport.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.jsj141.osport.domain.Admins;
import com.jsj141.osport.config.Constant;

@Service
public class AdminsService {
    private final Logger logger = LoggerFactory.getLogger(AdminsService.class);

    public void save(Admins admins) {
        admins.setLoginName(admins.getLoginName());
        Constant.FACADE.getAdminsDao().insert(admins);
    }
}
