package com.jsj141.osport.controller;

import com.jsj141.osport.domain.Shop;
import com.jsj141.osport.domain.Trip;
import com.jsj141.osport.domain.Triptime;
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
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

@Controller
@RequestMapping("/triptime")
public class TriptimeController {
    private final Logger logger = LoggerFactory.getLogger(UserController.class);


    @Autowired
    private TriptimeService triptimeService;

    /**
     * 获取Triptime信息
     * @param tripid
     * @param triptime
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/get", method = RequestMethod.POST)
    Result getTriptimeInfo(String tripid, Triptime triptime, HttpServletRequest request) {
        Result result = ResultUtil.initResult();
        triptime.setTripid(tripid);
        List<Triptime> triptimeList = triptimeService.getTriptimeInfo(triptime);
        if(triptimeList.size() == 0) {
            result.setCode(1);
            result.setMsg("Triptime数据为空");
        }else {
            ResultUtil.setSuccess(result, "获取Triptime信息成功", triptimeList);
        }
        return result;
    }

    /**
     * 检查Triptime信息是否都是null或者空字符串
     * @param triptime1
     * @param triptime2
     * @param triptime3
     * @param triptime4
     * @param triptime5
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/checkTriptimeNull", method = RequestMethod.POST)
    Result checkTriptimeNull(@RequestParam(value="triptime1") String triptime1,
                             @RequestParam(value="triptime2") String triptime2,
                             @RequestParam(value="triptime3") String triptime3,
                             @RequestParam(value="triptime4") String triptime4,
                             @RequestParam(value="triptime5") String triptime5,
                             HttpServletRequest request) {
        Result result = ResultUtil.initResult();
        System.out.println("triptime1:"+ triptime1);
        System.out.println("triptime2:"+ triptime2);
        System.out.println("triptime3:"+ triptime3);
        System.out.println("triptime4:"+ triptime4);
        System.out.println("triptime5:"+ triptime5);
        //判断是否都为空
        if(!triptime1.equals("null") && !triptime1.equals("")) {
            ResultUtil.setSuccess(result, "检查Triptime信息通过", null);
            return result;
        }
        if(!triptime2.equals("null") && !triptime2.equals("")) {
            ResultUtil.setSuccess(result, "检查Triptime信息通过", null);
            return result;
        }
        if(!triptime3.equals("null") && !triptime3.equals("")) {
            ResultUtil.setSuccess(result, "检查Triptime信息通过", null);
            return result;
        }
        if(!triptime4.equals("null") && !triptime4.equals("")) {
            ResultUtil.setSuccess(result, "检查Triptime信息通过", null);
            return result;
        }
        if(!triptime5.equals("null") && !triptime5.equals("")) {
            ResultUtil.setSuccess(result, "检查Triptime信息通过", null);
            return result;
        }
        result.setCode(1);
        result.setMsg("至少选择一项出行时间");
        return result;
    }

}
