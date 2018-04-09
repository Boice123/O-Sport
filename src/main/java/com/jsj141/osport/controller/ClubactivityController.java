package com.jsj141.osport.controller;

import com.jsj141.osport.domain.*;
import com.jsj141.osport.service.ClubdiaryService;
import com.jsj141.osport.service.ClubactivityService;
import com.jsj141.osport.service.TripService;
import com.jsj141.osport.service.TriptimeService;
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
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/clubactivity")
public class ClubactivityController {
    private final Logger logger = LoggerFactory.getLogger(ClubactivityController.class);

    @Autowired
    private TripService tripService;

    @Autowired
    private TriptimeService triptimeService;

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
                Club club,
                Clubactivity clubactivity,
                BindingResult bindingResult,
                HttpServletRequest request) {
        Result lastResult = ResultUtil.initResult();

        User loginUser = (User) WebUtils.getSessionAttribute(request, "loginUser");

        //检验当前用户是否部落创建人才可以创建活动
        club.setClubid(clubid);
        Club getClub = clubService.getByClubid(club);
        if (getClub.getClubowner().equals(loginUser.getUserid())) {
            //创建活动
            clubactivity.setClubactivityid(UUID.randomUUID().toString());
            clubactivity.setClubactivitytitle(clubactivitytitle);
            clubactivity.setClubactivitycontent(clubactivitycontent);
            clubactivity.setClubactivityimg(clubactivityimg);
            clubactivity.setClubid(clubid);
            clubactivityService.save(clubactivity);
            lastResult.setCode(0);
            lastResult.setMsg("添加成功");
            return lastResult;
        }
        lastResult.setCode(1);
        lastResult.setMsg("添加失败，您不是部落创建人，没有权限添加部落活动");
        return lastResult;


    }


    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    Result update(@RequestParam(value = "clubactivityid") String clubactivityid,
                  @RequestParam(value = "clubactivitytitle") String clubactivitytitle,
                  @RequestParam(value = "clubactivitycontent") String clubactivitycontent,
                  @RequestParam(value="clubactivityimg") String clubactivityimg,
                  Clubactivity clubactivity,
                  BindingResult bindingResult,
                  HttpServletRequest request) {
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
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/get", method = RequestMethod.POST)
    Result getclubactivity(
            @RequestParam(value = "clubactivityid") String clubactivityid,
            Clubactivity clubactivity,
            HttpServletRequest request) {
        Result result = ResultUtil.initResult();
        clubactivity.setClubactivityid(clubactivityid);
        Clubactivity ca = clubactivityService.get(clubactivity);
        if (ca != null) {
            result.setCode(0);
            result.setData(ca);
            result.setMsg("获取活动成功");
        } else {
            result.setCode(1);
            result.setMsg("没有该活动数据");
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
    @RequestMapping(value = "/getAllByClubid", method = RequestMethod.POST)
    Result getAllByClubid(@RequestParam(value = "clubid") String clubid,
                          Clubactivity clubactivity,
                          HttpServletRequest request) {
        Result result = ResultUtil.initResult();
        clubactivity.setClubid(clubid);
        List<Clubactivity> clubactivtyList = clubactivityService.getByClubid(clubactivity);
        if (clubactivtyList.size() != 0) {
            result.setCode(0);
            result.setData(clubactivtyList);
            result.setMsg("获取部落全部活动成功");
        } else {
            result.setCode(1);
            result.setMsg("部落没有活动数据");
        }
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
//        clubactivity.setClubid(clubid);
        List<Clubactivity> clubactivtyList = clubactivityService.listdesc(start, size, order, clubid);
        if (clubactivtyList.size() != 0) {
            result.setCode(0);
            result.setData(clubactivtyList);
            result.setMsg("获取部落全部活动成功");
        } else {
            result.setCode(1);
            result.setMsg("部落没有活动数据");
        }
        return result;
    }

    /**
     * 删除clubActivity信息
     *
     * @param clubactivityid
     * @param clubActivity
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    Result deleteTripInfo(@RequestParam(value = "clubactivityid") String clubactivityid,
                          Clubactivity clubActivity,
                          HttpServletRequest request) {
        Result result = ResultUtil.initResult();

        clubActivity.setClubactivityid(clubactivityid);
        clubactivityService.delete(clubActivity);
        clubuseractivityService.deleteByClubactivityid(clubactivityid);
//        if(result1.getCode() == 0 && result2.getCode() == 0) {
        result.setCode(0);
        result.setMsg("删除成功");
//        } else {
//            result.setCode(1);
//            result.setMsg("删除失败");
//        }
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
        result.setMsg("获取clubactivtyList成功");
        result.setData(clubactivtyList);
        return result;
    }
}