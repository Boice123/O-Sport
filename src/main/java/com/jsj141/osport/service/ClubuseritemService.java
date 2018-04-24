package com.jsj141.osport.service;

import com.jsj141.osport.config.Constant;
import com.jsj141.osport.domain.Club;
import com.jsj141.osport.domain.Clubuseritem;
import com.jsj141.osport.domain.Triporder;
import com.jsj141.osport.util.Result;
import com.jsj141.osport.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClubuseritemService {
    private final Logger logger = LoggerFactory.getLogger(ClubuseritemService.class);

    public Result save(Clubuseritem clubuseritem) {
        Result result = ResultUtil.initResult();
        Constant.FACADE.getClubuseritemDao().insert(clubuseritem);
        ResultUtil.setSuccess(result, "关注部落成功", clubuseritem);
        return result;
    }

    public void delete(Clubuseritem clubuseritem) {
        Constant.FACADE.getClubuseritemDao().delete(clubuseritem);
    }

    public Clubuseritem getUseridNClubid (Clubuseritem clubuseritem) {
        Clubuseritem getclubuseritem = Constant.FACADE.getClubuseritemDao().selectByUseridNClubid (clubuseritem);
        return getclubuseritem;
    }

    public List<Clubuseritem> getByClubid(String clubid) {
       List<Clubuseritem> getclubuseritem = Constant.FACADE.getClubuseritemDao().selectByClubid(clubid);
        return getclubuseritem;
    }
}
