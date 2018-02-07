package com.jsj141.osport.service;

import com.jsj141.osport.domain.Admin;
import com.jsj141.osport.config.Constant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.jsj141.osport.util.Result;
import com.jsj141.osport.util.ResultUtil;

@Service
public class AdminService {
    private final Logger logger = LoggerFactory.getLogger(AdminService.class);

    public Result save(Admin admin) {
        Result result = ResultUtil.initResult();
        Constant.FACADE.getAdminDao().insert(admin);
        ResultUtil.setSuccess(result, "添加成功", null);
        return result;
    }
}
