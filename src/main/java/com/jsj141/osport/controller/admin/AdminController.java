package com.jsj141.osport.controller.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import com.jsj141.osport.domain.Admins;
import com.jsj141.osport.service.AdminsService;
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
    private AdminsService adminsService;

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    Result save(@Valid Admins admin,HttpServletRequest request, HttpServletResponse response) {
        Result result = ResultUtil.initResult();
        adminsService.save(admin);
        ResultUtil.setSuccess(result, "保存成功", null);
        return result;
    }
}
