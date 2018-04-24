package com.jsj141.osport.service;

import com.iw86.base.Row;
import com.jsj141.osport.config.Constant;
import com.jsj141.osport.domain.Clubactivity;
import com.jsj141.osport.domain.Trip;
import com.jsj141.osport.domain.Clubuseractivity;
import com.jsj141.osport.util.Result;
import com.jsj141.osport.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClubuseractivityService {
    private final Logger logger = LoggerFactory.getLogger(ClubuseractivityService.class);

    public Result save(Clubuseractivity clubuserbactivity) {
        Result result = ResultUtil.initResult();
        Constant.FACADE.getClubuseractivityDao().insert(clubuserbactivity);
        ResultUtil.setSuccess(result, "添加clubserbactivity信息成功", clubuserbactivity);
        return result;
    }

    public List<Clubactivity> getByClubid(Clubactivity clubactivity) {
        return Constant.FACADE.getClubactivityDao().getByClubid(clubactivity);
    }

    public Clubuseractivity getByUseridNClubactivityId(Clubuseractivity clubuseractivity) {
        return Constant.FACADE.getClubuseractivityDao().getByUseridNClubactivityId(clubuseractivity);
    }

    public Result deleteByClubactivityid(String clubactivityid) {
        Result result = ResultUtil.initResult();
        Constant.FACADE.getClubuseractivityDao().deleteByClubactivityid(clubactivityid);
        ResultUtil.setSuccess(result, "删除对应的用户参与活动的信息成功", null);
        return result;
    }

    public Result deleteTripInfo(Trip trip) {
        Result result = ResultUtil.initResult();
        Constant.FACADE.getTripDao().delete(trip.getTripid());
        ResultUtil.setSuccess(result, "删除户外出团活动信息成功", null);
        return result;
    }
}
