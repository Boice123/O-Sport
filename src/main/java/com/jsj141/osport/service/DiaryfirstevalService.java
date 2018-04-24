package com.jsj141.osport.service;

import com.iw86.base.Row;
import com.jsj141.osport.config.Constant;
import com.jsj141.osport.domain.Diaryfirsteval;
import com.jsj141.osport.domain.Evaluate;
import com.jsj141.osport.util.Result;
import com.jsj141.osport.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiaryfirstevalService {
    private final Logger logger = LoggerFactory.getLogger(DiaryfirstevalService.class);

    public Result save(Diaryfirsteval diaryfirsteval) {
        Result result = ResultUtil.initResult();
        Constant.FACADE.getDiaryfirstevalDao().insert(diaryfirsteval);
        ResultUtil.setSuccess(result, "评论成功", diaryfirsteval);
        return result;
    }

    public Result delete(Diaryfirsteval diaryfirsteval) {
        Result result = ResultUtil.initResult();
        Constant.FACADE.getDiaryfirstevalDao().delete(diaryfirsteval);
        ResultUtil.setSuccess(result, "删除评论成功", null);
        return result;
    }

    public List<Diaryfirsteval> listdesc(String clubdiaryid, String order) {
        Row row = new Row();
        row.put("clubdiaryid",clubdiaryid);
        row.put("order",order);
        return Constant.FACADE.getDiaryfirstevalDao().listdesc(row);
    }
}
