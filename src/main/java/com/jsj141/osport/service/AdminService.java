package com.jsj141.osport.service;

import com.jsj141.osport.config.Constant;
import com.jsj141.osport.domain.*;
import com.jsj141.osport.util.Result;
import com.jsj141.osport.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    private final Logger logger = LoggerFactory.getLogger(AdminService.class);

    public Result save(Admin admin) {
        Result result = ResultUtil.initResult();
        Constant.FACADE.getAdminDao().insert(admin);
        ResultUtil.setSuccess(result, "添加管理员成功", admin);
        return result;
    }

    public Result signin(Admin admin) {
        Result result = ResultUtil.initResult();
        Admin loginAdmin = (Admin)Constant.FACADE.getAdminDao().selectByTel(admin);
        if(loginAdmin == null) {
            result.setCode(1);
            result.setMsg("不存在这个管理员");
        }else if(!loginAdmin.getAdminpassword().equals(admin.getAdminpassword())) {
            result.setCode(1);
            result.setMsg("密码错误，请重新确认");
        }else {
            ResultUtil.setSuccess(result, "登录成功", loginAdmin);
        }
        return result;
    }

    public Admin getAdminByTel(Admin admin) {
        Admin loginAdmin = (Admin)Constant.FACADE.getAdminDao().selectByTel(admin);
        return loginAdmin;
    }
}
