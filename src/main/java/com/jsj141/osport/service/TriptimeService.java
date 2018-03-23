package com.jsj141.osport.service;

import com.iw86.base.Row;
import com.jsj141.osport.config.Constant;
import com.jsj141.osport.domain.Triptime;
import com.jsj141.osport.util.Result;
import com.jsj141.osport.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TriptimeService {
    private final Logger logger = LoggerFactory.getLogger(TriptimeService.class);

    public Result save(Triptime Triptime) {
        Result result = ResultUtil.initResult();
        Constant.FACADE.getTriptimeDao().insert(Triptime);
        ResultUtil.setSuccess(result, "添加Triptime信息成功", Triptime);
        return result;
    }

    public Result update(Triptime Triptime) {
        Result result = ResultUtil.initResult();
        Constant.FACADE.getTriptimeDao().update(Triptime);
        ResultUtil.setSuccess(result, "修改Triptime信息成功", Triptime);
        return result;
    }

    public Result updateTriptimemaxpeople (Triptime triptime) {
        Result result = ResultUtil.initResult();
        Constant.FACADE.getTriptimeDao().updateTriptimemaxpeople(triptime);
        ResultUtil.setSuccess(result, "修改Triptimemaxpeople信息成功", triptime);
        return result;
    }

    public List<Triptime> getTriptimeInfo(Triptime Triptime) {
        return (List<Triptime>) Constant.FACADE.getTriptimeDao().selectList(Triptime);
    }
//
//    public Result getTriptimeCount(String shopid) {
//        Result result = ResultUtil.initResult();
//        int count = Constant.FACADE.getTriptimeDao().getCountByShopId(shopid);
//        ResultUtil.setSuccess(result, "获取当前店铺Triptime数量成功", count);
//        return result;
//    }

    public Result deleteTriptimeInfo(Triptime Triptime) {
        Result result = ResultUtil.initResult();
        Constant.FACADE.getTriptimeDao().deleteByTripid(Triptime.getTripid());
        ResultUtil.setSuccess(result, "删除户外出团活动信息成功", null);
        return result;
    }


    /**
     * 分页查询
     * @param page 要获得数据的页码
     * @param size 每一页显示的最大记录数
     * @return
     */
    public List<Triptime> list(int page, int size) {
        Row row = new Row();
        row.put("start", (page - 1) * size);
        row.put("size", size);
        return Constant.FACADE.getTriptimeDao().list(row);
    }

    /**
     * 取指定数目的数据
     * @param page 要获得数据的页码
     * @param size 每一页显示的最大记录数
//     * @return
//     */
//    public List<Triptime> listdesc(int page, int size, String order) {
//        Row row = new Row();
//        row.put("start", page);
//        row.put("size", size);
//        row.put("order",order);
//        return Constant.FACADE.getTriptimeDao().listdesc(row);
//    }
//
//    /**
//     * 商店下 取指定数目的数据
//     * @param start 要获得数据开始记录数
//     * @param size 每一页显示的最大记录数
//     * @return
//     */
//    public List<Triptime> listdesc(int start, int size, String shopid, String order) {
//        Row row = new Row();
//        row.put("start", start);
//        row.put("size", size);
//        row.put("shopid", shopid);
//        row.put("order",order);
//        return Constant.FACADE.getTriptimeDao().listdesc(row);
//    }

}
