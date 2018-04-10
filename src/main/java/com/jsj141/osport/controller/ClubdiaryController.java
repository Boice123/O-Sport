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
                @RequestParam(value="clubdiaryimg") String clubdiaryimg,
                Clubdiary clubdiary,
                BindingResult bindingResult,
                HttpServletRequest request) {
        Result lastResult = ResultUtil.initResult();

        User loginUser = (User) WebUtils.getSessionAttribute(request, "loginUser");
        clubdiary.setClubdiaryid(UUID.randomUUID().toString());
        clubdiary.setClubdiarytitle(clubdiarytitle);
        clubdiary.setClubdiarycontent(clubdiarycontent);
        clubdiary.setClubdiaryimg(clubdiaryimg);
        clubdiary.setUsername(loginUser.getUsername());
        clubdiary.setClubid(clubid);
        clubdiary.setUserid(loginUser.getUserid());
        clubdiary.setUsername(loginUser.getUsername());
        clubdiaryService.save(clubdiary);
        lastResult.setCode(0);
        lastResult.setMsg("添加成功");
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
     * 根据Clubid获取部落动态,分页
     * @param clubid
     * @param start
     * @param size
     * @param order
     * @param clubdiary
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getAllByClubidPaginaton", method = RequestMethod.POST)
    Result getAllByClubidPaginaton(
            @RequestParam(value="clubid") String clubid,
            @RequestParam(value="start") int start,
            @RequestParam(value="size") int size,
            @RequestParam(value="order") String order,
            Clubdiary clubdiary,
            HttpServletRequest request) {
        Result result = ResultUtil.initResult();
        List<Clubdiary> clubdiaryList= clubdiaryService.listdesc(start, size, order, clubid);
        if(clubdiaryList.size() != 0 ) {
            result.setCode(0);
            result.setData(clubdiaryList);
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
     * 获取部落全部动态
     * @param start
     * @param size
     * @param order
     * @param clubid
     * @param clubdiary
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getAllClubDiary", method = RequestMethod.POST)
    Result getAllClubDiary(
            @RequestParam(value = "start") int start,
            @RequestParam(value = "size") int size,
            @RequestParam(value = "order") String order,
            @RequestParam(value = "clubid") String clubid,
            Clubdiary clubdiary,
            HttpServletRequest request){
        Result result = ResultUtil.initResult();
        List<Clubdiary> clubDiaryList= clubdiaryService.listdesc(start, size, order, clubid);
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


    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    Result deleteTripInfo(@RequestParam(value="clubdiaryid") String clubdiaryid,
                          Clubdiary clubdiary,
                          HttpServletRequest request) {
        Result result = ResultUtil.initResult();

        clubdiary.setClubdiaryid(clubdiaryid);
        clubdiaryService.delete(clubdiary);
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
     * 批量删除CLubdiary
     * @param batchdelete
     * @param clubdiary
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/batchdelete", method = RequestMethod.POST)
    Result batchdeleteTripInfo(@RequestParam(value="batchdelete") String[] batchdelete,
                               Clubdiary clubdiary,
                               HttpServletRequest request) {
        Result result = ResultUtil.initResult();
        for(int i = 0;i< batchdelete.length; i++){
            String clubactivityid = batchdelete[i];
            clubdiary.setClubdiaryid(clubactivityid);
            clubdiaryService.delete(clubdiary);
        }
        result.setCode(0);
        result.setMsg("删除成功");
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
