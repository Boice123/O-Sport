package com.jsj141.osport.service;

import com.jsj141.osport.config.Constant;
import com.jsj141.osport.domain.Club;
import com.jsj141.osport.domain.Clubuseritem;
import com.jsj141.osport.domain.Shop;
import com.jsj141.osport.domain.Trip;
import com.jsj141.osport.domain.Triporder;
import com.jsj141.osport.util.Result;
import com.jsj141.osport.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClubuseritemService {
    private final Logger logger = LoggerFactory.getLogger(ClubuseritemService.class);

    public Result save(Clubuseritem clubuseritem) {
        Result result = ResultUtil.initResult();
        Constant.FACADE.getClubuseritemDao().insert(clubuseritem);
        ResultUtil.setSuccess(result, "关注部落成功", clubuseritem);
        return result;
    }

    public Result update(Shop shop) {
        Result result = ResultUtil.initResult();
        Constant.FACADE.getClubuseritemDao().update(shop);
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

    public Club getByClubowner (Club club) {
        Club getclub = Constant.FACADE.getClubDao().selectByClubowner(club);

        return getclub;
    }

    public Clubuseritem getUseridNClubid (Clubuseritem clubuseritem) {
        Clubuseritem getclubuseritem = Constant.FACADE.getClubuseritemDao().selectByUseridNClubid (clubuseritem);
        return getclubuseritem;
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

//    public Result getManageTrain(String shopid) {
//        Result result = ResultUtil.initResult();
//        List<Train> trainList = Constant.FACADE.getTrainDao().selectTrainByShopId(shopid);
//        if(trainList == null) {
//            result.setCode(1);
//            result.setMsg("小店还没有组织户外培训活动呢！");
//        }else {
//            result.setCode(0);
//            result.setData(trainList);
//            result.setMsg("获取店铺户外培训列表成功");
//        }
//        return result;
//    }
}
