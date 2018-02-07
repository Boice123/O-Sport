package com.jsj141.osport.service;

import com.jsj141.osport.config.Constant;
import com.jsj141.osport.domain.User;
import com.jsj141.osport.util.Result;
import com.jsj141.osport.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    public Result save(User user) {
        Result result = ResultUtil.initResult();
        Constant.FACADE.getUserDao().insert(user);
        ResultUtil.setSuccess(result, "添加成功", null);
        return result;
    }
}
