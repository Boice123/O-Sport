package com.jsj141.osport.controller;

import com.jsj141.osport.util.Result;
import com.jsj141.osport.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

import com.jsj141.osport.domain.Trip;
import com.jsj141.osport.domain.Triporder;
import com.jsj141.osport.service.TripService;
import com.jsj141.osport.service.TriporderService;

import java.util.*;

@Controller
@RequestMapping("/trip")
public class TripController {
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private TripService tripService;

    @Autowired
    private TriporderService triporderService;

    /**
     * 保存Trip信息
     * @param tripname
     * @param tripdescription
     * @param tripnotice
     * @param tripprice
     * @param maxpeople
     * @param tripimg
     * @param trip
     * @param triptime
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    Result save(@RequestParam(value="tripname") String tripname,
                @RequestParam(value="tripdescription") String tripdescription,
                @RequestParam(value="tripnotice") String tripnotice,
                @RequestParam(value="tripprice") Double tripprice,
                @RequestParam(value="maxpeople") Integer maxpeople,
                @RequestParam(value="tripimg") String tripimg,
                @RequestParam(value="tripprovice") String tripprovice,
                @RequestParam(value="tripcity") String tripcity,
                @RequestParam(value="triptime") String triptime,
                Trip trip,
                HttpServletRequest request) {
        Result lastResult = ResultUtil.initResult();
        //将triptime为null设置成""
        if(triptime.equals("null")) {
            triptime = "";
        }
        //保存trip
        trip.setTripid(UUID.randomUUID().toString());
        trip.setMaxpeople(maxpeople);
        trip.setTripdescription(tripdescription);
        trip.setTripimg(tripimg);
        trip.setTripnotice(tripnotice);
        trip.setTripname(tripname);
        trip.setTripprice(tripprice);
        trip.setTripprovice(tripprovice);
        trip.setTripcity(tripcity);
        trip.setTriptime(triptime);
        trip.setTripstatus("发布中");
        tripService.save(trip);
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
     * @param triptime
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
                @RequestParam(value="triptime") String triptime,
                Trip trip,
                HttpServletRequest request) {
        Result lastResult = ResultUtil.initResult();
        //将triptime为null设置成""
        if(triptime.equals("null")) {
            triptime = "";
        }
        //更新Trip
        trip.setMaxpeople(maxpeople);
        trip.setTripdescription(tripdescription);
        trip.setTripimg(tripimg);
        trip.setTripnotice(tripnotice);
        trip.setTripname(tripname);
        trip.setTripprice(tripprice);
        trip.setTripprovice(tripprovice);
        trip.setTripcity(tripcity);
        trip.setTriptime(triptime);
        tripService.update(trip);
        lastResult.setCode(0);
        lastResult.setMsg("修改成功");
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
        Trip t = tripService.getTripInfo(trip);
        result.setCode(0);
        result.setData(t);
        result.setMsg("获取Trip信息成功");
        return result;
    }

    /**
     * 获取Trip发布中数量信息
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getCount", method = RequestMethod.POST)
    Result getCount(HttpServletRequest request) {
        Result result = ResultUtil.initResult();
        result = tripService.getTripCount();
        return result;
    }

    /**
     * 获取Trip已关闭数量信息
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getCountClose", method = RequestMethod.POST)
    Result getCountClose(HttpServletRequest request) {
        Result result = ResultUtil.initResult();
        result = tripService.getTripCountClose();
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
    Result delete(String tripid, Trip trip, HttpServletRequest request) {
        Result result = ResultUtil.initResult();
        trip.setTripid(tripid);
        result = tripService.deleteTripInfo(trip);
        return result;
    }

    /**
     * 关闭Trip信息
     * @param tripid
     * @param trip
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/close", method = RequestMethod.POST)
    Result close(String tripid, String tripstatus,Trip trip, HttpServletRequest request) {
        Result result = ResultUtil.initResult();
        trip.setTripid(tripid);
        Trip tt = tripService.getTripInfo(trip);
        tt.setTripstatus(tripstatus);
        result = tripService.update(tt);
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
    Result batchdelete(@RequestParam(value="batchdelete") String[] batchdelete,
                                Trip trip,
                                HttpServletRequest request) {
        Result result = ResultUtil.initResult();
        for (int i = 0; i < batchdelete.length; i++) {
            String tripid = batchdelete[i];
            trip.setTripid(tripid);
            tripService.deleteTripInfo(trip);
        }
        result.setCode(0);
        result.setMsg("删除成功");
        return result;
    }

    /**
     * 获得Trip列表,排序，分页，发布中
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getTripList", method = RequestMethod.POST)
    Result getTripList(
            @RequestParam(value="start") int start,
            @RequestParam(value="size") int size,
            @RequestParam(value="order") String order,
            Triporder triporder) {
        Result result = ResultUtil.initResult();
        List<Trip> tripList = tripService.listdesc(start, size, order);
        for(int i = 0;i< tripList.size(); i++) {
            String tripid = tripList.get(i).getTripid();
            triporder.setTripid(tripid);
            List<Triporder> list = triporderService.getTripCloseOrder(tripid, "已取消");
            tripList.get(i).setCloseOrderList(list);
        }
        ResultUtil.setSuccess(result, "获得Trip发布中列表排序信息成功", tripList);
        return result;
    }

    /**
     * 获得Trip列表,排序，分页，已关闭
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getTripCloseList", method = RequestMethod.POST)
    Result getTripCloseList(
            @RequestParam(value="start") int start,
            @RequestParam(value="size") int size,
            @RequestParam(value="order") String order,
            Triporder triporder,
            HttpServletRequest request) {
        Result result = ResultUtil.initResult();
        List<Trip> tripList = tripService.listdescClose(start, size, order);
        for(int i = 0;i< tripList.size(); i++) {
            String tripid = tripList.get(i).getTripid();
            triporder.setTripid(tripid);
            List<Triporder> list = triporderService.getTripCloseOrder(tripid, "已取消");
            tripList.get(i).setCloseOrderList(list);
        }
        ResultUtil.setSuccess(result, "获得Trip已关闭列表排序信息成功", tripList);
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
}
