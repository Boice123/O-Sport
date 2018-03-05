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

    //保存店铺信息
    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    Result save(@RequestParam(value="shopimg",required = false)MultipartFile shopimg,String realid,String realname, String shopname, Shop shop, Model model, BindingResult bindingResult, HttpServletRequest request) {
        Result result = ResultUtil.initResult();

//        String realid = request.getParameter("realid");
//        String realname = request.getParameter("realname");
//        String shopname = request.getParameter("shopname");
        shop.setShopid(UUID.randomUUID().toString());
        User loginUser = (User) WebUtils.getSessionAttribute(request, "loginUser");
        System.out.println(loginUser.getUsername());
        shop.setUserid(loginUser.getUserid());
        shop.setShopname(shopname);
        loginUser.setRealid(realid);
        loginUser.setRealname(realname);

        String originalFilename = shopimg.getOriginalFilename();if(shopimg!=null && originalFilename!=null && originalFilename.length()>0){
            String pic_path = request.getSession().getServletContext().getRealPath("/")+"update/shop/";
            String newFileName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
            File newFile = new File(pic_path+newFileName);
            if (!newFile.getParentFile().exists()) {
                newFile.getParentFile().mkdirs();
            }
            try{
                shopimg.transferTo(newFile);
            }catch(Exception e) {
                e.getMessage();
            }
            shop.setShopimg("upload/shop/"+newFileName);
        }
        userService.update(loginUser);
        result = shopService.save(shop);
        return result;
//        if(result.getCode() == 0) {
//            model.addAttribute("resultInfo","<h3 color='green'>店铺注册成功</h3>");
//            return "redirect:localhost:8080/#/";
//        }else {
//            model.addAttribute("resultInfo","<h3 color='red'>店铺注册失败</h3>");
//            return "/shop/save";
//        }
    }

    //返回店铺信息
    @ResponseBody
    @RequestMapping(value = "/get", method = RequestMethod.POST)
    Result getShopInfo(String shopid,HttpServletRequest request) {
        Result result = ResultUtil.initResult();
        result = shopService.getShopInfo(shopid);
        return result;
    }


    //检查该用户是否有开店
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

}
