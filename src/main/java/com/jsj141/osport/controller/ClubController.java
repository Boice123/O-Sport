package com.jsj141.osport.controller;
import com.jsj141.osport.domain.Club;
import com.jsj141.osport.domain.User;
import com.jsj141.osport.service.ClubService;
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
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/club")
public class ClubController {
    private final Logger logger = LoggerFactory.getLogger(ClubController.class);

    @Autowired
    private ClubService clubService;

    /**
     * 保存Club信息
     * @param clubname
     * @param clubtab
     * @param club
     * @param bindingResult
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    Result save(@RequestParam(value="clubname") String clubname,
                    @RequestParam(value="clubtab") String clubtab,
                    Club club,
                    BindingResult bindingResult,
                    HttpServletRequest request) {
        Result result = ResultUtil.initResult();
        try{
            User loginUser = (User) WebUtils.getSessionAttribute(request, "loginUser");
            club.setClubid(UUID.randomUUID().toString());
            club.setClubname(clubname);
            club.setClubtab(clubtab);
            club.setClubowner(loginUser.getUserid());
            result = clubService.save(club);

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
//    @ResponseBody
//    @RequestMapping(value = "/update", method = RequestMethod.POST)
//    Result update(@RequestParam(value="shopimg") String shopimg,
//                @RequestParam(value="shopname") String shopname,
//                Shop shop,
//                BindingResult bindingResult,
//                HttpServletRequest request) {
//        Result result = ResultUtil.initResult();
//        try{
//            Shop loginShop = (Shop) WebUtils.getSessionAttribute(request, "loginShop");
//            shop.setShopid(loginShop.getShopid());
//            System.out.println(loginShop.getShopid());
//            shop.setShopname(shopname);
//            shop.setShopimg(shopimg);
//
//            result = shopService.update(shop);
//
//        } catch(Exception e) {
//            e.printStackTrace();
//        }
//        return result;
//    }

    /**
     * 根据Clubid返回Club信息
     * @param clubid
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getClubByClubid", method = RequestMethod.POST)
    Result getShopInfo(
            @RequestParam(value="clubid") String clubid,
            Club club,
            HttpServletRequest request) {
        Result result = ResultUtil.initResult();

        club.setClubid(clubid);
        Club getclub = (Club) clubService.getByClubid(club);
        if(getclub != null) {
            result.setCode(0);
            result.setData(getclub);
            result.setMsg("获取部落数据成功");
        }else {
            result.setCode(1);
            result.setMsg("部落数据不存在");
        }

        return result;
    }

    /**
     * 获取所有部落信息
     * @param club
     * @param bindingResult
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getAll", method = RequestMethod.POST)
    Result getAll(
                              Club club,
                              BindingResult bindingResult,
                              HttpServletRequest request) {
        Result result = ResultUtil.initResult();

        List<Club> clubList = clubService.getAll();
        if(clubList.size() != 0) {
            result.setCode(0);
            result.setData(clubList);
            result.setMsg("获取全部部落信息成功");
        }else {
            result.setCode(1);
            result.setMsg("不存在任何一条部落信息");
        }
        return result;
    }

    /**
     * 获取所有部落信息，带有排序的
     * @param start
     * @param size
     * @param order
     * @param club
     * @param bindingResult
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getAllOrder", method = RequestMethod.POST)
    Result getAll(
            @RequestParam(value="start") int start,
            @RequestParam(value="size") int size,
            @RequestParam(value="order") String order,
            Club club,
            BindingResult bindingResult,
            HttpServletRequest request) {
        Result result = ResultUtil.initResult();

        List<Club> clubList = clubService.listdesc(start,size,order);
        if(clubList.size() != 0) {
            result.setCode(0);
            result.setData(clubList);
            result.setMsg("获取全部部落排序信息成功");
        }else {
            result.setCode(1);
            result.setMsg("不存在任何一条部落信息");
        }
        return result;
    }

    /**
     * 根据部落标签获取相应的部落
     * @param clubtab
     * @param club
     * @param bindingResult
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getByClubtab", method = RequestMethod.POST)
    Result getByClubtab(
            @RequestParam(value="clubtab") String clubtab,
            Club club,
            BindingResult bindingResult,
            HttpServletRequest request) {
        Result result = ResultUtil.initResult();

        club.setClubtab(clubtab);
        List<Club> clubList = clubService.getByClubtab(club);
        if (clubList.size() != 0) {
            result.setCode(0);
            result.setData(clubList);
            result.setMsg("获取对应标签部落信息成功");
        } else {
            result.setCode(1);
            result.setMsg("暂时还没有您选择的部落信息");
        }
        return result;
    }

    /**
     * 获取当前用户创建的Club信息
     * @param clubowner
     * @param club
     * @param bindingResult
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getClubByClubowner", method = RequestMethod.POST)
    Result getClubByClubowner(@RequestParam(value="clubowner") String clubowner,
                              Club club,
                              BindingResult bindingResult,
                              HttpServletRequest request) {
        Result result = ResultUtil.initResult();

        club.setClubowner(clubowner);
        Club getclub = clubService.getByClubowner(club);
        if(getclub != null) {
            result.setCode(0);
            result.setData(getclub);
            result.setMsg("获取部落信息成功");
        }else {
            result.setCode(1);
            result.setMsg("部落信息不存在");
        }
        return result;
    }

    /**
     * 检查该用户是否有开店
     * @param request
     * @return
     */
//    @ResponseBody
//    @RequestMapping(value = "/checkShopExist", method = RequestMethod.POST)
//    Result checkShopExist(HttpServletRequest request) {
//        Result result = ResultUtil.initResult();
//
//        User loginUser = (User) WebUtils.getSessionAttribute(request, "loginUser");
//        System.out.println(loginUser.getUsername());
//        System.out.println(loginUser.getUserid());
//        result = shopService.checkShopExist(loginUser.getUserid());
//        System.out.println(result.getMsg());
//        return result;
//    }

    /**
     * 获得该店铺总成交量
     * @param shopid
     * @param request
     * @return
     */
//    @ResponseBody
//    @RequestMapping(value = "/getTodayTrading", method = RequestMethod.POST)
//    Result getTodayTrading(String shopid, Triporder tripOrder, HttpServletRequest request) {
//        Result result = ResultUtil.initResult();
//        result = shopService.getTodayTrading(shopid);
//        System.out.println(result.getMsg());
//        return result;
//    }


    /**
     * 获得该店铺管理的所有Trip信息
     * @param shopid
     * @param request
     * @return
     */
//    @ResponseBody
//    @RequestMapping(value = "/getManageTrip", method = RequestMethod.POST)
//    Result getManageTrip(String shopid, HttpServletRequest request) {
//        Result result = ResultUtil.initResult();
//
//        result = shopService.getManageTrip(shopid);
//        System.out.println(result.getMsg());
//        return result;
//    }



}
