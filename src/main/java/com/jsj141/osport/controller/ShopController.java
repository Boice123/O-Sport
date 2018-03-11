package com.jsj141.osport.controller;

import com.jsj141.osport.util.Result;
import com.jsj141.osport.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import java.util.UUID;
import java.io.*;
import org.apache.commons.io.FileUtils;
import com.jsj141.osport.domain.Shop;
import com.jsj141.osport.domain.User;
import com.jsj141.osport.service.ShopService;
import com.jsj141.osport.service.UserService;
import org.springframework.web.util.WebUtils;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/shop")
public class ShopController {
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private ShopService shopService;

    @Autowired
    private UserService userService;

    /**
     * 保存店铺信息
     * @param shopimg
     * @param realid
     * @param realname
     * @param shopname
     * @param shop
     * @param bindingResult
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    Result save(@RequestParam(value="shopimg") String shopimg,
                    @RequestParam(value="realid") String realid,
                    @RequestParam(value="realname") String realname,
                    @RequestParam(value="shopname") String shopname,
                    Shop shop,
                    BindingResult bindingResult,
                    HttpServletRequest request) {
        Result result = ResultUtil.initResult();
        try{
            User loginUser = (User) WebUtils.getSessionAttribute(request, "loginUser");
            shop.setShopid(UUID.randomUUID().toString());
            shop.setUserid(loginUser.getUserid());
            shop.setShopname(shopname);
            shop.setShopimg(shopimg);
            loginUser.setRealid(realid);
            loginUser.setRealname(realname);

            userService.update(loginUser);
            result = shopService.save(shop);

        } catch(Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 修改店铺信息
     * @param shopimg
     * @param shopname
     * @param shop
     * @param bindingResult
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    Result update(@RequestParam(value="shopimg") String shopimg,
                @RequestParam(value="shopname") String shopname,
                Shop shop,
                BindingResult bindingResult,
                HttpServletRequest request) {
        Result result = ResultUtil.initResult();
        try{
            Shop loginShop = (Shop) WebUtils.getSessionAttribute(request, "loginShop");
            shop.setShopid(loginShop.getShopid());
            System.out.println(loginShop.getShopid());
            shop.setShopname(shopname);
            shop.setShopimg(shopimg);

            result = shopService.update(shop);

        } catch(Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 返回店铺信息
     * @param shopid
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/get", method = RequestMethod.POST)
    Result getShopInfo(String shopid,HttpServletRequest request) {
        Result result = ResultUtil.initResult();

        result = shopService.getShopInfo(shopid);
        WebUtils.setSessionAttribute(request, "loginShop", result.getData());
        return result;
    }

    /**
     * 检查该用户是否有开店
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/checkShopExist", method = RequestMethod.POST)
    Result checkShopExist(HttpServletRequest request) {
        Result result = ResultUtil.initResult();

        User loginUser = (User) WebUtils.getSessionAttribute(request, "loginUser");
        System.out.println(loginUser.getUsername());
        System.out.println(loginUser.getUserid());
        result = shopService.checkShopExist(loginUser.getUserid());
        System.out.println(result.getMsg());
        return result;
    }

    /**
     * 获得该店铺管理的所有Trip信息
     * @param shopid
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getManageTrip", method = RequestMethod.POST)
    Result getManageTrip(String shopid, HttpServletRequest request) {
        Result result = ResultUtil.initResult();

        result = shopService.getManageTrip(shopid);
        System.out.println(result.getMsg());
        return result;
    }

}
