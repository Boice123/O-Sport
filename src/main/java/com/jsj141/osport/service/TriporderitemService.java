package com.jsj141.osport.service;

import com.iw86.base.Row;
import com.jsj141.osport.config.Constant;
import com.jsj141.osport.domain.Triporderitem;
import com.jsj141.osport.util.Result;
import com.jsj141.osport.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TriporderitemService {
    private final Logger logger = LoggerFactory.getLogger(TriporderitemService.class);

    public Result save(Triporderitem Triporderitem) {
        Result result = ResultUtil.initResult();
        Constant.FACADE.getTriporderitemDao().insert(Triporderitem);
        ResultUtil.setSuccess(result, "添加Triporderitem信息成功", Triporderitem);
        return result;
    }

    public Result update(Triporderitem Triporderitem) {
        Result result = ResultUtil.initResult();
        Constant.FACADE.getTriporderitemDao().update(Triporderitem);
        ResultUtil.setSuccess(result, "修改Triporderitem信息成功", Triporderitem);
        return result;
    }

    public Result getTriporderitemInfo(Triporderitem Triporderitem) {
        Result result = ResultUtil.initResult();
        Triporderitem TriporderitemInfo = (Triporderitem) Constant.FACADE.getTriporderitemDao().select(Triporderitem);
        ResultUtil.setSuccess(result, "获取Triporderitem信息成功", TriporderitemInfo);
        return result;
    }

//    public Result getTriporderitemCount(String shopid) {
//        Result result = ResultUtil.initResult();
//        int count = Constant.FACADE.getTriporderitemDao().getCountByShopId(shopid);
//        ResultUtil.setSuccess(result, "获取当前店铺Triporderitem数量成功", count);
//        return result;
//    }

    public Result deleteTriporderitemInfo(Triporderitem Triporderitem) {
        Result result = ResultUtil.initResult();
        Constant.FACADE.getTriporderitemDao().delete(Triporderitem.getTriporderitemid());
        ResultUtil.setSuccess(result, "删除Triporderitem信息成功", null);
        return result;
    }


    /**
     * 分页查询
     * @param page 要获得数据的页码
     * @param size 每一页显示的最大记录数
     * @return
     */
    public List<Triporderitem> list(int page, int size) {
        Row row = new Row();
        row.put("start", (page - 1) * size);
        row.put("size", size);
        return Constant.FACADE.getTriporderitemDao().list(row);
    }

    /**
     * 取指定数目的数据
     * @param page 要获得数据的页码
     * @param size 每一页显示的最大记录数
     * @return
//     */
//    public List<Triporderitem> listdesc(int page, int size, String order) {
//        Row row = new Row();
//        row.put("start", page);
//        row.put("size", size);
//        row.put("order",order);
//        return Constant.FACADE.getTriporderitemDao().listdesc(row);
//    }

    /**
     * 商店下 取指定数目的数据
     * @param start 要获得数据开始记录数
     * @param size 每一页显示的最大记录数
     * @return
     */
//    public List<Triporderitem> listdesc(int start, int size, String shopid, String order) {
//        Row row = new Row();
//        row.put("start", start);
//        row.put("size", size);
//        row.put("shopid", shopid);
//        row.put("order",order);
//        return Constant.FACADE.getTriporderitemDao().listdesc(row);
//    }

}
