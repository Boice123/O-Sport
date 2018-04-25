package com.jsj141.osport.controller;

import com.jsj141.osport.domain.*;
import com.jsj141.osport.service.ClubdiaryService;
import com.jsj141.osport.service.ClubactivityService;
import com.jsj141.osport.service.TripService;
import com.jsj141.osport.service.ClubService;
import com.jsj141.osport.service.ClubuseractivityService;
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
import java.util.*;

@Controller
@RequestMapping("/clubactivity")
public class ClubactivityController {
    private final Logger logger = LoggerFactory.getLogger(ClubactivityController.class);

    @Autowired
    private TripService tripService;

    @Autowired
    private ClubdiaryService clubdiaryService;

    @Autowired
    private ClubactivityService clubactivityService;

    @Autowired
    private ClubuseractivityService clubuseractivityService;

    @Autowired
    private ClubService clubService;


    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    Result save(@RequestParam(value = "clubid") String clubid,
                @RequestParam(value = "clubactivitytitle") String clubactivitytitle,
                @RequestParam(value = "clubactivitycontent") String clubactivitycontent,
                @RequestParam(value="clubactivityimg") String clubactivityimg,
                Clubactivity clubactivity) {
        Result lastResult = ResultUtil.initResult();
        clubactivity.setClubactivityid(UUID.randomUUID().toString());
        clubactivity.setClubactivityimg(clubactivityimg);
        clubactivity.setClubactivitycontent(clubactivitycontent);
        clubactivity.setClubactivitytitle(clubactivitytitle);
        clubactivity.setClubid(clubid);
        clubactivityService.save(clubactivity);
        lastResult.setCode(0);
        lastResult.setMsg("添加成功");
        return lastResult;
    }


    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    Result update(@RequestParam(value = "clubactivityid") String clubactivityid,
                  @RequestParam(value = "clubactivitytitle") String clubactivitytitle,
                  @RequestParam(value = "clubactivitycontent") String clubactivitycontent,
                  @RequestParam(value="clubactivityimg") String clubactivityimg,
                  Clubactivity clubactivity) {
        Result lastResult = ResultUtil.initResult();

        clubactivity.setClubactivityid(clubactivityid);
        clubactivity.setClubactivitycontent(clubactivitycontent);
        clubactivity.setClubactivitytitle(clubactivitytitle);
        clubactivity.setClubactivityimg(clubactivityimg);
        return clubactivityService.update(clubactivity);
    }

    /**
     * 根据clubactivityid获得clubactivity
     *
     * @param clubactivityid
     * @param clubactivity
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/get", method = RequestMethod.POST)
    Result get(
            @RequestParam(value = "clubactivityid") String clubactivityid,
            Clubactivity clubactivity) {
        Result result = ResultUtil.initResult();
        clubactivity.setClubactivityid(clubactivityid);
        Clubactivity ca = clubactivityService.get(clubactivity);
        if (ca != null) {
            result.setCode(0);
            result.setData(ca);
            result.setMsg("获取攻略信息成功");
        } else {
            result.setCode(1);
            result.setMsg("没有该攻略信息");
        }
        return result;
    }

    /**
     * 根据Clubid获取部落活动
     *
     * @param clubid
     * @param clubactivity
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getClubActivity", method = RequestMethod.POST)
    Result getClubActivity(@RequestParam(value = "clubid") String clubid,
                          Clubactivity clubactivity,
                          HttpServletRequest request) {
        Result result = ResultUtil.initResult();
        clubactivity.setClubid(clubid);
        List<Clubactivity> clubactivtyList = clubactivityService.getByClubid(clubactivity);
        result.setCode(0);
        result.setData(clubactivtyList);
        result.setMsg("获取部落全部攻略成功");
        return result;
    }

    /**
     * 根据Clubid获取部落活动,分页
     *
     * @param clubid
     * @param clubactivity
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getAllByClubidPaginaton", method = RequestMethod.POST)
    Result getAllByClubidPaginaton(
            @RequestParam(value = "clubid") String clubid,
            @RequestParam(value = "start") int start,
            @RequestParam(value = "size") int size,
            @RequestParam(value = "order") String order,
            Clubactivity clubactivity,
            HttpServletRequest request) {
        Result result = ResultUtil.initResult();
        List<Clubactivity> clubactivtyList = clubactivityService.listdesc(start, size, order, clubid);
        result.setCode(0);
        result.setData(clubactivtyList);
        result.setMsg("获取部落全部攻略成功");
        return result;
    }

    /**
     * 获取用户参与部落的攻略，排序，分页
     * @param userid
     * @param start
     * @param size
     * @param order
     * @param clubactivity
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getJoinClubActivity", method = RequestMethod.POST)
    Result getJoinClubActivity(
            @RequestParam(value = "userid") String userid,
            @RequestParam(value = "start") int start,
            @RequestParam(value = "size") int size,
            @RequestParam(value = "order") String order,
            Clubactivity clubactivity,
            Club club,
            HttpServletRequest request) {
            Result result = ResultUtil.initResult();
            List<Clubactivity> list= clubactivityService.getByUserid(start, size, order, userid);
//            for(int i=0; i < clubDiaryList.size(); i++) {
//                //设置Club信息
//                String cid = clubDiaryList.get(i).getClubid();
//                Club c = new Club();
//                c.setClubid(cid);
//                Club cc = clubService.getByClubid(c);
//                clubDiaryList.get(i).setClub(cc);
//                //设置用户信息
//                String uid = clubDiaryList.get(i).getUserid();
//                User u = new User();
//                u.setUserid(uid);
//                User user = userService.get(u);
//                clubDiaryList.get(i).setUser(user);
//                //设置评论信息
//                String cdid = clubDiaryList.get(i).getClubdiaryid();
//                List<Diaryfirsteval> evalList = diaryfirstevalService.listdesc(cdid, "evaltime");
//                for(int j=0; j < evalList.size(); j++) {
//                    //设置User信息
//                    String id = evalList.get(j).getUserid();
//                    User ue = new User();
//                    ue.setUserid(id);
//                    User uu = userService.get(ue);
//                    evalList.get(j).setUser(uu);
//                    //设置二级评论信息
//                    String firstevalid = evalList.get(j).getEvalid();
//    //            Diarysecondeval dsd = new Diarysecondeval();
//    //            dsd.setFirstevalid(eid);
//                    List<Diarysecondeval> list = diarysecondevalService.getByFirstevalid(firstevalid);
//                    evalList.get(j).setSecondevalList(list);
//                }
//                clubDiaryList.get(i).setDiaryfirsteval(evalList);
//            }
            result.setCode(0);
            result.setData(list);
            result.setMsg("获取参与的部落全部攻略成功");
            return result;
    }

    /**
     * 删除clubActivity信息
     *
     * @param clubactivityid
     * @param clubActivity
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    Result delete(@RequestParam(value = "clubactivityid") String clubactivityid,
                          Clubactivity clubActivity) {
        Result result = ResultUtil.initResult();

        clubActivity.setClubactivityid(clubactivityid);
        clubactivityService.delete(clubActivity);
//        clubuseractivityService.deleteByClubactivityid(clubactivityid);
        result.setCode(0);
        result.setMsg("删除成功");
        return result;
    }

    /**
     * 点赞clubActivity信息
     *
     * @param clubactivityid
     * @param clubActivity
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/like", method = RequestMethod.POST)
    Result like(@RequestParam(value = "clubactivityid") String clubactivityid,
                  Clubactivity clubActivity) {
        Result result = ResultUtil.initResult();

        clubActivity.setClubactivityid(clubactivityid);
        Clubactivity cc = clubactivityService.get(clubActivity);
        cc.setClubactivitypeople(cc.getClubactivitypeople() + 1);
        clubactivityService.update(cc);
        result.setCode(0);
        result.setMsg("点赞成功");
        return result;
    }

    /**
     * 批量删除CLubactivity信息
     *
     * @param batchdelete
     * @param batchdelete
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/batchdelete", method = RequestMethod.POST)
    Result batchdeleteTripInfo(@RequestParam(value = "batchdelete") String[] batchdelete,
                               Clubactivity clubactivity,
                               HttpServletRequest request) {
        Result result = ResultUtil.initResult();
        for (int i = 0; i < batchdelete.length; i++) {
            String clubactivityid = batchdelete[i];
            clubactivity.setClubactivityid(clubactivityid);
            clubactivityService.delete(clubactivity);
            clubuseractivityService.deleteByClubactivityid(clubactivityid);
        }
        result.setCode(0);
        result.setMsg("删除成功");
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/getAllClubActivity", method = RequestMethod.POST)
    Result getAllClubActivity(
            @RequestParam(value = "start") int start,
            @RequestParam(value = "size") int size,
            @RequestParam(value = "order") String order,
            @RequestParam(value = "clubid") String clubid,
            Clubactivity clubactivity,
            HttpServletRequest request) {
        Result result = ResultUtil.initResult();
        List<Clubactivity> clubactivtyList = clubactivityService.listdesc(start, size, order, clubid);
        result.setCode(0);
        result.setMsg("获取部落攻略成功");
        result.setData(clubactivtyList);
        return result;
    }
}