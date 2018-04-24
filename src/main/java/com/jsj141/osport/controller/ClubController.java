package com.jsj141.osport.controller;
import com.jsj141.osport.domain.Club;
import com.jsj141.osport.domain.Clubdiary;
import com.jsj141.osport.domain.Clubactivity;
import com.jsj141.osport.domain.Clubuseritem;
import com.jsj141.osport.service.ClubService;
import com.jsj141.osport.service.ClubdiaryService;
import com.jsj141.osport.service.ClubactivityService;
import com.jsj141.osport.service.ClubuseritemService;
import com.jsj141.osport.util.Result;
import com.jsj141.osport.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/club")
public class ClubController {
    private final Logger logger = LoggerFactory.getLogger(ClubController.class);

    @Autowired
    private ClubService clubService;

    @Autowired
    private ClubdiaryService clubdiaryService;

    @Autowired
    private ClubactivityService clubactivityService;

    @Autowired
    private ClubuseritemService clubuseritemService;

    /**
     * 保存Club信息
     *
     * @param clubname
     * @param clubtab
     * @param club
     * @param bindingResult
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    Result save(@RequestParam(value = "clubname") String clubname,
                @RequestParam(value = "clubimg") String clubimg,
                @RequestParam(value = "clubtab") String clubtab,
                Club club,
                BindingResult bindingResult,
                HttpServletRequest request) {
        Result result = ResultUtil.initResult();
        try {
            club.setClubid(UUID.randomUUID().toString());
            club.setClubname(clubname);
            club.setClubtab(clubtab);
            club.setClubimg(clubimg);
            result = clubService.save(club);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 修改Club信息
     * @param clubid
     * @param clubname
     * @param club
     * @param bindingResult
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateClub", method = RequestMethod.POST)
    Result updateClub(
                @RequestParam(value="clubid") String clubid,
                @RequestParam(value="clubname") String clubname,
                @RequestParam(value="clubimg") String clubimg,
                Club club,
                BindingResult bindingResult,
                HttpServletRequest request) {
        Result result = ResultUtil.initResult();
        try{
            club.setClubid(clubid);
            club.setClubname(clubname);
            club.setClubimg(clubimg);
            result = clubService.update(club);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    Result delete(@RequestParam(value = "clubid") String clubid,
                     Club club,
                     HttpServletRequest request) {
        Result result = ResultUtil.initResult();
        club.setClubid(clubid);
        clubService.delete(club);
        ResultUtil.setSuccess(result, "删除部落成功", null);
        return result;
    }

    /**
     * 根据Clubid返回Club信息
     * @param clubid
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getClubByClubid", method = RequestMethod.POST)
    Result getShopInfo(
            @RequestParam(value = "clubid") String clubid,
            Club club,
            HttpServletRequest request) {
        Result result = ResultUtil.initResult();
        club.setClubid(clubid);
        Club getclub = (Club) clubService.getByClubid(club);
        if (getclub != null) {
            result.setCode(0);
            result.setData(getclub);
            result.setMsg("获取部落数据成功");
        } else {
            result.setCode(1);
            result.setMsg("部落数据不存在");
        }
        return result;
    }

    /**
     * 获取所有部落信息
     * @param club
     * @param bindingResult
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getAll", method = RequestMethod.POST)
    Result getAll(
            Club club,
            BindingResult bindingResult,
            HttpServletRequest request) {
        Result result = ResultUtil.initResult();
        List<Club> clubList = clubService.getAll();
        for(int i = 0 ;i< clubList.size();i++) {
            String clubid = clubList.get(i).getClubid();
            List<Clubdiary> list = clubdiaryService.listdesc(-1, -1, "clubdiarytime",clubid);
            clubList.get(i).setClubdiaryList(list);
            // 设置攻略
            List<Clubactivity> aclist = clubactivityService.listdesc(-1, -1, "clubactivitytime",clubid);
            clubList.get(i).setClubactivityList(aclist);
        }
        if (clubList.size() != 0) {
            result.setCode(0);
            result.setData(clubList);
            result.setMsg("获取全部部落信息成功");
        } else {
            result.setCode(1);
            result.setMsg("不存在任何一条部落信息");
        }
        return result;
    }

    /**
     * 获取所有部落信息，带有排序的
     * @param start
     * @param size
     * @param order
     * @param club
     * @param bindingResult
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getAllOrder", method = RequestMethod.POST)
    Result getAll(
            @RequestParam(value = "start") int start,
            @RequestParam(value = "size") int size,
            @RequestParam(value = "order") String order,
            Club club,
            BindingResult bindingResult,
            HttpServletRequest request) {
        Result result = ResultUtil.initResult();
        List<Club> clubList = clubService.listdesc(start, size, order);
        for(int i = 0 ;i< clubList.size();i++) {
            // 设置动态
            String clubid = clubList.get(i).getClubid();
            List<Clubdiary> list = clubdiaryService.listdesc(-1, -1, "clubdiarytime",clubid);
            clubList.get(i).setClubdiaryList(list);
            // 设置攻略
            List<Clubactivity> aclist = clubactivityService.listdesc(-1, -1, "clubactivitytime",clubid);
            clubList.get(i).setClubactivityList(aclist);
        }
        if (clubList.size() != 0) {
            result.setCode(0);
            result.setData(clubList);
            result.setMsg("获取全部部落排序信息成功");
        } else {
            result.setCode(1);
            result.setMsg("不存在任何一条部落信息");
        }
        return result;
    }

    /**
     * 根据部落标签获取相应的部落
     *
     * @param clubtab
     * @param club
     * @param bindingResult
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getByClubtab", method = RequestMethod.POST)
    Result getByClubtab(
            @RequestParam(value = "clubtab") String clubtab,
            Club club,
            BindingResult bindingResult,
            HttpServletRequest request) {
        Result result = ResultUtil.initResult();
        club.setClubtab(clubtab);
        List<Club> clubList = clubService.getByClubtab(club);
        if (clubList.size() != 0) {
            result.setCode(0);
            result.setData(clubList);
            result.setMsg("获取对应标签部落信息成功");
        } else {
            result.setCode(1);
            result.setMsg("暂时还没有您选择的部落信息");
        }
        return result;
    }

    /**
     * 根据关键词获得Club信息
     *
     * @param searchKey
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/searchKey", method = RequestMethod.POST)
    Result searchKey(@RequestParam(value = "searchKey") String searchKey,
                     HttpServletRequest request) {
        Result result = ResultUtil.initResult();
        List<Club> clubList = clubService.searchKey(searchKey);
        ResultUtil.setSuccess(result, "根据关键词获得Club信息成功", clubList);
        return result;
    }

    /**
     * 加入部落
     * @param userid
     * @param clubid
     * @param club
     * @param clubuseritem
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/joinClub", method = RequestMethod.POST)
    Result joinClub(@RequestParam(value = "userid") String userid,
                    @RequestParam(value = "clubid") String clubid,
                     Club club,
                     Clubuseritem clubuseritem,
                     HttpServletRequest request) {
        Result result = ResultUtil.initResult();
        //判断用户是否已经加入过该部落
        clubuseritem.setClubid(clubid);
        clubuseritem.setUserid(userid);
        Clubuseritem c = clubuseritemService.getUseridNClubid(clubuseritem);
        if(c != null) {
            result.setCode(1);
            result.setMsg("您已经关注过该部落");
            return result;
        }
        //部落人数加一
        club.setClubid(clubid);
        Club cc = clubService.getByClubid(club);
        cc.setClubpeople(cc.getClubpeople() + 1);
        clubService.updateClubPeople(cc);
        //建立用户和部落之间的联系
        clubuseritem.setClubuseritemid(UUID.randomUUID().toString());
        clubuseritem.setClubid(clubid);
        clubuseritem.setUserid(userid);
        clubuseritemService.save(clubuseritem);

        ResultUtil.setSuccess(result, "关注部落成功", null);
        return result;
    }

    /**
     * 退出部落
     * @param clubid
     * @param club
     * @param clubuseritem
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/exitClub", method = RequestMethod.POST)
    Result exitClub(@RequestParam(value = "userid") String userid,
                    @RequestParam(value = "clubid") String clubid,
                    Club club,
                    Clubuseritem clubuseritem,
                    HttpServletRequest request) {
        Result result = ResultUtil.initResult();
        //删除用户和部落之间的联系
        clubuseritem.setUserid(userid);
        clubuseritem.setClubid(clubid);
        Clubuseritem cu = clubuseritemService.getUseridNClubid(clubuseritem);
        clubuseritemService.delete(cu);
        //部落人数减一
        club.setClubid(clubid);
        Club cc = clubService.getByClubid(club);
        cc.setClubpeople(cc.getClubpeople() - 1);
        clubService.updateClubPeople(cc);

        ResultUtil.setSuccess(result, "退出部落成功", null);
        return result;
    }
}
