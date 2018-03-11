package com.jsj141.osport.controller;

import com.jsj141.osport.domain.Admin;
import com.jsj141.osport.service.AdminService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.jsj141.osport.util.Result;
import com.jsj141.osport.util.ResultUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private AdminService adminService;

    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    Result save(@Valid Admin admin, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response) {
        Result result = ResultUtil.initResult();
        result = adminService.save(admin);
        return result;
    }
}
