package com.jsj141.osport.controller;

import com.jsj141.osport.domain.*;
import com.jsj141.osport.service.ClubdiaryService;
import com.jsj141.osport.service.ClubactivityService;
import com.jsj141.osport.service.TripService;
import com.jsj141.osport.service.TriptimeService;
import com.jsj141.osport.service.ClubService;
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
    private ClubService clubService;



    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    Result save(@RequestParam(value="clubid") String clubid ,
                @RequestParam(value="clubactivitytitle") String clubactivitytitle ,
                @RequestParam(value="clubactivitycontent") String clubactivitycontent,
//                @RequestParam(value="clubdiaryimg") String clubdiaryimg,
                Club club,
                Clubactivity clubactivity,
                BindingResult bindingResult,
                HttpServletRequest request) {
        Result lastResult = ResultUtil.initResult();

        User loginUser = (User) WebUtils.getSessionAttribute(request, "loginUser");

        //检验当前用户是否部落创建人才可以创建活动
        club.setClubid(clubid);
        Club getClub = clubService.getByClubid(club);
        if(getClub.getClubowner().equals(loginUser.getUserid())) {
            //创建活动
            clubactivity.setClubactivityid(UUID.randomUUID().toString());
            clubactivity.setClubactivitytitle(clubactivitytitle);
            clubactivity.setClubactivitycontent(clubactivitycontent);
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

    /**
     * 修改Trip信息
     * @param tripname
     * @param tripdescription
     * @param tripnotice
     * @param tripprice
     * @param maxpeople
     * @param tripimg
     * @param trip
     * @param bindingResult
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    Result update(@RequestParam(value="tripname") String tripname,
                @RequestParam(value="tripdescription") String tripdescription,
                @RequestParam(value="tripnotice") String tripnotice,
                @RequestParam(value="tripprice") Double tripprice,
                @RequestParam(value="maxpeople") Integer maxpeople,
                @RequestParam(value="tripimg") String tripimg,
                  @RequestParam(value="tripprovice") String tripprovice,
                  @RequestParam(value="tripcity") String tripcity,
                  @RequestParam(value="triptime1") String triptime1,
                  @RequestParam(value="triptime2") String triptime2,
                  @RequestParam(value="triptime3") String triptime3,
                  @RequestParam(value="triptime4") String triptime4,
                  @RequestParam(value="triptime5") String triptime5,
                  @RequestParam(value="triptimeid1") String triptimeid1,
                  @RequestParam(value="triptimeid2") String triptimeid2,
                  @RequestParam(value="triptimeid3") String triptimeid3,
                  @RequestParam(value="triptimeid4") String triptimeid4,
                  @RequestParam(value="triptimeid5") String triptimeid5,
                Trip trip,
                  Triptime triptimeEntity,
                BindingResult bindingResult,
                HttpServletRequest request) {
        Result lastResult = ResultUtil.initResult();

            lastResult.setCode(1);
            lastResult.setMsg("请不要选择重复的出行时间");
        return lastResult;
    }


    /**
     * 获取Trip信息
     * @param tripid
     * @param trip
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/get", method = RequestMethod.POST)
    Result getTripInfo(String tripid, Trip trip, HttpServletRequest request) {
        Result result = ResultUtil.initResult();
        trip.setTripid(tripid);
        result = tripService.getTripInfo(trip);
        return result;
    }

    /**
     * 根据Clubid获取部落活动
     * @param clubid
     * @param clubactivity
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getAllByClubid", method = RequestMethod.POST)
    Result getAllByClubid(@RequestParam(value="clubid") String clubid,
                       Clubactivity clubactivity,
                       HttpServletRequest request) {
        Result result = ResultUtil.initResult();
        clubactivity.setClubid(clubid);
        List<Clubactivity> clubactivtyList= clubactivityService.getByClubid(clubactivity);
        if(clubactivtyList.size() != 0 ) {
            result.setCode(0);
            result.setData(clubactivtyList);
            result.setMsg("获取部落全部活动成功");
        }else {
            result.setCode(1);
            result.setMsg("部落没有活动数据");
        }
        return result;
    }

    /**
     * 根据shopid，获取当前店铺的Trip数量信息
     * @param shopid
     * @param trip
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getCount", method = RequestMethod.POST)
    Result getCount(String shopid, Trip trip, HttpServletRequest request) {
        Result result = ResultUtil.initResult();
        result = tripService.getTripCount(shopid);
        return result;
    }

    /**
     * 删除Trip信息
     * @param tripid
     * @param trip
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    Result deleteTripInfo(String tripid, Trip trip, Triptime triptime, HttpServletRequest request) {
        Result result = ResultUtil.initResult();
        Result result1 = ResultUtil.initResult();
        Result result2 = ResultUtil.initResult();
        trip.setTripid(tripid);
        result1 = tripService.deleteTripInfo(trip);
        triptime.setTripid(tripid);
        result2 = triptimeService.deleteTriptimeInfo(triptime);
        if(result1.getCode() == 0 && result2.getCode() == 0) {
            result.setCode(0);
            result.setMsg("删除成功");
        } else {
            result.setCode(1);
            result.setMsg("删除失败");
        }
        return result;
    }

    /**
     * 批量删除Trip信息
     * @param batchdelete
     * @param trip
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/batchdelete", method = RequestMethod.POST)
    Result batchdeleteTripInfo(@RequestParam(value="batchdelete") String[] batchdelete,
                                Trip trip,
                                Triptime triptime,
                                HttpServletRequest request) {
        Result result = ResultUtil.initResult();
        for(int i = 0;i< batchdelete.length; i++){
            String tripid = batchdelete[i];
            trip.setTripid(tripid);
            triptime.setTripid(tripid);
            tripService.deleteTripInfo(trip);
            triptimeService.deleteTriptimeInfo(triptime);
        }
        result.setCode(0);
        result.setMsg("删除成功");
        return result;
    }


    /**
     * 获得Trip列表,按日期或成交量排序
     * @param size: 获取多少条数据
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getTripList", method = RequestMethod.POST)
    Result getTripList(@RequestParam(value="size") int size,
                       @RequestParam(value="order") String order,
                       HttpServletRequest request) {
        Result result = ResultUtil.initResult();
        List<Trip> tripList = tripService.listdesc(0, size, order);
        ResultUtil.setSuccess(result, "获得Trip列表排序信息成功", tripList);
        return result;
    }

    public String format(String time) {
        try {
            SimpleDateFormat sf = new SimpleDateFormat("EEE MMM dd hh:mm:ss z yyyy", Locale.ENGLISH);
            Date date = sf.parse(time);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            return df.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
