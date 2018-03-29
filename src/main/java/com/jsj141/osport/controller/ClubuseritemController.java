package com.jsj141.osport.controller;
import com.jsj141.osport.domain.Club;
import com.jsj141.osport.domain.Clubuseritem;
import com.jsj141.osport.domain.User;
import com.jsj141.osport.service.ClubService;
import com.jsj141.osport.service.ClubuseritemService;
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
import java.util.UUID;
import java.util.List;

@Controller
@RequestMapping("/clubuseritem")
public class ClubuseritemController {
    private final Logger logger = LoggerFactory.getLogger(ClubuseritemController.class);

    @Autowired
    private ClubuseritemService clubuseritemService;

    @Autowired
    private ClubService clubService;

    /**
     * 保存Clubuseritem信息
     * @param clubid
     * @param clubuseritem
     * @param bindingResult
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    Result save(@RequestParam(value="clubid") String clubid,
                    Clubuseritem clubuseritem,
                    Club club,
                    BindingResult bindingResult,
                    HttpServletRequest request) {
        Result result = ResultUtil.initResult();
        try{
            User loginUser = (User) WebUtils.getSessionAttribute(request, "loginUser");
            clubuseritem.setClubid(clubid);
            clubuseritem.setUserid(loginUser.getUserid());
            //判断用户是否已经加入过该部落
            Clubuseritem c = clubuseritemService.getUseridNClubid(clubuseritem);
            if(c != null) {
                result.setCode(1);
                result.setMsg("您已经关注过该部落");
                return result;
            }

            //判断用户是否要加入自己创建的部落，应该予以阻止
            club.setClubid(clubid);
            Club getclub = clubService.getByClubid(club);
            if(getclub.getClubowner().equals(loginUser.getUserid())) {
                result.setCode(1);
                result.setMsg("您是该部落创始人，不需要再关注");
                return result;
            }

            clubuseritem.setClubuseritemid(UUID.randomUUID().toString());
            clubuseritemService.save(clubuseritem);

            clubService.updateClubPeople(clubid);

            result.setCode(0);
            result.setMsg("关注部落成功");
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
     * 获取用户参与的部落
     * @param userid
     * @param clubuseritem
     * @param bindingResult
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getByUserid", method = RequestMethod.POST)
    Result getClubByClubowner(@RequestParam(value="userid") String userid,
                              Clubuseritem clubuseritem,
                              BindingResult bindingResult,
                              HttpServletRequest request) {
        Result result = ResultUtil.initResult();

        List<Club> getclub = clubService.getUserJoinClub(userid);
        if(getclub.size() != 0) {
            result.setCode(0);
            result.setData(getclub);
            result.setMsg("获取用户参与的部落信息成功");
        }else {
            result.setCode(1);
            result.setMsg("用户没有参与任何部落");
        }
        return result;
    }

}
