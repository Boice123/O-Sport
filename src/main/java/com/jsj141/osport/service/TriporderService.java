package com.jsj141.osport.service;

import com.iw86.base.Row;
import com.jsj141.osport.config.Constant;
import com.jsj141.osport.domain.Triporder;
import com.jsj141.osport.domain.Trip;
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
        ResultUtil.setSuccess(result, "添加成功", Triporder);
        return result;
    }

    public Result update(Triporder Triporder) {
        Result result = ResultUtil.initResult();
        Constant.FACADE.getTriporderDao().update(Triporder);
        ResultUtil.setSuccess(result, "修改成功", Triporder);
        return result;
    }

    public Triporder getTriporderInfo(Triporder Triporder) {
        Triporder TriporderInfo = (Triporder) Constant.FACADE.getTriporderDao().select(Triporder);
        return TriporderInfo;
    }

    public Triporder get(Triporder Triporder) {
        Result result = ResultUtil.initResult();
        Triporder TriporderInfo = (Triporder) Constant.FACADE.getTriporderDao().select(Triporder);
        return TriporderInfo;
    }

    public Result deleteTriporderInfo(Triporder Triporder) {
        Result result = ResultUtil.initResult();
        Constant.FACADE.getTriporderDao().delete(Triporder.getTriporderid());
        ResultUtil.setSuccess(result, "删除成功", null);
        return result;
    }

    public int count(String tripid) {
        Result result = ResultUtil.initResult();
        Row row = new Row();
        row.put("tripid", tripid);
        int count = Constant.FACADE.getTriporderDao().count(row);
        return count;
    }

    /**
     * 根据userid查询订单
     * @param
     * @return
     */
    public List<Triporder> getUserTripOrder(String userid, String triporderstatus) {
        Row row = new Row();
        if(!userid.equals("")) {
            row.put("userid", userid);
        }
        if(!triporderstatus.equals("")) {
            row.put("triporderstatus", triporderstatus);
        }
        List<Triporder> triporder = (List<Triporder>) Constant.FACADE.getTriporderDao().listdesc(row);
        for(int i = 0; i < triporder.size();i ++) {
        }
        return triporder;
    }

    /**
     * 根据tripid查询已取消的订单
     * @param
     * @return
     */
    public List<Triporder> getTripCloseOrder(String tripid, String triporderstatus) {
        Row row = new Row();
        if(!tripid.equals("")) {
            row.put("tripid", tripid);
        }
        if(!triporderstatus.equals("")) {
            row.put("triporderstatus", triporderstatus);
        }
        List<Triporder> triporder = (List<Triporder>) Constant.FACADE.getTriporderDao().listdesc(row);
        for(int i = 0; i < triporder.size();i ++) {
        }
        return triporder;
    }


    /**
     * 取指定数目的数据
     * @param page 要获得数据的页码
     * @param size 每一页显示的最大记录数
     * @return
     */
    public List<Triporder> listdesc(String tripid, String userid, String triporderstatus, int page, int size,  String order) {
        Row row = new Row();
        if(page != -1) {
            row.put("start", page);
        }
        if(size != -1) {
            row.put("size", size);
        }
        if(!tripid.equals("")) {
            row.put("tripid",tripid);
        }
        if(!userid.equals("")) {
            row.put("userid",userid);
        }
        if(!triporderstatus.equals("")) {
            row.put("triporderstatus",triporderstatus);
        }
        if(!order.equals("")) {
            row.put("order",order);
        }
        return Constant.FACADE.getTriporderDao().listdesc(row);
    }
}
