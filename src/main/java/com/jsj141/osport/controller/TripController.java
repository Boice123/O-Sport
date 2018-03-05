package com.jsj141.osport.controller;

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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.jsj141.osport.domain.Trip;
import  com.jsj141.osport.service.TripService;

@Controller
@RequestMapping("/trip")
public class TripController {
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private TripService tripService;

    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    Result save(@Valid Trip trip, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response) {
        Result result = ResultUtil.initResult();
        result = tripService.save(trip);
        return result;
    }
}
