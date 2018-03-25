package com.jsj141.osport.service;

import com.iw86.base.Row;
import com.jsj141.osport.config.Constant;
import com.jsj141.osport.domain.Clubactivity;
import com.jsj141.osport.domain.Trip;
import com.jsj141.osport.util.Result;
import com.jsj141.osport.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClubactivityService {
    private final Logger logger = LoggerFactory.getLogger(ClubactivityService.class);

    public Result save(Clubactivity clubactivity) {
        Result result = ResultUtil.initResult();
        Constant.FACADE.getClubactivityDao().insert(clubactivity);
        ResultUtil.setSuccess(result, "添加clubactivity信息成功", clubactivity);
        return result;
    }

    public Result update(Trip trip) {
        Result result = ResultUtil.initResult();
        Constant.FACADE.getTripDao().update(trip);
        ResultUtil.setSuccess(result, "修改Trip信息成功", trip);
        return result;
    }

    public Result updatePeople() {
        Result result = ResultUtil.initResult();
        Constant.FACADE.getClubactivityDao().updatePeople();
        ResultUtil.setSuccess(result, "修改Clubactivity人数信息成功");
        return result;
    }

    public List<Clubactivity> getByClubid(Clubactivity clubactivity) {
        return Constant.FACADE.getClubactivityDao().getByClubid(clubactivity);
    }

    public Result getTripInfo(Trip trip) {
        Result result = ResultUtil.initResult();
        Trip tripInfo = (Trip) Constant.FACADE.getTripDao().select(trip);
        ResultUtil.setSuccess(result, "获取Trip信息成功", tripInfo);
        return result;
    }

    public Result getTripCount(String shopid) {
        Result result = ResultUtil.initResult();
        int count = Constant.FACADE.getTripDao().getCountByShopId(shopid);
        ResultUtil.setSuccess(result, "获取当前店铺Trip数量成功", count);
        return result;
    }

    public Result deleteTripInfo(Trip trip) {
        Result result = ResultUtil.initResult();
        Constant.FACADE.getTripDao().delete(trip.getTripid());
        ResultUtil.setSuccess(result, "删除户外出团活动信息成功", null);
        return result;
    }


    /**
     * 分页查询
     * @param page 要获得数据的页码
     * @param size 每一页显示的最大记录数
     * @return
     */
    public List<Trip> list(int page, int size) {
        Row row = new Row();
        row.put("start", (page - 1) * size);
        row.put("size", size);
        return Constant.FACADE.getTripDao().list(row);
    }

    /**
     * 取指定数目的数据
     * @param page 要获得数据的页码
     * @param size 每一页显示的最大记录数
     * @return
     */
    public List<Trip> listdesc(int page, int size, String order) {
        Row row = new Row();
        row.put("start", page);
        row.put("size", size);
        row.put("order",order);
        return Constant.FACADE.getTripDao().listdesc(row);
    }

    /**
     * 商店下 取指定数目的数据
     * @param start 要获得数据开始记录数
     * @param size 每一页显示的最大记录数
     * @return
     */
    public List<Trip> listdesc(int start, int size, String shopid, String order) {
        Row row = new Row();
        row.put("start", start);
        row.put("size", size);
        row.put("shopid", shopid);
        row.put("order",order);
        return Constant.FACADE.getTripDao().listdesc(row);
    }

}
