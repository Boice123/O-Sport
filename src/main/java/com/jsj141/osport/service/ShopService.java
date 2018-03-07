package com.jsj141.osport.service;

import com.jsj141.osport.config.Constant;
import com.jsj141.osport.util.Result;
import com.jsj141.osport.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.jsj141.osport.domain.Shop;

@Service
public class ShopService {
    private final Logger logger = LoggerFactory.getLogger(ShopService.class);

    public Result save(Shop shop) {
        Result result = ResultUtil.initResult();
        Constant.FACADE.getShopDao().insert(shop);
        ResultUtil.setSuccess(result, "添加成功", shop);
        return result;
    }

    public Result update(Shop shop) {
        Result result = ResultUtil.initResult();
        Constant.FACADE.getShopDao().update(shop);
        ResultUtil.setSuccess(result, "修改成功", shop);
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
}
