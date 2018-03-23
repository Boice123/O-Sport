package com.jsj141.osport.controller;

import com.jsj141.osport.domain.*;
import com.jsj141.osport.service.TriporderService;
import com.jsj141.osport.service.TriporderitemService;
import com.jsj141.osport.service.TripService;
import com.jsj141.osport.service.TriptimeService;
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
@RequestMapping("/triporder")
public class TriporderController {
    private final Logger logger = LoggerFactory.getLogger(TriporderController.class);

    @Autowired
    private TripService tripService;

    @Autowired
    private TriptimeService triptimeService;

    @Autowired
    private TriporderService triporderService;

    @Autowired
    private TriporderitemService triporderitemService;


    /**
     * 保存triporder信息
     * @param tripid
     * @param triptimeid
     * @param tripordertotal
     * @param people
     * @param trip
     * @param triporder
     * @param triporderitem
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    Result save(@RequestParam(value="tripid") String tripid,
                @RequestParam(value="triptimeid") String triptimeid,
                @RequestParam(value="tripordertotal") double tripordertotal,
                @RequestParam(value="people") int people,
                Trip trip,
                Triptime triptime,
                Triporder triporder,
                Triporderitem triporderitem,
                HttpServletRequest request) {
            Result lastResult = ResultUtil.initResult();
            Result result1 = ResultUtil.initResult();

            //保存tripordertem
            triporderitem.setTriporderitemid(UUID.randomUUID().toString());
            triporderitem.setPeople(people);
            triporderitem.setTripid(tripid);
            triporderitem.setTriptimeid(triptimeid);
            result1 = triporderitemService.save(triporderitem);

            if(result1.getCode() == 0) {
                //保存triporder
                User loginUser = (User) WebUtils.getSessionAttribute(request, "loginUser");
                triporder.setTriporderid(UUID.randomUUID().toString());
                triporder.setTriporderitemid(triporderitem.getTriporderitemid());
                triporder.setTripordertotal(tripordertotal);
                triporder.setUserid(loginUser.getUserid());
                triporder.setUsername(loginUser.getUsername());
                triporderService.save(triporder);

                //trip的triptrading成交量增加
                trip.setTripid(tripid);
                trip.setTriptrading(people);
                tripService.updateTripTrading(trip);

                triptime.setTriptimeid(triptimeid);
                triptime.setTriptimemaxpeople(people);
                triptimeService.updateTriptimemaxpeople(triptime);
                lastResult.setCode(0);
                lastResult.setMsg("添加订单成功");
            }else {
                lastResult.setCode(1);
                lastResult.setMsg("添加订单失败");
            }
            return lastResult;
    }

    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    Result update(@RequestParam(value="tripname") String tripname,
                BindingResult bindingResult,
                HttpServletRequest request) {
                Result lastResult = ResultUtil.initResult();
            lastResult.setCode(0);
            lastResult.setMsg("修改成功");
            lastResult.setCode(1);
            lastResult.setMsg("请不要选择重复的出行时间");
        return lastResult;
    }


    /**
//     * 获取Triporder信息
//     * @param tripid
//     * @param trip
//     * @param request
//     * @return
//     */
//    @ResponseBody
//    @RequestMapping(value = "/get", method = RequestMethod.POST)
//    Result getTripInfo(String tripid, Trip trip, HttpServletRequest request) {
//        Result result = ResultUtil.initResult();
//        trip.setTripid(tripid);
//        result = triporderService.getTripInfo(trip);
//        return result;
//    }

    /**
     * 根据shopid，获取当前店铺的Trip数量信息
     * @param shopid
     * @param trip
     * @param request
     * @return
     */
//    @ResponseBody
//    @RequestMapping(value = "/getCount", method = RequestMethod.POST)
//    Result getCount(String shopid, Trip trip, HttpServletRequest request) {
//        Result result = ResultUtil.initResult();
//        result = triporderService.getTripCount(shopid);
//        return result;
//    }

    /**
     * 删除Trip信息
     * @param tripid
     * @param trip
     * @param request
     * @return
//     */
//    @ResponseBody
//    @RequestMapping(value = "/delete", method = RequestMethod.POST)
//    Result deleteTripInfo(String tripid, Trip trip, Triptime triptime, HttpServletRequest request) {
//        Result result = ResultUtil.initResult();
//        Result result1 = ResultUtil.initResult();
//        Result result2 = ResultUtil.initResult();
//        trip.setTripid(tripid);
//        result1 = triporderService.deleteTripInfo(trip);
//        triptime.setTripid(tripid);
//        result2 = triporderService.deleteTriptimeInfo(triptime);
//        if(result1.getCode() == 0 && result2.getCode() == 0) {
//            result.setCode(0);
//            result.setMsg("删除成功");
//        } else {
//            result.setCode(1);
//            result.setMsg("删除失败");
//        }
//        return result;
//    }

    /**
     * 批量删除Trip信息
     * @param batchdelete
     * @param trip
     * @param request
//     * @return
//     */
//    @ResponseBody
//    @RequestMapping(value = "/batchdelete", method = RequestMethod.POST)
//    Result batchdeleteTripInfo(@RequestParam(value="batchdelete") String[] batchdelete,
//                                Trip trip,
//                                Triptime triptime,
//                                HttpServletRequest request) {
//        Result result = ResultUtil.initResult();
//        for(int i = 0;i< batchdelete.length; i++){
//            String tripid = batchdelete[i];
//            trip.setTripid(tripid);
//            triptime.setTripid(tripid);
//            tripService.deleteTripInfo(trip);
//            triptimeService.deleteTriptimeInfo(triptime);
//        }
//        result.setCode(0);
//        result.setMsg("删除成功");
//        return result;
//    }


    /**
     * 获得Trip列表,按日期或成交量排序
     * @param size: 获取多少条数据
     * @param request
     * @return
     */
//    @ResponseBody
//    @RequestMapping(value = "/getTripList", method = RequestMethod.POST)
//    Result getTripList(@RequestParam(value="size") int size,
//                       @RequestParam(value="order") String order,
//                       HttpServletRequest request) {
//        Result result = ResultUtil.initResult();
//        List<Trip> tripList = tripService.listdesc(0, size, order);
//        ResultUtil.setSuccess(result, "获得Trip列表排序信息成功", tripList);
//        return result;
//    }
//
//    public String format(String time) {
//        try {
//            SimpleDateFormat sf = new SimpleDateFormat("EEE MMM dd hh:mm:ss z yyyy", Locale.ENGLISH);
//            Date date = sf.parse(time);
//            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//            return df.format(date);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "";
//    }
}
