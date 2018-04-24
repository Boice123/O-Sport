package com.jsj141.osport.service;

import com.iw86.base.Row;
import com.jsj141.osport.config.Constant;
import com.jsj141.osport.domain.Clubactivity;
import com.jsj141.osport.domain.Trip;
import com.jsj141.osport.util.Result;
import com.jsj141.osport.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClubactivityService {
    private final Logger logger = LoggerFactory.getLogger(ClubactivityService.class);

    public Result save(Clubactivity clubactivity) {
        Result result = ResultUtil.initResult();
        Constant.FACADE.getClubactivityDao().insert(clubactivity);
        ResultUtil.setSuccess(result, "添加成功", clubactivity);
        return result;
    }

    public Result update(Clubactivity clubactivity) {
        Result result = ResultUtil.initResult();
        Constant.FACADE.getClubactivityDao().update(clubactivity);
        ResultUtil.setSuccess(result, "修改成功", clubactivity);
        return result;
    }

    public Result updatePeople(String clubactivityid) {
        Result result = ResultUtil.initResult();
        Constant.FACADE.getClubactivityDao().updatePeople(clubactivityid);
        ResultUtil.setSuccess(result, "修改Clubactivity人数信息成功");
        return result;
    }

    public Clubactivity get(Clubactivity clubactivity) {
        return (Clubactivity) Constant.FACADE.getClubactivityDao().select(clubactivity);
    }

    public List<Clubactivity> getByClubid(Clubactivity clubactivity) {
        return Constant.FACADE.getClubactivityDao().getByClubid(clubactivity);
    }

    public List<Clubactivity> listdesc(int page, int size, String order, String clubid) {
        Row row = new Row();
        if (page != -1) {
            row.put("start", page);
        }
        if (size != -1) {
            row.put("size", size);
        }
        if (!order.equals("")) {
            row.put("order", order);
        }
        if (!clubid.equals("")) {
            row.put("clubid", clubid);
        }
        return Constant.FACADE.getClubactivityDao().listdesc(row);
    }

    public Result delete(Clubactivity clubactivity) {
        Result result = ResultUtil.initResult();
        Constant.FACADE.getClubactivityDao().delete(clubactivity);
        ResultUtil.setSuccess(result, "删除clubactivity数据成功", null);
        return result;
    }
}
