package com.jsj141.osport.controller;

import com.jsj141.osport.domain.Trip;
import com.jsj141.osport.domain.Triporder;
import com.jsj141.osport.util.Result;
import com.jsj141.osport.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.UUID;

import com.jsj141.osport.domain.Shop;
import com.jsj141.osport.domain.User;
import com.jsj141.osport.service.ShopService;
import com.jsj141.osport.service.UserService;
import com.jsj141.osport.service.TripService;
import com.jsj141.osport.service.TriporderService;
import org.springframework.web.util.WebUtils;

@Controller
@RequestMapping("/shop")
public class ShopController {
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private ShopService shopService;

    @Autowired
    private UserService userService;

    @Autowired
    private TripService tripService;

//    @Autowired
//    private TrainService trainService;

    @Autowired
    private TriporderService triporderService;

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
     * 返回店铺数量
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/count", method = RequestMethod.POST)
    Result count(HttpServletRequest request) {
        Result result = ResultUtil.initResult();
        List<Shop> shopList = shopService.getAllShop();
        result.setCode(0);
        result.setData(shopList.size());
        result.setMsg("获取商店数量成功");
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
     * 获得该店铺总成交量
     * @param shopid
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getTodayTrading", method = RequestMethod.POST)
    Result getTodayTrading(String shopid, Triporder tripOrder, HttpServletRequest request) {
        Result result = ResultUtil.initResult();
        result = shopService.getTodayTrading(shopid);
        System.out.println(result.getMsg());
        return result;
    }

    /**
     * 获得该店铺订单，分页
     * @param shopid
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getOrderPagination", method = RequestMethod.POST)
    Result getOrderPagination(@RequestParam(value="size") int size,
                              @RequestParam(value="start") int start,
                              @RequestParam(value="shopid") String shopid,
                              HttpServletRequest request) {
        Result result = ResultUtil.initResult();
        List<Triporder> triporderList = triporderService.listByShopid(start, size, shopid);
        ResultUtil.setSuccess(result, "获得TripOrder列表排序信息成功", triporderList);
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

    /**
     * 获得商店下的Trip列表,分页,按日期或成交量排序
     * @param size: 获取多少条数据
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getShopManageTripPagination", method = RequestMethod.POST)
    Result getTripList(@RequestParam(value="start") int start,
                       @RequestParam(value="size") int size,
                       @RequestParam(value="shopid") String shopid,
                       @RequestParam(value="order") String order,
                       HttpServletRequest request) {
        Result result = ResultUtil.initResult();
        List<Trip> tripList = tripService.listdesc(start, size, shopid.toString(), order);
        ResultUtil.setSuccess(result, "获得Trip列表排序信息成功", tripList);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/updateOrderStatus", method = RequestMethod.POST)
    Result updateOrderStatus(@RequestParam(value="triporderid") String triporderid,
                           Triporder triporder,
                           HttpServletRequest request) {
        Result result = ResultUtil.initResult();
        triporder.setTriporderid(triporderid);
//        triporder.setTriporderstatus(2);
        triporderService.update(triporder);
        ResultUtil.setSuccess(result, "确认订单成功", null);
        return result;
    }

    /**
     * 获得商店下的Trip列表,分页,按日期或成交量排序
     * @param size: 获取多少条数据
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getAllShopPagination", method = RequestMethod.POST)
    Result getAllShopPagination(
                       @RequestParam(value="start") int start,
                       @RequestParam(value="size") int size,
                       @RequestParam(value="order") String order,
                       HttpServletRequest request) {
        Result result = ResultUtil.initResult();
        List<Shop> shopList = shopService.listdesc(start, size, order);
        ResultUtil.setSuccess(result, "获得Shop列表排序信息成功", shopList);
        return result;
    }

}
