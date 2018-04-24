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

    public List<Clubdiary> getByClubid(Clubdiary clubdiary) {
        return Constant.FACADE.getClubdiaryDao().getByClubid(clubdiary);
    }

    public List<Clubdiary> getByClubowner(String clubowner) {
        return Constant.FACADE.getClubdiaryDao().getByClubowner(clubowner);
    }

    public List<Clubdiary> getCountByUserid(String userid) {
        return Constant.FACADE.getClubdiaryDao().getCountByUserid(userid);
    }

    public List<Clubdiary> getByUserid(int start, int size, String order, String userid) {
        Row row = new Row();
        if (start !=-1) {
            row.put("start", start);
        }
        if(size != -1) {
            row.put("size", size);
        }
        if(!order.equals("")) {
            row.put("order",order);
        }
        if(!userid.equals("")) {
            row.put("userid",userid);
        }
        return Constant.FACADE.getClubdiaryDao().getByUserid(row);
    }

    public Result delete(Clubdiary clubdiary) {
        Result result = ResultUtil.initResult();
        Constant.FACADE.getClubdiaryDao().delete(clubdiary);
        ResultUtil.setSuccess(result, "删除动态信息成功", null);
        return result;
    }

    public List<Clubdiary> listdesc(int page, int size, String order, String clubid) {
        Row row = new Row();
        if (page !=-1) {
            row.put("start", page);
        }
        if(size != -1) {
            row.put("size", size);
        }
        if(!order.equals("")) {
            row.put("order",order);
        }
        if(!clubid.equals("")) {
            row.put("clubid",clubid);
        }
        return Constant.FACADE.getClubdiaryDao().listdesc(row);
    }

}
