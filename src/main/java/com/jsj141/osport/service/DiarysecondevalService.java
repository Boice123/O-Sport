package com.jsj141.osport.service;

import com.iw86.base.Row;
import com.jsj141.osport.config.Constant;
import com.jsj141.osport.domain.Diarysecondeval;
import com.jsj141.osport.domain.Diaryfirsteval;
import com.jsj141.osport.util.Result;
import com.jsj141.osport.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiarysecondevalService {
    private final Logger logger = LoggerFactory.getLogger(DiarysecondevalService.class);

    public Result save(Diarysecondeval diarysecondeval) {
        Result result = ResultUtil.initResult();
        Constant.FACADE.getDiarysecondevalDao().insert(diarysecondeval);
        ResultUtil.setSuccess(result, "评论成功", diarysecondeval);
        return result;
    }

    public List<Diarysecondeval> getByFirstevalid(String firstevalid) {
        return Constant.FACADE.getDiarysecondevalDao().getByFirstevalid(firstevalid);
    }

    public Result delete(Diarysecondeval diarysecondeval) {
        Result result = ResultUtil.initResult();
        Constant.FACADE.getDiarysecondevalDao().delete(diarysecondeval);
        ResultUtil.setSuccess(result, "删除评论成功", null);
        return result;
    }
//    public List<Diaryfirsteval> listdesc(String clubdiaryid, String order) {
//        Row row = new Row();
//        row.put("clubdiaryid",clubdiaryid);
//        row.put("order",order);
//        return Constant.FACADE.getDiarysecondevalDao().listdesc(row);
//    }
}
