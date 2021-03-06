package com.jsj141.osport.controller;

import com.jsj141.osport.config.Constant;
import com.jsj141.osport.domain.Trip;
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

import com.jsj141.osport.domain.User;
import com.jsj141.osport.domain.Triporder;
import com.jsj141.osport.service.UserService;
import com.jsj141.osport.service.TriporderService;
import com.jsj141.osport.service.TripService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.UUID;
import java.util.List;


@Controller
@RequestMapping("/user")
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private TripService tripService;

    @Autowired
    private TriporderService triporderService;

    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    Result save(@Valid User user,BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        Result result = ResultUtil.initResult();
        //验证码验证
//        String checkCode1 = (String)session.getAttribute("checkcode");
//        if(!checkcode.equalsIgnoreCase(checkCode1)){
//            if(checkcode==null||checkcode==""){
//                result.setCode(1);
//                result.setMsg("验证码不能为空");
//            }else{
//                result.setCode(1);
//                result.setMsg("验证码错误，请重试");
//            }
//            return result;
//        }
        user.setUserid(UUID.randomUUID().toString());
        user.setUserimg("       http://localhost/upload/1804//1234567898765.jpg");
        result = userService.save(user);
        WebUtils.setSessionAttribute(request, "loginUser", user);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    Result signin(@RequestParam(value="password") String password,
                  @RequestParam(value="tel") String tel,
                  User user,
                  HttpServletRequest request) {
        Result result = ResultUtil.initResult();
        user.setPassword(password);
        user.setTel(tel);
        result = userService.signin(user);

        User loginUser = userService.getUserByTel(user);
        WebUtils.setSessionAttribute(request, "loginUser", loginUser);

        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/get", method = RequestMethod.POST)
    Result get(HttpServletRequest request, HttpServletResponse response) {
        Result result = ResultUtil.initResult();
        User loginUser = (User) WebUtils.getSessionAttribute(request, "loginUser");
        User nowUser = userService.get(loginUser);
        result.setCode(0);
        result.setMsg("获取用户信息成功");
        result.setData(nowUser);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/getAll", method = RequestMethod.POST)
    Result getAll(HttpServletRequest request, HttpServletResponse response) {
        Result result = ResultUtil.initResult();
        List<User> list = userService.list();
        result.setCode(0);
        result.setMsg("获取用户信息成功");
        result.setData(list);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    Result delete(@RequestParam(value="userid") String userid,
                  User user) {
        Result result = ResultUtil.initResult();
        user.setUserid(userid);
        result = userService.delete(user);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    Result logout(HttpServletRequest request, HttpServletResponse response) {
        Result result = ResultUtil.initResult();
        WebUtils.setSessionAttribute(request, "loginUser", null);
        result.setCode(0);
        result.setMsg("退出登录成功");
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    Result update(@RequestParam(value="username") String username,
                  @RequestParam(value="userimg") String userimg,
                  User user,
                  HttpServletRequest request,
                  HttpServletResponse response) {
        Result result = ResultUtil.initResult();
        User loginUser = (User) WebUtils.getSessionAttribute(request, "loginUser");
        user.setUsername(username);
        user.setUserimg(userimg);
        user.setUserid(loginUser.getUserid());
        result = userService.update(user);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/updatepassword", method = RequestMethod.POST)
    Result updatepassword(@RequestParam(value="newpwd") String newpwd,
                  @RequestParam(value="oldpwd") String oldpwd,
                  User user,
                  HttpServletRequest request,
                  HttpServletResponse response) {
        Result result = ResultUtil.initResult();
        User loginUser = (User) WebUtils.getSessionAttribute(request, "loginUser");
        user.setUserid(loginUser.getUserid());
        User nowUser = userService.get(user);
        if(nowUser.getPassword().equals(oldpwd)) {
            if(newpwd.equals(nowUser.getPassword())){
                result.setCode(1);
                result.setMsg("原密码与新密码不能相同");
            }else {
                nowUser.setPassword(newpwd);
                result= userService.update(nowUser);
            }
        }else {
            result.setCode(1);
            result.setMsg("原密码错误");
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/checkUserTelExist", method = RequestMethod.POST)
    Result checkUserTelExist(@Valid User user, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response) {
        Result result = ResultUtil.initResult();
        result = userService.checkUserTelExist(user);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/getUserTripOrder", method = RequestMethod.POST)
    Result getUserTripOrder(
            @RequestParam(value="triporderstatus") String triporderstatus,
            Triporder order,
            HttpServletRequest request) {
        Result result = ResultUtil.initResult();
        User loginUser = (User) WebUtils.getSessionAttribute(request, "loginUser");
        List<Triporder> triporderList = triporderService.getUserTripOrder(loginUser.getUserid(),triporderstatus);
        for(int i=0; i < triporderList.size(); i++) {
            String id = triporderList.get(i).getTripid();
            Trip t = new Trip();
            t.setTripid(id);
            Trip rt = tripService.getTripInfo(t);
            triporderList.get(i).setTrip(rt);
        }
        result.setCode(0);
        result.setData(triporderList);
        result.setMsg("获取用户订单成功");
        return result;
    }

}