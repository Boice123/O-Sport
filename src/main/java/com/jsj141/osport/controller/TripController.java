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
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.jsj141.osport.domain.Trip;
import  com.jsj141.osport.service.TripService;
import org.springframework.web.util.WebUtils;

import java.util.UUID;
import java.util.List;

@Controller
@RequestMapping("/trip")
public class TripController {
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private TripService tripService;

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
                Trip trip,
                BindingResult bindingResult,
                HttpServletRequest request) {
        Result result = ResultUtil.initResult();
        Shop loginShop = (Shop) WebUtils.getSessionAttribute(request, "loginShop");
        trip.setTripid(UUID.randomUUID().toString());
        trip.setShopid(loginShop.getShopid());
        trip.setMaxpeople(maxpeople);
        trip.setTripdescription(tripdescription);
        trip.setTripimg(tripimg);
        trip.setTripnotice(tripnotice);
        trip.setTripname(tripname);
        trip.setTripprice(tripprice);
        result = tripService.save(trip);
        return result;
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
                Trip trip,
                BindingResult bindingResult,
                HttpServletRequest request) {
        Result result = ResultUtil.initResult();
        trip.setMaxpeople(maxpeople);
        trip.setTripdescription(tripdescription);
        trip.setTripimg(tripimg);
        trip.setTripnotice(tripnotice);
        trip.setTripname(tripname);
        trip.setTripprice(tripprice);
        result = tripService.update(trip);
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
    Result deleteTripInfo(String tripid, Trip trip, HttpServletRequest request) {
        Result result = ResultUtil.initResult();
        trip.setTripid(tripid);
        result = tripService.deleteTripInfo(trip);
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
}
