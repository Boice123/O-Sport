package com.jsj141.osport.controller;

import com.jsj141.osport.domain.*;
import com.jsj141.osport.service.TriporderService;
import com.jsj141.osport.service.TripService;
import com.jsj141.osport.service.EvaluateService;
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
@RequestMapping("/evaluate")
public class EvaluateController {
    private final Logger logger = LoggerFactory.getLogger(EvaluateController.class);

    @Autowired
    private TriporderService triporderService;

    @Autowired
    private TripService tripService;

    @Autowired
    private EvaluateService evaluateService;

    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    Result save(@RequestParam(value="tripid") String tripid,
                @RequestParam(value="triporderid") String triporderid,
                @RequestParam(value="content") String content,
                Evaluate evaluate,
                Triporder triporder,
                BindingResult bindingResult,
                HttpServletRequest request) {
        Result result = ResultUtil.initResult();
        User loginUser = (User) WebUtils.getSessionAttribute(request, "loginUser");
        evaluate.setEvaluateid(UUID.randomUUID().toString());
        evaluate.setTripid(tripid);
        evaluate.setTriporderid(triporderid);
        evaluate.setUserid(loginUser.getUserid());
        evaluate.setContent(content);
        result = evaluateService.save(evaluate);
        triporder.setTriporderstatus("已评价");
        triporderService.update(triporder);
        return result;
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
     * 获取评价列表排列
     * @param tripid
     * @param order
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getCondition", method = RequestMethod.POST)
    Result getCondition(
                       @RequestParam(value="tripid") String tripid,
                       @RequestParam(value="order") String order,
                       HttpServletRequest request) {
        Result result = ResultUtil.initResult();
        List<Evaluate> evaluateList = evaluateService.listdesc(tripid, order);
        ResultUtil.setSuccess(result, "获得evaluateList列表排序信息成功", evaluateList);
        return result;
    }

    /**
     *  根据热度或者更新时间获取Trip
     */
    @ResponseBody
    @RequestMapping(value = "/getTripList", method = RequestMethod.POST)
    Result getTripList(
            @RequestParam(value="start") int start,
            @RequestParam(value="size") int size,
            @RequestParam(value="order") String order,
            HttpServletRequest request) {
        Result result = ResultUtil.initResult();
        List<Trip> tripList = tripService.listdesc(start, size, order);
        ResultUtil.setSuccess(result, "获得Trip列表排序信息成功", tripList);
        return result;
    }

    /**
     * 获得Trip列表,本站发起的
     * @param size: 获取多少条数据
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getWebTripPagination", method = RequestMethod.POST)
    Result getWebTripPagination(
            @RequestParam(value="start") int start,
            @RequestParam(value="size") int size,
            @RequestParam(value="shopid") String shopid,
            @RequestParam(value="order") String order,
            HttpServletRequest request) {
        Result result = ResultUtil.initResult();
        List<Trip> tripList = tripService.listdesc(start, size, shopid, order);
        ResultUtil.setSuccess(result, "获得Web Trip列表排序信息成功", tripList);
        return result;
    }

    /**
     * 根据地区和关键词获得Trip信息
     * @param tripprovice
     * @param tripcity
     * @param searchKey
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/searchKey", method = RequestMethod.POST)
    Result searchKey(@RequestParam(value="tripprovice") String tripprovice,
                     @RequestParam(value="tripcity") String tripcity,
                     @RequestParam(value="searchKey") String searchKey,
                     HttpServletRequest request) {
        Result result = ResultUtil.initResult();
        List<Trip> tripList = tripService.searchKey(tripprovice, tripcity, searchKey);
        ResultUtil.setSuccess(result, "根据地区和关键词获得Trip信息成功", tripList);
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
