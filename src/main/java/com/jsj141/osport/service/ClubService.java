package com.jsj141.osport.service;

import com.iw86.base.Row;
import com.jsj141.osport.config.Constant;
import com.jsj141.osport.domain.Club;
import com.jsj141.osport.domain.Triporder;
import com.jsj141.osport.util.Result;
import com.jsj141.osport.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClubService {
    private final Logger logger = LoggerFactory.getLogger(ClubService.class);

    public Result save(Club club) {
        Result result = ResultUtil.initResult();
        Constant.FACADE.getClubDao().insert(club);
        ResultUtil.setSuccess(result, "创建部落成功", club);
        return result;
    }

    public Result update(Club club) {
        Result result = ResultUtil.initResult();
        Constant.FACADE.getClubDao().update(club);
        ResultUtil.setSuccess(result, "修改club信息成功", club);
        return result;
    }

    public void delete(Club club) {
        Constant.FACADE.getClubDao().delete(club);
    }

    public Result updateClubPeople(Club club) {
        Result result = ResultUtil.initResult();
        Constant.FACADE.getClubDao().update(club);
        ResultUtil.setSuccess(result, "修改部落人数成功", null);
        return result;
    }

    public Club getByClubid(Club club) {
        Club getclub = (Club) Constant.FACADE.getClubDao().select(club);
        return getclub;
    }

    public List<Club> getUserJoinClub(String userid) {
        List<Club> clubList = Constant.FACADE.getClubDao().selectByUserid(userid);
        return clubList;
    }

    public List<Club> getAll() {
        List<Club> clubList = Constant.FACADE.getClubDao().selectAll();
        return clubList;
    }

    public List<Club> listdesc(int page, int size, String order) {
        Row row = new Row();
        row.put("start", page);
        row.put("size", size);
        row.put("order",order);
        return Constant.FACADE.getClubDao().listdesc(row);
    }
    public List<Club> getByClubtab(Club club) {
        List<Club> clubList = Constant.FACADE.getClubDao().selectByClubtab(club);
        return clubList;
    }


    public List<Club> searchKey(String searchKey) {
        Row row = new Row();
        searchKey = "'%" + searchKey + "%'";
        row.put("searchKey",searchKey);
        return Constant.FACADE.getClubDao().searchKey(row);
    }
}
