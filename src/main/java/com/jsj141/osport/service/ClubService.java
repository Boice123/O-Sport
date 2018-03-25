package com.jsj141.osport.service;

import com.iw86.base.Row;
import com.jsj141.osport.config.Constant;
import com.jsj141.osport.domain.Club;
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
public class ClubService {
    private final Logger logger = LoggerFactory.getLogger(ClubService.class);

    public Result save(Club club) {
        Result result = ResultUtil.initResult();
        Constant.FACADE.getClubDao().insert(club);
        ResultUtil.setSuccess(result, "创建部落成功", club);
        return result;
    }

    public Result update(Shop shop) {
        Result result = ResultUtil.initResult();
        Constant.FACADE.getClubDao().update(shop);
        ResultUtil.setSuccess(result, "修改店铺信息成功", shop);
        return result;
    }

    public Result updateClubPeople(String clubid) {
        Result result = ResultUtil.initResult();
        Constant.FACADE.getClubDao().updateClubPeople(clubid);
        ResultUtil.setSuccess(result, "增加部落人数成功", null);
        return result;
    }

    public Club getByClubid(Club club) {
        Club getclub = (Club) Constant.FACADE.getClubDao().select(club);
        return getclub;
    }

    public Club getByClubowner (Club club) {
        Club getclub = Constant.FACADE.getClubDao().selectByClubowner(club);
        return getclub;
    }

    public List<Club> getUserJoinClub(String userid) {
        List<Club> clubList = Constant.FACADE.getClubDao().selectByUserid(userid);
        return clubList;
    }

    public List<Club> getAll() {
        List<Club> clubList = Constant.FACADE.getClubDao().selectAll();
        return clubList;
    }

    public List<Club> listdesc(int page, int size, String order) {
        Row row = new Row();
        row.put("start", page);
        row.put("size", size);
        row.put("order",order);
        return Constant.FACADE.getClubDao().listdesc(row);
    }
    public List<Club> getByClubtab(Club club) {
        List<Club> clubList = Constant.FACADE.getClubDao().selectByClubtab(club);
        return clubList;
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
