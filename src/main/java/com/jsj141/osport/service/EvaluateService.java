package com.jsj141.osport.service;

import com.iw86.base.Row;
import com.jsj141.osport.config.Constant;
import com.jsj141.osport.domain.Evaluate;
import com.jsj141.osport.domain.Trip;
import com.jsj141.osport.util.Result;
import com.jsj141.osport.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluateService {
    private final Logger logger = LoggerFactory.getLogger(EvaluateService.class);

    public Result save(Evaluate evaluate) {
        Result result = ResultUtil.initResult();
        Constant.FACADE.getEvaluateDao().insert(evaluate);
        ResultUtil.setSuccess(result, "评价订单成功", evaluate);
        return result;
    }

    public List<Evaluate> listdesc(String tripid, String order) {
        Row row = new Row();
        row.put("tripid",tripid);
        row.put("order",order);
        return Constant.FACADE.getEvaluateDao().listdesc(row);
    }
}
