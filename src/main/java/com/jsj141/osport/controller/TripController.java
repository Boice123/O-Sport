package com.jsj141.osport.controller;

import com.jsj141.osport.domain.Shop;
import com.jsj141.osport.util.Result;
import com.jsj141.osport.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

import com.jsj141.osport.domain.Trip;
import com.jsj141.osport.domain.Triptime;
import com.jsj141.osport.service.TripService;
import com.jsj141.osport.service.TriptimeService;
import org.springframework.web.util.WebUtils;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.SimpleFormatter;

@Controller
@RequestMapping("/trip")
public class TripController {
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private TripService tripService;

    @Autowired
    private TriptimeService triptimeService;

    /**
     * 保存Trip信息
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
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    Result save(@RequestParam(value="tripname") String tripname,
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
                Trip trip,
                Triptime triptimeEntity,
                BindingResult bindingResult,
                HttpServletRequest request) {
        Result lastResult = ResultUtil.initResult();
        Shop loginShop = (Shop) WebUtils.getSessionAttribute(request, "loginShop");
        //将triptime为null全都设置成""
        if(triptime1.equals("null")) {
            triptime1 = "";
        }
        if(triptime2.equals("null")) {
            triptime2 = "";
        }
        if(triptime3.equals("null")) {
            triptime3 = "";
        }
        if(triptime4.equals("null")) {
            triptime4 = "";
        }
        if(triptime5.equals("null")) {
            triptime5 = "";
        }
        //检查是否有重复的
        Set<String> set = new HashSet<String>();
        int count =0;
        if(!triptime1.equals("")) {
            set.add(triptime1);
            count += 1;
        }
        if(!triptime2.equals("")) {
            set.add(triptime2);
            count += 1;
        }
        if(!triptime3.equals("")) {
            set.add(triptime3);
            count += 1;
        }
        if(!triptime4.equals("")) {
            set.add(triptime4);
            count += 1;
        }
        if(!triptime5.equals("")) {
            set.add(triptime5);
            count += 1;
        }
        if(set.size() == count){
            //保存trip
            trip.setTripid(UUID.randomUUID().toString());
            trip.setShopid(loginShop.getShopid());
            trip.setMaxpeople(maxpeople);
            trip.setTripdescription(tripdescription);
            trip.setTripimg(tripimg);
            trip.setTripnotice(tripnotice);
            trip.setTripname(tripname);
            trip.setTripprice(tripprice);
            trip.setTripprovice(tripprovice);
            trip.setTripcity(tripcity);
            tripService.save(trip);
            //保存triptime
            triptimeEntity.setTriptimeid(UUID.randomUUID().toString());
            triptimeEntity.setTriptime(triptime1);
            triptimeEntity.setTripid(trip.getTripid());
            triptimeEntity.setTriptimemaxpeople(maxpeople);
            triptimeService.save(triptimeEntity);

            triptimeEntity.setTriptimeid(UUID.randomUUID().toString());
            triptimeEntity.setTriptime(triptime2);
            triptimeEntity.setTripid(trip.getTripid());
            triptimeEntity.setTriptimemaxpeople(maxpeople);
            triptimeService.save(triptimeEntity);

            triptimeEntity.setTriptimeid(UUID.randomUUID().toString());
            triptimeEntity.setTriptime(triptime3);
            triptimeEntity.setTripid(trip.getTripid());
            triptimeEntity.setTriptimemaxpeople(maxpeople);
            triptimeService.save(triptimeEntity);

            triptimeEntity.setTriptimeid(UUID.randomUUID().toString());
            triptimeEntity.setTriptime(triptime4);
            triptimeEntity.setTripid(trip.getTripid());
            triptimeEntity.setTriptimemaxpeople(maxpeople);
            triptimeService.save(triptimeEntity);

            triptimeEntity.setTriptimeid(UUID.randomUUID().toString());
            triptimeEntity.setTriptime(triptime5);
            triptimeEntity.setTripid(trip.getTripid());
            triptimeEntity.setTriptimemaxpeople(maxpeople);
            triptimeService.save(triptimeEntity);
            lastResult.setCode(0);
            lastResult.setMsg("添加成功");
        }else{
            lastResult.setCode(1);
            lastResult.setMsg("请不要选择重复的出行时间");
        }
        return lastResult;
    }

    /**
     * 保存Trip信息
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
    @RequestMapping(value = "/adminsave", method = RequestMethod.POST)
    Result adminsave(@RequestParam(value="tripname") String tripname,
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
                @RequestParam(value="shopid") String shopid,
                Trip trip,
                Triptime triptimeEntity,
                BindingResult bindingResult,
                HttpServletRequest request) {
        Result lastResult = ResultUtil.initResult();
        Shop loginShop = (Shop) WebUtils.getSessionAttribute(request, "loginShop");
        //将triptime为null全都设置成""
        if(triptime1.equals("null")) {
            triptime1 = "";
        }
        if(triptime2.equals("null")) {
            triptime2 = "";
        }
        if(triptime3.equals("null")) {
            triptime3 = "";
        }
        if(triptime4.equals("null")) {
            triptime4 = "";
        }
        if(triptime5.equals("null")) {
            triptime5 = "";
        }
        //检查是否有重复的
        Set<String> set = new HashSet<String>();
        int count =0;
        if(!triptime1.equals("")) {
            set.add(triptime1);
            count += 1;
        }
        if(!triptime2.equals("")) {
            set.add(triptime2);
            count += 1;
        }
        if(!triptime3.equals("")) {
            set.add(triptime3);
            count += 1;
        }
        if(!triptime4.equals("")) {
            set.add(triptime4);
            count += 1;
        }
        if(!triptime5.equals("")) {
            set.add(triptime5);
            count += 1;
        }
        if(set.size() == count){
            //保存trip
            trip.setTripid(UUID.randomUUID().toString());
            trip.setShopid(shopid);
            trip.setMaxpeople(maxpeople);
            trip.setTripdescription(tripdescription);
            trip.setTripimg(tripimg);
            trip.setTripnotice(tripnotice);
            trip.setTripname(tripname);
            trip.setTripprice(tripprice);
            trip.setTripprovice(tripprovice);
            trip.setTripcity(tripcity);
            tripService.save(trip);
            //保存triptime
            triptimeEntity.setTriptimeid(UUID.randomUUID().toString());
            triptimeEntity.setTriptime(triptime1);
            triptimeEntity.setTripid(trip.getTripid());
            triptimeEntity.setTriptimemaxpeople(maxpeople);
            triptimeService.save(triptimeEntity);

            triptimeEntity.setTriptimeid(UUID.randomUUID().toString());
            triptimeEntity.setTriptime(triptime2);
            triptimeEntity.setTripid(trip.getTripid());
            triptimeEntity.setTriptimemaxpeople(maxpeople);
            triptimeService.save(triptimeEntity);

            triptimeEntity.setTriptimeid(UUID.randomUUID().toString());
            triptimeEntity.setTriptime(triptime3);
            triptimeEntity.setTripid(trip.getTripid());
            triptimeEntity.setTriptimemaxpeople(maxpeople);
            triptimeService.save(triptimeEntity);

            triptimeEntity.setTriptimeid(UUID.randomUUID().toString());
            triptimeEntity.setTriptime(triptime4);
            triptimeEntity.setTripid(trip.getTripid());
            triptimeEntity.setTriptimemaxpeople(maxpeople);
            triptimeService.save(triptimeEntity);

            triptimeEntity.setTriptimeid(UUID.randomUUID().toString());
            triptimeEntity.setTriptime(triptime5);
            triptimeEntity.setTripid(trip.getTripid());
            triptimeEntity.setTriptimemaxpeople(maxpeople);
            triptimeService.save(triptimeEntity);
            lastResult.setCode(0);
            lastResult.setMsg("添加成功");
        }else{
            lastResult.setCode(1);
            lastResult.setMsg("请不要选择重复的出行时间");
        }
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
        //将triptime为null全都设置成""
        if(triptime1.equals("null")) {
            triptime1 = "";
        }
        if(triptime2.equals("null")) {
            triptime2 = "";
        }
        if(triptime3.equals("null")) {
            triptime3 = "";
        }
        if(triptime4.equals("null")) {
            triptime4 = "";
        }
        if(triptime5.equals("null")) {
            triptime5 = "";
        }
        //检查是否有重复的
        Set<String> set = new HashSet<String>();
        int count =0;
        if(!triptime1.equals("")) {
            set.add(triptime1);
            count += 1;
        }
        if(!triptime2.equals("")) {
            set.add(triptime2);
            count += 1;
        }
        if(!triptime3.equals("")) {
            set.add(triptime3);
            count += 1;
        }
        if(!triptime4.equals("")) {
            set.add(triptime4);
            count += 1;
        }
        if(!triptime5.equals("")) {
            set.add(triptime5);
            count += 1;
        }
        if(set.size() == count) {
            //更新Trip
            trip.setMaxpeople(maxpeople);
            trip.setTripdescription(tripdescription);
            trip.setTripimg(tripimg);
            trip.setTripnotice(tripnotice);
            trip.setTripname(tripname);
            trip.setTripprice(tripprice);
            trip.setTripprovice(tripprovice);
            trip.setTripcity(tripcity);
            tripService.update(trip);
            //更新Triptime
            triptimeEntity.setTriptimeid(triptimeid1);
            triptimeEntity.setTriptime(triptime1);
            triptimeEntity.setTripid(trip.getTripid());
            triptimeEntity.setTriptimemaxpeople(maxpeople);
            triptimeService.update(triptimeEntity);

            triptimeEntity.setTriptimeid(triptimeid2);
            triptimeEntity.setTriptime(triptime2);
            triptimeEntity.setTripid(trip.getTripid());
            triptimeEntity.setTriptimemaxpeople(maxpeople);
            triptimeService.update(triptimeEntity);

            triptimeEntity.setTriptimeid(triptimeid3);
            triptimeEntity.setTriptime(triptime3);
            triptimeEntity.setTripid(trip.getTripid());
            triptimeEntity.setTriptimemaxpeople(maxpeople);
            triptimeService.update(triptimeEntity);

            triptimeEntity.setTriptimeid(triptimeid4);
            triptimeEntity.setTriptime(triptime4);
            triptimeEntity.setTripid(trip.getTripid());
            triptimeEntity.setTriptimemaxpeople(maxpeople);
            triptimeService.update(triptimeEntity);

            triptimeEntity.setTriptimeid(triptimeid5);
            triptimeEntity.setTriptime(triptime5);
            triptimeEntity.setTripid(trip.getTripid());
            triptimeEntity.setTriptimemaxpeople(maxpeople);
            triptimeService.update(triptimeEntity);
            lastResult.setCode(0);
            lastResult.setMsg("修改成功");
        }else{
            lastResult.setCode(1);
            lastResult.setMsg("请不要选择重复的出行时间");
        }
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
     * 获得Trip列表,商家入住的
     * @param size: 获取多少条数据
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getShopAllPagination", method = RequestMethod.POST)
    Result getShopAllPagination(
                       @RequestParam(value="start") int start,
                       @RequestParam(value="size") int size,
                       @RequestParam(value="shopid") String shopid,
                       @RequestParam(value="order") String order,
                       HttpServletRequest request) {
        Result result = ResultUtil.initResult();
        List<Trip> tripList = tripService.listdescn(start, size, shopid, order);
        ResultUtil.setSuccess(result, "获得Shop Trip列表排序信息成功", tripList);
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
