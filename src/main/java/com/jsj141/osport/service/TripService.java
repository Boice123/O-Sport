package com.jsj141.osport.service;

import com.jsj141.osport.config.Constant;
import com.jsj141.osport.util.Result;
import com.jsj141.osport.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;
import com.iw86.base.Row;

import com.jsj141.osport.domain.Trip;

@Service
public class TripService {
    private final Logger logger = LoggerFactory.getLogger(TripService.class);

    public Result save(Trip trip) {
        Result result = ResultUtil.initResult();
        Constant.FACADE.getTripDao().insert(trip);
        ResultUtil.setSuccess(result, "添加Trip信息成功", trip);
        return result;
    }

    public Result update(Trip trip) {
        Result result = ResultUtil.initResult();
        Constant.FACADE.getTripDao().update(trip);
        ResultUtil.setSuccess(result, "修改成功", trip);
        return result;
    }

    public Result updateTripTrading(Trip trip) {
        Result result = ResultUtil.initResult();
        Constant.FACADE.getTripDao().updateTripTrading(trip);
        ResultUtil.setSuccess(result, "修改Trip trading信息成功", trip);
        return result;
    }

    public Trip getTripInfo(Trip trip) {
        Result result = ResultUtil.initResult();
        Trip tripInfo = (Trip) Constant.FACADE.getTripDao().select(trip);
        ResultUtil.setSuccess(result, "获取Trip信息成功", tripInfo);
        return tripInfo;
    }

    public Result getTripCount() {
        Result result = ResultUtil.initResult();
        int count = Constant.FACADE.getTripDao().count(new Row());
        ResultUtil.setSuccess(result, "获取当前Trip发布中数量成功", count);
        return result;
    }

    public Result getTripCountClose() {
        Result result = ResultUtil.initResult();
        int count = Constant.FACADE.getTripDao().countClose(new Row());
        ResultUtil.setSuccess(result, "获取当前Trip已关闭数量成功", count);
        return result;
    }

    public Result deleteTripInfo(Trip trip) {
        Result result = ResultUtil.initResult();
        Constant.FACADE.getTripDao().delete(trip.getTripid());
        ResultUtil.setSuccess(result, "删除户外出团活动信息成功", null);
        return result;
    }

    /**
     * 取Trip指定数目的数据,已发布
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
     * 取Trip指定数目的数据,已关闭
     * @param page
     * @param size
     * @param order
     * @return
     */
    public List<Trip> listdescClose(int page, int size, String order) {
        Row row = new Row();
        row.put("start", page);
        row.put("size", size);
        row.put("order",order);
        return Constant.FACADE.getTripDao().listdescclose(row);
    }

    /**
     * 根据关键词和地区找Trip
     * @param tripprovice
     * @param tripcity
     * @param searchKey
     * @return
     */
    public List<Trip> searchKey(String tripprovice, String tripcity, String searchKey) {
        Row row = new Row();
        searchKey = "'%" + searchKey + "%'";
        if(!tripprovice.equals("") && !tripcity.equals("")) {
            row.put("tripprovice", tripprovice);
            row.put("tripcity", tripcity);
        }
        row.put("searchKey",searchKey);
        return Constant.FACADE.getTripDao().searchKey(row);
    }

}
