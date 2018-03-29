package com.jsj141.osport.service;

import com.iw86.base.Row;
import com.jsj141.osport.config.Constant;
import com.jsj141.osport.domain.Clubactivity;
import com.jsj141.osport.domain.Trip;
import com.jsj141.osport.domain.Clubdiary;
import com.jsj141.osport.util.Result;
import com.jsj141.osport.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClubdiaryService {
    private final Logger logger = LoggerFactory.getLogger(ClubdiaryService.class);

    public Result save(Clubdiary clubdiary) {
        Result result = ResultUtil.initResult();
        Constant.FACADE.getClubdiaryDao().insert(clubdiary);
        ResultUtil.setSuccess(result, "添加clubdiary信息成功", clubdiary);
        return result;
    }

    public Result update(Trip trip) {
        Result result = ResultUtil.initResult();
        Constant.FACADE.getTripDao().update(trip);
        ResultUtil.setSuccess(result, "修改Trip信息成功", trip);
        return result;
    }

    public Result updateTripTrading(Trip trip) {
        Result result = ResultUtil.initResult();
        Constant.FACADE.getTripDao().updateTripTrading(trip);
        ResultUtil.setSuccess(result, "修改Trip trading信息成功", trip);
        return result;
    }

    public List<Clubdiary> getByClubid(Clubdiary clubdiary) {
        return Constant.FACADE.getClubdiaryDao().getByClubid(clubdiary);
    }

    public List<Clubdiary> getByClubowner(String clubowner) {
        return Constant.FACADE.getClubdiaryDao().getByClubowner(clubowner);
    }

    public List<Clubdiary> getByUserid(String userid) {
        return Constant.FACADE.getClubdiaryDao().getByUserid(userid);
    }

    public Result getTripInfo(Trip trip) {
        Result result = ResultUtil.initResult();
        Trip tripInfo = (Trip) Constant.FACADE.getTripDao().select(trip);
        ResultUtil.setSuccess(result, "获取Trip信息成功", tripInfo);
        return result;
    }

    public Result delete(Clubdiary clubdiary) {
        Result result = ResultUtil.initResult();
        Constant.FACADE.getClubdiaryDao().delete(clubdiary);
        ResultUtil.setSuccess(result, "删除动态信息成功", null);
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

    public List<Clubdiary> listdesc(int page, int size, String order, String clubid) {
        Row row = new Row();
        row.put("start", page);
        row.put("size", size);
        row.put("order",order);
        row.put("clubid",clubid);
        return Constant.FACADE.getClubdiaryDao().listdesc(row);
    }

}
