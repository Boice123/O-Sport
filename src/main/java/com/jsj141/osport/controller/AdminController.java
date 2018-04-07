package com.jsj141.osport.controller;

import com.jsj141.osport.domain.Admin;

import com.jsj141.osport.domain.User;
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

import com.jsj141.osport.service.AdminService;
import org.springframework.web.util.WebUtils;

import java.util.UUID;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private AdminService adminService;

    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    Result save(@RequestParam(value="adminname") String adminname,
                @RequestParam(value="adminpassword") String adminpassword,
                @RequestParam(value="admintel") String admintel,
                Admin admin,
                BindingResult bindingResult,
                HttpServletRequest request,
                HttpServletResponse response) {
        Result result = ResultUtil.initResult();
        admin.setAdminid(UUID.randomUUID().toString());
        admin.setAdminname(adminname);
        admin.setAdminpassword(adminpassword);
        admin.setAdmintel(admintel);
        result = adminService.save(admin);
        WebUtils.setSessionAttribute(request, "loginAdmin", admin);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    Result signin(@RequestParam(value="password") String password,
                  @RequestParam(value="tel") String tel,
                  Admin admin,
                  HttpServletRequest request) {
        Result result = ResultUtil.initResult();
        admin.setAdminpassword(password);
        admin.setAdmintel(tel);
        result = adminService.signin(admin);

        Admin loginAdmin = adminService.getAdminByTel(admin);
        WebUtils.setSessionAttribute(request, "loginAdmin", loginAdmin);

        return result;
    }
}
