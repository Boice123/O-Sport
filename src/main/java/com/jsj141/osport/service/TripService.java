package com.jsj141.osport.service;

import com.jsj141.osport.config.Constant;
import com.jsj141.osport.util.Result;
import com.jsj141.osport.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.jsj141.osport.domain.Trip;

@Service
public class TripService {
    private final Logger logger = LoggerFactory.getLogger(TripService.class);

    public Result save(Trip trip) {
        Result result = ResultUtil.initResult();
        Trip addTrip = (Trip) Constant.FACADE.getTripDao().select(trip);
        Constant.FACADE.getTripDao().insert(trip);
        ResultUtil.setSuccess(result, "添加成功", trip);
        return result;
    }
}
