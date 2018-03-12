package com.jsj141.osport.service;

import com.iw86.base.Row;
import com.jsj141.osport.config.Constant;
import com.jsj141.osport.domain.Train;
import com.jsj141.osport.util.Result;
import com.jsj141.osport.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainService {
    private final Logger logger = LoggerFactory.getLogger(TrainService.class);

    public Result save(Train train) {
        Result result = ResultUtil.initResult();
        Train addTrain = (Train) Constant.FACADE.getTrainDao().select(train);
        Constant.FACADE.getTrainDao().insert(train);
        ResultUtil.setSuccess(result, "添加户外出团活动信息成功", train);
        return result;
    }

    public Result update(Train train) {
        Result result = ResultUtil.initResult();
        Constant.FACADE.getTrainDao().update(train);
        ResultUtil.setSuccess(result, "修改户外出团活动信息成功", train);
        return result;
    }

    public Result getTrainInfo(Train train) {
        Result result = ResultUtil.initResult();
        Train trainInfo = (Train) Constant.FACADE.getTrainDao().select(train);
        ResultUtil.setSuccess(result, "获取户外出团活动信息成功", trainInfo);
        return result;
    }

    public Result getTrainCount(String shopid) {
        Result result = ResultUtil.initResult();
        int count = Constant.FACADE.getTrainDao().getCountByShopId(shopid);
        ResultUtil.setSuccess(result, "获取当前店铺Train数量成功", count);
        return result;
    }

    public Result deleteTrainInfo(Train train) {
        Result result = ResultUtil.initResult();
        Constant.FACADE.getTrainDao().delete(train.getTrainid());
        ResultUtil.setSuccess(result, "删除户外出团活动信息成功", null);
        return result;
    }

    /**
     * 取指定数目的数据
     * @param page 要获得数据的页码
     * @param size 每一页显示的最大记录数
     * @return
     */
    public List<Train> listdesc(int page, int size, String order) {
        Row row = new Row();
        row.put("start", page);
        row.put("size", size);
        row.put("order",order);
        return Constant.FACADE.getTrainDao().listdesc(row);
    }
}
