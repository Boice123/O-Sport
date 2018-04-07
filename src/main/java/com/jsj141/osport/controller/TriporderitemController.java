package com.jsj141.osport.controller;

import com.jsj141.osport.domain.Triporderitem;
import com.jsj141.osport.service.TriporderitemService;
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
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
@RequestMapping("/triporderitem")
public class TriporderitemController {
    private final Logger logger = LoggerFactory.getLogger(TriporderitemController.class);

    @Autowired
    private TriporderitemService triporderitemService;

    @ResponseBody
    @RequestMapping(value = "/get", method = RequestMethod.POST)
    Result get(@RequestParam(value="triporderitemid") String triporderitemid,
                Triporderitem triporderitem,
                BindingResult bindingResult,
                HttpServletRequest request,
                HttpServletResponse response) {
        Result result = ResultUtil.initResult();
        triporderitem.setTriporderitemid(triporderitemid);
        return triporderitemService.getTriporderitemInfo(triporderitem);
    }
}
