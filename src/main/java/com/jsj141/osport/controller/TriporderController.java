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
    @RequestMapping(value = "/cancel", method = RequestMethod.POST)
    Result cancel(@RequestParam(value="triporderid") String triporderid,
                Triporderitem triporderitem,
                Triporder triporder,
                Triptime triptime,
                BindingResult bindingResult,
                HttpServletRequest request) {
                Result lastResult = ResultUtil.initResult();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date now = new Date();
            triporder.setTriporderid(triporderid);
            Triporder order = triporderService.get(triporder);
            triporderitem.setTriporderitemid(order.getTriporderitemid());
            Triporderitem getTriporderitem = triporderitemService.get(triporderitem);
            triptime.setTriptimeid(getTriporderitem.getTriptimeid());
            Triptime tripArrange = triptimeService.get(triptime);
            if(tripArrange == null) {
                lastResult.setCode(1);
                lastResult.setMsg("对应的出行时间不存在");
            }
            System.out.println(sdf.parse(tripArrange.getTriptime()).getTime());
            System.out.println(sdf.parse(sdf.format(now)).getTime());
            System.out.println(sdf.parse(tripArrange.getTriptime()).getTime() - sdf.parse(sdf.format(now)).getTime());
            System.out.println(tripArrange.getTriptime());
            System.out.println(sdf.format(now));
            if((sdf.parse(tripArrange.getTriptime()).getTime() - sdf.parse(sdf.format(now)).getTime() >= 1000 * 60 * 60 * 24)) {
                //更改订单表
                triporder.setTriporderstatus("已取消");
                triporderService.update(triporder);
                //删除订单项
//                triporderitemService.deleteTriporderitemInfo(triporderitem);
                //恢复Trip可用人数
                triptime.setTriptimemaxpeople(tripArrange.getTriptimemaxpeople() + getTriporderitem.getPeople());
                triptimeService.update(triptime);
                lastResult.setCode(0);
                lastResult.setMsg("取消订单成功");

            }else if((sdf.parse(tripArrange.getTriptime()).getTime() - sdf.parse(sdf.format(now)).getTime()) < 0) {
                lastResult.setCode(1);
                lastResult.setMsg("订单信息已过期");
            }else{
                lastResult.setCode(1);
                lastResult.setMsg("取消订单时间距离出团时间不足一天，取消订单失败");
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        return lastResult;
    }

    @ResponseBody
    @RequestMapping(value = "/sure", method = RequestMethod.POST)
    Result sure(@RequestParam(value="triporderid") String triporderid,
                Triporderitem triporderitem,
                Triporder triporder,
                Triptime triptime,
                  BindingResult bindingResult,
                  HttpServletRequest request) {
        Result lastResult = ResultUtil.initResult();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date now = new Date();
            triporder.setTriporderid(triporderid);
            Triporder order = triporderService.get(triporder);
            triporderitem.setTriporderitemid(order.getTriporderitemid());
            Triporderitem getTriporderitem = triporderitemService.get(triporderitem);
            triptime.setTriptimeid(getTriporderitem.getTriptimeid());
            Triptime tripArrange = triptimeService.get(triptime);
            if (tripArrange == null) {
                lastResult.setCode(1);
                lastResult.setMsg("对应的出行时间不存在");
            }
            if((sdf.parse(tripArrange.getTriptime()).getTime() - sdf.parse(sdf.format(now)).getTime() >= 0)) {
                triporder.setTriporderid(triporderid);
                triporder.setTriporderstatus("已确认");
                triporderService.update(triporder);
                lastResult.setCode(0);
                lastResult.setMsg("确认订单成功");
            }else {
                lastResult.setCode(1);
                lastResult.setMsg("还没到出团时间，确认订单失败");
            }

        }catch(Exception e) {
            e.printStackTrace();
        }
        return lastResult;
    }

    @ResponseBody
    @RequestMapping(value = "/updateStatus", method = RequestMethod.POST)
    Result update(@RequestParam(value="triporderstatus") String tripname,
                BindingResult bindingResult,
                HttpServletRequest request) {
                Result lastResult = ResultUtil.initResult();
            lastResult.setCode(0);
            lastResult.setMsg("修改成功");
            lastResult.setCode(1);
            lastResult.setMsg("请不要选择重复的出行时间");
        return lastResult;
    }

    @ResponseBody
    @RequestMapping(value = "/shopTripOrderCount", method = RequestMethod.POST)
    Result shopTripOrderCount(
        @RequestParam(value="shopid") String shopid,
        HttpServletRequest request) {
            Result lastResult = ResultUtil.initResult();
            int count = triporderService.shopTripOrderCount(shopid);
            lastResult.setCode(0);
            lastResult.setData(count);
            lastResult.setMsg("获取订单总数成功");
            return lastResult;
    }

    @ResponseBody
    @RequestMapping(value = "/getWebAllPagination", method = RequestMethod.POST)
    Result getWebAllPagination(
            @RequestParam(value="start") int start,
            @RequestParam(value="size") int size,
            @RequestParam(value="order") String order,
            @RequestParam(value="shopid") String shopid,
            HttpServletRequest request) {
        Result lastResult = ResultUtil.initResult();
        List<Triporder> orderList = triporderService.listdesc(start, size, shopid, order);
        lastResult.setCode(0);
        lastResult.setData(orderList);
        lastResult.setMsg("获取Web所有出行订单成功");
        return lastResult;
    }

    @ResponseBody
    @RequestMapping(value = "/getShopAllPagination", method = RequestMethod.POST)
    Result getShopAllPagination(
            @RequestParam(value="start") int start,
            @RequestParam(value="size") int size,
            @RequestParam(value="order") String order,
            @RequestParam(value="shopid") String shopid,
            HttpServletRequest request) {
        Result lastResult = ResultUtil.initResult();
        List<Triporder> orderList = triporderService.listdescn(start, size, shopid, order);
        lastResult.setCode(0);
        lastResult.setData(orderList);
        lastResult.setMsg("获取Shop所有出行订单成功");
        return lastResult;
    }


}
