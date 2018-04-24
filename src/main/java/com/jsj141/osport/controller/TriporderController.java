package com.jsj141.osport.controller;

import com.jsj141.osport.domain.*;
import com.jsj141.osport.service.TriporderService;
import com.jsj141.osport.service.TripService;
import com.jsj141.osport.service.UserService;
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
    private UserService userService;

    @Autowired
    private TriporderService triporderService;

    /**
     * 保存triporder信息
     * @param tripid
     * @param tripordertotal
     * @param people
     * @param trip
     * @param triporder
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    Result save(@RequestParam(value="tripid") String tripid,
                @RequestParam(value="tripordertotal") double tripordertotal,
                @RequestParam(value="people") int people,
                Trip trip,
                Triporder triporder,
                HttpServletRequest request) {
                Result lastResult = ResultUtil.initResult();
                Result result1 = ResultUtil.initResult();

                User loginUser = (User) WebUtils.getSessionAttribute(request, "loginUser");
                triporder.setTriporderid(UUID.randomUUID().toString());
                triporder.setTripordertotal(tripordertotal);
                triporder.setUserid(loginUser.getUserid());
                triporder.setPeople(people);
                triporder.setTripid(tripid);
                triporder.setTriporderstatus("未付款");
                triporderService.save(triporder);

                trip.setTripid(tripid);
                Trip tt = tripService.getTripInfo(trip);
                tt.setTriptrading(tripService.getTripInfo(trip).getTriptrading() + people);
                tt.setMaxpeople(tripService.getTripInfo(trip).getMaxpeople() - people);
                tripService.update(tt);

                lastResult.setCode(0);
                lastResult.setMsg("添加订单成功");
            return lastResult;
    }

    @ResponseBody
    @RequestMapping(value = "/cancel", method = RequestMethod.POST)
    Result cancel(@RequestParam(value="triporderid") String triporderid,
                Triporder triporder, Trip t) {
        Result lastResult = ResultUtil.initResult();
        triporder.setTriporderid(triporderid);
        triporder.setTriporderstatus("已取消");
        triporderService.update(triporder);

        //Trip maxpeople人数增加，triptrading报名数减少
        Triporder to = triporderService.getTriporderInfo(triporder);
        t.setTripid(to.getTripid());
        Trip tt = tripService.getTripInfo(t);
        tt.setMaxpeople(tt.getMaxpeople() + to.getPeople());
        tt.setTriptrading(tt.getTriptrading() - to.getPeople());
        tripService.update(tt);

        lastResult.setCode(0);
        lastResult.setMsg("取消订单成功");
        return lastResult;
    }

    @ResponseBody
    @RequestMapping(value = "/sure", method = RequestMethod.POST)
    Result sure(@RequestParam(value="triporderid") String triporderid,
                Triporder triporder,
                Trip trip) {
        Result lastResult = ResultUtil.initResult();
        triporder.setTriporderid(triporderid);
        triporder.setTriporderstatus("已付款");
        triporderService.update(triporder);

        //Trip tripsure付款数增加
        Triporder t = triporderService.getTriporderInfo(triporder);
        trip.setTripid(t.getTripid());
        Trip tt = tripService.getTripInfo(trip);
        tt.setTripsure(tt.getTripsure() + t.getPeople());
        tripService.update(tt)
;
        lastResult.setCode(0);
        lastResult.setMsg("确认订单付款成功");
        return lastResult;
    }

    @ResponseBody
    @RequestMapping(value = "/close", method = RequestMethod.POST)
    Result close(@RequestParam(value="triporderid") String triporderid,
                Triporder triporder,
                Trip trip) {
        Result lastResult = ResultUtil.initResult();
        triporder.setTriporderid(triporderid);
        triporder.setTriporderstatus("已关闭");
        triporderService.update(triporder);

        lastResult.setCode(0);
        lastResult.setMsg("关闭订单成功");
        return lastResult;
    }


    @ResponseBody
    @RequestMapping(value = "/listdesc", method = RequestMethod.POST)
    Result listdesc(@RequestParam(value="tripid") String tripid,
                    @RequestParam(value="userid") String userid,
                    @RequestParam(value="triporderstatus") String triporderstatus,
                    @RequestParam(value="start") int start,
                    @RequestParam(value="size") int size,
                    @RequestParam(value="order") String order,
                    Triporder triporder) {
        Result lastResult = ResultUtil.initResult();
        List<Triporder> triporderList = triporderService.listdesc(tripid, userid, triporderstatus, start, size, order);
        for(int i=0; i < triporderList.size(); i++) {
            //设置用户信息
            String uid = triporderList.get(i).getUserid();
            User u = new User();
            u.setUserid(uid);
            User user = userService.get(u);
            triporderList.get(i).setUser(user);
        }
        lastResult.setCode(0);
        lastResult.setMsg("获取订单成功");
        lastResult.setData(triporderList);
        return lastResult;
    }

    @ResponseBody
    @RequestMapping(value = "/count", method = RequestMethod.POST)
    Result count(@RequestParam(value="tripid") String tripid) {
        Result lastResult = ResultUtil.initResult();
        int count = triporderService.count(tripid);
        lastResult.setCode(0);
        lastResult.setMsg("获取订单数量成功");
        lastResult.setData(count);
        return lastResult;
    }
}
