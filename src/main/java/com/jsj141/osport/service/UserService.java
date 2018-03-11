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
        User loginUser = (User)Constant.FACADE.getUserDao().selectByTel(user);
        if(loginUser != null) {
            result.setCode(1);
            result.setMsg("该手机号已注册");
            return result;
        }
        Constant.FACADE.getUserDao().insert(user);
        ResultUtil.setSuccess(result, "添加成功", user);
        return result;
    }

    public void update(User user) {
        Constant.FACADE.getUserDao().update(user);
    }
    public Result signin(User user) {
        Result result = ResultUtil.initResult();
        User loginUser = (User)Constant.FACADE.getUserDao().selectByTel(user);
        if(loginUser == null) {
            result.setCode(1);
            result.setMsg("用户名不存在，请重新确认或前往注册");
        }else if(!loginUser.getPassword().equals(user.getPassword())) {
            result.setCode(1);
            result.setMsg("密码错误，请重新确认");
        }else {
            ResultUtil.setSuccess(result, "登录成功", loginUser);
        }
        return result;
    }

    public Result checkUserTelExist(User user) {
        Result result = ResultUtil.initResult();
        User loginUser = (User)Constant.FACADE.getUserDao().selectByTel(user);
        if(loginUser == null) {
            result.setCode(0);
            result.setMsg("该手机号可用");
        }else {
            result.setCode(1);
            result.setMsg("该手机号已注册！");
        }
        return result;
    }

    public User getUserByTel(User user) {
        User loginUser = (User)Constant.FACADE.getUserDao().selectByTel(user);
        return loginUser;
    }
}
