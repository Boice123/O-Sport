package com.jsj141.osport.service;

import com.jsj141.osport.config.Constant;
import com.jsj141.osport.util.Result;
import com.jsj141.osport.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import com.jsj141.osport.domain.Shop;
import com.jsj141.osport.domain.Trip;
import com.jsj141.osport.domain.Train;

@Service
public class ShopService {
    private final Logger logger = LoggerFactory.getLogger(ShopService.class);

    public Result save(Shop shop) {
        Result result = ResultUtil.initResult();
        Constant.FACADE.getShopDao().insert(shop);
        ResultUtil.setSuccess(result, "添加店铺成功", shop);
        return result;
    }

    public Result update(Shop shop) {
        Result result = ResultUtil.initResult();
        Constant.FACADE.getShopDao().update(shop);
        ResultUtil.setSuccess(result, "修改店铺信息成功", shop);
        return result;
    }

    public Result getShopInfo(String shopid) {
        Result result = ResultUtil.initResult();
        Shop shop = Constant.FACADE.getShopDao().selectByShopId(shopid);
        if(shop == null) {
            result.setCode(1);
            result.setMsg("该用户还没实名认证进行店铺注册");
        }else {
            result.setCode(0);
            result.setData(shop);
            result.setMsg("获取店铺信息成功");
        }
        return result;
    }

    public Result checkShopExist(String userid) {
        Result result = ResultUtil.initResult();
        Shop shop = Constant.FACADE.getShopDao().selectByUserId(userid);
        if(shop == null) {
            result.setCode(1);
            result.setMsg("该用户还没实名认证进行店铺注册");
        }else {
            result.setCode(0);
            result.setData(shop);
            result.setMsg("用户已实名开店");
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

    public Result getManageTrain(String shopid) {
        Result result = ResultUtil.initResult();
        List<Train> trainList = Constant.FACADE.getTrainDao().selectTrainByShopId(shopid);
        if(trainList == null) {
            result.setCode(1);
            result.setMsg("小店还没有组织户外培训活动呢！");
        }else {
            result.setCode(0);
            result.setData(trainList);
            result.setMsg("获取店铺户外培训列表成功");
        }
        return result;
    }
}
