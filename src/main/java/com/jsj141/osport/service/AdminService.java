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
            result.setMsg("手机号不存在，请重新确认或前往注册");
        }else if(!loginAdmin.getAdminpassword().equals(admin.getAdminpassword())) {
            result.setCode(1);
            result.setMsg("密码错误，请重新确认");
        }else {
            ResultUtil.setSuccess(result, "登录成功", loginAdmin);
        }
        return result;
    }

    public Result update(Shop shop) {
        Result result = ResultUtil.initResult();
        Constant.FACADE.getShopDao().update(shop);
        ResultUtil.setSuccess(result, "修改店铺信息成功", shop);
        return result;
    }

    public Admin getAdminByTel(Admin admin) {
        Admin loginAdmin = (Admin)Constant.FACADE.getAdminDao().selectByTel(admin);
        return loginAdmin;
    }

    public Result getTodayTrading(String shopid) {
        Result result = ResultUtil.initResult();
        List<Triporder> triporderList = Constant.FACADE.getTriporderDao().selectByShopid(shopid);
        if(triporderList.size() == 0) {
            result.setCode(1);
            result.setMsg("该商店还没有订单");
        }else {
            result.setCode(0);
            result.setData(triporderList);
            result.setMsg("获取商店订单成功");
        }
        return result;
    }

    public Result getManageTrip(String shopid) {
        Result result = ResultUtil.initResult();
        List<Trip> tripList = Constant.FACADE.getTripDao().selectTripByShopId(shopid);
        if(tripList == null) {
            result.setCode(1);
            result.setMsg("小店还没有组织户外出团活动呢！");
        }else {
            result.setCode(0);
            result.setData(tripList);
            result.setMsg("获取店铺户外出团活动列表成功");
        }
        return result;
    }

}
