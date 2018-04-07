package com.jsj141.osport.service;

import com.iw86.base.Row;
import com.jsj141.osport.config.Constant;
import com.jsj141.osport.domain.Triporder;
import com.jsj141.osport.domain.Trip;
import com.jsj141.osport.domain.Triptime;
import com.jsj141.osport.domain.Triporderitem;
import com.jsj141.osport.util.Result;
import com.jsj141.osport.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TriporderService {
    private final Logger logger = LoggerFactory.getLogger(TriporderService.class);

    public Result save(Triporder Triporder) {
        Result result = ResultUtil.initResult();
        Constant.FACADE.getTriporderDao().insert(Triporder);
        ResultUtil.setSuccess(result, "添加Triporder信息成功", Triporder);
        return result;
    }

    public Result update(Triporder Triporder) {
        Result result = ResultUtil.initResult();
        Constant.FACADE.getTriporderDao().update(Triporder);
        ResultUtil.setSuccess(result, "修改Triporder信息成功", Triporder);
        return result;
    }

    public Result getTriporderInfo(Triporder Triporder) {
        Result result = ResultUtil.initResult();
        Triporder TriporderInfo = (Triporder) Constant.FACADE.getTriporderDao().select(Triporder);
        ResultUtil.setSuccess(result, "获取Triporder信息成功", TriporderInfo);
        return result;
    }

    public Triporder get(Triporder Triporder) {
        Result result = ResultUtil.initResult();
        Triporder TriporderInfo = (Triporder) Constant.FACADE.getTriporderDao().select(Triporder);
        return TriporderInfo;
    }

    public Result deleteTriporderInfo(Triporder Triporder) {
        Result result = ResultUtil.initResult();
        Constant.FACADE.getTriporderDao().delete(Triporder.getTriporderid());
        ResultUtil.setSuccess(result, "删除Triporderorder信息成功", null);
        return result;
    }


    /**
     * 分页查询
     * @param page 要获得数据的页码
     * @param size 每一页显示的最大记录数
     * @return
     */
    public List<Triporder> list(int page, int size) {
        Row row = new Row();
        row.put("start", (page - 1) * size);
        row.put("size", size);
        return Constant.FACADE.getTriporderDao().list(row);
    }

    /**
     * 根据shopid分页查询
     * @param page 要获得数据的页码
     * @param size 每一页显示的最大记录数
     * @return
     */
    public List<Triporder> listByShopid(int page, int size, String shopid) {
        Row row = new Row();
        row.put("start", (page - 1) * size);
        row.put("size", size);
        row.put("shopid", shopid);
        return Constant.FACADE.getTriporderDao().list(row);
    }

    public int shopTripOrderCount(String shopid) {
        Row row = new Row();
        row.put("shopid",shopid);
        return Constant.FACADE.getTriporderDao().shopTripOrderCount(row);
    }

    /**
     * 根据userid查询订单
     * @param userid
     * @return
     */
    public List<Triporder> getUserTripOrder(String userid) {
        List<Triporder> triporder = (List<Triporder>) Constant.FACADE.getTriporderDao().selectByUserid(userid);
        for(int i = 0; i < triporder.size();i ++) {
            String triporderitemid = triporder.get(i).getTriporderitemid();
            Triporderitem triporderitem = new Triporderitem();
            triporderitem.setTriporderitemid(triporderitemid);
            Triporderitem resultitem = (Triporderitem) Constant.FACADE.getTriporderitemDao().select(triporderitem);
            String tripid = resultitem.getTripid();
            String triptimeid = resultitem.getTriptimeid();
            Trip trip = new Trip();
            trip.setTripid(tripid);
            Trip resultTrip = (Trip)Constant.FACADE.getTripDao().select(trip);
            Triptime triptime = new Triptime();
            triptime.setTriptimeid(triptimeid);
            Triptime resultTriptime = (Triptime)Constant.FACADE.getTriptimeDao().select(triptime);
            triporder.get(i).setTrip(resultTrip);
            triporder.get(i).setTriptime(resultTriptime);
            triporder.get(i).setTriporderitem(resultitem);
        }
        return triporder;
    }


    /**
     * 取指定数目的数据
     * @param page 要获得数据的页码
     * @param size 每一页显示的最大记录数
     * @return
     */
    public List<Triporder> listdesc(int page, int size, String shopid, String order) {
        Row row = new Row();
        if(page != -1) {
            row.put("start", page);
        }
        if(size != -1) {
            row.put("size", size);
        }
        if(!shopid.equals("")) {
            row.put("shopid",shopid);
        }
        if(!order.equals("")) {
                row.put("order",order);
        }
        return Constant.FACADE.getTriporderDao().listdesc(row);
    }

    /**
     * 取指定数目的数据
     * @param page 要获得数据的页码
     * @param size 每一页显示的最大记录数
     * @return
     */
    public List<Triporder> listdescn(int page, int size, String shopid, String order) {
        Row row = new Row();
        if(page != -1) {
            row.put("start", page);
        }
        if(size != -1){
            row.put("size", size);
        }
        if(!shopid.equals("")) {
            row.put("shopid",shopid);
        }
        if(!order.equals("")) {
            row.put("order",order);
        }
        return Constant.FACADE.getTriporderDao().listdescn(row);
    }

}
