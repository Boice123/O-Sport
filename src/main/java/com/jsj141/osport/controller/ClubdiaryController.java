package com.jsj141.osport.controller;

import com.jsj141.osport.domain.*;
import com.jsj141.osport.service.TripService;
import com.jsj141.osport.service.TriptimeService;
import com.jsj141.osport.service.ClubdiaryService;
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
@RequestMapping("/clubdiary")
public class ClubdiaryController {
    private final Logger logger = LoggerFactory.getLogger(ClubdiaryController.class);

    @Autowired
    private TripService tripService;

    @Autowired
    private TriptimeService triptimeService;

    @Autowired
    private ClubdiaryService clubdiaryService;


    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    Result save(@RequestParam(value="clubid") String clubid ,
                @RequestParam(value="clubdiarytitle") String clubdiarytitle ,
                @RequestParam(value="clubdiarycontent") String clubdiarycontent,
//                @RequestParam(value="clubdiaryimg") String clubdiaryimg,
                Clubdiary clubdiary,
                BindingResult bindingResult,
                HttpServletRequest request) {
        Result lastResult = ResultUtil.initResult();

        User loginUser = (User) WebUtils.getSessionAttribute(request, "loginUser");
        clubdiary.setClubdiaryid(UUID.randomUUID().toString());
        clubdiary.setClubdiarytitle(clubdiarytitle);
        clubdiary.setClubdiarycontent(clubdiarycontent);
        clubdiary.setUsername(loginUser.getUsername());
        clubdiary.setClubid(clubid);
        clubdiary.setUserid(loginUser.getUserid());
        clubdiaryService.save(clubdiary);
        lastResult.setCode(0);
        lastResult.setMsg("添加成功");
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
                Trip trip,
                  Triptime triptimeEntity,
                BindingResult bindingResult,
                HttpServletRequest request) {
        Result lastResult = ResultUtil.initResult();

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
     * 根据Clubid获取部落动态
     * @param clubid
     * @param clubdiary
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getAllByClubid", method = RequestMethod.POST)
    Result getAllByClubid(@RequestParam(value="clubid") String clubid,
                        Clubdiary clubdiary,
                        HttpServletRequest request) {
        Result result = ResultUtil.initResult();
        clubdiary.setClubid(clubid);
        List<Clubdiary> clubDiaryList= clubdiaryService.getByClubid(clubdiary);
        if(clubDiaryList.size() != 0 ) {
            result.setCode(0);
            result.setData(clubDiaryList);
            result.setMsg("获取部落全部动态成功");
        }else {
            result.setCode(1);
            result.setMsg("部落没有动态数据");
        }
        return result;
    }

    /**
     * 获取管理的部落全部动态
     * @param clubowner
     * @param club
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getOwnClubDiary", method = RequestMethod.POST)
    Result getOwnClubDiary(@RequestParam(value="clubowner") String clubowner,
                           Club club,
                           HttpServletRequest request){
        Result result = ResultUtil.initResult();
        List<Clubdiary> clubDiaryList= clubdiaryService.getByClubowner(clubowner);
        if(clubDiaryList.size() != 0 ) {
            result.setCode(0);
            result.setData(clubDiaryList);
            result.setMsg("获取管理的部落全部动态成功");
        }else {
            result.setCode(1);
            result.setMsg("管理的部落没有动态数据");
        }
        return result;
    }

    /**
     * 获取参与的部落全部动态
     * @param userid
     * @param club
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getJoinClubDiary", method = RequestMethod.POST)
    Result getJoinClubDiary(@RequestParam(value="userid") String userid,
                           Club club,
                           HttpServletRequest request){
        Result result = ResultUtil.initResult();
        List<Clubdiary> clubDiaryList= clubdiaryService.getByUserid(userid);
        if(clubDiaryList.size() != 0 ) {
            result.setCode(0);
            result.setData(clubDiaryList);
            result.setMsg("获取参与的部落全部动态成功");
        }else {
            result.setCode(1);
            result.setMsg("参与的部落没有动态数据");
        }
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
